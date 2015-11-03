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
 * @author xuruian 申请贷款及费用信息数据模型
 */
@JsonModel
public final class ApplyLoanInforBO implements Serializable {

	private static final long serialVersionUID = 3465033886695545493L;
	final UserInfoBO userInfo = BeaAppSettings.getUserInfo();
	// 方案套餐产品
	private String IPlan;
	// 业务品种
	private String IBType = "0201";
	// 币种
	private String ICurrency="01";
	// 贷款金额
	private String ILMoney;
	// 贷款期限
	private String ILDate;
	// 利率调整方式
	private String IRT;
	// 利率调整方式名称
	private String IRTName;

	// 利率调整周期
	private String IRPeriod="1";
	// 利率调整周期单位
	private String IRTU="020";
	// 利率调整周期单位名称
	private String IRTUName;

	// 贷款用途
	private String ILT;

	private String Ocomment;    //用途备注
	// 贷款支付方式
	private String IPM="1";
	// 贷款支付方式名称
	private String IPMName;

	// 不接收关于此贷款的短信标志
	private String IMType="2";

	// 入账机构
	private String IRBEAD = "6200";

	// 入账机构名称
	private String IRBEADName = "信用卡及渠道拓展中心";

	// 经办机构
	private String IDBEAD = userInfo.getOrg_id();
	// 经办机构名称
	private String IDBEADName = DbManager.getOrgNameById(userInfo.getOrg_id());

	// 利率类型
	private String IRType;
	// 利率类型名称
	private String IRTypeName;

	// 经办人
	private String IDNum = userInfo.getEmployee_mark();
	// 经办人名称
	private String IDNumName = userInfo.getReal_name();

	// 担保方式
	private String ICType="030";

	// 浮动幅度
	private String IFluctuate;

	// 执行利率
	private String IAR;

	// 还款方式,默认等额本息
	private String IBPM="RPT001";
	
	private String IIBPM = "01";

	// 还款周期
	private String IBPP = "1";

	// 是否在线支付
	private String IOnline;

	// 放款账户币种
	private String IGCurrency="01";
	// 放款账户币种名称
	private String IGCurrencyName;
	// 放款账户/卡号
	private String IGAccount;
	// 放款账户验证卡号
	private String IGAccountValid;

	// 放款账户名称
	private String IGName;

	// 还款账户是否同放款账户
	private String IBG;

	// 还款账户/卡号
	private String IBAccount;
	private String IBAccountValid;
	
	// 还款账号户名
	private String IBName;
	// 还款账户开户行代码
	private String IBBank;
	// 还款账户币种
	private String IBCurrency="01";
	// 还款账户币种名称
	private String IBCurrencyName;
	
	//账户管理费率
	private String IManagement;
	//缴费周期
	private String IPeriod;
	public String getIPMName() {
		return IPMName;
	}

	public void setIPMName(String iPMName) {
		IPMName = iPMName;
	}

	public String getIRBEADName() {
		return IRBEADName;
	}

	public void setIRBEADName(String iRBEADName) {
		IRBEADName = iRBEADName;
	}

	public String getIDBEADName() {
		return IDBEADName;
	}

	public void setIDBEADName(String iDBEADName) {
		IDBEADName = iDBEADName;
	}

	public String getIRTypeName() {
		return IRTypeName;
	}

	public void setIRTypeName(String iRTypeName) {
		IRTypeName = iRTypeName;
	}

	public String getIDNumName() {
		return IDNumName;
	}

	public void setIDNumName(String iDNumName) {
		IDNumName = iDNumName;
	}

	public String getIGCurrencyName() {
		return IGCurrencyName;
	}

	public void setIGCurrencyName(String iGCurrencyName) {
		IGCurrencyName = iGCurrencyName;
	}

	public String getIBCurrencyName() {
		return IBCurrencyName;
	}

	public void setIBCurrencyName(String iBCurrencyName) {
		IBCurrencyName = iBCurrencyName;
	}

	/**
	 * @brief 获取项目方案/套餐
	 */

	public String getIPlan() {
		return IPlan;
	}

	/**
	 * @brief 设置项目方案/套餐
	 */

	public void setIPlan(String iPlan) {
		IPlan = iPlan;
	}

	/**
	 * @brief 获取业务品种
	 */

