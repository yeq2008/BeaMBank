/**
 * 
 */
package com.bea.database.codemodel;

import java.io.Serializable;

import xc.android.sqlite.annotation.Id;
import xc.android.sqlite.annotation.NotNull;
import xc.android.sqlite.annotation.Table;

/**
 * @author cuiyuguo
 *	项目中的地区码表数据结构
 */
@Table(name = "SYS_AC_AREA")
public final class SYS_AC_AREA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -806809727179705541L;

	//编号		√	NUMBER	19
	@Id
	String AREA_ID;	
	//地区编号	√		VARCHAR2	50
	String AREA_NO;		
	//地区名称	√		VARCHAR2	100
	String AREA_NAME;
	//地区级别	√		CHAR	1
	int AREA_LEVEL;	
	//排序	√		VARCHAR2	50
	String VIEW_ORDER;
	//上级地区	√		NUMBER	19
	String PARENT_ID;
	//版本号	√		NUMBER	19
	String VERSION;	
	//表最后更新时间	√		DATE
	String OPR_TIME;	
	//数据状态	√		CHAR	1	Y表示可用,N表示不可用
	@NotNull(defValue="Y")
	String OPR_TYPE;
	public String getAREA_ID() {
		return AREA_ID;
	}
	public String getAREA_NO() {
		return AREA_NO;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public int getAREA_LEVEL() {
		return AREA_LEVEL;
	}
	public String getVIEW_ORDER() {
		return VIEW_ORDER;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public String getVERSION() {
		return VERSION;
	}
	public String getOPR_TIME() {
		return OPR_TIME;
	}
	public String getOPR_TYPE() {
		return OPR_TYPE;
	}
	public void setAREA_ID(String aREA_ID) {
		AREA_ID = aREA_ID;
	}
	public void setAREA_NO(String aREA_NO) {
		AREA_NO = aREA_NO;
	}
	public void setAREA_NAME(String aREA_NAME) {
		AREA_NAME = aREA_NAME;
	}
	public void setAREA_LEVEL(int aREA_LEVEL) {
		AREA_LEVEL = aREA_LEVEL;
	}
	public void setVIEW_ORDER(String vIEW_ORDER) {
		VIEW_ORDER = vIEW_ORDER;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public void setOPR_TIME(String oPR_TIME) {
		OPR_TIME = oPR_TIME;
	}
	public void setOPR_TYPE(String oPR_TYPE) {
		OPR_TYPE = oPR_TYPE;
	}
}
