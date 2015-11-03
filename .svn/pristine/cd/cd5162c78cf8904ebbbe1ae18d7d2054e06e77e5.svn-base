package com.bea.mbank.edit.step1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import xc.android.utils.XCToolkit;

import com.bea.application.ConstDefined;
import com.bea.database.DbManager;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.bea.mbank.widgets.GroupTagViewEx.GroupListDialogListener;
import com.android.widgets.GroupTagView.OnTagButtonSelectedListener;
import com.android.widgets.TitleValueView;
import com.android.widgets.XCImageButton;
import com.bea.mbank.R;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.mbank.util.SubHelperUtil;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.grwdy.ApplyLoanInforBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author xuruian 申请贷款及费用信息
 */
public class ApplyLoanInforFragment extends ContentBaseFragment<ApplyLoanInforBO> {
	@Override
	protected Class getInfoClass() {
		return ApplyLoanInforBO.class;
	}
	private static final String tableName = "申请贷款及费用信息";

 
	// 贷款金额
	@ViewInject(R.id.et_ilmoney)
	EditText et_ilmoney;
	// 贷款期限
	@ViewInject(R.id.gtv_irperiod)
	GroupTagViewEx gtv_irperiod;
	// 贷款用途
	@ViewInject(R.id.apply_dkuanytview)
	GroupTagViewEx aA_dkytu;
	// 浮动幅度
	@ViewInject(R.id.apply_et_ocomment)
	EditText et_ocomment;
	// 贷款支付方式
	@ViewInject(R.id.apply_dkkuanzfsview)
	GroupTagViewEx aA_dkzffs;
	// 不接收关于此贷款的短信标志
	@ViewInject(R.id.apply_dkduanxinview)
	GroupTagViewEx aA_dkdxinflag;
	// 利率类型
	@ViewInject(R.id.apply_lilvlxingview)
	GroupTagViewEx aA_lilvleix;
	// 经办人
	@ViewInject(R.id.apply_jingbanren)
	TitleValueView aA_jbren;
	// 浮动幅度
	@ViewInject(R.id.et_ifluctuate)
	EditText et_ifluctuate;
	@ViewInject(R.id.tv_fd_bfh)
	TextView tv_fd_bfh;
	// 执行利率
	@ViewInject(R.id.et_iar)
	EditText et_iar;
	// 还款方式
	@ViewInject(R.id.apply_gtvhuankuanfs)
	GroupTagViewEx aA_hkuanfs;
	// 还款周期
	@ViewInject(R.id.apply_gtvhuankuanzq)
	GroupTagViewEx aA_hkuanzq;
	//包含下面所以控件的RelativeLayout
	@ViewInject(R.id.rl_loanPayMethod)
	RelativeLayout rl_loanPayMethod;
	// 是否在线支付
	@ViewInject(R.id.apply_zaixianzfview)
	GroupTagViewEx aA_sfzaixzf;
	// 放款账户币种
	@ViewInject(R.id.apply_fangkuanbz)
	TextView Tv_fkuangbiz;
	// 放款账户币种
	@ViewInject(R.id.apply_fangkuanbzview)
	GroupTagViewEx aA_fkuangbiz;
	// 放款账户/卡号
	@ViewInject(R.id.apply_fangkuanzhangh)
	TitleValueView aA_fkuangkh;
	// 放款账户名称
	@ViewInject(R.id.apply_fangkuanzhanghname)
	TitleValueView aA_fkuanzhname;
	// 还款账户是否同放款账户
	@ViewInject(R.id.apply_hktongfkview)
	GroupTagViewEx aA_hktongfk;
	// 还款账户币种
	@ViewInject(R.id.apply_huankuanzhbiz)
	TextView Tv_hkzhbiz;
	// 还款账户币种
	@ViewInject(R.id.apply_huankuanzhbizkview)
	GroupTagViewEx aA_hkzhbiz;
	// 还款账户卡号
	@ViewInject(R.id.apply_huankuanzhkh)
	TitleValueView aA_hkuanhkh;
	// 还款账户户名
	@ViewInject(R.id.apply_huankuanzhhm)
	TitleValueView aA_hkuangname;
	// 还款账户开户行代码
	@ViewInject(R.id.apply_et_ibbank)
	EditText et_ibbank;
	// 产品类型
	@ViewInject(R.id.productTypeTitle)
	TextView aA_yewupinzTitle;
	@ViewInject(R.id.productTypeValue)
	GroupTagViewEx aA_yewupinz;
	
