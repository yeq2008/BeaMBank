package com.bea.mbank.helper.counter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.android.widgets.TitleValueView;
import com.bea.database.DbManager;
import com.bea.database.codemodel.MMP_LOAN_RATE;
import com.bea.mbank.R;
import com.bea.mbank.util.SubHelperUtil;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.cyg.viewinject.annotation.ViewInject;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import xc.android.fragment.XCBaseFragment;
import xc.android.utils.XCToolkit;

public class LoanCounterFragment extends XCBaseFragment {
	//整个布局
	@ViewInject(R.id.linearLayout)
	LinearLayout linearLayout;
	//------------左边页面:贷款计算器-----------------
	//还款方式
	@ViewInject(R.id.fglc_gtv_paybacktype)
	private GroupTagViewEx gtv_paybacktype;
	//贷款金额
	@ViewInject(R.id.fglc_tvv_loanAmount)
	private TitleValueView tvv_loanAmount;
	//按揭年数
	@ViewInject(R.id.fglc_tvv_payyears)
	private TitleValueView tvv_payyears;
	//浮动利率
	@ViewInject(R.id.fglc_tvv_floatrate)
	private TitleValueView tvv_floatrate;
	//利率
	@ViewInject(R.id.fglc_tvv_rate)
	private TitleValueView tvv_rate;
	//开始计算按钮
	@ViewInject(R.id.fglc_btn_calculator)
	private Button btn_calculator;
	
	//--------------左边页面：贷款明细--------------------
	//饼状图
	@ViewInject(R.id.fglc_fl_pic)
	private PieChartSurfaceView view_piechart;
	//利率
	//@ViewInject(R.id.loanDetail_tv_rateContent)
	//private TextView loanDetail_tv_rateContent;
	//贷款总额
	@ViewInject(R.id.loanDetail_tv_totalamountContent)
	private TextView loanDetail_tv_totalamountContent;
	//支付利息
	@ViewInject(R.id.loanDetail_tv_interestContent)
	private TextView loanDetail_tv_interestContent;
	
	//贷款总额
	//@ViewInject(R.id.loanSummary_loanTotalContent)
	//private TextView loanSummary_loanTotalContent;
	//月均还款
	@ViewInject(R.id.loanSummary_monthPayContent)
	private TextView loanSummary_monthPayContent;
	//支付利息
	//@ViewInject(R.id.loanSummary_payInterestContent)
	//private TextView loanSummary_payInterestContent;
	//贷款月数
	@ViewInject(R.id.loanSummary_monthContent)
	private TextView loanSummary_monthContent;
	
	//------------------右边页面----------------
	//列表
	@ViewInject(R.id.loan_listview)
	private ListView loan_listview;
	
	//存放计算结果的list
	private List<PayLoanBO> calcResults=new ArrayList<PayLoanBO>();
	
	private BaseAdapter adapter;
		
