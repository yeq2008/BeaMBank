/**
 * 
 */
package com.bea.remote;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;


/**
 * @author cuiyuguo
 *
 */
@JsonModel
public class JsonRemoteHeaderBO implements Serializable{
	//用户名
	String user_name;
	//IP地址
	String ip;	
	//APP编号
	String plant_id;
	//会话唯一标识
	String intserv_token;
	public String getUser_name() {
		return user_name;
	}
	public String getIp() {
		return ip;
	}
	public String getPlant_id() {
		return plant_id;
	}
	public String getIntserv_token() {
		return intserv_token;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setPlant_id(String plant_id) {
		this.plant_id = plant_id;
	}
	public void setIntserv_token(String intserv_token) {
		this.intserv_token = intserv_token;
	}	
}
