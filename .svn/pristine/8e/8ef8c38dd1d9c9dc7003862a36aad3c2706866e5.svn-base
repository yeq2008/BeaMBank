package com.bea.mbank.edit.crm;

import java.util.Map;

import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.database.DbManager;
import com.bea.mbank.R;
import com.bea.remote.models.crm.CrmCustDetail;
import com.bea.remote.models.grwdy.BasicInfoBO;
import com.bea.remote.models.grwdy.BorrowerInforBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import com.bea.remote.models.grwdy.ProfessionInforBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import xc.android.activity.XCBaseActivity;
import xc.android.fragment.XCBaseFragment;

/**
 * crm
 * @author fanglinhao
 */
public class CRMViewFragment extends XCBaseFragment{
	//反洗钱风险等级
	@ViewInject(R.id.fgh_tv_fxqfxdj)
	TextView tvv_fxqfxdj;
	//是否风险控制
	@ViewInject(R.id.fgh_tv_sffxkz)
	TextView tvv_sffxkz;
	//借贷五级分类
	@ViewInject(R.id.fgh_tv_jdwjfl)
	TextView tvv_jdwjfl;
	//是否员工
	@ViewInject(R.id.fgh_tv_sfyg)
	TextView tvv_sfyg;
	
	//客户名称
	@ViewInject(R.id.fgh_tv_username)
	TextView tvusername;
	//拼音
	@ViewInject(R.id.fgh_tv_userpingyin)
	TextView tvuserpingyin;
	//客户号
	@ViewInject(R.id.fgh_tv_custnum)
	TextView tvcustnum;
	//手机号
	@ViewInject(R.id.fgh_tv_phone)
	TextView tv_phone;
	//国籍
	@ViewInject(R.id.fgh_tv_country)
	TextView tv_country;
		
	//存款
	@ViewInject(R.id.fgh_tv_ck)
	TextView tvck;
	//理财
	@ViewInject(R.id.fgh_tv_lc)
	TextView tvlc;
	//保险
	@ViewInject(R.id.fgh_tv_bx)
	TextView tvbx;
	//贷款
	@ViewInject(R.id.fgh_tv_dk)
	TextView tvdk;
	//信用卡
	@ViewInject(R.id.fgh_tv_xyk)
	TextView tvxyk;
	//金贷通
	@ViewInject(R.id.fgh_tv_jdt)
	TextView tvjdt;
	//核心账户
	@ViewInject(R.id.fgh_tv_hxzh)
	TextView tvhxzh;
	//客户分析
	@ViewInject(R.id.fgh_tv_khfx)
	TextView tvkhfx;

	CrmCustDetail custDetail;
	CrmCustDetail custDetail2;
	Map<String ,Object> map;
	public CRMViewFragment(CrmCustDetail custDetail, Map<String ,Object> map) {
		this.custDetail = custDetail;
		this.map = map;
		init(custDetail);
	}
	
