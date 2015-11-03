package com.bea.remote.models.crm;

/**
 * crm 贷款
 * @author fanglinhao
 */
public class CrmDaikuan {
	private String LOAN_AMT ;// 贷款本金 
	private String SUM_PAY_MONEY ;// 累计还款金额
	private String ACTUAL_RATE ;// 实际年利率 
	private String DUE_BILL_NO ;// 借据编号
	private String CUUR_CD ;// 币种 
	private String LOAN_BAL ;// 贷款余额
	private String REC_ABLE_INT_RMB ;// 利息余额
	private String LOAN_STATUS ;// 贷款状态
	public String getLOAN_AMT() {
		return LOAN_AMT;
	}
	public void setLOAN_AMT(String lOAN_AMT) {
		LOAN_AMT = lOAN_AMT;
	}
	public String getSUM_PAY_MONEY() {
		return SUM_PAY_MONEY;
	}
	public void setSUM_PAY_MONEY(String sUM_PAY_MONEY) {
		SUM_PAY_MONEY = sUM_PAY_MONEY;
	}
	public String getACTUAL_RATE() {
		return ACTUAL_RATE;
	}
	public void setACTUAL_RATE(String aCTUAL_RATE) {
		ACTUAL_RATE = aCTUAL_RATE;
	}
	public String getDUE_BILL_NO() {
		return DUE_BILL_NO;
	}
	public void setDUE_BILL_NO(String dUE_BILL_NO) {
		DUE_BILL_NO = dUE_BILL_NO;
	}
	public String getCUUR_CD() {
		return CUUR_CD;
	}
	public void setCUUR_CD(String cUUR_CD) {
		CUUR_CD = cUUR_CD;
	}
	public String getLOAN_BAL() {
		return LOAN_BAL;
	}
	public void setLOAN_BAL(String lOAN_BAL) {
		LOAN_BAL = lOAN_BAL;
	}
	public String getREC_ABLE_INT_RMB() {
		return REC_ABLE_INT_RMB;
	}
	public void setREC_ABLE_INT_RMB(String rEC_ABLE_INT_RMB) {
		REC_ABLE_INT_RMB = rEC_ABLE_INT_RMB;
	}
	public String getLOAN_STATUS() {
		return LOAN_STATUS;
	}
	public void setLOAN_STATUS(String lOAN_STATUS) {
		LOAN_STATUS = lOAN_STATUS;
	}
}
