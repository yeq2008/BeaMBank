/**
 * 
 */
package com.bea.mbank.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import com.android.widgets.XCImageButton;
import com.bea.application.ConstDefined;
import com.bea.database.DbManager;
import com.bea.database.codemodel.MMP_PRODUCT;
import com.bea.database.codemodel.MMP_PRODUCT_ATT;
import com.bea.mbank.R;
import com.bea.mbank.util.FileUtils;
import com.bea.mbank.widgets.PopdownWindow;
import com.bea.remote.BeaRemoteSettings;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;
import com.macro.office.ExcelReaderFragment;
import com.macro.office.WordReaderFragment;
import com.xinchen.FileShowIntent;
import com.artifex.mupdf.MuPDFFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import xc.android.database.DbQueryManager;
import xc.android.file.XCFile;
import xc.android.file.XCFileInputStream;
import xc.android.fragment.XCBaseFragment;
import xc.android.remote.json.FileLoader;
import xc.android.utils.XCMD5;
import xc.android.utils.XCToolkit;

/**
 * @author cuiyuguo
 *	产品展示页面
 */
public class ProductShowFragment extends XCBaseFragment {
	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_product_show, null);
	}

	MMP_PRODUCT product;
	List<MMP_PRODUCT_ATT> attchs;
	MMP_PRODUCT_ATT mCurrentAttch;
	@ViewInject(R.id.title)TextView titleView;
	@ViewInject(R.id.saveColect)XCImageButton saveCollect;
	@ViewInject(R.id.attaches)XCImageButton attaches;
	@ViewInject(R.id.downProgress)ProgressBar downProgress;
	@Override
	protected void onInitContentView(View arg0) {
		Bundle bundle = getArguments();
		product = (MMP_PRODUCT)bundle.getSerializable(ConstDefined.bundleSerializableParamKey);
		attchs = DbManager.productATT(product.getPRODUCT_ID());
		mCurrentAttch = attchs.get(0);
		if (null != attchs && attchs.size() > 1) {
			attaches.setVisibility(View.VISIBLE);
		} else {
			attaches.setVisibility(View.GONE);
		}
		
		titleView.setText(product.getPRODUCT_NAME()+"（"+mCurrentAttch.getFILE_NAME()+"）");
		if (product.getCOLLECT_FLAG() == 0) {//没有被收藏状态
			saveCollect.setBackgroundResource(R.drawable.fm_product_collect_half);
		} else {
			saveCollect.setBackgroundResource(R.drawable.fm_product_collect_full);
		}
		showProductAttach(bundle.getString("filePath"));
	}

	@Override
	public void onDestroy() {
		if (null != file && file.exists()) {
			file.delete();file=null;
		}
		super.onDestroy();
	}
	File file = null;
	private void showProductAttach(String filePath) {
		if (null == filePath) {
			return;
		}
		
		String productFile = filePath;
		//是mdm加密文件，此时要转化成为非加密文件进行展示
		if (XCFile.isUseMdmSvn()&&XCFile.create(filePath).exists()) {
			try {
				file = new File(productFile=FileUtils.productTempPath(filePath));
				FileOutputStream outStream = new FileOutputStream(file);
				FileInputStream inStream = XCFileInputStream.create(filePath);
				byte[] b = new byte[10240];
				int len = 0;

				while ((len = inStream.read(b, 0, 10240)) != -1) {
					outStream.write(b, 0, len);
				}
				inStream.close();
				outStream.close();
			} catch (Exception e) {
				if (null != file && file.exists()) {
					file.delete();file=null;
				}
				e.printStackTrace();
				return;
			}
		}
		
		if (productFile.endsWith(".pdf")) {
			replaceFragmentController(R.id.pdfView, new MuPDFFragment(productFile));
		} else if (productFile.endsWith(".doc")) {
			Intent intent = FileShowIntent.getWordFileIntent(productFile);
			startActivity(intent);
		} else if (productFile.endsWith(".xls")) {
			Intent intent = FileShowIntent.getExcelFileIntent(productFile);
			startActivity(intent);
		} else if (productFile.endsWith(".ppt")) {
			Intent intent = FileShowIntent.getPptFileIntent(productFile);
			startActivity(intent);
		} else if (productFile.endsWith(".bmp")||productFile.endsWith(".jpg")||productFile.endsWith(".png")) {
			Intent intent = FileShowIntent.getImageFileIntent(productFile);
			startActivity(intent);
		} else {
			Intent intent = FileShowIntent.getHtmlFileIntent(productFile);
			startActivity(intent);
		}
	}
	//点击收藏按钮响应事件
	@OnClick(R.id.saveColect)
	public void onSaveCollectionBtnEvent(View v) {
		int oldFlag = product.getCOLLECT_FLAG();
		product.setCOLLECT_FLAG((oldFlag+1)%2);

		if (DbQueryManager.saveOrUpdate(product)) {
			if (product.getCOLLECT_FLAG() == 0) {//没有被收藏状态
				saveCollect.setBackgroundResource(R.drawable.fm_product_collect_half);
				XCToolkit.showToast("取消精选成功！");
			} else {
				saveCollect.setBackgroundResource(R.drawable.fm_product_collect_full);
				XCToolkit.showToast("收藏精选成功！");
			}
		} else {
			product.setCOLLECT_FLAG(oldFlag);
			if (oldFlag == 0) {//没有被收藏状态
				XCToolkit.showToast("收藏精选失败！");
			} else {
				XCToolkit.showToast("取消精选失败！");
			}
		}
	}
	
	//点击返回按钮响应事件
	@OnClick(R.id.goBackBtn)
	public void onGoBackBtnEvent(View v) {
		popupTopFragmentController();
	}
	
	//分类选取事件
	@OnClick(R.id.attaches)
	public void onAttachesListEvent(View v) {
		final PopdownWindow pop = new PopdownWindow(this, (View)v.getParent());
		ListView listView = createAttachesListView();
		pop.showWindow(listView, XCToolkit.dip2px(270), XCToolkit.dip2px(350));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				reInitByNewAttache(attchs.get(arg2), arg2);
				pop.hideWindow();
			}
		});
	}
	
	int mCurrentAttach = 0;
	private void reInitByNewAttache(MMP_PRODUCT_ATT newAttach, int index) {
		mCurrentAttch = newAttach;
		if (null == mCurrentAttch || null == mCurrentAttch.getFILE_PATH()) {
			return;
		}
		mCurrentAttach = index;
		titleView.setText(product.getPRODUCT_NAME()+"（"+mCurrentAttch.getFILE_NAME()+"）");
		final String fileUrl = BeaRemoteSettings.downloadUrl(mCurrentAttch.getFILE_PATH());
		String prefix = fileUrl.substring(fileUrl.lastIndexOf("."));
		final String filePath = FileUtils.downloadPath(XCMD5.MD5(fileUrl) + prefix);

		FileLoader.download(fileUrl, filePath, new FileLoader.FileLoaderHandler() {
			@Override
			public void onSuccess(String arg0) {
				showProductAttach(arg0);
			}

			@Override
			public void onStart() {
				downProgress.setProgress(0);
				downProgress.setVisibility(View.VISIBLE);
			}

			@Override
			public void onProgress(int arg0) {
				downProgress.setProgress(arg0);
			}

			@Override
			public void onFinish() {
				downProgress.setProgress(100);
				downProgress.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}
	
	private ListView createAttachesListView() {
		final Context context = getActivity();
		final int textSize = XCToolkit.px2sp(getResources().getDimension(R.dimen.value_font_size));
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
					tView.setBackgroundResource(R.drawable.fm_grwdy_pannal_itembg);

					convertView = tView;
				}
				TextView textView = (TextView)convertView;
				String title = attchs.get(position).getFILE_NAME();
				if (null != title) {
					textView.setText(title);
				}
				if (position == mCurrentAttach) {
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
				return attchs.size();
			}
		});
		
		listView.setSelection(mCurrentAttach);
		return listView;
	}
}
