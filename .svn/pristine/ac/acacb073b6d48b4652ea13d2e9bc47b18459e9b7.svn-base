package com.bea.mbank.edit.step2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import xc.android.application.XCApplication;
import xc.android.file.XCFile;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;

import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.image.ImageLoader;
import com.bea.image.PZSImageView;
import com.bea.mbank.R;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.mbank.edit.step1.SignNameDialog;
import com.bea.mbank.edit.step1.SignNameDialog.SignNameDialogListener;
import com.bea.mbank.util.FileUtils;
import com.bea.mbank.util.HtmlUtil;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.grwdy.ApplyLoanInforBO;
import com.bea.remote.models.grwdy.BasicInfoBO;
import com.bea.remote.models.grwdy.BorrowerInforBO;
import com.bea.remote.models.grwdy.CheckCodeBO;
import com.bea.remote.models.grwdy.ContactInforBO;
import com.bea.remote.models.grwdy.CreditInforBO;
import com.bea.remote.models.grwdy.JointInforBO;
import com.bea.remote.models.grwdy.MateInforBO;
import com.bea.remote.models.grwdy.PhotosItem;
import com.bea.remote.models.grwdy.ProfessionInforBO;
import com.bea.remote.models.grwdy.TakePhotosBO;
import com.bea.remote.models.user.UserInfoBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

/**
 * @author fanglinhao
 *	个人无抵押预览
 */
