package com.bea.mbank.home;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xc.android.remote.IRemoteResponse;
import xc.android.remote.json.JsonReqClient;
import xc.android.utils.XCMD5;
import xc.android.utils.XCToolkit;

import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.remote.JsonRemoteBO;
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

public class LoginDialog extends Dialog implements IRemoteResponse {
	@ViewInject(R.id.pop_setpassword_Original)
	EditText tvoriginal;
	@ViewInject(R.id.appset_EditText_Unused)
	EditText tvnew;
	@ViewInject(R.id.appset_EditText_Again)
	EditText tvnewagain;

	private Context context;
	JsonReqClient reqClient = new JsonReqClient(this);

	public LoginDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.pop_setpassword, null);

		setContentView(contentView);
		VInject.inject(this, contentView);
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.height = (int) (XCToolkit.getScreenHeight() * 0.4925f);
		lp.width = (int) (XCToolkit.getScreenWidth() * 0.5656f);
		dialogWindow.setAttributes(lp);
	}

	@OnClick(R.id.mytask_search_confirmBtn)
	public void confirm(View view) {
		String p1 = tvoriginal.getText().toString();
		String p2 = tvnew.getText().toString();
		String p3 = tvnewagain.getText().toString();
		if ("".equals(p1)) {
			XCToolkit.showToast("�����������");
			return;
		}
		if ("".equals(p2)) {
			XCToolkit.showToast("������������");
			return;
		}
		if ("".equals(p3)) {
			XCToolkit.showToast("���ٴ�����������");
			return;
		}
		if (p2.length() < 8) {
			XCToolkit.showToast("��������8λ�ַ�");
			return;
		}

		// ****************
		// ����ͬʱ�������ֺ���ĸ
		int count = 0;
		Pattern pattern = Pattern.compile("^\\d$");
		for (int i = 0; i < p2.length(); i++) {
			String tmp = p2.substring(i, i + 1);
			Matcher m = pattern.matcher(tmp);
			if (m.matches()) {
				count++;
			}
		}
		if (0 == count || p2.length() == count) {
			XCToolkit.showToast("������������֡���Сд��ĸ�����������϶���!");
			return;
		}
		// ****************

		// ****************
		// ���ٰ���һ����д��ĸ
		int count1 = 0;
		Pattern pattern1 = Pattern.compile("^[A-Z]$");
		for (int i = 0; i < p2.length(); i++) {
			String tmp = p2.substring(i, i + 1);
			Matcher m = pattern1.matcher(tmp);
			if (m.matches()) {
				count1++;
			}
		}
		if (count1 < 1) {
			XCToolkit.showToast("����������ٰ���һ����д��ĸ!");
			return;
		}
		// *****************

		if (!p2.equals(p3)) {
			XCToolkit.showToast("��������������벻ͬ������������");
			tvnew.setText("");
			tvnewagain.setText("");
			return;
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("password_new", XCMD5.MD5(p2));
		map.put("password_old", XCMD5.MD5(p1));
		reqClient.jsonRequest(0, "ͨ�ýӿ�",
				JsonRemoteBO.reqParam(ConstDefined.ModifyPword, map),
				Integer.MAX_VALUE);
	}

	@OnClick(R.id.mytask_search_closeBtn)
	public void close(View view) {
		dismiss();
	}

	@Override
	public void onNetConnectFailed(int actionTag, String obj) {
		onResponsFailed(actionTag, obj);
	}

	@Override
	public void onResponsFailed(int actionTag, String obj) {
		XCToolkit.showToast(obj);
	}

	@Override
	public void onResponsSuccess(int actionTag, Object obj) {
		XCToolkit.showToast("�����޸ĳɹ�������Ҫ�����������µ�¼��");
		dismiss();
	}
}