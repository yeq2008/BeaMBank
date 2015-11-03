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
 * �༭ģ���Ҳ���������ࣺ��Ҫ��ͳһ�������ö����������ݴ涯������������������
 */
public abstract class PanelBaseFragment extends XCBaseHttpFragment {
	@ViewInject(R.id.pannelReset)
	XCImageButton resetButton;
	protected GrwdyHomeBO homeBO = BeaAppSettings.instance().mGrwdyHomeBO;
	@Override
	protected void onInitContentView(View arg0) {
		//�ڶ���������������Ӧreset�¼�
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
	
	// �������
	@OnClick(R.id.pannelAdd)
	public void onPannelAddButtonEvent(View v) {
		Message msg = new Message();
		msg.what = ConstDefined.GRWDYCreditActionTag;
		sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
	}
		
	// �������ݣ����±༭
	@OnClick(R.id.pannelReset)
	public void onPannelResetButtonEvent(View v) {
		Message msg = new Message();
		msg.what = ConstDefined.GRWDYCreditActionTag;
		msg.obj = homeBO.originHomeBO;
		sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
	}

	//��������Ƿ���д���
	protected boolean checkRequired() {
		XCApplication.postMsg(ConstDefined.SavedataWhileChangeAction, null);
		if (homeBO.getIsPartRequiredData()) {//����
			if (homeBO.isBasicInfoRequired && !new BasicInfoFragment().
				isRequiredFieldInputed(homeBO.getBasicInfo())) {
				return false;
			}
			if (homeBO.isTakePhotosRequired && !new TakePhotosFragment().
				isRequiredFieldInputed(homeBO.getTakePhotos())) {
				return false;
			}
		} else {//ȫ��
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
		//�жϿͻ�������д�ı�����
		if (this instanceof Step3PanelFragment && null != homeBO.getCustManagerInput() && 
			!new Step3ContentFragment().isRequiredFieldInputed(homeBO.getCustManagerInput())) {
			return false;
		}
			
		return true;
	}
	
	// �ݴ����ݵ�������
	@OnClick(R.id.pannelSave)
	public void onPannelSaveButtonEvent(View v) {
		//��������Ƿ���д��ϣ���ȫ����д������ݴ浽������
		XCApplication.postMsg(ConstDefined.SavedataWhileChangeAction, null);
		saveDraftData2Server(false);
	}

	protected String zipPath = null;
	protected boolean savePhotos(int actionTag) {
		// ��������Ϣ���Ȱ����յ���Ƭ����������
		TakePhotosBO photos = homeBO.getTakePhotos();
		if (null != photos && photos.getPhotos().size() > 0 && photos.isChanged()) {
			try {
				zipPath = FileUtils.zipPhotos(photos.getPhotos());
				//ͷ����Я����Ϣ���˰�Ϊѹ����������ԭ������ԭ����ɾ�����Ա��ڷ�������ʡӲ����Դ
				Map<String, String> header = new HashMap<String, String>();
				header.put("isZipFile", "1");
				if (null != photos.zipFileUrl) {
					header.put("oldpath", XCBase64.encode(photos.zipFileUrl.getBytes()));
				}
				uploadFile(actionTag, "�ļ��ϴ�", XCFileInputStream.create(zipPath),
						  XCFile.create(zipPath).length(), header);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	protected void saveDraftData2Server(boolean saveData) {
		// �ȼ��ͼƬ����ͼƬ�Ĵ��ѹ�����ŵ���������ȥ
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
			tempSaveBO.setCBType("10");// ҵ��Ʒ��
			tempSaveBO.setBusiCode(tempBo.getBusiCode());// �������
			if (null != tempBo.getApplyLoanInfor())
				tempBo.getApplyLoanInfor().setIBType("0205");
		} else {
			if (!tempBo.isPartRequiredData) {//ȫ��
				tempSaveBO.setCBType("12");// ���Ѵ�
				if (null != tempBo.getApplyLoanInfor()) {
					String tmp = tempBo.getApplyLoanInfor().getIBType();
					if (null != tmp && "0206".equals(tmp))
						tempSaveBO.setCBType("11");// ��λ��
				}
			}else {//����
				tempSaveBO.setCBType("12");// ���Ѵ�
				if (null != tempBo.getBasicInfo()) {
					String tmp = tempBo.getBasicInfo().getLCBType();
					if (null != tmp && "0206".equals(tmp))
						tempSaveBO.setCBType("11");// ��λ��
				}
			}

		}
		
		TakePhotosBO photos = tempBo.getTakePhotos();
		if (null != photos) {
			tempSaveBO.setPhotoZipUrl(photos.zipFileUrl);
		}

		if (tempBo.isPartRequiredData) {//���� 
			//�ɼ����������޴�����
			tempSaveBO.setCAmount(null); 
			tempSaveBO.setCADate(XCToolkit.stringFromDate(new Date(), "yyyy/MM/dd HH:mm:ss"));
			if (null != tempBo.getBasicInfo()) {
				tempSaveBO.setCUNum(tempBo.getBasicInfo().getLCNum());
				String Fname = formatString(tempBo.getBasicInfo().getLCFName());
				String Lname= formatString(tempBo.getBasicInfo().getLCLName());
				tempSaveBO.setCUName(Fname+Lname);
			}
		} else {//ȫ��
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
		jsonRequest(101, "ͨ�ýӿ�", JsonRemoteBO.
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
		if (100 == actionTag) {// ����ͼƬ�ɹ�
			TakePhotosBO photos = homeBO.getTakePhotos();
			String remotePath = ((JSONObject) resData).optString("path");
			//ɾ����һ�εı���zip���ļ�
			if (null != photos.zipFileUrl && FileUtils.isPhotoZipExist(photos.zipFileUrl)) {
				String filePath = FileUtils.photoPath(photos.zipFileUrl);
				XCFile.create(filePath).delete();
			}
			File zipFile = XCFile.create(zipPath);
			if (zipFile.exists()) {
				// ���ض˵�ѹ�������ĳɺͷ�������������һ�£������´����жϱ�����û�У�û����ȥ����
				String newPath = FileUtils.photoPath(FileUtils.getFileName(remotePath));
				if (zipFile.renameTo(XCFile.create(newPath))) {
				} else {
					zipFile.delete();
				}
			}
			photos.setZipFileUrl(remotePath);
			saveDraftData2Server(true);
		} else if (101 == actionTag) {// �ݴ����ݳɹ�
			homeBO.setMainid(((JSONObject)resData).optString("mainid"));
			BeaAppSettings.updateOriginJSONdata(homeBO);
			XCToolkit.showToast("�ݴ�ɹ���");
			
			
		}
	}
	
	//���粻ͨ����Ҫ�ݴ浽���أ����ڴ�������
	@Override
	public void onNetConnectFailed(int actionTag, String message) {
		if (100 == actionTag) {// ����ͼƬʧ��
			XCFile.create(zipPath).delete();
		}
	}
	
	@Override
	public void onResponsFailed(int actionTag, String message) {
		if (100 == actionTag) {// ����ͼƬʧ��
			XCFile.create(zipPath).delete();
		}
	}
}
