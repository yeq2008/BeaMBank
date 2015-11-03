package com.bea.mbank.edit.step3;

import xc.android.utils.XCToolkit;
import android.text.method.NumberKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.widgets.GroupTagView;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.mbank.edit.step1.ZoneDialog;
import com.bea.mbank.edit.step1.ZoneDialog.ZoneDialogListener;
import com.bea.mbank.util.SubHelperUtil;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.bea.application.BeaAppSettings;
import com.bea.database.DbManager;
import com.bea.mbank.R;
import com.bea.remote.models.grwdy.CustManagerInputBO;
import com.bea.remote.models.grwdy.DetailAddrBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

/**
 * @author fanglinhao 客户经理填写 左侧内容
 */
public class Step3ContentFragment extends ContentBaseFragment<CustManagerInputBO> {
	@Override
	protected Class getInfoClass() {
		return CustManagerInputBO.class;
	}
	// 客是否享受员工贴息源
	@ViewInject(R.id.fgccmi_gtv_ointerest)
	GroupTagViewEx gtv_ointerest;
	// 资金使用地
	@ViewInject(R.id.fgccmi_gtv_olua)
	GroupTagViewEx gtv_olua;
	// 贷款使用地所在地
	@ViewInject(R.id.fgccmi_tv_ocounandprov_content)
	TextView tv_ocounandprov_content;
//	// 用途备注
//	@ViewInject(R.id.fgccmi_et_ocomment)
//	EditText et_ocomment;
	// 客户还款能力判断标准
	@ViewInject(R.id.fgccmi_gtv_ocrtype)
	GroupTagViewEx gtv_ocrtype;
	// 业务推荐类型
	@ViewInject(R.id.fgccmi_gtv_omic)
	GroupTagViewEx gtv_omic;

	// 推荐部门名称
	@ViewInject(R.id.fgccmi_tv_obead)
	TextView tv_obead;
	@ViewInject(R.id.fgccmi_et_obead)
	EditText et_obead;
	// 推荐客户-证件类型
	@ViewInject(R.id.fgccmi_rl_oc)
	RelativeLayout rl_oc;
	@ViewInject(R.id.fgccmi_gtv_octype)
	GroupTagViewEx gtv_octype;
	// 推荐客户-证件号码
	@ViewInject(R.id.fgccmi_et_ocnum)
	EditText et_ocnum;
	// 推荐客户-名称
	@ViewInject(R.id.fgccmi_et_ocname)
	EditText et_ocname;
	// 推荐客户-联系手机
	@ViewInject(R.id.fgccmi_et_octel)
	EditText et_octel;
	// 推荐员工工号
	@ViewInject(R.id.fgccmi_tv_orsnum)
	TextView tv_orsnum;
	@ViewInject(R.id.fgccmi_et_orsnum)
	EditText et_orsnum;

