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
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	
	/**
	 * ����ƴ��������ListView�����������
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
		//ʵ��������תƴ����
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		sideBar.setVisibility(View.GONE);
		
		//�����Ҳഥ������
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			@Override
			public void onTouchingLetterChanged(String s) {
				//����ĸ�״γ��ֵ�λ��
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
				//����Ҫ����adapter.getItem(position)����ȡ��ǰposition����Ӧ�Ķ���
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
		
		// ����a-z��������Դ����
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(getContext(), SourceDateList);
		sortListView.setAdapter(adapter);
		
		
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		
		//�������������ֵ�ĸı�����������
		mClearEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//������������ֵΪ�գ�����Ϊԭ�����б�����Ϊ���������б�
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
	 * ΪListView�������
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
				
				//����ת����ƴ��
				String pinyin = characterParser.getSelling(sortModel.getName());
				String sortString = pinyin.substring(0, 1).toUpperCase();
				
				// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
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
		//������ת��Ϊ�ַ���������ʽ
		String[] toArray = set.toArray(strArray);  
		sideBar.setAlphas(toArray);
		
		return mSortList;
		
	}
	
	/**
	 * ����������е�ֵ���������ݲ�����ListView
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
		
		// ����a-z��������
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
