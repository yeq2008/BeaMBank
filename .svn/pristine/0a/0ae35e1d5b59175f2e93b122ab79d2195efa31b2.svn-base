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
 *	产品附件信息
 */
@Table(name = "MMP_PRODUCT_ATT")
public final class MMP_PRODUCT_ATT implements Serializable {

//	名称	解释	是否允许为空	主键	数据类型	数据长度	备注
//	ATT_ID	编号		√	NUMBER	19	
//	PRODUCT_ID	产品类别编号	√		NUMBER	19	
//	FILE_NAME	文件名	√		VARCHAR2	300	
//	FILE_PATH	文件路径	√		VARCHAR2	300	
//	VERSION	版本号	√		NUMBER	19	
//	OPR_TIME	表最后更新时间	√		DATE		
//	OPR_TYPE	数据状态	√		CHAR	1	Y表示可用,N表示不可用
	
	@Id
	String ATT_ID;	
	String PRODUCT_ID;
	String FILE_NAME;	
	String FILE_PATH;	
	String VERSION;
	String OPR_TIME;
	@NotNull(defValue="Y")
	String OPR_TYPE;
	public String getATT_ID() {
		return ATT_ID;
	}
	public String getPRODUCT_ID() {
		return PRODUCT_ID;
	}
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public String getFILE_PATH() {
		return FILE_PATH;
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
	public void setATT_ID(String aTT_ID) {
		ATT_ID = aTT_ID;
	}
	public void setPRODUCT_ID(String pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
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
