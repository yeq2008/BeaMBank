package com.bea.mbank.edit.step1;

import java.util.ArrayList;

import xc.android.utils.XCToolkit;

import com.bea.database.DbManager;
import com.bea.mbank.util.SubHelperUtil;
import com.bea.mbank.widgets.GridTableView;
import com.bea.mbank.widgets.GridTableView.GridTableViewActionListener;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.android.widgets.TitleValueView;
import com.bea.mbank.R;
import com.bea.mbank.edit.ContentBaseFragment;
import com.bea.remote.models.grwdy.CreditFzBO;
import com.bea.remote.models.grwdy.CreditInforBO;
import com.bea.remote.models.grwdy.CreditZcBO;
import com.cyg.viewinject.VInject;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;

/**
 * @author xuruian 借款人资产/银行/信用资料
 */
public class CreditInforFragment extends ContentBaseFragment<CreditInforBO> {
	@Override
	protected Class getInfoClass() {
		return CreditInforBO.class;
	}

	private static final String tableName = "借款人资产资料";
	// 借款人资产列表
	@ViewInject(R.id.Credit_gridTableView)
	GridTableView gridTable;
	// 借款人资产列表
	@ViewInject(R.id.Credit_gridTableView1)
	GridTableView gridTable1;

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup arg1) {
		return inflater.inflate(R.layout.fm_grwdy_step1_content_creditinfo,
				null);
	}

	@Override
	protected void onInitContentView(View rootView, CreditInforBO info) {
		initGtv(info);
		gridTable
				.setGridTableViewActionListener(new GridTableViewActionListener() {

					@Override
					public void onModifyButtonEvent(int row) {
						gridTable.deleteRowData(row);
						CreditZcBO tmp = infoBO.getZcList().get(row);
						infoBO.getZcList().remove(tmp);
						new CreditinfoDialog(getActivity(), tmp, true).show();

					}

					@Override
					public void onDeleteButtonEvent(int row) {
						// TODO Auto-generated method stub
						gridTable.deleteRowData(row);
						infoBO.getZcList().remove(row);
					}

					@Override
					public void onAddButtonEvent() {
						// TODO Auto-generated method stub
						new CreditinfoDialog(getActivity(), null, false).show();

					}
				});
		gridTable1
				.setGridTableViewActionListener(new GridTableViewActionListener() {

					@Override
					public void onModifyButtonEvent(int row) {
						// TODO Auto-generated method stub
						gridTable1.deleteRowData(row);
						CreditFzBO tmp = infoBO.getFzList().get(row);
						infoBO.getFzList().remove(tmp);
						new CreditinfoDialog1(getActivity(), tmp, true).show();
					}

					@Override
					public void onDeleteButtonEvent(int row) {
						gridTable1.deleteRowData(row);
						infoBO.getFzList().remove(row);
					}

					@Override
					public void onAddButtonEvent() {
						new CreditinfoDialog1(getActivity(), null, false)
								.show();

					}
				});
	}

	/**
	 * 借款人资产
	 * 
	 * @author PersonLi
	 */
	class CreditinfoDialog extends Dialog {
		/**
		 * 资产项目分类
		 */
		private GroupTagViewEx mB_zcxmfenlei;
		/**
		 * 币种
		 */
		private GroupTagViewEx mB_bizhong;
		/**
		 * 余额
		 */
		private TitleValueView mB_yue;
		CreditZcBO info;
		/**
		 * 是否为修改状态
		 */
		private boolean Isedit;
		/**
		 * 是否填完数据
		 */
		private boolean Isok = false;

		public CreditinfoDialog(Context context, CreditZcBO creditZcBO,
				boolean Isedit) {
			super(context);
			this.info = creditZcBO;
			this.Isedit = Isedit;
			if (this.info == null) {
				this.info = new CreditZcBO();
			}
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			LayoutParams params = new LayoutParams(
					(int) (XCToolkit.getScreenWidth() * 0.5656f),
					(int) (XCToolkit.getScreenHeight() * 0.4925f));
			View contentView = mInflater.inflate(
					R.layout.pop_creditinfo_zichan, null);
			setContentView(contentView, params);
			VInject.inject(this, contentView);
			Init();
		}

		private void Init() {
			mB_zcxmfenlei = (GroupTagViewEx) findViewById(R.id.borrow_zc_xmfenleiview);
			mB_zcxmfenlei.addButtons(DbManager.codeDatas("AssetType"), "NAME",
					info.getPAType());
			mB_bizhong = (GroupTagViewEx) findViewById(R.id.borrower_bizhong_view);
			mB_bizhong.addButtons(DbManager.codeDatas("Currency"), "NAME",
					info.getPICurrency());
			mB_yue = (TitleValueView) findViewById(R.id.borower_yu_e);
			if (null != info) {
				mB_yue.setValueText(info.getPIBalance());
			}
			mB_yue.getValueView().addTextChangedListener(textWatcher);
		}

		@OnClick(R.id.mytask_search_confirmBtn)
		public void confirmBtn(View v) {
			if ("".equals(mB_zcxmfenlei.getCurrentValue())
					|| null == mB_zcxmfenlei.getCurrentValue()) {
				XCToolkit.showToast("请选择资产项目分类");
			} else if ("".equals(mB_bizhong.getCurrentValue())
					|| null == mB_bizhong.getCurrentValue()) {
				XCToolkit.showToast("请选择币种");
			} else if ("".equals(mB_yue.getValueText())
					|| null == mB_yue.getValueText()) {
				XCToolkit.showToast("请输入余额");
			} else {
				Isok = true;
			}

			if (Isok) {
				CreditZcBO info = new CreditZcBO();
				info.setPAType((String) mB_zcxmfenlei.getCurrentValue());
				info.setPATypeName(mB_zcxmfenlei.getYRLname());
				info.setPICurrency((String) mB_bizhong.getCurrentValue());
				info.setPICurrencyName(mB_bizhong.getYRLname());
				info.setPIBalance(mB_yue.getValueText());
				infoBO.getZcList().add(info);
				addDate(gridTable, mB_zcxmfenlei.getYRLname(),
						mB_bizhong.getYRLname(), mB_yue.getValueText(), infoBO
								.getZcList().size() - 1);

				// 关闭窗口
				CreditinfoDialog.this.dismiss();
			}
		}

		@OnClick(R.id.mytask_search_closeBtn)
		public void closemBtn(View v) {
			if (Isedit) {
				CreditZcBO info = new CreditZcBO();
				info.setPAType((String) mB_zcxmfenlei.getCurrentValue());
				info.setPATypeName(mB_zcxmfenlei.getYRLname());
				info.setPICurrency((String) mB_bizhong.getCurrentValue());
				info.setPICurrencyName(mB_bizhong.getYRLname());
				info.setPIBalance(mB_yue.getValueText());
				infoBO.getZcList().add(info);
				addDate(gridTable, mB_zcxmfenlei.getYRLname(),
						mB_bizhong.getYRLname(), mB_yue.getValueText(), infoBO
								.getZcList().size() - 1);
			}
			CreditinfoDialog.this.dismiss();
		}
	}

	/**
	 * 借款人负债
	 * 
	 * @author PersonLi
	 */
	class CreditinfoDialog1 extends Dialog {
		private GroupTagViewEx mB_FeiLei;
		private GroupTagViewEx mB_BiZhong;
		private TitleValueView mB_YuE;
		private TitleValueView mB_ShouXinEDu;
		private TitleValueView mB_HuanKuanYuE;
		private CreditFzBO info;
		/**
		 * 是否填完数据
		 */
		private boolean Isok = false;
		/**
		 * 是否为修改状态
		 */
		private boolean Isedit;

		public CreditinfoDialog1(Context context, CreditFzBO tmp, boolean b) {
			super(context);
			this.info = tmp;
			this.Isedit = b;
			if (this.info == null) {
				this.info = new CreditFzBO();
			}
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			LayoutParams params = new LayoutParams(
					(int) (XCToolkit.getScreenWidth() * 0.5656f),
					(int) (XCToolkit.getScreenHeight() * 0.4925f));
			View contentView = mInflater.inflate(
					R.layout.pop_creditinfo_fuzhai, null);
			setContentView(contentView, params);
			VInject.inject(this, contentView);
			Init();
		}

		private void Init() {
			mB_FeiLei = (GroupTagViewEx) findViewById(R.id.borrower_fenlei_subview);
			mB_BiZhong = (GroupTagViewEx) findViewById(R.id.borrower_bizhong_subview);
			mB_YuE = (TitleValueView) findViewById(R.id.borower_yu_e_sub);
			mB_ShouXinEDu = (TitleValueView) findViewById(R.id.borower_e_dulimit);
			mB_HuanKuanYuE = (TitleValueView) findViewById(R.id.borower_huankuan);
			mB_BiZhong.addButtons(DbManager.codeDatas("Currency"), "NAME",
					info.getPOCurrency());
			mB_FeiLei.addButtons(DbManager.codeDatas("OtherDebtType"), "NAME",
					info.getPPAType());
			if (null != info) {
				mB_YuE.setValueText(info.getPOBalance());
				mB_ShouXinEDu.setValueText(info.getPlimit());
				mB_HuanKuanYuE.setValueText(info.getPPM());
			}
			mB_YuE.getValueView().addTextChangedListener(textWatcher);
			mB_ShouXinEDu.getValueView().addTextChangedListener(textWatcher);
			mB_HuanKuanYuE.getValueView().addTextChangedListener(textWatcher);
		}

		@OnClick(R.id.mytask_search_confirmBtn)
		public void confirmBtn(View v) {
			if ("".equals(mB_FeiLei.getCurrentValue())
					|| null == mB_FeiLei.getCurrentValue()) {
				XCToolkit.showToast("请选择负债项目分类");
			} else if ("".equals(mB_BiZhong.getCurrentValue())
					|| null == mB_BiZhong.getCurrentValue()) {
				XCToolkit.showToast("请选择币种");
			} else if ("".equals(mB_YuE.getValueText())
					|| null == mB_YuE.getValueText()) {
				XCToolkit.showToast("请输入余额");
			} else if ("".equals(mB_ShouXinEDu.getValueText())
					|| null == mB_ShouXinEDu.getValueText()) {
				XCToolkit.showToast("请输入授信额度");
			} else if ("".equals(mB_HuanKuanYuE.getValueText())
					|| null == mB_HuanKuanYuE.getValueText()) {
				XCToolkit.showToast("请输入还款余额");
			} else {
				Isok = true;
			}
			if (Isok) {
				CreditFzBO info = new CreditFzBO();
				info.setPPAType((String) mB_FeiLei.getCurrentValue());
				info.setPPATypeName(mB_FeiLei.getYRLname());
				info.setPOCurrency((String) mB_BiZhong.getCurrentValue());
				info.setPOCurrencyName(mB_BiZhong.getYRLname());
				info.setPOBalance(mB_YuE.getValueText());
				info.setPlimit(mB_ShouXinEDu.getValueText());
				info.setPPM(mB_HuanKuanYuE.getValueText());
				infoBO.getFzList().add(info);
				addDate1(gridTable1, mB_FeiLei.getYRLname(),
						mB_BiZhong.getYRLname(), mB_YuE.getValueText(),
						mB_ShouXinEDu.getValueText(),
						mB_HuanKuanYuE.getValueText(), infoBO.getFzList()
								.size() - 1);
				// 关闭窗口
				CreditinfoDialog1.this.dismiss();
			}
		}

		@OnClick(R.id.mytask_search_closeBtn)
		public void closeBtn(View v) {
			if (Isedit) {
				CreditFzBO info = new CreditFzBO();
				info.setPPAType((String) mB_FeiLei.getCurrentValue());
				info.setPPATypeName(mB_FeiLei.getYRLname());
				info.setPOCurrency((String) mB_BiZhong.getCurrentValue());
				info.setPOCurrencyName(mB_BiZhong.getYRLname());
				info.setPOBalance(mB_YuE.getValueText());
				info.setPlimit(mB_ShouXinEDu.getValueText());
				info.setPPM(mB_HuanKuanYuE.getValueText());
				infoBO.getFzList().add(info);
				addDate1(gridTable1, mB_FeiLei.getYRLname(),
						mB_BiZhong.getYRLname(), mB_YuE.getValueText(),
						mB_ShouXinEDu.getValueText(),
						mB_HuanKuanYuE.getValueText(), infoBO.getFzList()
								.size() - 1);
			}
			CreditinfoDialog1.this.dismiss();
		}
	}

	/**
	 * 初始化
	 */
	private void initGtv(CreditInforBO info) {
		if (null == infoBO.getZcList()) {
			infoBO.setZcList(new ArrayList<CreditZcBO>());
		} else {
			for (int i = 0; i < infoBO.getZcList().size(); i++) {
				CreditZcBO cZcBO = infoBO.getZcList().get(i);
				addDate(gridTable, cZcBO.getPATypeName(),
						cZcBO.getPICurrencyName(), cZcBO.getPIBalance(), i);
			}
		}
		if (null == infoBO.getFzList()) {
			infoBO.setFzList(new ArrayList<CreditFzBO>());
		} else {
			for (int i = 0; i < infoBO.getFzList().size(); i++) {
				CreditFzBO cFzBO = infoBO.getFzList().get(i);
				addDate1(gridTable1, cFzBO.getPPATypeName(),
						cFzBO.getPOCurrencyName(), cFzBO.getPOBalance(),
						cFzBO.getPlimit(), cFzBO.getPPM(), i);
			}
		}
	}

	/**
	 * 页面销毁时掉用
	 */
	@Override
	public CreditInforBO getValueFromInterfaceView() {

		return infoBO;
	}

	public void addDate(GridTableView gridTable, String a, String b, String c,
			int index) {
		gridTable.beginTransaction();
		gridTable.setGridTitle(index, 0, a);
		gridTable.setGridTitle(index, 1, b);
		gridTable.setGridTitle(index, 2, c);
		gridTable.endTransaction();
	}

	public void addDate1(GridTableView gridTable, String a, String b, String c,
			String d, String e, int index) {
		gridTable.beginTransaction();
		gridTable.setGridTitle(index, 0, a);
		gridTable.setGridTitle(index, 1, b);
		gridTable.setGridTitle(index, 2, c);
		gridTable.setGridTitle(index, 3, d);
		gridTable.setGridTitle(index, 4, e);
		gridTable.endTransaction();
	}

	@Override
	public boolean isRequiredFieldInputed(CreditInforBO info) {
		SubHelperUtil.setTableName(tableName);
		if (null == info) {
			XCToolkit.showToast("请填写“借款人资产资料”");
			return false;
		}
		return true;
	}
}
