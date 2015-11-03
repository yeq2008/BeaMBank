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
 * @author Lsq ����˸�������
 */
public class BorrowerInforFragment extends ContentBaseFragment<BorrowerInforBO> {
	
	@Override
	protected Class getInfoClass() {
		return BorrowerInforBO.class;
	}
	private static final String tableName = "����˸�������";

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater.inflate(R.layout.fm_grwdy_step1_content_borrowerinfo,
				null);
	}

	// ������
	@ViewInject(R.id.Bor_fName)
	TitleValueView TvvfName;
	// ������
	@ViewInject(R.id.Bor_lName)
	TitleValueView TvvlName;
	// Ӣ����
	@ViewInject(R.id.Bor_efName)
	TitleValueView TvvefName;
	// Ӣ����
	@ViewInject(R.id.Bor_elName)
	TitleValueView TvvelName;
	
	// �Ա�
	@ViewInject(R.id.Bor_gtvSexual)
	GroupTagViewEx GtvSexual;
	// ����
	@ViewInject(R.id.Bor_gtvNational)
	GroupTagViewEx GtvNational;

	// ֤������
	@ViewInject(R.id.Bor_gtvDocumentType)
	GroupTagViewEx GtvDocumentType;
	// ֤������
	@ViewInject(R.id.Bor_DocumentNumber)
	TitleValueView TvvDocumentNumber;
	// ��֤����
	@ViewInject(R.id.Bor_gtvIssuing)
	GroupTagViewEx GtvIssuing;

	// �Ƿ�ͬ�����н���Ӫ��
	@ViewInject(R.id.Bor_gtvIsmarketing)
	GroupTagViewEx GtvIsmarketing;
	// ������ҵ���������
	@ViewInject(R.id.Bor_gtvService)
	GroupTagViewEx GtvService;
	// �ͻ�Դ
	@ViewInject(R.id.Bor_gtvCustomer)
	GroupTagViewEx GtvCustomer;
	// �Ƿ�ͨѶ��ַ
	@ViewInject(R.id.Bor_gtvIsAddress)
	GroupTagViewEx GtvIsAddress;
	// ��ַ����
	@ViewInject(R.id.Bor_gtvCompleteAddress)
	TextView TvvCompleteAddress;
	// ��������
	@ViewInject(R.id.Bor_Postcode)
	TitleValueView TvvPostcode;
	// �ʼķ�ʽ
	@ViewInject(R.id.Bor_gtvMailingCode)
	GroupTagViewEx GtvMailingCode;
	// Eamil
	@ViewInject(R.id.Bor_Email)
	TitleValueView TvvEmail;
	// ��������
	@ViewInject(R.id.Bor_InterCode)
	TitleValueView TvvInterCode;
	// �绰����
	@ViewInject(R.id.Bor_PhoneNumber)
	TitleValueView TvvPhoneNumber;
	// ���
	@ViewInject(R.id.Bor_Money)
	TitleValueView TvvMoney;
	// �Ƿ�Ϊ���Ž��պ���
	@ViewInject(R.id.Bor_gtvSmsNumber)
	GroupTagViewEx GtvSmsNumber;
	// ����ˮƽ
	@ViewInject(R.id.Bor_gtvEducation)
	GroupTagViewEx GtvEducation;
	// ����״̬
	@ViewInject(R.id.Bor_gtvMarryState)
	GroupTagViewEx GtvMarryState;
	// ��������
	@ViewInject(R.id.Bor_valueBirthDate)
	TextView TvbirthDate;
	// ֤��������
	@ViewInject(R.id.Bor_valueDocumentEndDate)
	TextView TvDocuEndDate;
	// ��������֪ͨ��ʽ
	@ViewInject(R.id.Bor_gtvCbrType)
	GroupTagViewEx GtvCbrType;
	// ���������˵���ʽ
	@ViewInject(R.id.Bor_gtvCmrType)
	GroupTagViewEx GtvCmrType;
	
	// is
	@ViewInject(R.id.Bor_gtvis)
	GroupTagViewEx Gtvis;
	@ViewInject(R.id.pannel_title) TextView pannel_title;
	@Override
	protected void onInitContentView(View rootView, BorrowerInforBO info) {
		if (homeBO.isPinAnXinBaoData) {
			pannel_title.setText("��ƽ���ű��������޵�Ѻ���Ѵ��������");
		} else {
			pannel_title.setText("����ʱ���������޵�Ѻ���Ѵ��������");
		}
		TvvMoney.getValueView().addTextChangedListener(textWatcher);
		initGtv(info);
	}

	private void onAddressSel(final View view, AddressBO address) {
		DetailAddrBO detailAddr = (DetailAddrBO) view.getTag();
		if (null == detailAddr) {
			detailAddr = new DetailAddrBO();
			// mad1 ���� mad2 ʡ���� mad3 ¥�� md4 ��ַ
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

	// ��������
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

	// ֤��������
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

	
	
	/** ��ʼ�� */
	private void initGtv(BorrowerInforBO info) { 
		//�Ա�����
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
					XCToolkit.showToast("���ݼ�ܹ涨�����ڹ�ϵ�˲������������������ѡ���Ƿ�Ϊ�������м��Ŷ���/��Ա֮����");
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
		tongzhifangshi.add(new YRL_BASIC_DATA("02","�ֻ�����"));
		GtvCbrType.addButtons(tongzhifangshi, "NAME",info.getMBRType());
		List<YRL_BASIC_DATA> zhangdanfangshi = new ArrayList<YRL_BASIC_DATA>();
		zhangdanfangshi.add(new YRL_BASIC_DATA("02","ֽ�ʶ��˵�"));
		GtvCmrType.addButtons(zhangdanfangshi, "NAME",info.getMMRType());
			GtvMailingCode.addButtons(DbManager.codeDatas("MailCode"), "NAME",info.homeAddr.getMSType());
			List<YRL_BASIC_DATA> sftxdzList = new ArrayList<YRL_BASIC_DATA>();
			sftxdzList.add(new YRL_BASIC_DATA("1","סլ��ַ"));
			sftxdzList.add(new YRL_BASIC_DATA("2","��λ��ַ"));
			
			//ͨѶ��ַ
			GtvIsAddress.addButtons(sftxdzList, "NAME");
			GtvIsAddress.setCurrentIndex("1");
			if(null != homeBO.getProfessionInfor()
			   && null != homeBO.getProfessionInfor().compAddres 
			   && "1".equals(homeBO.getProfessionInfor().compAddres.getMRAddr()))
				GtvIsAddress.setCurrentIndex("2");
			
			GtvMailingCode.setCurrentIndex(info.homeAddr.getMSType());
				
			TvvPostcode.setValueText(info.homeAddr.getMPC());
			if (homeBO.isPinAnXinBaoData) {
				infoBO.homeAddr.setMAD1("�л����񹲺͹�");
			}
			String s = "";
			if (null != info.homeAddr.getMACountryName())// ����
				s += info.homeAddr.getMACountryName() + " ";
			if (null != info.homeAddr.getMACityName())// ʡ�� ���� ����
				s += info.homeAddr.getMACityName() + " ";
			if (null != info.homeAddr.getMAD3())// ¥��
				s += info.homeAddr.getMAD3() + " ";
			if (null != info.homeAddr.getMAD4())// ��ַ
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
			// ��������
			TvbirthDate.setText(info.getMBDate());
			// ֤��������
			TvDocuEndDate.setText(info.getMCDate());
			
			 setTitleColor();
	}

	private void setTitleColor(){
		// �Ա� 
		TextView TvSexual = (TextView) findViewById(R.id.Bor_sexual);
		// ֤������ 
		TextView TvDocumentType = (TextView) findViewById(R.id.Bor_DocumentType);
		// �������� 
		TitleValueView TvPostcode = (TitleValueView) findViewById(R.id.Bor_Postcode);
		// ֤������ 
		TitleValueView TvDocumentNumber = (TitleValueView) findViewById(R.id.Bor_DocumentNumber);
		// סַ����
		TextView TvBor_CompleteAddress = (TextView) findViewById(R.id.Bor_CompleteAddress);
		// �ֻ����� 
		TitleValueView TvPhoneNumber = (TitleValueView) findViewById(R.id.Bor_PhoneNumber);
		// �������� 
		TitleValueView TvBor_Money  = (TitleValueView) findViewById(R.id.Bor_Money);
		// ͨѶ��ַ 
		TextView TvBor_IsAddress = (TextView) findViewById(R.id.Bor_IsAddress);
		// �������� 
		TextView TvBor_BirthDate = (TextView) findViewById(R.id.Bor_BirthDate);
		//������ 
		TitleValueView TvBor_fName = (TitleValueView) findViewById(R.id.Bor_fName);
		//������ 
		TitleValueView TvBor_lName = (TitleValueView) findViewById(R.id.Bor_lName);
		// ѧ�� 
		TextView TvBor_Education = (TextView) findViewById(R.id.Bor_Education);
		// ����״�� 
		TextView TvBor_MarryState = (TextView) findViewById(R.id.Bor_MarryState);
		// ����֪ͨ��ʽ
		TextView TvBor_CbrType = (TextView) findViewById(R.id.Bor_CbrType);
		// �˵�Ͷ�ݷ�ʽ
		TextView TvBor_CmrType = (TextView) findViewById(R.id.Bor_CmrType);
		//����
		TextView TvBor_National = (TextView) findViewById(R.id.Bor_National);
		//��֤����
		TextView TvBor_Issuing = (TextView) findViewById(R.id.Bor_Issuing);
		//�ͻ�Դ
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
		infoBO.setMITypeName("�¹�������");
		infoBO.setMCurrency("01");//�����
		infoBO.setMTType("1");//�ֻ�
		infoBO.setMTTypeName("�ֻ�");
		
		infoBO.homeAddr.setMSType((String) GtvMailingCode.getCurrentValue());
		//�Ƿ�ͨѶ��ַ
		infoBO.homeAddr.setMRAddr("1");
		if(null != GtvIsAddress.getCurrentValue() && 
		   "2".equals(GtvIsAddress.getCurrentValue()))
			infoBO.homeAddr.setMRAddr("2");
		
		infoBO.homeAddr.setMPC(TvvPostcode.getValueText());
		infoBO.homeAddr.setMSTypeName(GtvMailingCode.getYRLname());
		infoBO.homeAddr.setMAddr("1");
		infoBO.homeAddr.setMAddrName("סլ��ַ");
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
		//��չʾ�ֶ�ȡname
		infoBO.setMCTypeName(GtvDocumentType.getYRLname());
		infoBO.setMLRDTName(GtvService.getYRLname());
		infoBO.setMCSName(GtvCustomer.getYRLname());
		infoBO.setMCurrencyName("�����");
		
		
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
				double mn = 1*Integer.parseInt(infoBO.getMMoney());//����*���
				infoBO.setMRMB(df.format(mn));
				infoBO.setMTMoney(df.format(mn));
				if(null != infoBO.getMIType()){//3������  4��ְ����  5��������
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
			XCToolkit.showToast("����д������˸������ϡ���Ϣ");
			return false;
		} else {
			if (!SubHelperUtil.showCHToast(info.getMFName(), "������")) {
				return false;
			}
			if (!SubHelperUtil.showCHToast(info.getMLName(), "������")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getMEFName(), "Ӣ����")) {
				return false;
			}
			if (!SubHelperUtil.showEngToast(info.getMELName(), "Ӣ����")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMSex(), "�Ա�")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMCountry(), "����")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMBDate(), "��������")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMCType(), "֤������")) {
				return false;
			}
			if (!"".equals(info.getMCType()) && info.getMCType().toString().equals("Ind1010")) {
				if (!SubHelperUtil.showIDToast(info.getMCNum(), "֤������")) {
					return false;
				}
			}else {
				if (info.getMCNum().toString().equals("")) {
					XCToolkit.showToast("������"+tableName+"@֤������");
					return false;
				}
			}
			
			if (!SubHelperUtil.showToast(info.getMCC(), "��֤����")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMCDate(), "֤��������")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMSale(), "�Ƿ�ͬ�����н���Ӫ��")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMLRDT(), "������ҵ���������")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMCS(), "�ͻ�Դ")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMBRType(), "��������֪ͨ��ʽ")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMMRType(), "���������˵���ʽ")) {
				return false;
			}
			if (!SubHelperUtil.showEmailToast(info.getMEmail(), "Email")) {
				return false;
			}
			if (!homeBO.isPinAnXinBaoData) {
				if (!SubHelperUtil.showToast(info.homeAddr.getMAddr(), "סլ��ַ����")) {
					return false;
				}
				if (!SubHelperUtil.showToast(info.homeAddr.getMRAddr(), "�Ƿ�ͨѶ��ַ")) {
					return false;
				}
			} 
			if (!SubHelperUtil.showToast(info.homeAddr.getMAD1(), "סլ����-����")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.homeAddr.getMAD2(), "סլ����-ʡ/��/��")) {
				return false;
			}
