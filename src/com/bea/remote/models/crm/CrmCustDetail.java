package com.bea.remote.models.crm;

/**
 * crm客户汇总信息
 * @author fanglinhao
 */
public class CrmCustDetail {
	private String IDEN_NUM_TYPE;//证件类型
	private String GOVE_IDEN_NUM;//证件号码
	private String CH_NAME;//客户名称
	private String EN_NAME;//英文名称
	private String EXT_CLIENT_NO;//客户号
	private String GENDER;//性别
	private String BIRTHDATE;//出生日期
	private String RACE;//民族
	private String OCCUPATION;//职业
	private String EDUCATION_CODE;//教育水平
	private String MARITAL_STATUS;//婚姻状态
	private String ANN_INC;//年收入
	private String NATIONALITY;//国籍全称
	private String EMPLOYMENT_TYPE;//雇佣类型
	private String POSITION;//职称
	private String UNITPROPERTY;//单位性质
	private String POPULATION;//家庭人口数
	private String YEAROFSERVICE;//工作年限
	private String FREE_TRADE_AREA;//自贸区类型
	private String UNITCITY;//单位所在城市
	private String INDUSTRY_CODE_HK;//行业类型_香港标准
	private String INDUSTRY_CODE;//行业类型_大陆标准
	private String ADDR_STREET1;//客户地址
	private String PHONE_NUMBER;//手机号码
	private String TEL_NUMBER;//座机号码
	private String AML_TYPE;//反洗钱风险等级
	private String RISK_CTRL_FLAG;//是否风险控制
	private String LOAN_DEGREE;//借贷五级分类
	private String IS_BANK_STAFF;//是否员工
	private String CUST_TYPE;//客户标识
	
	private String DEPOSIT_TERM_AMT;//存款余额
	private String FINANCE_BALANCE_RMB;//理财总市值
	private String INSSRANCE_AMT;//保单数量(保险产品保费额)
	private String LOAN_TERM_AMT;//贷款本金
	private String CREDIT_BAL;//信用卡贷款余额
	private String GOLD_LOAN_BAL;//金贷通贷款余额
	private String AUM_BAL_RMB;//核心账户账面余额
	
	private String IF_PAY_WAGE;//是否代发工资
	private String IF_LOAN;//是否个人贷款客户
	private String IF_BANK_ASSU;//是否银保客户
	private String IF_WEIXIN;//是否微信客户
	private String IF_COMMON;//是否普通客户
	private String IF_DIAMOND;//是否钻石显卓客户
	private String IF_USA_RESID;//是否美籍客户
	private String IF_HEAD_BANK;//是否总行推荐客户
	private String IF_TWOPLACE;//是否两地通客户
	private String IF_FINANCE;//是否理财客户
	private String IF_CREDIT;//是否信用卡客户
	private String IF_ELEC;//是否个人网银客户
	private String IF_PHONE;//是否电话银行客户
	private String IF_TELEPHONE;//是否手机银行客户
	private String IF_HIGH_FINANCE;//是否显卓理财客户
	private String IF_VIP;//是否私人银行客户
	private String IF_MIDDLE_FINANCE;//是否优慧理财客户
	
	private String QUART_CONTR_AMT;//当前贡献度 
	private String AUM_AVG;//当期AUM月日均
	private String AUM_EVN_GRADE;//AUM客户评级 
	private String QUART_CONTR_GRADE;//贡献度评级 
	private String CUST_MGR_NM;//主办客户经理 
	
