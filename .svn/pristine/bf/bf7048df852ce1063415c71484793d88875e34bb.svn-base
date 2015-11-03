package com.bea.mbank.edit.step1;

import java.util.List;

import xc.android.utils.XCToolkit;

import com.bea.application.ConstDefined;
import com.bea.database.DbManager;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.mbank.mytask.DateDialog;
import com.bea.mbank.mytask.DateDialog.DateDialogListener;
import com.bea.mbank.util.SubHelperUtil;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.android.widgets.TitleValueView;
import com.bea.mbank.R;
import com.bea.remote.models.grwdy.MateInforBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author lsq 配偶信息
 */
public class MateInforFragment extends ContentBaseFragment<MateInforBO> {
	@Override
	protected Class getInfoClass() {
		return MateInforBO.class;
	}

	private static final String tableName = "借款人配偶资料";
	// 中文姓
	@ViewInject(R.id.Mate_fName)
	TitleValueView Tvv_fName;
	// 中文名
	@ViewInject(R.id.Mate_lName)
	TitleValueView Tvv_lName;
	// 英文姓名
	@ViewInject(R.id.Mate_efName)
	TitleValueView Tvv_efName;
	// 英文姓名
	@ViewInject(R.id.Mate_elName)
	TitleValueView Tvv_elName;
	// 证件号码
	@ViewInject(R.id.Mate_DocumentNumber)
	TitleValueView Tv_DocumentNumber;

	// 证件类型
	@ViewInject(R.id.Mate_gtvDocumentType)
	GroupTagViewEx gtv_DocumentType;
	List<String> gtv_DocumentType_list;
	// 发证国家
	@ViewInject(R.id.Mate_gtvIssuing)
	GroupTagViewEx gtv_Issuing;
	List<String> gtv_Issuing_list;

	// 证件到期日
	@ViewInject(R.id.Mate_valueDocumentDate)
	TextView dcmDate;

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater.inflate(R.layout.fm_grwdy_step1_content_mateinfo, null);
	}

	@Override
	protected void onInitContentView(View rootView, MateInforBO info) {
		initGtv(info);
	}

	private void initGtv(MateInforBO info) {
		gtv_DocumentType.addButtons(DbManager.codeDatas("CertType"), "NAME",
				info.getWCType());
		gtv_Issuing.addButtons(DbManager.codeDatas("CountryCode"), "NAME",
				info.getWCC());
		if (info != null) {
			Tvv_fName.setValueText(info.getWFName());
			Tvv_lName.setValueText(info.getWLName());
			Tvv_efName.setValueText(info.getWEFName());
			Tvv_elName.setValueText(info.getWELName());
			Tv_DocumentNumber.setValueText(info.getWCNum());

			dcmDate.setText(info.getWCDate());
		} 
		setTitleColor();
	}

	private void setTitleColor() {  
		// 证件类型 
		TextView TvDocumentType = (TextView) findViewById(R.id.Mate_DocumentType);
		// 发证国家 
		TextView TvIssuing = (TextView) findViewById(R.id.Mate_Issuing);
		if (homeBO.isPinAnXinBaoData) {
			TvDocumentType.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvIssuing.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			
		}
	}
	// 证件到期日
	@OnClick(R.id.Mate_valueDocumentDate)
	public void DocumentdateSelect(View view) {
		DateDialog dialog = new DateDialog(getActivity(), ((TextView) view)
				.getText().toString(), new DateDialogListener() {
			@Override
			public void onClick(String date) {
				dcmDate.setText(date);
			}
		});
		dialog.show();
	}

	@Override
	public MateInforBO getValueFromInterfaceView() {

		MateInforBO inputBO = new MateInforBO();
		inputBO.setWCType((String) gtv_DocumentType.getCurrentValue());
		inputBO.setWCC((String) gtv_Issuing.getCurrentValue());
		inputBO.setWFName(Tvv_fName.getValueText());
		inputBO.setWLName(Tvv_lName.getValueText());

		inputBO.setWEFName(Tvv_efName.getValueText());
		inputBO.setWELName(Tvv_elName.getValueText());
		inputBO.setWCNum(Tv_DocumentNumber.getValueText());
		inputBO.setWCType((String) gtv_DocumentType.getCurrentValue());
		inputBO.setWCC((String) gtv_Issuing.getCurrentValue());

		inputBO.setWCDate(dcmDate.getText().toString());
		return inputBO;
	}

	@Override
	public boolean isRequiredFieldInputed(MateInforBO info) {
		SubHelperUtil.setTableName(tableName);
		if (null == info) {
			XCToolkit.showToast("请填写“借款人配偶资料”信息");
			return false;
		} else {
			if (!SubHelperUtil.showCHToast(info.getWFName(), "中文姓")) {
				return false;
			}
			if (!SubHelperUtil.showCHToast(info.getWLName(), "中文名")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getWEFName(), "英文姓")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getWELName(), "英文名")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getWCType(), "证件类型")) {
				return false;
			}
			if (!"".equals(info.getWCType())
					&& info.getWCType().toString().equals("Ind1010")) {
				if (!SubHelperUtil.showIDToast(info.getWCNum(), "证件号码")) {
					return false;
				}
			} else {
				if (info.getWCNum().toString().equals("")) {
					XCToolkit.showToast("请输入" + tableName + "@证件号码");
					return false;
				}
			}
			if (!SubHelperUtil.showToast(info.getWCC(), "发证国家")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getWCDate(), "证件有效期")) {
				return false;
			}
		}
		SubHelperUtil.setTableName("");
		return true;
	}
}
