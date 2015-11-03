package com.bea.mbank.mytask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.database.DbManager;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.grouptagpop.GroupTagDialog;
import com.bea.grouptagpop.GroupTagDialog.OnSortListItemClickListener;
import com.bea.mbank.R;
import com.bea.mbank.mytask.DateDialog.DateDialogListener;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.cuishou.AddCustCsBO;
import com.bea.remote.models.cuishou.AddHisCsBO;
import com.bea.remote.models.cuishou.AddRelationTypeBO;
import com.bea.remote.models.cuishou.RequestCuiShouBo;
import com.bea.remote.models.user.UserInfoBO;
import com.cyg.viewinject.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import xc.android.activity.XCBaseActivity;
import xc.android.fragment.XCBaseHttpFragment;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;

/**
 * @author flh
 *	数据预览页面
 */
public final class CollecteDetailFragment extends XCBaseHttpFragment {
	String serialNo;//借据编号
	public CollecteDetailFragment(String serialNo) {
		this.serialNo = serialNo;
	}

	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_mytask_cuishou_detail, null);
	}

	@ViewInject(R.id.webview)
	WebView webView;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			XCToolkit.showToast("提交成功");
			CollecteDetailFragment.this.sendMessage(ConstDefined.cuishouDetailFragment, null);
			popupTopFragmentController();
		}
	};
	
	@SuppressLint("JavascriptInterface")
	@Override
	protected void onInitContentView(View view) {
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new WebAppInterface(getActivity()),"webviewutil");
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
				//构建一个Builder来显示网页中的对话框
				Builder builder = new Builder(getActivity());
				builder.setTitle("提示");
				builder.setMessage(message);
				builder.setPositiveButton(android.R.string.ok,
						new AlertDialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								result.confirm();
							}
						});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;
			};
		});
		webView.loadUrl("file:///android_asset/html/cuishou.html");
	}
	
	private void requestData(){
		Map<String, String> object = new HashMap<String, String>();
		object.put("SerialNo", serialNo);
		try {
			jsonRequest(101, "通用接口", JsonRemoteBO.reqParam(ConstDefined.CollecteDetail, object));
		} catch (Exception e) {
			XCLog.e("催收详情错误信息", e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void onResponsSuccess(int actionTag, Object object) {
		if(101 == actionTag){
			final String json = object==null?"":object.toString();
			webView.post(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl("javascript:loadData('"+json+"')");
				}
			});
		}else if(102 == actionTag){
			handler.sendEmptyMessage(0);
		}
	}
	
	public class WebAppInterface
    {
        Context mContext;
        WebAppInterface(Context c)
        {
            mContext = c;
        }
        // 如果target 大于等于API 17，则需要加上如下注解
        @JavascriptInterface
        public void handData(String data)
        {
        	Calendar calendar = Calendar.getInstance();
    		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        	RequestCuiShouBo cuiShouBo = null;
        	UserInfoBO userInfoBO = BeaAppSettings.getUserInfo();
        	try {
				cuiShouBo = XCJsonHelper.parseObject(data, RequestCuiShouBo.class);
				if(null != cuiShouBo){
					//客户承诺记录
					List<AddCustCsBO> custCsBOList = cuiShouBo.getCustormerPromise();
					AddCustCsBO custCsBO = custCsBOList.get(0);
					custCsBO.setInputUser(userInfoBO.getEmployee_mark());
					custCsBO.setInputOrg(userInfoBO.getOrg_id());
					custCsBO.setInputDate(format.format(calendar.getTime()));
					custCsBO.setUpdateUser(userInfoBO.getEmployee_mark());
					
					//历史催收记录
					List<AddHisCsBO> hisCsBOList = cuiShouBo.getCollectionHis();
					AddHisCsBO hisCsBO = hisCsBOList.get(0);
					hisCsBO.setExecutor(userInfoBO.getEmployee_mark());
					hisCsBO.setExecuteOrg(userInfoBO.getOrg_id());
					
					//更改联系方式
					if(null != cuiShouBo.getUPTEL()){
						List<AddRelationTypeBO> typeBOList = cuiShouBo.getUPTEL();
						AddRelationTypeBO typeBO = typeBOList.get(0);
						typeBO.setStatus("1");
						typeBO.setInputUser(userInfoBO.getEmployee_mark());
						typeBO.setInputDate(format.format(calendar.getTime()));
					}
					jsonRequest(102, "通用接口", JsonRemoteBO.reqParam(ConstDefined.SendMCollection, cuiShouBo));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        @JavascriptInterface
        public void onloadData(){
        	requestData();
        }
        
        @JavascriptInterface
        public void getDate(String id,String date){
        	final String inputid = id;
        	DateDialog dialog = new DateDialog(getActivity(),date, new DateDialogListener() {
				@Override
				public void onClick(String date) {
		        	final String s = date;
					webView.post(new Runnable() {
						@Override
						public void run() {
							webView.loadUrl("javascript:sendDate('"+s+"','"+inputid+"')");
						}
					});
				}
			});
        	dialog.show();
        }

    	@JavascriptInterface
        public void selectDialog(String iid,String vid,String type){
    		final String inputid = iid;
    		final String valueid = vid;
    		List<YRL_BASIC_DATA> list = DbManager.codeAyncDatas(type);
    		GroupTagDialog dialog = new GroupTagDialog(getActivity(), list);
    		dialog.setOnSortListItemClickListener(new OnSortListItemClickListener() {
    			@Override
    			public void onItemClicked(int index, Object data) {
    				if(data instanceof YRL_BASIC_DATA){
	    				final YRL_BASIC_DATA ybd = (YRL_BASIC_DATA) data;
	    				webView.post(new Runnable() {
	    					@Override
	    					public void run() {
	    						webView.loadUrl("javascript:sendSelect('"+inputid+"','"+valueid+"','"+ybd.getNO()+"','"+ybd.getNAME()+"')");
	    					}
	    				});
    				}
    			}
    		});
    		dialog.show();
    	}
    	
    	@JavascriptInterface
    	public void getParams(String id,String type){
    		final String iid = id;
    		List<YRL_BASIC_DATA> list = DbManager.codeAyncDatas(type);
    		final String json = XCJsonHelper.toJSONString(list);
    		webView.post(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl("javascript:sendParams('"+iid+"','"+json+"')");
				}
			});
    	}
    }
}
