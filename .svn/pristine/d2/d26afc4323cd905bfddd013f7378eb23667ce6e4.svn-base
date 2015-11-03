package com.bea.mbank.edit.step1;

import xc.android.utils.XCToolkit;

import com.bea.application.ConstDefined;
import com.bea.database.DbManager;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.mbank.util.SubHelperUtil;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.android.widgets.TitleValueView;
import com.bea.mbank.R;
import com.bea.remote.models.grwdy.ContactInforBO;
import com.cyg.viewinject.annotation.ViewInject;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author LSQ 联系人信息
 */
public class ContactInforFragment extends ContentBaseFragment<ContactInforBO> {
	@Override
	protected Class getInfoClass() {
		return ContactInforBO.class;
	}
	
	private static final String tableName = "联系人信息";

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater.inflate(R.layout.fm_grwdy_step1_content_contactinfo,
				null);
	}

	// 关系类型
	@ViewInject(R.id.Contact_gtvNexusType)
	GroupTagViewEx NexusType;
	// 中文姓
	@ViewInject(R.id.Contact_fName)
	TitleValueView fName;
	// 中文名
	@ViewInject(R.id.Contact_lName)
	TitleValueView lName;
	// 英文姓
	@ViewInject(R.id.Contact_efName)
	TitleValueView efName;
	// 英文名
	@ViewInject(R.id.Contact_elName)
	TitleValueView elName;
//	// 证件类型
//	@ViewInject(R.id.Contact_gtvDocumentType)
//	GroupTagViewEx DocumentType;
//	// 发证国家
//	@ViewInject(R.id.Contact_gtvIssuing)
//	GroupTagViewEx Issuing;
//	// 证件号码
//	@ViewInject(R.id.Contact_DocumentNumber)
//	TitleValueView DocumentNumber;
	// 国际区号
	@ViewInject(R.id.Contact_InterCode)
	TitleValueView InterCode;
	// 电话号码
	@ViewInject(R.id.Contact_PhoneNumber)
	TitleValueView PhoneNumber;

	@Override
	protected void onInitContentView(View rootView, ContactInforBO info) {
		initGtv(info);
	}

	/**
	 * 初始化数据
	 */
	private void initGtv(ContactInforBO info) {
		NexusType.addButtons(DbManager.codeDatas("RelationShip"), "NAME",info.getLRS());
//		DocumentType.addButtons(DbManager.codeDatas("CertType"), "NAME",info.getLCType());
//		Issuing.addButtons(DbManager.codeDatas("CountryCode"), "NAME",info.getLCC());
		if (info != null) {
			fName.setValueText(info.getLFName());
			lName.setValueText(info.getLLName());
			efName.setValueText(info.getLEFName());
			elName.setValueText(info.getLELName());
//			DocumentNumber.setValueText(info.getLCNum());
			InterCode.setValueText(info.getLIACode());
			PhoneNumber.setValueText(info.getLTNum());
		}
		 setTitleColor();
	}

	private void setTitleColor() {  
		// 证件类型 
		TextView TvDocumentType = (TextView) findViewById(R.id.Contact_DocumentType);
		// 发证国家 
		TextView TvIssuing = (TextView) findViewById(R.id.Contact_Issuing);
		if (homeBO.isPinAnXinBaoData) {
			TvDocumentType.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvIssuing.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			
		}
	}

	/**
	 * 页面销毁时将数据保存到数据模型里
	 */
	@Override
	public ContactInforBO getValueFromInterfaceView() {
		ContactInforBO inputBO = new ContactInforBO();
		inputBO.setLRS((String) NexusType.getCurrentValue());
//		inputBO.setLCType((String) DocumentType.getCurrentValue());
//		inputBO.setLCC((String) Issuing.getCurrentValue());
		inputBO.setLTType("1");
		inputBO.setLRSName(NexusType.getYRLname());
//		inputBO.setLCTypeName(DocumentType.getYRLname());
		inputBO.setLFName(fName.getValueText());
		inputBO.setLLName(lName.getValueText());
		inputBO.setLEFName(efName.getValueText());
		inputBO.setLELName(elName.getValueText());
//		inputBO.setLCNum(DocumentNumber.getValueText());
		inputBO.setLIACode(InterCode.getValueText());
		inputBO.setLTNum(PhoneNumber.getValueText());
		inputBO.setLIACode(InterCode.getValueText());

		return inputBO;
	}

	@Override
	public boolean isRequiredFieldInputed(ContactInforBO info) {
		SubHelperUtil.setTableName(tableName);
		if (null == info) {
			XCToolkit.showToast("请填写“联系人信息”");
			return false;
		} else {
			if (!SubHelperUtil.showToast(info.getLRS(), "关系类型")) {
				return false;
			}
			if (!SubHelperUtil.showCHToast(info.getLFName(), "中文姓")) {
				return false;
			}
			if (!SubHelperUtil.showCHToast(info.getLFName(), "中文名")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getLEFName(), "英文姓")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getLELName(), "英文名")) {
				return false;
			}
//			if (!SubHelperUtil.showToast(info.getLCType(), "证件类型")) {
//				return false;
//			}
//			if (!SubHelperUtil.showToast(info.getLCC(), "发证国家")) {
//				return false;
//			}
//			if (!"".equals(info.getLCType()) && info.getLCType().toString().equals("Ind1010")) {
//				if (!SubHelperUtil.showIDToast(info.getLCNum(), "证件号码")) {
//					return false;
//				}
//			}else {
//				if (info.getLCNum().toString().equals("")) {
//					XCToolkit.showToast("请输入"+tableName+"@证件号码");
//					return false;
//				}
//			} 
			if (!SubHelperUtil.showPhoneToast(info.getLTNum(), "手机号码")) {
				return false;
			}
		}
		SubHelperUtil.setTableName("");
		return true;
	}
}
