/**
 * 
 */
package com.bea.remote.models.grwdy;

import java.io.Serializable;
import com.bea.application.BeaAppSettings;
import com.bea.database.DbManager;
import com.bea.remote.models.user.UserInfoBO;

import xc.android.remote.json.JsonModel;

/**
 * @author cuiyuguo 借款人个人资料数据模型
 */
@JsonModel
public final class BorrowerInforBO implements Serializable {
	private static final long serialVersionUID = -7011754493697216211L;
	final UserInfoBO userInfo = BeaAppSettings.getUserInfo();
	/**
	 * 中文姓
	 */
	private String MFName;
	/**
	 * 中文名
	 */
	private String MLName;
	/**
	 * 英文I姓
	 */
	private String MEFName;
	/**
	 * 英文名
	 */
	private String MELName;
	/**
	 * 性别
	 */
	private String MSex = "1";
	/**
	 * 国籍
	 */
	private String MCountry = "CHN";
	/**
	 * 出生日期
	 */
	private String MBDate;
	/**
	 * 是否同意我行交叉营销
	 */
	private String MSale = "1";
	/**
	 * 与我行业务来往情况
	 */
	private String MLRDT;
	/**
	 * 客户源
	 */
	private String MCS;
	/**
	 * 接收银行通知方式＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	 */
	private String MBRType = "01";
	/**
	 * 接收银行账单方式＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
	 */
	private String MMRType = "02";

	/**
	 * 邮箱
	 */
	private String MEmail;
	/**
	 * 婚姻状况
	 */
	private String MMType;
	/**
	 * 教育水平
	 */
	private String MEE;
	/**
	 * 证件类型
	 */
	private String MCType = "Ind1010";
	/**
	 * 证件号码
	 */
	private String MCNum;
	/**
	 * 发证国家
	 */
	private String MCC = "CHN";
	/**
	 * 证件到期日
	 */
	private String MCDate;
	/**
	 * 更新人名称
	 */
	private String MUNum = userInfo.getEmployee_mark();
	/**
	 * 录入人名称
	 */
	private String MINum = userInfo.getEmployee_mark();

	/**
	 * 住宅地址
	 */
	public AddressBO homeAddr = new AddressBO("1");
	
	/**
	 * 电话类型
	 */
	private String MTType = "1";
	/**
	 * 国际区号
	 */
	private String MIACode="86";
	/**
	 * 区号
	 */
	private String MACode;
	/**
	 * 电话号码
	 */
	private String MTNum;
	/**
	 * 分机号
	 */
	private String MTDNum;
	/**
	 * 是否为短信接收号码
	 */
	private String MRS = "1";
	/**
	 * 登记人
	 */
	private String MRNum = userInfo.getEmployee_mark();
	/**
	 * 登记机构
	 */
	private String MRBEAD = userInfo.getOrg_id();
	/**
	 * 更新人
	 */
	private String MRUNum = userInfo.getEmployee_mark();
	
	
	/**
	 * 收入类型
	 */
	private String MIType;
	/**
	 * 收入币种
	 */
	private String MCurrency = "01";
	/**
	 * 金额
	 */
	private String MMoney;
	/**
	 * 汇率
	 */
	private String MRate;
	/**
	 * 折算为人民币金额
	 */
	private String MRMB;
	/**
	 * 汇总家庭总收入
	 */
	private String MTMoney;
	
	private String is = "2";
	// -------------------------------------------
	
	private String MRINum = userInfo.getEmployee_mark() ;
	private String MRIBEAD = userInfo.getOrg_id();
	private String MRIUNum = userInfo.getEmployee_mark() ;;
	
	private String MRINumName = userInfo.getUser_name();
	private String MRIBEADName = DbManager.getOrgNameById(userInfo.getOrg_id());
	private String MRIUNumName = userInfo.getUser_name();
	
	public String getMRINumName() {
		return MRINumName;
	}

	public void setMRINumName(String mRINumName) {
		MRINumName = mRINumName;
	}

	public String getMRIBEADName() {
		return MRIBEADName;
	}

	public void setMRIBEADName(String mRIBEADName) {
		MRIBEADName = mRIBEADName;
	}

	public String getMRIUNumName() {
		return MRIUNumName;
	}

