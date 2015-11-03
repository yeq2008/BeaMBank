package com.bea.grouptagpop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.bea.database.codemodel.SYS_AC_ORG;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.grouptagpop.SideBar.OnTouchingLetterChangedListener;
import com.bea.mbank.R;
import com.bea.mbank.widgets.GroupTagViewEx;

public class GroupTagDialog extends Dialog {
	GroupTagViewEx groupTagView;
	public GroupTagDialog(Context context, GroupTagViewEx groupTagView) {
		super(context, R.style.AppTheme_GroupTagDialog);
		this.groupTagView = groupTagView;
		mTags = (List<YRL_BASIC_DATA>)groupTagView.getBtnModels();
	}

	List<YRL_BASIC_DATA> mTags = null;
	public GroupTagDialog(Context context, List<YRL_BASIC_DATA> tags) {
		super(context, R.style.AppTheme_GroupTagDialog);
		this.mTags = tags;
	}
	
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	
	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	
	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pop_grouptag);
		initViews();
	}

	public interface OnSortListItemClickListener {
		public void onItemClicked(int index, Object data);
	}
	private OnSortListItemClickListener itemClickListener;
	public void setOnSortListItemClickListener(OnSortListItemClickListener listener) {
		itemClickListener = listener;
	}
	private void initViews() {
		//实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		sideBar.setVisibility(View.GONE);
		
		//设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			@Override
			public void onTouchingLetterChanged(String s) {
				//该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position);
				}
			}
		});
		
		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//这里要利用adapter.getItem(position)来获取当前position所对应的对象
				int index = ((SortModel)adapter.getItem(position)).getIndex();
				if (null != itemClickListener) {
					itemClickListener.onItemClicked(index, mTags.get(index));
				} 
				//update by flh
				//else 
				if (null != groupTagView) {
					groupTagView.setCurrentIndex(index);
				}
				dismiss();
			}
		});
		
		
		SourceDateList = filledData(mTags);
		
		// 根据a-z进行排序源数据
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(getContext(), SourceDateList);
		sortListView.setAdapter(adapter);
		
		
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		
		//根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}


	/**
	 * 为ListView填充数据
	 * @param datas
	 * @return
	 */
	private List<SortModel> filledData(List<?> datas){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		
		String titleField = groupTagView != null ? groupTagView.getTitleField():null;
		int index = 0;
		Set<String> set = new HashSet<String>(27);
		for (int i = 0; i < datas.size(); ++i) {
			Object o = datas.get(i);
			SortModel sortModel = null;
			try {
				sortModel = new SortModel();
				if (o instanceof YRL_BASIC_DATA) {
					sortModel.setName(((YRL_BASIC_DATA)o).getNAME());
				} else if (o instanceof SYS_AC_ORG) {
					sortModel.setName(((SYS_AC_ORG)o).getORG_NAME());
				} else if (null != titleField) {
					sortModel.setName(groupTagView.getTitle(o, titleField));
				} else {
					sortModel.setName(o.toString());
				}
				if (null == sortModel.getName()) {
					continue;
				}
				sortModel.setIndex(i);
				
				//汉字转换成拼音
				String pinyin = characterParser.getSelling(sortModel.getName());
				String sortString = pinyin.substring(0, 1).toUpperCase();
				
				// 正则表达式，判断首字母是否是英文字母
				if(sortString.matches("[A-Z]")){
					sortModel.setSortLetters(sortString.toUpperCase());
				}else{
					sortModel.setSortLetters("#");
				}
			} catch (Exception e) {
				sortModel = null;
				e.printStackTrace();
			}
			
			if (null != sortModel) {
				set.add(sortModel.getSortLetters());
				mSortList.add(sortModel);
			}
		}

		String[] strArray = new String[set.size()];
		//将集合转换为字符串数组形式
		String[] toArray = set.toArray(strArray);  
		sideBar.setAlphas(toArray);
		
		return mSortList;
		
	}
	
	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<SortModel> filterDateList = new ArrayList<SortModel>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(SortModel sortModel : SourceDateList){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || 
				   characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		
		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
	
	void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null)
			imm.hideSoftInputFromWindow(mClearEditText.getWindowToken(), 0);
	}
	
	@Override
	public void dismiss() {
		hideKeyboard();
		super.dismiss();
	}
}
