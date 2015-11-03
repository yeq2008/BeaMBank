/**
 * 
 */
package com.bea.database.codemodel;

import java.io.Serializable;

import xc.android.sqlite.annotation.Id;
import xc.android.sqlite.annotation.NotNull;
import xc.android.sqlite.annotation.Table;

/**
 * @author cuiyuguo
 *	��Ʒ������Ϣ
 */
@Table(name = "MMP_PRODUCT_ATT")
public final class MMP_PRODUCT_ATT implements Serializable {

//	����	����	�Ƿ�����Ϊ��	����	��������	���ݳ���	��ע
//	ATT_ID	���		��	NUMBER	19	
//	PRODUCT_ID	��Ʒ�����	��		NUMBER	19	
//	FILE_NAME	�ļ���	��		VARCHAR2	300	
//	FILE_PATH	�ļ�·��	��		VARCHAR2	300	
//	VERSION	�汾��	��		NUMBER	19	
//	OPR_TIME	��������ʱ��	��		DATE		
//	OPR_TYPE	����״̬	��		CHAR	1	Y��ʾ����,N��ʾ������
	
	@Id
	String ATT_ID;	
	String PRODUCT_ID;
	String FILE_NAME;	
	String FILE_PATH;	
	String VERSION;
	String OPR_TIME;
	@NotNull(defValue="Y")
	String OPR_TYPE;
	public String getATT_ID() {
		return ATT_ID;
	}
	public String getPRODUCT_ID() {
		return PRODUCT_ID;
	}
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public String getFILE_PATH() {
		return FILE_PATH;
	}
	public String getVERSION() {
		return VERSION;
	}
	public String getOPR_TIME() {
		return OPR_TIME;
	}
	public String getOPR_TYPE() {
		return OPR_TYPE;
	}
	public void setATT_ID(String aTT_ID) {
		ATT_ID = aTT_ID;
	}
	public void setPRODUCT_ID(String pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public void setOPR_TIME(String oPR_TIME) {
		OPR_TIME = oPR_TIME;
	}
	public void setOPR_TYPE(String oPR_TYPE) {
		OPR_TYPE = oPR_TYPE;
	}
	
}
