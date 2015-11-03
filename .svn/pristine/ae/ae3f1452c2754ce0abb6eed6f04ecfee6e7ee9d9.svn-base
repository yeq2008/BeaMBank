package com.bea.mbank.mytask;

import com.bea.mbank.R;
import com.cyg.viewinject.event.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import xc.android.activity.XCBaseActivity;
import xc.android.fragment.XCBaseFragment;

public class CollecteDetailPanelFragment extends XCBaseFragment{
	WebView webView;
	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup viewGroup) {
		return inflater.inflate(R.layout.fm_mytask_cuishou_detail_panel, null);
	}

	@Override
	protected void onInitContentView(View view) {
	}
	
	@OnClick(R.id.fgdpp_tv_basicinfo)
	public void onClick1(View v) {
		redirectAddr("model1");
	}
	
	@OnClick(R.id.fgdpp_tv_last3return)
	public void onClick2(View v) {
		redirectAddr("model2");
	}
	
	@OnClick(R.id.fgdpp_tv_lxxx)
	public void onClick3(View v) {
		redirectAddr("model3");
	}
	
//	@OnClick(R.id.fgdpp_tv_bczxjl)
//	public void onClick4(View v) {
//		redirectAddr("model4");
//	}
	
//	@OnClick(R.id.fgdpp_tv_phonerecord)
//	public void onClick5(View v) {
//		redirectAddr("model4_1");
//	}
	
	@OnClick(R.id.fgdpp_tv_cnhcsj)
	public void onClick6(View v) {
		redirectAddr("model5_1");
	}
	
	@OnClick(R.id.fgdpp_tv_lccsjl)
	public void onClick7(View v) {
		redirectAddr("model5_2");
	}
	
	private void redirectAddr(String id){
		if(null == webView)
			webView = (WebView) getActivity().findViewById(R.id.webview);
		webView.loadUrl("javascript:redirectAddr('"+id+"')");
	}
	
	@OnClick(R.id.fgdpp_btn_send)
	public void send(View view){
		if(null == webView)
			webView = (WebView) getActivity().findViewById(R.id.webview);
		webView.loadUrl("javascript:getData()");
	}
	
	@OnClick(R.id.fgdpp_iv_01)
	public void backReturn(View view){
		((XCBaseActivity)getActivity()).popupTopFragmentController();
	}
}
