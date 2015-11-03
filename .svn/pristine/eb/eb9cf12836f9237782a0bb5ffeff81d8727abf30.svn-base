package com.bea.mbank.edit.step1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import xc.android.utils.XCToolkit;

import com.bea.mbank.mytask.DateDialog;
import com.bea.mbank.util.HeplerUtil;
import com.bea.mbank.util.SubHelperUtil;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.android.widgets.GroupTagView.OnTagButtonSelectedListener;
import com.android.widgets.TitleValueView;
import com.bea.application.ConstDefined;
import com.bea.database.DbManager;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.mbank.R;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.mbank.edit.step1.ZoneDialog.ZoneDialogListener;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;
import com.bea.remote.models.grwdy.AddressBO;
import com.bea.remote.models.grwdy.BorrowerInforBO;
import com.bea.remote.models.grwdy.DetailAddrBO;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Lsq 借款人个人资料
 */
public class BorrowerInforFragment extends ContentBaseFragment<BorrowerInforBO> {
	
	@Override
	protected Class getInfoClass() {
		return BorrowerInforBO.class;
	}
	private static final String tableName = "借款人个人资料";

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater.inflate(R.layout.fm_grwdy_step1_content_borrowerinfo,
				null);
	}

	// 中文姓
	@ViewInject(R.id.Bor_fName)
	TitleValueView TvvfName;
	// 中文名
	@ViewInject(R.id.Bor_lName)
	TitleValueView TvvlName;
	// 英文姓
	@ViewInject(R.id.Bor_efName)
	TitleValueView TvvefName;
	// 英文名
	@ViewInject(R.id.Bor_elName)
	TitleValueView TvvelName;
	
	// 性别
	@ViewInject(R.id.Bor_gtvSexual)
	GroupTagViewEx GtvSexual;
	// 国籍
	@ViewInject(R.id.Bor_gtvNational)
	GroupTagViewEx GtvNational;

	// 证件类型
	@ViewInject(R.id.Bor_gtvDocumentType)
	GroupTagViewEx GtvDocumentType;
	// 证件号码
	@ViewInject(R.id.Bor_DocumentNumber)
	TitleValueView TvvDocumentNumber;
	// 发证国家
	@ViewInject(R.id.Bor_gtvIssuing)
	GroupTagViewEx GtvIssuing;

	// 是否同意我行交叉营销
	@ViewInject(R.id.Bor_gtvIsmarketing)
	GroupTagViewEx GtvIsmarketing;
	// 与我行业务来往情况
	@ViewInject(R.id.Bor_gtvService)
	GroupTagViewEx GtvService;
	// 客户源
	@ViewInject(R.id.Bor_gtvCustomer)
	GroupTagViewEx GtvCustomer;
	// 是否通讯地址
	@ViewInject(R.id.Bor_gtvIsAddress)
	GroupTagViewEx GtvIsAddress;
	// 地址详情
	@ViewInject(R.id.Bor_gtvCompleteAddress)
	TextView TvvCompleteAddress;
	// 邮政编码
	@ViewInject(R.id.Bor_Postcode)
	TitleValueView TvvPostcode;
	// 邮寄方式
	@ViewInject(R.id.Bor_gtvMailingCode)
	GroupTagViewEx GtvMailingCode;
	// Eamil
	@ViewInject(R.id.Bor_Email)
	TitleValueView TvvEmail;
	// 国际区号
	@ViewInject(R.id.Bor_InterCode)
	TitleValueView TvvInterCode;
	// 电话号码
	@ViewInject(R.id.Bor_PhoneNumber)
	TitleValueView TvvPhoneNumber;
	// 金额
	@ViewInject(R.id.Bor_Money)
	TitleValueView TvvMoney;
	// 是否为短信接收号码
	@ViewInject(R.id.Bor_gtvSmsNumber)
	GroupTagViewEx GtvSmsNumber;
	// 教育水平
	@ViewInject(R.id.Bor_gtvEducation)
	GroupTagViewEx GtvEducation;
	// 婚姻状态
	@ViewInject(R.id.Bor_gtvMarryState)
	GroupTagViewEx GtvMarryState;
	// 出生日期
	@ViewInject(R.id.Bor_valueBirthDate)
	TextView TvbirthDate;
	// 证件到期日
	@ViewInject(R.id.Bor_valueDocumentEndDate)
	TextView TvDocuEndDate;
	// 接收银行通知方式
	@ViewInject(R.id.Bor_gtvCbrType)
	GroupTagViewEx GtvCbrType;
	// 接收银行账单方式
	@ViewInject(R.id.Bor_gtvCmrType)
	GroupTagViewEx GtvCmrType;
	
	// is
	@ViewInject(R.id.Bor_gtvis)
	GroupTagViewEx Gtvis;
	@ViewInject(R.id.pannel_title) TextView pannel_title;
	@Override
	protected void onInitContentView(View rootView, BorrowerInforBO info) {
		if (homeBO.isPinAnXinBaoData) {
			pannel_title.setText("“平安信保”个人无抵押消费贷款申请表");
		} else {
			pannel_title.setText("“新时贷”个人无抵押消费贷款申请表");
		}
		TvvMoney.getValueView().addTextChangedListener(textWatcher);
		initGtv(info);
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
	
	@OnClick(R.id.Bor_gtvCompleteAddress)
	public void getDetailAddr(View view) {
		onAddressSel(view, infoBO.homeAddr);
	}

	// 出生日期
	@OnClick(R.id.Bor_valueBirthDate)
	public void birthdateSelect(View view) {
		String s = ((TextView) view).getText().toString();
		new DateDialog(getActivity(), s,
		new com.bea.mbank.mytask.DateDialog.DateDialogListener() {
			@Override
			public void onClick(String date) {
				TvbirthDate.setText(date);
			}
		}).show();
	}

	// 证件到期日
	@OnClick(R.id.Bor_valueDocumentEndDate)
	public void DocumentdateSelect(View view) {
		String s = ((TextView) view).getText().toString();
		new DateDialog(getActivity(), s,
		new com.bea.mbank.mytask.DateDialog.DateDialogListener() {
			@Override
			public void onClick(String date) {
				TvDocuEndDate.setText(date);
			}
		}).show();
	}

	
	
	/** 初始化 */
	private void initGtv(BorrowerInforBO info) { 
		//性别排序
		List<YRL_BASIC_DATA> sexlist = DbManager.codeAyncDatas("Sex");
		List<YRL_BASIC_DATA> sortSexlist = HeplerUtil.sortYRL_BASIC_DATA(sexlist);
		GtvSexual.addButtons(null == sortSexlist?sexlist:sortSexlist, "NAME");
		GtvSexual.setCurrentIndex(info.getMSex());
		
		GtvNational.addButtons(DbManager.codeDatas("CountryCode"), "NAME",info.getMCountry());
		GtvDocumentType.addButtons(DbManager.codeDatas("CertType"), "NAME",info.getMCType());
		GtvIssuing.addButtons(DbManager.codeDatas("CountryCode"), "NAME",info.getMCC());
		GtvIsmarketing.addButtons(DbManager.codeDatas("YesNo"), "NAME",info.getMSale());
		GtvService.addButtons(DbManager.codeDatas("LoanRelationDetailType"),"NAME",info.getMLRDT());
		Gtvis.addButtons(DbManager.codeDatas("YesNo"), "NAME",info.getIs());
		Gtvis.setOnTagButtonSelectedListener(new OnTagButtonSelectedListener() {
			@Override
			public void OnTagButtonSelected(int index, Object btnModel) {
				if (null != Gtvis.getCurrentValue()&&"1".equals(Gtvis.getCurrentValue())) {
					XCToolkit.showToast("根据监管规定，行内关系人不可申请此类贷款，请重新选择是否为东亚银行集团董事/雇员之亲属");
				}
			}
		});
		
		
		if(homeBO.isPinAnXinBaoData){
			if(null == info.getMCS()){ 
				GtvCustomer.addButtons(DbManager.codeDatas("CustomerSource"), "NAME","0065");
			}else {
				GtvCustomer.addButtons(DbManager.codeDatas("CustomerSource"), "NAME",info.getMCS()); 
			}
		}else {
			GtvCustomer.addButtons(DbManager.codeDatas("CustomerSource"), "NAME",info.getMCS()); 
		}
		GtvMarryState.addButtons(DbManager.codeDatas("Marriage"), "NAME",info.getMMType());
		GtvEducation.addButtons(DbManager.codeDatas("EducationExperience"),"NAME",info.getMEE());
		GtvSmsNumber.addButtons(DbManager.codeDatas("YesNo"), "NAME",info.getMRS());
		List<YRL_BASIC_DATA> tongzhifangshi = new ArrayList<YRL_BASIC_DATA>();
		tongzhifangshi.add(new YRL_BASIC_DATA("02","手机短信"));
		GtvCbrType.addButtons(tongzhifangshi, "NAME",info.getMBRType());
		List<YRL_BASIC_DATA> zhangdanfangshi = new ArrayList<YRL_BASIC_DATA>();
		zhangdanfangshi.add(new YRL_BASIC_DATA("02","纸质对账单"));
		GtvCmrType.addButtons(zhangdanfangshi, "NAME",info.getMMRType());
			GtvMailingCode.addButtons(DbManager.codeDatas("MailCode"), "NAME",info.homeAddr.getMSType());
			List<YRL_BASIC_DATA> sftxdzList = new ArrayList<YRL_BASIC_DATA>();
			sftxdzList.add(new YRL_BASIC_DATA("1","住宅地址"));
			sftxdzList.add(new YRL_BASIC_DATA("2","单位地址"));
			
			//通讯地址
			GtvIsAddress.addButtons(sftxdzList, "NAME");
			GtvIsAddress.setCurrentIndex("1");
			if(null != homeBO.getProfessionInfor()
			   && null != homeBO.getProfessionInfor().compAddres 
			   && "1".equals(homeBO.getProfessionInfor().compAddres.getMRAddr()))
				GtvIsAddress.setCurrentIndex("2");
			
			GtvMailingCode.setCurrentIndex(info.homeAddr.getMSType());
				
			TvvPostcode.setValueText(info.homeAddr.getMPC());
			if (homeBO.isPinAnXinBaoData) {
				infoBO.homeAddr.setMAD1("中华人民共和国");
			}
			String s = "";
			if (null != info.homeAddr.getMACountryName())// 国家
				s += info.homeAddr.getMACountryName() + " ";
			if (null != info.homeAddr.getMACityName())// 省份 城市 区县
				s += info.homeAddr.getMACityName() + " ";
			if (null != info.homeAddr.getMAD3())// 楼盘
				s += info.homeAddr.getMAD3() + " ";
			if (null != info.homeAddr.getMAD4())// 地址
				s += info.homeAddr.getMAD4();
			TvvCompleteAddress.setText(s);
			
			TvvfName.setValueText(info.getMFName());
			TvvlName.setValueText(info.getMLName());
			TvvefName.setValueText(info.getMEFName());
			TvvelName.setValueText(info.getMELName());
			TvvDocumentNumber.setValueText(info.getMCNum());
			TvvEmail.setValueText(info.getMEmail());
			
			
			TvvInterCode.setValueText(info.getMIACode());
			TvvPhoneNumber.setValueText(info.getMTNum());
			TvvMoney.setValueText(info.getMMoney());
			// 出生日期
			TvbirthDate.setText(info.getMBDate());
			// 证件到期日
			TvDocuEndDate.setText(info.getMCDate());
			
			 setTitleColor();
	}

	private void setTitleColor(){
		// 性别 
		TextView TvSexual = (TextView) findViewById(R.id.Bor_sexual);
		// 证件类型 
		TextView TvDocumentType = (TextView) findViewById(R.id.Bor_DocumentType);
		// 邮政编码 
		TitleValueView TvPostcode = (TitleValueView) findViewById(R.id.Bor_Postcode);
		// 证件号码 
		TitleValueView TvDocumentNumber = (TitleValueView) findViewById(R.id.Bor_DocumentNumber);
		// 住址详情
		TextView TvBor_CompleteAddress = (TextView) findViewById(R.id.Bor_CompleteAddress);
		// 手机号码 
		TitleValueView TvPhoneNumber = (TitleValueView) findViewById(R.id.Bor_PhoneNumber);
		// 月收入金额 
		TitleValueView TvBor_Money  = (TitleValueView) findViewById(R.id.Bor_Money);
		// 通讯地址 
		TextView TvBor_IsAddress = (TextView) findViewById(R.id.Bor_IsAddress);
		// 出生日期 
		TextView TvBor_BirthDate = (TextView) findViewById(R.id.Bor_BirthDate);
		//中文姓 
		TitleValueView TvBor_fName = (TitleValueView) findViewById(R.id.Bor_fName);
		//中文名 
		TitleValueView TvBor_lName = (TitleValueView) findViewById(R.id.Bor_lName);
		// 学历 
		TextView TvBor_Education = (TextView) findViewById(R.id.Bor_Education);
		// 婚姻状况 
		TextView TvBor_MarryState = (TextView) findViewById(R.id.Bor_MarryState);
		// 银行通知方式
		TextView TvBor_CbrType = (TextView) findViewById(R.id.Bor_CbrType);
		// 账单投递方式
		TextView TvBor_CmrType = (TextView) findViewById(R.id.Bor_CmrType);
		//国籍
		TextView TvBor_National = (TextView) findViewById(R.id.Bor_National);
		//发证国家
		TextView TvBor_Issuing = (TextView) findViewById(R.id.Bor_Issuing);
		//客户源
		TextView TvBor_Customer = (TextView) findViewById(R.id.Bor_Customer);
		
		if (homeBO.isPinAnXinBaoData) {
			TvSexual.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvDocumentType.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvPostcode.setTitleColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvDocumentNumber.setTitleColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_CompleteAddress.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvPhoneNumber.setTitleColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_Money.setTitleColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_IsAddress.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_BirthDate.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_fName.setTitleColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_lName.setTitleColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_Education.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_MarryState.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_CbrType.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_CmrType.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_National.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_Issuing.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			TvBor_Customer.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
		}
	}
	@Override
	public BorrowerInforBO getValueFromInterfaceView() {
		infoBO.setMSex((String) GtvSexual.getCurrentValue());
		infoBO.setMCountry((String) GtvNational.getCurrentValue());
		infoBO.setMCType((String) GtvDocumentType.getCurrentValue());
		infoBO.setMCC((String) GtvIssuing.getCurrentValue());
		infoBO.setMSale((String) GtvIsmarketing.getCurrentValue());
		infoBO.setMLRDT((String) GtvService.getCurrentValue());
		infoBO.setMCS((String) GtvCustomer.getCurrentValue());
		infoBO.setMMType((String) GtvMarryState.getCurrentValue());
		infoBO.setMEE((String) GtvEducation.getCurrentValue());
		infoBO.setMRS((String) GtvSmsNumber.getCurrentValue());
		infoBO.setIs((String) Gtvis.getCurrentValue());
		
		
		infoBO.setMIType("1");
		infoBO.setMITypeName("月工资收入");
		infoBO.setMCurrency("01");//人民币
		infoBO.setMTType("1");//手机
		infoBO.setMTTypeName("手机");
		
		infoBO.homeAddr.setMSType((String) GtvMailingCode.getCurrentValue());
		//是否通讯地址
		infoBO.homeAddr.setMRAddr("1");
		if(null != GtvIsAddress.getCurrentValue() && 
		   "2".equals(GtvIsAddress.getCurrentValue()))
			infoBO.homeAddr.setMRAddr("2");
		
		infoBO.homeAddr.setMPC(TvvPostcode.getValueText());
		infoBO.homeAddr.setMSTypeName(GtvMailingCode.getYRLname());
		infoBO.homeAddr.setMAddr("1");
		infoBO.homeAddr.setMAddrName("住宅地址");
		DetailAddrBO bo = (DetailAddrBO) TvvCompleteAddress.getTag();
		if (bo != null) {
			infoBO.homeAddr.setMACountry(bo.getCountryId());
			infoBO.homeAddr.setMACountryName(bo.getCountryName());
			infoBO.homeAddr.setMACity(bo.getProvinceId() + bo.getCityId() + bo.getZoneId());
			infoBO.homeAddr.setMACityName(bo.getProvinceName() + bo.getCityName() + bo.getZoneName());
			infoBO.homeAddr.setMAD1(bo.getCountryName());
			if (bo.getProvinceName().equals("")||bo.getCityName().equals("")||bo.getZoneName().equals("")) {
				infoBO.homeAddr.setMAD2(null);
			}else {
				infoBO.homeAddr.setMAD2(bo.getProvinceName() + bo.getCityName() + bo.getZoneName());
			}
		
			infoBO.homeAddr.setMAD3(bo.getLp());
			infoBO.homeAddr.setMAD4(bo.getAddr());
		}
		if (homeBO.isPinAnXinBaoData) {
		infoBO.homeAddr.setMAD2(infoBO.homeAddr.getMACityName() + " ");	
		}
		infoBO.setMBRType((String)GtvCbrType.getCurrentValue());
		infoBO.setMMRType((String)GtvCmrType.getCurrentValue());
		//不展示字段取name
		infoBO.setMCTypeName(GtvDocumentType.getYRLname());
		infoBO.setMLRDTName(GtvService.getYRLname());
		infoBO.setMCSName(GtvCustomer.getYRLname());
		infoBO.setMCurrencyName("人民币");
		
		
		infoBO.setMFName(TvvfName.getValueText());
		infoBO.setMLName(TvvlName.getValueText());
		infoBO.setMEFName(TvvefName.getValueText());
		infoBO.setMELName(TvvelName.getValueText());
		infoBO.setMCNum(TvvDocumentNumber.getValueText());
		infoBO.setMEmail(TvvEmail.getValueText());
		infoBO.setMIACode(TvvInterCode.getValueText());
		infoBO.setMTNum(TvvPhoneNumber.getValueText());
		infoBO.setMMoney(TvvMoney.getValueText());
		infoBO.setMRate("1");

		try{
			if(null != infoBO.getMMoney()){
				DecimalFormat df = new DecimalFormat("#.00");
				double mn = 1*Integer.parseInt(infoBO.getMMoney());//汇率*金额
				infoBO.setMRMB(df.format(mn));
				infoBO.setMTMoney(df.format(mn));
				if(null != infoBO.getMIType()){//3年收入  4兼职收入  5其他收入
					if("3".equals(infoBO.getMIType()) ||
					   "4".equals(infoBO.getMIType()) ||
					   "5".equals(infoBO.getMIType()))
						infoBO.setMTMoney(df.format(mn/12));
				}
			}
		}catch(Exception e){
			infoBO.setMRMB("0");
			infoBO.setMTMoney("0");
		}

		infoBO.setMBDate(TvbirthDate.getText().toString());
		infoBO.setMCDate(TvDocuEndDate.getText().toString());
		return infoBO;
	}

	@Override
	public boolean isRequiredFieldInputed(BorrowerInforBO info) {
		SubHelperUtil.setTableName(tableName);
		if (null == info) {
			XCToolkit.showToast("请填写“借款人个人资料”信息");
			return false;
		} else {
			if (!SubHelperUtil.showCHToast(info.getMFName(), "中文姓")) {
				return false;
			}
			if (!SubHelperUtil.showCHToast(info.getMLName(), "中文名")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getMEFName(), "英文姓")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getMELName(), "英文名")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMSex(), "性别")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMCountry(), "国籍")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMBDate(), "出生日期")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMCType(), "证件类型")) {
				return false;
			}
			if (!"".equals(info.getMCType()) && info.getMCType().toString().equals("Ind1010")) {
				if (!SubHelperUtil.showIDToast(info.getMCNum(), "证件号码")) {
					return false;
				}
			}else {
				if (info.getMCNum().toString().equals("")) {
					XCToolkit.showToast("请输入"+tableName+"@证件号码");
					return false;
				}
			}
			
			if (!SubHelperUtil.showToast(info.getMCC(), "发证国家")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMCDate(), "证件到期日")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMSale(), "是否同意我行交叉营销")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMLRDT(), "与我行业务来往情况")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMCS(), "客户源")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMBRType(), "接收银行通知方式")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMMRType(), "接收银行账单方式")) {
				return false;
			}
			if (!SubHelperUtil.showEmailToast(info.getMEmail(), "Email")) {
				return false;
			}
			if (!homeBO.isPinAnXinBaoData) {
				if (!SubHelperUtil.showToast(info.homeAddr.getMAddr(), "住宅地址类型")) {
					return false;
				}
				if (!SubHelperUtil.showToast(info.homeAddr.getMRAddr(), "是否通讯地址")) {
					return false;
				}
			} 
			if (!SubHelperUtil.showToast(info.homeAddr.getMAD1(), "住宅详情-国家")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.homeAddr.getMAD2(), "住宅详情-省/市/区")) {
				return false;
			}
