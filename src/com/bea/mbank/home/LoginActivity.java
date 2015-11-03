package com.bea.mbank.home;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.android.switchbutton.SwitchButton;
import com.android.widgets.XCImageButton;
import com.bea.application.BeaAppSettings;
import com.bea.application.BeaApplication;
import com.bea.application.ConstDefined;
import com.bea.database.SynchManager;
import com.bea.database.SynchManager.OriginDbCopyHandler;
import com.bea.mbank.R;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.user.LoginInfoBO;
import com.bea.remote.models.user.ModuelInfoBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;
import com.huawei.anyoffice.sdk.SDKContext;
import com.huawei.anyoffice.sdk.login.LoginAgent;
import com.huawei.anyoffice.sdk.login.SSOIntent;
import com.huawei.anyoffice.sdk.login.LoginParam.UserInfo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import xc.android.activity.XCBaseHttpActivity;
import xc.android.application.XCApplication;
import xc.android.database.DbBaseModel;
import xc.android.database.DbQueryManager;
import xc.android.file.XCFile;
import xc.android.remote.HWMdmManager;
import xc.android.utils.XCLog;
import xc.android.utils.XCMD5;
import xc.android.utils.XCToolkit;
import xc.android.widgets.XCMDMProgressDialog;
import xc.android.widgets.XCProgressDialog;

/**
 * @author cuiyuguo 登录页面
 */
public final class LoginActivity extends XCBaseHttpActivity {

	@Override
	protected View onCreateView(LayoutInflater inflater) {
		return inflater.inflate(R.layout.ac_applogin, null);
	}

	@ViewInject(R.id.aa_et_username)
	EditText userName;
	@ViewInject(R.id.aa_et_password)
	EditText passWord;
	@ViewInject(R.id.mdmSvnSettingBtn)
	XCImageButton mdmSvnSettingBtn;
	@ViewInject(R.id.switchButton)
	SwitchButton switchButton;

