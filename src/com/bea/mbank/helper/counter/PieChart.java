package com.bea.mbank.helper.counter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class PieChart {

	//贷款总额
	private float loanAmount;
	//支付利息
	private float payInterest;
	
	public PieChart(float loanAmount, float payInterest) {
		super();
		this.loanAmount = loanAmount;
		this.payInterest = payInterest;
	}
	
	public void draw(Canvas canvas, float x,float y,float r, int angle) {
		//super.onDraw(canvas);
		
		if(loanAmount!=0){
			
			RectF rectF=new RectF(0, 0, x*2, y*2);
		
			Paint paint1=new Paint();
			paint1.setAntiAlias(true);
			paint1.setColor(0xFFFFA169);
			//画第一个扇形
			canvas.drawArc(rectF, 180, angle, true, paint1);
			
			Paint paint2=new Paint();
			paint2.setAntiAlias(true);
			paint2.setColor(0xFF50A3FF);
			//画第二个扇形
			canvas.drawArc(rectF, 180+angle, 360-angle, true, paint2);
			
			
		}
	}


	public float getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(float loanAmount) {
		this.loanAmount = loanAmount;
	}

	public float getPayInterest() {
		return payInterest;
	}

	public void setPayInterest(float payInterest) {
		this.payInterest = payInterest;
	}

	
}

