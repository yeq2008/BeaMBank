/**
 * 
 */
package com.bea.remote;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import xc.android.activity.XCBaseListActivity;
import xc.android.fragment.XCBaseListFragment;
import xc.android.remote.IRemoteDataCryp;
import xc.android.remote.IRemoteResponse;
import xc.android.remote.RemoteInterfaceSettings;
import xc.android.remote.RemotePageConfig;
import xc.android.utils.XCCrypto;
import xc.android.utils.XCJsonHelper;
import xc.android.widgets.XCProgressDialog;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.bea.application.BeaAppSettings;
import com.bea.remote.models.user.LoginInfoBO;


/**
 * @author cuiyuguo
 *
 */
public class BeaRemoteSettings extends RemoteInterfaceSettings {
	/**
	 * @brief ����http����ͷ��Ϣ������������һ�𴫸���̨
	 *        �������Ҫ��������ͷ���˿��Է���null
	 * @param interfaceType  �ӿ�����
	 * @return ����ͷЯ����������Ϣ
	 */
	@Override
	public Map<String, String> getRequestHttpHeaders(int interfaceType) {
		return null;
	}

	/**
	 * @brief http��Ӧͷ�����httpͷ��Я����Ϣ�����������ڴ˺����н�������
	 *        ����ж�ĳ���������㣬���жϱ�����Ӧ���򷵻�true������false,
	 *        �����������Ӧͷ������Ĭ�ϴ�����
	 * @param context  ���ýӿڵķ��ؽ����߾��
	 * @param resHttpHeaders ��Ӧͷ��Ϣ
	 * 
	 * @return ����ж�ĳ���������㣬���жϱ�����Ӧ���򷵻�true������false
	 */
	@Override
	public boolean onResponseHeader(IRemoteResponse context, Map<String, String> resHttpHeaders) {
		return false;
	}
	
	/**
	 * @brief �������������ݿ�ʼʱ������õȴ�������û��ȴ���������
	 *        ��ܿ�������һ������androidϵͳĬ�ϵĵȴ�������XCProgressDialog���������ϵͳ����Ҫ���Ի����
	 *        �ȴ���꣬����ֱ��ʹ��XCProgressDialog
	 * @param context  ���ýӿڵķ��ؽ����߾��
	 */
	@Override
	public void startWaitCursor(IRemoteResponse context) {
		if (context instanceof Fragment) {
			XCProgressDialog.show(((Fragment)context).getActivity()).setCanceledOnTouchOutside(false);
		} else if (context instanceof Activity) {
			XCProgressDialog.show((Activity)context).setCanceledOnTouchOutside(false);
		}
	}

	/**
	 * @brief �������������ʱ���ã�ֹͣ�ȴ����
	 * 
	 * @param context  ���ýӿڵķ��ؽ����߾��
	 */
	@Override
	public void stopWaitCursor(IRemoteResponse context) {
		XCProgressDialog.dismiss();
	}
	
	/**
	 * @brief ϵͳ��������ýӿڵ�ַͳһǰ׺������и����ͳһǰ׺�ģ�������
	 *        remoteSettings()�е�url������ȫ·�����мǣ�ȫ·��������http://����https://��ͷ��
	 * 
	 * @param context  ���ýӿڵķ��ؽ����߾��
	 */
	//������Ա������ַ
//	final static String s_defaultServerUrl = "http://192.168.1.103:8080/mmp_mobile_service/";
	//���Ի�����ַ
//	final static String s_defaultServerUrl = "http://10.192.81.190:7001/mmp_mobile_service/";
	//����������ַ
	final static String s_productEnvIP = "180.168.28.124";
	final static String s_defaultServerUrl = "http://"+s_productEnvIP+"/mmp_mobile_service/";
	@Override
	public String urlDefaultPrefix() {
		String preUrl = "http://"+BeaAppSettings.getServerUrl() + "/mmp_mobile_service/";
		return null == preUrl ? s_defaultServerUrl : preUrl;
	}


