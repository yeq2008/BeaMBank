/**
 * 
 */
package com.bea.mbank.edit.step2;

import com.bea.application.BeaAppSettings;
import com.bea.mbank.edit.HomeBaseFragment;
import com.bea.remote.models.grwdy.GrwdyHomeBO;

import android.view.View;

/**
 * @author cuiyuguo
 * 编辑模块内容区域管理类基类，处理一些统一性操作
 */
public class Step2HomeFragment extends HomeBaseFragment {
	public Step2HomeFragment() {
		super();
	}
	
	public Step2HomeFragment(GrwdyHomeBO homeBO) {
		super();
		try {
			this.homeBO = homeBO;
			BeaAppSettings.instance().mGrwdyHomeBO = homeBO;
			BeaAppSettings.setOriginData(homeBO);
			homeBO.originHomeBO = homeBO.clone(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onInitContentView(View arg0) {
		super.onInitContentView(arg0);
		
		replaceContentFragment(new Step2ContentFragment());
		replacePanelFragment(new Step2PanelFragment());
	}
}