	@ViewInject(R.id.fgccmi_gtv_ostype)
	GroupTagViewEx gtv_ostype;

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater.inflate(R.layout.fm_grwdy_step3_content, null);
	}

	@Override
	protected void onInitContentView(View rootView, CustManagerInputBO info) {
		initGtv(info);
		et_orsnum.setKeyListener(new numberEngChar());
	}

	class numberEngChar extends NumberKeyListener{
		@Override
		protected char[] getAcceptedChars()
		{
			return new char[] { '1', '2', '3', '4', '5', '6', '7', '8','9', '0',
					'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
					'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		}

		@Override
		public int getInputType() {
			 return android.text.InputType.TYPE_CLASS_TEXT;  
		}
	}

	@OnClick(R.id.fgccmi_tv_ocounandprov_content)
	public void getDetailAddr(View view) {
		DetailAddrBO detailAddr = new DetailAddrBO();
		if (null != infoBO) {
			detailAddr.setCountryId(infoBO.getOCountry());
			detailAddr.setCountryName(infoBO.getOCountryName());
			if(null != infoBO.getOACode() && infoBO.getOACode().length()>2){
				detailAddr.setProvinceId(infoBO.getOACode().substring(0,2));
				detailAddr.setProvinceName(infoBO.getOACodeName());
			}
		}
		ZoneDialog dialog = new ZoneDialog(getActivity(), detailAddr,
				new ZoneDialogListener() {
					@Override
					public void onClick(DetailAddrBO addr) {
						tv_ocounandprov_content.setTag(addr);
						StringBuilder sb = new StringBuilder();
						sb.append(addr.getCountryName() == null ? "" : (addr
								.getCountryName() + " "));
						sb.append(addr.getProvinceName() == null ? "" : (addr
								.getProvinceName() + " "));
						tv_ocounandprov_content.setText(sb.toString());
					}
				});
		dialog.show();	
		if("1".equals(gtv_olua.getCurrentValue())){//境内
			dialog.goneView(2);
			dialog.getTvCountry().setEnabled(false);
		}else{//境外
			dialog.goneView(1);
			dialog.getTvCountry().setEnabled(true);
		}
	}

	@Override
	public CustManagerInputBO getValueFromInterfaceView() {
		infoBO.setOInterest((String) gtv_ointerest.getCurrentValue());
		infoBO.setOLUA((String) gtv_olua.getCurrentValue());
		infoBO.setOCRType((String) gtv_ocrtype.getCurrentValue());
		infoBO.setOMIC((String) gtv_omic.getCurrentValue());
		infoBO.setOMICName(gtv_omic.getYRLname());
		infoBO.setOEC("CNCMMP");
		infoBO.setOECName("移动进件");
		infoBO.setOSType((String) gtv_ostype.getCurrentValue());
		infoBO.setOCType((String) gtv_octype.getCurrentValue());
		infoBO.setOLP("2");

		DetailAddrBO addrBO = (DetailAddrBO) tv_ocounandprov_content.getTag();
		if (null != addrBO) {
			infoBO.setOCountry(addrBO.getCountryId());
			infoBO.setOCountryName(addrBO.getCountryName());
			if(null != addrBO.getProvinceId() && !"".equals(addrBO.getProvinceId().trim())){
				infoBO.setOACode(addrBO.getProvinceId()+"0000");
				infoBO.setOACodeName(addrBO.getProvinceName());
			}
		}

//		infoBO.setOcomment(et_ocomment.getText().toString());
		infoBO.setOBEAD(et_obead.getText().toString());
		infoBO.setOCNum(et_ocnum.getText().toString());
		infoBO.setOCName(et_ocname.getText().toString());
		infoBO.setOCTel(et_octel.getText().toString());
		infoBO.setORSNum(et_orsnum.getText().toString());
		infoBO.setOSNum(null != BeaAppSettings.getUserInfo()?BeaAppSettings.getUserInfo().getEmployee_mark():"");
		return infoBO;
	}

	/**
	 * 初始化控件
	 */
	private void initGtv(CustManagerInputBO info) {
		gtv_ointerest.addButtons(DbManager.codeDatas("YesNo"), "NAME",info.getOInterest());
		gtv_olua.addButtons(DbManager.codeDatas("LoanUseArea"), "NAME",info.getOLUA());
		gtv_ocrtype.addButtons(DbManager.codeDatas("CustomerRepayType"),"NAME",info.getOCRType());
		gtv_omic.addButtons(DbManager.codeDatas("MarketIngChannel"), "NAME",info.getOMIC());
		gtv_ostype.addButtons(DbManager.codeDatas("StaffLineType"), "NAME",info.getOSType());
		gtv_octype.addButtons(DbManager.codeDatas("CertType"), "NAME",info.getOCType());
		if (infoBO != null) {
			String s = "";
			if (null != info.getOCountryName())
				s += info.getOCountryName() + " ";
			if (null != info.getOACodeName())
				s += info.getOACodeName() + " ";
			tv_ocounandprov_content.setText(s);

//			et_ocomment.setText(info.getOcomment());
			et_obead.setText(info.getOBEAD());
			et_ocnum.setText(info.getOCNum());
			et_ocname.setText(info.getOCName());
			et_octel.setText(info.getOCTel());
			et_orsnum.setText(info.getORSNum());
		}
		
		tv_obead.setVisibility(View.GONE);
		et_obead.setVisibility(View.GONE);
		rl_oc.setVisibility(View.GONE);
		
		if (null != info.getOMIC()) {
			if ("010".equals(info.getOMIC())) {// 若业务推荐类型为我行员工推荐则此字段必输
				tv_orsnum.setVisibility(View.VISIBLE);
				et_orsnum.setVisibility(View.VISIBLE);
				tv_obead.setVisibility(View.GONE);
				et_obead.setVisibility(View.GONE);
				rl_oc.setVisibility(View.GONE);
			} else if ("020".equals(info.getOMIC())) {// 若业务推荐类型为其他部门推荐则此字段必输
				tv_orsnum.setVisibility(View.GONE);
				et_orsnum.setVisibility(View.GONE);
				tv_obead.setVisibility(View.VISIBLE);
				et_obead.setVisibility(View.VISIBLE);
				rl_oc.setVisibility(View.GONE);
			} else if ("030".equals(info.getOMIC())) {// 若业务推荐类型为客户推荐则此字段必输
				tv_orsnum.setVisibility(View.GONE);
				et_orsnum.setVisibility(View.GONE);
				tv_obead.setVisibility(View.GONE);
				et_obead.setVisibility(View.GONE);
				rl_oc.setVisibility(View.VISIBLE);
			}else{
				tv_orsnum.setVisibility(View.GONE);
				et_orsnum.setVisibility(View.GONE);
				tv_obead.setVisibility(View.GONE);
				et_obead.setVisibility(View.GONE);
				rl_oc.setVisibility(View.GONE);
			}
		}
		
		gtv_omic.setOnTagButtonSelectedListener(new GroupTagView.OnTagButtonSelectedListener(){
			@Override
			public void OnTagButtonSelected(int index, Object btnModel) {
				if(index==0){
					tv_orsnum.setVisibility(View.VISIBLE);
					et_orsnum.setVisibility(View.VISIBLE);
					tv_obead.setVisibility(View.GONE);
					et_obead.setVisibility(View.GONE);
					rl_oc.setVisibility(View.GONE);
				}else if(index==1){
					tv_orsnum.setVisibility(View.GONE);
					et_orsnum.setVisibility(View.GONE);
					tv_obead.setVisibility(View.VISIBLE);
					et_obead.setVisibility(View.VISIBLE);
					rl_oc.setVisibility(View.GONE);
				}else if(index==2){
					tv_orsnum.setVisibility(View.GONE);
					et_orsnum.setVisibility(View.GONE);
					tv_obead.setVisibility(View.GONE);
					et_obead.setVisibility(View.GONE);
					rl_oc.setVisibility(View.VISIBLE);
				}else{
					tv_orsnum.setVisibility(View.GONE);
					et_orsnum.setVisibility(View.GONE);
					tv_obead.setVisibility(View.GONE);
					et_obead.setVisibility(View.GONE);
					rl_oc.setVisibility(View.GONE);
				}
			}
		});
		
	}

	@Override
	public boolean isRequiredFieldInputed(CustManagerInputBO info) {
		if (null == info) {
			XCToolkit.showToast("请填写“客户经理填写”");
			return false;
		}
		
		SubHelperUtil.setTableName("客户经理填写");
		if (!SubHelperUtil.showToast(info.getOInterest(), "是否享受员工贴息"))
			return false;
		if (!SubHelperUtil.showToast(info.getOLUA(), "资金使用地"))
			return false;
		if (!SubHelperUtil.showNormal(info.getOCountry(), "贷款使用地"))// 所在国家
			return false;
		if("2".equals(info.getOLUA())){
			if("中华人民共和国".equals(info.getOCountryName())){
				XCToolkit.showToast("境外资金使用地所在地不能选择中华人民共和国");
				return false;
			}
		}
//		if (homeBO.isPinAnXinBaoData) {
//			// 如果是贷款用途为304-其他消费，此字段不能为空
//			if (null != homeBO.getApplyLoanInfor() && 
//				null != homeBO.getApplyLoanInfor().getILT() && 
//				"304".equals(homeBO.getApplyLoanInfor().getILT())) {
//				if (!SubHelperUtil.showNormal(info.getOcomment(), "用途备注"))
//					return false;
//			}
//		} 
		if (!SubHelperUtil.showToast(info.getOCRType(), "客户还款能力判断标准"))
			return false;
		if (!SubHelperUtil.showToast(info.getOMIC(), "业务推荐类型"))
			return false;
		if (null != info.getOMIC()) {
			if ("10".equals(info.getOMIC())) {// 若业务推荐类型为我行员工推荐则此字段必输
				if (!SubHelperUtil.showNormal(info.getORSNum(), "推荐员工工号"))
					return false;
			} else if ("20".equals(info.getOMIC())) {// 若业务推荐类型为其他部门推荐则此字段必输
				if (!SubHelperUtil.showNormal(info.getOBEAD(), "推荐部门名称"))
					return false;
			} else if ("30".equals(info.getOMIC())) {// 若业务推荐类型为客户推荐则此字段必输
				if (!SubHelperUtil.showNormal(info.getOCType(), "推荐客户-证件类型"))
					return false;
				if (!SubHelperUtil.showNormal(info.getOCNum(), "推荐客户-证件号码"))
					return false;
				if (!SubHelperUtil.showNormal(info.getOCName(), "推荐客户-名称"))
					return false;
				if (!SubHelperUtil.showNormal(info.getOCTel(), "推荐客户-联系手机"))
					return false;
			}
		}
		if (!SubHelperUtil.showToast(info.getOEC(), "业务进件类型"))
			return false;
		if (!SubHelperUtil.showToast(info.getOSType(), "员工所在条线"))
			return false;
		SubHelperUtil.setTableName("");
		return true;
	}
}
