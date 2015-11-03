package com.bea.mbank.edit.step1;

import java.util.ArrayList;
import java.util.List;

import xc.android.utils.XCToolkit;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bea.database.DbManager;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.mbank.util.HeplerUtil;
import com.bea.mbank.util.SubHelperUtil;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.android.widgets.TitleValueView;
import com.bea.mbank.R;
import com.bea.remote.models.grwdy.BasicInfoBO;
import com.cyg.viewinject.annotation.ViewInject;

/**
 * @author 李三强批量录入个人进本信息
 * 
 */
public class BasicInfoFragment extends ContentBaseFragment<BasicInfoBO> {
	
	@Override
	protected Class getInfoClass() {
		return BasicInfoBO.class;
	}

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater
				.inflate(R.layout.fm_grwdy_step1_content_basicinfo, null);
	}

	// 产品类型
	@ViewInject(R.id.Basic_productTypeValue)
	GroupTagViewEx GtvYewupinz;
	// 证件类型
	@ViewInject(R.id.Basic_gtvSexual)
	GroupTagViewEx GtvSex;
	// 中文姓
	@ViewInject(R.id.Basic_fName)
	TitleValueView fName;
	// 中文名
	@ViewInject(R.id.Basic_lName)
	TitleValueView lName;
	// 英文姓
	@ViewInject(R.id.Basic_efName)
	TitleValueView efName;
	// 英文名
	@ViewInject(R.id.Basic_elName)
	TitleValueView elName;
	// 证件类型
	@ViewInject(R.id.Basic_gtvDocumentType)
	GroupTagViewEx GtvDocumentType;
	// 证件号码
	@ViewInject(R.id.Basic_DocumentNumber)
	TitleValueView TvvDocumentNumber;
	// 发证国家
	@ViewInject(R.id.Basic_gtvIssuing)
	GroupTagViewEx GtvIssuing; 

	@Override
	protected void onInitContentView(View rootView, BasicInfoBO info) {
		initGtv(info);
	}

	private void initGtv(BasicInfoBO info) {
		List<YRL_BASIC_DATA> prdType = new ArrayList<YRL_BASIC_DATA>();
		prdType.add(new YRL_BASIC_DATA("0201", "个人无抵押消费贷款"));
		prdType.add(new YRL_BASIC_DATA("0206", "个人无抵押车位贷款"));
		GtvYewupinz.addButtons(prdType, "NAME");
		GtvYewupinz.setCurrentIndex(null==info.getLCBType()?"0201":info.getLCBType());

		List<YRL_BASIC_DATA> sexlist = DbManager.codeAyncDatas("Sex");
		List<YRL_BASIC_DATA> sortSexlist = HeplerUtil.sortYRL_BASIC_DATA(sexlist);
		GtvSex.addButtons(null == sortSexlist?sexlist:sortSexlist, "NAME");
		GtvSex.setCurrentIndex(info.getLCSex());
		
		GtvDocumentType.addButtons(DbManager.codeDatas("CertType"), "NAME",info.getLCType());
		GtvIssuing.addButtons(DbManager.codeDatas("CountryCode"), "NAME",info.getLCC());
		
		if (info != null) {
			fName.setValueText(info.getLCFName());
			lName.setValueText(info.getLCLName());
			efName.setValueText(info.getLCEFName());
			elName.setValueText(info.getLCELName());
			TvvDocumentNumber.setValueText(info.getLCNum());
		}
	}

	@Override
	public BasicInfoBO getValueFromInterfaceView() {
		BasicInfoBO bo = new BasicInfoBO();
		bo.setLCBType((String)GtvYewupinz.getCurrentValue());
		bo.setLCFName(fName.getValueText());
		bo.setLCLName(lName.getValueText());
		bo.setLCEFName(efName.getValueText());
		bo.setLCELName(elName.getValueText());
		bo.setLCSex((String) GtvSex.getCurrentValue());
		bo.setLCType((String) GtvDocumentType.getCurrentValue());
		bo.setLCC((String) GtvIssuing.getCurrentValue());
		bo.setLCNum(TvvDocumentNumber.getValueText());
		bo.setLCTypeName(GtvDocumentType.getYRLname());
		return bo;
	}

	@Override
	public boolean isRequiredFieldInputed(BasicInfoBO info) {
		if (null == info) {
			XCToolkit.showToast("请填写“个人基本资料”信息");
			return false;
		} else {
			if (!SubHelperUtil.showCHToast(info.getLCFName(), "中文姓")) {
				return false;
			}
			if (!SubHelperUtil.showCHToast(info.getLCLName(), "中文名")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getLCEFName(), "英文姓")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getLCELName(), "英文名")) {
				return false;
			}
			if (!"".equals(info.getLCType()) && info.getLCType().toString().equals("Ind1010")) {
				if (!SubHelperUtil.showIDToast(info.getLCNum(), "证件号码")) {
					return false;
				}
			}else {
				if (info.getLCNum().toString().equals("")) {
					XCToolkit.showToast("请输入证件号码");
					return false;
				}
			} 
			if (!SubHelperUtil.showToast(info.getLCSex(), "性别")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getLCC(), "发证国家")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getLCType(), "证件类型")) {
				return false;
			}
		}
		return true;
	}
}
