package com.bea.mbank.edit.step1;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xc.android.utils.XCToolkit;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.widgets.GroupTagView;
import com.android.widgets.TitleValueView;
import com.bea.application.BeaAppSettings;
import com.bea.database.DbManager;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.mbank.R;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.mbank.edit.step1.ZoneDialog.ZoneDialogListener;
import com.bea.mbank.mytask.DateDialog;
import com.bea.mbank.mytask.DateDialog.DateDialogListener;
import com.bea.mbank.util.HeplerUtil;
import com.bea.mbank.util.SubHelperUtil;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.bea.remote.models.grwdy.DetailAddrBO;
import com.bea.remote.models.grwdy.JointInforBO;
import com.bea.remote.models.grwdy.jointAddressBO;
import com.bea.remote.models.user.UserInfoBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

/**
 * @author xuruian 共同借款人资料
 */
public class JointInforFragment extends ContentBaseFragment<JointInforBO> {
	@Override
	protected Class getInfoClass() {
		return JointInforBO.class;
	}

	private static final String tableName = "共同借款人个人资料";
	// 中文姓
	@ViewInject(R.id.join_chName)
	private TitleValueView mJ_chName;
	// 中文名
	@ViewInject(R.id.join_chNamesub)
	private TitleValueView mJ_chNamesub;
	// 英文姓
	@ViewInject(R.id.join_pinName)
	private TitleValueView mJ_pinName;
	// 英文名
	@ViewInject(R.id.join_pinNamesub)
	private TitleValueView mJ_pinNamesub;
	// 与借款人关系
	@ViewInject(R.id.join_relationsview)
	private GroupTagViewEx mJ_Relajiek;
	// 性别
	@ViewInject(R.id.join_sexSelect)
	private GroupTagViewEx mJ_Sexselec;
	// 国籍
	@ViewInject(R.id.join_countryview)
	GroupTagViewEx mJ_guoji;
	// 出生日期
	@ViewInject(R.id.join_birthdayview)
	TextView mJ_birthday;
	// 证件类型
	@ViewInject(R.id.join_cardlei)
	GroupTagViewEx mJ_cardlei;
	// 证件号码
	@ViewInject(R.id.join_cardNumber)
	TitleValueView mJ_cardnum;
	// 发证国家
	@ViewInject(R.id.join_countryof)
	GroupTagViewEx mJ_cardconuty;
	// 证件到期日
	@ViewInject(R.id.dateview)
	TextView mJ_cardoutdate;
	// 电子邮箱
	@ViewInject(R.id.join_joinemail)
	TitleValueView mJ_email;
	// 地址所属国家地区，省，市，地址详情
	@ViewInject(R.id.addressview)
	TextView mJ_addressinf;
	// 邮政编码
	@ViewInject(R.id.join_pcode)
	TitleValueView mJ_pcode;
	// 邮寄方式 42
	@ViewInject(R.id.join_youjifs)
	GroupTagViewEx youjifs;
	// 是否通讯地址
	@ViewInject(R.id.join_ansSelect_home)
	GroupTagViewEx mJ_isdadreshome;
	// 地址所属国家地区，省，市，地址详情
	@ViewInject(R.id.addressview_home)
	TextView mJ_addressinfhome;
	// 邮政编码
	@ViewInject(R.id.join_pcode_home)
	TitleValueView mJ_pcodehome;
	// 邮寄方式 42
	@ViewInject(R.id.join_youjifs_home)
	GroupTagViewEx youjifshome;
	// 雇佣类型
	@ViewInject(R.id.join_exployeView)
	GroupTagViewEx mJ_exployei;
	
