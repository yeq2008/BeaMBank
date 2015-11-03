/**
 * 
 */
package com.bea.remote.models.grwdy;

import java.io.Serializable;

import com.bea.application.BeaAppSettings;
import com.bea.remote.models.user.UserInfoBO;

import xc.android.remote.json.JsonModel;

/**
 * @author LSQ 联系人信息数据模型
 */
@JsonModel
public final class ContactInforBO implements Serializable {
	private static final long serialVersionUID = 4351419280534116521L;
	final UserInfoBO userInfo = BeaAppSettings.getUserInfo();
	/**
	 * 关系类型
	 */
	private String LRS = "301";
	/**
	 * 中文姓
	 */
	private String LFName;
	/**
	 * 中文名
	 */
	private String LLName;
	/**
	 * 英文姓
	 */
	private String LEFName;
	/**
	 * 英文名
	 */
	private String LELName;
	/**
	 * 证件类型
	 */
//	private String LCType = "Ind1010";
	/**
	 * 发证国家
	 */
//	private String LCC = "CHN";
	/**
	 * 证件号码
	 */
//	private String LCNum;
//	/**
//	 * 接收银行通知方式
//	 */
//	private String LBRType = "1";
//	/**
//	 * 接收银行账单方式
//	 */
//	private String LMRType = "2";
//
//	/**
//	 * 邮箱
//	 */
//	private String LEmail;
	/**
	 * 电话类型
	 */
	private String LTType = "1";
	/**
	 * 国际区号
	 */
	private String LIACode = "86";
	/**
	 * 区号
	 */
	private String LACode;
	/**
	 * 电话号码
	 */
	private String LTNum;
	/**
	 * 分机号
	 */
	private String LTDNum;
	/**
	 * 登记人工号
	 */
	private String LRNum = userInfo.getEmployee_mark();
	/**
	 * 登记机构
	 */
	private String LRBEAD = userInfo.getOrg_id();
	/**
	 * 更新人工号
	 */
	private String LRUNum = userInfo.getEmployee_mark();


	/**
	 * 关系类型name
	 */
	private String LRSName;
	/**
	 * 证件类型name
	 */
//	private String LCTypeName;
//	/**
//	 * 电话类型name
//	 */
//	private String LTTypeName;
	/**
	 * 登记人name
	 */
//	private String LRNumName  ;
	/**
	 * 登记机构name
	 */
//	private String LRBEADName  ;
	/**
	 * 更新人name
	 */
//	private String LRUNumName  ;




	/**
	 * 关系类型name
	 */
	public String getLRSName() {
		return LRSName;
	}
	/**
	 * 关系类型name
	 */ 
	public void setLRSName(String lRSName) {
		LRSName = lRSName;
	}

//	/**
//	 * 证件类型name
//	 */
//	public String getLCTypeName() {
//		return LCTypeName;
//	} 
//	/**
//	 * 证件类型name
//	 */
//	public void setLCTypeName(String lCTypeName) {
//		LCTypeName = lCTypeName;
//	}

//	/**
//	 * 电话类型name
//	 */
//	public String getLTTypeName() {
//		return LTTypeName;
//	}
//	/**
//	 * 电话类型name
//	 */
//	public void setLTTypeName(String lTTypeName) {
//		LTTypeName = lTTypeName;
//	}
//	/**
//	 * 登记人name
//	 */
//	public String getLRNumName() {
//		return LRNumName;
//	}
//	/**
//	 * 登记人name
//	 */
//	public void setLRNumName(String lRNumName) {
//		LRNumName = lRNumName;
//	}
//	/**
//	 * 登记机构name
//	 */
//	public String getLRBEADName() {
//		return LRBEADName;
//	}
//	/**
//	 * 登记机构name
//	 */
//	public void setLRBEADName(String lRBEADName) {
//		LRBEADName = lRBEADName;
//	}
//	/**
//	 * 更新人name
//	 */
//	public String getLRUNumName() {
//		return LRUNumName;
//	}
//	/**
//	 * 更新人name
//	 */
//	public void setLRUNumName(String lRUNumName) {
//		LRUNumName = lRUNumName;
//	}

//	/**
//	 * 接收银行通知方式 
//	 */
//	public String getLBRType() {
//		return LBRType;
//	}
//
//	/**
//	 * 接收银行通知方式 
//	 */
//	public void setLBRType(String lBRType) {
//		LBRType = lBRType;
//	}
//
//	/**
//	 * 接收银行账单方式 
//	 */
//	public String getLMRType() {
//		return LMRType;
//	}
//
//	/**
//	 * 接收银行账单方式
//	 */
//	public void setLMRType(String lMRType) {
//		LMRType = lMRType;
//	}
//
//	/**
//	 * 电子邮箱 
//	 */
//	public String getLEmail() {
//		return LEmail;
//	}
//
//	/**
//	 * 电子邮箱 
//	 */
//	public void setLEmail(String lEmail) {
//		LEmail = lEmail;
//	}

