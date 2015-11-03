package com.bea.mbank.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.AbsListView.LayoutParams;


public class SwipeActionAdapter extends DecoratorAdapter {
	public class RootContentView extends FrameLayout {
		View contentView = null;
		Drawable originDrawable = null;
		public RootContentView(Context context, View contentView) {
			super(context);
			this.contentView = contentView;
			originDrawable = contentView.getBackground();
		}
		public View contentView(){return contentView;}
		public View statueView = null;
		public void setStatueView(View statueView) {
			removeStatueView();
			this.statueView = statueView;
			if (null != statueView) {
				addView(statueView, 0, new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
			}
		}
		public void removeStatueView() {
			if (null != this.statueView) {
				removeView(this.statueView);
				this.statueView=null; 
			}
		}
	}
	
	public SwipeActionAdapter(BaseAdapter baseAdapter) {
		super(baseAdapter);
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent){
		View content = null==convertView?null:((RootContentView)convertView).contentView;
		View childContent = super.getView(position,content,null);
        if(convertView == null) {
        	RootContentView p = new RootContentView(parent.getContext(), childContent);
        	p.addView(childContent, new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        	convertView = p;
        }
        return convertView;
    }
}
