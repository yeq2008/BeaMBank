package com.bea.remote.models.crm;

import java.io.Serializable;

/**
 * crm ����
 * @author fanglinhao
 */
public class CrmBaoXian implements Serializable {
	private String INSSRANCE_AMT;//��Ʒ���Ѷ�
	private String PAY_TERM;//�ɷ����ޣ��꣩
	private String BALANCE_RMB;//�������
	private String PROD_NM;//��Ʒ��������
	private String CURR_CD;//���� 
	private String INSS_MATUR_DT;//������������
	private String AUM_BAL;//AUM��� 
	private String ACCT_STAT_CD;//�˻�״̬ 
	public String getINSSRANCE_AMT() {
		return INSSRANCE_AMT;
	}
	public void setINSSRANCE_AMT(String iNSSRANCE_AMT) {
		INSSRANCE_AMT = iNSSRANCE_AMT;
	}
	public String getPAY_TERM() {
		return PAY_TERM;
	}
	public void setPAY_TERM(String pAY_TERM) {
		PAY_TERM = pAY_TERM;
	}
	public String getBALANCE_RMB() {
		return BALANCE_RMB;
	}
	public void setBALANCE_RMB(String bALANCE_RMB) {
		BALANCE_RMB = bALANCE_RMB;
	}
	public String getPROD_NM() {
		return PROD_NM;
	}
	public void setPROD_NM(String pROD_NM) {
		PROD_NM = pROD_NM;
	}
	public String getCURR_CD() {
		return CURR_CD;
	}
	public void setCURR_CD(String cURR_CD) {
		CURR_CD = cURR_CD;
	}
	public String getINSS_MATUR_DT() {
		return INSS_MATUR_DT;
	}
	public void setINSS_MATUR_DT(String iNSS_MATUR_DT) {
		INSS_MATUR_DT = iNSS_MATUR_DT;
	}
	public String getAUM_BAL() {
		return AUM_BAL;
	}
	public void setAUM_BAL(String aUM_BAL) {
		AUM_BAL = aUM_BAL;
	}
	public String getACCT_STAT_CD() {
		return ACCT_STAT_CD;
	}
	public void setACCT_STAT_CD(String aCCT_STAT_CD) {
		ACCT_STAT_CD = aCCT_STAT_CD;
	}
}
