package com.bea.mbank.edit.step1;

import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import xc.android.file.XCFile;
import xc.android.file.XCFileOutputStream;
import xc.android.utils.XCBase64;
import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;

import com.bea.database.DbManager;
import com.bea.image.ImageLoader;
import com.bea.mbank.R;
import com.bea.mbank.util.FileUtils;
import com.bea.remote.models.crm.CrmPersonInfo;
import com.cyg.viewinject.VInject;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonInforDialog extends Dialog {
	@ViewInject(R.id.iv_photo)
	ImageView imageView;
	// 姓名
	@ViewInject(R.id.value_name)
	TextView tvname;
	// 发证机关
	@ViewInject(R.id.value_fzjg)
	TextView tvfzjg;
	// 发证国家
	@ViewInject(R.id.value_fzgj)
	TextView tvfzgj;
	// 证件类型
	@ViewInject(R.id.value_zjlx)
	TextView tvzjlx;
	// 身份证
	@ViewInject(R.id.value_creditcard)
	TextView tvcreditcard;

	personInfoDialogListener dialogListener;

	CrmPersonInfo Info;

	public PersonInforDialog(Context context, CrmPersonInfo crmPersonInfo) {
		super(context, R.style.AppTheme_Dialog);
		this.Info = crmPersonInfo;
	}

	public interface personInfoDialogListener {
		public void click(String flag, String card, String country,
				String cardType, String username);
	}

	public void setDialogListener(personInfoDialogListener dialogListener) {
		this.dialogListener = dialogListener;
	}

	private void init() {
		if (null != Info.getName())
			tvname.setText(Info.getName());
		if (null != Info.getCreditCard())
			tvcreditcard.setText(Info.getCreditCard());
		if (null != Info.getIssueOffice())
			tvfzjg.setText(Info.getIssueOffice());
		if (null != Info.getCardType())
			tvzjlx.setText(DbManager.codeDataName("CertType",
					Info.getCardType()));
		if (null != Info.getNation())
			tvfzgj.setText(DbManager.codeDataName("CountryCode",
					Info.getNation()));
	}

	private void initPhone(CrmPersonInfo info) {
		try {
			if (null != info && null != info.getCheckResult()) {
				if ("00".equals(info.getCheckResult())) {
					byte[] b = XCBase64.decode(info.getPhoto());
					for (int i = 0; i < b.length; ++i) {
						if (b[i] < 0) {// 调整异常数据
							b[i] += 256;
						}
					}
					String path = FileUtils.photoTempDir;// 新生成的图片
					File dir = XCFile.create(path);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyyMMddHHmmss");
					String filename = dateFormat.format(new Date());
					File file = XCFile.create(dir + File.separator + filename
							+ ".jpg");
					if (!file.exists()) {
						file.createNewFile();
					}
					OutputStream out = XCFileOutputStream.create(file);
					out.write(b);
					out.flush();
					out.close();
					ImageLoader.load(
							FileUtils.photoTempPath(filename + ".jpg"),
							imageView, -1, -1);
				}
			}
		} catch (Exception e) {
			XCLog.e("message", "身份证联网核查照片错误：" + e.getMessage());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = LayoutInflater.from(getContext());
		View contentView = inflater.inflate(R.layout.pop_personinfor, null);
		setContentView(contentView,
				new LayoutParams((int) (XCToolkit.getScreenWidth() * 0.5656f),
						(int) (XCToolkit.getScreenHeight() * 0.4925f)));
		VInject.inject(this, contentView);
		setCanceledOnTouchOutside(false);

		initPhone(Info);
		init();
	}

	@OnClick(R.id.closepop)
	public void close(View view) {
		dialogListener.click("close", "", "", "", "");
		dismiss();
	}

	@OnClick(R.id.copypop)
	public void copy(View view) {
		dialogListener.click("query", Info.getCreditCard(), Info.getNation(),
				Info.getCardType(), Info.getName());
		dismiss();
	}
}
