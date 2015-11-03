package com.bea.remote.models.cuishou;

/**
 * 催收 增加信息 客户承诺
 * @author fanglinhao
 */
public class AddCustCsBO {
	private String LoanNo;//借据号
	private String ProsimeAmt;//承诺还款金额
	private String ProsimeDate;//承诺还款日期
	private String InputUser;//录入人
	private String InputOrg;//录入机构
	private String InputDate;//录入日期
	private String UpdateUser;//更新人
	public AddCustCsBO(){}
	public AddCustCsBO(String loanNo, String prosimeAmt, String prosimeDate,
			String inputUser, String inputOrg, String inputDate,
			String updateUser) {
		LoanNo = loanNo;
		ProsimeAmt = prosimeAmt;
		ProsimeDate = prosimeDate;
		InputUser = inputUser;
		InputOrg = inputOrg;
		InputDate = inputDate;
		UpdateUser = updateUser;
	}
	public String getLoanNo() {
		return LoanNo;
	}
	public void setLoanNo(String loanNo) {
		LoanNo = loanNo;
	}
	public String getProsimeAmt() {
		return ProsimeAmt;
	}
	public void setProsimeAmt(String prosimeAmt) {
		ProsimeAmt = prosimeAmt;
	}
	public String getProsimeDate() {
		return ProsimeDate;
	}
	public void setProsimeDate(String prosimeDate) {
		ProsimeDate = prosimeDate;
	}
	public String getInputUser() {
		return InputUser;
	}
	public void setInputUser(String inputUser) {
		InputUser = inputUser;
	}
	public String getInputOrg() {
		return InputOrg;
	}
	public void setInputOrg(String inputOrg) {
		InputOrg = inputOrg;
	}
	public String getInputDate() {
		return InputDate;
	}
	public void setInputDate(String inputDate) {
		InputDate = inputDate;
	}
	public String getUpdateUser() {
		return UpdateUser;
	}
	public void setUpdateUser(String updateUser) {
		UpdateUser = updateUser;
	}
}