	/**
	 * �ӿ���������
	 * <!-- �ӿ����ùؼ��֣�
	 *  <����> url���ӿں�����Ӧ�������ַ��String��
		<hessian���룬Json����Ҫ> method���ӿں�������String��,hessian�������
		<Hessian����,
		 Json��ѡ�������ΪJsonRemoteBO����Ϊ����ʵ����ģ��,
		          ����˲�������Ϊ����㲻��JsonRemoteBO�ṹ> resultBO���ӿں������صĽ������ģ�ͣ�Class��
		<��ѡ> cryptoReq���Ƿ�����������Ҫ���ܣ�boolean��,Ĭ��Ϊ���ܵģ��򽫵���getCrypInterface()��ʵ��������м���
		<��ѡ> cryptoRes���Ƿ񷵻�������Ҫ���ܣ�boolean��,Ĭ��Ϊ���ܵģ��򽫵���getCrypInterface()��ʵ��������н���
		<��ѡ> isUseCache���ӿ��Ƿ�ʹ�û�����ƣ�boolean����Ĭ����false������ʹ�û���
		<��ѡ> cacheMaxTime���ӿ�ʹ�û������ʱ���˻������ݵ������Чʱ����int, ��λΪ���ӣ�
		<��ѡ> noWaitCursor���˽ӿڲ���Ҫ�ȴ���ꡣ
		<��ѡ> noCareResponse��ֻ��Ҫ����Ϣ�����������������ķ����������ķ��ء�ע�⣺�������Ϊ����noWaitCursorҲ��������Ϊ��
	  -->
	 */
	@Override
	public Map<String, Map<String, Object>> remoteSettings() {
		Map<String, Map<String, Object>> retMap = new HashMap<String, Map<String,Object>>();
		
		Map<String, Object> itemMap = null;
		//ͨ�ýӿ�
		itemMap = new HashMap<String, Object>();
		itemMap.put("url", "servlet/jsonServlet");
		retMap.put("ͨ�ýӿ�", itemMap);
		
		//�û���¼�ӿ�
		itemMap = new HashMap<String, Object>();
		itemMap.put("url", "servlet/jsonServlet");
		itemMap.put("resultBO", LoginInfoBO.class);
		retMap.put("�û���¼", itemMap);
		
		//�ļ��ϴ��ӿ�
		itemMap = new HashMap<String, Object>();
		if (s_defaultServerUrl.contains(s_productEnvIP)) {//����������ַ
			itemMap.put("url", "servlet/FileUploadServlet");
		} else {//���Ի���
			itemMap.put("url", "servlet/UploadServlet");
		}
		retMap.put("�ļ��ϴ�", itemMap);
		
		//�ļ�ɾ���ӿ�
		itemMap = new HashMap<String, Object>();
		itemMap.put("noCareResponse", true);
		if (s_defaultServerUrl.contains(s_productEnvIP)) {//����������ַ
			itemMap.put("url", "servlet/FileDeleteServlet");
		} else {//���Ի���
			itemMap.put("url", "servlet/DeleteServlet");
		}
		retMap.put("�ļ�ɾ��", itemMap);
		
		//�汾���ӿ�
		itemMap = new HashMap<String, Object>();
		itemMap.put("url", "servlet/jsonServlet");
		itemMap.put("noWaitCursor", true);
		retMap.put("�汾���", itemMap);
		
		return retMap;
	}

	/**
	 * @brief ���ݷ���ӿڷ��ص����ݣ�������ҳ��Ϣ
	 *        ���Ǹ��б�ҳ���ҳʹ�õ�
	 * @param resData  �ӿڷ��ص��������ݰ�
	 * @param returnClass  �ӿڷ��ص����ݰ�����
	 * 
	 * @return ��ҳ������Ϣ
	 */
	@Override
	public RemotePageConfig onParsePageConfig(Object resObject) {
		return null;
	}
	
	/**
	 * @brief ҵ��ӿڷ��ص����ݣ���Ŀ������Ա���Ը�����Ŀ�����ݽӿ�ģ�Ͷ�������ص������жϼ�һЩ��ش���
	 * ע�⣺context��onResponsSuccess��onResponsFailedҲ���ݾ���������á�
	 *        
	 * @param context ���ýӿڵķ��ؽ����߾��
	 * @param actionTag ����ı�־
	 * @param resObject �ӿڷ��ص����ݰ�
	 * @param returnClass �������������������ݲ���
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onResponseSucceed(IRemoteResponse context, Class returnClass, int actionTag, Object resData) {
		try {
			if (null != resData) {
				JsonRemoteBO jsonBO = XCJsonHelper.parseObject(resData.toString(), JsonRemoteBO.class);
				if (null != jsonBO && null != returnClass && null != jsonBO.getObject()) {
					JSONObject objJson = (JSONObject)jsonBO.getObject();
					jsonBO.setObject(XCJsonHelper.parseObject(objJson.toString(), returnClass));
				}
				
				RemotePageConfig pageConfig = null;
				if (null != jsonBO) {
					JsonRemotePagerBO pagerBO = jsonBO.getPager();
					if (null != pagerBO) {
						pageConfig = new RemotePageConfig();
						pageConfig.setCurrentPage(pagerBO.getPage());
						pageConfig.setPageSize(pagerBO.getPageSize());
						pageConfig.setTotalPage(pagerBO.getTotalPage());
						pageConfig.setTotalRow(pagerBO.getTotalRow());
					}
				} else {
					pageConfig = RemoteInterfaceSettings.instance().onParsePageConfig(resData);
				}
				
				if (context instanceof XCBaseListFragment) {
					((XCBaseListFragment)context).onManagementPager(pageConfig);
				} else if (context instanceof XCBaseListActivity) {
					((XCBaseListActivity)context).onManagementPager(pageConfig);
				}
				if (null == jsonBO) {
					context.onResponsFailed(actionTag, "�ӿڷ��������޷�������");
				} else {
					int errorCode = BeaRemoteErrorManager.analyzeRemoteError(jsonBO.getError());
					if (BeaRemoteErrorManager.RemoteNoError == errorCode) {
						context.onResponsSuccess(actionTag, jsonBO.getObject());
					} else if (BeaRemoteErrorManager.RemoteHasError == errorCode) {
						context.onResponsFailed(actionTag, jsonBO.getError().getMsg());
					}
				}
			} else {
				context.onResponsFailed(actionTag, "�ӿڷ���Ϊ�գ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public IRemoteDataCryp getCrypInterface() {
		return XCCrypto.setDESKey("bea.mobile.application");
	}
	
	public static String downloadUrl(String fileUrl) {
		if (s_defaultServerUrl.contains(s_productEnvIP)) {//����������ַ
			return s_defaultServerUrl+"servlet/FileDownloadServlet?filePath="+fileUrl;
		} else {//���Ի���
			return s_defaultServerUrl+"servlet/DownloadServlet?filePath="+fileUrl;
		}
	}
}
