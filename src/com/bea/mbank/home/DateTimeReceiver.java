/**
 * 
 */
package com.bea.mbank.home;

import java.util.Date;

import xc.android.application.XCApplication;
import xc.android.utils.XCToolkit;

import com.bea.application.BeaAppSettings;
import com.bea.application.BeaApplication;
import com.bea.application.ConstDefined;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * @author cuiyuguo
 *	监听用户设置系统时间事件
 */
public class DateTimeReceiver extends BroadcastReceiver {
	private static Date newDate = null;
	@Override
	public void onReceive(Context context, Intent intent) {
		//发送广播，通知需要从服务器端重新取当前服务器时间
        LocalBroadcastManager.getInstance(BeaApplication.getInstance())
                             .sendBroadcast(new Intent(ConstDefined.DeviceTimeSetActionKey));
	}
}
