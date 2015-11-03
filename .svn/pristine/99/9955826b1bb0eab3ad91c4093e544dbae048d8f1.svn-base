/**
 * 
 */
package com.bea.mbank.product;

import android.view.View;

import com.bea.database.DbManager;

/**
 * @author cuiyuguo
 *	产品精选页面，与ProductHomeFragment有一点点区别，为了免于在ProductHomeFragment中
 *  做判断，最好将其提到一个新类中处理特殊的部分
 */
public final class ProductCollectFragment extends ProductHomeFragment {
	@Override
	protected void onInitContentView(View arg0) {
		titleView.setText("精选");
	}
	
	@Override
	public void onResume() {
		products = DbManager.productCollects(1);
		super.onResume();
		
		setViewPagerData();
	}
}
