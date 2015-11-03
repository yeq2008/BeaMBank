package com.bea.database.codemodel;

import java.io.Serializable;

import xc.android.sqlite.annotation.Id;
import xc.android.sqlite.annotation.NotNull;
import xc.android.sqlite.annotation.Table;

/**
 * 贷款利率
 */
@Table(name = "MMP_LOAN_RATE")
public class MMP_LOAN_RATE implements Serializable {
	private static final long serialVersionUID = -8418236687505290272L;
	@Id
	private String RATENO; //利率编号
	private int MINTERM; //最小准入条件
	private int MAXTERM; //最大准入条件
	private String RATEVALUE; //利率值
	private String EFFECTDATE; //生效日期
	private String RATETYPE; //利率类型
	private String VERSION;// 版本号
	private String OPR_TIME;// 表最后更新时间
	@NotNull(defValue="Y")
	private String OPR_TYPE;// 数据状态
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