	@ViewInject(R.id.rl_ifluctuate)
	RelativeLayout rl_ifluctuate;
	// 账户管理费率
	@ViewInject(R.id.et_Management)
	EditText et_Management;
	//缴费周期
	@ViewInject(R.id.apply_Period)
	TextView apply_Period;
	@ViewInject(R.id.apply_gtvPeriod)
	GroupTagViewEx gtvPeriod;
	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater.inflate(R.layout.fm_grwdy_step1_content_applyloaninfo, null);
	}

	@Override
	protected void onInitContentView(View rootView, ApplyLoanInforBO info) {
		et_ilmoney.addTextChangedListener(textWatcher);
		et_iar.addTextChangedListener(textWatcher);
		et_Management.addTextChangedListener(textWatcher);
		aA_lilvleix.setOnTagButtonSelectedListener(new OnTagButtonSelectedListener() {
			@Override
			public void OnTagButtonSelected(int index, Object btnModel) {
				if ("RAT001".equals(aA_lilvleix.getCurrentValue())) {
					initFdRate(); 
					et_iar.setEnabled(false);
				}else if ("RAT002".equals(aA_lilvleix.getCurrentValue())) {
					initGdRate();
					et_iar.setEnabled(true);
				}
			} 
		});
		gtv_irperiod.setOnTagButtonSelectedListener(new OnTagButtonSelectedListener() {
			@Override
			public void OnTagButtonSelected(int index, Object btnModel) {
				if("RAT001".equals(aA_lilvleix.getCurrentValue())){
					String limit = (String)gtv_irperiod.getCurrentValue();
					String rateStr = DbManager.getRateByLimitAndRateType(limit,"010");
					if(!"".equals(rateStr)){
						float rate=Float.parseFloat(rateStr);
						if("RAT001".equals(aA_lilvleix.getCurrentValue())){
							float floatRate;
							String floatRateStr=et_ifluctuate.getText().toString();
							floatRate="".equals(floatRateStr)?0:Float.parseFloat(floatRateStr)/100;
							float zxRate=rate*(1+floatRate);
							et_iar.setText(zxRate+"");
						}else{
							et_iar.setText(rate+"");
						}
					}
				}
			} 
		});
		et_ifluctuate.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
			@Override
			public void afterTextChanged(Editable s) {
				try{
					//执行利率
					String limit = (String)gtv_irperiod.getCurrentValue();
					String rate = DbManager.getRateByLimitAndRateType(limit,"010");
					DecimalFormat df = new DecimalFormat("#0.00");
					if("".equals(s.toString())){
						et_iar.setText(rate);
					}else{
						String s1 = s.toString();
						String s2 = s1.substring(0, 1);
						if("-".equals(s2)){//上浮
							if(s1.length()>1){
								String s3 = s1.substring(1,s1.length());
								inputLimit(s3,s);
								s1 = s1.substring(1, s1.length());
								double tmp = Double.parseDouble(s1);
								double zxlvd = Double.parseDouble(rate);
								double lv1 = zxlvd * (1-tmp/100);
								et_iar.setText(df.format(lv1));
							}
						}else{
							inputLimit(s1,s);
							double tmp = Double.parseDouble(s1);
							double zxlvd = Double.parseDouble(rate);
							double lv1 = zxlvd * (1+tmp/100);
							et_iar.setText(df.format(lv1));
						}
					}
				}catch(Exception e){
					infoBO.setIAR("0");
				}
			}
			private void inputLimit(String tmp, Editable s){
				if(tmp.length()<1)
					return;
				String first = tmp.substring(0,1);
				Pattern pattern = Pattern.compile("^[1-9]$");
				Matcher m = pattern.matcher(first);
				if(m.matches()){
					int index = tmp.indexOf(".");
					if(-1 != index){
						String xs = tmp.substring(index+1,tmp.length());
						if(xs.length()>2)
							s.replace(index+3, tmp.length(), "");
					}
				} else if("0".equals(first)){
					if(tmp.length()>1){
						String sec = tmp.substring(1,2);
						if(!".".equals(sec)){
							s.replace(1, tmp.length(), "");
						} else {
							if(tmp.length()>4){
								s.replace(4, tmp.length(), "");
							}
						}
					}
				} else{
					s.clear();
				}
			}
		});
		
		//还款账户币种
		aA_fkuangbiz.setGroupListDialogListener(new GroupListDialogListener() {
			@Override
			public void click(int index) {
				if(judgeAcount())
					aA_hkzhbiz.setCurrentIndex(index);
			}
		});
		
		aA_hktongfk.setOnTagButtonSelectedListener(new OnTagButtonSelectedListener() {
			@Override
			public void OnTagButtonSelected(int index, Object btnModel) {
				if(!judgeAcount()){
					aA_hkuanhkh.setValueText("");
					aA_hkuangname.setValueText("");
				}else{
					aA_hkzhbiz.setCurrentIndex((String)aA_fkuangbiz.getCurrentValue());
					aA_hkuanhkh.setValueText(aA_fkuangkh.getValueText());
					aA_hkuangname.setValueText(aA_fkuanzhname.getValueText());
				}
			}
		});
		initGtv(info);
		if("RAT001".equals(aA_lilvleix.getCurrentValue())){
			String limit = (String)gtv_irperiod.getCurrentValue();
			float rate = Float.parseFloat(DbManager.getRateByLimitAndRateType(limit,"010"));
			String floatRateStr=et_ifluctuate.getText().toString();
			float floatRate="".equals(floatRateStr)?0:Float.parseFloat(floatRateStr)/100;
			float zxRate=rate*(1+floatRate);
			
			et_iar.setText(zxRate+"");
		}
	}
	
	private boolean judgeAcount(){
		String tmp = (String)aA_hktongfk.getCurrentValue();
		if(null != tmp && "1".equals(tmp))
			return true;
		return false;
	}

	private void initGtv(ApplyLoanInforBO info) { 
		//业务品种
		if (!homeBO.getIsPinAnXinBaoData()) {
			//业务类型对应利率类型
			//***************
			aA_yewupinzTitle.setVisibility(View.VISIBLE);
			aA_yewupinz.setVisibility(View.VISIBLE);
			List<YRL_BASIC_DATA> prdType = new ArrayList<YRL_BASIC_DATA>();
			prdType.add(new YRL_BASIC_DATA("0201", "个人无抵押消费贷款"));
			prdType.add(new YRL_BASIC_DATA("0206", "个人无抵押车位贷款"));
			aA_yewupinz.addButtons(prdType, "NAME");
			aA_yewupinz.setCurrentIndex(info.getIBType());
			aA_yewupinz.setOnTagButtonSelectedListener(new OnTagButtonSelectedListener() {
				@Override
				public void OnTagButtonSelected(int index, Object btnModel) {
					List<YRL_BASIC_DATA> dkytlist = new ArrayList<YRL_BASIC_DATA>();
					List<YRL_BASIC_DATA> lvlist = new ArrayList<YRL_BASIC_DATA>();
					aA_dkytu.removeAllViews();
					aA_lilvleix.removeAllViews();
					if(0 == index){
						dkytlist.add(new YRL_BASIC_DATA("201", "消费性购车"));
						dkytlist.add(new YRL_BASIC_DATA("301", "房产装修"));
						dkytlist.add(new YRL_BASIC_DATA("302", "教育学资"));
						dkytlist.add(new YRL_BASIC_DATA("303", "旅游"));
						dkytlist.add(new YRL_BASIC_DATA("304", "其他消费"));
						lvlist.add(new YRL_BASIC_DATA("RAT002", "固定利率"));
						lvlist.add(new YRL_BASIC_DATA("RAT001", "浮动利率"));
					}
					if(1 == index){
						dkytlist.add(new YRL_BASIC_DATA("304", "其他消费"));
						lvlist.add(new YRL_BASIC_DATA("RAT001", "浮动利率"));
					}
					aA_dkytu.addButtons(dkytlist, "NAME");
					aA_lilvleix.addButtons(lvlist, "NAME");
					if(0 == index)
						initGdRate();
					if(1 == index)
						initFdRate();
				}
			});
		}
		
		//***************
		//贷款用途
		String tmp = (String)aA_yewupinz.getCurrentValue();
		List<YRL_BASIC_DATA> dkytlist = new ArrayList<YRL_BASIC_DATA>();
		dkytlist.add(new YRL_BASIC_DATA("201", "消费性购车"));
		dkytlist.add(new YRL_BASIC_DATA("301", "房产装修"));
		dkytlist.add(new YRL_BASIC_DATA("302", "教育学资"));
		dkytlist.add(new YRL_BASIC_DATA("303", "旅游"));
		dkytlist.add(new YRL_BASIC_DATA("304", "其他消费"));
		et_ocomment.setText(info.getOcomment());//用途备注
		if(!homeBO.getIsPinAnXinBaoData() && null != tmp && "0206".equals(tmp)){
			dkytlist = new ArrayList<YRL_BASIC_DATA>();
			dkytlist.add(new YRL_BASIC_DATA("304", "其他消费"));
		}
		aA_dkytu.addButtons(dkytlist, "NAME");
		aA_dkytu.setCurrentIndex(info.getILT());
		//***************
		
		aA_dkzffs.addButtons(DbManager.codeDatas("PaymentMode"), "NAME",info.getIPM());//贷款支付方式
		
		//贷款期限
		List<YRL_BASIC_DATA> gtv_irperiod_list = new ArrayList<YRL_BASIC_DATA>();
		if(homeBO.isPinAnXinBaoData){
			gtv_irperiod_list.add(new YRL_BASIC_DATA(info.getILDate(), info.getILDate()+"月"));
		}else{
			gtv_irperiod_list.add(new YRL_BASIC_DATA("6", "6月"));
			gtv_irperiod_list.add(new YRL_BASIC_DATA("12", "12月"));
			gtv_irperiod_list.add(new YRL_BASIC_DATA("24", "24月"));
			gtv_irperiod_list.add(new YRL_BASIC_DATA("36", "36月"));
			gtv_irperiod_list.add(new YRL_BASIC_DATA("48", "48月"));
			gtv_irperiod_list.add(new YRL_BASIC_DATA("60", "60月"));
		}
		gtv_irperiod.addButtons(gtv_irperiod_list, "NAME");
		gtv_irperiod.setCurrentIndex(info.getILDate());

		//***********************flh
		//利率类型
		//个人无抵押车位贷只能选择浮动利率
		List<YRL_BASIC_DATA> lvlist = new ArrayList<YRL_BASIC_DATA>();
		if(null != info.getIBType() && "0206".equals(info.getIBType())){
			lvlist.add(new YRL_BASIC_DATA("RAT001", "浮动利率"));
		}else if (homeBO.isPinAnXinBaoData) {
			lvlist.add(new YRL_BASIC_DATA("RAT001", "浮动利率"));
		}else{
			lvlist.add(new YRL_BASIC_DATA("RAT002", "固定利率"));
			lvlist.add(new YRL_BASIC_DATA("RAT001", "浮动利率"));
		}
		aA_lilvleix.addButtons(lvlist, "NAME");
		aA_lilvleix.setCurrentIndex(info.getIRType()); 
			et_iar.setEnabled(false);
		//平安信保下利率类型如为空 默认为浮动利率
		if(homeBO.getIsPinAnXinBaoData()){
			if(null == info.getIRType())
				aA_lilvleix.setCurrentIndex("RAT001");
		}
		
		String rate = (String)aA_lilvleix.getCurrentValue();
		if(null != rate){//浮动利率
			if("RAT001".equals(rate))
				initFdRate();
			if("RAT002".equals(rate))
				initGdRate();
		}

		//执行利率
		if(null != info.getIAR())
			et_iar.setText(info.getIAR());
		if(null != info.getIFluctuate())
			et_ifluctuate.setText(info.getIFluctuate());
		
		//***********************flh
		
		
		aA_sfzaixzf.addButtons(DbManager.codeDatas("YesNo"), "NAME",info.getIOnline());//是否在线支付
		aA_fkuangbiz.setVisibility(View.GONE);
		Tv_fkuangbiz.setVisibility(View.GONE);
		aA_hktongfk.addButtons(DbManager.codeDatas("YesNo"), "NAME",info.getIBG());//还款账户是否同放款账户
		aA_hkzhbiz.setVisibility(View.GONE);
		Tv_hkzhbiz.setVisibility(View.GONE);
		
		//还款周期
		List<YRL_BASIC_DATA> hkzqlist = new ArrayList<YRL_BASIC_DATA>();
		hkzqlist.add(new YRL_BASIC_DATA("1", "月"));
		aA_hkuanzq.addButtons( hkzqlist,"NAME");
		aA_hkuanzq.setCurrentIndex(info.getIBPP());
		
		//还款方式
		List<YRL_BASIC_DATA> hkfslist = new ArrayList<YRL_BASIC_DATA>();
		hkfslist.add(new YRL_BASIC_DATA("RPT001", "等额本息"));
		aA_hkuanfs.addButtons(hkfslist, "NAME");
		aA_hkuanfs.setCurrentIndex(info.getIBPM());
		
		et_ilmoney.setText(info.getILMoney());//贷款金额
		
		if (homeBO.isPinAnXinBaoData)
			et_ilmoney.setEnabled(false);//设置为不可编辑
		
		aA_jbren.setValueText(info.getIDNumName());//经办人
		
		//放款账户卡号
		aA_fkuangkh.setValueText(info.getIGAccount());
		aA_fkuangkh.getValueView().addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
			@Override
			public void afterTextChanged(Editable s) {
				fkzhCheckButton.setSelected(null != infoBO.getIGAccountValid() &&
	                    		infoBO.getIGAccountValid().equals(infoBO.getIGAccount()));
				if(judgeAcount())
					aA_hkuanhkh.setValueText(s.toString());
			}
		});
		//卡号合法性验证状态
		fkzhCheckButton.setSelected(null != info.getIGAccountValid() &&
				                    info.getIGAccountValid().equals(info.getIGAccount()));
		// 绑定放款账户名称
		aA_fkuanzhname.setValueText(info.getIGName());
		aA_fkuanzhname.getValueView().addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {
				if(judgeAcount())
					aA_hkuangname.setValueText(s.toString());
			}
		});
		// 绑定还款账户卡号
		aA_hkuanhkh.setValueText(info.getIBAccount());
		aA_hkuanhkh.getValueView().addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {
				hkzhCheckButton.setSelected(null != infoBO.getIBAccountValid() &&
						infoBO.getIBAccountValid().equals(s.toString()));
			}
		});
		//卡号合法性验证状态
		hkzhCheckButton.setSelected(null != info.getIBAccountValid() &&
				info.getIBAccountValid().equals(info.getIBAccount()));
		
		aA_hkuangname.setValueText(info.getIBName());//还款账户户名
		et_ibbank.setText(info.getIBBank());//还款账户开户行代码
		
		if(!homeBO.isPinAnXinBaoData)
			rl_loanPayMethod.setVisibility(View.GONE);
		else{
			
			if("2".equals(info.getIPM())){
				rl_loanPayMethod.setVisibility(View.GONE);
			}
			//贷款支付方式为自主支付时要填下面的东西，为受托支付时下面的隐藏，不要填
			aA_dkzffs.setOnTagButtonSelectedListener(aA_dkzffs_listener);
		}
		et_Management.setText(info.getIManagement());//账户管理费率
		if(!homeBO.isPinAnXinBaoData){
			apply_Period.setVisibility(View.VISIBLE);
			gtvPeriod.setVisibility(View.VISIBLE);
			//缴费周期选项
			List<YRL_BASIC_DATA> Period = new ArrayList<YRL_BASIC_DATA>();
			Period.add(new YRL_BASIC_DATA("1", "一次性"));
			Period.add(new YRL_BASIC_DATA("2", "按月"));
			gtvPeriod.addButtons(Period, "NAME");
			gtvPeriod.setCurrentIndex(info.getIPeriod());
		}else {
			apply_Period.setVisibility(View.GONE);
			gtvPeriod.setVisibility(View.GONE);
		}
		setTitleColor();
	}

	private void setTitleColor(){ 
		// 贷款金额标题 
		TextView tv_ilmoney = (TextView) findViewById(R.id.tv_ilmoney);
		// 贷款期限标题 
		TextView tv_irperiod = (TextView) findViewById(R.id.tv_irperiod);
		// 还款方式
		TextView tv_huankuanfs = (TextView) findViewById(R.id.apply_huankuanfs);
		// 还款周期
		TextView tv_huankuanzq = (TextView) findViewById(R.id.apply_huankuanzq);
		//经办人
		TitleValueView Tv_jingbanren = (TitleValueView) findViewById(R.id.apply_jingbanren);
		// 利率类型
		TextView tv_lilvlxing = (TextView) findViewById(R.id.apply_lilvlxing);
		if (homeBO.isPinAnXinBaoData) {//如果是平安信保传过来的数据更改标题颜色
			tv_ilmoney.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			tv_irperiod.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			tv_huankuanfs.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			tv_huankuanzq.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
			Tv_jingbanren.setTitleColor(Color.parseColor(ConstDefined.GroupTitleColor));
			tv_lilvlxing.setTextColor(Color.parseColor(ConstDefined.GroupTitleColor));
		}
	}
	@Override
	public ApplyLoanInforBO getValueFromInterfaceView() {
		// 绑定利率调整周期
		infoBO.setIRPeriod("1");
		infoBO.setIRTU("020");
		infoBO.setIRTUName("月"); 
		infoBO.setILT((String) aA_dkytu.getCurrentValue());
		infoBO.setOcomment(et_ocomment.getText().toString());
		infoBO.setIPM((String) aA_dkzffs.getCurrentValue());
		infoBO.setIPMName(aA_dkzffs.getYRLname());
		infoBO.setIMType((String) homeBO.getBorrowerInfor().getMRS());
		infoBO.setIBType((String)aA_yewupinz.getCurrentValue());
		if(homeBO.isPinAnXinBaoData)
			infoBO.setIBType("0205");
		
		//利率类型
		infoBO.setIRType((String) aA_lilvleix.getCurrentValue());
		infoBO.setIRTypeName(aA_lilvleix.getYRLname());
		if (aA_lilvleix.getCurrentValue().equals("RAT001")) {
			infoBO.setIRT("5");
			infoBO.setIRTName("下一还款日");
		}else if (aA_lilvleix.getCurrentValue().equals("RAT002")) {
			infoBO.setIRT("7");
			infoBO.setIRTName("不调整");
		}
		infoBO.setIFluctuate(et_ifluctuate.getText().toString());//浮动幅度
		infoBO.setIAR(et_iar.getText().toString());//执行利率
		
		infoBO.setICType("030");
		infoBO.setIOnline((String) aA_sfzaixzf.getCurrentValue());
		infoBO.setIGCurrency("01");
		infoBO.setIGCurrencyName("人民币");
		infoBO.setIBG((String) aA_hktongfk.getCurrentValue());
		infoBO.setIBCurrency("01");
		infoBO.setIBCurrencyName("人民币"); 
		infoBO.setIBPP((String) aA_hkuanzq.getCurrentValue());//还款周期
		infoBO.setILMoney(et_ilmoney.getText().toString());//贷款金额
		infoBO.setILDate((String)gtv_irperiod.getCurrentValue());//贷款期限
		infoBO.setIBPM("RPT001");//还款方
		infoBO.setIGAccount(aA_fkuangkh.getValueText().toString());//放款账户卡号
		infoBO.setIGName(aA_fkuanzhname.getValueText().toString());//放款账户名称
		infoBO.setIBAccount(aA_hkuanhkh.getValueText().toString());//还款账户卡号
		infoBO.setIBName(aA_hkuangname.getValueText().toString());// 还款账户户名
		infoBO.setIBBank(et_ibbank.getText().toString());// 还款账户开户行代码
		infoBO.setIManagement(et_Management.getText().toString());//账户管理费率
		if(!homeBO.isPinAnXinBaoData)
			infoBO.setIPeriod((String) gtvPeriod.getCurrentValue());//缴费周期
		return infoBO;
	}
	
	OnTagButtonSelectedListener aA_dkzffs_listener =  new OnTagButtonSelectedListener(){
		@Override
		public void OnTagButtonSelected(int index, Object btnModel) {
			if(index==1){
				rl_loanPayMethod.setVisibility(View.GONE);
			}else if(index==0){
				rl_loanPayMethod.setVisibility(View.VISIBLE);
			}
		}
	};

	@Override
	public boolean isRequiredFieldInputed(ApplyLoanInforBO info) {
		SubHelperUtil.setTableName(tableName);
		if (null == info) {
			XCToolkit.showToast("请填写申请贷款及费用信息");
			return false;
		} else {
			if (info.getILMoney().equals("")) {
				XCToolkit.showToast("请输入"+tableName+"@贷款金额");
				return false;
			}
			if (!SubHelperUtil.showNormal(info.getILDate(), "贷款期限")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getIRT(), "利率调整方式")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getILT(), "贷款用途")) {
				return false;
			}
			if (homeBO.isPinAnXinBaoData) {
			// 如果是贷款用途为304-其他消费，此字段不能为空
			if (null != homeBO.getApplyLoanInfor() && 
				null != homeBO.getApplyLoanInfor().getILT() && 
				"304".equals(homeBO.getApplyLoanInfor().getILT())) {
				if (!SubHelperUtil.showNormal(info.getOcomment(), "用途备注"))
					return false;
			}
		} 
			if (!SubHelperUtil.showToast(info.getIPM(), "贷款支付方式")) {
				return false;
			}

			if (!SubHelperUtil.showToast(info.getIMType(), "不接受关于此贷款的短信标志")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.getIRBEAD(), "入账机构")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.getIDBEAD(), "经办机构")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getIRType(), "利率类型")) {
				return false;
			}
			if (!SubHelperUtil.showToast(info.getICType(), "担保方式")) {
				return false;
			}
			if(null != info.getIRType() && info.getIRType().equals("RAT001")){
				if(null != info.getIFluctuate() && ("0".equals(info.getIFluctuate()) || "-0".equals(info.getIFluctuate()))){
					
				}else 
					if (!SubHelperUtil.showRateToast(info.getIFluctuate(), "浮动幅度")) {
					return false;
				}
			}
			if (!SubHelperUtil.showNormal(info.getIAR(), "执行利率")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.getIBPM(), "还款方式")) {
				return false;
			}
			if (!SubHelperUtil.showNormal(info.getIBPP(), "还款周期")) {
				return false;
			}
			if (homeBO.isPinAnXinBaoData) {
				if(null != info.getIPM() && "1".equals(info.getIPM())){
					if (!SubHelperUtil.showToast(info.getIOnline(), "是否在线支付")) {
						return false;
					}
					if (!SubHelperUtil.showNumToast(info.getIGAccount(), "放款账户卡号")) {
						return false;
					}
					if (!SubHelperUtil.showNormal(info.getIGName(), "放款账户名称")) {
						return false;
					}
					if (!SubHelperUtil.showToast(info.getIBG(), "还款账户是否同放款账户")) {
						return false;
					}
					if (!SubHelperUtil.showNumToast(info.getIBAccount(), "还款账户卡号")) {
						return false;
					}
					if(!SubHelperUtil.showNormal(info.getIBName(), "还款账号户名")){
			        	  return false;
			        }	
					if (!SubHelperUtil.showNumToast(info.getIBBank(), "还款账户开户行代码",4)) {
						return false;
					}
				}
				if(null != info.getIBG() && "1".equals(info.getIBG())){
					if(null != info.getIGName() && null != info.getIBName() && !info.getIGName().equals(info.getIBName())){
						XCToolkit.showToast("申请贷款及费用信息_还款账户名称与放款账户名称不同！");
						return false;
					}
					if(null != info.getIBAccount() && null!= info.getIGAccount() && !info.getIBAccount().equals(info.getIGAccount())){
						XCToolkit.showToast("申请贷款及费用信息_还款账户卡号与放款账户卡号不同！");
						return false;
					}
					if(null != info.getIBCurrency() && null != info.getIGCurrency() && !info.getIBCurrency().equals(info.getIGCurrency())){
						XCToolkit.showToast("申请贷款及费用信息_还款账户币种与放款账户币种不同！");
						return false;
					}
				}
			}
			if (!SubHelperUtil.showRateToast(info.getIManagement(), "账户管理费率")) {
				return false;
			}
			if (!homeBO.isPinAnXinBaoData) {
				if (!SubHelperUtil.showNormal(info.getIPeriod(), "缴费周期")) {
					return false;
				}
			}
			
			SubHelperUtil.setTableName("");
			return true;
		}
	}
	
	//放款账户验证按钮
	@ViewInject(R.id.fkzhCheckButton)XCImageButton fkzhCheckButton;
	@OnClick(R.id.fkzhCheckButton)
	public void onFKZHcheckButtonEvent(View v) {
		if (aA_fkuangkh.getValueText().length()<0) {
			XCToolkit.showToast("请正确输入卡号后再进行验证");return;
		}
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("acct", aA_fkuangkh.getValueText());
		jsonRequest(111, "通用接口", JsonRemoteBO.
				reqParam(ConstDefined.CardValidCheck, reqMap));
	}
	
	//还款账户验证按钮
	@ViewInject(R.id.hkzhCheckButton)XCImageButton hkzhCheckButton;
	@OnClick(R.id.hkzhCheckButton)
	public void onHKZHcheckButtonEvent(View v) {
		if (aA_hkuanhkh.getValueText().length()<0) {
			XCToolkit.showToast("请正确输入卡号后再进行验证");return;
		}
		
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("acct", aA_hkuanhkh.getValueText());
		jsonRequest(112, "通用接口", JsonRemoteBO.
				reqParam(ConstDefined.CardValidCheck, reqMap));
	}
	
	@Override
	public void onResponsSuccess(int actionTag, Object arg1) {
		JSONObject jObject = (JSONObject)arg1;
		String acctStat = jObject.optString("acctStat");
		if ("00".equals(acctStat)||"02".equals(acctStat)) {//(正常，不动户)验证通过
			if (111 == actionTag) {
				fkzhCheckButton.setImageResource(R.drawable.cardvalid_check_bg);
				infoBO.setIGAccountValid(aA_fkuangkh.getValueText());
				//卡号合法性验证状态
				fkzhCheckButton.setSelected(true);
				aA_fkuanzhname.setValueText(jObject.optString("chnName"));
			} else if (112 == actionTag) {
				hkzhCheckButton.setImageResource(R.drawable.cardvalid_check_bg);
				infoBO.setIBAccountValid(aA_hkuanhkh.getValueText());
				//卡号合法性验证状态
				hkzhCheckButton.setSelected(true);
				aA_hkuangname.setValueText(jObject.optString("chnName"));
				et_ibbank.setText(jObject.optString("accBranch"));
			}
		} else {
			XCToolkit.showToast(jObject.optString("acctStatInfo"));
		}
	}

	private void initFdRate(){
		String limit = (String)gtv_irperiod.getCurrentValue();
		String rate = DbManager.getRateByLimitAndRateType(limit,"010");
		et_iar.setText(rate);
		if (homeBO.isPinAnXinBaoData) {
			et_ifluctuate.setText("40"); 
		}else {
			et_ifluctuate.setText("0"); 
		}
		rl_ifluctuate.setVisibility(View.VISIBLE);
	}
	
	private void initGdRate(){
		et_ifluctuate.setText("0");
		et_iar.setText("8.8");
		rl_ifluctuate.setVisibility(View.GONE);
	}
}
