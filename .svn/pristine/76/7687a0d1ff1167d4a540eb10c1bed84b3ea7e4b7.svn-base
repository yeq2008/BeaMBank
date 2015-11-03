package com.bea.mbank.home;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.bea.application.APKUpdateManager;
import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.database.codemodel.MMP_PRODUCT_CATEGORY;
import com.bea.mbank.R;
import com.bea.mbank.edit.step1.Step1HomeFragment;
import com.bea.mbank.edit.step2.Step2HomeFragment;
import com.bea.mbank.helper.counter.LoanCounterFragment;
import com.bea.mbank.home.MainMenuActivity.MenueMessage;
import com.bea.mbank.mytask.CollecteListFragment;
import com.bea.mbank.mytask.CommitListFragment;
import com.bea.mbank.mytask.DraftListFragment;
import com.bea.mbank.paxb.PaxbHomeFragment;
import com.bea.mbank.product.ProductCollectFragment;
import com.bea.mbank.product.ProductHomeFragment;
import com.bea.mbank.settings.SetSuccess;
import com.bea.mbank.settings.SettingFragment;
import com.bea.remote.BeaRemoteErrorManager;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;

import xc.android.activity.XCBaseGroupActivity;
import xc.android.application.XCApplication;
import xc.android.remote.IRemoteResponse;
import xc.android.remote.json.JsonReqClient;
import xc.android.utils.XCToolkit;

import com.cyg.viewinject.annotation.ViewInject;

public class MainActivity extends XCBaseGroupActivity implements IRemoteResponse{
	APKUpdateManager appVersionManager;
	@Override
	protected View onCreateView(LayoutInflater arg0) {
		return inflater.inflate(R.layout.ac_apphome, null);
	}

	@ViewInject(R.id.content) FrameLayout content;
	@ViewInject(R.id.menu) FrameLayout menuLayout;
	@Override
	protected void onInitContentView() {
		// 监听主菜单消息事件
		observeMessage(ConstDefined.MenuClickReqKey);
		observeMessage(ConstDefined.APPHomeChangeActionKey);
		observeMessage(ConstDefined.APPNewVersionActionKey);

		menuLayout.setLayoutParams(new LayoutParams((int)(XCToolkit.getScreenWidth()*.1875f), 
				                       LayoutParams.MATCH_PARENT));
		// 初始化应用主菜单
		Intent intent = new Intent(this, MainMenuActivity.class);
		exchange2SubActivity(intent, menuLayout);
		
		//检查新版本
		appVersionManager = new APKUpdateManager(this, true);
		//注册新版本通知广播  
		IntentFilter intentFilter = new IntentFilter();  
		intentFilter.addAction(ConstDefined.APPHomeChangeActionKey);  
		intentFilter.addAction(ConstDefined.DeviceTimeSetActionKey);  
        LocalBroadcastManager.getInstance(XCApplication.getInstance()).
        		registerReceiver(mBroadcastReceiver, intentFilter); 
	}

	/**
	 * @brief 消息监听函数，当有消息来将调用此函数
	 * @param msgkey
	 *            消息关键字
	 * @param msg
	 *            消息信息，发送过来的消息实际内容
	 */
	int mCurrentIndex = -1;

