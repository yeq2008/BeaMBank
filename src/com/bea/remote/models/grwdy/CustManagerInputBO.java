package com.bea.remote.models.grwdy;

import java.io.Serializable;

/**
 * @author fanglinhao
 * �ͻ�������д
 */
public class CustManagerInputBO implements Serializable {
	private String OInterest = "2";    //�Ƿ�����Ա����Ϣ
//	private String Ocomment;    //��;��ע
	private String OLUA;    //�ʽ�ʹ�õ�
	private String OLP;//�Ƿ���������������
	private String OCountry;    //����ʹ�õ����ڹ���
	private String OCountryName;//����ʹ�õ����ڹ���
	private String OACode;    //����ʹ�õ�����ʡ��
	private String OACodeName;
	private String OCRType;    //�ͻ����������жϱ�׼
	private String OMIC = "010";    //ҵ���Ƽ�����
	private String OMICName;
	private String OBEAD;    //�Ƽ���������
	private String OCType = "Ind1010";    //�Ƽ��ͻ�-֤������
	private String OCNum;    //�Ƽ��ͻ�-֤������
	private String OCName;    //�Ƽ��ͻ�-����
	private String OCTel;    //�Ƽ��ͻ�-��ϵ�ֻ�
	private String OEC;    //ҵ���������
	private String OECName;
	private String ORSNum;    //�Ƽ�Ա������
	private String ORSName;    //�Ƽ�Ա������
	private String OSNum;    //Ա������
	private String OSName;    //Ա������
	private String OSType;    //Ա����������
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