	public CrmCustDetail() {}
	public String getIDEN_NUM_TYPE() {
		return IDEN_NUM_TYPE;
	}
	public void setIDEN_NUM_TYPE(String iDEN_NUM_TYPE) {
		IDEN_NUM_TYPE = iDEN_NUM_TYPE;
	}
	public String getGOVE_IDEN_NUM() {
		return GOVE_IDEN_NUM;
	}
	public void setGOVE_IDEN_NUM(String gOVE_IDEN_NUM) {
		GOVE_IDEN_NUM = gOVE_IDEN_NUM;
	}
	public String getCH_NAME() {
		return CH_NAME;
	}
	public void setCH_NAME(String cH_NAME) {
		CH_NAME = cH_NAME;
	}
	public String getEN_NAME() {
		return EN_NAME;
	}
	public void setEN_NAME(String eN_NAME) {
		EN_NAME = eN_NAME;
	}
	public String getEXT_CLIENT_NO() {
		return EXT_CLIENT_NO;
	}
	public void setEXT_CLIENT_NO(String eXT_CLIENT_NO) {
		EXT_CLIENT_NO = eXT_CLIENT_NO;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public String getBIRTHDATE() {
		return BIRTHDATE;
	}
	public void setBIRTHDATE(String bIRTHDATE) {
		BIRTHDATE = bIRTHDATE;
	}
	public String getRACE() {
		return RACE;
	}
	public void setRACE(String rACE) {
		RACE = rACE;
	}
	public String getOCCUPATION() {
		return OCCUPATION;
	}
	public void setOCCUPATION(String oCCUPATION) {
		OCCUPATION = oCCUPATION;
	}
	public String getEDUCATION_CODE() {
		return EDUCATION_CODE;
	}
	public void setEDUCATION_CODE(String eDUCATION_CODE) {
		EDUCATION_CODE = eDUCATION_CODE;
	}
	public String getMARITAL_STATUS() {
		return MARITAL_STATUS;
	}
	public void setMARITAL_STATUS(String mARITAL_STATUS) {
		MARITAL_STATUS = mARITAL_STATUS;
	}
	public String getANN_INC() {
		return ANN_INC;
	}
	public void setANN_INC(String aNN_INC) {
		ANN_INC = aNN_INC;
	}
	public String getNATIONALITY() {
		return NATIONALITY;
	}
	public void setNATIONALITY(String nATIONALITY) {
		NATIONALITY = nATIONALITY;
	}
	public String getEMPLOYMENT_TYPE() {
		return EMPLOYMENT_TYPE;
	}
	public void setEMPLOYMENT_TYPE(String eMPLOYMENT_TYPE) {
		EMPLOYMENT_TYPE = eMPLOYMENT_TYPE;
	}
	public String getPOSITION() {
		return POSITION;
	}
	public void setPOSITION(String pOSITION) {
		POSITION = pOSITION;
	}
	public String getUNITPROPERTY() {
		return UNITPROPERTY;
	}
	public void setUNITPROPERTY(String uNITPROPERTY) {
		UNITPROPERTY = uNITPROPERTY;
	}
	public String getPOPULATION() {
		return POPULATION;
	}
	public void setPOPULATION(String pOPULATION) {
		POPULATION = pOPULATION;
	}
	public String getYEAROFSERVICE() {
		return YEAROFSERVICE;
	}
	public void setYEAROFSERVICE(String yEAROFSERVICE) {
		YEAROFSERVICE = yEAROFSERVICE;
	}
	public String getFREE_TRADE_AREA() {
		return FREE_TRADE_AREA;
	}
	public void setFREE_TRADE_AREA(String fREE_TRADE_AREA) {
		FREE_TRADE_AREA = fREE_TRADE_AREA;
	}
	public String getUNITCITY() {
		return UNITCITY;
	}
	public void setUNITCITY(String uNITCITY) {
		UNITCITY = uNITCITY;
	}
	public String getINDUSTRY_CODE_HK() {
		return INDUSTRY_CODE_HK;
	}
	public void setINDUSTRY_CODE_HK(String iNDUSTRY_CODE_HK) {
		INDUSTRY_CODE_HK = iNDUSTRY_CODE_HK;
	}
	public String getINDUSTRY_CODE() {
		return INDUSTRY_CODE;
	}
	public void setINDUSTRY_CODE(String iNDUSTRY_CODE) {
		INDUSTRY_CODE = iNDUSTRY_CODE;
	}
	public String getADDR_STREET1() {
		return ADDR_STREET1;
	}
	public void setADDR_STREET1(String aDDR_STREET1) {
		ADDR_STREET1 = aDDR_STREET1;
	}
	public String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}
	public void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}
	public String getAML_TYPE() {
		return AML_TYPE;
	}
	public void setAML_TYPE(String aML_TYPE) {
		AML_TYPE = aML_TYPE;
	}
	public String getRISK_CTRL_FLAG() {
		return RISK_CTRL_FLAG;
	}
	public void setRISK_CTRL_FLAG(String rISK_CTRL_FLAG) {
		RISK_CTRL_FLAG = rISK_CTRL_FLAG;
	}
	public String getLOAN_DEGREE() {
		return LOAN_DEGREE;
	}
	public void setLOAN_DEGREE(String lOAN_DEGREE) {
		LOAN_DEGREE = lOAN_DEGREE;
	}
	public String getIS_BANK_STAFF() {
		return IS_BANK_STAFF;
	}
	public void setIS_BANK_STAFF(String iS_BANK_STAFF) {
		IS_BANK_STAFF = iS_BANK_STAFF;
	}
	public String getDEPOSIT_TERM_AMT() {
		return DEPOSIT_TERM_AMT;
	}
	public void setDEPOSIT_TERM_AMT(String dEPOSIT_TERM_AMT) {
		DEPOSIT_TERM_AMT = dEPOSIT_TERM_AMT;
	}
	public String getFINANCE_BALANCE_RMB() {
		return FINANCE_BALANCE_RMB;
	}
	public void setFINANCE_BALANCE_RMB(String fINANCE_BALANCE_RMB) {
		FINANCE_BALANCE_RMB = fINANCE_BALANCE_RMB;
	}
	public String getINSSRANCE_AMT() {
		return INSSRANCE_AMT;
	}
	public void setINSSRANCE_AMT(String iNSSRANCE_AMT) {
		INSSRANCE_AMT = iNSSRANCE_AMT;
	}
	public String getLOAN_TERM_AMT() {
		return LOAN_TERM_AMT;
	}
	public void setLOAN_TERM_AMT(String lOAN_TERM_AMT) {
		LOAN_TERM_AMT = lOAN_TERM_AMT;
	}
	public String getCREDIT_BAL() {
		return CREDIT_BAL;
	}
	public void setCREDIT_BAL(String cREDIT_BAL) {
		CREDIT_BAL = cREDIT_BAL;
	}
	public String getGOLD_LOAN_BAL() {
		return GOLD_LOAN_BAL;
	}
	public void setGOLD_LOAN_BAL(String gOLD_LOAN_BAL) {
		GOLD_LOAN_BAL = gOLD_LOAN_BAL;
	}
	public String getCUST_TYPE() {
		return CUST_TYPE;
	}
	public void setCUST_TYPE(String cUST_TYPE) {
		CUST_TYPE = cUST_TYPE;
	}
	public String getIF_PAY_WAGE() {
		return IF_PAY_WAGE;
	}
	public void setIF_PAY_WAGE(String iF_PAY_WAGE) {
		IF_PAY_WAGE = iF_PAY_WAGE;
	}
	public String getIF_LOAN() {
		return IF_LOAN;
	}
	public void setIF_LOAN(String iF_LOAN) {
		IF_LOAN = iF_LOAN;
	}
	public String getIF_BANK_ASSU() {
		return IF_BANK_ASSU;
	}
	public void setIF_BANK_ASSU(String iF_BANK_ASSU) {
		IF_BANK_ASSU = iF_BANK_ASSU;
	}
	public String getIF_WEIXIN() {
		return IF_WEIXIN;
	}
	public void setIF_WEIXIN(String iF_WEIXIN) {
		IF_WEIXIN = iF_WEIXIN;
	}
	public String getIF_COMMON() {
		return IF_COMMON;
	}
	public void setIF_COMMON(String iF_COMMON) {
		IF_COMMON = iF_COMMON;
	}
	public String getIF_DIAMOND() {
		return IF_DIAMOND;
	}
	public void setIF_DIAMOND(String iF_DIAMOND) {
		IF_DIAMOND = iF_DIAMOND;
	}
	public String getIF_USA_RESID() {
		return IF_USA_RESID;
	}
	public void setIF_USA_RESID(String iF_USA_RESID) {
		IF_USA_RESID = iF_USA_RESID;
	}
	public String getIF_HEAD_BANK() {
		return IF_HEAD_BANK;
	}
	public void setIF_HEAD_BANK(String iF_HEAD_BANK) {
		IF_HEAD_BANK = iF_HEAD_BANK;
	}
	public String getIF_TWOPLACE() {
		return IF_TWOPLACE;
	}
	public void setIF_TWOPLACE(String iF_TWOPLACE) {
		IF_TWOPLACE = iF_TWOPLACE;
	}
	public String getIF_FINANCE() {
		return IF_FINANCE;
	}
	public void setIF_FINANCE(String iF_FINANCE) {
		IF_FINANCE = iF_FINANCE;
	}
	public String getIF_CREDIT() {
		return IF_CREDIT;
	}
	public void setIF_CREDIT(String iF_CREDIT) {
		IF_CREDIT = iF_CREDIT;
	}
	public String getIF_ELEC() {
		return IF_ELEC;
	}
	public void setIF_ELEC(String iF_ELEC) {
		IF_ELEC = iF_ELEC;
	}
	public String getIF_PHONE() {
		return IF_PHONE;
	}
	public void setIF_PHONE(String iF_PHONE) {
		IF_PHONE = iF_PHONE;
	}
	public String getIF_TELEPHONE() {
		return IF_TELEPHONE;
	}
	public void setIF_TELEPHONE(String iF_TELEPHONE) {
		IF_TELEPHONE = iF_TELEPHONE;
	}
	public String getIF_HIGH_FINANCE() {
		return IF_HIGH_FINANCE;
	}
	public void setIF_HIGH_FINANCE(String iF_HIGH_FINANCE) {
		IF_HIGH_FINANCE = iF_HIGH_FINANCE;
	}
	public String getIF_VIP() {
		return IF_VIP;
	}
	public void setIF_VIP(String iF_VIP) {
		IF_VIP = iF_VIP;
	}
	public String getIF_MIDDLE_FINANCE() {
		return IF_MIDDLE_FINANCE;
	}
	public void setIF_MIDDLE_FINANCE(String iF_MIDDLE_FINANCE) {
		IF_MIDDLE_FINANCE = iF_MIDDLE_FINANCE;
	}
	public String getTEL_NUMBER() {
		return TEL_NUMBER;
	}
	public void setTEL_NUMBER(String tEL_NUMBER) {
		TEL_NUMBER = tEL_NUMBER;
	}
	public String getAUM_BAL_RMB() {
		return AUM_BAL_RMB;
	}
	public void setAUM_BAL_RMB(String aUM_BAL_RMB) {
		AUM_BAL_RMB = aUM_BAL_RMB;
	}
	public String getQUART_CONTR_AMT() {
		return QUART_CONTR_AMT;
	}
	public void setQUART_CONTR_AMT(String qUART_CONTR_AMT) {
		QUART_CONTR_AMT = qUART_CONTR_AMT;
	}
	public String getAUM_AVG() {
		return AUM_AVG;
	}
	public void setAUM_AVG(String aUM_AVG) {
		AUM_AVG = aUM_AVG;
	}
	public String getAUM_EVN_GRADE() {
		return AUM_EVN_GRADE;
	}
	public void setAUM_EVN_GRADE(String aUM_EVN_GRADE) {
		AUM_EVN_GRADE = aUM_EVN_GRADE;
	}
	public String getQUART_CONTR_GRADE() {
		return QUART_CONTR_GRADE;
	}
	public void setQUART_CONTR_GRADE(String qUART_CONTR_GRADE) {
		QUART_CONTR_GRADE = qUART_CONTR_GRADE;
	}
	public String getCUST_MGR_NM() {
		return CUST_MGR_NM;
	}
	public void setCUST_MGR_NM(String cUST_MGR_NM) {
		CUST_MGR_NM = cUST_MGR_NM;
	}
}
