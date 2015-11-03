package com.bea.mbank.edit;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import xc.android.application.XCApplication;
import xc.android.file.XCFile;
import xc.android.file.XCFileInputStream;
import xc.android.fragment.XCBaseHttpFragment;
import xc.android.utils.XCBase64;
import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.android.widgets.XCImageButton;
import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.mbank.edit.step1.ApplyLoanInforFragment;
import com.bea.mbank.edit.step1.BasicInfoFragment;
import com.bea.mbank.edit.step1.BorrowerInforFragment;
import com.bea.mbank.edit.step1.ContactInforFragment;
import com.bea.mbank.edit.step1.CreditInforFragment;
import com.bea.mbank.edit.step1.JointInforFragment;
import com.bea.mbank.edit.step1.MateInforFragment;
import com.bea.mbank.edit.step1.ProfessionInforFragment;
import com.bea.mbank.edit.step1.TakePhotosFragment;
import com.bea.mbank.edit.step3.Step3ContentFragment;
import com.bea.mbank.edit.step3.Step3PanelFragment;
import com.bea.mbank.util.FileUtils;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.draft.DraftListBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import com.bea.remote.models.grwdy.TakePhotosBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

/**
 * @author cuiyuguo 
 * 编辑模块右侧控制面板基类：主要想统一处理“重置动作”、“暂存动作”、“新增动作”
 */
public abstract class PanelBaseFragment extends XCBaseHttpFragment {
	@ViewInject(R.id.pannelReset)
	XCImageButton resetButton;
	protected GrwdyHomeBO homeBO = BeaAppSettings.instance().mGrwdyHomeBO;
	@Override
	protected void onInitContentView(View arg0) {
		//第二步第三步不能响应reset事件
		String className = getClass().getName();
		if (className.equals("Step2PanelFragment") ||
			className.equals("Step3PanelFragment")) {
			resetButton.setClickable(false);
		}
	}

	@Override
	public void onDestroy() {
		XCLog.i("=====destroy", getClass().getName());
		super.onDestroy();
	}
	
	// 重新添加
	@OnClick(R.id.pannelAdd)
	public void onPannelAddButtonEvent(View v) {
		Message msg = new Message();
		msg.what = ConstDefined.GRWDYCreditActionTag;
		sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
	}
		
	// 放弃内容，重新编辑
	@OnClick(R.id.pannelReset)
	public void onPannelResetButtonEvent(View v) {
		Message msg = new Message();
		msg.what = ConstDefined.GRWDYCreditActionTag;
		msg.obj = homeBO.originHomeBO;
		sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
	}

	//检查数据是否都填写完毕
	protected boolean checkRequired() {
		XCApplication.postMsg(ConstDefined.SavedataWhileChangeAction, null);
		if (homeBO.getIsPartRequiredData()) {//批量
			if (homeBO.isBasicInfoRequired && !new BasicInfoFragment().
				isRequiredFieldInputed(homeBO.getBasicInfo())) {
				return false;
			}
			if (homeBO.isTakePhotosRequired && !new TakePhotosFragment().
				isRequiredFieldInputed(homeBO.getTakePhotos())) {
				return false;
			}
		} else {//全量
			if (homeBO.isBorrowerInforRequired && !new BorrowerInforFragment().
				isRequiredFieldInputed(homeBO.getBorrowerInfor())) {
				return false;
			}
			if (homeBO.isProfessionInforRequired && !new ProfessionInforFragment().
				isRequiredFieldInputed(homeBO.getProfessionInfor())) {
				return false;
			}
			if (homeBO.isJointInforRequired && !new JointInforFragment().
				isRequiredFieldInputed(homeBO.getJointInfor())) {
				return false;
			}
			if (homeBO.isContactInforRequired && !new ContactInforFragment().
				isRequiredFieldInputed(homeBO.getContactInfor())) {
				return false;
			}
			if (homeBO.isCreditInforRequired && !new CreditInforFragment().
				isRequiredFieldInputed(homeBO.getCreditInfor())) {
				return false;
			}
			if (homeBO.isMateInforRequired && !new MateInforFragment().
				isRequiredFieldInputed(homeBO.getMateInfor())) {
				return false;
			}
			if (homeBO.isApplyLoanInforRequired && !new ApplyLoanInforFragment().
				isRequiredFieldInputed(homeBO.getApplyLoanInfor())) {
				return false;
			}
			if (homeBO.isTakePhotosRequired && !new TakePhotosFragment().
				isRequiredFieldInputed(homeBO.getTakePhotos())) {
				return false;
			}
		}
		//判断客户经理填写的必填项
		if (this instanceof Step3PanelFragment && null != homeBO.getCustManagerInput() && 
			!new Step3ContentFragment().isRequiredFieldInputed(homeBO.getCustManagerInput())) {
			return false;
		}
			
		return true;
	}
	
	// 暂存数据到服务器
	@OnClick(R.id.pannelSave)
	public void onPannelSaveButtonEvent(View v) {
		//检查数据是否填写完毕，如全部填写完毕则暂存到服务器
		XCApplication.postMsg(ConstDefined.SavedataWhileChangeAction, null);
		saveDraftData2Server(false);
	}

