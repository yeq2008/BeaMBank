package com.bea.remote.models.cuishou;

import java.io.Serializable;

/**
 * @author fanglinhao
 * �������ϵ��Ϣ
 */
public class JKRLXInfo implements Serializable {
	private String TelType;//	�绰����
	private String IntArea;//	��������
	private String Area;//	����
	private String Telephone;//	�绰����
	private String Ext;//	�ֻ���
	private String IsInformation;//	�ͻ��Ƿ���ܶ�Ϣ
	private String IsNew;//	�Ƿ�����
	public JKRLXInfo(){}
	public JKRLXInfo(String telType, String intArea, String area,
			String telephone, String ext, String isInformation, String isNew) {
		TelType = telType;
		IntArea = intArea;
		Area = area;
		Telephone = telephone;
		Ext = ext;
		IsInformation = isInformation;
		IsNew = isNew;
	}
	public String getTelType() {
		return TelType;
	}
	public void setTelType(String telType) {
		TelType = telType;
	}
	public String getIntArea() {
		return IntArea;
	}
	public void setIntArea(String intArea) {
		IntArea = intArea;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getExt() {
		return Ext;
	}
	public void setExt(String ext) {
		Ext = ext;
	}
	public String getIsInformation() {
		return IsInformation;
	}
	public void setIsInformation(String isInformation) {
		IsInformation = isInformation;
	}
	public String getIsNew() {
		return IsNew;
	}
	public void setIsNew(String isNew) {
		IsNew = isNew;
	}
}
