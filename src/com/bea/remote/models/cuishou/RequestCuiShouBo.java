package com.bea.remote.models.cuishou;

import java.util.List;

/**
 * ���� ������Ϣ
 * @author fanglinhao
 */
public class RequestCuiShouBo {
	private List<AddCustCsBO> CustormerPromise;//�ͻ���ŵ��¼
	private List<AddRelationTypeBO> UPTEL;//������ϵ��ʽ
	private List<AddHisCsBO> collectionHis;//��ʷ���ռ�¼
	
	public List<AddCustCsBO> getCustormerPromise() {
		return CustormerPromise;
	}
	public void setCustormerPromise(List<AddCustCsBO> custormerPromise) {
		CustormerPromise = custormerPromise;
	}
	public List<AddRelationTypeBO> getUPTEL() {
		return UPTEL;
	}
	public void setUPTEL(List<AddRelationTypeBO> uPTEL) {
		UPTEL = uPTEL;
	}
	public List<AddHisCsBO> getCollectionHis() {
		return collectionHis;
	}
	public void setCollectionHis(List<AddHisCsBO> collectionHis) {
		this.collectionHis = collectionHis;
	}
}