	public String getIBType() {
		return IBType;
	}

	/**
	 * @brief 设置业务品种
	 */

	public void setIBType(String iBType) {
		IBType = iBType;
	}

	/**
	 * @brief 获取币种
	 */

	public String getICurrency() {
		return ICurrency;
	}

	/**
	 * @brief 设置币种
	 */

	public void setICurrency(String iCurrency) {
		ICurrency = iCurrency;
	}

	/**
	 * @brief 获取贷款金额
	 */

	public String getILMoney() {
		return ILMoney;
	}

	/**
	 * @brief 设置贷款金额
	 */

	public void setILMoney(String iLMoney) {
		ILMoney = iLMoney;
	}

	/**
	 * @brief 获取贷款期限
	 */

	public String getILDate() {
		return ILDate;
	}

	/**
	 * @brief 设置贷款期限
	 */

	public void setILDate(String iLDate) {
		ILDate = iLDate;
	}

	/**
	 * @brief 获取利率调整方式
	 */
	public String getIRTName() {
		return IRTName;
	}

	public String getIRT() {
		return IRT;
	}

	/**
	 * @brief 设置利率调整方式
	 */

	public void setIRT(String iRT) {
		IRT = iRT;
	}

	public void setIRTName(String iRTName) {
		IRTName = iRTName;
	}

	/**
	 * @brief 获取利率调整周期
	 */

	public String getIRPeriod() {
		return IRPeriod;
	}

	/**
	 * @brief 设置利率调整周期
	 */

	public void setIRPeriod(String iRPeriod) {
		IRPeriod = iRPeriod;
	}

	/**
	 * @brief 获取利率调整周期单位
	 */
	public String getIRTUName() {
		return IRTUName;
	}

	public String getIRTU() {
		return IRTU;
	}

	/**
	 * @brief 设置利率调整周期单位
	 */

	public void setIRTU(String iRTU) {
		IRTU = iRTU;
	}

	public void setIRTUName(String iRTUName) {
		IRTUName = iRTUName;
	}

	/**
	 * @brief 获取贷款用途
	 */

	public String getILT() {
		return ILT;
	}

	/**
	 * @brief 设置贷款用途
	 */

	public void setILT(String iLT) {
		ILT = iLT;
	}

	/** 用途备注*/
	public String getOcomment() {
		return Ocomment;
	}
	/** 用途备注*/
	public void setOcomment(String ocomment) {
		Ocomment = ocomment;
	}

	/**
	 * @brief 获取贷款支付方式
	 */

	public String getIPM() {
		return IPM;
	}

	/**
	 * @brief 设置贷款支付方式
	 */

	public void setIPM(String iPM) {
		IPM = iPM;
	}

	/**
	 * @brief 获取是否接收关于此贷款的短信标志
	 */

	public String getIMType() {
		return IMType;
	}

	/**
	 * @brief 设置是否接收关于此贷款的短信标志
	 */

	public void setIMType(String iMType) {
		IMType = iMType;
	}

	/**
	 * @brief 获取入账机构
	 */

	public String getIRBEAD() {
		return IRBEAD;
	}

	/**
	 * @brief 设置入账机构
	 */

	public void setIRBEAD(String iRBEAD) {
		IRBEAD = iRBEAD;
	}

	/**
	 * @brief 获取经办机构
	 */

	public String getIDBEAD() {
		return IDBEAD;
	}

	/**
	 * @brief 设置经办机构
	 */

	public void setIDBEAD(String iDBEAD) {
		IDBEAD = iDBEAD;
	}

	/**
	 * @brief 获取利率类型
	 */

	public String getIRType() {
		return IRType;
	}

	/**
	 * @brief 设置利率类型
	 */

	public void setIRType(String iRType) {
		IRType = iRType;
	}

	/**
	 * @brief 获取经办人
	 */

	public String getIDNum() {
		return IDNum;
	}

	/**
	 * @brief 设置经办人
	 */

	public void setIDNum(String iDNum) {
		IDNum = iDNum;
	}

	/**
	 * @brief 获取担保方式
	 */

	public String getICType() {
		return ICType;
	}

	/**
	 * @brief 设置担保方式
	 */

	public void setICType(String iCType) {
		ICType = iCType;
	}

	/**
	 * @brief 获取浮动幅度
	 */

