package com.bea.database.codemodel;

import java.io.Serializable;

import xc.android.sqlite.annotation.Id;
import xc.android.sqlite.annotation.NotNull;
import xc.android.sqlite.annotation.Table;

/**
 * ��������
 */
@Table(name = "MMP_LOAN_RATE")
public class MMP_LOAN_RATE implements Serializable {
	private static final long serialVersionUID = -8418236687505290272L;
	@Id
	private String RATENO; //���ʱ��
	private int MINTERM; //��С׼������
	private int MAXTERM; //���׼������
	private String RATEVALUE; //����ֵ
	private String EFFECTDATE; //��Ч����
	private String RATETYPE; //��������
	private String VERSION;// �汾��
	private String OPR_TIME;// ��������ʱ��
	@NotNull(defValue="Y")
	private String OPR_TYPE;// ����״̬
	public String getRATENO() {
		return RATENO;
	}
	public void setRATENO(String rATENO) {
		RATENO = rATENO;
	}
	public int getMINTERM() {
		return MINTERM;
	}
	public void setMINTERM(int mINTERM) {
		MINTERM = mINTERM;
	}
	public int getMAXTERM() {
		return MAXTERM;
	}
	public void setMAXTERM(int mAXTERM) {
		MAXTERM = mAXTERM;
	}
	public String getRATEVALUE() {
		return RATEVALUE;
	}
	public void setRATEVALUE(String rATEVALUE) {
		RATEVALUE = rATEVALUE;
	}
	public String getEFFECTDATE() {
		return EFFECTDATE;
	}
	public void setEFFECTDATE(String eFFECTDATE) {
		EFFECTDATE = eFFECTDATE;
	}
	public String getRATETYPE() {
		return RATETYPE;
	}
	public void setRATETYPE(String rATETYPE) {
		RATETYPE = rATETYPE;
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
}
