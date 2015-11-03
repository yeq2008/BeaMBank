package com.bea.remote.models.grwdy;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;

import com.bea.application.BeaAppSettings;
import com.bea.database.DbManager;
import com.bea.remote.models.user.UserInfoBO;

@JsonModel
public class CreditFzBO implements Serializable {

	private static final long serialVersionUID = 9208260521501489395L;
	final UserInfoBO userInfo = BeaAppSettings.getUserInfo();
	// 负债项目分类
	private String PPAType = "15";
	//负债项目分类名称
	private String PPATypeName;
	// 币种
	private String POCurrency = "01";
	// 币种名称
	private String POCurrencyName;
	// 余额
	private String POBalance;
	// 授信额度
	private String Plimit;
	// 每月还款金额
	private String PPM;
	// 登记人
	private String PPRNum = userInfo.getEmployee_mark();
	// 登记人名称
	private String PPRNumName = userInfo.getUser_name();
	// 登记机构
	private String PPRBEAD = userInfo.getOrg_id();
	// 登记机构名称
	private String PPRBEADName = DbManager.getOrgNameById(userInfo.getOrg_id());

	
	
	public String getPPAType() {
		return PPAType;
	}

	public void setPPAType(String pPAType) {
		PPAType = pPAType;
	}

	public String getPPATypeName() {
		return PPATypeName;
	}

	public void setPPATypeName(String pPATypeName) {
		PPATypeName = pPATypeName;
	}

	public String getPOCurrency() {
		return POCurrency;
	}

	public void setPOCurrency(String pOCurrency) {
		POCurrency = pOCurrency;
	}

	public String getPOCurrencyName() {
		return POCurrencyName;
	}

	public void setPOCurrencyName(String pOCurrencyName) {
		POCurrencyName = pOCurrencyName;
	}

	public String getPOBalance() {
		return POBalance;
	}

	public void setPOBalance(String pOBalance) {
		POBalance = pOBalance;
	}

	public String getPlimit() {
		return Plimit;
	}

	public void setPlimit(String plimit) {
		Plimit = plimit;
	}

	public String getPPM() {
		return PPM;
	}

	public void setPPM(String pPM) {
		PPM = pPM;
	}

	public String getPPRNum() {
		return PPRNum;
	}

	public void setPPRNum(String pPRNum) {
		PPRNum = pPRNum;
	}

	public String getPPRNumName() {
		return PPRNumName;
	}

	public void setPPRNumName(String pPRNumName) {
		PPRNumName = pPRNumName;
	}

	public String getPPRBEAD() {
		return PPRBEAD;
	}

	public void setPPRBEAD(String pPRBEAD) {
		PPRBEAD = pPRBEAD;
	}

	public String getPPRBEADName() {
		return PPRBEADName;
	}

	public void setPPRBEADName(String pPRBEADName) {
		PPRBEADName = pPRBEADName;
	}

}
