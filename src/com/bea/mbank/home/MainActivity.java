package com.bea.mbank.home;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.bea.application.APKUpdateManager;
import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.database.codemodel.MMP_PRODUCT_CATEGORY;
import com.bea.mbank.R;
import com.bea.mbank.edit.step1.Step1HomeFragment;
import com.bea.mbank.edit.step2.Step2HomeFragment;
import com.bea.mbank.helper.counter.LoanCounterFragment;
import com.bea.mbank.home.MainMenuActivity.MenueMessage;
import com.bea.mbank.mytask.CollecteListFragment;
import com.bea.mbank.mytask.CommitListFragment;
import com.bea.mbank.mytask.DraftListFragment;
import com.bea.mbank.paxb.PaxbHomeFragment;
import com.bea.mbank.product.ProductCollectFragment;
import com.bea.mbank.product.ProductHomeFragment;
import com.bea.mbank.settings.SetSuccess;
import com.bea.mbank.settings.SettingFragment;
import com.bea.remote.BeaRemoteErrorManager;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;

import xc.android.activity.XCBaseGroupActivity;
import xc.android.application.XCApplication;
import xc.android.remote.IRemoteResponse;
import xc.android.remote.json.JsonReqClient;
import xc.android.utils.XCToolkit;

import com.cyg.viewinject.annotation.ViewInject;

public class MainActivity extends XCBaseGroupActivity implements IRemoteResponse{
	APKUpdateManager appVersionManager;
	@Override
	protected View onCreateView(LayoutInflater arg0) {
		return inflater.inflate(R.layout.ac_apphome, null);
	}

	@ViewInject(R.id.content) FrameLayout content;
	@ViewInject(R.id.menu) FrameLayout menuLayout;
	@Override
	protected void onInitContentView() {
		// �������˵���Ϣ�¼�
		observeMessage(ConstDefined.MenuClickReqKey);
		observeMessage(ConstDefined.APPHomeChangeActionKey);
		observeMessage(ConstDefined.APPNewVersionActionKey);

		menuLayout.setLayoutParams(new LayoutParams((int)(XCToolkit.getScreenWidth()*.1875f), 
				                       LayoutParams.MATCH_PARENT));
		// ��ʼ��Ӧ�����˵�
		Intent intent = new Intent(this, MainMenuActivity.class);
		exchange2SubActivity(intent, menuLayout);
		
		//����°汾
		appVersionManager = new APKUpdateManager(this, true);
		//ע���°汾֪ͨ�㲥  
		IntentFilter intentFilter = new IntentFilter();  
		intentFilter.addAction(ConstDefined.APPHomeChangeActionKey);  
		intentFilter.addAction(ConstDefined.DeviceTimeSetActionKey);  
        LocalBroadcastManager.getInstance(XCApplication.getInstance()).
        		registerReceiver(mBroadcastReceiver, intentFilter); 
	}

	/**
	 * @brief ��Ϣ����������������Ϣ�������ô˺���
	 * @param msgkey
	 *            ��Ϣ�ؼ���
	 * @param msg
	 *            ��Ϣ��Ϣ�����͹�������Ϣʵ������
	 */
	int mCurrentIndex = -1;

