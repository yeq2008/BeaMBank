package com.bea.mbank.edit.crm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.crm.CrmBaoXian;
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
 * crm 保险详情
 * @author fanglinhao
 */
public class CRMBaoxianViewFragment extends XCBaseHttpFragment {
	@ViewInject(R.id.webview)
	WebView webView;
	Map<String ,Object> map;
	List<CrmBaoXian> list;
	public CRMBaoxianViewFragment(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup viewGroup) {
		return inflater.inflate(R.layout.fm_grwdy_crm_baoxian, null);
	}

	@Override
	protected void onInitContentView(View view) {
		requestData();
	}
	
	private void requestData(){
		if(null == map)
			return;
		try {
			jsonRequest(104, "通用接口", JsonRemoteBO.reqParam(ConstDefined.crmBaoXianDetial, map));
		} catch (Exception e) {
			XCLog.e("crm保险详情请求错误信息", e.getMessage());
			e.printStackTrace();
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
	
	@Override
	public void onResponsSuccess(int actionTag, Object object) {
		if(104 == actionTag){
			try {
				list = XCJsonHelper.parseArray(object.toString(),CrmBaoXian.class);
				loadHtml();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
        		list = new ArrayList<CrmBaoXian>();
        	final String json = XCJsonHelper.toJSONString(list);
        	webView.post(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl("javascript:loadBaoxianData('"+json+"')");
				}
			});
        }
    }
}
