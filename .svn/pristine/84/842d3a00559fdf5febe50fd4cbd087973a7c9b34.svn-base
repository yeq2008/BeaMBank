package com.bea.mbank.edit.step1;

import xc.android.application.XCApplication;
import xc.android.utils.XCToolkit;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.android.uitableview.ViewHolder;
import com.android.widgets.XCButton;
import com.android.widgets.XCImageButton;
import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.mbank.edit.PanelBaseFragment;
import com.bea.mbank.edit.crm.CRMDialog;
import com.bea.mbank.edit.crm.CRMDialog.CrmDialogClickListener;
import com.bea.mbank.edit.step2.Step2HomeFragment;
import com.bea.mbank.widgets.SlideCutListView;
import com.bea.mbank.widgets.SlideCutListView.MoveDirection;
import com.bea.mbank.widgets.SlideCutListView.MoveListener;
import com.bea.mbank.widgets.SwipeActionAdapter;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

/**
 * @author cuiyuguo 个人无抵押贷款模块右侧控制面板
 */
public class GrwdyPanelFragment extends PanelBaseFragment implements
		MoveListener {
	class MyPannelAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return homeBO.isPartRequiredData ? 2 : homeBO.isPinAnXinBaoData ? 7
					: 8;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.v_grwdy_pannel_item,
						null);
				initPannelItemViewHolder(convertView, position, holder);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			initPannelItem(position, holder);

			TextView titleView = (TextView) holder.findViewById(R.id.title);
			String tmp = titleView.getText().toString();
			if (currentItem.equals(tmp)) {
				convertView.findViewById(R.id.llitem).setBackgroundColor(
						0xffe6e6e6);
			} else {
				convertView.findViewById(R.id.llitem).setBackgroundColor(
						0xffffffff);
			}

			return convertView;
		}

		private void initPannelItemViewHolder(View convertView, int position,
				ViewHolder holder) {
			holder.holderView(convertView.findViewById(R.id.lineState));
			holder.holderView(convertView.findViewById(R.id.title));
		}

		private void initPannelItem(int position, ViewHolder holder) {
			TextView titleView = (TextView) holder.findViewById(R.id.title);
			View lineState = holder.findViewById(R.id.lineState);

			if (homeBO.isPartRequiredData) {
				if (0 == position) {
					titleView.setText("个人基本资料");
					setLineState(lineState, homeBO.isBasicInfoRequired, true);
				} else if (1 == position) {
					titleView.setText("影像资料");
					setLineState(lineState, homeBO.isTakePhotosRequired, true);
				}
			} else {
				if (0 == position) {
					titleView.setText("借款人个人资料");
					setLineState(lineState, homeBO.isBorrowerInforRequired,
							true);
				} else if (1 == position) {
					titleView.setText("借款人职业信息");
					setLineState(lineState, homeBO.isProfessionInforRequired,
							true);
				} else if (2 == position) {
					titleView.setText("联系人信息");
					setLineState(lineState, homeBO.isContactInforRequired, true);
				} else if (3 == position) {
					titleView.setText("借款人资产资料");
					setLineState(lineState, homeBO.isCreditInforRequired, false);
				} else if (4 == position) {
					titleView.setText("共同借款人个人资料");
					setLineState(lineState, homeBO.isJointInforRequired, false);
				} else if (5 == position) {
					titleView.setText("借款人配偶资料");
					setLineState(lineState, homeBO.isMateInforRequired, false);
				} else if (6 == position) {
					titleView.setText("申请贷款及费用信息");
					setLineState(lineState, homeBO.isApplyLoanInforRequired,
							true);
				} else if (7 == position) {
					titleView.setText("影像资料");
					setLineState(lineState, homeBO.isTakePhotosRequired, true);
				}
			}
		}

		private void setLineState(View stateView, boolean isRequired,
				boolean useRedColor) {
			if (null == stateView) {
				return;
			}
			if (isRequired) {
				stateView.setBackgroundColor(useRedColor ? 0xffff0000
						: 0xffFB9F1D);
			} else {
				stateView.setBackgroundColor(0xffffffff);
			}
		}
	}

	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_grwdy_step1_pannel, null);
	}

	@ViewInject(R.id.listview)
	SlideCutListView listview;
	SwipeActionAdapter adapter;
	@ViewInject(R.id.partOrAll)
	ImageButton ibPartOrAll;
	@ViewInject(R.id.pannelLast)
	XCButton lastBtn;
	@ViewInject(R.id.pannelNext)
	XCButton nextBtn;
	@ViewInject(R.id.pannelCRM)
	XCImageButton crmbtn;
	String currentItem = "借款人个人资料";
	String currentItemWithoutMove = "借款人个人资料";

	@Override
	protected void onInitContentView(View arg0) {
		super.onInitContentView(arg0);
		setRequiredData(homeBO.isPartRequiredData);

		adapter = new SwipeActionAdapter(new MyPannelAdapter());
		listview.setMoveListener(this);
		listview.setMoveDirection(MoveDirection.LEFT_RIGHT);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View item,
					int position, long arg3) {
				if (item instanceof SwipeActionAdapter.RootContentView) {
					item = ((SwipeActionAdapter.RootContentView) item)
							.contentView();
				}
				ViewHolder holder = (ViewHolder) item.getTag();
				TextView titleView = (TextView) holder.findViewById(R.id.title);
				if (setCurrentItem(titleView.getText().toString()))
					adapter.notifyDataSetChanged();
				onPannalItemClicked(titleView.getText());
			}
		});

		if (homeBO.getMainid().equals("0")) {// 非草稿件
			if ("part".equals(BeaAppSettings.getPreference("all_part_flag",
					"all"))) {
				ibPartOrAll.setTag("all2part");
			} else {
				ibPartOrAll.setTag("part2all");
			}
			partOrAllOnClick();
		} else {// 草稿件
			ibPartOrAll.setVisibility(View.GONE);
			crmbtn.setBackgroundResource(R.drawable.panel_btn_crmlong);
			RelativeLayout.LayoutParams lp = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
			crmbtn.setLayoutParams(lp);
			if (homeBO.isPartRequiredData) {
				partOrAllBtnStatus(true, "个人基本资料");
			} else {
				partOrAllBtnStatus(false, "借款人个人资料");
			}
		}
	}

	@OnClick(R.id.partOrAll)
	public void partOrAllClick(View view) {
		partOrAllOnClick();
		// 记录操作类型(全量/批量)
		BeaAppSettings.savePreference("all_part_flag",
				homeBO.isPartRequiredData ? "part" : "all");
	}

	private void partOrAllOnClick() {
		String tag = (String) ibPartOrAll.getTag();
		mCurrentIndex = -1;
		if ("all2part".equals(tag)) {
			ibPartOrAll.setBackgroundResource(R.drawable.pannel_btn_partinput);
			partOrAllBtnStatus(true, "个人基本资料");
			ibPartOrAll.setTag("part2all");
		} else if ("part2all".equals(tag)) {
			ibPartOrAll.setBackgroundResource(R.drawable.pannel_btn_allinput);
			partOrAllBtnStatus(false, "借款人个人资料");
			ibPartOrAll.setTag("all2part");
		}
	}

	private void partOrAllBtnStatus(boolean flag, String title) {
		setRequiredData(flag);
		adapter.notifyDataSetChanged();
		onPannalItemClicked(title);
	}

	// add by flh
	// 菜单高亮效果
	private boolean setCurrentItem(String title) {
		boolean flag = false;
		if ("借款人个人资料".equals(title) && homeBO.isBorrowerInforRequired) {
			currentItem = title;
			currentItemWithoutMove = title;
			flag = true;
		} else if ("借款人配偶资料".equals(title) && homeBO.isMateInforRequired) {
			currentItem = title;
			flag = true;
		} else if ("借款人职业信息".equals(title) && homeBO.isProfessionInforRequired) {
			currentItem = title;
			currentItemWithoutMove = title;
			flag = true;
		} else if ("联系人信息".equals(title) && homeBO.isContactInforRequired) {
			currentItem = title;
			currentItemWithoutMove = title;
			flag = true;
		} else if ("共同借款人个人资料".equals(title) && homeBO.isJointInforRequired) {
			currentItem = title;
			flag = true;
		} else if ("借款人资产资料".equals(title) && homeBO.isCreditInforRequired) {
			currentItem = title;
			flag = true;
		} else if ("申请贷款及费用信息".equals(title) && homeBO.isApplyLoanInforRequired) {
			currentItem = title;
			currentItemWithoutMove = title;
			flag = true;
		} else if ("个人基本资料".equals(title) && homeBO.isBasicInfoRequired) {
			currentItem = title;
			currentItemWithoutMove = title;
			flag = true;
		} else if ("影像资料".equals(title) && homeBO.isTakePhotosRequired) {
			currentItem = title;
			currentItemWithoutMove = title;
			flag = true;
		}
		return flag;
	}

	private void onPannalItemClicked(Object itemTitle) {
		if ("借款人个人资料".equals(itemTitle)) {
			sendMessage(ConstDefined.EditPannelActionKey,
					ConstDefined.BorrowerInforTag,
					homeBO.isBorrowerInforRequired);
		} else if ("借款人配偶资料".equals(itemTitle)) {
			sendMessage(ConstDefined.EditPannelActionKey, ConstDefined.MateTag,
					homeBO.isMateInforRequired);
		} else if ("借款人职业信息".equals(itemTitle)) {
			sendMessage(ConstDefined.EditPannelActionKey,
					ConstDefined.ProfessionInforTag,
					homeBO.isProfessionInforRequired);
		} else if ("联系人信息".equals(itemTitle)) {
			sendMessage(ConstDefined.EditPannelActionKey,
					ConstDefined.ContactInforTag, homeBO.isContactInforRequired);
		} else if ("共同借款人个人资料".equals(itemTitle)) {
			sendMessage(ConstDefined.EditPannelActionKey,
					ConstDefined.JointInforTag, homeBO.isJointInforRequired);
		} else if ("借款人资产资料".equals(itemTitle)) {
			sendMessage(ConstDefined.EditPannelActionKey,
					ConstDefined.CreditInforTag, homeBO.isCreditInforRequired);
		} else if ("申请贷款及费用信息".equals(itemTitle)) {
			sendMessage(ConstDefined.EditPannelActionKey,
					ConstDefined.ApplyLoanInfoTag,
					homeBO.isApplyLoanInforRequired);
		} else if ("个人基本资料".equals(itemTitle)) {
			sendMessage(ConstDefined.EditPannelActionKey,
					ConstDefined.BasicInfoTag, homeBO.isBasicInfoRequired);
		} else if ("影像资料".equals(itemTitle)) {
			sendMessage(ConstDefined.EditPannelActionKey,
					ConstDefined.TakePhotosTag, homeBO.isTakePhotosRequired);
		}
	}

	int mCurrentIndex = -1;

	private void sendMessage(String msgkey, Object msgObject, boolean isActived) {
		if (isActived
				&& (mCurrentIndex != (Integer) msgObject || ConstDefined.CreditInforTag == (Integer) msgObject)) {
			sendMessage(msgkey, mCurrentIndex = (Integer) msgObject);
		}
	}

	private void setRequiredData(boolean requiredData) {
		homeBO.isPartRequiredData = requiredData;
		if (!requiredData) {
			homeBO.isBorrowerInforRequired = true;
			homeBO.isProfessionInforRequired = true;
			homeBO.isContactInforRequired = true;
			homeBO.isApplyLoanInforRequired = true;
			homeBO.isTakePhotosRequired = true;
		} else {
			homeBO.isBasicInfoRequired = true;
			homeBO.isTakePhotosRequired = true;
		}
	}

	@Override
	public void MoveItem(View itemView, MoveDirection direction, int position) {
		ViewHolder holder = (ViewHolder) itemView.getTag();
		TextView titleView = (TextView) holder.findViewById(R.id.title);

		if ("借款人配偶资料".equals(titleView.getText())) {
			homeBO.isMateInforRequired = !homeBO.isMateInforRequired;
			if (!homeBO.isMateInforRequired) {
				currentItem = currentItemWithoutMove;
				adapter.notifyDataSetChanged();
				sendMessage(ConstDefined.EditPannelActionKey,
						ConstDefined.Change2BlankTag, true);
			} else if (homeBO.isMateInforRequired) {
				currentItem = "借款人配偶资料";
				adapter.notifyDataSetChanged();
				sendMessage(ConstDefined.EditPannelActionKey,
						ConstDefined.MateTag, true);
			}
		} else if ("共同借款人个人资料".equals(titleView.getText())) {
			homeBO.isJointInforRequired = !homeBO.isJointInforRequired;
			if (!homeBO.isJointInforRequired) {
				currentItem = currentItemWithoutMove;
				adapter.notifyDataSetChanged();
				sendMessage(ConstDefined.EditPannelActionKey,
						ConstDefined.Change2BlankTag, true);
			} else if (homeBO.isJointInforRequired) {
				currentItem = "共同借款人个人资料";
				adapter.notifyDataSetChanged();
				sendMessage(ConstDefined.EditPannelActionKey,
						ConstDefined.JointInforTag, true);
			}
		} else if ("借款人资产资料".equals(titleView.getText())) {
			homeBO.isCreditInforRequired = !homeBO.isCreditInforRequired;
			if (!homeBO.isCreditInforRequired) {
				currentItem = currentItemWithoutMove;
				adapter.notifyDataSetChanged();
				sendMessage(ConstDefined.EditPannelActionKey,
						ConstDefined.Change2BlankTag, true);
			} else if (homeBO.isCreditInforRequired) {
				currentItem = "借款人资产资料";
				adapter.notifyDataSetChanged();
				sendMessage(ConstDefined.EditPannelActionKey,
						ConstDefined.CreditInforTag, true);
			}
		}
	}

	@Override
	public boolean isCanMoveItem(View itemView, int position) {
		if (homeBO.isPartRequiredData) {// 批量
			return false;
		} else {
			ViewHolder holder = (ViewHolder) itemView.getTag();
			TextView titleView = (TextView) holder.findViewById(R.id.title);
			return !("借款人个人资料".equals(titleView.getText())
					|| "借款人职业信息".equals(titleView.getText())
					|| "联系人信息".equals(titleView.getText())
					|| "申请贷款及费用信息".equals(titleView.getText()) || "影像资料"
						.equals(titleView.getText()));
		}
	}

	/**
	 * 修改人徐瑞安 在crm系统中查询客户
	 */
	@OnClick(R.id.pannelCRM)
	public void onPannelCRMButtonEvent(View v) {
		if (null == BeaAppSettings.getUserInfo()) {
			XCToolkit.showToast("请先登录！");
		} else {
			CRMDialog dialog = new CRMDialog(getActivity(),
					new CrmDialogClickListener() {
						@Override
						public void click() {
							if (null != homeBO)
								homeBO.setIsCrmQueryed("1");
							XCApplication.postMsg(
									ConstDefined.SavedataWhileChangeAction,
									null);
							pushFragmentController(new BlackListHomeFragment());
						}
					});
			dialog.show();
		}
	}

	/**
	 * 下一步
	 * 
	 * @param v
	 */
	@OnClick(R.id.pannelNext)
	public void onPannelNextButtonEvent(View v) {
		// crm是否确认客户
		if (null != homeBO && "0".equals(homeBO.getIsCrmQueryed())) {
			CRMDialog dialog = new CRMDialog(getActivity(),
					new CrmDialogClickListener() {
						@Override
						public void click() {
							if (null != homeBO)
								homeBO.setIsCrmQueryed("1");
							XCApplication.postMsg(
									ConstDefined.SavedataWhileChangeAction,
									null);
							pushFragmentController(new BlackListHomeFragment());
						}
					});
			dialog.show();
			return;
		}

		// 检查数据是否填写完毕
		if (checkRequired()) {// 全部填写后，进入预览页面
			if (homeBO.getIsPartRequiredData()) {
				homeBO.isBorrowerInforRequired = false;
				homeBO.isProfessionInforRequired = false;
				homeBO.isContactInforRequired = false;
				homeBO.isApplyLoanInforRequired = false;
				homeBO.isJointInforRequired = false;
				homeBO.isCreditInforRequired = false;

				pushFragmentController(new Step2HomeFragment());
			} else {
				homeBO.isBasicInfoRequired = false;
				pushFragmentController(new Step2HomeFragment());
			}
		}
	}

	@Override
	public View getMoveStatueView(View itemView, int position) {
		ViewHolder holder = (ViewHolder) itemView.getTag();
		TextView titleView = (TextView) holder.findViewById(R.id.title);
		if ("借款人配偶资料".equals(titleView.getText())) {
			return getMoveStatueView(homeBO.isMateInforRequired);
		} else if ("共同借款人个人资料".equals(titleView.getText())) {
			return getMoveStatueView(homeBO.isJointInforRequired);
		} else if ("借款人资产资料".equals(titleView.getText())) {
			return getMoveStatueView(homeBO.isCreditInforRequired);
		} else if ("借款人个人资料".equals(titleView.getText())) {
			return getMoveStatueView(homeBO.isBorrowerInforRequired);
		} else if ("借款人职业信息".equals(titleView.getText())) {
			return getMoveStatueView(homeBO.isProfessionInforRequired);
		} else if ("联系人信息".equals(titleView.getText())) {
			return getMoveStatueView(homeBO.isContactInforRequired);
		} else if ("申请贷款及费用信息".equals(titleView.getText())) {
			return getMoveStatueView(homeBO.isApplyLoanInforRequired);
		} else if ("影像资料".equals(titleView.getText())) {
			return getMoveStatueView(homeBO.isTakePhotosRequired);
		} else if ("个人基本资料".equals(titleView.getText())) {
			return getMoveStatueView(homeBO.isBasicInfoRequired);
		} else {
			return null;
		}
	}

	private View getMoveStatueView(boolean isActived) {
		if (!isActived) {
			return mInflater.inflate(R.layout.v_grwdy_pannel_activate_bg, null);
		} else {
			View v = mInflater.inflate(R.layout.v_grwdy_pannel_activate_bg,
					null);
			TextView tv = (TextView) v.findViewById(R.id.title);
			tv.setText("取消");
			tv.setTextColor(0xff000000);
			tv.setBackgroundColor(0xffe8e8e8);
			return v;
		}
	}
}
