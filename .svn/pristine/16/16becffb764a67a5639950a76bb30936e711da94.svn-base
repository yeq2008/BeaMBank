/**
 * 
 */
package com.bea.mbank.widgets;

import java.util.List;

import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.widgets.GroupTagView;
import com.bea.database.DbArrayList;
import com.bea.database.codemodel.SYS_AC_ORG;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.grouptagpop.GroupTagDialog;
import com.bea.grouptagpop.GroupTagDialog.OnSortListItemClickListener;
import com.bea.mbank.R;

/**
 * @author cuiyuguo
 *
 */
public final class GroupTagViewEx extends GroupTagView {
	public GroupTagViewEx(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mCurrentIndex = isSpinnerShow?-1:0;
	}

	public GroupTagViewEx(Context context, AttributeSet attrs) {
		super(context, attrs);
		mCurrentIndex = isSpinnerShow?-1:0;
	}

	public GroupTagViewEx(Context context) {
		super(context);
		mCurrentIndex = isSpinnerShow?-1:0;
	}

	boolean mUseGroupTagDialog = false;
	public void setUseGroupTagDialogMode(boolean flag) {
		mUseGroupTagDialog = flag;
	}
	
	List<?> mBtnModels = null;
	public List<?> getBtnModels() {
		return mBtnModels;
	}

	String mTitleField = null;
	public String getTitleField() {
		return mTitleField;
	}

	TextView spinner = null;
	String mmindexText = null;
	public void addButtons(List<?> btnModels, String titleField, String indexText) {
		mBtnModels = btnModels; 
		setCurrentIndex(indexText);
		addButtons(btnModels, titleField);
	}
	@Override
	public void addButtons(List<?> btnModels, String titleField) {
		mBtnModels = btnModels;
		mTitleField = titleField;
		if (null == mBtnModels) {
			XCLog.e("=======", "titleField="+titleField);return;
		}
		//为异步加载码表数据添加
		//cuiyuguo add start
		if (null != btnModels && btnModels instanceof DbArrayList &&
			!((DbArrayList)btnModels).init(this, titleField)) {
			return;
		}
		//cuiyuguo add end
		mTitleFontSize = (int)getResources().getDimension(R.dimen.value_font_size);
		if (isSpinnerShow&&null!=btnModels&&btnModels.size()>0) {
			removeAllViewsInLayout();

			spinner = new TextView(getContext());
			spinner.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.value_font_size));
			spinner.setTextColor(0xff535353);
			spinner.setHint(hintText);
			spinner.setPadding(0, 0, 0, 0);
			spinner.setMinimumWidth(100);
			spinner.setGravity(Gravity.CENTER_VERTICAL);
			spinner.setBackgroundResource(R.drawable.spinner_bg_normal);
			addView(spinner);
			XCToolkit.addClickScop(spinner, addtionTouchedSize);
			spinner.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mUseGroupTagDialog) {
						GroupTagSelDialog dialog = new GroupTagSelDialog(GroupTagViewEx.this);
						dialog.setCancelable(true);
						dialog.setCanceledOnTouchOutside(true);
						dialog.setTitle("请选择");
						dialog.show();
					} else {
//						GroupListDialog dialog = new GroupListDialog(GroupTagViewEx.this);
//						dialog.setCancelable(true);
//						dialog.setCanceledOnTouchOutside(true);
//						dialog.setTitle("请选择");
//						dialog.show();
						GroupTagDialog dialog = new GroupTagDialog(getContext(), GroupTagViewEx.this);
						dialog.setOnSortListItemClickListener(new OnSortListItemClickListener() {
							@Override
							public void onItemClicked(int index, Object data) {
								if(null != listDialogListener)
									listDialogListener.click(index);
							}
						});
						dialog.setCancelable(true);
						dialog.setCanceledOnTouchOutside(true);
						dialog.setTitle("请选择");
						dialog.show();
					}
				}
			});
		} else {
			super.addButtons(btnModels, titleField);
		}
	}
	

	
	private GroupListDialogListener listDialogListener;
	public interface GroupListDialogListener{
		public void click(int index);
	}
	public void setGroupListDialogListener(GroupListDialogListener listDialogListener){
		this.listDialogListener = listDialogListener;
	}
	
