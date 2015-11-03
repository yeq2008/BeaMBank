/**
 * 
 */
package com.bea.remote.models.user;

import java.io.Serializable;
import java.util.List;

import xc.android.database.DbQueryManager;
import xc.android.remote.json.JsonModel;

/**
 * @author cuiyuguo
 *	用户登录后获得的数据模型
 */
@JsonModel
public class LoginInfoBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3557323874048284194L;

	UserInfoBO   userinfo;
	List<ModuelInfoBO> module_info;
	String intserv_token;
	
	//服务器系统时间
	String system_time;
	//草稿件条数
	int draft_cnt;
	//待发件条数
	int sendwait_cnt;
	//催收件条数
	int coll_cnt;
	public UserInfoBO getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserInfoBO userinfo) {
		this.userinfo = userinfo;
	}
	public String getIntserv_token() {
		return intserv_token;
	}
	public int getDraft_cnt() {
		return draft_cnt;
	}
	public int getSendwait_cnt() {
		return sendwait_cnt;
	}
	public void setIntserv_token(String intserv_token) {
		this.intserv_token = intserv_token;
	}
	public void setDraft_cnt(int draft_cnt) {
		this.draft_cnt = draft_cnt;
	}
	public void setSendwait_cnt(int sendwait_cnt) {
		this.sendwait_cnt = sendwait_cnt;
	}
	public void setModule_info(List<ModuelInfoBO> moduleinfo) {
		this.module_info = moduleinfo;
	}
	public List<ModuelInfoBO> getModule_info() {
		return module_info;
	}
	public int getColl_cnt() {
		return coll_cnt;
	}
	public void setColl_cnt(int coll_cnt) {
		this.coll_cnt = coll_cnt;
	}
	public String getSystem_time() {
		return system_time;
	}
	public void setSystem_time(String system_time) {
		this.system_time = system_time;
	}
}
