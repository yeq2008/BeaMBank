package com.bea.remote.models.draft;

import java.io.Serializable;

import com.bea.remote.models.grwdy.GrwdyHomeBO;

import xc.android.remote.json.JsonModel;

/**
 * @author cuiyuguo
 * �ݴ�����ģ��
 */
@JsonModel
public final class DraftListBO implements Serializable {
	//�µĽ�����д0(����)
	String mainid = "0";
	String isCrmQueryed = "0";//crm�Ƿ��ѯ
	String isReaded = "1";//�Ƿ��Ķ��³�
	//�Ƿ����ݸ�ʽ�������ύʧ�ܱ�ʾ
	String isFormatErrorFlag = "1";
	//ҵ��Ʒ�� 10 ƽ���ű� 11��λ��12���Ѵ�
	String CBType;
	//�ͻ�����
	String CUName;
	//�ͻ�֤������
	String CUNum;
	//������
	String CAmount;
	//��������
	String CLDate;
	//��������
	String CADate;
	//Ҫ�ݴ������
	GrwdyHomeBO listinfo;
	//Ӱ�����ϵķ�������ŵ�ַ
	String photoZipUrl;
	//������
	String busiCode;
	public String getMainid() {
		return mainid;
	}
	public String getCBType() {
		return CBType;
	}
	public String getCUName() {
		return CUName;
	}
	public String getCUNum() {
		return CUNum;
	}
	public String getCAmount() {
		return CAmount;
	}
	public String getCLDate() {
		return CLDate;
	}
	public String getCADate() {
		return CADate;
	}
	public GrwdyHomeBO getListinfo() {
		return listinfo;
	}
	public void setListinfo(GrwdyHomeBO listinfo) {
		this.listinfo = listinfo;
	}
	public void setMainid(String mainid) {
		this.mainid = mainid;
	}
	public void setCBType(String cBType) {
		CBType = cBType;
	}
	public void setCUName(String cUName) {
		CUName = cUName;
	}
	public void setCUNum(String cUNum) {
		CUNum = cUNum;
	}
	public void setCAmount(String cAmount) {
		CAmount = cAmount;
	}
	public void setCLDate(String cLDate) {
		CLDate = cLDate;
	}
	public void setCADate(String cADate) {
		CADate = cADate;
	}

	public String getPhotoZipUrl() {
		return photoZipUrl;
	}
	public void setPhotoZipUrl(String photoZipUrl) {
		this.photoZipUrl = photoZipUrl;
	}
	public String getBusiCode() {
		return busiCode;
	}
	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	public String getIsCrmQueryed() {
		return isCrmQueryed;
	}
	public void setIsCrmQueryed(String isCrmQueryed) {
		this.isCrmQueryed = isCrmQueryed;
	}
	public String getIsReaded() {
		return isReaded;
	}
	public void setIsReaded(String isReaded) {
		this.isReaded = isReaded;
	}
	public String getIsFormatErrorFlag() {
		return isFormatErrorFlag;
	}
	public void setIsFormatErrorFlag(String isFormatErrorFlag) {
		this.isFormatErrorFlag = isFormatErrorFlag;
	}
}