//	@Override
//	public void addView(View child) {
//		if (mBtnModels instanceof DbArrayList) {
//			super.addView(child);
//		} else {
//			attachViewToParent(child, getChildCount(), null);
//		}
//	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (null != spinner) {
			spinner.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			setMeasuredDimension(Math.max(260, spinner.getMeasuredWidth()), 
					             spinner.getMeasuredHeight()+addtionTouchedSize);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (null != spinner) {
//			spinner.layout(10, addtionTouchedSize/2, r-l-10, b-t-addtionTouchedSize/2);
			spinner.layout(0, addtionTouchedSize/2, r-l, b-t-addtionTouchedSize/2);
			XCToolkit.addClickScop(spinner, addtionTouchedSize);
		} else {
			super.onLayout(changed, l, t, r, b);
		}
	}
	
	@Override
	protected Button insertButton(Object btnModel, String titleField) {
		Button btn = super.insertButton(btnModel, titleField);

		if (null != btn) {
			XCToolkit.addClickScop(btn, addtionTouchedSize);
		}
		return btn;
	}
	@Override
	public void setCurrentIndex(int index) {
		//为异步加载码表数据添加
		//cuiyuguo add start
		if (null != mBtnModels && mBtnModels.size()<=0 && 
			mBtnModels instanceof DbArrayList) {
			((DbArrayList)mBtnModels).setDefaultIndex(index);
			return;
		}
		//cuiyuguo add end
				
		if (null != spinner && null != mBtnModels && index>=0&&index < mBtnModels.size()) {
			mCurrentIndex = index;
			spinner.setTag(mBtnModels.get(index));
			String title = getTitle(mBtnModels.get(index), mTitleField);
			spinner.setText(title);
		} else {
			super.setCurrentIndex(index);
		}
	}
	
	public void setCurrentIndex(String indexText) {
		//为异步加载码表数据添加
		//cuiyuguo add start
		if (null != mBtnModels && mBtnModels.size()<=0 && 
			mBtnModels instanceof DbArrayList) {
			((DbArrayList)mBtnModels).setDefaultIndex(indexText);
			return;
		}
		//cuiyuguo add end
				
		if (null != indexText && null != mBtnModels && mBtnModels.size() > 0) {
			if (mBtnModels.get(0) instanceof YRL_BASIC_DATA) {
				for (int i = 0; i < mBtnModels.size(); ++i) {
					YRL_BASIC_DATA item = ((List<YRL_BASIC_DATA>)mBtnModels).get(i);
					if (null != item && indexText.equals(item.getNO())) {
						setCurrentIndex(i);
						break;
					}
				}
			} else if (mBtnModels.get(0) instanceof SYS_AC_ORG) {
				for (int i = 0; i < mBtnModels.size(); ++i) {
					SYS_AC_ORG item = ((List<SYS_AC_ORG>)mBtnModels).get(i);
					if (null != item && indexText.equals(item.getORG_CODE())) {
						setCurrentIndex(i);
						break;
					}
				}
			} else if (mBtnModels.get(0) instanceof String) {
				for (int i = 0; i < mBtnModels.size(); ++i) {
					String item = ((List<String>)mBtnModels).get(i);
					if (null != item && indexText.equals(item)) {
						setCurrentIndex(i);
						break;
					}
				}
			} else {
				for (int i = 0; i < mBtnModels.size(); ++i) {
					String item = getTitle(mBtnModels.get(i), mTitleField);
					if (null != item && indexText.equals(item)) {
						setCurrentIndex(i);
						break;
					}
				}
			}
		}
	}
	
	@Override
	public void setTagButtonBackground(int resId) {
		if (null != spinner) {
			spinner.setBackgroundResource(resId);
		} else {
			super.setTagButtonBackground(resId);
		}
	}
	
	@Override
	public Object getCurrentValue() {
		Object value = null;
		if (null != spinner && mCurrentIndex>=0 && mCurrentIndex<mBtnModels.size()) {
			value = mBtnModels.get(mCurrentIndex);
		} else {
			value = super.getCurrentValue();
		}
		if (value instanceof YRL_BASIC_DATA) {
			return ((YRL_BASIC_DATA)value).getNO();
		} else if (value instanceof SYS_AC_ORG) {
			return ((SYS_AC_ORG)value).getORG_CODE();
		}else {
			return value;
		}
	}
	
	public String getYRLname() {
		Object value = null;
		if (null != spinner && mCurrentIndex>=0 && mCurrentIndex<mBtnModels.size()) {
			value = mBtnModels.get(mCurrentIndex);
		} else {
			value = super.getCurrentValue();
		}
		if (value instanceof YRL_BASIC_DATA) {
			return ((YRL_BASIC_DATA)value).getNAME();
		} else if (value instanceof SYS_AC_ORG) {
			return ((SYS_AC_ORG)value).getORG_NAME();
		} else {
			return null;
		}
	}
	
	@Override
	public String getTitle(Object btnModel, String titleField) {
		if (btnModel instanceof YRL_BASIC_DATA) {
			return ((YRL_BASIC_DATA)btnModel).getNAME();
		} else if (btnModel instanceof SYS_AC_ORG) {
			return ((SYS_AC_ORG)btnModel).getORG_NAME();
		} else {
			return super.getTitle(btnModel, titleField);
		}
	}
	
	class GroupTagSelDialog extends Dialog {
		GroupTagViewEx groupTagView = null;
		public GroupTagSelDialog(final GroupTagViewEx groupTagView) {
			super(groupTagView.getContext());
			this.groupTagView = groupTagView;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		
			Context context = getContext();
			ScrollView scrollView = new ScrollView(context);
			scrollView.setBackgroundColor(0xffffffff);
			LayoutParams params = new LayoutParams(XCToolkit.dip2px(350), XCToolkit.dip2px(350));
			setContentView(scrollView, params);
			
			LinearLayout layout = new LinearLayout(context);
			params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			scrollView.addView(layout, params);
			
			GroupTagView tagView = new GroupTagView(context);
			tagView.addButtons(groupTagView.mBtnModels, groupTagView.mTitleField);
			layout.addView(tagView);
			tagView.setOnTagButtonSelectedListener(new OnTagButtonSelectedListener() {
				@Override
				public void OnTagButtonSelected(int index, Object btnModel) {
					groupTagView.setCurrentIndex(index);
					dismiss();
				}
			});
		}
	}
	class GroupListDialog extends Dialog {
		GroupTagViewEx groupTagView = null;
		public GroupListDialog(final GroupTagViewEx groupTagView) {
			super(groupTagView.getContext());
			this.groupTagView = groupTagView;
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		
			final int textSize = XCToolkit.px2sp(getResources().getDimension(R.dimen.value_font_size));
			Context context = getContext();
			ListView listView = new ListView(context);
			listView.setCacheColorHint(0);
			listView.setBackgroundColor(0xffffffff);
			listView.setDivider(new ColorDrawable(0xffb6b6b6));
			listView.setDividerHeight(1);
			listView.setAdapter(new BaseAdapter() {
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					if (convertView == null) {
						TextView tView = new TextView(getContext());
						tView.setTextSize(textSize);
						tView.setTextColor(0xff535353);
						tView.setPadding(10, 10, 10, 10);

						convertView = tView;
					}
					TextView textView = (TextView)convertView;
					String title = getTitle(groupTagView.mBtnModels.get(position), groupTagView.mTitleField);
					if (null != title) {
						textView.setText(title);
					}
					if (position == groupTagView.mCurrentIndex) {
						textView.setBackgroundColor(0xffffbe21);
					} else {
						textView.setBackgroundColor(0);
					}
					return convertView;
				}
				
				@Override
				public long getItemId(int position) {
					return 0;
				}
				
				@Override
				public Object getItem(int position) {
					return null;
				}
				
				@Override
				public int getCount() {
					return groupTagView.mBtnModels.size();
				}
			});
			LayoutParams params = new LayoutParams(350, 350);
			setContentView(listView, params);
			listView.setSelection(groupTagView.mCurrentIndex);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					groupTagView.setCurrentIndex(arg2);
					dismiss();
				}
			});
		}
	}
}
