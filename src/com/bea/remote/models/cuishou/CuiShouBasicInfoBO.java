package com.bea.remote.models.cuishou;

import java.io.Serializable;
import java.util.List;

/**
 * @author fanglinhao
 * ���ջ�����Ϣ
 */
public class CuiShouBasicInfoBO implements Serializable {
	private String SerialNo;//��ݱ��
	private String CustomerName;//�ͻ�����
	private String Title;//��ν
	private String BusinessType;//ҵ��Ʒ��
	private String Currency;//�������
	private String BusinessSum;//������
	private String NormalBalance;//�����������
	private String LoanTerm;//��������(����)
	private String PutOutDate;//�ſ�����
	private String MaturityDate;//�������
	private String AcctAmount;//�˻������
	private String OverDueBalance;//�ڹ�Ƿ��
	private String InterestBalance;//�ڹ�ǷϢ
	private String InterestBalance2;//Ӧ��������Ϣ
	private String OdinteBalance;//Ӧ����Ҽ��շ�Ϣ
	private String AllBalance;//����Ӧ�����
	private String ClassifyResultName;//�弶������
	private String LoanRate;//ִ��������
	private String DefaultDueDay;//Ĭ�ϻ�����
	private String NextDueDate;//�´λ�����
	private String ReScheduleFlag;//�Ƿ�Ϊ�������
	private String FirstODDate;//�״���������
	private String TATimes;//�ۼ���������
	private String LCATimes;//������������
	private String OverDueDays;//��ǰ��������
	private String LastSum;//��������
	private String CustomerID;//�ͻ�ID
	private String SerialNoCI;//����ID
	private List<Last3ReturnInfoBO> cs_Payback3_dt;//������ʻ�����Ϣ
	private List<JKRLXInfo> cs_IndTel_dt;//�������ϵ��Ϣ
	private List<CustCLRecord> cs_CustormerPromise_dt;//�ͻ���ŵ��¼
	private List<CuiShouRecordBO> cs_collectionHis_dt;//������ʷ��¼
	public CuiShouBasicInfoBO(){}
	public CuiShouBasicInfoBO(String serialNo, String customerName,
			String title, String businessType, String currency,
			String businessSum, String normalBalance, String loanTerm,
			String putOutDate, String maturityDate, String acctAmount,
			String overDueBalance, String interestBalance,
			String interestBalance2, String odinteBalance, String allBalance,
			String classifyResultName, String loanRate, String defaultDueDay,
			String nextDueDate, String reScheduleFlag, String tATimes,
			String lCATimes, String overDueDays, String lastSum,
			String customerID, String serialNoCI,
			List<Last3ReturnInfoBO> cs_Payback3_dt,
			List<JKRLXInfo> cs_IndTel_dt,
			List<CustCLRecord> cs_CustormerPromise_dt,
			List<CuiShouRecordBO> cs_collectionHis_dt) {
		SerialNo = serialNo;
		CustomerName = customerName;
		Title = title;
		BusinessType = businessType;
		Currency = currency;
		BusinessSum = businessSum;
		NormalBalance = normalBalance;
		LoanTerm = loanTerm;
		PutOutDate = putOutDate;
		MaturityDate = maturityDate;
		AcctAmount = acctAmount;
		OverDueBalance = overDueBalance;
		InterestBalance = interestBalance;
		InterestBalance2 = interestBalance2;
		OdinteBalance = odinteBalance;
		AllBalance = allBalance;
		ClassifyResultName = classifyResultName;
		LoanRate = loanRate;
		DefaultDueDay = defaultDueDay;
		NextDueDate = nextDueDate;
		ReScheduleFlag = reScheduleFlag;
		TATimes = tATimes;
		LCATimes = lCATimes;
		OverDueDays = overDueDays;
		LastSum = lastSum;
		CustomerID = customerID;
		SerialNoCI = serialNoCI;
		this.cs_Payback3_dt = cs_Payback3_dt;
		this.cs_IndTel_dt = cs_IndTel_dt;
		this.cs_CustormerPromise_dt = cs_CustormerPromise_dt;
		this.cs_collectionHis_dt = cs_collectionHis_dt;
	}
	public String getSerialNo() {
		return SerialNo;
	}
	public void setSerialNo(String serialNo) {
		SerialNo = serialNo;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getBusinessType() {
		return BusinessType;
	}
	public void setBusinessType(String businessType) {
		BusinessType = businessType;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getBusinessSum() {
		return BusinessSum;
	}
	public void setBusinessSum(String businessSum) {
		BusinessSum = businessSum;
	}
	public String getNormalBalance() {
		return NormalBalance;
	}
	public void setNormalBalance(String normalBalance) {
		NormalBalance = normalBalance;
	}
	public String getLoanTerm() {
		return LoanTerm;
	}
	public void setLoanTerm(String loanTerm) {
		LoanTerm = loanTerm;
	}
	public String getPutOutDate() {
		return PutOutDate;
	}
	public void setPutOutDate(String putOutDate) {
		PutOutDate = putOutDate;
	}
	public String getMaturityDate() {
		return MaturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		MaturityDate = maturityDate;
	}
	public String getAcctAmount() {
		return AcctAmount;
	}
	public void setAcctAmount(String acctAmount) {
		AcctAmount = acctAmount;
	}
	public String getOverDueBalance() {
		return OverDueBalance;
	}
	public void setOverDueBalance(String overDueBalance) {
		OverDueBalance = overDueBalance;
	}
	public String getInterestBalance() {
		return InterestBalance;
	}
	public void setInterestBalance(String interestBalance) {
		InterestBalance = interestBalance;
	}
	public String getInterestBalance2() {
		return InterestBalance2;
	}
	public void setInterestBalance2(String interestBalance2) {
		InterestBalance2 = interestBalance2;
	}
	public String getOdinteBalance() {
		return OdinteBalance;
	}
	public void setOdinteBalance(String odinteBalance) {
		OdinteBalance = odinteBalance;
	}
	public String getAllBalance() {
		return AllBalance;
	}
	public void setAllBalance(String allBalance) {
		AllBalance = allBalance;
	}
	public String getClassifyResultName() {
		return ClassifyResultName;
	}
	public void setClassifyResultName(String classifyResultName) {
		ClassifyResultName = classifyResultName;
	}
	public String getLoanRate() {
		return LoanRate;
	}
	public void setLoanRate(String loanRate) {
		LoanRate = loanRate;
	}
	public String getDefaultDueDay() {
		return DefaultDueDay;
	}
	public void setDefaultDueDay(String defaultDueDay) {
		DefaultDueDay = defaultDueDay;
	}
	public String getNextDueDate() {
		return NextDueDate;
	}
	public void setNextDueDate(String nextDueDate) {
		NextDueDate = nextDueDate;
	}
	public String getReScheduleFlag() {
		return ReScheduleFlag;
	}
	public void setReScheduleFlag(String reScheduleFlag) {
		ReScheduleFlag = reScheduleFlag;
	}
	public String getFirstODDate() {
		return FirstODDate;
	}
	public void setFirstODDate(String firstODDate) {
		FirstODDate = firstODDate;
	}
	public String getTATimes() {
		return TATimes;
	}
	public void setTATimes(String tATimes) {
		TATimes = tATimes;
	}
	public String getLCATimes() {
		return LCATimes;
	}
	public void setLCATimes(String lCATimes) {
		LCATimes = lCATimes;
	}
	public String getOverDueDays() {
		return OverDueDays;
	}
	public void setOverDueDays(String overDueDays) {
		OverDueDays = overDueDays;
	}
	public String getLastSum() {
		return LastSum;
	}
	public void setLastSum(String lastSum) {
		LastSum = lastSum;
	}
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public String getSerialNoCI() {
		return SerialNoCI;
	}
	public void setSerialNoCI(String serialNoCI) {
		SerialNoCI = serialNoCI;
	}
	public List<Last3ReturnInfoBO> getCs_Payback3_dt() {
		return cs_Payback3_dt;
	}
	public void setCs_Payback3_dt(List<Last3ReturnInfoBO> cs_Payback3_dt) {
		this.cs_Payback3_dt = cs_Payback3_dt;
	}
	public List<JKRLXInfo> getCs_IndTel_dt() {
		return cs_IndTel_dt;
	}
	public void setCs_IndTel_dt(List<JKRLXInfo> cs_IndTel_dt) {
		this.cs_IndTel_dt = cs_IndTel_dt;
	}
	public List<CustCLRecord> getCs_CustormerPromise_dt() {
		return cs_CustormerPromise_dt;
	}
	public void setCs_CustormerPromise_dt(List<CustCLRecord> cs_CustormerPromise_dt) {
		this.cs_CustormerPromise_dt = cs_CustormerPromise_dt;
	}
	public List<CuiShouRecordBO> getCs_collectionHis_dt() {
		return cs_collectionHis_dt;
	}
	public void setCs_collectionHis_dt(List<CuiShouRecordBO> cs_collectionHis_dt) {
		this.cs_collectionHis_dt = cs_collectionHis_dt;
	}
}
