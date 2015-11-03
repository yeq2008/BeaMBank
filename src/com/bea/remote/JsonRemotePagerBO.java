/**
 * 
 */
package com.bea.remote;

import java.io.Serializable;

import xc.android.remote.json.JsonModel;



/**
 * @author cuiyuguo
 *
 */
@JsonModel
public class JsonRemotePagerBO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6464291973960405816L;
	
	//当前页码
	int    page;
	//每页纪录条数
	int    pageSize;
	//总页数
	int    totalPage;
	//总条数
	int    totalRow;
		
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
}
