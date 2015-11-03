 package com.bea.application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bea.remote.BeaRemoteSettings;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import com.bea.remote.models.user.LoginInfoBO;
import com.bea.remote.models.user.ModuelInfoBO;
import com.bea.remote.models.user.UserInfoBO;

import xc.android.application.XCApplication;
import xc.android.remote.RemoteInterfaceSettings;
import xc.android.utils.XCBase64;
import xc.android.utils.XCCrypto;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


/**
 * 
 * ������Ŀ������Ϣ��������Щ��Ϣ�Ǵ��ʱ��������Ϣ��д����xml�ļ��У�
 * ��Щ��Ϣ�ǳ������й����е�״̬��Ϣ��
 */

public class BeaAppSettings {
	private static BeaAppSettings instance = null;
	private SharedPreferences prefer = null;
    public static  Map<String,Object> addressmap=new HashMap<String,Object>();
	public static synchronized BeaAppSettings instance() {
		if (instance == null) {
			instance = new BeaAppSettings();
		}
		return instance;
	}
	private BeaAppSettings() {
		try {
			Initialized(XCApplication.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private Editor editor() {
		return prefer().edit();
	}
	private SharedPreferences prefer() {
		return prefer;
	}
	
	public void Initialized(Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		if (null == prefer) {
			prefer = context.getSharedPreferences("BeaAppSettings", Context.MODE_PRIVATE);
		}
	}
	
	//���б༭���ݵ�ҳ���ʼ��ǰ����
	String originJSONdata = null;
	Object originData = null;
	public static void setOriginData(Object data) {
		try {
			instance().originData = data;
			instance().originJSONdata = XCJsonHelper.toJSONString(data);
		} catch (Exception e) {
			instance().originJSONdata = null;
		}
	}
	public static void updateOriginJSONdata(Object data) {
		instance().originJSONdata = XCJsonHelper.toJSONString(data);
	}
	public static boolean isDataChanged() {
		try {
			//֪ͨ��ҳ�汣���Լ�������
			XCApplication.postMsg(ConstDefined.SavedataWhileChangeAction, null);
			String newJson = XCJsonHelper.toJSONString(instance.originData);
//			Log.d("--oldData--", instance.originJSONdata);
//			Log.d("--newData--", newJson);
			
			if (null != instance().originData && null != instance().originJSONdata) {
				return !newJson.equals(instance().originJSONdata);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	//�û���¼��Ϣ
	private LoginInfoBO mLoginInfoBO = null;
	public static LoginInfoBO getLoginInfo() {
		return instance().mLoginInfoBO;
	}
	public static void setLoginInfo(LoginInfoBO loginInfo) {
		instance().mLoginInfoBO = loginInfo;
	}
	public static UserInfoBO getUserInfo() {
		if (null != instance().mLoginInfoBO && 
			null != instance().mLoginInfoBO.getUserinfo()) {
			return instance().mLoginInfoBO.getUserinfo();
		} else {
			return new UserInfoBO();
		}
	}
	//�����޵�Ѻ����
	public GrwdyHomeBO mGrwdyHomeBO = null;
	public static String photoPath() {
//		if (null != instance().mLoginInfoBO) {
//			UserInfoBO uinfo = instance().mLoginInfoBO.getUserinfo();
//			
//			return uinfo.getUser_name()+"_"+uinfo.getUser_id();
//		} else {
//			return "123456";
//		}
		return "files";
	}
	
	private static String getDESString(String fieldName) {
		try {
			String fieldValue = instance().prefer().getString(fieldName, ""); 
			byte[] bytes = XCBase64.decode(fieldValue);
		   
		    if (null != bytes && null != (bytes = XCCrypto.instance().dataDecrypt(bytes))) {
		    	return new String(bytes);
			} else {
				return "";
			}
		} catch (Exception e) {
			XCLog.e("settings", e.getLocalizedMessage());
			return "";
		}
	}
	private static void setDESString(String fieldName, String fieldValue) {
		if (null == fieldName || fieldName.trim().isEmpty()) {
			return;
		}
		try {
			if (null != fieldValue) {
				byte[] desBytes = XCCrypto.instance().dataEncrypt(fieldValue.getBytes());
				String value = new String(XCBase64.encode(desBytes));
				instance().editor().putString(fieldName, value).commit();
			} else {
				instance().editor().remove(fieldName).commit();
			}
		} catch (Exception e) {
			XCLog.e("settings", e.getLocalizedMessage());
		}
	}
	//�����¼�û���
	public static String getUserName() {
		return getDESString("userName");
	}
	public static void setUserName(String userName) {
		setDESString("userName", userName);
	}

	//����SVN��¼�û���
	public static String getSvnUserName() {
		return getDESString("svnName");
	}
	public static void setSvnUserName(String svnName) {
		setDESString("svnName", svnName);
	}
	
	//�����������ַ
	public static String getServerUrl() {
		return getPreference("serverUrl", "");
	}
	public static void setServerUrl(String serverurl) {
		savePreference("serverUrl", serverurl);
		RemoteInterfaceSettings.setGlobleRemoteSettings(new BeaRemoteSettings());
	}
	public static boolean getNoneLoginSwitch() {
		return instance().prefer().getBoolean("noLoginSwt", false);
	}
	public static void setNoneLoginSwitch(boolean swtFlag) {
		instance().editor().putBoolean("noLoginSwt", swtFlag);
	}
	
	//����SVN��¼�û�����
	public static String getSvnPword() {
		return getDESString("svnPword");
	}
	public static void setSvnPword(String svnPword) {
		setDESString("svnPword", svnPword);
	}
		
	public static String getAPKVersion() {
		return getDESString("apkVersion");
	}
	public static void setAPKVersion(String version) {
		setDESString("apkVersion", version);
	}
	
	private static long timeDiffStep = 0;
	private static String dateSetString = null, currentDateString = null;
	public static long getSystemTimeDiffStep() {
		return timeDiffStep;
	}
	public static void setSystemTimeDiffStep(String systemTime) {
		try {
			Date date = new Date();
			
			currentDateString = XCToolkit.stringFromDate(date, "yyyy-MM-dd");
			if (null == dateSetString) {
				dateSetString = currentDateString;
			}
			
			timeDiffStep = date.getHours()*3600+date.getMinutes()*60+date.getSeconds() - 
				       Long.parseLong(systemTime.substring(0, 2))*3600-
				       Long.parseLong(systemTime.substring(2, 4))*60-
				       Long.parseLong(systemTime.substring(4, 6));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * flh
	 * ��ȡ������Ϣ
	 */
	public static String getPreference(String key,String defValue){
		return instance().prefer().getString(key, defValue);
	}
	
	/**
	 * flh
	 * ������Ϣ����
	 */
	public static void savePreference(String key,String value){
		instance().editor().putString(key, value).commit();
	}
	
	public static Map<String,Object> blankListMap;
	//��ȡ�˵�Ȩ��
	public static boolean getMenuRight(String moduelUrl){
		if(null == instance().mLoginInfoBO)
			return true;
		List<ModuelInfoBO> moduelInfoBOs = instance().mLoginInfoBO.getModule_info();
		ModuelInfoBO paxbModuel = null;
		if(null != moduelInfoBOs){
			for(ModuelInfoBO bo : moduelInfoBOs){
				if(moduelUrl.equals(bo.getUrl())){
					paxbModuel = bo;
					break;
				}
			}
		}
		if(null != paxbModuel){
			String startTime = paxbModuel.getStart_time();
			String endTime = paxbModuel.getEnd_time();
			if(null != startTime && null != endTime && !"".equals(startTime) && !"".equals(endTime)){
				try{
					long time1 = Long.parseLong(startTime.substring(0, 2))*3600+
						         Long.parseLong(startTime.substring(2, 4))*60+
						         Long.parseLong(startTime.substring(4, 6));
					long time2 = Long.parseLong(endTime.substring(0, 2))*3600+
					             Long.parseLong(endTime.substring(2, 4))*60+
					             Long.parseLong(endTime.substring(4, 6));
					Date date = new Date();
					long time3 = date.getHours()*3600+date.getMinutes()*60+date.getSeconds() -
							     getSystemTimeDiffStep();
					return (null!=currentDateString && null!=dateSetString &&
							currentDateString.endsWith(dateSetString)) && 
							(time1<time3 && time3<time2);
				}catch(Exception e){}
			}
		}
		return true;
	}
}