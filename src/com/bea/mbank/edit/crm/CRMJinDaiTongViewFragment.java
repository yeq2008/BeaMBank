package com.bea.mbank.edit.crm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.crm.CrmJinDaiTong;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import xc.android.activity.XCBaseActivity;
import xc.android.fragment.XCBaseHttpFragment;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCLog;

/**
 * crm ���ͨ
 * @author fanglinhao
 */
public class CRMJinDaiTongViewFragment extends XCBaseHttpFragment {
	@ViewInject(R.id.webview)
	WebView webView;
	Map<String ,Object> map;
	List<CrmJinDaiTong> list;
	public CRMJinDaiTongViewFragment(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup viewGroup) {
		return inflater.inflate(R.layout.fm_grwdy_crm_jindaitong, null);
	}

	@Override
	protected void onInitContentView(View view) {
		requestData();
	}
	
	private void requestData(){
		if(null == map)
			return;
		try {
			jsonRequest(107, "ͨ�ýӿ�", JsonRemoteBO.reqParam(ConstDefined.crmJinDaiTongDetial, map));
		} catch (Exception e) {
			XCLog.e("crm���ͨ�������������Ϣ", e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void onResponsSuccess(int actionTag, Object object) {
		if(107 == actionTag){
			try {
				list = XCJsonHelper.parseArray(object.toString(),CrmJinDaiTong.class);
				loadHtml();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void loadHtml(){
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new JsHelper(getActivity()),"jsHelper");
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient());
		webView.loadUrl("file:///android_asset/html/crmdetail.html");
	}
	
	@OnClick(R.id.iv_return)
	public void back(View view){
		((XCBaseActivity)getActivity()).popupTopFragmentController();
	}
	
	public class JsHelper
    {
        Context mContext;
        JsHelper(Context c)
        {
            mContext = c;
        }
        
        @JavascriptInterface
        public void loadData(){
        	if(null == list)
        		list = new ArrayList<CrmJinDaiTong>();
        	final String json = XCJsonHelper.toJSONString(list);
        	webView.post(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl("javascript:loadJindaitongData('"+json+"')");
				}
			});
        }
    }
}