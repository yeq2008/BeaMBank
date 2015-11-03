/**
 * 
 */
package com.bea.remote.models.grwdy;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;

/**
 * @author cuiyuguo
 * 地址数据模型
 */
@JsonModel
public final class AddressBO implements Serializable {
	/**
	 * 地址类型
	 */
	private String MAddr;
	/**
	 * 是否通讯地址
	 */
	private String MRAddr;
	/**
	 * 地址所属国家/地区
	 */
	private String MACountry = "CHN";
	/**
	 * 地址所属省/市
	 */
	private String MACity;
	/**
	 * 地址详情1
	 */
	private String MAD1;
	/**
	 * 地址详情2
	 */
	private String MAD2;
	/**
	 * 地址详情3
	 */
	private String MAD3;
	/**
	 * 地址详情4
	 */
	private String MAD4;
	/**
	 * 邮政编码
	 */
	private String MPC;
	/**
	 * 邮寄方式
	 */
	private String MSType = "6";
	/**
	 * 地址类型name
	 */
	private String MAddrName;
	/**
	 * 地址所属国家/地区name
	 */
	private String MACountryName;
	/**
	 * 地址所属省/市name
	 */
	private String MACityName;
	/**
	 * 邮寄方式name
	 */
	private String MSTypeName;
	
	public AddressBO(){}
	public AddressBO(String mAddr) {
		MAddr = mAddr;
	}
	public String getMAddr() {
		return MAddr;
	}
	public String getMRAddr() {
		return MRAddr;
	}
	public String getMACountry() {
		return MACountry;
	}
	public String getMACity() {
		return MACity;
	}
	public String getMAD1() {
		return MAD1;
	}
	public String getMAD2() {
		return MAD2;
	}
	public String getMAD3() {
		return MAD3;
	}
	public String getMAD4() {
		return MAD4;
	}
	public String getMPC() {
		return MPC;
	}
	public String getMSType() {
		return MSType;
	}
	public void setMAddr(String mAddr) {
		MAddr = mAddr;
	}
	public void setMRAddr(String mRAddr) {
		MRAddr = mRAddr;
	}
	public void setMACountry(String mACountry) {
		MACountry = mACountry;
	}
	public void setMACity(String mACity) {
		MACity = mACity;
	}
	public void setMAD1(String mAD1) {
		MAD1 = mAD1;
	}
	public void setMAD2(String mAD2) {
		MAD2 = mAD2;
	}
	public void setMAD3(String mAD3) {
		MAD3 = mAD3;
	}
	public void setMAD4(String mAD4) {
		MAD4 = mAD4;
	}
	public void setMPC(String mPC) {
		MPC = mPC;
	}
	public void setMSType(String mSType) {
		MSType = mSType;
	}
	public String getMAddrName() {
		return MAddrName;
	}
	public String getMACountryName() {
		return MACountryName;
	}
	public String getMACityName() {
		return MACityName;
	}
	public String getMSTypeName() {
		return MSTypeName;
	}
	public void setMAddrName(String mAddrName) {
		MAddrName = mAddrName;
	}
	public void setMACountryName(String mACountryName) {
		MACountryName = mACountryName;
	}
	public void setMACityName(String mACityName) {
		MACityName = mACityName;
	}
	public void setMSTypeName(String mSTypeName) {
		MSTypeName = mSTypeName;
	}
}
