package com.bea.mbank.settings;

import xc.android.fragment.XCBaseFragment; 
import com.bea.mbank.R; 
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup; 
import android.widget.RelativeLayout; 

public class SetMenuFragment extends XCBaseFragment {

	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		
		return arg0.inflate(R.layout.fm_appset_content_menu, null);
	} 
	private RelativeLayout rLayout;
	XCBaseFragment xcBaseFragment; 
	@Override
	protected void onInitContentView(View arg0) {
		
		xcBaseFragment = new SettingFragment();
		rLayout = (RelativeLayout) arg0
				.findViewById(R.id.appset_Relativelayout1);
		rLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundColor(Color.LTGRAY);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.setBackgroundColor(Color.WHITE);
					pushFragmentController(xcBaseFragment);
				}
				return true;
			}
		});
	}

}
