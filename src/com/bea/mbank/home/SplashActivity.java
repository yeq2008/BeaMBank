package com.bea.mbank.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;


/**
 * @author apple
 * 程序启动等待页面
 */
public class SplashActivity extends Activity {
	public static WindowManager windowManager;
	private final static int STOPSPLASH = 1;
	View splash;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView view = new ImageView(this);
		view.setBackgroundColor(0xffff0000);
		
		setContentView(view);
		windowManager = getWindowManager();
		Message msg = new Message();
		msg.what = STOPSPLASH;
		splashHandler.sendMessageDelayed(msg, 2000); // ms
	}

	@Override
	public void onDestroy() {
		splashHandler = null;
		System.gc();
		super.onDestroy();
	}
	
	@SuppressLint("HandlerLeak")
	private Handler splashHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case STOPSPLASH:
					checkLogin();					
					break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
				checkLogin();
		}
		return true;
	}

	private void checkLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		startActivity(intent);
		finish();
    }
}