//			if (!SubHelperUtil.showToast(info.homeAddr.getMAD3(), "住宅详情-楼盘")) {
//				return false;
//			}
//			if (!SubHelperUtil.showToast(info.homeAddr.getMAD4(), "住宅详情-详细地址")) {
//				return false;
//			}
			if (!SubHelperUtil.showPcodeToast(info.homeAddr.getMPC(), "邮政编码")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.homeAddr.getMSType(), "邮寄方式")) {
				return false;
			}
			if (!SubHelperUtil.showNumToast(info.getMIACode(), "国际区号")) {
				return false;
			}
			if (!SubHelperUtil.showPhoneToast(info.getMTNum(), "手机号码")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMRS(), "是否为短信接收号码")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMEE(), "教育水平")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMMType(), "婚姻状况")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMIType(), "收入类型")) {
				return false;
			}
			if (!SubHelperUtil.showNumDecimalToast(info.getMMoney(), "金额")) {
				return false;
			}
			if (!SubHelperUtil.showNumDecimalToast(info.getMRMB(), "折算为人民币金额")) {
				return false;
			}
			if (info.getIs() != null && info.getIs().equals("1")) {
				XCToolkit.showToast("根据监管规定，行内关系人不可申请此类贷款，请重新选择是否为东亚银行集团董事/雇员之亲属@"+tableName);
				return false;
			}
		}
		SubHelperUtil.setTableName("");
		return true;
	}
}
