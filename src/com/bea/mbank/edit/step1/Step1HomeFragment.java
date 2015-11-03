package com.bea.mbank.edit.step1;

import xc.android.utils.XCToolkit;

import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.mbank.edit.HomeBaseFragment;
import com.bea.mbank.edit.crm.CRMDialog;
import com.bea.mbank.edit.crm.CRMDialog.CrmDialogClickListener;
import com.cyg.viewinject.annotation.ViewInject;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author cuiyuguo 第一步管理页面
 */
public class Step1HomeFragment extends HomeBaseFragment {
	// @Override
	// public void onDestroy() {
	// if (null != homeBO && homeBO.isTakePhotosRequired &&
	// null != homeBO.getTakePhotos() &&
	// null == homeBO.getTakePhotos().zipFileUrl) {
	// homeBO.getTakePhotos().zipTempFile = FileUtils.zipPhotos(
	// homeBO.getTakePhotos().getPhotos());
	// }
	// BeaAppSettings.setOriginData(null);
	// super.onDestroy();
	// }

	@Override
	protected void onInitContentView(View arg0) {
		super.onInitContentView(arg0);
		// 监听主菜单消息事件
		observeMessage(ConstDefined.EditPannelActionKey);
		if (homeBO.getMainid().equals("0")) {
			// 新建
			// 根据上次保存操作(全量/批量)
			// 选择是否录入类型(全量/批量)
			// 默认为全量
			if (!homeBO.isPinAnXinBaoData) {
				if ("part".equals(BeaAppSettings.getPreference("all_part_flag",
						"all")))
					homeBO.setIsPartRequiredData(true);
			}
		}

		// 加载内容部分
		if (homeBO.getIsPartRequiredData()) {
			onReceiveMessage(ConstDefined.EditPannelActionKey,
					ConstDefined.BasicInfoTag);
		} else {
			onReceiveMessage(ConstDefined.EditPannelActionKey,
					ConstDefined.BorrowerInforTag);
		}
		// 控制面板部分加载
		if (homeBO.isPinAnXinBaoData) {
			replacePanelFragment(new PaxbPanelFragment());
		} else {
			replacePanelFragment(new GrwdyPanelFragment());
		}

		openCRMDialog();
	}

	// 打开crm查询
	private void openCRMDialog() {
		if (null == getArguments().get(ConstDefined.bundleSerializableParamKey)) {
			// 新建
			CRMDialog dialog = new CRMDialog(getActivity(),
					new CrmDialogClickListener() {
						@Override
						public void click() {
							if (null != homeBO)
								homeBO.setIsCrmQueryed("1");// 0未查询1已查询
							pushFragmentController(new BlackListHomeFragment());
						}
					});
			dialog.show();
		}
	}

	/**
	 * @brief 消息监听函数，当有消息来将调用此函数
	 * @param msgkey
	 *            消息关键字
	 * @param msg
	 *            消息信息，发送过来的消息实际内容
	 */
	int mCurrentIndex = -1;
	int mLastActiveIndex = -1;

	@Override
	public void onReceiveMessage(String msgkey, Object msgObject) {
		if (ConstDefined.EditPannelActionKey.equals(msgkey)) {
			// 已经是当前项，不需切换
			if (mCurrentIndex == (Integer) msgObject) {
				return;
			}

			mCurrentIndex = (Integer) msgObject;

			// 个人无抵押：借款人个人资料
			if (((Integer) msgObject) == ConstDefined.BorrowerInforTag) {
				replaceContentFragment(new BorrowerInforFragment());
			}
			// 个人无抵押：借款人职业资料
			else if (((Integer) msgObject) == ConstDefined.ProfessionInforTag) {
				replaceContentFragment(new ProfessionInforFragment());
			}
			// 个人无抵押：共同借款人个人资料
			else if (((Integer) msgObject) == ConstDefined.JointInforTag) {
				replaceContentFragment(new JointInforFragment());
			}
			// 个人无抵押：联系人信息
			else if (((Integer) msgObject) == ConstDefined.ContactInforTag) {
				replaceContentFragment(new ContactInforFragment());
			}
			// 个人无抵押：借款人资产/银行/信用资料
			else if (((Integer) msgObject) == ConstDefined.CreditInforTag) {
				replaceContentFragment(new CreditInforFragment());
			}
			// 个人无抵押：配偶信息信息
			else if (((Integer) msgObject) == ConstDefined.MateTag) {
				replaceContentFragment(new MateInforFragment());
			}
			// 个人无抵押：申请贷款及费用信息
			else if (((Integer) msgObject) == ConstDefined.ApplyLoanInfoTag) {
				replaceContentFragment(new ApplyLoanInforFragment());
			}
			// 个人无抵押：个人基本资料
			else if (((Integer) msgObject) == ConstDefined.BasicInfoTag) {
				replaceContentFragment(new BasicInfoFragment());
			}
			// 个人无抵押：影像采集
			else if (((Integer) msgObject) == ConstDefined.TakePhotosTag) {
				replaceContentFragment(new TakePhotosFragment());
			}
			// 个人无抵押：跳到空白页面
			else if (((Integer) msgObject) == ConstDefined.Change2BlankTag) {
				onReceiveMessage(ConstDefined.EditPannelActionKey,
						mLastActiveIndex);
				return;
			}
			if (((Integer) msgObject) != ConstDefined.JointInforTag
					&& ((Integer) msgObject) != ConstDefined.CreditInforTag
					&& ((Integer) msgObject) != ConstDefined.MateTag) {
				mLastActiveIndex = mCurrentIndex;
			}
		} else {
			super.onReceiveMessage(msgkey, msgObject);
		}
	}
}
