package com.bea.mbank.edit.crm;

import java.util.HashMap;
import java.util.Map;

import xc.android.utils.XCToolkit;

import com.bea.application.BeaAppSettings;
import com.bea.database.DbManager;
import com.bea.mbank.R;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.bea.remote.models.grwdy.BasicInfoBO;
import com.bea.remote.models.grwdy.BorrowerInforBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import com.cyg.viewinject.VInject;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * @author cuiyuguo
 * 
 */
public final class CRMDialog extends Dialog {
	@ViewInject(R.id.nameInput)
	EditText nameInput;
	@ViewInject(R.id.mingInput)
	EditText mingInput;
	@ViewInject(R.id.country)
	GroupTagViewEx country;
	@ViewInject(R.id.cardType)
	GroupTagViewEx cardType;
	@ViewInject(R.id.identiferInput)
	EditText identiferInput;
	@ViewInject(R.id.confirmBtn)
	ImageButton confirmBtn;
	private Map<String, Object> map;

	String countryNo = "CHN";
	String cardTypeNo = "Ind1010";

	private CrmDialogClickListener dialogClickListener;

	public interface CrmDialogClickListener {
		public void click();
	}

	public CRMDialog(Context context,
			CrmDialogClickListener crmDialogClickListener) {
		super(context, R.style.AppTheme_Dialog);
		this.dialogClickListener = crmDialogClickListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View contentView = inflater.inflate(R.layout.pop_crmedit, null);
		// setContentView(contentView, new LayoutParams(XCToolkit.dip2px(720),
		// XCToolkit.dip2px(370)));
		setContentView(contentView,
				new LayoutParams((int) (XCToolkit.getScreenWidth() * 0.57f),
						(int) (XCToolkit.getScreenHeight() * 0.4875f)));
		VInject.inject(this, contentView);
		setCanceledOnTouchOutside(false);

		// 借款人个人资料里面有可能填写了，所以此处从那边取初始数据
		GrwdyHomeBO homeBO = BeaAppSettings.instance().mGrwdyHomeBO;
		if (null != homeBO) {
			if (null != homeBO.getBorrowerInfor()) {
				BorrowerInforBO bInfo = homeBO.getBorrowerInfor();
				if (null != bInfo.getMCType()) {
					cardTypeNo = bInfo.getMCType();
				}
				if (null != bInfo.getMCountry()) {
					countryNo = bInfo.getMCountry();
				}
				if (null != bInfo.getMFName() && null != bInfo.getMLName()) {
					nameInput.setText(bInfo.getMFName());
					mingInput.setText(bInfo.getMLName());
				} else if (null != bInfo.getMEFName()
						&& null != bInfo.getMELName()) {
					nameInput.setText(bInfo.getMEFName());
					mingInput.setText(bInfo.getMELName());
				}
				if (null != bInfo.getMCNum()) {
					identiferInput.setText(bInfo.getMCNum());
				}
			} else if (null != homeBO.getBasicInfo()) {
				BasicInfoBO basicInfoBO = homeBO.getBasicInfo();
				if (null != basicInfoBO.getLCType()) {
					cardTypeNo = basicInfoBO.getLCType();
				}
				if (null != basicInfoBO.getLCC()) {
					countryNo = basicInfoBO.getLCC();
				}
				if (null != basicInfoBO.getLCFName()
						&& null != basicInfoBO.getLCLName()) {
					nameInput.setText(basicInfoBO.getLCFName());
					mingInput.setText(basicInfoBO.getLCLName());
				} else if (null != basicInfoBO.getLCEFName()
						&& null != basicInfoBO.getLCELName()) {
					nameInput.setText(basicInfoBO.getLCEFName());
					mingInput.setText(basicInfoBO.getLCELName());
				}
				if (null != basicInfoBO.getLCNum()) {
					identiferInput.setText(basicInfoBO.getLCNum());
				}
			}
		}
		// 默认为中华人民共和国
		country.addButtons(DbManager.codeDatas("CountryCode"), "NAME",
				countryNo);
		// 证件类型，默认为身份证
		cardType.addButtons(DbManager.codeDatas("CertType"), "NAME", cardTypeNo);
	}

	@OnClick(R.id.confirmBtn)
	public void onConfirmButtonEvent(View v) {
		if (null == nameInput.getText()
				|| "".equals(nameInput.getText().toString().trim())) {
			XCToolkit.showToast("请输入姓");
			return;
		}
		if (null == mingInput.getText()
				|| "".equals(mingInput.getText().toString().trim())) {
			XCToolkit.showToast("请输入名");
			return;
		}
		if (null == identiferInput.getText()
				|| "".equals(identiferInput.getText().toString().trim())) {
			XCToolkit.showToast("请输入身份证号码");
			return;
		}

		map = new HashMap<String, Object>();
		map.put("global_id", identiferInput.getText().toString().trim());
		map.put("global_type", cardType.getCurrentValue());
		map.put("global_con", country.getCurrentValue());
		map.put("custom_name", nameInput.getText().toString().trim()
				+ mingInput.getText().toString().trim());// 姓名
		map.put("xin", nameInput.getText().toString().trim());// 姓
		map.put("ming", mingInput.getText().toString().trim());// 名
		queryBlankList();
	}

	private void queryBlankList() {
		confirmBtn.setEnabled(false);
		BeaAppSettings.instance();
		BeaAppSettings.blankListMap = map;
		dialogClickListener.click();
		dismiss();
	}

	@OnClick(R.id.closeBtn)
	public void onCloseButtonEvent(View v) {
		dismiss();
	}
}