	public void setMRIUNumName(String mRIUNumName) {
		MRIUNumName = mRIUNumName;
	}

	// _______________________________界面不显示， name——————————————————————————
	/**
	 * 证件类型name
	 */
	private String MCTypeName;
	/**
	 * 更新人名称name
	 */
	private String MUNumName = userInfo.getUser_name();
	/**
	 * 录入人名称name
	 */
	private String MINumName = userInfo.getUser_name();
	/**
	 * 与我行业务来往情况name
	 */
	private String MLRDTName;
	/**
	 * 客户源name
	 */
	private String MCSName;
	
	/**
	 * 电话类型name
	 */
	private String MTTypeName;
	/**
	 * 收入类型name
	 */
	private String MITypeName;
	/**
	 * 收入币种name
	 */
	private String MCurrencyName;
	/**
	 * 登记人name
	 */
	private String MRNumName = userInfo.getUser_name();
	/**
	 * 登记机构name
	 */
	private String MRBEADName = DbManager.getOrgNameById(userInfo.getOrg_id());
	/**
	 * 更新人name
	 */
	private String MRUNumName = userInfo.getUser_name();

	/**
	 * 得到中文姓
	 */
	public String getMFName() {
		return MFName;
	}

	/**
	 * 设置中文姓
	 */
	public void setMFName(String mFName) {
		MFName = mFName;
	}

	/**
	 * 得到中文名
	 */
	public String getMLName() {
		return MLName;
	}

	/**
	 * 设置中文名
	 */
	public void setMLName(String mLName) {
		MLName = mLName;
	}

	/**
	 * 得到英文姓
	 */
	public String getMEFName() {
		return MEFName;
	}

	/**
	 * 设置英文姓
	 */
	public void setMEFName(String mEFName) {
		MEFName = mEFName;
	}

	/**
	 * 得到英文名
	 */
	public String getMELName() {
		return MELName;
	}

	/**
	 * 设置英文名
	 */
	public void setMELName(String mELName) {
		MELName = mELName;
	}

	/**
	 * 得到性别
	 */
	public String getMSex() {
		return MSex;
	}

	/**
	 * 设置性别
	 */
	public void setMSex(String mSex) {
		MSex = mSex;
	}

	/**
	 * 得到国籍
	 */
	public String getMCountry() {
		return MCountry;
	}

	/**
	 * 设置国籍
	 */
	public void setMCountry(String mCountry) {
		MCountry = mCountry;
	}

	/**
	 * 得到出生日期
	 */
	public String getMBDate() {
		return MBDate;
	}

	/**
	 * 设置出生日期
	 */
	public void setMBDate(String mBDate) {
		MBDate = mBDate;
	}

	/**
	 * 得到证件类型
	 */
	public String getMCType() {
		return MCType;
	}

	/**
	 * 设置证件类型
	 */
	public void setMCType(String mCType) {
		MCType = mCType;
	}

	/**
	 * 得到证件号码
	 */
	public String getMCNum() {
		return MCNum;
	}

	/**
	 * 设置证件号码
	 */
	public void setMCNum(String mCNum) {
		MCNum = mCNum;
	}

	/**
	 * 得到发证国家
	 */
	public String getMCC() {
		return MCC;
	}

	/**
	 * 设置发证国家
	 */
	public void setMCC(String mCC) {
		MCC = mCC;
	}

	/**
	 * 得到证件到期日
	 */
	public String getMCDate() {
		return MCDate;
	}

	/**
	 * 设置证件到期日
	 */
	public void setMCDate(String mCDate) {
		MCDate = mCDate;
	}

	/**
	 * 得到更新人名称
	 */
	public String getMUNum() {
		return MUNum;
	}

	/**
	 * 设置更新人名称
	 */
	public void setMUNum(String mUNum) {
		MUNum = mUNum;
	}

	/**
	 * 得到录入人名称
	 */
	public String getMINum() {
		return MINum;
	}

	/**
	 * 设置录入人名称
	 */
	public void setMINum(String mINum) {
		MINum = mINum;
	}

	/**
	 * 得到接收银行通知方式
	 */
	public String getMBRType() {
		return MBRType;
	}

	/**
	 * 设置接收银行通知方式
	 */
	public void setMBRType(String mBRType) {
		MBRType = mBRType;
	}

