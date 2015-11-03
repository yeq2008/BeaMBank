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
 *	项目中的码表数据结构
 */
@Table(name = "YRL_BASIC_DATA")
public final class YRL_BASIC_DATA implements Serializable {
	@Id
	String DATA_TYPE;
	@Id
	String NO;
	String NAME;
	String ORDER_NO;
	String VERSION;
	@NotNull(defValue="Y")
	String OPR_TYPE;
	public YRL_BASIC_DATA(){}
	public YRL_BASIC_DATA(String nO, String nAME) {
		NO = nO;
		NAME = nAME;
	}
	public String getDATA_TYPE() {
		return DATA_TYPE;
	}
	public String getNO() {
		return NO;
	}
	public String getNAME() {
		return NAME;
	}
	public String getORDER_NO() {
		return ORDER_NO;
	}
	public String getVERSION() {
		return VERSION;
	}
	public String getOPR_TYPE() {
		return OPR_TYPE;
	}
	public void setDATA_TYPE(String dATA_TYPE) {
		DATA_TYPE = dATA_TYPE;
	}
	public void setNO(String nO) {
		NO = nO;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public void setORDER_NO(String oRDER_NO) {
		ORDER_NO = oRDER_NO;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public void setOPR_TYPE(String oPR_TYPE) {
		OPR_TYPE = oPR_TYPE;
	}
}
