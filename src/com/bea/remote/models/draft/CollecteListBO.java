/**
 * 
 */
package com.bea.remote.models.draft;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;

/**
 * @author lsq 催收暂存数据模型
 */
@JsonModel
public final class CollecteListBO implements Serializable {

	private static final long serialVersionUID = -837709391933213199L;
	/**
	 * 查询条件
	 */
	String selCond;
	/**
	 * 查询条件
	 */
	public String getSelCond() {
		return selCond;
	}
	/**
	 * 查询条件
	 */
	public void setSelCond(String selCond) {
		this.selCond = selCond;
	}
	/**
	 * 业务品种
	 */
	String BusinessType;
	/**
	 * 客户名称
	 */
	String CustomerName;
	/**
	 * 客户电话
	 */
	String LenderPhoneNumber;
	/**
	 * 五级分类结果
	 */
	String ClassifyResultName;
	/**
	 * 当期应还金额
	 */
	String AllBalance;
	/**
	 * 借据编号
	 */
	String SerialNo;

	/**
	 * 业务品种
	 * @return
	 */
	public String getBusinessType() {
		return BusinessType;
	}
	/**
	 * 业务品种
	 * @return
	 */
	public void setBusinessType(String businessType) {
		BusinessType = businessType;
	}
	/**
	 * 客户名称
	 * @return
	 */
	public String getCustomerName() {
		return CustomerName;
	}
	/**
	 * 客户名称
	 * @return
	 */
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	/**
	 * 客户电话
	 * @return
	 */
	public String getLenderPhoneNumber() {
		return LenderPhoneNumber;
	}
	/**
	 * 客户电话
	 * @return
	 */
	public void setLenderPhoneNumber(String lenderPhoneNumber) {
		LenderPhoneNumber = lenderPhoneNumber;
	}
	/**
	 * 五级分类结果
	 * @return
	 */
	public String getClassifyResultName() {
		return ClassifyResultName;
	}
	/**
	 * 五级分类结果
	 * @return
	 */
	public void setClassifyResultName(String classifyResultName) {
		ClassifyResultName = classifyResultName;
	}
	/**
	 * 本期应还金额
	 * @return
	 */
	public String getAllBalance() {
		return AllBalance;
	}
	/**
	 * 本期应还金额
	 * @return
	 */
	public void setAllBalance(String allBalance) {
		AllBalance = allBalance;
	}
	/**
	 * 借据编号
	 * @return
	 */
	public String getSerialNo() {
		return SerialNo;
	}
	/**
	 * 借据编号
	 * @return
	 */
	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}
}