	/**
	 * 得到接收银行账单方式
	 */
	public String getMMRType() {
		return MMRType;
	}

	/**
	 * 设置接收银行账单方式
	 */
	public void setMMRType(String mMRType) {
		MMRType = mMRType;
	}

	/**
	 * 得到Email
	 */
	public String getMEmail() {
		return MEmail;
	}

	/**
	 * 设置Email
	 */
	public void setMEmail(String mEmail) {
		MEmail = mEmail;
	}



	/**
	 * 得到电话类型
	 */
	public String getMTType() {
		return MTType;
	}

	/**
	 * 设置电话类型
	 */
	public void setMTType(String mTType) {
		MTType = mTType;
	}

	/**
	 * 得到国际区号
	 */
	public String getMIACode() {
		return MIACode;
	}

	/**
	 * 设置国际区号
	 */
	public void setMIACode(String mIACode) {
		MIACode = mIACode;
	}

	/**
	 * 得到区号
	 */
	public String getMACode() {
		return MACode;
	}

	/**
	 * 设置区号
	 */
	public void setMACode(String mACode) {
		MACode = mACode;
	}

	/**
	 * 得到电话号码
	 */
	public String getMTNum() {
		return MTNum;
	}

	/**
	 * 设置电话号码
	 */
	public void setMTNum(String mTNum) {
		MTNum = mTNum;
	}

	/**
	 * 得到分机号
	 */
	public String getMTDNum() {
		return MTDNum;
	}

	/**
	 * 设置分机号
	 */
	public void setMTDNum(String mTDNum) {
		MTDNum = mTDNum;
	}

	/**
	 * 得到是否为短信接收号码
	 */
	public String getMRS() {
		return MRS;
	}

	/**
	 * 设置是否为短信接收号码
	 */
	public void setMRS(String mRS) {
		MRS = mRS;
	}

	/**
	 * 得到登记人
	 */
	public String getMRNum() {
		return MRNum;
	}

	/**
	 * 设置登记人
	 */
	public void setMRNum(String mRNum) {
		MRNum = mRNum;
	}

	/**
	 * 得到登记机构
	 */
	public String getMRBEAD() {
		return MRBEAD;
	}

	/**
	 * 设置登记机构
	 */
	public void setMRBEAD(String mRBEAD) {
		MRBEAD = mRBEAD;
	}

	/**
	 * 得到更新人
	 */
	public String getMRUNum() {
		return MRUNum;
	}

	/**
	 * 设置更新人
	 */
	public void setMRUNum(String mRUNum) {
		MRUNum = mRUNum;
	}

	/**
	 * 得到教育水平
	 */
	public String getMEE() {
		return MEE;
	}

	/**
	 * 设置教育水平
	 */
	public void setMEE(String mEE) {
		MEE = mEE;
	}

	/**
	 * 得到婚姻状况
	 */
	public String getMMType() {
		return MMType;
	}

	/**
	 * 设置婚姻状况
	 */
	public void setMMType(String mMType) {
		MMType = mMType;

	}

	/**
	 * 得到收入类型
	 */
	public String getMIType() {
		return MIType;
	}

	/**
	 * 设置收入类型
	 */
	public void setMIType(String mIType) {
		MIType = mIType;
	}

	/**
	 * 得到收入币种
	 */
	public String getMCurrency() {
		return MCurrency;
	}

	/**
	 * 设置收入币种
	 */
	public void setMCurrency(String mCurrency) {
		MCurrency = mCurrency;
	}

	/**
	 * 得到金额
	 */
	public String getMMoney() {
		return MMoney;
	}

	/**
	 * 设置金额
	 */
	public void setMMoney(String mMoney) {
		MMoney = mMoney;
	}

	/**
	 * 得到汇率
	 */
	public String getMRate() {
		return MRate;
	}

	/**
	 * 设置汇率
	 */
	public void setMRate(String mRate) {
		MRate = mRate;
	}

	/**
	 * 得到折算为人民币金额
	 */
	public String getMRMB() {
		return MRMB;
	}

	/**
	 * 设置折算为人民币金额
	 */
	public void setMRMB(String mRMB) {
		MRMB = mRMB;
	}

	/**
	 * 得到汇总家庭月总收入
	 */
	public String getMTMoney() {
		return MTMoney;
	}

