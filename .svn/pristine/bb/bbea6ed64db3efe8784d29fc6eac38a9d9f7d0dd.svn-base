/**
 * 
 */
package com.bea.remote;

import java.io.Serializable;
import java.util.Map;

import com.bea.application.ConstDefined;
import com.bea.application.BeaAppSettings;

import xc.android.remote.RemotePageConfig;
import xc.android.remote.json.JsonModel;
import xc.android.utils.XCJsonHelper;

/**
 * @author cuiyuguo
 *
 */
@JsonModel
public class JsonRemoteBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8383732603221056623L;

	//接口id
	int commId;
	//接口请求攻关信息头
	JsonRemoteHeaderBO head;
	//接口返回的错误信息
	JsonRemoteErrorBO error;
	//接口返回的页码信息
	JsonRemotePagerBO pager;
	//接口返回的实际数据
	Object object;
	
	public JsonRemoteBO() {
		if (null != BeaAppSettings.getLoginInfo()) {
			head = new JsonRemoteHeaderBO();
			head.setIntserv_token(BeaAppSettings.
			getLoginInfo().getIntserv_token());
			head.setUser_name(BeaAppSettings.
			getLoginInfo().getUserinfo().getUser_name());
			head.setPlant_id(ConstDefined.AppPlantID);
		}
	}
	
	public static Map<String, ?> reqParam(int commId, Object object) {
		JsonRemoteBO remoteBO = new JsonRemoteBO();
		remoteBO.commId = commId;
		remoteBO.object = object;
		
		return XCJsonHelper.beanToMap(remoteBO);
	}
	public static Map<String, ?> reqParam(int commId, Object object, RemotePageConfig pager) {
		JsonRemotePagerBO pagerBO = null;
		if (null != pager) {
			pagerBO = new JsonRemotePagerBO();
			pagerBO.setPage(pager.getCurrentPage());
			pagerBO.setPageSize(pager.getPageSize());
		}
		JsonRemoteBO remoteBO = new JsonRemoteBO();
		remoteBO.commId = commId;
		remoteBO.object = object;
		remoteBO.pager = pagerBO;
		
		return XCJsonHelper.beanToMap(remoteBO);
	}
	
	
	/**
	 *	@brief	将json字符串反解析成JsonRemoteBO对象
	 *
	 *	@param 	jsonString 	要解析的json字符串
	 *	@param 	objClass    JsonRemoteBO对象内真正的数据模型类
	 *
	 *	@return	<#return value description#>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JsonRemoteBO parseJson(String jsonString, Class objClass) {
		JsonRemoteBO jsonBO = null;
		try {
			jsonBO = XCJsonHelper.parseObject(jsonString, JsonRemoteBO.class);
			if (null != jsonBO && null != objClass) {
				jsonBO.setObject(XCJsonHelper.parseObject(jsonBO.toString(), objClass));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonBO;
	}

	public int getCommId() {
		return commId;
	}
	public void setCommId(int commId) {
		this.commId = commId;
	}
	public JsonRemoteErrorBO getError() {
		return error;
	}
	public void setError(JsonRemoteErrorBO error) {
		this.error = error;
	}
	public JsonRemotePagerBO getPager() {
		return pager;
	}
	public void setPager(JsonRemotePagerBO pager) {
		this.pager = pager;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}

	public JsonRemoteHeaderBO getHead() {
		return head;
	}
}