	// 经营实体类型
	@ViewInject(R.id.join_entity)
	TextView Tv_entitylei;
	// 经营实体类型
	@ViewInject(R.id.join_entitylei)
	GroupTagViewEx mJ_entitylei;
	// 注册币种
	@ViewInject(R.id.join_moneyView)
	GroupTagViewEx mJ_zcbizhong;
	// 经营实体类型注册资金
	@ViewInject(R.id.join_moneycont)
	TitleValueView mJ_zczijin;
	// 单位名称，
	@ViewInject(R.id.join_company)
	TitleValueView mJ_companyname;
	// 单位性质
	@ViewInject(R.id.join_companypro)
	TextView Tv_companpro;
	// 单位性质
	@ViewInject(R.id.join_companyproview)
	GroupTagViewEx mJ_companpro;
	// 单位地址
	@ViewInject(R.id.tv_ccaddr_content)
	TextView ccaddr_content;
	// 行业分类中国
	@ViewInject(R.id.join_worklei_ch_view)
	GroupTagViewEx mJ_workleich;
	// 行业分类香港
	@ViewInject(R.id.join_worklei_hk_view)
	GroupTagViewEx mJ_workleihk;
	// 职业
	@ViewInject(R.id.join_zhiyeview)
	GroupTagViewEx mJ_zhiye;
	// 职位
	@ViewInject(R.id.join_zhiweiview)
	GroupTagViewEx mJ_zhiwei;
	// 入职日期
	@ViewInject(R.id.ruzhidate)
	TextView mJ_ruzhidate;
	// 工作年限
	@ViewInject(R.id.join_workyear)
	EditText mJ_workyear;
	// 是否本行员工
	@ViewInject(R.id.join_of_bank_yuangongview)
	GroupTagViewEx mJ_bhyuangong;
	// 员工所在部门
	@ViewInject(R.id.join_of_bumen_yuangong)
	TitleValueView mJ_bumen;
	// 员工工号
	@ViewInject(R.id.join_of_yugongghao)
	TitleValueView mJ_ygongnum;
	// 教育水平 37
	@ViewInject(R.id.join_eduspingview)
	GroupTagViewEx eduview;
	// 婚姻状况 38
	@ViewInject(R.id.join_marryview)
	GroupTagViewEx marryview;
	// 是否同意我行交叉营销 39
	@ViewInject(R.id.join_banjcha_view)
	GroupTagViewEx banjchaview;
	// 与我行业务往来情况 40
	@ViewInject(R.id.join_yewuwlai_view)
	GroupTagViewEx yewuwlaiview;
	// 客户源 41
	@ViewInject(R.id.join_kehuyuan_view)
	GroupTagViewEx kehuyuaniview;
	// 国际区号 44
	@ViewInject(R.id.join_guojiquhao)
	TitleValueView guojiquhao;
	// 电话号码 46
	@ViewInject(R.id.join_phonum)
	TitleValueView phonum;
	// 是否为短信接收号码48
	@ViewInject(R.id.join_sfdxinjieshou_view)
	GroupTagViewEx sfdxinjieshou;
	// 金额 51
	@ViewInject(R.id.join_jin_e)
	TitleValueView jin_e;
	// 接收银行通知方式
	@ViewInject(R.id.join_gtvCbrType)
	GroupTagViewEx gtvCbrType;
	// 是否同意我行交叉营销 39
	@ViewInject(R.id.join_gtvCmrType)
	GroupTagViewEx gtvCmrType;

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater.inflate(R.layout.fm_grwdy_step1_content_jointinfo, null);
	}

	@Override
	protected void onInitContentView(View rootView, JointInforBO info) {
		jin_e.getValueView().addTextChangedListener(textWatcher);
		mJ_zczijin.getValueView().addTextChangedListener(textWatcher);
		initGtv(info);
	}

	@Override
	public JointInforBO getValueFromInterfaceView() {
		//证件类型
		infoBO.setCCType((String)mJ_cardlei.getCurrentValue());
		//单位性质
		infoBO.setCCTpye((String) mJ_companpro.getCurrentValue());
		infoBO.setCCTpyeName(mJ_companpro.getYRLname());
		infoBO.setCRelationship((String) mJ_Relajiek.getCurrentValue());
		infoBO.setCCountry((String) mJ_guoji.getCurrentValue());
		infoBO.setCBDate(mJ_birthday.getText().toString());
		infoBO.setCCC((String) mJ_cardconuty.getCurrentValue());
		infoBO.setCCDate(mJ_cardoutdate.getText().toString());
		infoBO.setCEE((String) eduview.getCurrentValue());
		infoBO.setCMType((String) marryview.getCurrentValue());
		infoBO.setCSale((String) banjchaview.getCurrentValue());
		infoBO.setCLRDT((String) yewuwlaiview.getCurrentValue());
		infoBO.setCLRDTName(yewuwlaiview.getYRLname());
		infoBO.setCCS((String) kehuyuaniview.getCurrentValue());
		infoBO.setCCSName(kehuyuaniview.getYRLname());

		infoBO.homeAddr.setCRAddr((String) mJ_isdadreshome.getCurrentValue());
		infoBO.homeAddr.setCSType((String) youjifshome.getCurrentValue());
		infoBO.homeAddr.setCPC(mJ_pcodehome.getValueText().toString());
		infoBO.homeAddr.setCSTypeName(youjifshome.getYRLname());
		infoBO.homeAddr.setCAddrName("住宅地址");
		// 个人资料 住宅地址详情
		DetailAddrBO bo = (DetailAddrBO) mJ_addressinfhome.getTag();
		if (bo != null) {
			infoBO.homeAddr.setCACountry(bo.getCountryId());
			infoBO.homeAddr.setCACountryName(bo.getCountryName());
			infoBO.homeAddr.setCCCity(bo.getProvinceId() + bo.getCityId()
					+ bo.getZoneId());
			infoBO.homeAddr.setCCCityName(bo.getProvinceName()
					+ bo.getCityName() + bo.getZoneName());
			infoBO.homeAddr.setCAD1(bo.getCountryName());
			infoBO.homeAddr.setCAD2(bo.getProvinceName() + bo.getCityName()
					+ bo.getZoneName());
			infoBO.homeAddr.setCAD3(bo.getLp());
			infoBO.homeAddr.setCAD4(bo.getAddr());
		}
		
		infoBO.censusAddr.setCSType((String) youjifs.getCurrentValue());
		infoBO.censusAddr.setCPC(mJ_pcode.getValueText().toString());
		infoBO.censusAddr.setCSTypeName(youjifs.getYRLname());
		infoBO.censusAddr.setCAddrName("户籍地址");
		// 个人资料 户籍地址详情
		bo = (DetailAddrBO) mJ_addressinf.getTag();
		if (bo != null) {
			infoBO.censusAddr.setCACountry(bo.getCountryId());
			infoBO.censusAddr.setCACountryName(bo.getCountryName());
			infoBO.censusAddr.setCCCity(bo.getProvinceId() + bo.getCityId()
					+ bo.getZoneId());
			infoBO.censusAddr.setCCCityName(bo.getProvinceName()
					+ bo.getCityName() + bo.getZoneName());
			infoBO.censusAddr.setCAD1(bo.getCountryName());
			if (bo.getProvinceName().equals("")||bo.getCityName().equals("")||bo.getZoneName().equals("")) {
				infoBO.censusAddr.setCAD2(null);
			}else {
				infoBO.censusAddr.setCAD2(bo.getProvinceName() + bo.getCityName()+ bo.getZoneName());
			}
			 	
			infoBO.censusAddr.setCAD3(bo.getLp());
			infoBO.censusAddr.setCAD4(bo.getAddr());
		}
		
		String tmp = (String)mJ_isdadreshome.getCurrentValue();
		if(null != tmp){
			if("1".equals(tmp)){
				infoBO.homeAddr.setCRAddr("1");
				infoBO.censusAddr.setCRAddr("2");
			}
			if("2".equals(tmp)){
				infoBO.homeAddr.setCRAddr("2");
				infoBO.censusAddr.setCRAddr("1");
			}
		}

		// 职业资料 地址详情
		DetailAddrBO addrBO = (DetailAddrBO) ccaddr_content.getTag();
		if (null != addrBO) {
			infoBO.setCCCountry(addrBO.getCountryId());
			infoBO.setCCCountryName(addrBO.getCountryName());
			infoBO.setCCity(addrBO.getProvinceId() + addrBO.getCityId()
					+ addrBO.getZoneId());
			infoBO.setCCityName(addrBO.getProvinceName() + addrBO.getCityName()
					+ addrBO.getZoneName());
			infoBO.setCCAddr(addrBO.getAddr());
		}
		
		infoBO.setCMTType("1");
		infoBO.setCMTTypeName("手机");
		infoBO.setCRS((String) sfdxinjieshou.getCurrentValue());
		infoBO.setCIType("1");
		infoBO.setCITypeName("月工资收入");
		infoBO.setCCurrency("01");
		infoBO.setCCurrencyName("人民币");
		infoBO.setCRCurrency((String) mJ_zcbizhong.getCurrentValue());
		infoBO.setCRCurrencyName(mJ_zcbizhong.getYRLname());
		infoBO.setCET((String) mJ_exployei.getCurrentValue());
		infoBO.setCOT((String) mJ_entitylei.getCurrentValue());
		infoBO.setCCTpye((String) mJ_companpro.getCurrentValue());
		infoBO.setCCTpyeName(mJ_companpro.getYRLname());
		
		infoBO.setCBRType((String) gtvCbrType.getCurrentValue());
		infoBO.setCMRType((String) gtvCmrType.getCurrentValue());

		infoBO.setCIdType((String) mJ_workleich.getCurrentValue());
		infoBO.setCIdTypeName(mJ_workleich.getYRLname());
		infoBO.setCITypeHK((String) mJ_workleihk.getCurrentValue());
		infoBO.setCITypeHKName(mJ_workleihk.getYRLname());
		infoBO.setCOBEA((String) mJ_zhiye.getCurrentValue());
		infoBO.setCPosition((String) mJ_zhiwei.getCurrentValue());
		infoBO.setCBEA((String) mJ_bhyuangong.getCurrentValue());
		infoBO.setCSex((String) mJ_Sexselec.getCurrentValue());

		infoBO.setCFName(mJ_chName.getValueText().toString());
		infoBO.setCLName(mJ_chNamesub.getValueText().toString());
		infoBO.setCEFName(mJ_pinName.getValueText().toString());
		infoBO.setCELName(mJ_pinNamesub.getValueText().toString());
		infoBO.setCIACode(guojiquhao.getValueText().toString());
		infoBO.setCTNum(phonum.getValueText().toString());
		infoBO.setCMoney(jin_e.getValueText().toString());
		infoBO.setCRate("1");
		try{
			if(null != infoBO.getCMoney()){
				DecimalFormat df = new DecimalFormat("#.00");
				double mn = 1*Integer.parseInt(infoBO.getCMoney());//汇率*金额
				infoBO.setCRMB(df.format(mn));
				infoBO.setCTMoney(df.format(mn));
				if(null != infoBO.getCIType()){//3年收入  4兼职收入  5其他收入
					if("3".equals(infoBO.getCIType()) ||
					   "4".equals(infoBO.getCIType()) ||
					   "5".equals(infoBO.getCIType()))
						infoBO.setCTMoney(df.format(mn/12));
				}
			}
		}catch(Exception e){
			infoBO.setCRMB("0");
			infoBO.setCTMoney("0");
		}
		infoBO.setCCNum(mJ_cardnum.getValueText().toString());
		infoBO.setCEmail(mJ_email.getValueText().toString());
		infoBO.setCCName(mJ_companyname.getValueText().toString());
		infoBO.setCCapital(mJ_zczijin.getValueText().toString());
		infoBO.setCYear(mJ_workyear.getText().toString());
		infoBO.setCBEAD(mJ_bumen.getValueText().toString());
		infoBO.setCBEAID(mJ_ygongnum.getValueText().toString());
		infoBO.setCIDate(mJ_ruzhidate.getText().toString()); 
		
		BeaAppSettings.instance();
		UserInfoBO userInfoBO = BeaAppSettings.getUserInfo();
		if (null != userInfoBO) {
			infoBO.setCRNum(userInfoBO.getEmployee_mark());
			infoBO.setCRNumName(userInfoBO.getUser_name());
			infoBO.setCRBEAD(userInfoBO.getOrg_id());
			infoBO.setCRBEADName(DbManager.getOrgNameById(userInfoBO
					.getOrg_id()));
			infoBO.setCRUNum(userInfoBO.getEmployee_mark());
			infoBO.setCRUNumName(userInfoBO.getUser_name());

			infoBO.setCIRNum(userInfoBO.getEmployee_mark());
			infoBO.setCIRNumName(userInfoBO.getUser_name());
			infoBO.setCIRUNum(userInfoBO.getEmployee_mark());
			infoBO.setCIRUNumName(userInfoBO.getUser_name());
			infoBO.setCIRBEAD(userInfoBO.getOrg_id());
			infoBO.setCIRBEADName(DbManager.getOrgNameById(userInfoBO
					.getOrg_id()));
		}
		return infoBO;
	}

	/**
	 * 初始化
	 * @param info
	 */
	private void initGtv(final JointInforBO info) {
		mJ_Relajiek.addButtons(DbManager.codeDatas("RelationShip"), "NAME", info.getCRelationship());
		
		//性别排序
		List<YRL_BASIC_DATA> sexlist = DbManager.codeAyncDatas("Sex");
		List<YRL_BASIC_DATA> sortSexlist = HeplerUtil.sortYRL_BASIC_DATA(sexlist);
		mJ_Sexselec.addButtons(null == sortSexlist?sexlist:sortSexlist, "NAME");
		mJ_Sexselec.setCurrentIndex(info.getCSex());
		
		mJ_guoji.addButtons(DbManager.codeDatas("CountryCode"), "NAME", info.getCCountry());
		mJ_cardlei.addButtons(DbManager.codeDatas("CertType"), "NAME", info.getCCType());
		mJ_cardconuty.addButtons(DbManager.codeDatas("CountryCode"), "NAME", info.getCCC());
		mJ_exployei.addButtons(DbManager.codeDatas("EmploymentType"), "NAME",info.getCET());
		mJ_zcbizhong.addButtons(DbManager.codeDatas("Currency"), "NAME",info.getCRCurrency());

		mJ_zhiye.addButtons(DbManager.codeDatas("OccupationBEA"), "NAME",info.getCOBEA());
		mJ_zhiwei.addButtons(DbManager.codeDatas("Position"), "NAME",info.getCPosition());
		mJ_bhyuangong.addButtons(DbManager.codeDatas("YesNo"), "NAME",info.getCBEA());
	 
		eduview.addButtons(DbManager.codeDatas("EducationExperience"), "NAME", info.getCEE());
		marryview.addButtons(DbManager.codeDatas("Marriage"), "NAME", info.getCMType());
		banjchaview.addButtons(DbManager.codeDatas("YesNo"), "NAME", info.getCSale());
		yewuwlaiview.addButtons(DbManager.codeDatas("LoanRelationDetailType"), "NAME", info.getCLRDT());
		kehuyuaniview.addButtons(DbManager.codeDatas("CustomerSource"), "NAME",info.getCCS());
		sfdxinjieshou.addButtons(DbManager.codeDatas("YesNo"), "NAME",info.getCRS());
		
		List<YRL_BASIC_DATA> tongzhifangshi = new ArrayList<YRL_BASIC_DATA>();
		tongzhifangshi.add(new YRL_BASIC_DATA("02","手机短信"));
		gtvCbrType.addButtons(tongzhifangshi, "NAME",info.getCBRType());
		
		List<YRL_BASIC_DATA> zhangdanfangshi = new ArrayList<YRL_BASIC_DATA>();
		zhangdanfangshi.add(new YRL_BASIC_DATA("02","纸质对账单"));
		gtvCmrType.addButtons(zhangdanfangshi, "NAME",info.getCMRType());
		
		youjifs.addButtons(DbManager.codeDatas("MailCode"), "NAME",info.censusAddr.getCSType());
		youjifshome.addButtons(DbManager.codeDatas("MailCode"), "NAME",info.homeAddr.getCSType());
		
		mJ_birthday.setText(info.getCBDate());
		mJ_cardoutdate.setText(info.getCCDate());
		
		mJ_chName.setValueText(info.getCFName());
		mJ_pinName.setValueText(info.getCELName());
		mJ_pinNamesub.setValueText(info.getCELName());
		mJ_chNamesub.setValueText(info.getCLName());
		guojiquhao.setValueText(info.getCIACode());
		phonum.setValueText(info.getCTNum());
		jin_e.setValueText(info.getCMoney());
		mJ_cardnum.setValueText(info.getCCNum());
		mJ_email.setValueText(info.getCEmail());
		mJ_companyname.setValueText(info.getCCName());
		mJ_zczijin.setValueText(info.getCCapital());
		mJ_pcode.setValueText(info.censusAddr.getCPC());
		mJ_pcodehome.setValueText(info.homeAddr.getCPC());
		
		// 个人资料 地址详情
		String s = "";
		if (null != info.censusAddr.getCACountryName())// 国家
			s += info.censusAddr.getCACountryName() + " ";
		if (null != info.censusAddr.getCCCityName())// 省份 城市 区县
			s += info.censusAddr.getCCCityName() + " ";
		if (null != info.censusAddr.getCAD3())// 楼盘
			s += info.censusAddr.getCAD3() + " ";
		if (null != info.censusAddr.getCAD4())// 地址
			s += info.censusAddr.getCAD4();
		mJ_addressinf.setText(s);

		List<YRL_BASIC_DATA> sftxdzList = new ArrayList<YRL_BASIC_DATA>();
		sftxdzList.add(new YRL_BASIC_DATA("1","住宅地址"));
		sftxdzList.add(new YRL_BASIC_DATA("2","户籍地址"));
		mJ_isdadreshome.addButtons(sftxdzList, "NAME");
		mJ_isdadreshome.setCurrentIndex("1");
		if(null != info.homeAddr.getCRAddr() && 
		   "1".equals(info.censusAddr.getCRAddr()))
			mJ_isdadreshome.setCurrentIndex("2");
		
		// 个人资料 地址详情
		s = "";
		if (null != info.homeAddr.getCACountryName())// 国家
			s += info.homeAddr.getCACountryName() + " ";
		if (null != info.homeAddr.getCCCityName())// 省份 城市 区县
			s += info.homeAddr.getCCCityName() + " ";
		if (null != info.homeAddr.getCAD3())// 楼盘
			s += info.homeAddr.getCAD3() + " ";
		if (null != info.homeAddr.getCAD4())// 地址
			s += info.homeAddr.getCAD4();
		mJ_addressinfhome.setText(s);
		
		// 职业资料 地址详情
		String addr = "";
		if (null != info.getCCCountryName())
			addr += info.getCCCountryName() + " ";
		if (null != info.getCCityName())
			addr += info.getCCityName() + " ";
		if (null != info.getCCAddr())
			addr += info.getCCAddr();
		ccaddr_content.setText(addr);

		mJ_workyear.setText(info.getCYear());
		mJ_workyear.setEnabled(false);
		mJ_bumen.setValueText(info.getCBEAD());
		mJ_ygongnum.setValueText(info.getCBEAID());
		mJ_ruzhidate.setText(info.getCIDate());
		if(Integer.parseInt(info.getCBEA())==2){
			mJ_bumen.setVisibility(View.GONE);
			mJ_ygongnum.setVisibility(View.GONE);
		}
		
		if (null != info.getCBEA() && info.getCBEA().equals("1")) {
			mJ_workleich.addButtons(DbManager.codeDatas("IndustryType"), "NAME","J01");
			mJ_workleihk.addButtons(DbManager.codeDatas("IndustryTypeHK"), "NAME","40101021");
			mJ_entitylei.addButtons(DbManager.codeDatas("OperateType"), "NAME","020");
			mJ_companpro.addButtons(DbManager.codeDatas("UnitProperty"), "NAME","05");

			mJ_entitylei.setVisibility(View.GONE);
			Tv_entitylei.setVisibility(View.GONE);
			mJ_companpro.setVisibility(View.GONE);
			Tv_companpro.setVisibility(View.GONE);
		
		}else if (null != info.getCBEA() && info.getCBEA().equals("2")) {
			mJ_workleich.addButtons(DbManager.codeDatas("IndustryType"), "NAME",info.getCIdType());
			mJ_workleihk.addButtons(DbManager.codeDatas("IndustryTypeHK"), "NAME",info.getCITypeHK());
			mJ_entitylei.addButtons(DbManager.codeDatas("OperateType"), "NAME",info.getCOT());
			mJ_companpro.addButtons(DbManager.codeDatas("UnitProperty"), "NAME",info.getCCTpye());

			mJ_entitylei.setVisibility(View.VISIBLE);
			Tv_entitylei.setVisibility(View.VISIBLE);
			mJ_companpro.setVisibility(View.VISIBLE);
			Tv_companpro.setVisibility(View.VISIBLE);
		}
		//如果是本行员工则要输入员工所在部门和员工工号，否则不用输入。
		mJ_bhyuangong.setOnTagButtonSelectedListener(new GroupTagView.OnTagButtonSelectedListener(){

			@Override
			public void OnTagButtonSelected(int index, Object btnModel) {
				if(index==1){
					mJ_bumen.setVisibility(View.GONE);
					mJ_ygongnum.setVisibility(View.GONE);
					mJ_entitylei.addButtons(DbManager.codeDatas("OperateType"), "NAME",info.getCOT());
					mJ_companpro.addButtons(DbManager.codeDatas("UnitProperty"), "NAME",info.getCCTpye());
					mJ_workleich.addButtons(DbManager.codeDatas("IndustryType"), "NAME",info.getCIdType());
					mJ_workleihk.addButtons(DbManager.codeDatas("IndustryTypeHK"), "NAME",info.getCITypeHK());
					mJ_entitylei.setVisibility(View.VISIBLE);
					Tv_entitylei.setVisibility(View.VISIBLE);
					mJ_companpro.setVisibility(View.VISIBLE);
					Tv_companpro.setVisibility(View.VISIBLE);
				}else if(index==0){
					mJ_bumen.setVisibility(View.VISIBLE);
					mJ_ygongnum.setVisibility(View.VISIBLE);
					mJ_workleich.addButtons(DbManager.codeDatas("IndustryType"), "NAME","J01");
					mJ_workleihk.addButtons(DbManager.codeDatas("IndustryTypeHK"), "NAME","40101021");
					mJ_entitylei.addButtons(DbManager.codeDatas("OperateType"), "NAME","020");
					mJ_companpro.addButtons(DbManager.codeDatas("UnitProperty"), "NAME","05");
					mJ_entitylei.setVisibility(View.GONE);
					Tv_entitylei.setVisibility(View.GONE);
					mJ_companpro.setVisibility(View.GONE);
					Tv_companpro.setVisibility(View.GONE);
				}
			}
		});
	}

	private void onAddressSel(final View view, jointAddressBO address) {
		DetailAddrBO detailAddr = (DetailAddrBO) view.getTag();
		if (null == detailAddr) {
			detailAddr = new DetailAddrBO();
			// mad1 国家 mad2 省市区 mad3 楼盘 md4 地址
			detailAddr.setLp(address.getCAD3());
			detailAddr.setAddr(address.getCAD4());
			detailAddr.setCountryId(address.getCACountry());
			detailAddr.setCountryName(address.getCACountryName());
			detailAddr.setAllid(address.getCCCity());
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
	@OnClick(R.id.addressview_home)
	public void getHomeDetailAddr(View view) {
		onAddressSel(view, infoBO.homeAddr);
	}
	
	// 个人资料 地址详情
	@OnClick(R.id.addressview)
	public void getDetailAddr(View view) {
		onAddressSel(view, infoBO.censusAddr);
	}

	// 职业资料 地址详情
	@OnClick(R.id.tv_ccaddr_content)
	public void addrClick(View view) {
		jointAddressBO address = new jointAddressBO();
		address.setCAD4(infoBO.getCCAddr());
		address.setCACountry(infoBO.getCCCountry());
		address.setCACountryName(infoBO.getCCCountryName());
		address.setCCCity(infoBO.getCCity());
		
		onAddressSel(view, address);
	}

	// 出生日期
	@OnClick(R.id.join_birthdayview)
	public void dateSelect1(View view) {
		DateDialog dialog = new DateDialog(getActivity(), ((TextView) view)
				.getText().toString(), new DateDialogListener() {
			@Override
			public void onClick(String date) {
				mJ_birthday.setText(date);
			}
		});
		dialog.show();
	}

	// 证件到期日
	@OnClick(R.id.dateview)
	public void dateSelect2(View view) {
		DateDialog dialog = new DateDialog(getActivity(), ((TextView) view)
				.getText().toString(), new DateDialogListener() {
			@Override
			public void onClick(String date) {
				mJ_cardoutdate.setText(date);
			}
		});
		dialog.show();
	}
	Date lastdate  = null;
	// 入职日期
	@OnClick(R.id.ruzhidate)
	public void dateSelect3(View view) {
		DateDialog dialog = new DateDialog(getActivity(), ((TextView) view)
				.getText().toString(), new DateDialogListener() {
			@Override
			public void onClick(String date) {
				mJ_ruzhidate.setText(date);
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				try {
					lastdate = format.parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				HeplerUtil util = new HeplerUtil();
				int month = util.getMonthNum(new Date(), lastdate);
				if (month<0) {
					mJ_workyear.setText("");
				}else {
					mJ_workyear.setText(""+month);
				}
			}
		});
		dialog.show();
	}

	@Override
	public boolean isRequiredFieldInputed(JointInforBO info) {
		SubHelperUtil.setTableName(tableName);
		if (info != null) {
			if (!SubHelperUtil.showToast(info.getCSex(), "性别")) {
				return false;
			}
			if (!SubHelperUtil.showCHToast(info.getCFName(), "中文姓")) {
				return false;
			}
			if (!SubHelperUtil.showCHToast(info.getCLName(), "中文名")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getCEFName(), "英文姓")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getCELName(), "英文名")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCRelationship(), "与借款人关系")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCCountry(), "国籍")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCBDate(), "出生日期")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCCType(), "证件类型")) {
				return false;
			}
			if (!"".equals(info.getCCType()) && info.getCCType().toString().equals("Ind1010")) {
				if (!SubHelperUtil.showIDToast(info.getCCNum(), "证件号码")) {
					return false;
				}
			}else {
				if (info.getCCNum().toString().equals("")) {
					XCToolkit.showToast("请输入"+tableName+"@证件号码");
					return false;
				}
			}
	 
			if (!SubHelperUtil.showToast(info.getCCC(), "发证国家")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCCDate(), "证件到期日")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCEE(), "教育水平")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCMType(), "婚姻状况")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCSale(), "是否同意我行的交叉营销")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCLRDT(), "与我行业务来往情况")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCCS(), "客户源")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCBRType(), "接收银行通知方式")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCMRType(), "接收银行账单方式")) {
				return false;
			}
			if (!SubHelperUtil.showEmailToast(info.getCEmail(), "电子邮箱")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.homeAddr.getCAddr(), "住宅地址类型")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.homeAddr.getCAD1(), "地址详情-国家")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.homeAddr.getCAD2(), "地址详情-省/市/区")) {
				return false;
			}
