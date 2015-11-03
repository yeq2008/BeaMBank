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
 *	�����������Ĺ�����
 */
public final class BeaRemoteErrorManager {
	//�ӿڷ��ص������޴���֪ͨҳ����н���ʹ��
	public final static int RemoteNoError = 0;
	//�ӿڷ��ص������д���֪ͨҳ�������ʾ
	public final static int RemoteHasError = 1;
	//�ӿڷ��ص����ݿ����д���Ҳ����û���󣬱����Ѿ������ˣ���㲻�ù���
	public final static int RemoteOtherError = 2;
	
	//�ӿڴ����û����µ�¼��ǰһ������������
	public final static int RemoteError_UserReLogin = 0;
	
	private static void postLocalBroadcast(int errorCode, String errorMsg) {
		//���͹㲥��֪ͨ���°汾��
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
		} else if (18 == errorCode) {//�û�����������
			postLocalBroadcast(RemoteError_UserReLogin, "�˻�������һ̨�豸�ϵ�¼�ˣ����������µ�¼��");
			return RemoteOtherError;
		} else if (17 == errorCode) {//�û���ʱ�������������������ʱ
			postLocalBroadcast(RemoteError_UserReLogin, "�˻���ʱ���޲�������Ҫ���µ�¼��");
			return RemoteOtherError;
		} else {
			return RemoteHasError;
		}
	}
}