	/**
	 * 关系类型 
	 */
	public String getLRS() {
		return LRS;
	}

	/**
	 * 关系类型 
	 */
	public void setLRS(String lRS) {
		LRS = lRS;
	}

	/**
	 * 得到姓（中文）
	 */
	public String getLFName() {
		return LFName;
	}

	/**
	 * 设置姓（中文）
	 */
	public void setLFName(String lFName) {
		LFName = lFName;
	}

	/**
	 * 得到名（中文）
	 */
	public String getLLName() {
		return LLName;
	}

	/**
	 * 设置名（中文）
	 */
	public void setLLName(String lLName) {
		LLName = lLName;
	}

	/**
	 * 得到姓（英文/拼音）
	 */
	public String getLEFName() {
		return LEFName;
	}

	/**
	 * 设置姓（英文/拼音）
	 */
	public void setLEFName(String lEFName) {
		LEFName = lEFName;
	}

	/**
	 * 得到名（英文/拼音）
	 */
	public String getLELName() {
		return LELName;
	}

	/**
	 * 设置名（英文/拼音）
	 */
	public void setLELName(String lELName) {
		LELName = lELName;
	}

//	/**
//	 * 得到证件类型
//	 */
//	public String getLCType() {
//		return LCType;
//	}
//
//	/**
//	 * 设置证件类型
//	 */
//	public void setLCType(String lCType) {
//		LCType = lCType;
//	}
//
//	/**
//	 * 得到发证国家
//	 */
//	public String getLCC() {
//		return LCC;
//	}
//
//	/**
//	 * 设置发证国家
//	 */
//	public void setLCC(String lCC) {
//		LCC = lCC;
//	}
//
//	/**
//	 * 得到证件号码
//	 */
//	public String getLCNum() {
//		return LCNum;
//	}
//
//	/**
//	 * 设置证件号码
//	 */
//	public void setLCNum(String lCNum) {
//		LCNum = lCNum;
//	}

	/**
	 * 得到电话类型
	 */
	public String getLTType() {
		return LTType;
	}

	/**
	 * 设置电话类型
	 */
	public void setLTType(String lTType) {
		LTType = lTType;
	}

	/**
	 * 得到国际区号
	 */
	public String getLIACode() {
		return LIACode;
	}

	/**
	 * 设置国际区号
	 */
	public void setLIACode(String lIACode) {
		LIACode = lIACode;
	}

	/**
	 * 得到区号
	 */
	public String getLACode() {
		return LACode;
	}

	/**
	 * 设置区号
	 */
	public void setLACode(String lACode) {
		LACode = lACode;
	}

	/**
	 * 得到电话号码
	 */
	public String getLTNum() {
		return LTNum;
	}

	/**
	 * 设置电话号码
	 */
	public void setLTNum(String lTNum) {
		LTNum = lTNum;
	}

	/**
	 * 得到分机号
	 */
	public String getLTDNum() {
		return LTDNum;
	}

	/**
	 * 设置分机号
	 */
	public void setLTDNum(String lTDNum) {
		LTDNum = lTDNum;
	}

	/**
	 * 得到登记人
	 */
	public String getLRNum() {
		return LRNum;
	}

	/**
	 * 设置登记人
	 */
	public void setLRNum(String lRNum) {
		LRNum = lRNum;
	}

	/**
	 * 得到登记机构
	 */
	public String getLRBEAD() {
		return LRBEAD;
	}

	/**
	 * 设置登记机构
	 */
	public void setLRBEAD(String lRBEAD) {
		LRBEAD = lRBEAD;
	}

	/**
	 * 得到更新人
	 */
	public String getLRUNum() {
		return LRUNum;
	}

	/**
	 * 设置更新人
	 */
	public void setLRUNum(String lRUNum) {
		LRUNum = lRUNum;
	}

}