	@Override
	public void onReceiveMessage(final String mkey, final Object msgObject) {	
		// 检查是否有编辑页面的数据还未处理
		if (BeaAppSettings.isDataChanged()) {
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setCancelable(false);
			builder.setMessage("您要放弃已编辑的数据进行切换吗？");
			builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					exchange2SubModuel(mkey, msgObject);
				}
			});
			builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					menuClickEventIgnored(mkey, msgObject);
				}
			});
			builder.show();
		} else {
			exchange2SubModuel(mkey, msgObject);
		}
	}

	private void exchange2SubModuel(String mkey, Object msgObject) {
		BeaAppSettings.setOriginData(null);
		//从菜单项点击事件过来的请求
		if (ConstDefined.APPNewVersionActionKey.equals(mkey)) {
			//告诉菜单页面更新菜单状态
//			MenueMessage menuMsg = MainMenuActivity.menueMessage(-1, -1);
//			sendMessage(ConstDefined.MenuClickResKey, menuMsg);
			appVersionManager.checkUpdateInfo();
			return;
		}
		//从菜单项点击事件过来的请求
		else if (ConstDefined.MenuClickReqKey.equals(mkey)) {
			MenueMessage msg = (MenueMessage)msgObject;
			// 响应我的任务菜单项－－草稿事件
			if (msg.actionUrl.equals(ConstDefined.mytaskDraftUrl)) {
				exchange2SubActivity(DraftListFragment.class, null);
			}
			// 响应我的任务菜单项－－待发事件
			else if (msg.actionUrl.equals(ConstDefined.mytaskOutgoUrl)) {
				exchange2SubActivity(CommitListFragment.class, null);
			}
			// 响应我的任务菜单项－－催收事件
			else if (msg.actionUrl.equals(ConstDefined.mytaskCollectUrl)) {
				exchange2SubActivity(CollecteListFragment.class, null);
			}
			// 响应产品中心
			else if (msg.actionUrl.equals(ConstDefined.productUrl+0)) {//精选产品
				exchange2SubActivity(ProductCollectFragment.class, null);
			}
			// 响应产品中心
			else if (msg.actionUrl.startsWith(ConstDefined.productUrl)) {
				MMP_PRODUCT_CATEGORY category = (MMP_PRODUCT_CATEGORY)msg.msgObject;
				Bundle bundle = new Bundle();
				bundle.putSerializable("category", category);
				exchange2SubActivity(ProductHomeFragment.class, bundle);
			}
			// 响应个人无抵押贷款菜单项事件
			else if (msg.actionUrl.equals(ConstDefined.loanGRWDYUrl)) {
				Bundle bundle = new Bundle();
				exchange2SubActivity(Step1HomeFragment.class, bundle);
			}
			// 响应平安信保菜单项事件
			else if (msg.actionUrl.equals(ConstDefined.loanPAXBUrl)) {
				exchange2SubActivity(PaxbHomeFragment.class, null);
			}
			// 响应辅助功能菜单项－－贷款计算器
			else if (msg.actionUrl.equals(ConstDefined.utilsCalculatorUrl)) {
				exchange2SubActivity(LoanCounterFragment.class, null);
			}
			//告诉菜单页面更新菜单状态
			sendMessage(ConstDefined.MenuClickResKey, msg);
		} else if (ConstDefined.APPHomeChangeActionKey.equals(mkey) && msgObject instanceof Message) {
			Message msg = (Message)msgObject;
			// 页面内跳转到编辑页面
			if (msg.what == ConstDefined.GRWDYCreditActionTag) {
				GrwdyHomeBO detail = (GrwdyHomeBO)msg.obj;
				Bundle bundle = new Bundle();
				boolean isPAXB = false;//
				if (null != detail) {
					isPAXB = detail.getIsPinAnXinBaoData();//
					bundle.putSerializable(ConstDefined.bundleSerializableParamKey, detail);
				}
				if (null != detail && !detail.getIsPartRequiredData() && detail.getIsSMSValid()) {
					exchange2SubActivity(Step2HomeFragment.class, bundle);
				} else {
					exchange2SubActivity(Step1HomeFragment.class, bundle);
				}
				
				//告诉菜单页面更新菜单状态
				MenueMessage menuMsg = MainMenuActivity.menueMessage(
				isPAXB?ConstDefined.loanPAXBUrl:ConstDefined.loanGRWDYUrl);//
				sendMessage(ConstDefined.MenuClickResKey, menuMsg);
			}
			// 系统设置
			else if (msg.what == ConstDefined.SettingTag) {
				exchange2SubActivity(SettingFragment.class, null);
			}else if (msg.what == ConstDefined.SetSuccessTag) {
				exchange2SubActivity(SetSuccess.class, null);
			}
		} 
		// 切换到空白页面
		else if (ConstDefined.APPHomeChangeActionKey.equals(mkey) &&
				 ((Integer) msgObject) == ConstDefined.BlankActivityTag) {
			//告诉菜单页面更新菜单状态
			MenueMessage menuMsg = MainMenuActivity.menueMessage(null);
			sendMessage(ConstDefined.MenuClickResKey, menuMsg);
			exchange2SubActivity(null, content);
		}
	}

	private void menuClickEventIgnored(String mkey, Object msgObject) {
		//从菜单项点击事件过来的请求被忽略
		if (ConstDefined.MenuClickReqKey.equals(mkey)) {
			MenueMessage msg = (MenueMessage)msgObject;
			msg.actionUrl = msg.actionOldUrl;
			//告诉菜单页面更新菜单状态
			sendMessage(ConstDefined.MenuClickResKey, msg);
		}
	}
	private void exchange2SubActivity(Class<?> homeFragment, Bundle bundle) {
		Intent intent = new Intent(this, BeaBaseActivity.class);
		if (null != homeFragment) {
			intent.putExtra(ConstDefined.homeFragmentKey, homeFragment.getName());
		}
		if (null != bundle) {
			intent.putExtras(bundle);
		}
		exchange2SubActivity(intent, content);
	}

	/*---------------------------------------------------------------------------------*/
	/* 当屏幕上有键盘显示的时候，点击空白处键盘消失的处理 */
	/*---------------------------------------------------------------------------------*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit2Home();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exit2Home() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(false);
		builder.setMessage("您要退出应用程序吗？");
		builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				exitApplication();
			}
		});
		builder.setNegativeButton("否", null);
		builder.show();
	}
	
	private Handler msgHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			onBroadcastMessage(msg.what, (String)msg.obj);
			super.handleMessage(msg);
		}
	};
	
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){  
        @Override  
        public void onReceive(Context context, Intent intent) {  
        	String action = intent.getAction();  
        	if(action.equals(ConstDefined.APPHomeChangeActionKey)){  
        		Message msg = new Message();
        		msg.what = intent.getIntExtra("errorCode", -1);
        		msg.obj = intent.getStringExtra("errorMsg");
        		msgHandler.sendMessage(msg);
            } else if(action.equals(ConstDefined.DeviceTimeSetActionKey)){  
        		Message msg = new Message();
        		msg.what = intent.getIntExtra("errorCode", -123456);
        		msgHandler.sendMessage(msg);
            }
        }   
    }; 
    private JsonReqClient jsonReqClient = new JsonReqClient(this);
    private void onBroadcastMessage(int errorCode, String errorMsg) {
		if (BeaRemoteErrorManager.RemoteError_UserReLogin == errorCode) {
			final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setCancelable(false);
			builder.setMessage(errorMsg);
			builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					startActivity(new Intent(MainActivity.this, LoginActivity.class));
					MainActivity.this.finish();
				}
			});
			builder.show();
		} else {//设备的时间被设置，此时需要从服务器重新取当前时间
			jsonReqClient.jsonRequest(errorCode, "通用接口", 
			JsonRemoteBO.reqParam(ConstDefined.systemTime, null), 0);
		}
	}

	@Override
	public void onNetConnectFailed(int arg0, String arg1) {}
	@Override
	public void onResponsFailed(int arg0, String arg1) {}
	@Override
	public void onResponsSuccess(int arg0, Object arg1) {
		JSONObject jObject = (JSONObject)arg1;
		String systime = jObject.optString("system_time");
		BeaAppSettings.setSystemTimeDiffStep(systime);
	}
}
