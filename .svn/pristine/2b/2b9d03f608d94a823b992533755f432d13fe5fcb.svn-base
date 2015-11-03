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
 *	产品信息
 */
@Table(name = "MMP_PRODUCT")
public class MMP_PRODUCT implements Serializable {

//	名称	解释	是否允许为空	主键	数据类型	数据长度	备注
//	PRODUCT_ID	编号		√	NUMBER	19	
//	PRODUCT_NAME	产品名称	√		VARCHAR2	300	
//	CATEGORY_ID	产品类别编号	√		NUMBER	19	
//	VERSION	版本号	√		NUMBER	19	
//	OPR_TIME	表最后更新时间	√		DATE		
//	OPR_TYPE	数据状态	√		CHAR	1	Y表示可用,N表示不可用
//	COVER_NAME	封面文件名	√		VARCHAR2	300	
//	COVER_PATH	封面文件路径	√		VARCHAR2	300	
	@Id
	String PRODUCT_ID;
	String PRODUCT_NAME;	
	String CATEGORY_ID;	
	String VERSION;	
	String OPR_TIME;
	@NotNull(defValue="Y")
	String OPR_TYPE;
	String COVER_NAME;		
	String COVER_PATH;
	int COLLECT_FLAG;
	
	public String getPRODUCT_ID() {
		return PRODUCT_ID;
	}
	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}
	public String getCATEGORY_ID() {
		return CATEGORY_ID;
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
	public void setPRODUCT_ID(String pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}
	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}
	public void setCATEGORY_ID(String cATEGORY_ID) {
		CATEGORY_ID = cATEGORY_ID;
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
	public String getCOVER_NAME() {
		return COVER_NAME;
	}
	public String getCOVER_PATH() {
		return COVER_PATH;
	}
	public void setCOVER_NAME(String cOVER_NAME) {
		COVER_NAME = cOVER_NAME;
	}
	public void setCOVER_PATH(String cOVER_PATH) {
		COVER_PATH = cOVER_PATH;
	}
	public int getCOLLECT_FLAG() {
		return COLLECT_FLAG;
	}
	public void setCOLLECT_FLAG(int cOLLECT_FLAG) {
		COLLECT_FLAG = cOLLECT_FLAG;
	}
	
}
