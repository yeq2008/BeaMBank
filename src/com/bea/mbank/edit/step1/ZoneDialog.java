package com.bea.mbank.edit.step1;

import java.util.List;

import xc.android.utils.XCCrypto;
import xc.android.utils.XCToolkit;

import com.android.widgets.GroupTagView.OnTagButtonSelectedListener;
import com.bea.database.DbManager;
import com.bea.database.codemodel.SYS_AC_AREA;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.mbank.R;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.bea.remote.models.grwdy.DetailAddrBO;
import com.cyg.viewinject.VInject;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @author fanglinhao 国家省市区选择对话框
 */
public class ZoneDialog extends Dialog {
	private Context context;
	private LayoutInflater inflater;
	private ZoneDialogListener listener;
	DetailAddrBO detailAddr;

	public interface ZoneDialogListener {
		public void onClick(DetailAddrBO addr);
	}

	final int height = (int) (XCToolkit.getScreenHeight() * 0.4925f);
	final int width = (int) (XCToolkit.getScreenWidth() * 0.5656f);
	@ViewInject(R.id.pzp_iv_vertical_divider1)
	View pzp_iv_vertical_divider1;
	// 国家
	@ViewInject(R.id.pzp_tv_country)
	TextView tvCountry;
	// 省份
	@ViewInject(R.id.pzp_tv_province)
	TextView tvProvince;
	@ViewInject(R.id.pzp_iv_horizontal_divider1)
	View pzp_iv_horizontal_divider1;
	@ViewInject(R.id.pzp_iv_vertical_divider2)
	View pzp_iv_vertical_divider2;
	// 城市
	@ViewInject(R.id.pzp_tv_city)
	TextView tvCity;
	// 地区
	@ViewInject(R.id.pzp_tv_zone)
	TextView tvZone;
	@ViewInject(R.id.pzp_iv_horizontal_divider2)
	View pzp_iv_horizontal_divider2;
	// 楼盘
	@ViewInject(R.id.pzp_et_lp)
	EditText etLp;
	// 详细地址
	@ViewInject(R.id.pzp_et_addr)
	EditText etAddr;

	public ZoneDialog(Context context, DetailAddrBO addr,
			ZoneDialogListener listener) {
		super(context);
		this.context = context;
		this.listener = listener;
		this.detailAddr = addr;
		if (null == detailAddr)
			detailAddr = new DetailAddrBO();
		init();
	}

