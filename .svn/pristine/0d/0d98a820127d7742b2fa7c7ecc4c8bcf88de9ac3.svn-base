package com.bea.remote.models.paxb;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;

/**
 * 平安信保 任务
 * @author fanglinhao
 */
@JsonModel
public class WaitToSelectTask implements Serializable {
	private String APPLYCURRENCY;//申请币种   
	private String IDType;//证件类型      
	private String ID;//客户编号/证件号      
	private String taskflag;//1 待选 2已选
	private String Policyno;//保单编号        
	private String ORGID;//经办机构           
	private String mainid;//
	private String ApplAmt;//申请金额      
	private String CustName;//姓名     
	public WaitToSelectTask(){}
	public WaitToSelectTask(String aPPLYCURRENCY, String iDType, String iD,
			String taskflag, String policyno, String oRGID, String mainid,
			String applAmt, String custName) {
		APPLYCURRENCY = aPPLYCURRENCY;
		IDType = iDType;
		ID = iD;
		this.taskflag = taskflag;
		Policyno = policyno;
		ORGID = oRGID;
		this.mainid = mainid;
		ApplAmt = applAmt;
		CustName = custName;
	}
	public String getAPPLYCURRENCY() {
		return APPLYCURRENCY;
	}
	public void setAPPLYCURRENCY(String aPPLYCURRENCY) {
		APPLYCURRENCY = aPPLYCURRENCY;
	}
	public String getIDType() {
		return IDType;
	}
	public void setIDType(String iDType) {
		IDType = iDType;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTaskflag() {
		return taskflag;
	}
	public void setTaskflag(String taskflag) {
		this.taskflag = taskflag;
	}
	public String getPolicyno() {
		return Policyno;
	}
	public void setPolicyno(String policyno) {
		Policyno = policyno;
	}
	public String getORGID() {
		return ORGID;
	}
	public void setORGID(String oRGID) {
		ORGID = oRGID;
	}
	public String getMainid() {
		return mainid;
	}
	public void setMainid(String mainid) {
		this.mainid = mainid;
	}
	public String getApplAmt() {
		return ApplAmt;
	}
	public void setApplAmt(String applAmt) {
		ApplAmt = applAmt;
	}
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}
}
