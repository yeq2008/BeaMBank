package com.bea.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.bea.mbank.R;
import com.bea.remote.BeaRemoteSettings;
import com.bea.remote.JsonRemoteBO;
import com.huawei.svn.sdk.thirdpart.SvnHttpClient;

import xc.android.application.XCApplication;
import xc.android.file.XCFile;
import xc.android.remote.IRemoteResponse;
import xc.android.remote.json.JsonReqClient;
import xc.android.utils.XCToolkit;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

public class APKUpdateManager implements IRemoteResponse{
	private Context mContext;
	// ��ʾ��
	private String updateMsg = "�����µ������Ŷ���׿����ذ�~";

	// ���صİ�װ��url
	private String apkUrl;
	private Dialog noticeDialog;
	private Dialog downloadDialog;
	/* ���ذ���װ·�� */
//	private static final String savePath = XCSdCardDirUtils.getDataRootDir();
	private static final String savePath = BeaApplication.workPath();
	private static final String saveFileName = savePath + File.separator + "beaupdate.apk";

	/* ��������֪ͨuiˢ�µ�handler��msg���� */
	private ProgressBar mProgress;
	private static final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	private int progress;
	private Thread downLoadThread;
	private boolean interceptFlag = false;

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				break;
			case DOWN_OVER:
				installApk();
				break;
			default:
				break;
			}
		};
	};

	//����Ƿ��Ѱ�װ�˱�Ӧ��
	public static boolean appIsInstalled(Context context,String pageName){
		PackageInfo packageInfo;

        try {
            packageInfo = context.getPackageManager().getPackageInfo(pageName, 0);
        } catch (Exception e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if(packageInfo ==null){
            return false;
        }else{
            return true;
        }
	}
	
	public APKUpdateManager(Context context, boolean reqNewPackage) {
		this.mContext = context;
		if (reqNewPackage) {
			requestActivityData();
		}
		//ɾ���Ѿ����ڵ����ذ�װ��
		new File(saveFileName).delete();
	}

	// �ⲿ�ӿ�����Activity����
	boolean isForcedUpdate = false;
	public void checkUpdateInfo() {
		if (null != apkUrl && apkUrl.length() > 0) {
			if (isForcedUpdate) {
				showForcedNoticeDialog();
			} else {
				showNoticeDialog();
			}
		} else {
			requestActivityData();
		}
	}

	// �ⲿ�ӿ�����Activity����
	public void installNewAPK(String apkUrl, String appName) {
		if ((null != apkUrl && apkUrl.length() > 0) && null != appName) {
			this.apkUrl = apkUrl;
			showNoticeDialog_installNew(appName);
		}
	}
		
	private void showNoticeDialog() {
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle("����汾����");
		builder.setMessage(updateMsg);
		builder.setPositiveButton("����", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showDownloadDialog("����汾����");
			}
		});
		builder.setNegativeButton("�Ժ���˵", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		noticeDialog = builder.create();
		noticeDialog.setCancelable(false);
		noticeDialog.setCanceledOnTouchOutside(false);
		noticeDialog.show();
	}
	
	private void showForcedNoticeDialog() {
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle("����汾����");
		builder.setMessage(updateMsg);
		builder.setPositiveButton("����", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showDownloadDialog("        ����汾����        ");
			}
		});
		noticeDialog = builder.create();
		noticeDialog.setCancelable(false);
		noticeDialog.setCanceledOnTouchOutside(false);
		noticeDialog.show();
	}

	private void showNoticeDialog_installNew(final String appName) {
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle("��װӦ��");
		builder.setMessage(String.format("��Ҫ��װ��%s����", appName));
		builder.setPositiveButton("��װ", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				showDownloadDialog(String.format("���ذ�װ��%s��", appName));
			}
		});
		builder.setNegativeButton("�Ժ���˵", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		noticeDialog = builder.create();
		noticeDialog.setCancelable(false);
		noticeDialog.setCanceledOnTouchOutside(false);
		noticeDialog.show();
	}
	
	private void showDownloadDialog(String title) {
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setTitle(title);

		final LayoutInflater inflater = LayoutInflater.from(mContext);
		View v = inflater.inflate(R.layout.v_update_progress, null);
		mProgress = (ProgressBar) v.findViewById(R.id.progress);

		builder.setView(v);
		if (!isForcedUpdate) {
			builder.setNegativeButton("ȡ��", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					interceptFlag = true;
				}
			});
		}
		downloadDialog = builder.create();
		downloadDialog.setCancelable(false);
		downloadDialog.setCanceledOnTouchOutside(false);
		downloadDialog.show();

		downloadApk();
	}

	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				//�Ȼ�ȡ�ļ��ܳ���
				HttpClient client = null;
				if (XCFile.isUseMdmSvn()) {
					client = new SvnHttpClient();
				} else {
					client = new DefaultHttpClient();
				}
				HttpGet httpGet = new HttpGet();
				httpGet.setURI(new URI(apkUrl));
				HttpResponse response = client.execute(httpGet);
				HttpEntity entity  = response.getEntity();

				long fileSize = entity.getContentLength();
				InputStream is = entity.getContent();
				
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
				FileOutputStream fos = new FileOutputStream(saveFileName);

				int count = 0;
				byte buf[] = new byte[10240];

				do {
					int numread = is.read(buf);
					count += numread;
					progress = (int) (((float) count / fileSize) * 100);
					// ���½���
					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numread <= 0) {
						// �������֪ͨ��װ
						mHandler.sendEmptyMessage(DOWN_OVER);
						break;
					}
					fos.write(buf, 0, numread);
				} while (!interceptFlag);// ���ȡ����ֹͣ����.

				fos.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * ����apk
	 * 
	 * @param url
	 */
	private void downloadApk() {
		interceptFlag = false;
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}

	/**
	 * ��װapk
	 * 
	 * @param url
	 */
	private void installApk() {
		if (null != downloadDialog && downloadDialog.isShowing()) {
			downloadDialog.dismiss();
			downloadDialog = null;
		}
		
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		try {
			apkfile = new File(saveFileName);
			apkfile.setReadable(true, false);
			apkfile.setExecutable(true, false);
			Uri installUri = Uri.fromFile(apkfile);  
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setDataAndType(installUri,"application/vnd.android.package-archive");
			((Activity)mContext).startActivity(i);
		} catch (Exception e) {
			XCToolkit.showToast("��װ����װʧ�ܣ�"+e.getLocalizedMessage());
		}
	}

	JsonReqClient reqClient = new JsonReqClient(this);
	String currentVersion = null;
	protected void requestActivityData() {
		//��ȡpackagemanager��ʵ��       
		PackageManager packageManager = XCApplication.getInstance().getPackageManager();     
		//getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ  
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(XCApplication.getInstance().getPackageName(), 0);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (null != packInfo) {
			Map<String, Object> params = new HashMap<String, Object>();
			String versionString = currentVersion = packInfo.versionName;
			params.put("version_code", versionString);
			params.put("code", versionString.replaceAll("\\.", ""));
			params.put("bundle", packInfo.packageName);
			params.put("plant_id", ConstDefined.AppPlantID);
			params.put("isMDMVersion", BeaApplication.isMDMApk()?1:0);

			reqClient.jsonRequest(0, "�汾���", 
			JsonRemoteBO.reqParam(ConstDefined.CheckAppVersion, params), Integer.MAX_VALUE);
		}
	}

	@Override
	public void onResponsSuccess(int actionTag, Object resData) {
		JSONObject jObject = (JSONObject)resData;
		//url	�汾��ַ
		apkUrl = BeaRemoteSettings.downloadUrl(jObject.optString("url"));
		//apk_name	APK�ļ���
		String apk_name = jObject.optString("apk_name");
		//version_code	�汾��
		String version_code = jObject.optString("version_code");
		//version_date	�汾ʱ��
		String version_date = jObject.optString("version_date");
		//remark	��ע
		String remark = jObject.optString("remark");
		if (null != remark && remark.length() > 0) {
			updateMsg = remark;
		}
		isForcedUpdate = (1 == jObject.optInt("forced_updating"));
		if (!isForcedUpdate && !version_code.equals(currentVersion)) {
			//���͹㲥��֪ͨ���°汾��
	    	Intent intent = new Intent(ConstDefined.APPNewVersionActionKey);
	    	intent.putExtra("versionInfo", jObject.toString());
	        LocalBroadcastManager.getInstance(XCApplication.getInstance()).sendBroadcast(intent);
		} else if (isForcedUpdate && !version_code.equals(currentVersion)) {
			checkUpdateInfo();
		}
	}

	@Override
	public void onResponsFailed(int arg0, String arg1) {}
	@Override
	public void onNetConnectFailed(int arg0, String arg1) {}
}