	private void init() {
		List<SYS_AC_AREA> list = DbManager.zoneData(detailAddr.getAllid());
		if (null != list) {
			SYS_AC_AREA province = null;
			SYS_AC_AREA city = null;
			SYS_AC_AREA zone = null;
			if (list.size() > 0)
				province = list.get(0);
			if (list.size() > 1)
				city = list.get(1);
			if (list.size() > 2)
				zone = list.get(2);
			if (null != province) {
				detailAddr.setProvince(province.getAREA_ID());
				detailAddr.setProvinceId(province.getAREA_NO());
				detailAddr.setProvinceName(province.getAREA_NAME());
			}
			if (null != city) {
				detailAddr.setCity(city.getAREA_ID());
				detailAddr.setCityId(city.getAREA_NO());
				detailAddr.setCityName(city.getAREA_NAME());
			}
			if (null != zone) {
				detailAddr.setZone(zone.getAREA_ID());
				detailAddr.setZoneId(zone.getAREA_NO());
				detailAddr.setZoneName(zone.getAREA_NAME());
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.pop_zone_parent, null);

		setContentView(contentView);
		VInject.inject(this, contentView);
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.height = height;
		lp.width = width;
		dialogWindow.setAttributes(lp);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		initParams();

		// 修改地址的时候，下级的地址清空
		tvProvince.addTextChangedListener(new MyTextWatcher(tvProvince));
		tvCity.addTextChangedListener(new MyTextWatcher(tvCity));
	}

	class MyTextWatcher implements TextWatcher {
		private View mView;
		private String before = "";
		private String after = "";

		public MyTextWatcher(View mView) {
			super();
			this.mView = mView;
		}

		@Override
		public void afterTextChanged(Editable arg0) {
			after = arg0.toString();
			if (!before.equals(after)) {
				switch (mView.getId()) {
				case R.id.pzp_tv_province:
					tvCity.setText("");
				case R.id.pzp_tv_city:
					tvZone.setText("");
				default:
					break;
				}
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			before = s.toString();
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

	}

	/**
	 * 初始化省市区
	 */
	private void initParams() {
		if (null == detailAddr.getCountryId()
				|| null == detailAddr.getCountryName()) {
			detailAddr.setCountryId("CHN");
			detailAddr.setCountryName("中华人民共和国");
		}
		tvCountry.setText(detailAddr.getCountryName());
		if (null != detailAddr.getProvinceId()
				&& null != detailAddr.getProvinceName())
			tvProvince.setText(detailAddr.getProvinceName());
		if (null != detailAddr.getCityId() && null != detailAddr.getCityName())
			tvCity.setText(detailAddr.getCityName());
		if (null != detailAddr.getZoneId() && null != detailAddr.getZoneName())
			tvZone.setText(detailAddr.getZoneName());
		if (null != detailAddr.getLp())
			etLp.setText(detailAddr.getLp());
		if (null != detailAddr.getAddr())
			etAddr.setText(detailAddr.getAddr());
	}

	@OnClick(R.id.pzp_tv_country)
	public void countrySelect(View country) {
		popSelect(country, DbManager.codeAyncDatas("CountryCode"), "NAME",
				tvCountry, "国家");
	}

	@OnClick(R.id.pzp_tv_province)
	public void provinceSelect(View province) {
		List list = DbManager.zoneDatas(null);
		popSelect(province, DbManager.zoneDatas(null), "AREA_NAME", tvProvince,
				"省份");
	}

	@OnClick(R.id.pzp_tv_city)
	public void citySelect(View city) {
		if (null == detailAddr || null == detailAddr.getProvince())
			return;
		popSelect(city, DbManager.zoneDatas(detailAddr.getProvince()),
				"AREA_NAME", tvCity, "城市");
	}

	@OnClick(R.id.pzp_tv_zone)
	public void zoneSelect(View zone) {
		if (null == detailAddr || null == detailAddr.getCity())
			return;
		popSelect(zone, DbManager.zoneDatas(detailAddr.getCity()), "AREA_NAME",
				tvZone, "区域");
	}

	@OnClick(R.id.pzp_im_refresh)
	public void refreshSelect(View view) {
		tvCountry.setText("");
		tvProvince.setText("");
		tvCity.setText("");
		tvZone.setText("");
		etLp.setText("");
		etAddr.setText("");
	}

	@OnClick(R.id.pzp_im_close)
	public void close(View view) {
		// DetailAddrBO addr = getDetailAddr();
		// listener.onClick(addr);
		dismiss();
	}

	@OnClick(R.id.pzp_im_confirm)
	public void confirm(View view) {
		DetailAddrBO addr = getDetailAddr();
		listener.onClick(addr);
		dismiss();
	}

	private DetailAddrBO getDetailAddr() {
		detailAddr.setLp(etLp.getText().toString().trim());
		detailAddr.setAddr(etAddr.getText().toString().trim());
		return detailAddr;
	}

	/**
	 * 回填数据
	 * 
	 * @param v
	 * @param list
	 * @param editText
	 */
	private void popSelect(View v, final List list, String name,
			final TextView textView, String title) {
		View view = inflater.inflate(R.layout.pop_zone, null);
		// TextView tvHint = (TextView)view.findViewById(R.id.pz_et_01);
		// tvHint.setHint(title);
		ImageView imConfirm = (ImageView) view.findViewById(R.id.pz_im_confirm);
		ImageView imClose = (ImageView) view.findViewById(R.id.pz_im_close);
		GroupTagViewEx tagView = (GroupTagViewEx) view
				.findViewById(R.id.pz_gtv_item);
		tagView.addButtons(list, name);

		final PopupWindow popupWindow = new PopupWindow(view, width - 20,
				height - 20);
		popupWindow.showAsDropDown((View) v.getParent().getParent(), 0,
				-height + 20);
		popupWindow.setOutsideTouchable(true);

		imClose.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		imConfirm.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
			}
		});
		tagView.setOnTagButtonSelectedListener(new OnTagButtonSelectedListener() {
			@Override
			public void OnTagButtonSelected(int index, Object btnModel) {
				String tag = textView.getTag().toString();
				String s = "";
				if ("country".equals(tag)) {
					detailAddr.setCountryId(((YRL_BASIC_DATA) btnModel).getNO());
					s = ((YRL_BASIC_DATA) btnModel).getNAME();
					detailAddr.setCountryName(s);
				} else if ("province".equals(tag)) {
					detailAddr.setProvince(((SYS_AC_AREA) btnModel)
							.getAREA_ID());
					detailAddr.setProvinceId(((SYS_AC_AREA) btnModel)
							.getAREA_NO());
					s = ((SYS_AC_AREA) btnModel).getAREA_NAME();
					detailAddr.setProvinceName(s);
					;
				} else if ("city".equals(tag)) {
					detailAddr.setCity(((SYS_AC_AREA) btnModel).getAREA_ID());
					detailAddr.setCityId(((SYS_AC_AREA) btnModel).getAREA_NO());
					s = ((SYS_AC_AREA) btnModel).getAREA_NAME();
					detailAddr.setCityName(s);
				} else {
					detailAddr.setZone(((SYS_AC_AREA) btnModel).getAREA_ID());
					detailAddr.setZoneId(((SYS_AC_AREA) btnModel).getAREA_NO());
					s = ((SYS_AC_AREA) btnModel).getAREA_NAME();
					detailAddr.setZoneName(s);
				}
				textView.setText(s);
				popupWindow.dismiss();
			}
		});
	}

