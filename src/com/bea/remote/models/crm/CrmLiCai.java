package com.bea.remote.models.crm;

import java.io.Serializable;

/**
 * crm ���
 * @author fanglinhao
 *
 */
public class CrmLiCai implements Serializable {
	private String OPEN_DATE;//��������
	private String TRANS_AMT;//���ױ���
	private String TOT_VOL;//�ݶ�����
	private String FACE_VALUE;//��Ʒ��ֵ
	private String BALANCE;//��Ʒ��ֵ
	private String PROD_NM;//��Ʋ�Ʒ��������
	public String getOPEN_DATE() {
		return OPEN_DATE;
	}
	public void setOPEN_DATE(String oPEN_DATE) {
		OPEN_DATE = oPEN_DATE;
	}
	public String getTRANS_AMT() {
		return TRANS_AMT;
	}
	public void setTRANS_AMT(String tRANS_AMT) {
		TRANS_AMT = tRANS_AMT;
	}
	public String getTOT_VOL() {
		return TOT_VOL;
	}
	public void setTOT_VOL(String tOT_VOL) {
		TOT_VOL = tOT_VOL;
	}
	public String getFACE_VALUE() {
		return FACE_VALUE;
	}
	public void setFACE_VALUE(String fACE_VALUE) {
		FACE_VALUE = fACE_VALUE;
	}
	public String getBALANCE() {
		return BALANCE;
	}
	public void setBALANCE(String bALANCE) {
		BALANCE = bALANCE;
	}
	public String getPROD_NM() {
		return PROD_NM;
	}
	public void setPROD_NM(String pROD_NM) {
		PROD_NM = pROD_NM;
	}
}
