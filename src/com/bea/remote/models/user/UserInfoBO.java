/**
 * 
 */
package com.bea.remote.models.user;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;

/**
 * @author cuiyuguo
 * ��¼�û���Ϣ
 */
@JsonModel
public final class UserInfoBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7590153777414667637L;
	//���
	String user_id;
	//�û���
	String user_name;		
	//��������
	String real_name;
	//Ա����
	String employee_mark;
	//����id
	String org_id;
	//�绰
	String telephone;	
	//�ֻ�
	String phone;
	//�ʼ�
	String email;
	//�����¼IP
	String latest_ip;	
	//�����¼ʱ��
	String latest_login_time;
	//LANID
	String lan_id;
	//ְλ
	String job;	
	//��ְʱ��
	String entry_date;
	String is_first_login;//�Ƿ��һ�ε�¼ ����	1-��,0-��
	String change_pw_time;//����޸�����ʱ��
	//ְ����������
	String work_line;
	//�Ƿ��ǹ���Ա
	int admin_flag;
	//�Ự��ʶ
	String intserv_token;
	
	public String getUser_id() {
		return user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getReal_name() {
		return real_name;
	}
	public String getEmployee_mark() {
		return employee_mark;
	}
	public String getOrg_id() {
		return org_id;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getLatest_ip() {
		return latest_ip;
	}
	public String getLatest_login_time() {
		return latest_login_time;
	}
	public String getLan_id() {
		return lan_id;
	}
	public String getJob() {
		return job;
	}
	public String getEntry_date() {
		return entry_date;
	}
	public String getWork_line() {
		return work_line;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public void setEmployee_mark(String employee_mark) {
		this.employee_mark = employee_mark;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setLatest_ip(String latest_ip) {
		this.latest_ip = latest_ip;
	}
	public void setLatest_login_time(String latest_login_time) {
		this.latest_login_time = latest_login_time;
	}
	public void setLan_id(String lan_id) {
		this.lan_id = lan_id;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}
	public void setWork_line(String work_line) {
		this.work_line = work_line;
	}
	public String getIntserv_token() {
		return intserv_token;
	}
	public void setIntserv_token(String intserv_token) {
		this.intserv_token = intserv_token;
	}
	public int getAdmin_flag() {
		return admin_flag;
	}
	public void setAdmin_flag(int admin_flag) {
		this.admin_flag = admin_flag;
	}
	public String getIs_first_login() {
		return is_first_login;
	}
	public void setIs_first_login(String is_first_login) {
		this.is_first_login = is_first_login;
	}
	public String getChange_pw_time() {
		return change_pw_time;
	}
	public void setChange_pw_time(String change_pw_time) {
		this.change_pw_time = change_pw_time;
	}	
}
