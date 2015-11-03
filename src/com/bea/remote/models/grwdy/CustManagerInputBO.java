package com.bea.remote.models.grwdy;

import java.io.Serializable;

/**
 * @author fanglinhao
 * 客户经理填写
 */
public class CustManagerInputBO implements Serializable {
	private String OInterest = "2";    //是否享受员工贴息
//	private String Ocomment;    //用途备注
	private String OLUA;    //资金使用地
	private String OLP;//是否联动存量房交易
	private String OCountry;    //贷款使用地所在国家
	private String OCountryName;//贷款使用地所在国家
	private String OACode;    //贷款使用地所在省份
	private String OACodeName;
	private String OCRType;    //客户还款能力判断标准
	private String OMIC = "010";    //业务推荐类型
	private String OMICName;
	private String OBEAD;    //推荐部门名称
	private String OCType = "Ind1010";    //推荐客户-证件类型
	private String OCNum;    //推荐客户-证件号码
	private String OCName;    //推荐客户-名称
	private String OCTel;    //推荐客户-联系手机
	private String OEC;    //业务进件类型
	private String OECName;
	private String ORSNum;    //推荐员工工号
	private String ORSName;    //推荐员工姓名
	private String OSNum;    //员工工号
	private String OSName;    //员工姓名
	private String OSType;    //员工所在条线
	public String getOInterest() {
		return OInterest;
	}
	public void setOInterest(String oInterest) {
		OInterest = oInterest;
	}
//	public String getOcomment() {
//		return Ocomment;
//	}
//	public void setOcomment(String ocomment) {
//		Ocomment = ocomment;
//	}
	public String getOLUA() {
		return OLUA;
	}
	public void setOLUA(String oLUA) {
		OLUA = oLUA;
	}
	public String getOLP() {
		return OLP;
	}
	public void setOLP(String oLP) {
		OLP = oLP;
	}
	public String getOCountry() {
		return OCountry;
	}
	public void setOCountry(String oCountry) {
		OCountry = oCountry;
	}
	public String getOCountryName() {
		return OCountryName;
	}
	public void setOCountryName(String oCountryName) {
		OCountryName = oCountryName;
	}
	public String getOACode() {
		return OACode;
	}
	public void setOACode(String oACode) {
		OACode = oACode;
	}
	public String getOACodeName() {
		return OACodeName;
	}
	public void setOACodeName(String oACodeName) {
		OACodeName = oACodeName;
	}
	public String getOCRType() {
		return OCRType;
	}
	public void setOCRType(String oCRType) {
		OCRType = oCRType;
	}
	public String getOMIC() {
		return OMIC;
	}
	public void setOMIC(String oMIC) {
		OMIC = oMIC;
	}
	public String getOMICName() {
		return OMICName;
	}
	public void setOMICName(String oMICName) {
		OMICName = oMICName;
	}
	public String getOBEAD() {
		return OBEAD;
	}
	public void setOBEAD(String oBEAD) {
		OBEAD = oBEAD;
	}
	public String getOCType() {
		return OCType;
	}
	public void setOCType(String oCType) {
		OCType = oCType;
	}
	public String getOCNum() {
		return OCNum;
	}
	public void setOCNum(String oCNum) {
		OCNum = oCNum;
	}
	public String getOCName() {
		return OCName;
	}
	public void setOCName(String oCName) {
		OCName = oCName;
	}
	public String getOCTel() {
		return OCTel;
	}
	public void setOCTel(String oCTel) {
		OCTel = oCTel;
	}
	public String getOEC() {
		return OEC;
	}
	public void setOEC(String oEC) {
		OEC = oEC;
	}
	public String getOECName() {
		return OECName;
	}
	public void setOECName(String oECName) {
		OECName = oECName;
	}
	public String getORSNum() {
		return ORSNum;
	}
	public void setORSNum(String oRSNum) {
		ORSNum = oRSNum;
	}
	public String getORSName() {
		return ORSName;
	}
	public void setORSName(String oRSName) {
		ORSName = oRSName;
	}
	public String getOSNum() {
		return OSNum;
	}
	public void setOSNum(String oSNum) {
		OSNum = oSNum;
	}
	public String getOSName() {
		return OSName;
	}
	public void setOSName(String oSName) {
		OSName = oSName;
	}
	public String getOSType() {
		return OSType;
	}
	public void setOSType(String oSType) {
		OSType = oSType;
	}
}
