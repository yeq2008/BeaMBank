package com.bea.mbank.home;

import xc.android.application.XCApplication;
import xc.android.remote.HWMdmManager;
import xc.android.utils.XCToolkit;

import com.bea.application.BeaAppSettings;
import com.bea.application.BeaApplication;
import com.bea.mbank.R;
import com.cyg.viewinject.VInject;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;
import com.huawei.anyoffice.sdk.SDKContext;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MDMSvnSettingDialog extends Dialog{

	private Context context;
	@ViewInject(R.id.yu_et_username)
	private EditText userName;
	@ViewInject(R.id.yu_et_password)
	private EditText passWord;
	@ViewInject(R.id.yu_login)
	private Button yu_login;
	public MDMSvnSettingDialog(Context context) {
		super(context);
		this.context=context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View contentView = LayoutInflater.from(context).inflate(R.layout.pop_setmdmsvn, null);
		
		setContentView(contentView);
		VInject.inject(this, contentView);
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.height = XCToolkit.dip2px(404);
		lp.width = XCToolkit.dip2px(444);
		dialogWindow.setAttributes(lp);
		
		//本地已设置的进行预填充
		userName.setText(BeaAppSettings.getSvnUserName());
		passWord.setText(BeaAppSettings.getSvnPword());
	}
	
	@OnClick(R.id.yu_login)
	public void yuLogin(View view){
		//设置华为MDM网关认证账户信息
		Bundle bundle = XCApplication.getInstance().getHWMdmConfig();
		bundle.putString(HWMdmManager.HWMdm_UserName, userName.getText().toString().trim());
		BeaAppSettings.setSvnUserName(userName.getText().toString().trim());
		bundle.putString(HWMdmManager.HWMdm_PassWord, passWord.getText().toString().trim());
		BeaAppSettings.setSvnPword(passWord.getText().toString().trim());
		SDKContext.getInstance().reset();
		dismiss();
	}
	
}