//			if (!SubHelperUtil.showToast(info.homeAddr.getMAD3(), "סլ����-¥��")) {
//				return false;
//			}
//			if (!SubHelperUtil.showToast(info.homeAddr.getMAD4(), "סլ����-��ϸ��ַ")) {
//				return false;
//			}
			if (!SubHelperUtil.showPcodeToast(info.homeAddr.getMPC(), "��������")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.homeAddr.getMSType(), "�ʼķ�ʽ")) {
				return false;
			}
			if (!SubHelperUtil.showNumToast(info.getMIACode(), "��������")) {
				return false;
			}
			if (!SubHelperUtil.showPhoneToast(info.getMTNum(), "�ֻ�����")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMRS(), "�Ƿ�Ϊ���Ž��պ���")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMEE(), "����ˮƽ")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMMType(), "����״��")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getMIType(), "��������")) {
				return false;
			}
			if (!SubHelperUtil.showNumDecimalToast(info.getMMoney(), "���")) {
				return false;
			}
			if (!SubHelperUtil.showNumDecimalToast(info.getMRMB(), "����Ϊ����ҽ��")) {
				return false;
			}
			if (info.getIs() != null && info.getIs().equals("1")) {
				XCToolkit.showToast("���ݼ�ܹ涨�����ڹ�ϵ�˲������������������ѡ���Ƿ�Ϊ�������м��Ŷ���/��Ա֮����@"+tableName);
				return false;
			}
		}
		SubHelperUtil.setTableName("");
		return true;
	}
}
