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
 * 登录用户权限数据模型
 */
@JsonModel
@Table(name = "ModuelInfoBO")
public final class ModuelInfoBO implements Serializable {
	//编号
	@Id
	String module_id;
	//模块菜单名称
	String module_name;
	//菜单地址
	String url;	
	//排序
	String view_order;	
	//是否开启
	int enable;	
	//上级编号
	String parent_id;	
	//APP编号
	String plant_id;
	//权限开始时间
	String start_time;
	//权限结束时间
	String end_time;
	
	//本地添加，本地使用
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