	private void init(CrmCustDetail o){
		custDetail2 = new CrmCustDetail();
		custDetail2.setIDEN_NUM_TYPE(o.getIDEN_NUM_TYPE());
		custDetail2.setGOVE_IDEN_NUM(o.getGOVE_IDEN_NUM());
		custDetail2.setCH_NAME(o.getCH_NAME());
		custDetail2.setGENDER(o.getGENDER());
		custDetail2.setBIRTHDATE(o.getBIRTHDATE());
		custDetail2.setEDUCATION_CODE(o.getEDUCATION_CODE());
		custDetail2.setEMPLOYMENT_TYPE(o.getEMPLOYMENT_TYPE());
		custDetail2.setOCCUPATION(o.getOCCUPATION());
		custDetail2.setPOSITION(o.getPOSITION());
		custDetail2.setUNITPROPERTY(o.getUNITPROPERTY());
		custDetail2.setYEAROFSERVICE(o.getYEAROFSERVICE());
		custDetail2.setMARITAL_STATUS(o.getMARITAL_STATUS());
		custDetail2.setPHONE_NUMBER(o.getPHONE_NUMBER());
		custDetail2.setINDUSTRY_CODE_HK(o.getINDUSTRY_CODE_HK());
		custDetail2.setINDUSTRY_CODE(o.getINDUSTRY_CODE());
	}

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup group) {
		return inflater.inflate(R.layout.fm_grwdy_crm_home, null);
	}

	@Override
	protected void onInitContentView(View view) {
		String tmp = "否";
		tvusername.setText(custDetail.getCH_NAME()==null?"":custDetail.getCH_NAME().trim());
		tvuserpingyin.setText(custDetail.getEN_NAME()==null?"":custDetail.getEN_NAME().trim());
		tvcustnum.setText(custDetail.getEXT_CLIENT_NO()==null?"客户号：":"客户号："+custDetail.getEXT_CLIENT_NO().trim());
		tv_phone.setText(custDetail.getPHONE_NUMBER());
		tv_country.setText(DbManager.codeDataName("CountryCode", custDetail.getNATIONALITY()));
		
		custDetail.setGENDER(DbManager.codeDataName("Sex", custDetail.getGENDER()));
		custDetail.setOCCUPATION(DbManager.codeDataName("OccupationBEA", custDetail.getOCCUPATION()));
		custDetail.setNATIONALITY(DbManager.codeDataName("CountryCode", custDetail.getNATIONALITY()));
		custDetail.setRACE(DbManager.codeDataName("Nationality", custDetail.getRACE()));
		custDetail.setEDUCATION_CODE(DbManager.codeDataName("EducationExperience", custDetail.getEDUCATION_CODE()));
		tmp = custDetail.getBIRTHDATE();
		if(null != tmp && tmp.length()==8)
			custDetail.setBIRTHDATE(tmp.substring(0,4)+"/"+tmp.substring(4,6)+"/"+tmp.substring(6,8));
		custDetail.setRACE(DbManager.codeDataName("Nationality", custDetail.getRACE()));
		custDetail.setMARITAL_STATUS(DbManager.codeDataName("Marriage", custDetail.getMARITAL_STATUS()));
		custDetail.setEMPLOYMENT_TYPE(DbManager.codeDataName("EmploymentType", custDetail.getEMPLOYMENT_TYPE()));
		custDetail.setPOSITION(DbManager.codeDataName("Position", custDetail.getPOSITION()));
		custDetail.setUNITPROPERTY(DbManager.codeDataName("UnitProperty", custDetail.getUNITPROPERTY()));
		custDetail.setUNITCITY(DbManager.zoneName(custDetail.getUNITCITY()));
		custDetail.setINDUSTRY_CODE_HK(DbManager.codeDataName("IndustryTypeHK", custDetail.getINDUSTRY_CODE_HK()));
		custDetail.setINDUSTRY_CODE(DbManager.codeDataName("IndustryType", custDetail.getINDUSTRY_CODE()));
		custDetail.setIDEN_NUM_TYPE(DbManager.codeDataName("CertType", custDetail.getIDEN_NUM_TYPE()));
		
		tvv_fxqfxdj.setText(DbManager.codeDataName("RiskLevel", custDetail.getAML_TYPE()));//反洗钱风险等级
		tvv_sffxkz.setText(custDetail.getRISK_CTRL_FLAG());//是否风险控制
		tvv_jdwjfl.setText(custDetail.getLOAN_DEGREE());//借贷五级分类
		tmp = "";
		if(null != custDetail.getIS_BANK_STAFF() && "Y".equals(custDetail.getIS_BANK_STAFF()))
			tmp = "是";
		if(null != custDetail.getIS_BANK_STAFF() && "N".equals(custDetail.getIS_BANK_STAFF()))
			tmp = "否";
		tvv_sfyg.setText(tmp);//是否员工
		
		tvck.setText("¥ "+formatString(custDetail.getDEPOSIT_TERM_AMT()));//存款
		tvlc.setText("¥ "+formatString(custDetail.getFINANCE_BALANCE_RMB()));//理财
		tvbx.setText("¥ "+formatString(custDetail.getINSSRANCE_AMT()));//保险
		tvdk.setText("¥ "+formatString(custDetail.getLOAN_TERM_AMT()));//贷款
		tvxyk.setText("¥ "+formatString(custDetail.getCREDIT_BAL()));//信用卡
		tvjdt.setText("¥ "+formatString(custDetail.getGOLD_LOAN_BAL()));//金贷通
		tvhxzh.setText("¥ "+formatString(custDetail.getQUART_CONTR_AMT()));//核心账户
		tvkhfx.setText(formatString(custDetail.getCUST_TYPE()));//客户分析
	}
	
	private String formatString(String s){
		if(null == s)
			return "";
		if("null".equals(s))
			return "";
		return s;
	}
	
	@OnClick(R.id.fgh_iv_btn_app)
	public void startApply(View view){
		GrwdyHomeBO detail = BeaAppSettings.instance().mGrwdyHomeBO;
		BeaAppSettings.setOriginData(null);
		
		if(!detail.isPartRequiredData){//全量
			if(null == detail.getBorrowerInfor())
				detail.setBorrowerInfor(new BorrowerInforBO());
			detail.getBorrowerInfor().setMCType(custDetail2.getIDEN_NUM_TYPE());//证件类型
			detail.getBorrowerInfor().setMCNum(custDetail2.getGOVE_IDEN_NUM());//证件号码
			try{
				String tmp = custDetail2.getCH_NAME();//客户名称
				if(null != tmp){
					detail.getBorrowerInfor().setMFName(tmp.substring(0, 1));
					detail.getBorrowerInfor().setMLName(tmp.substring(1, tmp.length()));
				}
				tmp = custDetail2.getEN_NAME();//英文名
				if(null != tmp){
					int index = tmp.indexOf(" ");
					detail.getBorrowerInfor().setMEFName(tmp.substring(0,index));
					detail.getBorrowerInfor().setMELName(tmp.substring(index,tmp.length()));
				}
			}catch(Exception e){}
			detail.getBorrowerInfor().setMSex(custDetail2.getGENDER());
			detail.getBorrowerInfor().setMBDate(custDetail2.getBIRTHDATE());
			String tmp = custDetail2.getBIRTHDATE();
			if(null != tmp && tmp.length()>7)
				detail.getBorrowerInfor().setMBDate(tmp.substring(0,4)+"/"+tmp.substring(4,6)+"/"+tmp.substring(6,8));
			detail.getBorrowerInfor().setMEE(custDetail2.getEDUCATION_CODE());
			detail.getBorrowerInfor().setMMType(custDetail2.getMARITAL_STATUS());
			detail.getBorrowerInfor().setMTNum(custDetail2.getPHONE_NUMBER());
			detail.setBorrowerInfor(detail.getBorrowerInfor());
			
			if(null == detail.getProfessionInfor())
				detail.setProfessionInfor(new ProfessionInforBO());
			detail.getProfessionInfor().setJET(custDetail2.getEMPLOYMENT_TYPE());
			detail.getProfessionInfor().setJOBEA(custDetail2.getOCCUPATION());
			detail.getProfessionInfor().setJPosition(custDetail2.getPOSITION());
			detail.getProfessionInfor().setJCTpye(custDetail2.getUNITPROPERTY());
			detail.getProfessionInfor().setJYear(custDetail2.getYEAROFSERVICE());
			detail.getProfessionInfor().setJITypeHK(custDetail2.getINDUSTRY_CODE_HK());
			detail.getProfessionInfor().setJIType(custDetail2.getINDUSTRY_CODE());
		}else{
			if(null == detail.getBasicInfo())
				detail.setBasicInfo(new BasicInfoBO());
			String tmp = custDetail2.getCH_NAME();//客户名称
			if(null != tmp && tmp.length()>1){
				detail.getBasicInfo().setLCFName(tmp.substring(0, 1));
				detail.getBasicInfo().setLCLName(tmp.substring(1, tmp.length()));
			}
			tmp = custDetail2.getEN_NAME();//英文名
			if(null != tmp && tmp.length() > 1){
				int index = tmp.indexOf(" ");
				detail.getBasicInfo().setLCEFName(tmp.substring(0,index));
				detail.getBasicInfo().setLCELName(tmp.substring(index,tmp.length()));
			}
			detail.getBasicInfo().setLCNum(custDetail2.getGOVE_IDEN_NUM());
			detail.getBasicInfo().setLCSex(custDetail2.getGENDER());
			detail.getBasicInfo().setLCType(custDetail2.getIDEN_NUM_TYPE());
			detail.getBasicInfo().setLCC(custDetail2.getNATIONALITY());
		}
		
		Message msg = new Message();
		msg.what = ConstDefined.GRWDYCreditActionTag;
		msg.obj = detail.clone(true);
		sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
	}
	
	@OnClick(R.id.fgh_ib_return)
	public void returnback(View view){
		((XCBaseActivity)getActivity()).popupTopFragmentController();
	}
	
	//用户详情
	@OnClick(R.id.fgh_rl_userinfo1)
	public void userinfo1(View view){
		pushFragmentController(new CRMCommonViewFragment(custDetail,1));
	}
	
	//用户详情
	@OnClick(R.id.fgh_rl_userinfo2)
	public void userinfo2(View view){
		pushFragmentController(new CRMCommonViewFragment(custDetail,1));
	}
	
	//客户分析
	@OnClick(R.id.fgh_tv_khfx)
	public void kehufenxi(View view){
		pushFragmentController(new CRMCommonViewFragment(custDetail,2));
	}

	//存款
	@OnClick(R.id.fgh_tv_ck)
	public void cunKuan(View view){
		pushFragmentController(new CRMCunKuanViewFragment(map));
	}
	
	//理财
	@OnClick(R.id.fgh_tv_lc)
	public void licai(View view){
		pushFragmentController(new CRMLiCaiViewFragment(map));
	}
	
	//保险
	@OnClick(R.id.fgh_tv_bx)
	public void baoxian(View view){
		pushFragmentController(new CRMBaoxianViewFragment(map));
	}
	
	//贷款
	@OnClick(R.id.fgh_tv_dk)
	public void daikuan(View view){
		pushFragmentController(new CRMDaikuanViewFragment(map));
	}
	
	//信用卡
	@OnClick(R.id.fgh_tv_xyk)
	public void xinyongka(View view){
		pushFragmentController(new CRMXinyongkaViewFragment(map));
	}
	
	//金贷通
	@OnClick(R.id.fgh_tv_jdt)
	public void jindaitong(View view){
		pushFragmentController(new CRMJinDaiTongViewFragment(map));
	}
	
	//核心账户
	@OnClick(R.id.fgh_tv_hxzh)
	public void hxzh(View view){
		pushFragmentController(new CRMCommonViewFragment(custDetail,3));
	}
}
