package com.bea.grouptagpop;

public class SortModel {
	
	private String name;   //显示的数据
	private String sortLetters;  //显示数据拼音的首字母
	private int index;  //数据源序号
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

}
