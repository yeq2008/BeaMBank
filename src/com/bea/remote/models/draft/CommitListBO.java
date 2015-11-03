/**
 * 
 */
package com.bea.remote.models.draft;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;

/**
 * @author lsq
 * 待发暂存数据模型
 */
@JsonModel
public class CommitListBO implements Serializable {

	//新的进件填写0(主键)
	String mainid = "0";
	//业务品种 10 平安信保 11 新时贷
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
	String listinfo;
	//影像资料的服务器存放地址
	String photoZipUrl;
	//客户签名图片的服务器存放地址
	String signedUrl;
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
	public String getListinfo() {
		return listinfo;
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
	public void setListinfo(String listinfo) {
		this.listinfo = listinfo;
	}
	public String getPhotoZipUrl() {
		return photoZipUrl;
	}
	public void setPhotoZipUrl(String photoZipUrl) {
		this.photoZipUrl = photoZipUrl;
	}
	public String getSignedUrl() {
		return signedUrl;
	}
	public void setSignedUrl(String signedUrl) {
		this.signedUrl = signedUrl;
	}
}
