package com.bea.remote.models.blacklist;

/**
 * ������
 * NDS�����Ϣ
 * @author fanglinhao
 */
public class NDSInfo {
	private String WATCHLIST_TYPE;//���������� 
	private String WATCHLIST_DESC;//��������������
	private String WATCHLIST_NAME;//���������� 
	private String BIRTHDAY;//��������������
	private String COUNTRY_NAME;//���������� 
	private String MATCH_RATE;//ƥ���� 
	private String PRE_CLIENT_NAME;//�ͻ���ת������
	private String CONVERSION_NAME;//ת��������
	public String getWATCHLIST_TYPE() {
		return WATCHLIST_TYPE;
	}
	public void setWATCHLIST_TYPE(String wATCHLIST_TYPE) {
		WATCHLIST_TYPE = wATCHLIST_TYPE;
	}
	public String getWATCHLIST_DESC() {
		return WATCHLIST_DESC;
	}
	public void setWATCHLIST_DESC(String wATCHLIST_DESC) {
		WATCHLIST_DESC = wATCHLIST_DESC;
	}
	public String getWATCHLIST_NAME() {
		return WATCHLIST_NAME;
	}
	public void setWATCHLIST_NAME(String wATCHLIST_NAME) {
		WATCHLIST_NAME = wATCHLIST_NAME;
	}
	public String getBIRTHDAY() {
		return BIRTHDAY;
	}
	public void setBIRTHDAY(String bIRTHDAY) {
		BIRTHDAY = bIRTHDAY;
	}
	public String getCOUNTRY_NAME() {
		return COUNTRY_NAME;
	}
	public void setCOUNTRY_NAME(String cOUNTRY_NAME) {
		COUNTRY_NAME = cOUNTRY_NAME;
	}
	public String getMATCH_RATE() {
		return MATCH_RATE;
	}
	public void setMATCH_RATE(String mATCH_RATE) {
		MATCH_RATE = mATCH_RATE;
	}
	public String getPRE_CLIENT_NAME() {
		return PRE_CLIENT_NAME;
	}
	public void setPRE_CLIENT_NAME(String pRE_CLIENT_NAME) {
		PRE_CLIENT_NAME = pRE_CLIENT_NAME;
	}
	public String getCONVERSION_NAME() {
		return CONVERSION_NAME;
	}
	public void setCONVERSION_NAME(String cONVERSION_NAME) {
		CONVERSION_NAME = cONVERSION_NAME;
	}
}
