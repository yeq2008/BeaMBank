package com.bea.remote.models.cuishou;

import java.util.List;

/**
 * 催收 增加信息
 * @author fanglinhao
 */
public class RequestCuiShouBo {
	private List<AddCustCsBO> CustormerPromise;//客户承诺记录
	private List<AddRelationTypeBO> UPTEL;//更改联系方式
	private List<AddHisCsBO> collectionHis;//历史催收记录
	
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
