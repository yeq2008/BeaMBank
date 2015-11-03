package com.bea.mbank.edit.step2;

import xc.android.utils.XCToolkit;

import com.artifex.mupdf.MuPDFCore;
import com.artifex.mupdf.MuPDFPageAdapter;
import com.artifex.mupdf.ReaderView;
import com.bea.mbank.R;
import com.cyg.viewinject.VInject;
import com.cyg.viewinject.annotation.ViewInject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class PdfShowDialog extends Dialog {
	@ViewInject(R.id.pp_fl)
	FrameLayout frameLayout;
	Context context;
	String filePath;
	MuPDFCore pdfCore;

	public PdfShowDialog(Context context, String filePath) {
		super(context);
		this.context = context;
		this.filePath = filePath;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.pop_pdf, null);

		setContentView(contentView);

		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		// lp.height = 640;
		// lp.width = 960;
		lp.height = (int) (XCToolkit.getScreenHeight() * 0.825f);
		lp.width = (int) (XCToolkit.getScreenWidth() * 0.75f);
		dialogWindow.setAttributes(lp);

		VInject.inject(this, contentView);
		try {
			pdfCore = new MuPDFCore(filePath);
			if (null == pdfCore)
				return;
			createUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createUI() {
		ReaderView mDocView = new ReaderView(context);
		mDocView.setAdapter(new MuPDFPageAdapter(context, pdfCore));
		LayoutParams params = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		frameLayout.addView(mDocView, params);
	}
}
