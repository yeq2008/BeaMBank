package com.bea.database.codemodel;

import java.io.Serializable;

import xc.android.sqlite.annotation.Id;
import xc.android.sqlite.annotation.NotNull;
import xc.android.sqlite.annotation.Table;
@Table(name = "SYS_AC_ORG")
public class SYS_AC_ORG implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String ORG_ID; // 机构编号
	private String ORG_NAME; // 机构名称
	private String ORG_CODE;// 机构代码
	private String ORG_LEVEL;// 级别
	private String CITY;// 所在地
	private String ADDRESS;// 通讯地址
	private String PARENT_ID; // 上级机构
	private String PARENT_NAME; // 上级机构名称
	private String STATUS;// 状态
	private String VERSION;//版本号
	private String OPR_TIME; // 表最后更新时间
	@NotNull(defValue="Y")
	private String OPR_TYPE; // 数据状态
	public String getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(String oRG_ID) {
		ORG_ID = oRG_ID;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String oRG_NAME) {
		ORG_NAME = oRG_NAME;
	}
	public String getORG_CODE() {
		return ORG_CODE;
	}
	public void setORG_CODE(String oRG_CODE) {
		ORG_CODE = oRG_CODE;
	}
	public String getORG_LEVEL() {
		return ORG_LEVEL;
	}
	public void setORG_LEVEL(String oRG_LEVEL) {
		ORG_LEVEL = oRG_LEVEL;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public String getOPR_TIME() {
		return OPR_TIME;
	}
	public void setOPR_TIME(String oPR_TIME) {
		OPR_TIME = oPR_TIME;
	}
	public String getOPR_TYPE() {
		return OPR_TYPE;
	}
	public void setOPR_TYPE(String oPR_TYPE) {
		OPR_TYPE = oPR_TYPE;
	}
	public String getPARENT_NAME() {
		return PARENT_NAME;
	}
	public void setPARENT_NAME(String pARENT_NAME) {
		PARENT_NAME = pARENT_NAME;
	}
}