//			if (!SubHelperUtil.showNormal(info.homeAddr.getCAD3(), "地址详情-楼盘")) {
//				return false;
//			}
//			if (!SubHelperUtil.showNormal(info.homeAddr.getCAD4(), "地址详情-详细地址")) {
//				return false;
//			}
			if (!SubHelperUtil.showPcodeToast(info.homeAddr.getCPC(), "邮政编码")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.homeAddr.getCSType(), "邮寄方式")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.censusAddr.getCAddr(), "户籍地址类型")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.censusAddr.getCACountry(), "地址所属的地区/国家")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.censusAddr.getCCCity(), "地址所属的省/市")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.censusAddr.getCAD1(), "地址详情的国家/省市")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.censusAddr.getCAD2(), "地址详情的镇区路")) {
				return false;
			}
//			if (!SubHelperUtil.showNormal(info.censusAddr.getCAD3(), "地址详情楼层")) {
//				return false;
//			}
//			if (!SubHelperUtil.showNormal(info.censusAddr.getCAD4(), "地址详情的楼盘")) {
//				return false;
//			}
			if (!SubHelperUtil.showPcodeToast(info.censusAddr.getCPC(), "邮政编码")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.censusAddr.getCSType(), "邮寄方式")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.getCIACode(), "国际区号")) {
				return false;
			}
			if (!SubHelperUtil.showPhoneToast(info.getCTNum(), "手机号码")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCRS(), "是否为短信接收号码")) {
				return false;
			}
			if (!SubHelperUtil.showNumToast(info.getCMoney(), "金额")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCRCurrency(), "注册币种")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCET(), "雇佣类型")) {
				return false;
			}
			if (info.getCET() != null && info.getCET().equals("2")) {
				if (!SubHelperUtil.showToast(info.getCOT(), "经营实体类型")) {
					return false;
				}
				if (!SubHelperUtil.showNumDecimalToast(info.getCCapital(), "经营实体类型注册资金")) {
					return false;
				}
			}
			if (!SubHelperUtil.showNormal(info.getCCName(), "单位名称")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCCTpye(), "单位性质")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCCCountry(), "单位所在国家")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCCity(), "单位所在城市")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.getCCAddr(), "单位地址")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCIdType(), "行业分类（中国）")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCITypeHK(), "行业分类（香港）")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCOBEA(), "职业")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCPosition(), "职位")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.getCIDate(), "入职日期")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.getCYear(), "正确的入职日期")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getCBEA(), "是否本行员工")) {
				return false;
			}
			if (info.getCBEA() != null && info.getCBEA().equals("1")) {
				if (!SubHelperUtil.showNormal(info.getCBEAID(), "员工工号")) {
					return false;
				}
				if (!SubHelperUtil.showNormal(info.getCBEAD(), "员工所在部门")) {
					return false;
				}
			}
			SubHelperUtil.setTableName("");
			return true;
		} else {
			XCToolkit.showToast("请填写共同借款人信息");
			return false;
		}
	}
}
