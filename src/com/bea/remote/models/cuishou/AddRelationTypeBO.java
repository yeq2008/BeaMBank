package com.bea.remote.models.cuishou;

/**
 * ���� ������Ϣ ��ϵ��ʽ
 * @author fanglinhao
 */
public class AddRelationTypeBO {
	private String LoanNo;//��ݺ�
	private String Title;//��ν
	private String FullName;//��ϵ������
	private String Relationship;//�����˹�ϵ
	private String PhoneNo;//�̶��绰
	private String MobileNo;//�ƶ��绰
	private String Address;//��ϵ��ַ
	private String Status;//״̬
	private String InputUser;//������
	private String InputDate;//����ʱ��
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getLoanNo() {
		return LoanNo;
	}
	public void setLoanNo(String loanNo) {
		LoanNo = loanNo;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getRelationship() {
		return Relationship;
	}
	public void setRelationship(String relationship) {
		Relationship = relationship;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getInputUser() {
		return InputUser;
	}
	public void setInputUser(String inputUser) {
		InputUser = inputUser;
	}
	public String getInputDate() {
		return InputDate;
	}
	public void setInputDate(String inputDate) {
		InputDate = inputDate;
	}
}