	@Override
	protected void onInitContentView() {
		int screenWidth = getWindowManager().getDefaultDisplay().getWidth();// 真实分辨率,
																			// 宽
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight();// 真实分辨率,高
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120(ldpi)/160(mdpi)/213(tvdpi)/240(hdpi)/320(xhdpi)）
		Toast.makeText(
				this,
				"真实分辨率：" + screenWidth + "*" + screenHeight + "  每英寸:"
						+ densityDPI, 10).show();

		userName.setText(BeaAppSettings.getUserName());
		passWord.setImeOptions(EditorInfo.IME_ACTION_GO);
		passWord.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_GO
						|| (null != event && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
					onLoginButtonEvent(mdmSvnSettingBtn);
				}
				return false;
			}
		});
		// 获取AnyOffice传入参数
		// if (XCFile.isUseMdmSvn()) {
		// SSOIntent ssoIntent = new SSOIntent(getIntent());
		// if (ssoIntent.getUsername() != null && null !=
		// ssoIntent.getPassword()) {
		// Bundle bundle = XCApplication.getInstance().getHWMdmConfig();
		// bundle.putString(HWMdmManager.HWMdm_UserName,
		// ssoIntent.getUsername().trim());
		// BeaAppSettings.setSvnUserName(ssoIntent.getUsername().trim());
		// bundle.putString(HWMdmManager.HWMdm_PassWord,
		// ssoIntent.getPassword().trim());
		// BeaAppSettings.setSvnPword(ssoIntent.getPassword().trim());
		// }
		// } else {
		// mdmSvnSettingBtn.setVisibility(View.GONE);
		// }
		switchButton.setChecked(BeaAppSettings.getNoneLoginSwitch());
		switchButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				BeaAppSettings.setNoneLoginSwitch(isChecked);
			}
		});
	}

	@OnClick(R.id.loginButton)
	public void onLoginButtonEvent(View view) {
		if (switchButton.isChecked()) {
			ArrayList<ModuelInfoBO> list = new ArrayList<ModuelInfoBO>();
			ModuelInfoBO info1 = new ModuelInfoBO();
			info1.setEnable(1);
			info1.setModule_name("我的任务");
			info1.setParent_id("0");
			info1.setModule_id("001");
			info1.setUrl(ConstDefined.mytaskUrl);
			list.add(info1);

			info1 = new ModuelInfoBO();
			info1.setEnable(1);
			info1.setModule_name("草稿件");
			info1.setModule_id("0011");
			info1.setParent_id("001");
			info1.setUrl(ConstDefined.mytaskDraftUrl);
			list.add(info1);

			info1 = new ModuelInfoBO();
			info1.setEnable(1);
			info1.setModule_name("待发件");
			info1.setParent_id("001");
			info1.setModule_id("0012");
			info1.setUrl(ConstDefined.mytaskOutgoUrl);
			list.add(info1);

			info1 = new ModuelInfoBO();
			info1.setEnable(1);
			info1.setModule_name("催收件");
			info1.setParent_id("001");
			info1.setModule_id("0013");
			info1.setUrl(ConstDefined.mytaskCollectUrl);
			list.add(info1);

			info1 = new ModuelInfoBO();
			info1.setEnable(1);
			info1.setModule_name("产品中心");
			info1.setParent_id("0");
			info1.setModule_id("002");
			info1.setUrl(ConstDefined.productUrl);
			list.add(info1);

			info1 = new ModuelInfoBO();
			info1.setEnable(1);
			info1.setModule_name("零售贷款业务");
			info1.setParent_id("0");
			info1.setModule_id("003");
			info1.setUrl(ConstDefined.loanUrl);
			list.add(info1);

			info1 = new ModuelInfoBO();
			info1.setEnable(1);
			info1.setModule_name("个人无抵押");
			info1.setParent_id("003");
			info1.setModule_id("0031");
			info1.setUrl(ConstDefined.loanGRWDYUrl);
			list.add(info1);

			info1 = new ModuelInfoBO();
			info1.setEnable(1);
			info1.setModule_name("平安信保");
			info1.setParent_id("003");
			info1.setModule_id("0032");
			info1.setUrl(ConstDefined.loanPAXBUrl);
			list.add(info1);

			info1 = new ModuelInfoBO();
			info1.setEnable(1);
			info1.setModule_name("辅助功能");
			info1.setParent_id("0");
			info1.setModule_id("004");
			info1.setUrl(ConstDefined.utilsUrl);
			list.add(info1);

			info1 = new ModuelInfoBO();
			info1.setEnable(1);
			info1.setModule_name("贷款计算器");
			info1.setParent_id("004");
			info1.setModule_id("0041");
			info1.setUrl(ConstDefined.utilsCalculatorUrl);
			list.add(info1);

			DbQueryManager.saveAll(list);

			redirect();
			return;
		}
		if ("".equals(userName.getText().toString().trim())) {
			XCToolkit.showToast("用户名不能为空");
			return;
		}
		if ("".equals(passWord.getText().toString().trim())) {
			XCToolkit.showToast("密码不能为空");
			return;
		}

		onRequestLoginData();
	}

	private void onRequestLoginData() {
		// 组织登录请求参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", userName.getText().toString());
		map.put("password", XCMD5.MD5(passWord.getText().toString()));
		map.put("plant_id", ConstDefined.AppPlantID);

		jsonRequest(0, "用户登录",
				JsonRemoteBO.reqParam(ConstDefined.UserLogin, map));
	}

	private void onResponseLoginData(Object resObject) {
		LoginInfoBO info = (LoginInfoBO) resObject;

		// 登录成功，将用户名保存下来
		BeaAppSettings.setUserName(info.getUserinfo().getUser_name());
		DbQueryManager.deleteAllDB(ModuelInfoBO.class);
		DbQueryManager.saveAll(info.getModule_info());

		// 进行码表同步
		SynchManager.startSynch();

		String xsdpdf = "xsd";
		String paxbpdf = "paxb";
		// 拷贝项目路径下文件到安装目录
		copyFileFromProjectToInstall(xsdpdf);
		copyFileFromProjectToInstall(paxbpdf);
		// info.getUserinfo().setChange_pw_time("20150707182400");
		// info.getUserinfo().setIs_first_login("1");
		// 首次登录
		// 强制修改密码
		if (null != info.getUserinfo().getIs_first_login()
				&& "1".equals(info.getUserinfo().getIs_first_login())) {
			Builder builder = new Builder(LoginActivity.this);
			builder.setTitle("提示");
			builder.setMessage("您是首次登录,请修改密码！");
			builder.setPositiveButton(android.R.string.ok,
					new AlertDialog.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							LoginDialog loginDialog = new LoginDialog(
									LoginActivity.this);
							loginDialog.show();
							passWord.setText("");
						}
					});
			builder.setCancelable(false);
			builder.create();
			builder.show();
			return;
		}

		try {
			Calendar lastCalendar = Calendar.getInstance();
			Calendar nowCalendar = Calendar.getInstance();

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			Date lastdate = format
					.parse(info.getUserinfo().getChange_pw_time());
			lastCalendar.setTime(lastdate);
			lastCalendar.add(Calendar.MONTH, 3);

			// 已3月未修改密码
			if (lastCalendar.compareTo(nowCalendar) <= 0) {
				/*
				 * LoginDialog dialog1 = new LoginDialog(LoginActivity.this);
				 * dialog1.show(); passWord.setText(""); return;
				 */
				Builder builder = new Builder(LoginActivity.this);
				builder.setTitle("提示");
				builder.setMessage("您已一个月未修改密码,请修改！");
				builder.setPositiveButton(android.R.string.ok,
						new AlertDialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								LoginDialog loginDialog = new LoginDialog(
										LoginActivity.this);
								loginDialog.show();
								passWord.setText("");
							}
						});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return;
			}

			lastCalendar.setTime(lastdate);
			nowCalendar.add(Calendar.DAY_OF_MONTH, -80);

			if (lastCalendar.compareTo(nowCalendar) <= 0) {
				Builder builder = new Builder(LoginActivity.this);
				builder.setTitle("提示");
				builder.setMessage("您即将一个月未修改密码,请尽快修改！");
				builder.setPositiveButton(android.R.string.ok,
						new AlertDialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								redirect();
							}
						});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return;
			}
		} catch (Exception e) {
		}

		redirect();
	}

	@Override
	public void onResponsSuccess(int actionTag, final Object resObject) {
		LoginInfoBO info = (LoginInfoBO) resObject;
		BeaAppSettings.setLoginInfo(info);
		BeaAppSettings.setSystemTimeDiffStep(info.getSystem_time());

		DbBaseModel dbmodel = BeaApplication.getInstance()
				.getDatabaseSettings();
		boolean dbExist = new File(dbmodel.getDbDir() + File.separator
				+ dbmodel.getDbName()).exists();
		if (!dbExist && dbmodel.isUseMDMSqlite()) {
			SynchManager.copySqliteDb(new OriginDbCopyHandler() {
				@Override
				public void onStart() {
					XCMDMProgressDialog.show(LoginActivity.this,
							"正在进行帐号初始化，请稍后...")
							.setCanceledOnTouchOutside(false);
				}

				@Override
				public void onFinish() {
					XCMDMProgressDialog.dismiss();
					onResponseLoginData(resObject);
				}
			});
		} else {
			onResponseLoginData(resObject);
		}
	}

	// 正常登陆
	private void redirect() {
		BeaApplication.clearLoginUserFolder(BeaAppSettings.getUserInfo());
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

	private void copyFileFromProjectToInstall(String string) {
		try {
			InputStream is = getAssets().open("pdf/" + string + ".pdf");
			String filename = XCApplication.workPath() + File.separator
					+ (string + ".pdf");
			File file = new File(filename);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream os = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int hasread = 0;
			while (-1 != (hasread = is.read(buffer))) {
				os.write(buffer, 0, hasread);
			}
			os.flush();
			os.close();
		} catch (Exception e) {
			XCLog.e("拷贝文件出错：", e.getMessage());
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onBackPressed();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(false);
		builder.setMessage("您要退出应用程序吗？");
		builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				exitApplication();
			}
		});
		builder.setNegativeButton("否", null);
		builder.show();
	}

	@OnClick(R.id.mdmSvnSettingBtn)
	public void onMDMSvnSettingButtonEvent(View v) {
		ServerUrlSettingDialog dialog = new ServerUrlSettingDialog(
				LoginActivity.this);
		dialog.show();
	}
}