	// 隐藏i级以下的地址
	public void goneView(int i) {
		switch (i) {
		case 1: {// 隐藏国家以下的地址
			tvProvince.setVisibility(View.GONE);
		}
		case 2: {// 隐藏省级以下的地址
			pzp_iv_horizontal_divider1.setVisibility(View.GONE);
			pzp_iv_vertical_divider2.setVisibility(View.GONE);
			tvCity.setVisibility(View.GONE);
		}
		case 3: {// 隐藏城市以下的地址
			tvZone.setVisibility(View.GONE);
			pzp_iv_horizontal_divider2.setVisibility(View.GONE);
		}
		case 4: {// 隐藏区域以下的地址
			etLp.setVisibility(View.GONE);
			etAddr.setVisibility(View.GONE);
		}
		default:
			break;
		}
	}

	public TextView getTvCountry() {
		return tvCountry;
	}

	public void setTvCountry(TextView tvCountry) {
		this.tvCountry = tvCountry;
	}

	public TextView getTvProvince() {
		return tvProvince;
	}

	public void setTvProvince(TextView tvProvince) {
		this.tvProvince = tvProvince;
	}

	public TextView getTvCity() {
		return tvCity;
	}

	public void setTvCity(TextView tvCity) {
		this.tvCity = tvCity;
	}

	public TextView getTvZone() {
		return tvZone;
	}

	public void setTvZone(TextView tvZone) {
		this.tvZone = tvZone;
	}

	public EditText getEtLp() {
		return etLp;
	}

	public void setEtLp(EditText etLp) {
		this.etLp = etLp;
	}

	public EditText getEtAddr() {
		return etAddr;
	}

	public void setEtAddr(EditText etAddr) {
		this.etAddr = etAddr;
	}
}