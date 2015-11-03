package com.bea.remote.models.crm;

import java.io.Serializable;

/**
 * crm 存款
 * @author fanglinhao
 */
public class CrmCunKuan implements Serializable {
	private String BALANCE ;// 余额 
	private String CURR_CD ;// 币种代码 
	private String EFFECT_RATE ;// 执行利率（%）
	private String ACCT_STAUTS ;// 账户状态
	private String PROD_NM;//产品分类名称 
	private String MONTH_AVG_RMB;//账户余额月日均（折人民币）
	private String YEAR_AVG_RMB;//账户余额年日均（折人民币）
	public String getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(String bALANCE) {
		BALANCE = bALANCE;
	}
	public String getCURR_CD() {
		return CURR_CD;
	}
	public void setCURR_CD(String cURR_CD) {
		CURR_CD = cURR_CD;
	}
	public String getEFFECT_RATE() {
		return EFFECT_RATE;
	}
	public void setEFFECT_RATE(String eFFECT_RATE) {
		EFFECT_RATE = eFFECT_RATE;
	}
	public String getACCT_STAUTS() {
		return ACCT_STAUTS;
	}
	public void setACCT_STAUTS(String aCCT_STAUTS) {
		ACCT_STAUTS = aCCT_STAUTS;
	}
	public String getPROD_NM() {
		return PROD_NM;
	}
	public void setPROD_NM(String pROD_NM) {
		PROD_NM = pROD_NM;
	}
	public String getMONTH_AVG_RMB() {
		return MONTH_AVG_RMB;
	}
	public void setMONTH_AVG_RMB(String mONTH_AVG_RMB) {
		MONTH_AVG_RMB = mONTH_AVG_RMB;
	}
	public String getYEAR_AVG_RMB() {
		return YEAR_AVG_RMB;
	}
	public void setYEAR_AVG_RMB(String yEAR_AVG_RMB) {
		YEAR_AVG_RMB = yEAR_AVG_RMB;
	}
}
