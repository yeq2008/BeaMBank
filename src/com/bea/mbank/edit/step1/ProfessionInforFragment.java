package com.bea.mbank.edit.step1;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import xc.android.utils.XCToolkit;

import com.android.widgets.GroupTagView.OnTagButtonSelectedListener;
import com.bea.mbank.mytask.DateDialog;
import com.bea.mbank.mytask.DateDialog.DateDialogListener;
import com.bea.mbank.util.HeplerUtil;
import com.bea.mbank.util.SubHelperUtil;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.bea.application.ConstDefined;
import com.bea.database.DbManager;
import com.bea.mbank.R;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.mbank.edit.step1.ZoneDialog.ZoneDialogListener;
import com.bea.remote.models.grwdy.AddressBO;
import com.bea.remote.models.grwdy.DetailAddrBO;
import com.bea.remote.models.grwdy.ProfessionInforBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.graphics.Color;
import android.text.method.NumberKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author fanglinhao 借款人职业信息
 */
public class ProfessionInforFragment extends ContentBaseFragment<ProfessionInforBO> {
	@Override
	protected Class getInfoClass() {
		return ProfessionInforBO.class;
	}
	private static final String tableName = "借款人职业信息";
	// 是否本行员工
	@ViewInject(R.id.fgcp_gtv_jbea)
	GroupTagViewEx gtv_jbea;
	// 员工所在部门
	@ViewInject(R.id.fgcp_et_jbead)
	GroupTagViewEx et_jbead;
	// 员工工号
	@ViewInject(R.id.fgcp_et_jbeaid)
	EditText et_jbeaid;
	//员工所在部门,员工工号的RelativeLayout
	@ViewInject(R.id.fgcp_rl_jbead)
	RelativeLayout rl_jbead;
	// 雇佣类型
	@ViewInject(R.id.fgcp_gtv_jet)
	GroupTagViewEx gtv_jet;
	// 经营实体类型
	@ViewInject(R.id.fgcp_tv_jot)
	TextView tv_jot;
	// 经营实体类型
	@ViewInject(R.id.fgcp_gtv_jot)
	GroupTagViewEx gtv_jot;
	// 注册币种
	@ViewInject(R.id.fgcp_gtv_jcurrency)
	GroupTagViewEx gtv_jcurrency;
	// 经营实体类型注册资金
	@ViewInject(R.id.fgcp_et_jcapital)
	EditText et_jcapital;
	// 单位名称
	@ViewInject(R.id.fgcp_et_jcname)
	EditText et_jcname;
	// 单位性质
	@ViewInject(R.id.fgcp_tv_jctpye)
	TextView tv_jctpye;
	// 单位性质
	@ViewInject(R.id.fgcp_gtv_jctpye)
	GroupTagViewEx gtv_jctpye;
	// 单位地址
	@ViewInject(R.id.fgcp_tv_jcaddr_content)
	TextView tv_jcaddr_content;
	// 职业
	@ViewInject(R.id.fgcp_gtv_jobea)
	GroupTagViewEx gtv_jobea;
	// 职位
	@ViewInject(R.id.fgcp_gtv_jposition)
	GroupTagViewEx gtv_jposition;
	// 工作年限
	@ViewInject(R.id.fgcp_et_jyear)
	EditText et_jyear;
	// 行业分类（中国）
	@ViewInject(R.id.fgcp_tv_jitype)
	TextView tv_jitype;
	// 行业分类（中国）
	@ViewInject(R.id.fgcp_gtv_jitype)
	GroupTagViewEx gtv_jitype;
	// 行业分类（香港）
	@ViewInject(R.id.fgcp_tv_jitypehk)
	TextView tv_jitypehk;
	// 行业分类（香港）
	@ViewInject(R.id.fgcp_gtv_jitypehk)
	GroupTagViewEx gtv_jitypehk;
	@ViewInject(R.id.fgcp_tv_jidate_content)
	TextView tv_jidate_content;
	//邮政编码
	@ViewInject(R.id.fgcp_et_postcode)
	EditText et_code; 
	// 邮寄方式
	@ViewInject(R.id.fgcp_gtvMailingCode)
	GroupTagViewEx gtv_MailingCode; 
	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater.inflate(R.layout.fm_grwdy_step1_content_professioninfo, null);
	}

	@Override
	protected void onInitContentView(View rootView, ProfessionInforBO info) {
		et_jcapital.addTextChangedListener(textWatcher);
		initGtv(info);
		et_jbeaid.setKeyListener(new NumberKeyListener(){

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
		});
	}

	private void onAddressSel(final View view, AddressBO address) {
		DetailAddrBO detailAddr = (DetailAddrBO) view.getTag();
		if (null == detailAddr) {
			detailAddr = new DetailAddrBO();
			// mad1 国家 mad2 省市区 mad3 楼盘 md4 地址
			detailAddr.setLp(address.getMAD3());//
			detailAddr.setAddr(address.getMAD4());//
			detailAddr.setCountryId(address.getMACountry());
			detailAddr.setCountryName(address.getMACountryName());
			detailAddr.setAllid(address.getMACity());
		}
		
		new ZoneDialog(getActivity(), detailAddr, new ZoneDialogListener() {
			@Override
			public void onClick(DetailAddrBO addr) {
				view.setTag(addr);
				StringBuilder sBuilder = new StringBuilder();
				sBuilder.append(addr.getCountryName() == null ? ""
						: (addr.getCountryName() + " "));
				sBuilder.append(addr.getProvinceName() == null ? ""
						: (addr.getProvinceName() + " "));
				sBuilder.append(addr.getCityName() == null ? "" : (addr
						.getCityName() + " "));
				sBuilder.append(addr.getZoneName() == null ? "" : (addr
						.getZoneName() + " "));
				sBuilder.append(addr.getLp() == null ? "" : (addr
						.getLp() + " "));
				sBuilder.append(addr.getAddr() == null ? "" : addr
						.getAddr());
				((TextView)view).setText(sBuilder.toString());
			}
		}).show();
	}
	
	@OnClick(R.id.fgcp_tv_jcaddr_content)
	public void getDetailAddr(View view) {
		onAddressSel(view, infoBO.compAddres);
	}

	@Override
	public ProfessionInforBO getValueFromInterfaceView() {
		infoBO.setJBEA((String) gtv_jbea.getCurrentValue());
		infoBO.setJET((String) gtv_jet.getCurrentValue());
		infoBO.setJCurrency((String) gtv_jcurrency.getCurrentValue());
		infoBO.setJCurrencyName(gtv_jcurrency.getYRLname());
		infoBO.setJCTpye((String) gtv_jctpye.getCurrentValue());
		infoBO.setJCTpyeName(gtv_jctpye.getYRLname());
		infoBO.setJIType((String) gtv_jitype.getCurrentValue());
		infoBO.setJITypeName(gtv_jitype.getYRLname());
		infoBO.setJITypeHK((String) gtv_jitypehk.getCurrentValue());
		infoBO.setJITypeHKName(gtv_jitypehk.getYRLname());
		infoBO.setJOT((String) gtv_jot.getCurrentValue());
		infoBO.setJOBEA((String)gtv_jobea.getCurrentValue());
		infoBO.setJPosition((String)gtv_jposition.getCurrentValue());
		infoBO.setJBEAD((String)et_jbead.getCurrentValue());
		infoBO.setJCapital(et_jcapital.getText().toString());
		infoBO.setJCName(et_jcname.getText().toString());
		infoBO.compAddres.setMSType((String) gtv_MailingCode.getCurrentValue());
		infoBO.compAddres.setMSTypeName(gtv_MailingCode.getYRLname());
		infoBO.compAddres.setMAddr("2");
		infoBO.compAddres.setMAddrName("单位地址");  
		infoBO.compAddres.setMPC(et_code.getText().toString());
		
		//是否通讯地址
		if(null != homeBO.getBorrowerInfor() && 
		   null != homeBO.getBorrowerInfor().homeAddr){
			infoBO.compAddres.setMRAddr("1");
			if(null != homeBO.getBorrowerInfor().homeAddr.getMRAddr() &&
			   "1".equals(homeBO.getBorrowerInfor().homeAddr.getMRAddr()))
			   infoBO.compAddres.setMRAddr("2");
		}
		if (homeBO.isPinAnXinBaoData) {
			infoBO.compAddres.setMAD1("中华人民共和国");
		}
		//单位地址
		DetailAddrBO bo = (DetailAddrBO) tv_jcaddr_content.getTag();
		if (bo != null) {
			infoBO.compAddres.setMACountry(bo.getCountryId());
			infoBO.compAddres.setMACountryName(bo.getCountryName());
			infoBO.compAddres.setMACity(bo.getProvinceId() + bo.getCityId() + bo.getZoneId());
			infoBO.compAddres.setMACityName(bo.getProvinceName() + bo.getCityName() + bo.getZoneName());
			infoBO.compAddres.setMAD1(bo.getCountryName());
			infoBO.compAddres.setMAD2(bo.getProvinceName() + bo.getCityName() + bo.getZoneName());
			infoBO.compAddres.setMAD3(bo.getLp());
			infoBO.compAddres.setMAD4(bo.getAddr());
		}
		if (homeBO.isPinAnXinBaoData) {
			infoBO.compAddres.setMAD2(infoBO.compAddres.getMACityName() + " ");	
			}
		infoBO.setJIDate(tv_jidate_content.getText().toString());
		infoBO.setJYear(et_jyear.getText().toString());
		infoBO.setJBEAID(et_jbeaid.getText().toString());
		return infoBO;
	}

	

	/** 初始化选择项*/
	private void initGtv(final ProfessionInforBO info) { 
		gtv_jbea.addButtons(DbManager.codeDatas("YesNo"), "NAME",info.getJBEA());
		gtv_jet.addButtons(DbManager.codeDatas("EmploymentType"), "NAME",info.getJET());
		
		if (null != info.getJBEA() && info.getJBEA().equals("1")) {
			gtv_jitype.addButtons(DbManager.codeDatas("IndustryType"), "NAME","J01");
			gtv_jitypehk.addButtons(DbManager.codeDatas("IndustryTypeHK"), "NAME","40101021");
			gtv_jot.addButtons(DbManager.codeDatas("OperateType"), "NAME","020");
			gtv_jctpye.addButtons(DbManager.codeDatas("UnitProperty"), "NAME","05");
			gtv_jot.setVisibility(View.GONE);
			tv_jot.setVisibility(View.GONE);
			gtv_jctpye.setVisibility(View.GONE);
			tv_jctpye.setVisibility(View.GONE);
		}else if (null != info.getJBEA() && info.getJBEA().equals("2")) {
			gtv_jitype.addButtons(DbManager.codeDatas("IndustryType"), "NAME",info.getJIType());
			gtv_jitypehk.addButtons(DbManager.codeDatas("IndustryTypeHK"), "NAME",info.getJITypeHK());
			gtv_jot.addButtons(DbManager.codeDatas("OperateType"), "NAME",info.getJOT());
			gtv_jctpye.addButtons(DbManager.codeDatas("UnitProperty"), "NAME",info.getJCTpye());
			gtv_jot.setVisibility(View.VISIBLE);
			tv_jot.setVisibility(View.VISIBLE);
			gtv_jctpye.setVisibility(View.VISIBLE);
			tv_jctpye.setVisibility(View.VISIBLE);
		}
		
		gtv_jcurrency.addButtons(DbManager.codeDatas("Currency"), "NAME",info.getJCurrency());
		gtv_jobea.addButtons(DbManager.codeDatas("OccupationBEA"), "NAME",info.getJOBEA());
		gtv_jposition.addButtons(DbManager.codeDatas("Position"), "NAME",info.getJPosition());
		gtv_MailingCode.addButtons(DbManager.codeDatas("MailCode"), "NAME",info.compAddres.getMSType());
		
		et_jbead.addButtons(DbManager.getOrgs(), "ORG_NAME",info.getJBEAD());
		if (info != null) {
			et_jbead.setCurrentIndex(info.getJBEAD());
			et_jcapital.setText(info.getJCapital());
			et_jcname.setText(info.getJCName());
			
			et_code.setText(info.compAddres.getMPC());
			String s = "";
			if (null != info.compAddres.getMACountryName())//国家
				s += info.compAddres.getMACountryName() + " ";
			if (null != info.compAddres.getMACityName())//省份 城市 区县
				s += info.compAddres.getMACityName() + " ";
			if (null != info.compAddres.getMAD3())//楼盘
				s += info.compAddres.getMAD3() + " ";
			if (null != info.compAddres.getMAD4())//地址
				s += info.compAddres.getMAD4();
			tv_jcaddr_content.setText(s);
			
			et_jyear.setText(info.getJYear());
			et_jyear.setEnabled(false);//工作年限设置为不可编辑
			tv_jidate_content.setText(info.getJIDate());
			et_jbeaid.setText(info.getJBEAID());
		}
		
		int tmp = 0;
		try {
			tmp = Integer.parseInt(info.getJBEA());
		} catch (Exception e) {tmp = 0;}
		if(tmp==2){
			rl_jbead.setVisibility(View.GONE);
		}
		
		//如果是本行员工则要输入员工所在部门和员工工号，否则不用输入。
		gtv_jbea.setOnTagButtonSelectedListener(new OnTagButtonSelectedListener(){
			@Override
			public void OnTagButtonSelected(int index, Object btnModel) {
				if(index==1){
					gtv_jitype.addButtons(DbManager.codeDatas("IndustryType"), "NAME",info.getJIType());
					gtv_jitypehk.addButtons(DbManager.codeDatas("IndustryTypeHK"), "NAME",info.getJITypeHK());
					gtv_jot.addButtons(DbManager.codeDatas("OperateType"), "NAME",info.getJOT());
					gtv_jctpye.addButtons(DbManager.codeDatas("UnitProperty"), "NAME",info.getJCTpye());
					rl_jbead.setVisibility(View.GONE);
					gtv_jot.setVisibility(View.VISIBLE);
					tv_jot.setVisibility(View.VISIBLE);
					gtv_jctpye.setVisibility(View.VISIBLE);
					tv_jctpye.setVisibility(View.VISIBLE);
				}else if(index==0){
					rl_jbead.setVisibility(View.VISIBLE);
					gtv_jitype.addButtons(DbManager.codeDatas("IndustryType"), "NAME","J01");
					gtv_jitypehk.addButtons(DbManager.codeDatas("IndustryTypeHK"), "NAME","40101021");
					gtv_jot.addButtons(DbManager.codeDatas("OperateType"), "NAME","020");
					gtv_jctpye.addButtons(DbManager.codeDatas("UnitProperty"), "NAME","05");
					gtv_jot.setVisibility(View.GONE);
					tv_jot.setVisibility(View.GONE);
					gtv_jctpye.setVisibility(View.GONE);
					tv_jctpye.setVisibility(View.GONE);
				}
			}
		});	
		settitleColor(); 
	}
	/**设置平安信保特殊字段title颜色*/
	private void settitleColor(){
		// 职业 
		TextView title_jobea = (TextView) findViewById(R.id.fgcp_tv_jobea);
		// 行业分类（中国） 
		TextView title_jitype = (TextView) findViewById(R.id.fgcp_tv_jitype);
		// 行业分类（中国） 
		TextView title_jitypehk = (TextView) findViewById(R.id.fgcp_tv_jitypehk);
		// 单位性质 
		TextView title_jctpye = (TextView) findViewById(R.id.fgcp_tv_jctpye);
		// 单位地址
		TextView title_jcaddr = (TextView) findViewById(R.id.fgcp_tv_jcaddr);
		// 邮政编码 
		TextView title_postcode= (TextView) findViewById(R.id.fgcp_tv_postcode);
		// 单位名称 
		TextView title_jcname = (TextView) findViewById(R.id.fgcp_tv_jcname);
		if (homeBO.isPinAnXinBaoData) {
			title_jobea.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			title_jitype.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			title_jctpye.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			title_postcode.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			title_jcname.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			title_jcaddr.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			title_jitypehk.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
		}
	}
	Date lastdate = null;
	@OnClick(R.id.fgcp_tv_jidate_content)
	public void dateSelect(View view) {
		DateDialog dialog = new DateDialog(getActivity(), 
				((TextView) view).getText().toString(), 
				new DateDialogListener() {
			@Override
			public void onClick(String date) { 
				tv_jidate_content.setText(date);
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				try {
					lastdate = format.parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				HeplerUtil util = new HeplerUtil();
				int month = util.getMonthNum(new Date(), lastdate);
				if (month<0) {
					et_jyear.setText("");
				}else {
					et_jyear.setText(""+month);
				}
			}
		});
		dialog.show();
	}

	@Override
	public boolean isRequiredFieldInputed(ProfessionInforBO info) {
		SubHelperUtil.setTableName(tableName);
		if (null == info) {
			XCToolkit.showToast("请填写“借款人职业”信息");
			return false;
		}
		if(!SubHelperUtil.showToast(info.getJBEA(), "是否本行员工"))
			return false;
		if(!SubHelperUtil.showToast(info.getJET(), "雇佣类型"))
			return false;
		// 雇佣类型为自雇，该字段必输
		if (null != info.getJET() && "2".equals(info.getJET())) {
			if (!SubHelperUtil.showToast(info.getJOT(), "经营实体类型"))
				return false;
			if (!SubHelperUtil.showToast(info.getJCurrency(), "注册币种"))
				return false;
			if (!SubHelperUtil.showNumDecimalToast(info.getJCapital(),"经营实体类型注册资金"))
				return false;
			
		} 
		if (!SubHelperUtil.showNormal(info.getJCName(), "单位名称"))
			return false;
		if(!SubHelperUtil.showToast(info.getJCTpye(), "单位性质"))
			return false;
		if(!SubHelperUtil.showToast(info.compAddres.getMACountry(), "国家"))//国家
			return false;
		if(null == info.compAddres.getMACity() || info.compAddres.getMACity().length()<5){
			XCToolkit.showToast("请选择省市区/县");
			return false;
		}
//		if (!SubHelperUtil.showToast(info.compAddres.getMAD3(), "住宅详情-楼盘")) {
//			return false;
//		}
//		if (!SubHelperUtil.showToast(info.compAddres.getMAD4(), "住宅详情-详细地址")) {
//			return false;
//		}
		if (!SubHelperUtil.showPcodeToast(info.compAddres.getMPC(), "邮政编码")) {
			return false;
		}
		if (!SubHelperUtil.showToast(info.compAddres.getMSType(), "邮寄方式")) {
			return false;
		}
		if (!SubHelperUtil.showToast(info.getJIType(), "行业分类(中国)"))
			return false;
		if (!SubHelperUtil.showToast(info.getJITypeHK(), "行业分类(香港)"))
			return false;
		if (!SubHelperUtil.showNormal(info.getJOBEA(), "职业"))
			return false;
		if (!SubHelperUtil.showNormal(info.getJPosition(), "职位"))
			return false;
		if (!SubHelperUtil.showNormal(info.getJIDate(), "入职日期"))
			return false;
		if (!SubHelperUtil.showNormal(info.getJYear(), "正确的入职日期"))
			return false;
		// 如果是员工，该字段必输
		if (null != info.getJBEA() && "1".equals(info.getJBEA())) {
			if (!SubHelperUtil.showNormal(info.getJBEAID(), "员工工号"))
				return false;
			if (!SubHelperUtil.showNormal(info.getJBEAD(), "员工所在部门"))
				return false;
		}
		SubHelperUtil.setTableName("");
		return true;
	}
}
