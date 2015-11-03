package com.bea.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import xc.android.application.XCApplication;
import xc.android.database.DbBaseModel;
import xc.android.database.DbQueryManager;
import xc.android.database.MDMDbCopyNewManager;
import xc.android.database.MDMDbCopyOldManager;
import xc.android.file.XCFile;
import xc.android.file.XCFileInputStream;
import xc.android.remote.IRemoteResponse;
import xc.android.remote.json.FileLoader;
import xc.android.remote.json.JsonReqClient;
import xc.android.sqlite.DBFindRowCallback;
import xc.android.sqlite.sqlite.CursorUtils;
import xc.android.sqlite.sqlite.Selector;
import xc.android.utils.XCBase64;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCLog;

import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bea.application.BeaApplication;
import com.bea.application.ConstDefined;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.mbank.util.ClassUtil;
import com.bea.mbank.util.FileUtils;
import com.bea.remote.JsonRemoteBO;


public class SynchManager implements IRemoteResponse{
	private static SynchManager instance = null;
	private static synchronized SynchManager instance() {
		if (instance == null) {
			instance = new SynchManager();
		}
		return instance;
	}

	private static String[] getSynchTables() {
		//要同步的子表，由于程序在打包时有固化的数据库文件db.sqlit存在，程序在运行时检查运行环境有没有这个文件，没有的话
		//会拷贝一份过去。本程序每次同步码表时会读取总管理表下的子表数组以确定要同步的子表，但有一个问题，当程序更新了，又
		//有新表加入了，此时虽然在新的程序包内固化的数据库文件中有新添入的子表项，但是由于运行环境下已有一份拷贝存在，所以
		//新的数据库文件没有再拷贝过去，导致总管理表中数据不含新添入的子表。但是我又不希望把运行环境的数据库文件删除掉，所以
		//程序设计上希望开发者在此将总管理表下的子表都列一下。
		return new String[] {"YRL_BASIC_DATA", "SYS_AC_AREA", "MMP_PRODUCT_CATEGORY", "MMP_PRODUCT",
				             "MMP_PRODUCT_ATT", "MMP_LOAN_RATE", "SYS_AC_ORG"};
	}
	private List<SqliteSynBO> synchRecord = null;
	private SynchManager() {
		synchRecord = DbManager.getSynRecords();
		if (null != synchRecord) {
			String[] tables = getSynchTables();
			for (String t : tables) {
				boolean exist = false;
				for (SqliteSynBO tb : synchRecord) {
					if (tb.getTABLENAME().equals(t)) {
						exist = true;
						break;
					}
				}
				if (!exist) {//不存在
					SqliteSynBO b = new SqliteSynBO();
					b.setTABLENAME(t);
					b.setVERSION("0");
					if (DbQueryManager.saveOrUpdate(b)) {
						synchRecord.add(b);
					}
				}
			}
		} else {
			XCLog.i("=====synchRecord", "数据库读取不到数据！");
		}
	}
	private boolean hasSynched;
	//是否已经同步过了
	public static boolean hasSynched() {
		return instance().hasSynched;
	}

	public static void startSynch() {
		instance().jsonRequest();
	}

	JsonReqClient reqClient = new JsonReqClient(this);
	private void jsonRequest() {
		if (null != synchRecord) {
			reqClient.jsonRequest(0, "通用接口", JsonRemoteBO.
					reqParam(ConstDefined.CodeDBSynch, synchRecord), Integer.MAX_VALUE);
		}
	}
	
	@Override
	public void onResponsFailed(int arg0, String arg1) {}
	@Override
	public void onNetConnectFailed(int arg0, String arg1) {}

	static Map<String, Class<?>> codeMap = ClassUtil.getCFromPackage(
			BeaApplication.getInstance(), "com.bea.database.codemodel");
	@Override
	public void onResponsSuccess(int arg0, Object arg1) {
		final JSONArray fields = (JSONArray)arg1;
		if (null == fields) {
			return;
		}
		XCLog.d("---码表同步---", fields.toString());
		//这个比较耗时，需要放在线程中处理，否则会将ui主线程卡死
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < fields.length(); ++i) {
					try {
						JSONObject field = fields.getJSONObject(i);
						String tableName = field.getString("tablename");
						String version = field.getString("version");
						if (field.has("data")) {
							List<?> codes = XCJsonHelper.parseArray(field.getJSONArray("data"), 
							        codeMap.get(tableName));
							if (null != codes && DbQueryManager.transactionToDB(codes)) {
								SqliteSynBO bo = new SqliteSynBO();
								bo.setTABLENAME(tableName);
								bo.setVERSION(version);
								DbQueryManager.saveOrUpdate(bo);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public interface OriginDbCopyHandler {
		public void onStart();
		public void onFinish();
	}
	//从源（未加密）的数据库导成加密数据库，导好后要将新数据库拷出来放到项目里使用，
	//银行项目要注意安全性，所以数据库必须要用加密的，本项目由于要求集成mdm，所以
	//加密方式只能采用mdm的
	public static void copySqliteDb(final OriginDbCopyHandler copyHandler) {
		if (null != copyHandler) {
			copyHandler.onStart();
		}
		final Handler msgHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {//"copyNew.db"
				DbBaseModel dbmodel = BeaApplication.getInstance().getDatabaseSettings();
				//将拷贝后的数据库文件重命名
				new File(dbmodel.getDbDir()+"/copyNew.db").renameTo(
				new File(dbmodel.getDbDir()+File.separator+dbmodel.getDbName()));
				new File(dbmodel.getDbDir()+"/copyOld.db").delete();
				if (null != copyHandler) {
					copyHandler.onFinish();
				}
			}
		};
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					MDMDbCopyOldManager.setGlobleDbHelper(BeaApplication.getInstance().getDatabaseSettings());
					MDMDbCopyNewManager.setGlobleDbHelper(BeaApplication.getInstance().getDatabaseSettings());
					
					List<?> list = MDMDbCopyOldManager.searchDB(SqliteSynBO.class, null);
					MDMDbCopyNewManager.saveAll(list);
					XCLog.i("--allConstant", "Source Count="+list.size()+"Copy Count="+
					MDMDbCopyNewManager.getCount(SqliteSynBO.class, null));
					for (String name : getSynchTables()) {
						list = MDMDbCopyOldManager.searchDB(codeMap.get(name), null);
						MDMDbCopyNewManager.transactionToDB(list);
						XCLog.i("--"+name, "Source Count="+list.size()+"Copy Count="+
								MDMDbCopyNewManager.getCount(codeMap.get(name), null));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					msgHandler.sendEmptyMessage(0);
				}
			}
		}).start();
	}
}