	/**
	 * 设置汇总家庭月总收入
	 */
	public void setMTMoney(String mTMoney) {
		MTMoney = mTMoney;
	}

	/**
	 * 得到登记人
	 */
	public String getMRINum() {
		return MRINum;
	}

	/**
	 * 设置登记人
	 */
	public void setMRINum(String mIRNum) {
		MRINum = mIRNum;
	}

	/**
	 * 得到登记机构
	 */
	public String getMRIBEAD() {
		return MRIBEAD;
	}

	/**
	 * 设置登记机构
	 */
	public void setMRIBEAD(String mIRBEAD) {
		MRIBEAD = mIRBEAD;
	}

	/**
	 * 得到更新人
	 */
	public String getMRIUNum() {
		return MRIUNum;
	}

	/**
	 * 设置更新人
	 */
	public void setMRIUNum(String mIRUNum) {
		MRIUNum = mIRUNum;
	}

	public String getMCTypeName() {
		return MCTypeName;
	}

	public void setMCTypeName(String mCTypeName) {
		MCTypeName = mCTypeName;
	}

	public String getMUNumName() {
		return MUNumName;
	}

	public void setMUNumName(String mUNumName) {
		MUNumName = mUNumName;
	}

	public String getMINumName() {
		return MINumName;
	}

	public void setMINumName(String mINumName) {
		MINumName = mINumName;
	}

	/**
	 * 是否同意我行交叉营销
	 * 
	 * @return
	 */
	public String getMSale() {
		return MSale;
	}

	/**
	 * 是否同意我行交叉营销
	 * 
	 * @return
	 */
	public void setMSale(String mSale) {
		MSale = mSale;
	}

	/**
	 * 与我行业务来往情况
	 * 
	 * @return
	 */
	public String getMLRDT() {
		return MLRDT;
	}

	/**
	 * 与我行业务来往情况
	 */
	public void setMLRDT(String mLRDT) {
		MLRDT = mLRDT;
	}

	/**
	 * 与我行业务来往情况 name
	 */
	public String getMLRDTName() {
		return MLRDTName;
	}

	/**
	 * 与我行业务来往情况 name
	 */
	public void setMLRDTName(String mLRDTName) {
		MLRDTName = mLRDTName;
	}

	/**
	 * 客户源
	 */
	public String getMCS() {
		return MCS;
	}

	/**
	 * 客户源
	 */
	public void setMCS(String mCS) {
		MCS = mCS;
	}

	/**
	 * 客户源 name
	 */

	public String getMCSName() {
		return MCSName;
	}

	/**
	 * 客户源 name
	 */
	public void setMCSName(String mCSName) {
		MCSName = mCSName;
	}

	/**
	 * 电话类型name
	 */
	public String getMTTypeName() {
		return MTTypeName;
	}

	/**
	 * 电话类型name
	 */
	public void setMTTypeName(String mTTypeName) {
		MTTypeName = mTTypeName;
	}

	/**
	 * 收入类型name
	 */
	public String getMITypeName() {
		return MITypeName;
	}

	/**
	 * 收入类型name
	 */
	public void setMITypeName(String mITypeName) {
		MITypeName = mITypeName;
	}

	/**
	 * 收入币种name
	 */
	public String getMCurrencyName() {
		return MCurrencyName;
	}

	/**
	 * 收入币种name
	 */
	public void setMCurrencyName(String mCurrencyName) {
		MCurrencyName = mCurrencyName;
	}

	/**
	 * 登记人name
	 */
	public String getMRNumName() {
		return MRNumName;
	}

	/**
	 * 登记人name
	 */
	public void setMRNumName(String mRNumName) {
		MRNumName = mRNumName;
	}

	/**
	 * 登记机构name
	 */
	public String getMRBEADName() {
		return MRBEADName;
	}

	/**
	 * 登记机构name
	 */
	public void setMRBEADName(String mRBEADName) {
		MRBEADName = mRBEADName;
	}

	/**
	 * 更新人name
	 */
	public String getMRUNumName() {
		return MRUNumName;
	}

	/**
	 * 更新人name
	 */
	public void setMRUNumName(String mRUNumName) {
		MRUNumName = mRUNumName;
	}

	public String getIs() {
		return is;
	}

	public void setIs(String is) {
		this.is = is;
	}
	
	

}
