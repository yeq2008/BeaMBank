package com.bea.mbank.edit.step1;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.remote.JsonRemoteBO;
import com.cyg.viewinject.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import xc.android.fragment.XCBaseHttpFragment;
import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;

public class BlackListHomeFragment extends XCBaseHttpFragment {
	@ViewInject(R.id.webview)
	WebView webView;
	private Handler handler = new Handler();

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup group) {
		return inflater.inflate(R.layout.fm_grwdy_blacklist_home, null);
	}

	@SuppressLint({ "JavascriptInterface"})
	@Override
	protected void onInitContentView(View view) {
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new WebAppInterface(getActivity()),"webviewutil");
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient());
		webView.loadUrl("file:///android_asset/html/blacklist.html");
	}
	
	public class WebAppInterface
    {
		Context mContext;
        WebAppInterface(Context c)
        {
            mContext = c;
        }
		@JavascriptInterface
        public void getNDSInfo(){
			BeaAppSettings.instance();
			if(null != BeaAppSettings.blankListMap){
				Map<String, Object> paramMap = BeaAppSettings.blankListMap;
				String username = (String) paramMap.get("xin");
				String userming = (String) paramMap.get("ming");
				if(null != username ){
					String nametype = "C"; 
					Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
					Matcher m = pattern.matcher(username);
					if(m.matches()){
						nametype = "E";  
					}
					Map<String, String> object = new HashMap<String, String>();
					object.put("FIRST_NAME", username);
					object.put("NAME", userming);
					object.put("NAME_LANG", nametype);
					requestData(101, ConstDefined.NDSInfo, object);
				}
			}
        }
		
		@JavascriptInterface
        public void getAlertInfo(){
			Map<String, Object> paramMap = BeaAppSettings.blankListMap;
			Map<String, String> object = new HashMap<String, String>();
			object.put("Entity_Code", "0018");
			object.put("KEY-ID-I", paramMap.get("global_id").toString());
			object.put("COD-ID-I", paramMap.get("global_type").toString());
			object.put("COD-ISS-CTRY-I", paramMap.get("global_con").toString());
			requestData(102, ConstDefined.AFTAertInfo, object);
        }
		
		@JavascriptInterface
        public void getRelationInfo(){
			Map<String, Object> paramMap = BeaAppSettings.blankListMap;
			Map<String, String> object = new HashMap<String, String>();
			object.put("Entity_Code", "0018");
			object.put("KEY-ID-I", paramMap.get("global_id").toString());
			object.put("COD-ID-I", paramMap.get("global_type").toString());
			object.put("COD-ISS-CTRY-I", paramMap.get("global_con").toString());
			requestData(103, ConstDefined.AFTRelationInfo, object);
        }
		
		@JavascriptInterface
        public void getFSInfo(){
			Map<String, Object> paramMap = BeaAppSettings.blankListMap;
			Map<String, String> object = new HashMap<String, String>();
			object.put("Entity_Code", "0018");
			object.put("KEY-ID-I", paramMap.get("global_id").toString());
			object.put("COD-ID-I", paramMap.get("global_type").toString());
			object.put("COD-ISS-CTRY-I", paramMap.get("global_con").toString());
			requestData(104, ConstDefined.AFTFsInfo, object);
        }
		
		private void requestData(int actiontag, int tag, Map<String, String> object){
			try {
				jsonRequest(actiontag, "通用接口", JsonRemoteBO.reqParam(tag, object));
			} catch (Exception e) {
				XCLog.e("黑名单错误信息", e.getMessage());
			}
		}
    }

	@Override
	public void onResponsSuccess(int tag, Object obj) {
		if(101 == tag){
			responseData("NDSInfo",obj);
		}else if(102 == tag){
			responseData("alertInfo","");
		}else if(103 == tag){
			responseData("relationInfo","");
		}else if(104 == tag){
			responseData("fsInfo","");
		}
	}
	
	@Override
	public void onResponsFailed(int actionTag, String obj) {
		XCToolkit.showToast(obj);
	}
	
	@Override
	public void onNetConnectFailed(int actionTag, String obj) {
		onResponsFailed(actionTag, obj);
	}
	
	private void responseData(final String functionname,final Object obj){
		handler.post(new Runnable() {
			@Override
			public void run() {
				webView.loadUrl("javascript:"+functionname+"('"+obj.toString()+"')");
			}
		});
	}
}
