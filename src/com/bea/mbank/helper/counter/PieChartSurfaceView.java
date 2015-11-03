package com.bea.mbank.helper.counter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class PieChartSurfaceView extends SurfaceView implements Runnable,
		Callback {

	private SurfaceHolder mHolder;
	private Canvas mCanvas;
	// 画图的线程
	private Thread t;
	private boolean isRunning;

	// 贷款总额
	private float loanAmount = 50;
	// 支付利息
	private float payInterest = 50;
	// 角度（从0慢慢增加）
	private int a;

	private float width;
	private float height;
	private float radius;
	private int angle;
	private PieChart pieChart;

	public PieChartSurfaceView(Context context) {
		this(context, null);
	}

	public PieChartSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mHolder = getHolder();
		mHolder.addCallback(this);

		setBackgroundColor(Color.WHITE);
		setFocusable(true);
		setFocusableInTouchMode(true);
		setKeepScreenOn(true);
		
		getHolder().setFormat(PixelFormat.TRANSLUCENT);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		setZOrderOnTop(true);// 设置画布 背景透明
		a = 0;
		pieChart = new PieChart(loanAmount, payInterest);
		angle = (int) (loanAmount / (payInterest + loanAmount) * 360);
		isRunning = true;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		isRunning = false;
	}

	@Override
	public void run() {
		while (isRunning) {
			long start = System.currentTimeMillis();
			draw();
			long end = System.currentTimeMillis();

			try {
				if (end - start < 50) {
					Thread.sleep(50 - (end - start));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void draw() {
		try {
			// 获得canvas
			mCanvas = mHolder.lockCanvas();
			if (mCanvas != null) {
				// drawSomething..
				drawPieChart(mCanvas, width / 2, height / 2, radius);
			}
		} catch (Exception e) {
		} finally {
			if (mCanvas != null)
				mHolder.unlockCanvasAndPost(mCanvas);
		}
	}

	// 画饼状图
	private void drawPieChart(Canvas mCanvas, float width, float height,
			float radius) {
		if (a < angle)
			pieChart.setPayInterest(a);
		else
			isRunning = false;

		pieChart.draw(mCanvas, width, height, radius, a);
		a += 3;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = this.getWidth();
		height = this.getHeight();
		radius = (width > height ? height : width) / 2;
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

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