	protected String zipPath = null;
	protected boolean savePhotos(int actionTag) {
		// 有拍照信息，先把拍照的照片传到服务器
		TakePhotosBO photos = homeBO.getTakePhotos();
		if (null != photos && photos.getPhotos().size() > 0 && photos.isChanged()) {
			try {
				zipPath = FileUtils.zipPhotos(photos.getPhotos());
				//头里面携带信息，此包为压缩包，如有原来包则将原来包删除，以便于服务器节省硬盘资源
				Map<String, String> header = new HashMap<String, String>();
				header.put("isZipFile", "1");
				if (null != photos.zipFileUrl) {
					header.put("oldpath", XCBase64.encode(photos.zipFileUrl.getBytes()));
				}
				uploadFile(actionTag, "文件上传", XCFileInputStream.create(zipPath),
						  XCFile.create(zipPath).length(), header);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	protected void saveDraftData2Server(boolean saveData) {
		// 先检查图片，有图片的打成压缩包放到服务器上去
		if (!saveData && savePhotos(100)) {
			return;
		}

		GrwdyHomeBO tempBo = homeBO.clone(true);
		DraftListBO tempSaveBO = new DraftListBO();
		tempSaveBO.setMainid(tempBo.getMainid());
		tempSaveBO.setIsCrmQueryed(tempBo.getIsCrmQueryed());
		tempSaveBO.setIsReaded(tempBo.getIsReaded());
		tempSaveBO.setIsFormatErrorFlag("1");
		if(tempBo.getIsSMSValid())
			tempSaveBO.setIsFormatErrorFlag("0");
		tempSaveBO.setListinfo(tempBo);
		
		if (tempBo.isPinAnXinBaoData) {
			tempSaveBO.setCBType("10");// 业务品种
			tempSaveBO.setBusiCode(tempBo.getBusiCode());// 保单编号
			if (null != tempBo.getApplyLoanInfor())
				tempBo.getApplyLoanInfor().setIBType("0205");
		} else {
			if (!tempBo.isPartRequiredData) {//全量
				tempSaveBO.setCBType("12");// 消费贷
				if (null != tempBo.getApplyLoanInfor()) {
					String tmp = tempBo.getApplyLoanInfor().getIBType();
					if (null != tmp && "0206".equals(tmp))
						tempSaveBO.setCBType("11");// 车位贷
				}
			}else {//批量
				tempSaveBO.setCBType("12");// 消费贷
				if (null != tempBo.getBasicInfo()) {
					String tmp = tempBo.getBasicInfo().getLCBType();
					if (null != tmp && "0206".equals(tmp))
						tempSaveBO.setCBType("11");// 车位贷
				}
			}

		}
		
		TakePhotosBO photos = tempBo.getTakePhotos();
		if (null != photos) {
			tempSaveBO.setPhotoZipUrl(photos.zipFileUrl);
		}

		if (tempBo.isPartRequiredData) {//批量 
			//采集的数据中无此内容
			tempSaveBO.setCAmount(null); 
			tempSaveBO.setCADate(XCToolkit.stringFromDate(new Date(), "yyyy/MM/dd HH:mm:ss"));
			if (null != tempBo.getBasicInfo()) {
				tempSaveBO.setCUNum(tempBo.getBasicInfo().getLCNum());
				String Fname = formatString(tempBo.getBasicInfo().getLCFName());
				String Lname= formatString(tempBo.getBasicInfo().getLCLName());
				tempSaveBO.setCUName(Fname+Lname);
			}
		} else {//全量
			if (null != tempBo.getBorrowerInfor()) {
				String Fname = formatString(tempBo.getBorrowerInfor().getMFName());
				String Lname= formatString(tempBo.getBorrowerInfor().getMLName());
			 
				tempSaveBO.setCUName(Fname+Lname);
				tempSaveBO.setCUNum(tempBo.getBorrowerInfor().getMCNum());
			}
			if (null != tempBo.getApplyLoanInfor()) {
				tempSaveBO.setCAmount(tempBo.getApplyLoanInfor().getILMoney());
				tempSaveBO.setCLDate(tempBo.getApplyLoanInfor().getILDate());
			}
			tempSaveBO.setCADate(XCToolkit.stringFromDate(new Date(), "yyyy/MM/dd HH:mm:ss"));
		}
		jsonRequest(101, "通用接口", JsonRemoteBO.
		reqParam(ConstDefined.SaveDraft, tempSaveBO));
	}

	public String formatString(String s){
		if (null == s)
			s = "";
		if("null".equals(s))
			return "";
		
		return s; 
		
	}
	@Override
	public void onResponsSuccess(int actionTag, Object resData) {
		if (100 == actionTag) {// 保存图片成功
			TakePhotosBO photos = homeBO.getTakePhotos();
			String remotePath = ((JSONObject) resData).optString("path");
			//删除上一次的本地zip包文件
			if (null != photos.zipFileUrl && FileUtils.isPhotoZipExist(photos.zipFileUrl)) {
				String filePath = FileUtils.photoPath(photos.zipFileUrl);
				XCFile.create(filePath).delete();
			}
			File zipFile = XCFile.create(zipPath);
			if (zipFile.exists()) {
				// 本地端的压缩包名改成和服务器给的名字一致，这样下次先判断本地有没有，没有再去下载
				String newPath = FileUtils.photoPath(FileUtils.getFileName(remotePath));
				if (zipFile.renameTo(XCFile.create(newPath))) {
				} else {
					zipFile.delete();
				}
			}
			photos.setZipFileUrl(remotePath);
			saveDraftData2Server(true);
		} else if (101 == actionTag) {// 暂存数据成功
			homeBO.setMainid(((JSONObject)resData).optString("mainid"));
			BeaAppSettings.updateOriginJSONdata(homeBO);
			XCToolkit.showToast("暂存成功！");
			
			
		}
	}
	
	//网络不通，需要暂存到本地，放在待发件中
	@Override
	public void onNetConnectFailed(int actionTag, String message) {
		if (100 == actionTag) {// 保存图片失败
			XCFile.create(zipPath).delete();
		}
	}
	
	@Override
	public void onResponsFailed(int actionTag, String message) {
		if (100 == actionTag) {// 保存图片失败
			XCFile.create(zipPath).delete();
		}
	}
}
