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
public final class jointAddressBO implements Serializable {
	private String CAddr;
	private String CAddrName;
	private String CRAddr;
	private String CACountry;
	private String CACountryName;
	private String CCCity;
	private String CCCityName;
	/**
	 * 地址详情1
	 */
	private String CAD1;
	/**
	 * 地址详情2
	 */
	private String CAD2;
	/**
	 * 地址详情3
	 */
	private String CAD3;
	/**
	 * 地址详情4
	 */
	private String CAD4;
	private String CPC;
	private String CSType;
	private String CSTypeName;
	
	
	
	public jointAddressBO(){}
	
	public jointAddressBO(String cAddr) { 
		CAddr = cAddr;
	}
	public String getCAddr() {
		return CAddr;
	}
	public void setCAddr(String cAddr) {
		CAddr = cAddr;
	}
	public String getCAddrName() {
		return CAddrName;
	}
	public void setCAddrName(String cAddrName) {
		CAddrName = cAddrName;
	}
	public String getCRAddr() {
		return CRAddr;
	}
	public void setCRAddr(String cRAddr) {
		CRAddr = cRAddr;
	}
	public String getCACountry() {
		return CACountry;
	}
	public void setCACountry(String cACountry) {
		CACountry = cACountry;
	}
	public String getCACountryName() {
		return CACountryName;
	}
	public void setCACountryName(String cACountryName) {
		CACountryName = cACountryName;
	}
	public String getCCCity() {
		return CCCity;
	}
	public void setCCCity(String cCCity) {
		CCCity = cCCity;
	}
	public String getCCCityName() {
		return CCCityName;
	}
	public void setCCCityName(String cCCityName) {
		CCCityName = cCCityName;
	}
 
	 
	public String getCAD1() {
		return CAD1;
	}
	public void setCAD1(String cAD1) {
		CAD1 = cAD1;
	}
	public String getCAD2() {
		return CAD2;
	}
	public void setCAD2(String cAD2) {
		CAD2 = cAD2;
	}
	public String getCAD3() {
		return CAD3;
	}
	public void setCAD3(String cAD3) {
		CAD3 = cAD3;
	}
	public String getCAD4() {
		return CAD4;
	}
	public void setCAD4(String cAD4) {
		CAD4 = cAD4;
	}
	public String getCPC() {
		return CPC;
	}
	public void setCPC(String cPC) {
		CPC = cPC;
	}
	public String getCSType() {
		return CSType;
	}
	public void setCSType(String cSType) {
		CSType = cSType;
	}
	public String getCSTypeName() {
		return CSTypeName;
	}
	public void setCSTypeName(String cSTypeName) {
		CSTypeName = cSTypeName;
	}

}
