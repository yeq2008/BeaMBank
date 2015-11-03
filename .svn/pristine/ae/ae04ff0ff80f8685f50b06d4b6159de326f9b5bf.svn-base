package com.bea.mbank.helper.counter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 饼状图右边的点，表示支付利息在饼状图中的颜色
 * @author Yishuai
 *
 */
public class InterestsPayDot extends View{

	public InterestsPayDot(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InterestsPayDot(Context context) {
		this(context,null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		float x=this.getWidth()/2;
		float y=this.getHeight()/2;
		float r=x>y?y:x;
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(0xFF50A3FF);
		canvas.drawCircle(x, y, r, paint);
	}

}