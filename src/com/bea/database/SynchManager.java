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
		//Ҫͬ�����ӱ����ڳ����ڴ��ʱ�й̻������ݿ��ļ�db.sqlit���ڣ�����������ʱ������л�����û������ļ���û�еĻ�
		//�´��һ�ݹ�ȥ��������ÿ��ͬ�����ʱ���ȡ�ܹ�����µ��ӱ�������ȷ��Ҫͬ�����ӱ�����һ�����⣬����������ˣ���
		//���±�����ˣ���ʱ��Ȼ���µĳ�����ڹ̻������ݿ��ļ�������������ӱ�������������л���������һ�ݿ������ڣ�����
		//�µ����ݿ��ļ�û���ٿ�����ȥ�������ܹ���������ݲ�����������ӱ��������ֲ�ϣ�������л��������ݿ��ļ�ɾ����������
		//���������ϣ���������ڴ˽��ܹ�����µ��ӱ���һ�¡�
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
				if (!exist) {//������
					SqliteSynBO b = new SqliteSynBO();
					b.setTABLENAME(t);
					b.setVERSION("0");
					if (DbQueryManager.saveOrUpdate(b)) {
						synchRecord.add(b);
					}
				}
			}
		} else {
			XCLog.i("=====synchRecord", "���ݿ��ȡ�������ݣ�");
		}
	}
	private boolean hasSynched;
	//�Ƿ��Ѿ�ͬ������
	public static boolean hasSynched() {
		return instance().hasSynched;
	}

	public static void startSynch() {
		instance().jsonRequest();
	}

	JsonReqClient reqClient = new JsonReqClient(this);
	private void jsonRequest() {
		if (null != synchRecord) {
			reqClient.jsonRequest(0, "ͨ�ýӿ�", JsonRemoteBO.
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
		XCLog.d("---���ͬ��---", fields.toString());
		//����ȽϺ�ʱ����Ҫ�����߳��д�������Ὣui���߳̿���
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
	//��Դ��δ���ܣ������ݿ⵼�ɼ������ݿ⣬���ú�Ҫ�������ݿ⿽�����ŵ���Ŀ��ʹ�ã�
	//������ĿҪע�ⰲȫ�ԣ��������ݿ����Ҫ�ü��ܵģ�����Ŀ����Ҫ�󼯳�mdm������
	//���ܷ�ʽֻ�ܲ���mdm��
	public static void copySqliteDb(final OriginDbCopyHandler copyHandler) {
		if (null != copyHandler) {
			copyHandler.onStart();
		}
		final Handler msgHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {//"copyNew.db"
				DbBaseModel dbmodel = BeaApplication.getInstance().getDatabaseSettings();
				//������������ݿ��ļ�������
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
