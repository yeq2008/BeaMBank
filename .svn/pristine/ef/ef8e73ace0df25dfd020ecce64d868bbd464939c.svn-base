/**
 * 
 */
package com.bea.remote;

import xc.android.application.XCApplication;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.bea.application.ConstDefined;

/**
 * @author cuiyuguo
 *	处理网络错误的管理类
 */
public final class BeaRemoteErrorManager {
	//接口返回的数据无错误，通知页面进行解析使用
	public final static int RemoteNoError = 0;
	//接口返回的数据有错误，通知页面进行提示
	public final static int RemoteHasError = 1;
	//接口返回的数据可能有错误，也可能没错误，本类已经处理了，外层不用管了
	public final static int RemoteOtherError = 2;
	
	//接口错误，用户重新登录，前一个被踢下来了
	public final static int RemoteError_UserReLogin = 0;
	
	private static void postLocalBroadcast(int errorCode, String errorMsg) {
		//发送广播，通知有新版本了
    	Intent intent = new Intent(ConstDefined.APPHomeChangeActionKey);
    	intent.putExtra("errorCode", errorCode);
    	if (null != errorMsg) {
    		intent.putExtra("errorMsg", errorMsg);
		}
        LocalBroadcastManager.getInstance(XCApplication.getInstance()).sendBroadcast(intent);
	}
	public static int analyzeRemoteError(JsonRemoteErrorBO error) {
		int errorCode = error.getCode();
		if (0 == errorCode) {
			return RemoteNoError;
		} else if (18 == errorCode) {//用户被踢下来了
			postLocalBroadcast(RemoteError_UserReLogin, "账户已在另一台设备上登录了，您必须重新登录！");
			return RemoteOtherError;
		} else if (17 == errorCode) {//用户长时间无网络操作，操作超时
			postLocalBroadcast(RemoteError_UserReLogin, "账户长时间无操作，需要重新登录！");
			return RemoteOtherError;
		} else {
			return RemoteHasError;
		}
	}
}
