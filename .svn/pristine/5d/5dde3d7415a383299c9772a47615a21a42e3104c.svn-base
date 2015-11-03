package com.bea.remote.models.draft;

import java.io.Serializable;

import com.bea.remote.models.grwdy.GrwdyHomeBO;

import xc.android.remote.json.JsonModel;

/**
 * @author cuiyuguo
 * 暂存数据模型
 */
@JsonModel
public final class DraftListBO implements Serializable {
	//新的进件填写0(主键)
	String mainid = "0";
	String isCrmQueryed = "0";//crm是否查询
	String isReaded = "1";//是否阅读章程
	//是否数据格式错误导致提交失败标示
	String isFormatErrorFlag = "1";
	//业务品种 10 平安信保 11车位贷12消费贷
	String CBType;
	//客户名称
	String CUName;
	//客户证件号码
	String CUNum;
	//申请金额
	String CAmount;
	//申请期限
	String CLDate;
	//申请日期
	String CADate;
	//要暂存的数据
	GrwdyHomeBO listinfo;
	//影像资料的服务器存放地址
	String photoZipUrl;
	//保单号
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