	//月均还款
	private float monthAveragePay;
	//支付利息
	private float interestsTotal;
	//贷款年数
	private int loanYears;
	//贷款年利率
	private float loanRate;
	//基准利率
	private BigDecimal baseRate=BigDecimal.ZERO;
	//浮动利率
	private BigDecimal floatRate=BigDecimal.ZERO;
	//利率总额
	private float interestSum;
	//月供总额
	private float monthpaySum;
	private LayoutInflater inflater;
	//利率码表
	private List<MMP_LOAN_RATE> LoanRateList;
	
	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		inflater = arg0;
		return arg0.inflate(R.layout.fm_helper_loancounter, null);
	}

	@Override
	protected void onInitContentView(View arg0) {

		List<String> lists=new ArrayList<String>();
		lists.add("等额本息");
		lists.add("等额本金");
//		lists.add("快速双周供");
//		lists.add("一次性还本付息");
		gtv_paybacktype.addButtons(lists, "NAME");
		
		/*fglc_et_payyears.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				String text=arg0.toString();
				if(!"".equals(text)){
					loanYears=Integer.parseInt(text);
					String rate=DbManager.getRateByLimitAndRateType(loanYears*12+"","010");
					if(!"".equals(rate)){
						baseRate=Float.parseFloat(rate);
						loanRate=baseRate*(1+floatRate/100);
						tv_rate.setText(loanRate+"");
					}
					else{
						XCToolkit.showToast("获取利率失败");
					}
						
				}else{
					tv_rate.setText("");
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {}
			
		});
		
		fglc_et_floatrate.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				String floatRateText=arg0.toString();
				if(!"-".equals(floatRateText)&&!"+".equals(floatRateText)){
					if("".equals(floatRateText)){
						loanRate=baseRate;
						floatRate=0;
					}
					else
						floatRate=Float.parseFloat(floatRateText);
					
					if(baseRate!=0){
						loanRate=baseRate*(1+floatRate/100);
						tv_rate.setText(loanRate+"");
					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {}
			
		});*/

		linearLayout.setOnTouchListener(new View.OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				linearLayout.requestFocus();
				return false;
			}
			
		});
		
		tvv_payyears.getValueView().setOnFocusChangeListener(new View.OnFocusChangeListener(){

			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				if(!arg1){
					String text=tvv_payyears.getValueText();
					if(!"".equals(text)&&!"0".equals(text)){
						loanYears=Integer.parseInt(text);
						String rate=DbManager.getRateByLimitAndRateType(loanYears*12+"","010");
						if(!"".equals(rate)){
							baseRate=new BigDecimal(rate);
							loanRate=baseRate.multiply(BigDecimal.ONE.add(floatRate.divide(new BigDecimal(100),6, BigDecimal.ROUND_HALF_UP))).floatValue();
							tvv_rate.setValueText(loanRate+"");
						}else{
							XCToolkit.showToast("获取利率失败");
						}
							
					}else{
						tvv_rate.setValueText("");
					}
				}
			}
			
		});
		
		tvv_floatrate.getValueView().setOnFocusChangeListener(new View.OnFocusChangeListener(){

			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				if(!arg1){
					String floatRateText=tvv_floatrate.getValueText();
					if(!"-".equals(floatRateText)&&!"+".equals(floatRateText)){
						if("".equals(floatRateText)){
							loanRate=baseRate.floatValue();
							floatRate=BigDecimal.ZERO;
						}
						else
							floatRate=new BigDecimal(floatRateText);
						
						if(baseRate.intValue()!=0){
							loanRate=baseRate.multiply(BigDecimal.ONE.add(floatRate.divide(new BigDecimal(100), 6,BigDecimal.ROUND_HALF_UP))).floatValue();
							tvv_rate.setValueText(loanRate+"");
						}
					}
				}
			}
			
		});
		
		adapter=new BaseAdapter(){

			@Override
			public int getCount() {
				return calcResults.size();
			}

			@Override
			public Object getItem(int arg0) {
				return calcResults.get(arg0);
			}

			@Override
			public long getItemId(int arg0) {
				return arg0;
			}

			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				ViewHolder viewHolder;
				if(arg1==null){
					viewHolder=new ViewHolder();
					arg1=inflater.inflate(R.layout.v_loancounter_item, null);
					viewHolder.tv_month=(TextView) arg1.findViewById(R.id.month);
					viewHolder.tv_monthPay=(TextView) arg1.findViewById(R.id.monthPay);
					viewHolder.tv_monthPrincipal=(TextView) arg1.findViewById(R.id.monthPrincipal);
					viewHolder.tv_monthInterests=(TextView) arg1.findViewById(R.id.monthInterests);
					viewHolder.tv_balance=(TextView) arg1.findViewById(R.id.balance);
					arg1.setTag(viewHolder);
				}else{
					viewHolder=(ViewHolder) arg1.getTag();
				}
				PayLoanBO result=calcResults.get(arg0);
				if(result.getYear()==0){
					viewHolder.tv_month.setText(result.getMonth()+"");
					viewHolder.tv_month.setTextColor(Color.GRAY);
					viewHolder.tv_monthPay.setText((int)result.getMonthPay()+"");
					viewHolder.tv_monthPrincipal.setText((int)result.getMonthPrincipal()+"");
					viewHolder.tv_monthInterests.setText((int)result.getMonthInterests()+"");
					viewHolder.tv_balance.setText((int)result.getBalance()+"");
				}else{
					viewHolder.tv_month.setText("第"+result.getYear()+"年");
					viewHolder.tv_month.setTextColor(Color.RED);
					viewHolder.tv_monthPay.setText("");
					viewHolder.tv_monthPrincipal.setText("");
					viewHolder.tv_monthInterests.setText("");
					viewHolder.tv_balance.setText("");
				}
				return arg1;
			}
			
			
			
		};

		loan_listview.setAdapter(adapter);
		
		btn_calculator.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				linearLayout.requestFocus();
				if(checkInput()){
					BigDecimal loanAmount=new BigDecimal(tvv_loanAmount.getValueText());
					int loanMonth=loanYears*12;
					
					calculateLoanDetail(loanAmount.multiply(new BigDecimal(10000)),new BigDecimal(loanRate),loanMonth);
					//loanDetail_tv_rateContent.setText(loanRate+"");
					loanDetail_tv_totalamountContent.setText(loanAmount+"万元");
					loanDetail_tv_interestContent.setText((String.format("%.4f", (interestsTotal/10000)))+"万元");
					
					//loanSummary_loanTotalContent.setText(loanAmount+" 万");
					loanSummary_monthPayContent.setText((String.format("%.2f", monthAveragePay))+" 元");
					//loanSummary_payInterestContent.setText((String.format("%.4f", (interestsTotal/10000)))+" 万");
					loanSummary_monthContent.setText(loanMonth+"月"+" ("+loanMonth/12+"年"+")");
					

					view_piechart.setLoanAmount(loanAmount.multiply(new BigDecimal(10000)).floatValue());
					view_piechart.setPayInterest(interestsTotal);
					view_piechart.surfaceCreated(view_piechart.getHolder());
					adapter.notifyDataSetChanged();
				}
			}
			
		});
	
	}
	
	static class ViewHolder {
		TextView tv_month;
		TextView tv_monthPay;
		TextView tv_monthPrincipal;
		TextView tv_monthInterests;
		TextView tv_balance;
	}
	
	/**
	 * 计算右边界面需要的数据
	 * @param money:贷款金额，rate：贷款年利率，month：贷款几个月
	 */
	private void calculateLoanDetail(BigDecimal money,BigDecimal rate,int month) {
		calcResults=new ArrayList<PayLoanBO>();
		BigDecimal loanAmount=money;
		int loanMonth=month;
		//月息
		BigDecimal loanRate=rate.divide(new BigDecimal(1200),6,BigDecimal.ROUND_HALF_UP);
		interestSum=0;
		monthpaySum=0;
		if(gtv_paybacktype.getCurrentValue().equals("等额本息")){
			FixedPaymentMortgage(loanAmount,loanMonth,loanRate);
		}else if(gtv_paybacktype.getCurrentValue().equals("等额本金")){
			FixedBasisMortgage(loanAmount,loanMonth,loanRate);
		}else if(gtv_paybacktype.getCurrentValue().equals("快速双周供")){
			//FastWeeksMortgage(loanAmount,loanMonth,loanRate);
		}else if(gtv_paybacktype.getCurrentValue().equals("一次性还本付息")){
			//OneTimePayMortgage();
		}
		monthAveragePay=(monthpaySum/loanMonth);
		interestsTotal= interestSum;
	}
	
	//等额本息
	private void FixedPaymentMortgage(BigDecimal loanAmount,int loanMonth,BigDecimal loanRate){
		PayLoanBO temp;
		BigDecimal leftLoanAmount=loanAmount;
		//记录年数
		int years=0;
		//月供
		BigDecimal payEachMonth=(loanAmount.multiply(loanRate).multiply(loanRate.add(new BigDecimal(1)).pow(loanMonth))).divide((loanRate.add(new BigDecimal(1)).pow(loanMonth)).subtract(new BigDecimal(1)),4,BigDecimal.ROUND_HALF_UP);
		//月供本金
		BigDecimal monthPrincipal;
		//月供利息
		BigDecimal interestsEachMonth;
		for(int i=0;i<loanMonth;i++){
			interestsEachMonth=leftLoanAmount.multiply(loanRate).setScale(4, BigDecimal.ROUND_HALF_UP);
			monthPrincipal=payEachMonth.subtract(interestsEachMonth).setScale(4, BigDecimal.ROUND_HALF_UP);
			leftLoanAmount=leftLoanAmount.subtract(monthPrincipal).setScale(4, BigDecimal.ROUND_HALF_UP);
			
			//加入年份
			if(i%12==0){
				temp=new PayLoanBO();
				years++;
				temp.setYear(years);
				calcResults.add(temp);
			}
			
			temp=new PayLoanBO();
			temp.setMonth(i%12+1);
			temp.setMonthPay(payEachMonth.floatValue());
			temp.setMonthInterests(interestsEachMonth.floatValue());
			temp.setMonthPrincipal(monthPrincipal.floatValue());
			temp.setBalance(leftLoanAmount.floatValue());

			calcResults.add(temp);
			interestSum+=interestsEachMonth.floatValue();
			monthpaySum+=payEachMonth.floatValue();
		}
	}
	
	//等额本金
	private void FixedBasisMortgage(BigDecimal loanAmount,int loanMonth,BigDecimal loanRate){
		PayLoanBO temp;
		//记录年数
		int years=0;
		//累计已还
		BigDecimal payed=new BigDecimal(0);
		//本月月供
		BigDecimal payThisMonth;
		//每月本金=贷款额/期数
		BigDecimal monthPrincipal=loanAmount.divide(new BigDecimal(loanMonth),2,BigDecimal.ROUND_HALF_UP);
		//本月利息
		BigDecimal interestsThisMonth;
		
		interestSum=loanAmount.multiply(loanRate).multiply(new BigDecimal(loanMonth+1)).divide(new BigDecimal(2),2,BigDecimal.ROUND_HALF_UP).floatValue();
		//float payTotal=interestsTotal+loanMonth;
		for(int i=0;i<loanMonth;i++){
			payThisMonth=monthPrincipal.add((loanAmount.subtract(payed)).multiply(loanRate)).setScale(4, BigDecimal.ROUND_HALF_UP);
			interestsThisMonth=(loanAmount.subtract(payed)).multiply(loanRate).setScale(4, BigDecimal.ROUND_HALF_UP);
			payed=payed.add(monthPrincipal).setScale(4, BigDecimal.ROUND_HALF_UP);
			
			//加入年份
			if(i%12==0){
				temp=new PayLoanBO();
				years++;
				temp.setYear(years);
				calcResults.add(temp);
			}
			
			temp=new PayLoanBO();
			temp.setMonth(i%12+1);
			temp.setMonthPay(payThisMonth.floatValue());
			temp.setMonthInterests(interestsThisMonth.floatValue());
			temp.setMonthPrincipal(monthPrincipal.floatValue());
			temp.setBalance(loanAmount.subtract(payed).floatValue());
			calcResults.add(temp);
			
			monthpaySum+=payThisMonth.floatValue();
		}
	}
	
	//快速双周供
	private void FastWeeksMortgage(BigDecimal loanAmount,int loanMonth,BigDecimal loanRate){
		//双周利率
		BigDecimal TwoWeekRate=loanRate.divide(new BigDecimal(2),6,BigDecimal.ROUND_HALF_UP);
		//1.按等额本息计算月供
		BigDecimal payEachMonth=(loanAmount.multiply(loanRate).multiply(loanRate.add(new BigDecimal(1)).pow(loanMonth))).divide((loanRate.add(new BigDecimal(1)).pow(loanMonth)).subtract(new BigDecimal(1)),2,BigDecimal.ROUND_HALF_UP);
		//2.计算月供的一半
		BigDecimal payEach2Weeks=payEachMonth.divide(new BigDecimal(2),6,BigDecimal.ROUND_HALF_UP);
		//3.反推双周供还款总期数	4.还款总期数取整
		int peroid= new BigDecimal(Math.log((payEach2Weeks.divide(payEach2Weeks.subtract(loanAmount.multiply(TwoWeekRate)),2,BigDecimal.ROUND_HALF_UP)).doubleValue())).divide(new BigDecimal(Math.log(TwoWeekRate.doubleValue()+1)),2,BigDecimal.ROUND_HALF_UP).intValue();
		//5.按照新还款总期数，调用等额本息公式重新计算期供
		BigDecimal periodPay=loanAmount.multiply(TwoWeekRate).multiply(BigDecimal.ONE.add(BigDecimal.ONE.divide(BigDecimal.ONE.add(TwoWeekRate).pow(peroid).subtract(BigDecimal.ONE),2,BigDecimal.ROUND_HALF_UP)));
	}
	
	//一次性还本付息
	private void OneTimePayMortgage(){
		
	}
	
	//检查左边信息填写
	private boolean checkInput(){
		SubHelperUtil.setTableName("");
		if(!SubHelperUtil.showNumDecimalToast(tvv_loanAmount.getValueText(),"贷款金额"))
			return false;
		if(!SubHelperUtil.showNumToast(tvv_payyears.getValueText(),"按揭年数"))
			return false;
		if("".equals(tvv_rate.getValueText())){
			XCToolkit.showToast("获取利率失败");
			return false;
		}
		
		return true;
	}

	
}
