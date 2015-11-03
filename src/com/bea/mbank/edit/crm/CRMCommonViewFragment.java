package com.bea.mbank.edit.crm;

import com.bea.mbank.R;
import com.bea.remote.models.crm.CrmCustDetail;
import com.cyg.viewinject.annotation.ViewInject;

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
import xc.android.fragment.XCBaseFragment;
import xc.android.utils.XCJsonHelper;

/**
 * crm
 * flag=1:客户基本信息
 * flag=2:客户分析
 * @author fanglinhao
 */
public class CRMCommonViewFragment extends XCBaseFragment{
	@ViewInject(R.id.webview)
	WebView webView;
	CrmCustDetail custDetail;
	int flag;
	public CRMCommonViewFragment(CrmCustDetail custDetail, int flag) {
		this.custDetail = custDetail;
		this.flag = flag;
	}

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup group) {
		return inflater.inflate(R.layout.fm_grwdy_crm_custinfo, null);
	}

	@Override
	protected void onInitContentView(View view) {
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new JsHelper(getActivity()),"jsHelper");
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient());
		if(1 == flag){
			webView.loadUrl("file:///android_asset/html/crmCustInfo.html");
		}else if(2 == flag){
			webView.loadUrl("file:///android_asset/html/crmCustAnalysis.html");
		}else if(3 == flag){
			webView.loadUrl("file:///android_asset/html/crmCoreCust.html");
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
        	final String json = XCJsonHelper.toJSONString(custDetail);
        	webView.post(new Runnable() {
				@Override
				public void run() {
					if(1 == flag){
						webView.loadUrl("javascript:loadCustInfoData('"+json+"')");
					}else if(2 == flag){
						webView.loadUrl("javascript:loadCustAnalysisData('"+json+"')");
					}else if(3 == flag){
						webView.loadUrl("javascript:loadCoreCustData('"+json+"')");
					}
					
				}
			});
        }
        @JavascriptInterface
        public void returnBack(){
        	webView.post(new Runnable() {
				@Override
				public void run() {
					((XCBaseActivity)getActivity()).popupTopFragmentController();
				}
			});
        }
    }
}
