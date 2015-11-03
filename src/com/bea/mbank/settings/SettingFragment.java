package com.bea.mbank.settings;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xc.android.fragment.XCBaseFragment;
import xc.android.fragment.XCBaseHttpFragment;
import xc.android.utils.XCMD5;
import xc.android.utils.XCToolkit;

import com.android.widgets.XCButton;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.remote.JsonRemoteBO;

import android.graphics.Color;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class SettingFragment extends XCBaseHttpFragment {
	/**eText1原始密码 eText2 新密码  eText3重复新密码 */
	private EditText eText1, eText2, eText3;
	XCButton affirmBt;
	XCBaseFragment xbf;

	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_appset_content_menu_setting, null);
	}

	@Override
	protected void onInitContentView(View arg0) {
		xbf = new SetSuccess();
		eText1 = (EditText) arg0.findViewById(R.id.appset_EditText_Original);
		eText2 = (EditText) arg0.findViewById(R.id.appset_EditText_Unused);
		eText3 = (EditText) arg0.findViewById(R.id.appset_EditText_Again);
		// 设置editText最大输入字符数
		eText1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(16) });
		eText2.setFilters(new InputFilter[] { new InputFilter.LengthFilter(16) });
		eText3.setFilters(new InputFilter[] { new InputFilter.LengthFilter(16) });
		// 设置eText1默认获取焦点，切hint值为黑色
		eText1.setHintTextColor(Color.BLACK);
		//
		eText1.setOnTouchListener(new TouchView(eText1));
		eText2.setOnTouchListener(new TouchView(eText2));
		eText3.setOnTouchListener(new TouchView(eText3));

		affirmBt = (XCButton) arg0.findViewById(R.id.appset_AffirmButton);
		affirmBt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String p1 = eText1.getText().toString();
				String p2 = eText2.getText().toString();
				String p3 = eText3.getText().toString();
				if ("".equals(p1)) {
					XCToolkit.showToast("请输入旧密码");
					return;
				}
				if ("".equals(p2)) {
					XCToolkit.showToast("请输入新密码");
					return;
				}
				if ("".equals(p3)) {
					XCToolkit.showToast("请再次输入新密码");
					return;
				}
				if (p2.length() < 8) {
					XCToolkit.showToast("密码至少8位字符");
					return;
				}

				// ****************
				// 必须同时包含数字和字母
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
					XCToolkit.showToast("密码必须由数字、大小写字母和特殊符号组合而成!");
					return;
				}
				// ****************

				// ****************
				// 至少包含一个大写字母
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
					XCToolkit.showToast("密码必须至少包含一个大写字母!");
					return;
				}
				// *****************

				if (!p2.equals(p3)) {
					XCToolkit.showToast("两次输入的新密码不同，请重新输入");
					eText2.setText("");
					eText3.setText("");
					return;
				}
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("password_new", XCMD5.MD5(p3));
				map.put("password_old", XCMD5.MD5(p1));
				jsonRequest(0, "通用接口",
						JsonRemoteBO.reqParam(ConstDefined.ModifyPword, map),
						Integer.MAX_VALUE);

			}
		});

	}

	class TouchView implements OnTouchListener {
		public EditText view;

		public TouchView(EditText view) {
			this.view = view;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			eText1.setHintTextColor(Color.GRAY);
			eText2.setHintTextColor(Color.GRAY);
			eText3.setHintTextColor(Color.GRAY);
			setTouchView(view);
			return false;
		}
	}

	public void setTouchView(EditText editext) {
		if (editext.isFocusable()) {
			editext.setHintTextColor(Color.BLACK);
		} else {
			editext.setHintTextColor(Color.GRAY);
		}
	}

	@Override
	public void onResponsSuccess(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		XCToolkit.showToast("密码修改成功，请重新登录！");
//		pushFragmentController(xbf); 
		Message msg = new Message();
		msg.what = ConstDefined.SetSuccessTag;
		sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
	} 
}
