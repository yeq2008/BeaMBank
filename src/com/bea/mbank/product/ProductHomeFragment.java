package com.bea.mbank.product;

import java.util.List;

import xc.android.fragment.XCBaseFragment;
import xc.android.remote.json.FileLoader;
import xc.android.utils.XCMD5;
import xc.android.utils.XCToolkit;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bea.application.ConstDefined;
import com.bea.database.DbManager;
import com.bea.database.codemodel.MMP_PRODUCT;
import com.bea.database.codemodel.MMP_PRODUCT_ATT;
import com.bea.database.codemodel.MMP_PRODUCT_CATEGORY;

import com.bea.image.ImageLoader;
import com.bea.mbank.R;
import com.bea.mbank.util.FileUtils;
import com.bea.mbank.widgets.PopdownWindow;
import com.bea.remote.BeaRemoteSettings;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

/**
 * @author 崔玉国
 * 	产品中心界面入口
 */
public class ProductHomeFragment extends XCBaseFragment {
	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_product, null);
	}

	@ViewInject(R.id.title) TextView titleView;
	@ViewInject(R.id.viewPager) ViewPager viewpager;
	@ViewInject(R.id.indictorLayout) LinearLayout layout;
	@ViewInject(R.id.cate_lay) RelativeLayout cate_lay;
	@ViewInject(R.id.catename) TextView cateNameView;
	// 每一页显示的最多产品数目
	private final static float SHOWNUMBER = 8.0f;
	protected MMP_PRODUCT_CATEGORY category;
	protected List<MMP_PRODUCT_CATEGORY> subCategories;
	protected List<MMP_PRODUCT> products;
	@Override
	protected void onInitContentView(View arg0) {
		Bundle bundle = getArguments();
		category = (MMP_PRODUCT_CATEGORY)bundle.getSerializable("category");
		products = DbManager.products(category.getCATEGORY_ID());
		
		subCategories = DbManager.productCategorys(category.getCATEGORY_ID());
		titleView.setText(category.getCATEGORY_NAME());
		
		if (subCategories != null && subCategories.size() > 0) {// 产品小分类的下拉框
			if (null == products || products.size() <= 0) {//产品大类下无产品，展示第一个小类
				category = subCategories.get(0);
				products = DbManager.products(category.getCATEGORY_ID());
			} else {//把大类的添加进来
				subCategories.add(0, category);
			}
			cate_lay.setVisibility(subCategories.size()>1?View.VISIBLE:View.GONE);
		} else {
			cate_lay.setVisibility(View.GONE);
		}
		
		cateNameView.setText(category.getCATEGORY_NAME());
		setViewPagerData();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (null == products || products.size() <= 0) {
			viewpager.setBackgroundResource(R.drawable.nobook);
		} else {
			viewpager.setBackgroundResource(R.color.transparentColor);
		}
	}
	
	private int mCurrentCategory = 0;
	private void reInitByNewCategory(MMP_PRODUCT_CATEGORY newCate, int index) {
		category = newCate;
		mCurrentCategory = index;
		cateNameView.setText(category.getCATEGORY_NAME());
		products = DbManager.products(category.getCATEGORY_ID());
		setViewPagerData();
	}
	
	//分类选取事件
	@OnClick(R.id.catelist)
	public void onCategoryListEvent(View v) {
		final PopdownWindow pop = new PopdownWindow(this, (View)cate_lay.getParent());
		ListView listView = createCategoryListView();
		pop.showWindow(listView, XCToolkit.dip2px(270), XCToolkit.dip2px(350));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				reInitByNewCategory(subCategories.get(arg2), arg2);
				pop.hideWindow();
			}
		});
	}
	
	@OnClick(R.id.cate_lay)
	public void onCategorySelecteEvent(View v) {
		onCategoryListEvent(v);
	}
	
	protected PagerViewAdapter pagerAdapter;
	protected void setViewPagerData() {
		int pageNumber = (int)Math.ceil(products.size()/SHOWNUMBER);
		if (pageNumber > 1) {
			for (int i = 0; i < pageNumber; i++) {
				ImageView indictor = new ImageView(getActivity());
				if (0 == i) {
					indictor.setBackgroundResource(R.drawable.product_card_start);
				} else {
					indictor.setBackgroundResource(R.drawable.product_card_comple);
				}
				
				layout.addView(indictor);
			}
		}
		
		viewpager.setAdapter(pagerAdapter = new PagerViewAdapter());
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			// 当viewpager滑动时候，底部小圆点会切换背景色
			@Override
			public void onPageSelected(int index) {
				for (int i = 0; i < layout.getChildCount(); i++) {
					ImageView indictor = (ImageView) layout.getChildAt(i);
					if (index == i) {
						indictor.setBackgroundResource(R.drawable.product_card_start);
					} else {
						indictor.setBackgroundResource(R.drawable.product_card_comple);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}

			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
	}

	//水平分页显示adapter
	class PagerViewAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return (int)Math.ceil(products.size()/SHOWNUMBER);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			View view = mInflater.inflate(R.layout.v_product_carditem, null);
			GridView gridview = (GridView) view.findViewById(R.id.product_cartitema);
			gridview.setHorizontalSpacing(XCToolkit.dip2px(50));

			int start = (int)(position * SHOWNUMBER);
			gridview.setAdapter(new GridViewAdapter(products.subList(start,
					Math.min((int)SHOWNUMBER + start, products.size()))));
			((ViewPager) container).addView(view);
			return view;
		}
	}

	//一页内产品展示的gridview 适配器
	class GridViewAdapter extends BaseAdapter {
		List<MMP_PRODUCT> products;
		public GridViewAdapter(List<MMP_PRODUCT> products) {
			super();
			this.products = products;
		}

		@Override
		public int getCount() {
			return products.size();
		}

		@Override
		public Object getItem(int position) {
			return products.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		class ViewHolder {
			public ImageView relaview;
			public ProgressBar prob;
			public TextView titlename;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder viewholder = null;
			if (convertView == null) {
				viewholder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.v_product_carditemview, null);
				viewholder.relaview = (ImageView) convertView.findViewById(R.id.product_bg);
				viewholder.prob = (ProgressBar) convertView.findViewById(R.id.down_loadcard);
				viewholder.titlename = (TextView) convertView.findViewById(R.id.product_name);
				convertView.setTag(viewholder);
			} else {
				viewholder = (ViewHolder) convertView.getTag();
			}
			final MMP_PRODUCT pt = products.get(position);
			final ProgressBar progressBar = viewholder.prob;
			viewholder.titlename.setText(pt.getPRODUCT_NAME());
			initImageViewCover(viewholder.relaview, pt);
			viewholder.relaview.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					downloadProductAndShow(v, pt, progressBar);
				}
			});

			return convertView;
		}
		
		//下载产品封面
		private void initImageViewCover(final ImageView img, MMP_PRODUCT pt) {
			if (null == pt.getCOVER_PATH()){
				return;
			}
			final String fileUrl = BeaRemoteSettings.downloadUrl(pt.getCOVER_PATH());
			final String filePath = FileUtils.downloadPath(XCMD5.MD5(fileUrl) + ".jpg");
			FileLoader.download(fileUrl, filePath,
					new FileLoader.FileLoaderHandler() {
						@Override
						public void onSuccess(String arg0) {
							ImageLoader.load(arg0, img, img.getWidth(), img.getHeight());
						}

						@Override
						public void onStart() {}

						@Override
						public void onProgress(int arg0) {}

						@Override
						public void onFinish() {}

						@Override
						public void onFailure(Throwable arg0) {}
					});
		}
		//点击产品后，先下载再展示
		String currentSelectedPrd;
		private void downloadProductAndShow(final View v, final MMP_PRODUCT pt, final ProgressBar progress) {
			List<MMP_PRODUCT_ATT> attchs = DbManager.productATT(pt.getPRODUCT_ID());
			if (null == attchs || attchs.size() <= 0) {
				return;
			}
			MMP_PRODUCT_ATT firstAtt = attchs.get(0);
			if (null == firstAtt || null == firstAtt.getFILE_PATH()) {
				return;
			}
			final String fileUrl = BeaRemoteSettings.downloadUrl(firstAtt.getFILE_PATH());
			String prefix = fileUrl.substring(fileUrl.lastIndexOf("."));
			final String filePath = FileUtils.downloadPath(XCMD5.MD5(fileUrl) + prefix);
			currentSelectedPrd = filePath;v.setClickable(false);
			FileLoader.download(fileUrl, filePath, new FileLoader.FileLoaderHandler() {
				@Override
				public void onSuccess(String arg0) {
					showProduct(arg0, pt);
				}

				@Override
				public void onStart() {
					progress.setProgress(0);
					progress.setVisibility(View.VISIBLE);
				}

				@Override
				public void onProgress(int arg0) {
					progress.setProgress(arg0);
				}

				@Override
				public void onFinish() {
					progress.setProgress(100);
					progress.setVisibility(View.INVISIBLE);
					v.setClickable(true);
				}

				@Override
				public void onFailure(Throwable arg0) {
					v.setClickable(true);
				}
			});
		}
		
		//展示产品信息
		private void showProduct(String filePath, MMP_PRODUCT pt) {
			//可能同时点了多个，都处于下载状态，所以最后一个才能展示
			if (filePath.equals(currentSelectedPrd)) {
				Bundle bundle = new Bundle();
				bundle.putSerializable(ConstDefined.bundleSerializableParamKey, pt);
				bundle.putString("filePath", filePath);
				ProductShowFragment fragment = new ProductShowFragment();
				fragment.setArguments(bundle);
				pushFragmentController(fragment);
			}
		}
	}
	
	private ListView createCategoryListView() {
		final Context context = getActivity();
		final int textSize = XCToolkit.px2sp(getResources().getDimension(R.dimen.crmtitlefont));
		ListView listView = new ListView(context);
		listView.setCacheColorHint(0);
		listView.setBackgroundColor(0xffffffff);
		listView.setDivider(new ColorDrawable(0xffb6b6b6));
		listView.setDividerHeight(1);
		listView.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					TextView tView = new TextView(context);
					tView.setTextSize(textSize);
					tView.setTextColor(0xff535353);
					tView.setPadding(10, 10, 10, 10);

					convertView = tView;
				}
				TextView textView = (TextView)convertView;
				String title = subCategories.get(position).getCATEGORY_NAME();
				if (null != title) {
					textView.setText(title);
				}
				if (position == mCurrentCategory) {
					textView.setBackgroundColor(0xffE7E7E7);
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
				return subCategories.size();
			}
		});
		
		listView.setSelection(mCurrentCategory);
		return listView;
	}
}
