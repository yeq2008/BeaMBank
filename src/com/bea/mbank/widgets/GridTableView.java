/**
 * 
 */
package com.bea.mbank.widgets;

import java.util.ArrayList;
import java.util.List;
import xc.android.utils.XCToolkit;

import com.android.uitableview.SlideView;
import com.android.uitableview.UITableView;
import com.android.uitableview.UITableViewAdapter;
import com.android.uitableview.ViewHolder;
import com.android.widgets.BottomLineTitleView;
import com.android.widgets.XCImageButton;
import com.bea.mbank.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author cuiyuguo
 *
 */
public class GridTableView extends LinearLayout {
	public GridTableView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initLayout(context, attrs);
	}

	public GridTableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayout(context, attrs);
	}

	public GridTableView(Context context) {
		super(context);
		initLayout(context, null);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	BottomLineTitleView titleView;
	UITableView listView;
	GridTableAdapter adapter;

	private void initLayout(Context context, AttributeSet attrs) {
		if (null == attrs) {
			return;
		}
		View container = LayoutInflater.from(context).inflate(
				R.layout.v_gridtable, null);
		if (null == container) {
			throw new NullPointerException("无法加载v_gridtable.xml文件，请检查资源");
		}
		addView(container);

		titleView = (BottomLineTitleView) container
				.findViewById(R.id.groupTitle);
		listView = (UITableView) container.findViewById(R.id.listView);

		initDefStyle(context, attrs);
		listView.setAdapter(adapter = new GridTableAdapter());
		listView.setDivider(new ColorDrawable(0xffE3E4E5));
		listView.setDividerHeight(1);
		listView.setSelector(null);
		adapter.setUITableView(listView);
	}

	public void deleteRowData(int row) {
		if (row >= 0 && row < list.size()) {
			list.remove(row);
			if (null != adapter) {
				adapter.notifyDataSetChanged();
			}
		}
	}

	CharSequence[] titleStrings = null;

	private void initDefStyle(Context context, AttributeSet attrs) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.GridTableView);
		if (null != typedArray) {
			int count = typedArray.getIndexCount();
			for (int i = 0; i < count; i++) {
				int index = typedArray.getIndex(i);
				if (R.styleable.GridTableView_grt_title == index) {
					String titleText = typedArray.getString(index);
					titleView.setTitleText(titleText);
				} else if (R.styleable.GridTableView_grt_titles == index) {
					titleStrings = typedArray.getTextArray(index);
				} else if (R.styleable.GridTableView_grt_titleFont == index) {
					int titleFont = (int) typedArray.getDimension(index,
							XCToolkit.sp2px(16));
					titleView.setTextSize(titleFont);
				}
			}
		}
		typedArray.recycle();
	}

	public void setGridTableTitles(List<CharSequence> titles) {
		CharSequence[] strArray = new CharSequence[titles.size()];
		for (int i = 0; i < titles.size(); ++i) {
			strArray[i] = titles.get(i);
		}
		titleStrings = strArray;
		if (null != adapter) {
			adapter.notifyDataSetChanged();
		}
	}

	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	boolean isBeginTransaction = false;

	public void beginTransaction() {
		isBeginTransaction = true;
	}

	public void endTransaction() {
		isBeginTransaction = false;
		if (null != adapter) {
			adapter.notifyDataSetChanged();
		}
	}

	public void setGridTitles(int row, String... args) {
		if (!isBeginTransaction) {
			return;
		}
		for (int i = 0; i < args.length; i++) {
			setGridTitle(row, i, args[i]);
		}
	}

	public void setGridTitle(int row, int column, String title) {
		if (!isBeginTransaction) {
			return;
		}
		if (list.size() > row) {
			ArrayList<String> rowItem = list.get(row);
			if (rowItem.size() > column) {
				rowItem.set(column, title);
			} else {
				for (int i = rowItem.size(); i < column + 1; i++) {
					if (i == column) {
						rowItem.add(title);
					} else {
						rowItem.add("");
					}
				}
			}
		} else {
			for (int i = list.size() - 1; i < row; i++) {
				list.add(new ArrayList<String>());
			}
			setGridTitle(row, column, title);
		}
	}

	class GridTableAdapter extends UITableViewAdapter {
		@Override
		public boolean hasSectionTitle(int section) {
			return null != titleStrings && titleStrings.length > 0;
		}

		@Override
		public int rowsInSection(int section) {
			return list != null && hasSectionTitle(section) ? list.size() : 0;
		}

		@Override
		public int viewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(IndexPath path) {
			if (path.row < 0) {
				return 0;
			} else {
				return 1;
			}
		}

		private View createGridRowHeader() {
			LinearLayout root = new LinearLayout(getContext());
			root.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			root.setBackgroundColor(0xffEEEEEE);
			root.setId(-100);
			root.setMinimumHeight((int) (XCToolkit.getScreenHeight() * 0.05f));
			LayoutParams params = null;
			int index = 1;
			if (null != titleStrings && titleStrings.length > 0) {
				for (CharSequence title : titleStrings) {
					String[] ts = title.toString().split("@");
					TextView tv = new TextView(getContext());
					tv.setId(index);
					index++;
					if (ts.length > 0) {
						tv.setText(ts[0]);
					}
					tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources()
							.getDimension(R.dimen.title_font_size));
					tv.setTextColor(0xff000000);
					tv.setLines(1);
					tv.setGravity(Gravity.CENTER_VERTICAL);
					// int width = ts.length > 1 && null != ts[1] ?
					// XCToolkit.dip2px(Integer.valueOf(ts[1]))
					// : android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
					int width = ts.length > 1 && null != ts[1] ? (int) (XCToolkit
							.getScreenWidth() * ((Integer.valueOf(ts[1])) / 1280f))
							: android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
					params = new LayoutParams(width,
							android.view.ViewGroup.LayoutParams.MATCH_PARENT);
					params.leftMargin = XCToolkit.dip2px(10);
					params.gravity = Gravity.CENTER_VERTICAL;
					root.addView(tv, params);
				}
			}
			XCImageButton addButton = new XCImageButton(getContext());
			params = new LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			params.leftMargin = 60;
			params.topMargin = (int) (XCToolkit.getScreenWidth() * 0.0125f);
			params.bottomMargin = (int) (XCToolkit.getScreenWidth() * 0.0125f);
			addButton.setBackgroundResource(R.drawable.pannel_btn_zhengjia);
			root.addView(addButton, params);
			addButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (null != mActionListener) {
						mActionListener.onAddButtonEvent();
					}
				}
			});

			return root;
		}

		private View createGridRowItem() {
			LinearLayout root = new LinearLayout(getContext());
			root.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			root.setBackgroundColor(0xffFDFDFD);
			root.setId(-100);
			root.setMinimumHeight((int) (XCToolkit.getScreenWidth() * 0.05f));
			LayoutParams params = null;
			int index = 1;
			if (null != titleStrings && titleStrings.length > 0) {
				for (CharSequence title : titleStrings) {
					String[] ts = title.toString().split("@");
					TextView tv = new TextView(getContext());
					tv.setId(index);
					index++;
					tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources()
							.getDimension(R.dimen.value_font_size));
					tv.setLines(1);
					tv.setGravity(Gravity.CENTER_VERTICAL);
					tv.setTextColor(0xff535353);
					int width = ts.length > 1 && null != ts[1] ? (int) (XCToolkit
							.getScreenWidth() * ((Integer.valueOf(ts[1])) / 1280f))
							: android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
					params = new LayoutParams(width,
							android.view.ViewGroup.LayoutParams.MATCH_PARENT);
					params.leftMargin = XCToolkit.dip2px(10);
					params.gravity = Gravity.CENTER_VERTICAL;
					root.addView(tv, params);
				}
			}
			return root;
		}

		@Override
		public View viewWithType(int type) {
			if (0 == type) {
				return createGridRowHeader();
			} else {
				return createGridRowItem();
			}
		}

		@Override
		public void initViewHolder(int type, View itemView,
				ViewHolder viewHolder) {
			viewHolder.holderView(itemView);
		}

		@Override
		public void initViewForRowAtIndexPath(IndexPath path, int type,
				ViewHolder viewHolder) {
			List<String> rowList = list.get(path.row);
			LinearLayout root = (LinearLayout) viewHolder.findViewById(-100);

			if (null != titleStrings && titleStrings.length > 0) {
				for (int i = 0; i < titleStrings.length; i++) {
					TextView item = (TextView) root.findViewById(i + 1);
					if (null != rowList && rowList.size() > i) {
						item.setText(rowList.get(i));
					} else {
						item.setText("");
					}
				}
			}
		}

		@Override
		public boolean isNoneSelected(IndexPath path) {
			return path.row < 0;
		}

		@Override
		public void itemSelectedAtIndexPath(IndexPath path) {
		}

		@Override
		protected int isSlideToDoSuport(IndexPath path) {
			return R.layout.v_todo_gridtable;
		}

		@Override
		protected void initSlideToDoEvent(final View todoView) {
			todoView.findViewById(R.id.deleteAction).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							final IndexPath path = (IndexPath) todoView
									.getTag(UITableViewAdapter.SLIDEVIEWTag);
							if (null != mActionListener) {
								((SlideView) todoView.getParent()).shrink();
								mActionListener.onDeleteButtonEvent(path.row);
							}
						}
					});
			todoView.findViewById(R.id.modifyAction).setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							final IndexPath path = (IndexPath) todoView
									.getTag(UITableViewAdapter.SLIDEVIEWTag);
							if (null != mActionListener) {
								((SlideView) todoView.getParent()).shrink();
								mActionListener.onModifyButtonEvent(path.row);
							}
						}
					});
		}
	}

	public interface GridTableViewActionListener {
		public void onAddButtonEvent();

		public void onDeleteButtonEvent(int row);

		public void onModifyButtonEvent(int row);
	}

	GridTableViewActionListener mActionListener = null;

	public void setGridTableViewActionListener(
			GridTableViewActionListener listener) {
		mActionListener = listener;
	}
}
