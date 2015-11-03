/**
 * 
 */
package com.bea.mbank.edit;

import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import com.cyg.viewinject.annotation.ViewInject;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import xc.android.fragment.XCBaseFragment;
import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;

/**
 * @author cuiyuguo
 * 编辑模块内容区域管理类基类，处理一些统一性操作
 */
public abstract class HomeBaseFragment extends XCBaseFragment {
	protected GrwdyHomeBO homeBO = null;
	@Override
	protected void onInitContentView(View arg0) {
		if (null != getArguments()) {
			homeBO = (GrwdyHomeBO)getArguments().get(ConstDefined.bundleSerializableParamKey);
			if (null == homeBO) {
				homeBO = new GrwdyHomeBO();
			}
			BeaAppSettings.instance().mGrwdyHomeBO = homeBO;
			BeaAppSettings.setOriginData(homeBO);
			homeBO.originHomeBO = homeBO.clone(false);
		} else {
			homeBO = BeaAppSettings.instance().mGrwdyHomeBO;
		}
	}
	@Override
	public void onDestroy() {
		XCLog.i("=====destroy", getClass().getName());
		super.onDestroy();
	}
	
	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_grwdy_step_home, null);
	}
	
	public void replaceContentFragment(Fragment fragment) {
		replaceFragmentController(R.id.pannel_item, fragment);
	}
	
	public void replacePanelFragment(Fragment fragment) {
		replaceFragmentController(R.id.pannelLayout, fragment);
	}
}
