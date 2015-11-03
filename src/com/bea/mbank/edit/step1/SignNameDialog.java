package com.bea.mbank.edit.step1;

import xc.android.utils.XCToolkit;

import com.android.signature.SignatureView;
import com.bea.mbank.R;
import com.cyg.viewinject.VInject;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Ç©Ãû¶Ô»°¿ò
 * 
 * @author fanglinhao
 */
public class SignNameDialog extends Dialog {
	private Context context;
	private SignNameDialogListener listener;
	final int height = (int) (XCToolkit.getScreenWidth() * 0.4925f);
	final int width = (int) (XCToolkit.getScreenWidth() * 0.5656f);

	@ViewInject(R.id.sigatureView)
	SignatureView sigview;

	public interface SignNameDialogListener {
		public void onClick(Bitmap bitmap);
	}

	public SignNameDialog(Context context, SignNameDialogListener listener) {
		super(context);
		this.context = context;
		this.listener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View contentView = LayoutInflater.from(context).inflate(
				R.layout.pop_signname, null);

		setContentView(contentView);
		VInject.inject(this, contentView);
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.height = height;
		lp.width = width;
		dialogWindow.setAttributes(lp);
	}

	@OnClick(R.id.sigh_commit)
	public void commit(View view) {
		listener.onClick(sigview.getSignatureBitmap());
		dismiss();
	}

	@OnClick(R.id.sigh_reset)
	public void reset(View view) {
		sigview.clear();
	}

	@OnClick(R.id.sigh_closeing)
	public void close(View view) {
		sigview.clear();
		dismiss();
	}
}
