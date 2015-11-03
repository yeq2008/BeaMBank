package com.bea.remote.models.grwdy;

import java.io.Serializable;

public class MateInforBO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 中文姓
	 */
	private String WFName;
	/**
	 * 中文名
	 */
	private String WLName;
	/**
	 * 英文姓
	 */
	private String WEFName;
	/**
	 * 英文名
	 */
	private String WELName;
	/**
	 * 证件类型
	 */
	private String WCType  = "Ind1010";
	/**
	 * 证件号码
	 */
	private String WCNum;
	/**
	 * 发证国家
	 */
	private String WCC = "CHN";
	/**
	 * 证件有效期
	 */
	private String WCDate;

	/**
	 * 得到姓（中文）
	 */
	public String getWFName() {
		return WFName;
	}

	/**
	 * 设置姓（中文）
	 */
	public void setWFName(String wFName) {
		WFName = wFName;
	}

	/**
	 * 得到名（中文）
	 */
	public String getWLName() {
		return WLName;
	}

	/**
	 * 设置名（中文）
	 */
	public void setWLName(String wLName) {
		WLName = wLName;
	}

	/**
	 * 得到姓（英文/拼音）
	 */
	public String getWEFName() {
		return WEFName;
	}

	/**
	 * 设置姓（英文/拼音）
	 */
	public void setWEFName(String wEFName) {
		WEFName = wEFName;
	}

	/**
	 * 得到名（英文/拼音）
	 */
	public String getWELName() {
		return WELName;
	}

	/**
	 * 设置名（英文/拼音）
	 */
	public void setWELName(String wELName) {
		WELName = wELName;
	}

	/**
	 * 得到证件类型
	 */
	public String getWCType() {
		return WCType;
	}

	/**
	 * 设置证件类型
	 */
	public void setWCType(String wCType) {
		WCType = wCType;
	}

	/**
	 * 得到证件号码
	 */
	public String getWCNum() {
		return WCNum;
	}

	/**
	 * 设置证件号码
	 */
	public void setWCNum(String wCNum) {
		WCNum = wCNum;
	}

	/**
	 * 得到发证国家
	 */
	public String getWCC() {
		return WCC;
	}

	/**
	 * 设置发证国家
	 */
	public void setWCC(String wCC) {
		WCC = wCC;
	}

	/**
	 * 得到证件有效期
	 */
	public String getWCDate() {
		return WCDate;
	}

	/**
	 * 设置证件有效期
	 */
	public void setWCDate(String wCDate) {
		WCDate = wCDate;
	}

}
