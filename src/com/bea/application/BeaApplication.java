/**
 * 
 */
package com.bea.application;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;

import com.bea.database.DbManager;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.mbank.R;
import com.bea.mbank.util.FileFolderUtil;
import com.bea.remote.BeaRemoteSettings;
import com.bea.remote.models.user.UserInfoBO;
import com.huawei.anyoffice.sdk.SDKContext;

import xc.android.application.XCApplication;
import xc.android.database.DbBaseModel;
import xc.android.database.DbQueryManager;
import xc.android.remote.HWMdmManager;
import xc.android.remote.RemoteInterfaceSettings;
import xc.android.utils.XCBase64;
import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;

/**
 * @author cuiyuguo app程序入口类
 */
public class BeaApplication extends XCApplication {
	private final static String productMD5Sign = "846EDB2B34CE127A465E009A3993ADFE";
	@Override
	protected void initApplicationGlobleSettings() {
		// log配置,mdm版是release版本，去处log日志
		XCLog.setLogMode(!isMDMVersion(), null);
	}

	public static void clearLoginUserFolder(final UserInfoBO userInfo) {
		String folder = workPath() + File.separator + BeaAppSettings.photoPath();
		// 清除一个月之前的文件
		FileFolderUtil.clearFolder(folder,
				XCToolkit.getDateByAddDays(new Date(), -30));
	}

	@Override
	public String registerAuthorizedCode() {
		return "ODU2MTM1NGM3ZTNlZWQ2MDM2MzViMjk5YTEzYjM1ZjA=";
	}

	private DbBaseModel g_dBaseModel = null;
	@Override
	public DbBaseModel getDatabaseSettings() {
		if (null == g_dBaseModel) {
			DbBaseModel baseModel = new DbBaseModel();
//			// 当两个apk包的版本不同时，此时第一次运行时要干掉以前的文件重新同步，所以开发人员请在每次apk打包时同步
//			// 一版最新数据放到raw下，此处不要再改
//			String apkVersion = BeaAppSettings.getAPKVersion();
//			baseModel.setClearOld(!apkVersion.equals(packInfo.versionName));
//			BeaAppSettings.setAPKVersion(packInfo.versionName);
			baseModel.setDbDir(workPath()+"/databases");
			baseModel.setCipher(true);
			baseModel.setRawIsCipher(true);
			baseModel.setSecretKey("beaBankApp");//密码不要改，改了数据库要重新导beaBankApp
			baseModel.setUseMDMSqlite(isMDMVersion());
			baseModel.setRawResourceId(R.raw.db_origin);
			g_dBaseModel = baseModel;
		}
		if (null != BeaAppSettings.getLoginInfo()) {
			//登录后每个帐号对应一个数据库文件，因为在产品精选那块儿有一个小缺陷，不是对应某个人的
			UserInfoBO uInfo = BeaAppSettings.getUserInfo();
			String dbName = uInfo.getUser_name() + uInfo.getUser_id();
			g_dBaseModel.setDbName(XCBase64.encode(dbName.getBytes())+".sqlite");
		} else {
			g_dBaseModel.setDbName("beapp.sqlite");
		}
		
		return g_dBaseModel;
	}
	@Override
	public RemoteInterfaceSettings getRemoteSettings() {
		return new BeaRemoteSettings();
	}

	// 华为mdm网关认证所需信息，如果此函数返回null则表示不认证mdm网关，也即不使用华为mdm
	// 网络安全通道
	static Bundle bundle = null;
	private String apkMD5Sign = null;
	@Override
	public Bundle getHWMdmConfig() {
		if (null == bundle && isMDMVersion()) {
			bundle = new Bundle();
			bundle.putString(HWMdmManager.HWMdm_GateWay,  ConstDefined.HuaWeiMDMGateWay);
//			//测试帐号：lch04602321／Baaa,004
//			bundle.putString(HWMdmManager.HWMdm_UserName, BeaAppSettings.getSvnUserName());
//			bundle.putString(HWMdmManager.HWMdm_PassWord, BeaAppSettings.getSvnPword());
//			bundle.putString(HWMdmManager.HWMdm_UserName, "lch04602321");
//			bundle.putString(HWMdmManager.HWMdm_PassWord, "Baaa,004");
		}
		return bundle;
	}

	@Override
	public void initHWMdmSDKContext() {
		if (!SDKContext.getInstance().sdkInitComplete()) {
			String workPath = XCApplication.workPath();

			File path = new File(workPath);
			if (!path.exists()) {
				path.mkdirs();
			}

			boolean inited = SDKContext.getInstance().init(this, workPath);
			XCLog.d("===mdm初始化==", "SDKContext.getInstance().init:" + inited);
		}
	}
	
	private boolean isMDMVersion() {
		if (null == apkMD5Sign) {
			apkMD5Sign = getAPKMD5Signature();
		}
		return productMD5Sign.equals(apkMD5Sign);
	}
	public static boolean isMDMApk() {
		return ((BeaApplication)getInstance()).isMDMVersion();
	}
}
