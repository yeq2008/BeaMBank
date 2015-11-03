package com.bea.remote.models.grwdy;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;

import com.bea.application.BeaAppSettings;
import com.bea.database.DbManager;
import com.bea.remote.models.user.UserInfoBO;
@JsonModel
public class CreditZcBO implements Serializable {

	private static final long serialVersionUID = -866981060731767733L;
	final UserInfoBO userInfo = BeaAppSettings.getUserInfo();
	// �ʲ���Ŀ����
	private String PAType = "07";
	// �ʲ���Ŀ��������
	private String PATypeName;
	// ����
	private String PICurrency = "01";
	// ��������
	private String PICurrencyName;
	// ���
	private String PIBalance;
	// �Ǽ���
	private String PRNum = userInfo.getEmployee_mark();
	// �Ǽ�������
	private String PRNumName = userInfo.getUser_name();
	// �Ǽǻ���
	private String PRBEAD = userInfo.getOrg_id();
	// �Ǽǻ���
	private String PRBEADName = DbManager.getOrgNameById(userInfo.getOrg_id());

	public String getPAType() {
		return PAType;
	}

	public void setPAType(String pAType) {
		PAType = pAType;
	}

	public String getPATypeName() {
		return PATypeName;
	}

	public void setPATypeName(String pATypeName) {
		PATypeName = pATypeName;
	}

	public String getPICurrency() {
		return PICurrency;
	}

	public void setPICurrency(String pICurrency) {
		PICurrency = pICurrency;
	}

	public String getPICurrencyName() {
		return PICurrencyName;
	}

	public void setPICurrencyName(String pICurrencyName) {
		PICurrencyName = pICurrencyName;
	}

	public String getPIBalance() {
		return PIBalance;
	}

	public void setPIBalance(String pIBalance) {
		PIBalance = pIBalance;
	}

	public String getPRNum() {
		return PRNum;
	}

	public void setPRNum(String pRNum) {
		PRNum = pRNum;
	}

	public String getPRNumName() {
		return PRNumName;
	}

	public void setPRNumName(String pRNumName) {
		PRNumName = pRNumName;
	}

	public String getPRBEAD() {
		return PRBEAD;
	}

	public void setPRBEAD(String pRBEAD) {
		PRBEAD = pRBEAD;
	}

	public String getPRBEADName() {
		return PRBEADName;
	}

	public void setPRBEADName(String pRBEADName) {
		PRBEADName = pRBEADName;
	}

}
