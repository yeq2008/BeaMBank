package com.bea.remote.models.blacklist;

/**
 * ������
 * ������Ϣ
 * @author fanglinhao
 */
public class FSInfo {
	private String TYP_NOTIFICATION_O;
	//��Ϣ����
	//R��ʾRestrictive,�����Ƶģ� 
	//I��ʾInformative���ṩ��Ϣ�ģ�
	private String DAT_NTFMAT_O;//ʧЧ����
	private String DAT_CREATION_O;//��Ч����
	private String USR_CREATION_O;//��Ч����
	private String DAT_LASTMOD_O;//��������
	private String USR_LASTMOD_O;//������
	public String getTYP_NOTIFICATION_O() {
		return TYP_NOTIFICATION_O;
	}
	public void setTYP_NOTIFICATION_O(String tYP_NOTIFICATION_O) {
		TYP_NOTIFICATION_O = tYP_NOTIFICATION_O;
	}
	public String getDAT_NTFMAT_O() {
		return DAT_NTFMAT_O;
	}
	public void setDAT_NTFMAT_O(String dAT_NTFMAT_O) {
		DAT_NTFMAT_O = dAT_NTFMAT_O;
	}
	public String getDAT_CREATION_O() {
		return DAT_CREATION_O;
	}
	public void setDAT_CREATION_O(String dAT_CREATION_O) {
		DAT_CREATION_O = dAT_CREATION_O;
	}
	public String getUSR_CREATION_O() {
		return USR_CREATION_O;
	}
	public void setUSR_CREATION_O(String uSR_CREATION_O) {
		USR_CREATION_O = uSR_CREATION_O;
	}
	public String getDAT_LASTMOD_O() {
		return DAT_LASTMOD_O;
	}
	public void setDAT_LASTMOD_O(String dAT_LASTMOD_O) {
		DAT_LASTMOD_O = dAT_LASTMOD_O;
	}
	public String getUSR_LASTMOD_O() {
		return USR_LASTMOD_O;
	}
	public void setUSR_LASTMOD_O(String uSR_LASTMOD_O) {
		USR_LASTMOD_O = uSR_LASTMOD_O;
	}
}
