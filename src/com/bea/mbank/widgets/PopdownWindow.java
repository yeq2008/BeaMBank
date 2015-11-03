/**
 * 
 */
package com.bea.mbank.widgets;

import java.util.ArrayList;

import com.bea.mbank.R;

import xc.android.fragment.XCBaseFragment;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

/**
 * @author cuiyuguo
 *
 */
public final class PopdownWindow {
	private XCBaseFragment mContext;
	private View parent ;
	private PopupWindow mPopupWindow;
	private FrameLayout mContentView;
	private ArrayList<View> contentViews = null;
	public PopdownWindow(XCBaseFragment context, View anchor) {
		this.mContext = context;
		this.parent = anchor;
		contentViews = new ArrayList<View>();
		
		LayoutParams vl = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		vl.setMargins(10, 10, 10, 10);
		mContentView = new FrameLayout(mContext.getActivity());
		mContentView.setLayoutParams(vl);

		mPopupWindow = new PopupWindow(mContentView,android.view.ViewGroup.LayoutParams.MATCH_PARENT, 
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, true);
		mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style_down);  
		mPopupWindow.setTouchable(true);
		mPopupWindow.setOutsideTouchable(true);
//		mPopupWindow.setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), (Bitmap) null));
		Drawable drawable = mContext.getResources().getDrawable(R.drawable.frame_back);
		mPopupWindow.setBackgroundDrawable(drawable);
		mPopupWindow.update();  
		mPopupWindow.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getY()<0) {//点击在导航栏上
					//弹出窗口消失
					mPopupWindow.dismiss();
					if (null != parent) {
						MotionEvent event1 = MotionEvent.obtain(event);
						event1.setLocation(Math.abs(event.getX()), Math.abs(event.getY()));
						//先分发按下事件
						event1.setAction(MotionEvent.ACTION_DOWN);
						parent.dispatchTouchEvent(event1);
						//再先分发抬起事件
						event1.setAction(MotionEvent.ACTION_UP);
						parent.dispatchTouchEvent(event1);
					}
				}
				return false;
			}
		});
		mPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				mContext.toggleMask();
			}
		});
	}
	
	public View showWindow(int layout, int width, int height){
		View popupView = mContext.getActivity().getLayoutInflater().inflate(layout, null);
		LayoutParams vl = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		popupView.setLayoutParams(vl);
		popupView.setPadding(0, 0, 0, 0);
		
		mContentView.addView(popupView);
		mContext.toggleMask();
		mPopupWindow.setWidth(width);
		mPopupWindow.setHeight(height);
		mPopupWindow.showAsDropDown(parent, 0, 0);
		
		return popupView;
	}

	public View showWindow(View v, int width, int height){
		View popupView = v;
		v.setBackgroundColor(0);
		LayoutParams vl = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 
				    android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		popupView.setLayoutParams(vl);
		popupView.setPadding(0, 0, 0, 0);
		
		mContentView.addView(popupView);
		mContext.toggleMask();
		mPopupWindow.setWidth(width);
		mPopupWindow.setHeight(height);
		mPopupWindow.showAsDropDown(parent, 0, 0);
		
		return popupView;
	}
	
	
	public void hideWindow(){
		if(null != mPopupWindow) {
			mPopupWindow.dismiss();
		}
	}
}
