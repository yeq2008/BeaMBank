package com.bea.mbank.edit.step3;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import xc.android.database.DbQueryManager;
import xc.android.file.XCFile;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCToolkit;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.database.CommittedDataBO;
import com.bea.mbank.R;
import com.bea.mbank.edit.PanelBaseFragment;
import com.bea.mbank.util.FileUtils;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import com.bea.remote.models.grwdy.TakePhotosBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

/**
 * @author fanglinhao
 * 客户经理填写右侧panel
 */
public class Step3PanelFragment extends PanelBaseFragment {
	@ViewInject(R.id.listview)
	ListView listView;
	
	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup group) {
		return inflater.inflate(R.layout.fm_grwdy_step3_panel, null);
	}

	@Override
	protected void onInitContentView(View view) {
		super.onInitContentView(view);
		
		homeBO.isCustManagerInputRequired = true;
		List<String> list = new ArrayList<String>();
		list.add("客户经理填写");
		PreviewAdapter adapter = new PreviewAdapter(list);
		listView.setAdapter(adapter);
	}
	
	//上一步
	@OnClick(R.id.pannelLast)
	public void lastStep(View view){
		popupTopFragmentController();
	}
	
	//提交
	@OnClick(R.id.pannelsubmit)
	public void submitButtonEvent(View view){
		// 检查数据是否填写完毕
		if (checkRequired()) {//全部填写后，可以发送
			submitData2Server(false);
		}
	}
	GrwdyHomeBO sendHomeBO = null;
	protected void submitData2Server(boolean sendData) {
		// 先检查图片，有图片的打成压缩包放到服务器上去
		if (!sendData && savePhotos(-100)) {
			return;
		}

		//数据拷贝
		GrwdyHomeBO tBo = homeBO.clone(true);
		int commId = tBo.isPinAnXinBaoData  ? ConstDefined.PAXB_Command3 : 
			         tBo.isPartRequiredData ? ConstDefined.SendPiMCollection :
			        	                      ConstDefined.SendMData;
		//此放置服务器提交到YRL时失败，服务器将其移到草稿件，此时还可以输入手机验证码重新验证及编辑
		tBo.setIsSMSValid(false);
		tBo.setIsCrmQueryed(homeBO.getIsCrmQueryed());
		tBo.setIsReaded(homeBO.getIsReaded());
		
		//如果个人资料中接收短信，则申请贷款及费用信息中不接收短信
		if(null != tBo.getBorrowerInfor() && null != tBo.getApplyLoanInfor()){
			tBo.getApplyLoanInfor().setIMType("1");
			if(null != tBo.getBorrowerInfor().getMRS() && "1".equals(tBo.getBorrowerInfor().getMRS()))
				tBo.getApplyLoanInfor().setIMType("2");
		}
		Log.i("===提交", "commId="+commId);
		jsonRequest(1234, "通用接口", JsonRemoteBO.reqParam(commId, sendHomeBO=tBo));
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
	
	@Override
	public void onResponsSuccess(int actionTag, Object resData) {
		if (-100 == actionTag) {// 保存图片成功
			TakePhotosBO photos = homeBO.getTakePhotos();
			String remotePath = ((JSONObject) resData).optString("path");
			File zipFile = XCFile.create(zipPath);
			if (zipFile.exists()) {
				// 本地端的压缩包名改成和服务器给的名字一致，这样下次先判断本地有没有，没有再去下载
				String newPath = FileUtils.photoPath(FileUtils.getFileName(remotePath));
				zipFile.renameTo(XCFile.create(newPath));
			}
			photos.setZipFileUrl(remotePath);
			submitData2Server(true);
		} else if (1234 == actionTag) {// 发送数据成功
			BeaAppSettings.updateOriginJSONdata(homeBO);
			XCToolkit.showToast("数据提交成功！");
			sendMessage(ConstDefined.APPHomeChangeActionKey, ConstDefined.BlankActivityTag);
			
			//记录操作类型(全量/批量)
			BeaAppSettings.savePreference("all_part_flag", homeBO.isPartRequiredData?"part":"all");
		} else {
			super.onResponsSuccess(actionTag, resData);
		}
	}
	//保存到本地待发件数据库中
	private void save2LocalCommited() {
		CommittedDataBO tempSaveBO = new CommittedDataBO();
		tempSaveBO.setMainid(sendHomeBO.getMainid());
		tempSaveBO.setListinfo(XCJsonHelper.toJSONString(sendHomeBO));
		tempSaveBO.setCBType(sendHomeBO.isPinAnXinBaoData?"10":"11");

		if (sendHomeBO.isPartRequiredData) {//批量 
			//采集的数据中无此内容
			tempSaveBO.setCAmount(null); 
			tempSaveBO.setCADate(XCToolkit.stringFromDate(new Date(), "yyyy/MM/dd HH:mm:ss"));
			if (null != sendHomeBO.getBasicInfo()) {
				tempSaveBO.setCUNum(sendHomeBO.getBasicInfo().getLCNum());
				tempSaveBO.setCUName(sendHomeBO.getBasicInfo().getLCFName()+sendHomeBO.getBasicInfo().getLCLName());
			}
		} else {//全量
			if (null != sendHomeBO.getBorrowerInfor()) {
				tempSaveBO.setCUName(sendHomeBO.getBorrowerInfor().getMFName()+
						             sendHomeBO.getBorrowerInfor().getMLName());
				tempSaveBO.setCUNum(sendHomeBO.getBorrowerInfor().getMCNum());
			}
			if (null != sendHomeBO.getApplyLoanInfor()) {
				tempSaveBO.setCAmount(sendHomeBO.getApplyLoanInfor().getILMoney());
				tempSaveBO.setCLDate(sendHomeBO.getApplyLoanInfor().getILDate());
			}
			tempSaveBO.setCADate(XCToolkit.stringFromDate(new Date(), "yyyy/MM/dd HH:mm:ss"));
		}
		DbQueryManager.saveOrUpdate(tempSaveBO);
		BeaAppSettings.updateOriginJSONdata(homeBO);
		XCToolkit.showToast("由于网络状态不畅，数据暂时无法提交。此将被放置到“我的任务”－>“待发件”下。");
		sendMessage(ConstDefined.APPHomeChangeActionKey, ConstDefined.BlankActivityTag);
	}
	//网络不通，需要暂存到本地，放在待发件中
	@Override
	public void onNetConnectFailed(int actionTag, String message) {
		if (-100 == actionTag) {// 保存图片失败
			TakePhotosBO photos = sendHomeBO.getTakePhotos();
			photos.setZipFileUrl(zipPath+"@local");
			save2LocalCommited();
		} else if (1234 == actionTag) {//提交数据失败
			save2LocalCommited();
		} else {
			super.onNetConnectFailed(actionTag, message);
		}
	}
	
	@Override
	public void onResponsFailed(int actionTag, String message) {
		if (1234 == actionTag) {// 发送数据成功
			BeaAppSettings.updateOriginJSONdata(homeBO);
			XCToolkit.showToast("数据提交失败，此数据被转到草稿件！");
			sendMessage(ConstDefined.APPHomeChangeActionKey, ConstDefined.BlankActivityTag);
		} else {
			super.onResponsFailed(actionTag, message);
		}
	}
}