public final class Step2ContentFragment extends ContentBaseFragment<TakePhotosBO> {
	@Override
	protected Class getInfoClass() {
		return TakePhotosBO.class;
	}
	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_grwdy_step2_content, null);
	}

	@ViewInject(R.id.webview)
	WebView webView;
	@ViewInject(R.id.webviewhint)
	WebView webviewhint;
	@ViewInject(R.id.fgdpc_iv_signname)
	ImageView imageView;
	@ViewInject(R.id.fgdpc_iv_signname_mate)
	ImageView imageViewMate;
	@ViewInject(R.id.fgdpc_iv_signname_join)
	ImageView imageViewJoin;
	@ViewInject(R.id.gridview)
	GridView gridView;
	@ViewInject(R.id.rl_msg_sign)
	RelativeLayout rl_msg_sign;//包含了短信验证和签名的layout
	@ViewInject(R.id.fgcdp_rl_sms)
	RelativeLayout smsLayout;
	@ViewInject(R.id.fgcdp_btn_send)
	Button codeButton;//验证码
	@ViewInject(R.id.fgcdp_readshure)
	TextView readshure;//我已阅读
	@ViewInject(R.id.fgcdp_cb)
	CheckBox checkBox;
	
	EditText checkCodeEt;
	List<String> imageList;
	String checkCode;
	String checkPhone;
	@Override
	protected void onInitContentView(View rootView, TakePhotosBO info) {
    	if(homeBO.isPartRequiredData){//批量不需要短信验证和签名
    		rl_msg_sign.setVisibility(View.GONE);
    		rootView.findViewById(R.id.fgcdp_v_line).setVisibility(View.GONE);
    	}
    	if(!homeBO.isPinAnXinBaoData){
    		readshure.setText(Html.fromHtml("<u>"+"我已阅读 “东亚银行新时贷 ”个人无抵押消费贷款章程及条款"+"</u>"));
    	} else{
    		readshure.setText(Html.fromHtml("<u>"+"我已阅读 “东亚银行平安信保 ”个人无抵押消费贷款章程及条款"+"</u>"));
		}
    	downloadPhotos(new AfterDownload() {
			@Override
			public void AfterDownloadAction() {
				checkAndInit();
			}
		});
		
		checkCodeEt = (EditText) rootView.findViewById(R.id.fgcdp_et_checkcode);
		checkCodeEt.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
			@Override
			public void afterTextChanged(Editable s) {
				if(null == checkCode){
					XCToolkit.showToast("请先获取验证码！");
					return;
				}
				//短信验证挡板
//				if(checkCode.equals(s.toString())){
				if(s.toString().length() == 6){
					checkflag = false;
					i = 0;
					codeButton.setEnabled(false);
					codeButton.setText("验证通过");
			    	homeBO.setIsSMSValid(true);
			    	sendMessage(ConstDefined.smsedHiddenBack, null);
			    	return;
				}
				
//				if(s.toString().length() == 6 && !checkCode.equals(s.toString())){
//					XCToolkit.showToast("验证不通过，请重新获取！");
//				}
			}
		});
		if(null != homeBO.getIsReaded() && "2".equals(homeBO.getIsReaded()))
			checkBox.setChecked(true);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String tmp = "1";
				if(isChecked)
					tmp = "2";
				homeBO.setIsReaded(tmp);
			}
		});
	}
	
	private void checkAndInit() {
		initGridView();
		if(!BeaAppSettings.instance().mGrwdyHomeBO.isPartRequiredData)
			initSignPhoto();
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new JsHelper(getActivity()),"jsHelper");
		webView.setWebViewClient(new WebViewClient());
		webView.setWebChromeClient(new WebChromeClient());
		webView.loadUrl("file:///android_asset/html/grwdy.html");
		if(!homeBO.isPartRequiredData){
			webviewhint.loadUrl("file:///android_asset/html/hint.html");
		}else{
			webviewhint.setVisibility(View.GONE);
		}
	}
	private void initGridView(){
		List<PhotosItem> photos = null;
		if(null != (photos=infoBO.photos()) && photos.size()>0){
			imageList = new ArrayList<String>();
			for(PhotosItem p : photos){
				imageList.add(p.getPhoto());
			}
			gridView.setAdapter(new ImageViewAdapter());
			gridView.setNumColumns(4);
			gridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					new PhotoDialog(getActivity(), imageList.get(position)).show();
				}
			});
		}
	}
	
	public void initSignPhoto(){
		if (null != infoBO.signedPhoto(PhotosItem.SignedPhoto)) {
			if(!homeBO.getIsSMSValid())
				smsLayout.setVisibility(View.VISIBLE);
			ImageLoader.load(FileUtils.photoTempPath(infoBO.signedPhoto(PhotosItem.SignedPhoto)), imageView, 362, 162);
		}
		if(homeBO.isMateInforRequired){
			getActivity().findViewById(R.id.fgcdp_fl_signname_mate).setVisibility(View.VISIBLE);
			if (null != infoBO.signedPhoto(PhotosItem.SignedPhotoMate)) {
				ImageLoader.load(FileUtils.photoTempPath(infoBO.signedPhoto(PhotosItem.SignedPhotoMate)), imageViewMate, 362, 162);
			}
		}
		if(homeBO.isJointInforRequired){
			getActivity().findViewById(R.id.fgcdp_fl_signname_join).setVisibility(View.VISIBLE);
			if (null != infoBO.signedPhoto(PhotosItem.SignedPhotoJoin)) {
				ImageLoader.load(FileUtils.photoTempPath(infoBO.signedPhoto(PhotosItem.SignedPhotoJoin)), imageViewJoin, 362, 162);
			}
		}
	}
	
	@OnClick(R.id.fgcdp_readshure)
	public void readshure(View view){
		if (homeBO.isPinAnXinBaoData) {
			String filename =  XCApplication.workPath() + File.separator + "paxb.pdf";
			PdfShowDialog dialog = new PdfShowDialog(getActivity(), filename);
			dialog.show();
		}else if (!homeBO.isPinAnXinBaoData) {
			String filename =  XCApplication.workPath() + File.separator + "xsd.pdf";
			PdfShowDialog dialog = new PdfShowDialog(getActivity(), filename);
			dialog.show();
		}
		
	}
	
	String signedPhoto = null;
	@OnClick(R.id.fgcdp_fl_signname)
	public void signName(View view){
		if(null != homeBO.getIsReaded() && "2".equals(homeBO.getIsReaded())){
			openSignName(1);//借款人
		}else{
			XCToolkit.showToast("请先阅读章程");
		}
	}
	
	@OnClick(R.id.fgcdp_fl_signname_mate)
	public void signNameMate(View view){
		if(null != homeBO.getIsReaded() && "2".equals(homeBO.getIsReaded())){
			openSignName(2);//借款人配偶
		}else{
			XCToolkit.showToast("请先阅读章程");
		}
	}
	
	@OnClick(R.id.fgcdp_fl_signname_join)
	public void signNameJoin(View view){
		if(null != homeBO.getIsReaded() && "2".equals(homeBO.getIsReaded())){
			openSignName(3);//共同借款人
		}else{
			XCToolkit.showToast("请先阅读章程");
		}
	}
	
	private void openSignName(final int flag){
		SignNameDialog dialog = new SignNameDialog(getActivity(), new SignNameDialogListener() {
			@Override
			public void onClick(Bitmap bitmap) {
				String fileName = XCToolkit.stringFromDate(new Date(), "yyyyMMddHHmmss")+".jpg";
				File file = XCFile.create(FileUtils.savePhotoFile(fileName, bitmap));
				if (file.exists()) {
					signedPhoto = fileName;
				} else {
					XCLog.e("--签名--", "签名文件保存失败");
				}
				if(1 == flag){
					infoBO.addPhoto(PhotosItem.SignedPhoto, signedPhoto);
					smsLayout.setVisibility(View.VISIBLE);
					ImageLoader.load(FileUtils.photoTempPath(fileName), imageView, 362, 162);
				} else if(2 == flag){
					infoBO.addPhoto(PhotosItem.SignedPhotoMate, signedPhoto);
					ImageLoader.load(FileUtils.photoTempPath(fileName), imageViewMate, 362, 162);
				} else{
					infoBO.addPhoto(PhotosItem.SignedPhotoJoin, signedPhoto);
					ImageLoader.load(FileUtils.photoTempPath(fileName), imageViewJoin, 362, 162);
				}
			}
		});
		dialog.show();
	}
	
	Timer timer;
	int i;
	//短信验证码
	@OnClick(R.id.fgcdp_btn_send)
	public void checkCode(View view){
		UserInfoBO userInfo = BeaAppSettings.getUserInfo();
		if(null == userInfo || null == userInfo.getOrg_id() || "".equals(userInfo.getOrg_id())){
			XCToolkit.showToast("用户未登陆");
			return;
		}
		if(null == homeBO.getBorrowerInfor() || null == homeBO.getBorrowerInfor().getMTNum()
				|| "".equals(homeBO.getBorrowerInfor().getMTNum().trim())){
			XCToolkit.showToast("手机号码未填写");
			return;
		}
		
		Map<String, String> object = new HashMap<String, String>();
		object.put("trantype", "11");
		object.put("orgid", userInfo.getOrg_id());
		object.put("mobileno", homeBO.getBorrowerInfor().getMTNum());
		try {
			jsonRequest(102, "通用接口", JsonRemoteBO.reqParam(ConstDefined.ShortMsgVerify, object));
		} catch (Exception e) {
			XCLog.e("短信验证错误信息", e.getMessage());
			e.printStackTrace();
		}
		i = 60;
		codeButton.setEnabled(false);
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				checkCodeHandler.sendMessage(new Message());
			}
		}, 1000, 1000);
	}


	@Override
	public void onResponsSuccess(int actionTag, Object resObject) {
		if(102 == actionTag){
			try {
				CheckCodeBO codeBO = XCJsonHelper.parseObject(resObject.toString(), CheckCodeBO.class);
				if(null != codeBO)
					checkCode = codeBO.getChkcode();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	boolean checkflag = true;
	private Handler checkCodeHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(checkflag){
				if(i > 0){
					codeButton.setText(i+"秒后重新发送");
				}else{
					timer.cancel();
					codeButton.setText("获取验证码");
					codeButton.setEnabled(true);
				}
			}
			i--;
		}
	};
	
	class ImageViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return imageList.size();
		}

		@Override
		public Object getItem(int position) {
			return imageList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView iv;
			if(null == convertView) {
				iv=new ImageView(getActivity());
				iv.setLayoutParams(new GridView.LayoutParams(100, 100));
				iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
				iv.setPadding(10,10,10,10);
			} else {
				iv = (ImageView)convertView;
			}
			String filename = imageList.get(position);
			if(null != filename)
				ImageLoader.load(FileUtils.photoTempPath(filename), iv, 100, 100);
			return iv;
		}
		
	}
	
	/**
	 * 点击图片放大查看
	 */
	class PhotoDialog extends Dialog{
		String imageFileName = null;
		public PhotoDialog(Context context, String imageFileName) {
			super(context, R.style.AppTheme_Dialog);
			this.imageFileName = imageFileName;
		}
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			LayoutParams params = new LayoutParams(700, 500);
			PZSImageView imageView = new PZSImageView(getActivity());
			imageView.setScaleType(ScaleType.FIT_CENTER);
			setContentView(imageView, params);
			ImageLoader.load(FileUtils.photoTempPath(imageFileName), imageView, -1, -1);
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
        	
        	BorrowerInforBO borrowerInforBO = homeBO.isBorrowerInforRequired?homeBO.getBorrowerInfor():null;
    		MateInforBO spouseInforBO = homeBO.isMateInforRequired?homeBO.getMateInfor():null;
    		ProfessionInforBO professionInforBO = homeBO.isProfessionInforRequired?homeBO.getProfessionInfor():null;
    		ContactInforBO contactInforBO = homeBO.isContactInforRequired?homeBO.getContactInfor():null;
    		JointInforBO jointInforBO = homeBO.isJointInforRequired?homeBO.getJointInfor():null;
    		CreditInforBO creditInforBO = homeBO.isCreditInforRequired?homeBO.getCreditInfor():null;
    		ApplyLoanInforBO applyLoanInforBO = homeBO.isApplyLoanInforRequired?homeBO.getApplyLoanInfor():null;
    		BasicInfoBO basicInfoBO = homeBO.isBasicInfoRequired?homeBO.getBasicInfo():null;
    		HtmlUtil htmlUtil = new HtmlUtil();
    		
    		String tmp = "1";
    		if(homeBO.isPinAnXinBaoData){
    			if(null != applyLoanInforBO && 
    			   null != applyLoanInforBO.getIPM() &&
    			   "1".equals(applyLoanInforBO.getIPM()))
    				tmp = "2";
    		}
    		if(homeBO.isPartRequiredData)//批量
    			tmp = "3";
    		final String flag = tmp;
    		final String borrowerInforBOContent = htmlUtil.borrowerInforBOHtml(borrowerInforBO);//借款人个人资料
    		final String spouseInforBOContent = htmlUtil.mateInforBOHtml(spouseInforBO);//借款人配偶资料
    		final String professionInforBOContent = htmlUtil.professionInforBOHtml(professionInforBO);//借款人职业资料
    		final String contactInforBOContent = htmlUtil.contactInforBOHtml(contactInforBO);//联系人信息
    		final String jointInforBOContent = htmlUtil.jointInforBOHtml(jointInforBO);//共同借款人信息
    		final String creditInforBOContent = XCJsonHelper.toJSONString(creditInforBO);//借款人资产
    		final String applyLoanInforBOContent = htmlUtil.applyLoanInforBOHtml(homeBO.isPinAnXinBaoData,applyLoanInforBO);//申请贷款及费用信息
    		final String basicInfoBOContent = htmlUtil.basicInfo(basicInfoBO);//个人基本信息
        	
        	webView.post(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl("javascript:loadData('"
							+borrowerInforBOContent+"','"
							+professionInforBOContent+"','"
							+creditInforBOContent+"','"
							+jointInforBOContent+"','"
							+contactInforBOContent+"','"
							+spouseInforBOContent+"','"
							+applyLoanInforBOContent+"','"
							+basicInfoBOContent	+ "','"+flag+"')");
					
					//webView.loadUrl("javascript:loadData('"+json+"')");
				}
			});
        }
    }

	@Override
	public TakePhotosBO getValueFromInterfaceView() {
		return infoBO;
	}

	@Override
	public boolean isRequiredFieldInputed(TakePhotosBO info) {
		return true;
	}
}
