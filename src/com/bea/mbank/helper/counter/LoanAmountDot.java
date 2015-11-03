package com.bea.mbank.helper.counter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 饼状图右边的点，表示贷款总额在饼状图中的颜色
 * @author Yishuai
 *
 */
public class LoanAmountDot extends View{

	public LoanAmountDot(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LoanAmountDot(Context context) {
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
		paint.setColor(0xFFFFA169);
		canvas.drawCircle(x, y, r, paint);
	}

}
