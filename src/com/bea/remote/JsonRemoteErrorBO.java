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
public class JsonRemoteErrorBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7010743075538557353L;
	
	//´íÎó´úÂë
	int     code;
	
	//´íÎóÏûÏ¢
	String  msg;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