	public String getIFluctuate() {
		return IFluctuate;
	}

	/**
	 * @brief 设置浮动幅度
	 */

	public void setIFluctuate(String iFluctuate) {
		IFluctuate = iFluctuate;
	}

	/**
	 * @brief 获取执行利率
	 */

	public String getIAR() {
		return IAR;
	}

	/**
	 * @brief 设置执行利率
	 */

	public void setIAR(String iAR) {
		IAR = iAR;
	}

	/**
	 * @brief 获取还款方式
	 */

	public String getIBPM() {
		return IBPM;
	}

	/**
	 * @brief 设置还款方式
	 */

	public void setIBPM(String iBPM) {
		IBPM = iBPM;
	}
	
	public String getIIBPM() {
		return IIBPM;
	}

	public void setIIBPM(String iIBPM) {
		IIBPM = iIBPM;
	}

	/**
	 * @brief 获取还款周期
	 */

	public String getIBPP() {
		return IBPP;
	}

	/**
	 * @brief 设置还款周期
	 */

	public void setIBPP(String iBPP) {
		IBPP = iBPP;
	}

	/**
	 * @brief 获取是否在线支付
	 */

	public String getIOnline() {
		return IOnline;
	}

	/**
	 * @brief 设置是否在线支付
	 */

	public void setIOnline(String iOnline) {
		IOnline = iOnline;
	}

	/**
	 * @brief 获取放款账户币种
	 */

	public String getIGCurrency() {
		return IGCurrency;
	}

	/**
	 * @brief 设置放款账户币种
	 */

	public void setIGCurrency(String iGCurrency) {
		IGCurrency = iGCurrency;
	}

	/**
	 * @brief 获取放款账户号
	 */

	public String getIGAccount() {
		return IGAccount;
	}

	/**
	 * @brief 设置放款账户号
	 */

	public void setIGAccount(String iGAccount) {
		IGAccount = iGAccount;
	}

	/**
	 * @brief 获取放款账户名
	 */

	public String getIGName() {
		return IGName;
	}

	/**
	 * @brief 设置放款账户名
	 */

	public void setIGName(String iGName) {
		IGName = iGName;
	}

	/**
	 * @brief 获取还款账户是否同放款账户
	 */

	public String getIBG() {
		return IBG;
	}

	/**
	 * @brief 设置还款账户是否同放款账户
	 */

	public void setIBG(String iBG) {
		IBG = iBG;
	}

	/**
	 * @brief 获取还款账户币种
	 */

	public String getIBCurrency() {
		return IBCurrency;
	}

	/**
	 * @brief 设置还款账户币种
	 */

	public void setIBCurrency(String iBCurrency) {
		IBCurrency = iBCurrency;
	}

	/**
	 * @brief 获取还款账户号
	 */

	public String getIBAccount() {
		return IBAccount;
	}

	/**
	 * @brief 设置还款账户号
	 */

	public void setIBAccount(String iBAccount) {
		IBAccount = iBAccount;
	}

	/**
	 * @brief 获取还款账户名
	 */

	public String getIBName() {
		return IBName;
	}

	/**
	 * @brief 设置还款账户名
	 */

	public void setIBName(String iBName) {
		IBName = iBName;
	}

	/**
	 * @brief 获取还款账户开户行代码
	 */

	public String getIBBank() {
		return IBBank;
	}

	/**
	 * @brief 设置还款账户开户行代码
	 */

	public void setIBBank(String iBBank) {
		IBBank = iBBank;
	}

	public String getIGAccountValid() {
		return IGAccountValid;
	}

	public String getIBAccountValid() {
		return IBAccountValid;
	}

	public void setIGAccountValid(String iGAccountValid) {
		IGAccountValid = iGAccountValid;
	}

	public void setIBAccountValid(String iBAccountValid) {
		IBAccountValid = iBAccountValid;
	}
	/** 账户管理费率*/
	public String getIManagement() {
		return IManagement;
	}
	/** 账户管理费率*/
	public void setIManagement(String iManagement) {
		IManagement = iManagement;
	}
	/** 缴费周期*/
	public String getIPeriod() {
		return IPeriod;
	}
	/** 缴费周期*/
	public void setIPeriod(String iPeriod) {
		IPeriod = iPeriod;
	}
	
	

}
