package com.bea.mbank.edit.step2;

import java.util.ArrayList;
import java.util.List;

import xc.android.utils.XCToolkit;

import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.mbank.edit.PanelBaseFragment;
import com.bea.mbank.edit.step3.Step3HomeFragment;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author fanglinhao
 *	个人无抵押预览
 */
public final class Step2PanelFragment extends PanelBaseFragment {
	@ViewInject(R.id.listview)
	ListView listView;
	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_grwdy_step2_panel, null);
	}
	
	@Override
	protected void onInitContentView(View view) {
		super.onInitContentView(view);
		observeMessage(ConstDefined.smsedHiddenBack);
		setRequiredData(homeBO.isPartRequiredData);

		refreshPanelLastBtnStatue();
		List<String> list = new ArrayList<String>();
		if(homeBO.isBorrowerInforRequired)
			list.add("借款人个人资料");
		if(homeBO.isProfessionInforRequired)
			list.add("借款人职业信息");
		if(homeBO.isCreditInforRequired)
			list.add("借款人资产资料");
		if(homeBO.isJointInforRequired)
			list.add("共同借款人个人资料");
		if(homeBO.isContactInforRequired)
			list.add("联系人信息");
		if (homeBO.isMateInforRequired)
			list.add("借款人配偶资料");
		if (homeBO.isApplyLoanInforRequired)
			list.add("申请贷款及费用信息");
		if(homeBO.isBasicInfoRequired)
			list.add("个人基本资料");
		if(homeBO.isTakePhotosRequired)
			list.add("影像资料");
		PreviewAdapter adapter = new PreviewAdapter(list);
		listView.setAdapter(adapter);
	}
	
	@Override
	public void onReceiveMessage(String msgkey, Object msgObject) {
		refreshPanelLastBtnStatue();
	}

	public void refreshPanelLastBtnStatue() {
		if (!homeBO.getIsPartRequiredData() && homeBO.getIsSMSValid()) {
			pannelLast.setVisibility(View.INVISIBLE);
		} else {
			pannelLast.setVisibility(View.VISIBLE);
		}
	}
	
	private void setRequiredData(boolean requiredData) {
		homeBO.isPartRequiredData = requiredData;
		if (!requiredData) {
			homeBO.isBorrowerInforRequired = true;
			homeBO.isProfessionInforRequired = true;
			homeBO.isContactInforRequired = true;
			homeBO.isApplyLoanInforRequired = true;
			homeBO.isTakePhotosRequired = true;
			homeBO.isBasicInfoRequired = false;
		} else {
			homeBO.isBasicInfoRequired = true;
			homeBO.isTakePhotosRequired = true;
			
			homeBO.isBorrowerInforRequired = false;
			homeBO.isProfessionInforRequired = false;
			homeBO.isContactInforRequired = false;
			homeBO.isApplyLoanInforRequired = false;
			homeBO.isCreditInforRequired = false;
			homeBO.isJointInforRequired = false;
			homeBO.isMateInforRequired = false;
		}
	}
	
	/**
	 * 上一步
	 */
	@ViewInject(R.id.pannelLast) 
	View pannelLast;
	@OnClick(R.id.pannelLast)
	public void lastStep(View view){
//		BeaAppSettings.setOriginData(null);
//		homeBO.setIsSMSValid(false);
//		Message msg = new Message();
//		msg.what = ConstDefined.GRWDYCreditActionTag;
//		msg.obj = homeBO;
//		sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
		popupTopFragmentController();
	}
	
	/**
	 * 下一步
	 */
	@OnClick(R.id.pannelNext)
	public void nextStep(View view){
		//没有经过短信认证，不能进入第三步
		if(!BeaAppSettings.instance().mGrwdyHomeBO.isPartRequiredData){//全量才需要签名和短信验证
			if (!homeBO.getIsSMSValid() && !homeBO.getIsPartRequiredData()) {
				XCToolkit.showToast("请客户签名并短信验证后才可进入下一步！");
				return;
			}
		}
		if(null != homeBO.getIsReaded() && "2".equals(homeBO.getIsReaded())){
			pushFragmentController(new Step3HomeFragment());
		}else{
			XCToolkit.showToast("请先阅读章程");
		}
	}
	
	/**
	 * 个人无抵押预览
	 * 
	 * @author fanglinhao
	 */
	class PreviewAdapter extends BaseAdapter {
		List<String> list;

		public PreviewAdapter(List<String> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Previewholder previewholder;
			if (null == convertView) {
				previewholder = new Previewholder();
				convertView = mInflater.inflate(R.layout.v_grwdy_preview_item,
						null);
				previewholder.textView = (TextView) convertView
						.findViewById(R.id.vgpi_tv01);
				convertView.setTag(previewholder);
			} else {
				previewholder = (Previewholder) convertView.getTag();
			}
			previewholder.textView.setText(list.get(position));
			return convertView;
		}
	}

	/**
	 * 个人无抵押预览
	 * 
	 * @author fanglinhao
	 */
	class Previewholder {
		TextView textView;
	}
}
