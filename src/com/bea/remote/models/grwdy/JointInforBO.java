package com.bea.remote.models.grwdy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bea.application.BeaAppSettings;
import com.bea.database.DbManager;
import com.bea.remote.models.user.UserInfoBO;

import xc.android.remote.json.JsonModel;

/**
 * @author xuruian 共同借款人个人资料数据模型
 */
@JsonModel
public final class JointInforBO implements Serializable {
	final UserInfoBO userInfo = BeaAppSettings.getUserInfo();
	private String CFName;//姓（中文）
	private String CLName;//名（中文）
	private String CEFName;//姓（英文/拼音）
	private String CELName;//名（英文/拼音）
	private String CRelationship;//与主借款人关联关系
	private String CSex = "1";//性别
	private String CCountry = "CHN";//国籍
	private String CBDate;//出生日期
	private String CCType = "Ind1010";//证件类型
	private String CCNum;//证件号码
	private String CCC = "CHN";//发证国家
	private String CCDate;//证件到期日
	private String CUNum;//更新人名称
	private String CINum;//录入人名称
	private String CEE;//教育水平
	private String CMType;//婚姻状况
	private String CSale = "1";//是否同意我行交叉营销
	private String CLRDT;//与我行业务来往情况
	private String CLRDTName;//与我行业务来往情况
	private String CCS;//客户源
	private String CCSName;//客户源
	private String CBRType = "01";//接收银行通知方式
	private String CMRType = "02";//接收银行账单方式
	private String CEmail;//电子邮箱
	/**
	 * 住宅地址
	 */
	public jointAddressBO homeAddr = new jointAddressBO("1");
	
	/**
	 * 户籍地址
	 */
	public jointAddressBO censusAddr = new jointAddressBO("5");
	
	private List<jointAddressBO> address;
	
	private String CMTType = "1";//电话类型
	private String CMTTypeName;//电话类型
	private String CIACode = "86";//国际区号
	private String CACode;//区号
	private String CTNum;//电话号码
	private String CTDNum;//分机号
	private String CRS = "1";//是否为短信接收号码
	private String CRNum;//登记人
	private String CRNumName;//登记人
	private String CRBEAD;//登记机构
	private String CRBEADName = DbManager.getOrgNameById(userInfo.getOrg_id());//登记机构
	private String CRUNum;//更新人
	private String CRUNumName;//更新人
	private String CIType;//收入类型
	private String CITypeName;//收入类型
	private String CCurrency = "01";//收入币种
	private String CCurrencyName;//收入币种
	private String CMoney;//金额
	private String CRate;//汇率
	private String CRMB;//折算为人民币金额
	private String CTMoney;//汇总家庭月总收入
	private String CIRNum;//登记人
	private String CIRNumName;//登记人
	private String CIRBEAD;//登记机构
	private String CIRBEADName =  DbManager.getOrgNameById(userInfo.getOrg_id());//登记机构
	private String CIRUNum;//更新人
	private String CIRUNumName;//更新人
	
	private String CET = "1";//雇佣类型
	private String COT;//经营实体类型
	private String CRCurrency = "01";//注册币种
	private String CRCurrencyName;//注册币种
	private String CCapital;//经营实体类型注册资金
	private String CCName;//单位名称
	private String CCTpye;//单位性质
	private String CCTpyeName;//单位性质
	private String CCCountry = "CHN";//单位所在国家
	private String CCCountryName;//单位所在国家
	private String CCity = "310000";//单位所在城市
	private String CCityName;//单位所在城市
	private String CCAddr;//单位地址
	private String CIdType;//行业分类（中国）
	private String CIdTypeName;//行业分类（中国）
	private String CITypeHK;//行业分类（香港）
	private String CITypeHKName;//行业分类（香港）
	private String COBEA;//职业
	private String CPosition;//职位
	private String CIDate;//入职日期
	private String CYear;//工作年限
	private String CBEA = "2";//是否本行员工
	private String CBEAID;//员工工号
	private String CBEAD;//员工所在部门
	public String getCFName() {
		return CFName;
	}
	public void setCFName(String cFName) {
		CFName = cFName;
	}
	public String getCLName() {
		return CLName;
	}
	public void setCLName(String cLName) {
		CLName = cLName;
	}
	public String getCEFName() {
		return CEFName;
	}
	public void setCEFName(String cEFName) {
		CEFName = cEFName;
	}
	public String getCELName() {
		return CELName;
	}
	public void setCELName(String cELName) {
		CELName = cELName;
	}
	public String getCRelationship() {
		return CRelationship;
	}
	public void setCRelationship(String cRelationship) {
		CRelationship = cRelationship;
	}
	public String getCSex() {
		return CSex;
	}
	public void setCSex(String cSex) {
		CSex = cSex;
	}
	public String getCCountry() {
		return CCountry;
	}
	public void setCCountry(String cCountry) {
		CCountry = cCountry;
	}
	public String getCBDate() {
		return CBDate;
	}
	public void setCBDate(String cBDate) {
		CBDate = cBDate;
	}
	public String getCCType() {
		return CCType;
	}
	public void setCCType(String cCType) {
		CCType = cCType;
	}
	public String getCCNum() {
		return CCNum;
	}
	public void setCCNum(String cCNum) {
		CCNum = cCNum;
	}
	public String getCCC() {
		return CCC;
	}
	public void setCCC(String cCC) {
		CCC = cCC;
	}
	public String getCCDate() {
		return CCDate;
	}
	public void setCCDate(String cCDate) {
		CCDate = cCDate;
	}
	public String getCUNum() {
		return CUNum;
	}
	public void setCUNum(String cUNum) {
		CUNum = cUNum;
	}
	public String getCINum() {
		return CINum;
	}
	public void setCINum(String cINum) {
		CINum = cINum;
	}
	public String getCEE() {
		return CEE;
	}
	public void setCEE(String cEE) {
		CEE = cEE;
	}
	public String getCMType() {
		return CMType;
	}
	public void setCMType(String cMType) {
		CMType = cMType;
	}
	public String getCSale() {
		return CSale;
	}
	public void setCSale(String cSale) {
		CSale = cSale;
	}
	public String getCLRDT() {
		return CLRDT;
	}
	public void setCLRDT(String cLRDT) {
		CLRDT = cLRDT;
	}
	public String getCLRDTName() {
		return CLRDTName;
	}
	public void setCLRDTName(String cLRDTName) {
		CLRDTName = cLRDTName;
	}
	public String getCCS() {
		return CCS;
	}
	public void setCCS(String cCS) {
		CCS = cCS;
	}
	public String getCCSName() {
		return CCSName;
	}
	public void setCCSName(String cCSName) {
		CCSName = cCSName;
	}
	public String getCBRType() {
		return CBRType;
	}
	public void setCBRType(String cBRType) {
		CBRType = cBRType;
	}
	public String getCMRType() {
		return CMRType;
	}
	public void setCMRType(String cMRType) {
		CMRType = cMRType;
	}
	public String getCEmail() {
		return CEmail;
	}
	public void setCEmail(String cEmail) {
		CEmail = cEmail;
	}

