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
 * @author fanglinhao �ͻ�������д �������
 */
public class Step3ContentFragment extends ContentBaseFragment<CustManagerInputBO> {
	@Override
	protected Class getInfoClass() {
		return CustManagerInputBO.class;
	}
	// ���Ƿ�����Ա����ϢԴ
	@ViewInject(R.id.fgccmi_gtv_ointerest)
	GroupTagViewEx gtv_ointerest;
	// �ʽ�ʹ�õ�
	@ViewInject(R.id.fgccmi_gtv_olua)
	GroupTagViewEx gtv_olua;
	// ����ʹ�õ����ڵ�
	@ViewInject(R.id.fgccmi_tv_ocounandprov_content)
	TextView tv_ocounandprov_content;
//	// ��;��ע
//	@ViewInject(R.id.fgccmi_et_ocomment)
//	EditText et_ocomment;
	// �ͻ����������жϱ�׼
	@ViewInject(R.id.fgccmi_gtv_ocrtype)
	GroupTagViewEx gtv_ocrtype;
	// ҵ���Ƽ�����
	@ViewInject(R.id.fgccmi_gtv_omic)
	GroupTagViewEx gtv_omic;

	// �Ƽ���������
	@ViewInject(R.id.fgccmi_tv_obead)
	TextView tv_obead;
	@ViewInject(R.id.fgccmi_et_obead)
	EditText et_obead;
	// �Ƽ��ͻ�-֤������
	@ViewInject(R.id.fgccmi_rl_oc)
	RelativeLayout rl_oc;
	@ViewInject(R.id.fgccmi_gtv_octype)
	GroupTagViewEx gtv_octype;
	// �Ƽ��ͻ�-֤������
	@ViewInject(R.id.fgccmi_et_ocnum)
	EditText et_ocnum;
	// �Ƽ��ͻ�-����
	@ViewInject(R.id.fgccmi_et_ocname)
	EditText et_ocname;
	// �Ƽ��ͻ�-��ϵ�ֻ�
	@ViewInject(R.id.fgccmi_et_octel)
	EditText et_octel;
	// �Ƽ�Ա������
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
		if("1".equals(gtv_olua.getCurrentValue())){//����
			dialog.goneView(2);
			dialog.getTvCountry().setEnabled(false);
		}else{//����
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
		infoBO.setOECName("�ƶ�����");
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
	 * ��ʼ���ؼ�
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
			if ("010".equals(info.getOMIC())) {// ��ҵ���Ƽ�����Ϊ����Ա���Ƽ�����ֶα���
				tv_orsnum.setVisibility(View.VISIBLE);
				et_orsnum.setVisibility(View.VISIBLE);
				tv_obead.setVisibility(View.GONE);
				et_obead.setVisibility(View.GONE);
				rl_oc.setVisibility(View.GONE);
			} else if ("020".equals(info.getOMIC())) {// ��ҵ���Ƽ�����Ϊ���������Ƽ�����ֶα���
				tv_orsnum.setVisibility(View.GONE);
				et_orsnum.setVisibility(View.GONE);
				tv_obead.setVisibility(View.VISIBLE);
				et_obead.setVisibility(View.VISIBLE);
				rl_oc.setVisibility(View.GONE);
			} else if ("030".equals(info.getOMIC())) {// ��ҵ���Ƽ�����Ϊ�ͻ��Ƽ�����ֶα���
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
			XCToolkit.showToast("����д���ͻ�������д��");
			return false;
		}
		
		SubHelperUtil.setTableName("�ͻ�������д");
		if (!SubHelperUtil.showToast(info.getOInterest(), "�Ƿ�����Ա����Ϣ"))
			return false;
		if (!SubHelperUtil.showToast(info.getOLUA(), "�ʽ�ʹ�õ�"))
			return false;
		if (!SubHelperUtil.showNormal(info.getOCountry(), "����ʹ�õ�"))// ���ڹ���
			return false;
		if("2".equals(info.getOLUA())){
			if("�л����񹲺͹�".equals(info.getOCountryName())){
				XCToolkit.showToast("�����ʽ�ʹ�õ����ڵز���ѡ���л����񹲺͹�");
				return false;
			}
		}
//		if (homeBO.isPinAnXinBaoData) {
//			// ����Ǵ�����;Ϊ304-�������ѣ����ֶβ���Ϊ��
//			if (null != homeBO.getApplyLoanInfor() && 
//				null != homeBO.getApplyLoanInfor().getILT() && 
//				"304".equals(homeBO.getApplyLoanInfor().getILT())) {
//				if (!SubHelperUtil.showNormal(info.getOcomment(), "��;��ע"))
//					return false;
//			}
//		} 
		if (!SubHelperUtil.showToast(info.getOCRType(), "�ͻ����������жϱ�׼"))
			return false;
		if (!SubHelperUtil.showToast(info.getOMIC(), "ҵ���Ƽ�����"))
			return false;
		if (null != info.getOMIC()) {
			if ("10".equals(info.getOMIC())) {// ��ҵ���Ƽ�����Ϊ����Ա���Ƽ�����ֶα���
				if (!SubHelperUtil.showNormal(info.getORSNum(), "�Ƽ�Ա������"))
					return false;
			} else if ("20".equals(info.getOMIC())) {// ��ҵ���Ƽ�����Ϊ���������Ƽ�����ֶα���
				if (!SubHelperUtil.showNormal(info.getOBEAD(), "�Ƽ���������"))
					return false;
			} else if ("30".equals(info.getOMIC())) {// ��ҵ���Ƽ�����Ϊ�ͻ��Ƽ�����ֶα���
				if (!SubHelperUtil.showNormal(info.getOCType(), "�Ƽ��ͻ�-֤������"))
					return false;
				if (!SubHelperUtil.showNormal(info.getOCNum(), "�Ƽ��ͻ�-֤������"))
					return false;
				if (!SubHelperUtil.showNormal(info.getOCName(), "�Ƽ��ͻ�-����"))
					return false;
				if (!SubHelperUtil.showNormal(info.getOCTel(), "�Ƽ��ͻ�-��ϵ�ֻ�"))
					return false;
			}
		}
		if (!SubHelperUtil.showToast(info.getOEC(), "ҵ���������"))
			return false;
		if (!SubHelperUtil.showToast(info.getOSType(), "Ա����������"))
			return false;
		SubHelperUtil.setTableName("");
		return true;
	}
}
