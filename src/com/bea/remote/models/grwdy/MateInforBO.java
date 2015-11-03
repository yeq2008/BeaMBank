package com.bea.remote.models.grwdy;

import java.io.Serializable;

public class MateInforBO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * ������
	 */
	private String WFName;
	/**
	 * ������
	 */
	private String WLName;
	/**
	 * Ӣ����
	 */
	private String WEFName;
	/**
	 * Ӣ����
	 */
	private String WELName;
	/**
	 * ֤������
	 */
	private String WCType  = "Ind1010";
	/**
	 * ֤������
	 */
	private String WCNum;
	/**
	 * ��֤����
	 */
	private String WCC = "CHN";
	/**
	 * ֤����Ч��
	 */
	private String WCDate;

	/**
	 * �õ��գ����ģ�
	 */
	public String getWFName() {
		return WFName;
	}

	/**
	 * �����գ����ģ�
	 */
	public void setWFName(String wFName) {
		WFName = wFName;
	}

	/**
	 * �õ��������ģ�
	 */
	public String getWLName() {
		return WLName;
	}

	/**
	 * �����������ģ�
	 */
	public void setWLName(String wLName) {
		WLName = wLName;
	}

	/**
	 * �õ��գ�Ӣ��/ƴ����
	 */
	public String getWEFName() {
		return WEFName;
	}

	/**
	 * �����գ�Ӣ��/ƴ����
	 */
	public void setWEFName(String wEFName) {
		WEFName = wEFName;
	}

	/**
	 * �õ�����Ӣ��/ƴ����
	 */
	public String getWELName() {
		return WELName;
	}

	/**
	 * ��������Ӣ��/ƴ����
	 */
	public void setWELName(String wELName) {
		WELName = wELName;
	}

	/**
	 * �õ�֤������
	 */
	public String getWCType() {
		return WCType;
	}

	/**
	 * ����֤������
	 */
	public void setWCType(String wCType) {
		WCType = wCType;
	}

	/**
	 * �õ�֤������
	 */
	public String getWCNum() {
		return WCNum;
	}

	/**
	 * ����֤������
	 */
	public void setWCNum(String wCNum) {
		WCNum = wCNum;
	}

	/**
	 * �õ���֤����
	 */
	public String getWCC() {
		return WCC;
	}

	/**
	 * ���÷�֤����
	 */
	public void setWCC(String wCC) {
		WCC = wCC;
	}

	/**
	 * �õ�֤����Ч��
	 */
	public String getWCDate() {
		return WCDate;
	}

	/**
	 * ����֤����Ч��
	 */
	public void setWCDate(String wCDate) {
		WCDate = wCDate;
	}

}