	public String getCMTType() {
		return CMTType;
	}
	public void setCMTType(String cMTType) {
		CMTType = cMTType;
	}
	public String getCMTTypeName() {
		return CMTTypeName;
	}
	public void setCMTTypeName(String cMTTypeName) {
		CMTTypeName = cMTTypeName;
	}
	public String getCIACode() {
		return CIACode;
	}
	public void setCIACode(String cIACode) {
		CIACode = cIACode;
	}
	public String getCACode() {
		return CACode;
	}
	public void setCACode(String cACode) {
		CACode = cACode;
	}
	public String getCTNum() {
		return CTNum;
	}
	public void setCTNum(String cTNum) {
		CTNum = cTNum;
	}
	public String getCTDNum() {
		return CTDNum;
	}
	public void setCTDNum(String cTDNum) {
		CTDNum = cTDNum;
	}
	public String getCRS() {
		return CRS;
	}
	public void setCRS(String cRS) {
		CRS = cRS;
	}
	public String getCRNum() {
		return CRNum;
	}
	public void setCRNum(String cRNum) {
		CRNum = cRNum;
	}
	public String getCRNumName() {
		return CRNumName;
	}
	public void setCRNumName(String cRNumName) {
		CRNumName = cRNumName;
	}
	public String getCRBEAD() {
		return CRBEAD;
	}
	public void setCRBEAD(String cRBEAD) {
		CRBEAD = cRBEAD;
	}
	public String getCRBEADName() {
		return CRBEADName;
	}
	public void setCRBEADName(String cRBEADName) {
		CRBEADName = cRBEADName;
	}
	public String getCRUNum() {
		return CRUNum;
	}
	public void setCRUNum(String cRUNum) {
		CRUNum = cRUNum;
	}
	public String getCRUNumName() {
		return CRUNumName;
	}
	public void setCRUNumName(String cRUNumName) {
		CRUNumName = cRUNumName;
	}
	public String getCIType() {
		return CIType;
	}
	public void setCIType(String cIType) {
		CIType = cIType;
	}
	public String getCITypeName() {
		return CITypeName;
	}
	public void setCITypeName(String cITypeName) {
		CITypeName = cITypeName;
	}
	public String getCCurrency() {
		return CCurrency;
	}
	public void setCCurrency(String cCurrency) {
		CCurrency = cCurrency;
	}
	public String getCCurrencyName() {
		return CCurrencyName;
	}
	public void setCCurrencyName(String cCurrencyName) {
		CCurrencyName = cCurrencyName;
	}
	public String getCMoney() {
		return CMoney;
	}
	public void setCMoney(String cMoney) {
		CMoney = cMoney;
	}
	public String getCRate() {
		return CRate;
	}
	public void setCRate(String cRate) {
		CRate = cRate;
	}
	public String getCRMB() {
		return CRMB;
	}
	public void setCRMB(String cRMB) {
		CRMB = cRMB;
	}
	public String getCTMoney() {
		return CTMoney;
	}
	public void setCTMoney(String cTMoney) {
		CTMoney = cTMoney;
	}
	public String getCIRNum() {
		return CIRNum;
	}
	public void setCIRNum(String cIRNum) {
		CIRNum = cIRNum;
	}
	public String getCIRNumName() {
		return CIRNumName;
	}
	public void setCIRNumName(String cIRNumName) {
		CIRNumName = cIRNumName;
	}
	public String getCIRBEAD() {
		return CIRBEAD;
	}
	public void setCIRBEAD(String cIRBEAD) {
		CIRBEAD = cIRBEAD;
	}
	public String getCIRBEADName() {
		return CIRBEADName;
	}
	public void setCIRBEADName(String cIRBEADName) {
		CIRBEADName = cIRBEADName;
	}
	public String getCIRUNum() {
		return CIRUNum;
	}
	public void setCIRUNum(String cIRUNum) {
		CIRUNum = cIRUNum;
	}
	public String getCIRUNumName() {
		return CIRUNumName;
	}
	public void setCIRUNumName(String cIRUNumName) {
		CIRUNumName = cIRUNumName;
	}
	public String getCET() {
		return CET;
	}
	public void setCET(String cET) {
		CET = cET;
	}
	public String getCOT() {
		return COT;
	}
	public void setCOT(String cOT) {
		COT = cOT;
	}
	public String getCRCurrency() {
		return CRCurrency;
	}
	public void setCRCurrency(String cRCurrency) {
		CRCurrency = cRCurrency;
	}
	public String getCRCurrencyName() {
		return CRCurrencyName;
	}
	public void setCRCurrencyName(String cRCurrencyName) {
		CRCurrencyName = cRCurrencyName;
	}
	public String getCCapital() {
		return CCapital;
	}
	public void setCCapital(String cCapital) {
		CCapital = cCapital;
	}
	public String getCCName() {
		return CCName;
	}
	public void setCCName(String cCName) {
		CCName = cCName;
	}
	public String getCCTpye() {
		return CCTpye;
	}
	public void setCCTpye(String cCTpye) {
		CCTpye = cCTpye;
	}
	public String getCCTpyeName() {
		return CCTpyeName;
	}
	public void setCCTpyeName(String cCTpyeName) {
		CCTpyeName = cCTpyeName;
	}
	public String getCCCountry() {
		return CCCountry;
	}
	public void setCCCountry(String cCCountry) {
		CCCountry = cCCountry;
	}
	public String getCCCountryName() {
		return CCCountryName;
	}
	public void setCCCountryName(String cCCountryName) {
		CCCountryName = cCCountryName;
	}
	public String getCCity() {
		return CCity;
	}
	public void setCCity(String cCity) {
		CCity = cCity;
	}
	public String getCCityName() {
		return CCityName;
	}
	public void setCCityName(String cCityName) {
		CCityName = cCityName;
	}
	public String getCCAddr() {
		return CCAddr;
	}
	public void setCCAddr(String cCAddr) {
		CCAddr = cCAddr;
	}
	public String getCIdType() {
		return CIdType;
	}
	public void setCIdType(String cIdType) {
		CIdType = cIdType;
	}
	public String getCIdTypeName() {
		return CIdTypeName;
	}
	public void setCIdTypeName(String cIdTypeName) {
		CIdTypeName = cIdTypeName;
	}
	public String getCITypeHK() {
		return CITypeHK;
	}
	public void setCITypeHK(String cITypeHK) {
		CITypeHK = cITypeHK;
	}
	public String getCITypeHKName() {
		return CITypeHKName;
	}
	public void setCITypeHKName(String cITypeHKName) {
		CITypeHKName = cITypeHKName;
	}
	public String getCOBEA() {
		return COBEA;
	}
	public void setCOBEA(String cOBEA) {
		COBEA = cOBEA;
	}
	public String getCPosition() {
		return CPosition;
	}
	public void setCPosition(String cPosition) {
		CPosition = cPosition;
	}
	public String getCIDate() {
		return CIDate;
	}
	public void setCIDate(String cIDate) {
		CIDate = cIDate;
	}
	public String getCYear() {
		return CYear;
	}
	public void setCYear(String cYear) {
		CYear = cYear;
	}
	public String getCBEA() {
		return CBEA;
	}
	public void setCBEA(String cBEA) {
		CBEA = cBEA;
	}
	public String getCBEAID() {
		return CBEAID;
	}
	public void setCBEAID(String cBEAID) {
		CBEAID = cBEAID;
	}
	public String getCBEAD() {
		return CBEAD;
	}
	public void setCBEAD(String cBEAD) {
		CBEAD = cBEAD;
	}

	public List<jointAddressBO> getAddress() {
		List<jointAddressBO> address = new ArrayList<jointAddressBO>();
		address.add(homeAddr);
		address.add(censusAddr);
		return address;
	}

	public void setAddress(List<jointAddressBO> address) {
		this.address = address;
		if (null != address) {
			if (address.size() == 1) {
				homeAddr = address.get(0);
			} else if (address.size() == 2) {
				homeAddr = address.get(0);
				censusAddr = address.get(1);
			}
		}
	}
	
	
	
}
