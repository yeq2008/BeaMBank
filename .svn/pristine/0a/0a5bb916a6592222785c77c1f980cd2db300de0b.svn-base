/**
 * 
 */
package com.bea.mbank.home;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import xc.android.activity.XCBaseActivity;
import xc.android.fmmanager.FragmentConfiger;

/**
 * @author cuiyuguo
 *
 */
@FragmentConfiger(pushAnim = {R.anim.push_push_enter, R.anim.push_push_exit, 
		                      R.anim.push_pop_enter, R.anim.push_pop_exit},
		          containerID = R.id.content)
public class BeaBaseActivity extends XCBaseActivity {
	@Override
	protected View onCreateView(LayoutInflater inflater) {
		return inflater.inflate(R.layout.ac_common, null);
	}

	@Override
	protected void onInitContentView() {
		//监听主菜单消息事件
		observeMessage(ConstDefined.EditResetActionKey);
		String homeFragment = getIntent().getStringExtra(ConstDefined.homeFragmentKey);
		Class class1 = null;Bundle bundle;
		try {
			if (null != homeFragment && null != (class1=Class.forName(homeFragment))) {
				Fragment fm = (Fragment)class1.newInstance();
				if (null != (bundle = getIntent().getExtras())) {
					fm.setArguments(bundle);
				}
				pushFragmentController(fm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onReceiveMessage(String msgkey, Object msgObject) {
		if (ConstDefined.EditResetActionKey.equals(msgkey)) {
			//初始数据不存在，重新创建
			if (null == msgObject) {
				msgObject = new GrwdyHomeBO();
			}
			Message msg = new Message();
			msg.what = ConstDefined.GRWDYCreditActionTag;
			msg.obj = msgObject;
			//这个是要切换模块，所以此层无法处理，需要向上层反馈
			sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
		}
	}
	
	@Override
	public boolean dispatchTouchEvent(android.view.MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_UP) {
			InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			if (null != imm && null != getCurrentFocus()) {
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
			}
		}
		return super.dispatchTouchEvent(event);
	}
}
