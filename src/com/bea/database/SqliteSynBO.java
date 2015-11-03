package com.bea.database;

import java.io.Serializable;

import xc.android.sqlite.annotation.Id;
import xc.android.sqlite.annotation.Table;



/**
 *	@brief	数据库码表同步管理表
 */
@Table(name = "allConstant")
public class SqliteSynBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1135162576709717059L;
	
	//子表英文名字
	@Id
	String  TABLENAME;
	//子表中文名
	String  CHS_NAME;
	//子表当前版本号
	String  VERSION;
	
	public String getTABLENAME() {
		return TABLENAME;
	}
	public void setTABLENAME(String tTABLENAME) {
		TABLENAME = tTABLENAME;
	}
	public String getCHS_NAME() {
		return CHS_NAME;
	}
	public void setCHS_NAME(String cHS_NAME) {
		CHS_NAME = cHS_NAME;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
}
