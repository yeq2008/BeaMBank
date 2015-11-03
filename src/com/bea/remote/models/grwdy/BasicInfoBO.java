/**
 * 
 */
package com.bea.remote.models.grwdy;

import java.io.Serializable;

import com.bea.application.BeaAppSettings;
import com.bea.remote.models.user.UserInfoBO;

/**
 * @author lsq����¼����˻�����������ģ��
 *
 */
public class BasicInfoBO implements Serializable { 
	private static final long serialVersionUID = -6766989837247583237L;
	final UserInfoBO userInfo = BeaAppSettings.getUserInfo();
	private String LCBType;
	private String LCFName;
	private String LCLName;
	private String LCEFName;
	private String LCELName;
	private String LCSex;
	private String LCTypeName;
	private String LCType = "Ind1010";
	private String LCC = "CHN";
	private String LCNum;
	private String LCMINum = userInfo.getEmployee_mark();
	/**
	 * ҵ��Ʒ��
	 * @return
	 */
	public String getLCBType() {
		return LCBType;
	}
	/**
	 * ҵ��Ʒ��
	 * @param lCBType
	 */
	public void setLCBType(String lCBType) {
		LCBType = lCBType;
	}
	/**
	 * ¼����ID
	 */
	public String getLCMINum() {
		return LCMINum;
	}
	/**
	 * ¼����ID
	 */
	public void setLCMINum(String lCMINum) {
		LCMINum = lCMINum;
	}
	/**
	 * �Ա�
	 * @return
	 */
	public String getLCSex() {
		return LCSex;
	}
	/**
	 * �Ա�
	 * @return
	 */
	public void setLCSex(String lCSex) {
		LCSex = lCSex;
	}
	/**
	 * ֤������name
	 * @return
	 */
	public String getLCTypeName() {
		return LCTypeName;
	}
	/**
	 * ֤������name
	 * @return
	 */
	public void setLCTypeName(String lCTypeName) {
		LCTypeName = lCTypeName;
	}
	/**
	 *������
	 */
	public String getLCFName() {
		return LCFName;
	}
	/**
	 *������
	 */
	public void setLCFName(String lCFName) {
		LCFName = lCFName;
	}
	/**
	 *������
	 */
	public String getLCLName() {
		return LCLName;
	}
	/**
	 *������
	 */
	public void setLCLName(String lCLName) {
		LCLName = lCLName;
	}
	/**
	 *Ӣ����
	 */
	public String getLCEFName() {
		return LCEFName;
	}
	/**
	 *Ӣ����
	 */
	public void setLCEFName(String lCEFName) {
		LCEFName = lCEFName;
	}
	/**
	 *Ӣ����
	 */
	public String getLCELName() {
		return LCELName;
	}
	/**
	 *Ӣ����
	 */
	public void setLCELName(String lCELName) {
		LCELName = lCELName;
	}
	/**
	 * �õ�֤������
	 */
	public String getLCType() {
		return LCType;
	}
	/**
	 * ����֤������
	 */
	public void setLCType(String lCType) {
		LCType = lCType;
	}
	/**
	 * �õ���֤����
	 */
	public String getLCC() {
		return LCC;
	}
	/**
	 * ���÷�֤����
	 * @param lCC
	 */
	public void setLCC(String lCC) {
		LCC = lCC;
	}
	/**
	 * �õ�֤������
	 */
	public String getLCNum() {
		return LCNum;
	}
	/**
	 * ����֤������
	 */
	public void setLCNum(String lCNum) {
		LCNum = lCNum;
	}



}
