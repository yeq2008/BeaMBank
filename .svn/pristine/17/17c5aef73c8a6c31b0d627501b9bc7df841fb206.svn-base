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
 *	项目中的产品类别信息
 */
@Table(name = "MMP_PRODUCT_CATEGORY")
public final class MMP_PRODUCT_CATEGORY implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1507392773568546842L;

//	名称	解释	是否允许为空	主键	数据类型	数据长度	备注
//	CATEGORY_ID	编号		√	NUMBER	19	
//	CATEGORY_NAME	产品类别名称	√		VARCHAR2	300	
//	PARENT_ID	产品大类	√		NUMBER	19	
//	VERSION	版本号	√		NUMBER	19	
//	OPR_TIME	表最后更新时间	√		DATE		
//	OPR_TYPE	数据状态	√		CHAR	1	Y表示可用,N表示不可用
	
	@Id
	String CATEGORY_ID;
	String CATEGORY_NAME;
	int VIEW_ORDER;
	String PARENT_ID;	
	String VERSION;	
	@NotNull(defValue="Y")
	String OPR_TIME;		
	String OPR_TYPE;
	
	public String getCATEGORY_ID() {
		return CATEGORY_ID;
	}
	public String getCATEGORY_NAME() {
		return CATEGORY_NAME;
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
	public void setCATEGORY_ID(String cATEGORY_ID) {
		CATEGORY_ID = cATEGORY_ID;
	}
	public void setCATEGORY_NAME(String cATEGORY_NAME) {
		CATEGORY_NAME = cATEGORY_NAME;
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
	public int getVIEW_ORDER() {
		return VIEW_ORDER;
	}
	public void setVIEW_ORDER(int vIEW_ORDER) {
		VIEW_ORDER = vIEW_ORDER;
	}
	
}
