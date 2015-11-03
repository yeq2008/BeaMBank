package com.bea.mbank.mytask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;

import com.android.wheelview.WheelAdapter;
import com.android.wheelview.WheelView;
import com.android.wheelview.WheelView.OnWheelChangedListener;
import com.bea.mbank.R;
import com.cyg.viewinject.VInject;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 获取日期
 * 
 * @author fanglinhao
 */
public class DateDialog extends Dialog {
	private Context context;
	private DateDialogListener listener;
	private String date;// 格式 xxxx/xx/xx
	@ViewInject(R.id.wheelyear)
	WheelView wheelyear;
	@ViewInject(R.id.wheelmonth)
	WheelView wheelmonth;
	@ViewInject(R.id.wheelday)
	WheelView wheelday;

	public interface DateDialogListener {
		public void onClick(String date);
	}

	public DateDialog(Context context, String date, DateDialogListener listener) {
		super(context);
		this.context = context;
		this.listener = listener;
		this.date = date;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.pop_html_datepicker, null);
		setContentView(contentView);
		VInject.inject(this, contentView);
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.height = (int) (XCToolkit.getScreenHeight() * 0.4925f);
		lp.width = (int) (XCToolkit.getScreenWidth() * 0.5656f);
		dialogWindow.setAttributes(lp);

		init();
	}

	private void init() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (null != date && date.length() > 7) {
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				calendar.setTime(format.parse(date));
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH);
				day = calendar.get(Calendar.DAY_OF_MONTH);
			} catch (Exception e) {
				XCLog.e("message", "日期转换错误：" + e.getMessage());
			}
		}

		wheelyear.setAdapter(new WheelAdapter(getYearList(), null));
		wheelmonth.setAdapter(new WheelAdapter(getMonthList(), null));
		wheelyear.setCurrentItem(year - 1900);
		wheelmonth.setCurrentItem(month);
		wheelday.setAdapter(new WheelAdapter(getDayList(year, month), null));
		wheelday.setCurrentItem(day - 1);
		wheelyear.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {

				int year = Integer.parseInt(wheel.getCurrentOriginObject()
						.toString().replace("年", ""));
				int month = Integer.parseInt(wheelmonth
						.getCurrentOriginObject().toString().replace("月", ""));
				wheelday.setAdapter(new WheelAdapter(
						getDayList(year, month - 1), null));

			}
		});
		wheelmonth.addChangingListener(new OnWheelChangedListener() {
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year = Integer.parseInt(wheelyear.getCurrentOriginObject()
						.toString().replace("年", ""));
				int month = Integer.parseInt(wheel.getCurrentOriginObject()
						.toString().replace("月", ""));
				wheelday.setAdapter(new WheelAdapter(
						getDayList(year, month - 1), null));
			}
		});
	}

	@OnClick(R.id.closedate)
	public void close(View view) {
		dismiss();
	}

	@OnClick(R.id.OK)
	public void commit(View view) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				wheelyear.getCurrentOriginObject().toString().replace("年", "/"))
				.append(wheelmonth.getCurrentOriginObject().toString()
						.replace("月", "/"))
				.append(wheelday.getCurrentOriginObject().toString()
						.replace("日", ""));
		listener.onClick(sb.toString());
		dismiss();
	}

	public ArrayList<String> getYearList() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 1900; i < 2101; i++) {
			list.add(i + "年");
		}
		return list;

	}

	public ArrayList<String> getMonthList() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 1; i < 13; i++) {
			if (i < 10) {
				list.add("0" + i + "月");
			} else {
				list.add(i + "月");
			}
		}
		return list;
	}

	public ArrayList<String> getDayList(int year, int month) {
		ArrayList<String> list = new ArrayList<String>();
		int day = 0;
		if (1 == month) {
			if (0 == year % 400 || (0 != year % 100 && 0 == year % 4)) {
				day = 29;
			} else {
				day = 28;
			}
		} else {
			switch (month) {
			case 0:
				day = 31;
				break;
			case 2:
				day = 31;
				break;
			case 3:
				day = 30;
				break;
			case 4:
				day = 31;
				break;
			case 5:
				day = 30;
				break;
			case 6:
				day = 31;
				break;
			case 7:
				day = 31;
				break;
			case 8:
				day = 30;
				break;
			case 9:
				day = 31;
				break;
			case 10:
				day = 30;
				break;
			case 11:
				day = 31;
				break;
			}
		}
		for (int i = 1; i < day + 1; i++) {
			if (i < 10) {
				list.add("0" + i + "日");
			} else {
				list.add(i + "日");
			}
		}
		return list;
	}
}
