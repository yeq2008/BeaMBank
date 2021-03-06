package com.bea.mbank.home;

import xc.android.utils.XCToolkit;

import com.bea.application.BeaAppSettings;
import com.bea.mbank.R;
import com.cyg.viewinject.VInject;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class ServerUrlSettingDialog extends Dialog {

	private Context context;
	@ViewInject(R.id.server_url)
	private EditText serverUrl;

	public ServerUrlSettingDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.pop_setserverurl, null);

		setContentView(contentView);
		VInject.inject(this, contentView);
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		// lp.height = XCToolkit.dip2px(404);
		// lp.width = XCToolkit.dip2px(444);
		lp.height = (int) (XCToolkit.getScreenHeight() * 0.56);
		lp.width = (int) (XCToolkit.getScreenWidth() * 0.36);
		dialogWindow.setAttributes(lp);

		// 本地已设置的进行预填充
		serverUrl.setText(BeaAppSettings.getServerUrl());
	}

	@OnClick(R.id.server_confirm)
	public void onConfirmButtonEvent(View view) {
		BeaAppSettings.setServerUrl(serverUrl.getText().toString());
		dismiss();
	}

}
