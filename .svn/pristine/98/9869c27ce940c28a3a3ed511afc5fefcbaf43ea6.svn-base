/**
 * 
 */
package com.bea.database;

import java.util.ArrayList;
import java.util.List;
import xc.android.database.DbQueryManager;
import xc.android.sqlite.sqlite.Selector;
import xc.android.utils.XCLog;

import com.android.widgets.GroupTagView;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.mbank.widgets.GroupTagViewEx;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * @author cuiyuguo
 * @param <T>
 *
 */
public class DbArrayList extends ArrayList<YRL_BASIC_DATA> implements Runnable{
	private GroupTagView tagView;
	private String titleField;
	public Selector selector = null;
	public DbArrayList(Selector selector) {
		super();
		this.selector = selector;
	}
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 10) {
				refreshTagView();
			}
		}
	};
	
	private void refreshTagView() {
		if (null != tagView) {
			tagView.addButtons(this, titleField);
			if (index > 0) {
				tagView.setCurrentIndex(index);
			} else if (null != indexString) {
				((GroupTagViewEx)tagView).setCurrentIndex(indexString);
			}
			tagView = null;
			titleField = null;
			index = -1;
			indexString = null;
		}
	}
	
	private boolean isInited = false;
	public boolean init(GroupTagView tagView, String titleField) {
		if (!isInited) {
			if ((tagView instanceof GroupTagViewEx)) {
				this.tagView = tagView;
				this.titleField = titleField;
				new Thread(this).start();
			} else {
				queryAndAdd();
			}
			isInited = true;
		}
		
		return isInited;
	}
	
	private boolean isQueryed = false;
	private void queryAndAdd() {
		try {
			List<YRL_BASIC_DATA> list = (List<YRL_BASIC_DATA>) new DbQueryManager().search(selector);
			if (null != list) {
				addAll(list);
			} else {
				XCLog.e("--码表读取错误--", "条件："+ selector.toString());
			}
		} catch (Exception e) {
		}
		isQueryed = true;
	}
	public int index = -1;
	public void setDefaultIndex(int index) {
		this.index = index;
	}
	
	public String indexString = null;
	public void setDefaultIndex(String index) {
		this.indexString = index;
	}
	@Override
	public void run() {
		queryAndAdd();
		handler.sendEmptyMessage(10);
	}
}
