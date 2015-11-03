/**
 * 
 */
package com.bea.remote.models.user;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;
import xc.android.sqlite.annotation.Id;
import xc.android.sqlite.annotation.Table;

/**
 * @author cuiyuguo
 * ��¼�û�Ȩ������ģ��
 */
@JsonModel
@Table(name = "ModuelInfoBO")
public final class ModuelInfoBO implements Serializable {
	//���
	@Id
	String module_id;
	//ģ��˵�����
	String module_name;
	//�˵���ַ
	String url;	
	//����
	String view_order;	
	//�Ƿ���
	int enable;	
	//�ϼ����
	String parent_id;	
	//APP���
	String plant_id;
	//Ȩ�޿�ʼʱ��
	String start_time;
	//Ȩ�޽���ʱ��
	String end_time;
	
	//������ӣ�����ʹ��
	boolean statue = false;
	
	public String getModule_id() {
		return module_id;
	}

	public String getUrl() {
		return url;
	}
	public String getView_order() {
		return view_order;
	}
	public int getEnable() {
		return enable;
	}

	public String getParent_id() {
		return parent_id;
	}
	public String getPlant_id() {
		return plant_id;
	}

	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public void setView_order(String view_order) {
		this.view_order = view_order;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public void setPlant_id(String plant_id) {
		this.plant_id = plant_id;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public boolean isStatue() {
		return statue;
	}

	public void setStatue(boolean statue) {
		this.statue = statue;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
}