	@Override
	public void onReceiveMessage(final String mkey, final Object msgObject) {	
		// ����Ƿ��б༭ҳ������ݻ�δ����
		if (BeaAppSettings.isDataChanged()) {
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setCancelable(false);
			builder.setMessage("��Ҫ�����ѱ༭�����ݽ����л���");
			builder.setPositiveButton("��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					exchange2SubModuel(mkey, msgObject);
				}
			});
			builder.setNegativeButton("��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					menuClickEventIgnored(mkey, msgObject);
				}
			});
			builder.show();
		} else {
			exchange2SubModuel(mkey, msgObject);
		}
	}

	private void exchange2SubModuel(String mkey, Object msgObject) {
		BeaAppSettings.setOriginData(null);
		//�Ӳ˵������¼�����������
		if (ConstDefined.APPNewVersionActionKey.equals(mkey)) {
			//���߲˵�ҳ����²˵�״̬
//			MenueMessage menuMsg = MainMenuActivity.menueMessage(-1, -1);
//			sendMessage(ConstDefined.MenuClickResKey, menuMsg);
			appVersionManager.checkUpdateInfo();
			return;
		}
		//�Ӳ˵������¼�����������
		else if (ConstDefined.MenuClickReqKey.equals(mkey)) {
			MenueMessage msg = (MenueMessage)msgObject;
			// ��Ӧ�ҵ�����˵�����ݸ��¼�
			if (msg.actionUrl.equals(ConstDefined.mytaskDraftUrl)) {
				exchange2SubActivity(DraftListFragment.class, null);
			}
			// ��Ӧ�ҵ�����˵���������¼�
			else if (msg.actionUrl.equals(ConstDefined.mytaskOutgoUrl)) {
				exchange2SubActivity(CommitListFragment.class, null);
			}
			// ��Ӧ�ҵ�����˵���������¼�
			else if (msg.actionUrl.equals(ConstDefined.mytaskCollectUrl)) {
				exchange2SubActivity(CollecteListFragment.class, null);
			}
			// ��Ӧ��Ʒ����
			else if (msg.actionUrl.equals(ConstDefined.productUrl+0)) {//��ѡ��Ʒ
				exchange2SubActivity(ProductCollectFragment.class, null);
			}
			// ��Ӧ��Ʒ����
			else if (msg.actionUrl.startsWith(ConstDefined.productUrl)) {
				MMP_PRODUCT_CATEGORY category = (MMP_PRODUCT_CATEGORY)msg.msgObject;
				Bundle bundle = new Bundle();
				bundle.putSerializable("category", category);
				exchange2SubActivity(ProductHomeFragment.class, bundle);
			}
			// ��Ӧ�����޵�Ѻ����˵����¼�
			else if (msg.actionUrl.equals(ConstDefined.loanGRWDYUrl)) {
				Bundle bundle = new Bundle();
				exchange2SubActivity(Step1HomeFragment.class, bundle);
			}
			// ��Ӧƽ���ű��˵����¼�
			else if (msg.actionUrl.equals(ConstDefined.loanPAXBUrl)) {
				exchange2SubActivity(PaxbHomeFragment.class, null);
			}
			// ��Ӧ�������ܲ˵�������������
			else if (msg.actionUrl.equals(ConstDefined.utilsCalculatorUrl)) {
				exchange2SubActivity(LoanCounterFragment.class, null);
			}
			//���߲˵�ҳ����²˵�״̬
			sendMessage(ConstDefined.MenuClickResKey, msg);
		} else if (ConstDefined.APPHomeChangeActionKey.equals(mkey) && msgObject instanceof Message) {
			Message msg = (Message)msgObject;
			// ҳ������ת���༭ҳ��
			if (msg.what == ConstDefined.GRWDYCreditActionTag) {
				GrwdyHomeBO detail = (GrwdyHomeBO)msg.obj;
				Bundle bundle = new Bundle();
				boolean isPAXB = false;//
				if (null != detail) {
					isPAXB = detail.getIsPinAnXinBaoData();//
					bundle.putSerializable(ConstDefined.bundleSerializableParamKey, detail);
				}
				if (null != detail && !detail.getIsPartRequiredData() && detail.getIsSMSValid()) {
					exchange2SubActivity(Step2HomeFragment.class, bundle);
				} else {
					exchange2SubActivity(Step1HomeFragment.class, bundle);
				}
				
				//���߲˵�ҳ����²˵�״̬
				MenueMessage menuMsg = MainMenuActivity.menueMessage(
				isPAXB?ConstDefined.loanPAXBUrl:ConstDefined.loanGRWDYUrl);//
				sendMessage(ConstDefined.MenuClickResKey, menuMsg);
			}
			// ϵͳ����
			else if (msg.what == ConstDefined.SettingTag) {
				exchange2SubActivity(SettingFragment.class, null);
			}else if (msg.what == ConstDefined.SetSuccessTag) {
				exchange2SubActivity(SetSuccess.class, null);
			}
		} 
		// �л����հ�ҳ��
		else if (ConstDefined.APPHomeChangeActionKey.equals(mkey) &&
				 ((Integer) msgObject) == ConstDefined.BlankActivityTag) {
			//���߲˵�ҳ����²˵�״̬
			MenueMessage menuMsg = MainMenuActivity.menueMessage(null);
			sendMessage(ConstDefined.MenuClickResKey, menuMsg);
			exchange2SubActivity(null, content);
		}
	}

	private void menuClickEventIgnored(String mkey, Object msgObject) {
		//�Ӳ˵������¼����������󱻺���
		if (ConstDefined.MenuClickReqKey.equals(mkey)) {
			MenueMessage msg = (MenueMessage)msgObject;
			msg.actionUrl = msg.actionOldUrl;
			//���߲˵�ҳ����²˵�״̬
			sendMessage(ConstDefined.MenuClickResKey, msg);
		}
	}
	private void exchange2SubActivity(Class<?> homeFragment, Bundle bundle) {
		Intent intent = new Intent(this, BeaBaseActivity.class);
		if (null != homeFragment) {
			intent.putExtra(ConstDefined.homeFragmentKey, homeFragment.getName());
		}
		if (null != bundle) {
			intent.putExtras(bundle);
		}
		exchange2SubActivity(intent, content);
	}

	/*---------------------------------------------------------------------------------*/
	/* ����Ļ���м�����ʾ��ʱ�򣬵���հ״�������ʧ�Ĵ��� */
	/*---------------------------------------------------------------------------------*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit2Home();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit2Home() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(false);
		builder.setMessage("��Ҫ�˳�Ӧ�ó�����");
		builder.setPositiveButton("��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				exitApplication();
			}
		});
		builder.setNegativeButton("��", null);
		builder.show();
	}
	
	private Handler msgHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			onBroadcastMessage(msg.what, (String)msg.obj);
			super.handleMessage(msg);
		}
	};
	
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){  
        @Override  
        public void onReceive(Context context, Intent intent) {  
        	String action = intent.getAction();  
        	if(action.equals(ConstDefined.APPHomeChangeActionKey)){  
        		Message msg = new Message();
        		msg.what = intent.getIntExtra("errorCode", -1);
        		msg.obj = intent.getStringExtra("errorMsg");
        		msgHandler.sendMessage(msg);
            } else if(action.equals(ConstDefined.DeviceTimeSetActionKey)){  
        		Message msg = new Message();
        		msg.what = intent.getIntExtra("errorCode", -123456);
        		msgHandler.sendMessage(msg);
            }
        }   
    }; 
    private JsonReqClient jsonReqClient = new JsonReqClient(this);
    private void onBroadcastMessage(int errorCode, String errorMsg) {
		if (BeaRemoteErrorManager.RemoteError_UserReLogin == errorCode) {
			final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setCancelable(false);
			builder.setMessage(errorMsg);
			builder.setPositiveButton("֪����", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					startActivity(new Intent(MainActivity.this, LoginActivity.class));
					MainActivity.this.finish();
				}
			});
			builder.show();
		} else {//�豸��ʱ�䱻���ã���ʱ��Ҫ�ӷ���������ȡ��ǰʱ��
			jsonReqClient.jsonRequest(errorCode, "ͨ�ýӿ�", 
			JsonRemoteBO.reqParam(ConstDefined.systemTime, null), 0);
		}
	}

	@Override
	public void onNetConnectFailed(int arg0, String arg1) {}
	@Override
	public void onResponsFailed(int arg0, String arg1) {}
	@Override
	public void onResponsSuccess(int arg0, Object arg1) {
		JSONObject jObject = (JSONObject)arg1;
		String systime = jObject.optString("system_time");
		BeaAppSettings.setSystemTimeDiffStep(systime);
	}
}