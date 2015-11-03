package com.bea.mbank.mytask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import xc.android.fragment.XCBaseListFragment;
import xc.android.remote.RemotePageConfig;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCToolkit;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.uitableview.UITableView;
import com.android.uitableview.UITableViewAdapter;
import com.android.uitableview.ViewHolder;
import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.mbank.widgets.GroupTagViewEx;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.draft.DraftListBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import com.cyg.viewinject.annotation.ViewInject;

public class DraftListFragment extends XCBaseListFragment {
	// �����İ�ť
	@ViewInject(R.id.searchview)
	private Button msearButton;
	RemotePageConfig rpc;

	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_mytask_draftlist, null);
	}

	@Override
	protected void onInitContentView(View arg0) {
		tableView = (UITableView) arg0.findViewById(R.id.uITableView1);
		msearButton = (Button) findViewById(R.id.searchview);
		msearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new TaskSearchDialog(getActivity()).show();
			}
		});
	}

	class TaskSearchDialog extends Dialog {
		public TaskSearchDialog(Context context) {
			super(context);
		}

		List<String> btnModels = new ArrayList<String>();
		// ��ѯ����
		EditText etName;
		// ��ѯ֤������
		EditText etNumber;
		// ��ϵ����
		GroupTagViewEx CBType;
		String strCbtype;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			LayoutParams params = new LayoutParams(
					(int) (XCToolkit.getScreenWidth() * 0.5656f),
					(int) (XCToolkit.getScreenHeight() * 0.4925f));
			View contentView = mInflater.inflate(R.layout.pop_mytask_search,
					null);
			setContentView(contentView, params);
			btnModels.add("ȫ��");
			btnModels.add("ƽ���ű����Ѵ���");
			btnModels.add("��ʱ�����Ѵ�");
			btnModels.add("��ʱ����λ��");
			CBType = (GroupTagViewEx) findViewById(R.id.Seach_gtvCBType);
			CBType.addButtons(btnModels, "NAME");
			CBType.setCurrentIndex("ƽ���ű����Ѵ���");
			etName = (EditText) findViewById(R.id.Search_editeName);
			etNumber = (EditText) findViewById(R.id.Search_cardNumName);
			adapter = new DraftListAdapter();
			findViewById(R.id.mytask_search_confirmBtn).setOnClickListener(
					new android.view.View.OnClickListener() {
						@Override
						public void onClick(View v) {
							if ((CBType.getCurrentValue()).equals("ȫ��")) {
								strCbtype = "00";
							} else if ((CBType.getCurrentValue())
									.equals("ƽ���ű����Ѵ���")) {
								strCbtype = "10";
							} else if ((CBType.getCurrentValue())
									.equals("��ʱ����λ��")) {
								strCbtype = "11";
							} else if ((CBType.getCurrentValue())
									.equals("��ʱ�����Ѵ�")) {
								strCbtype = "12";
							}
							Map<String, String> object = new HashMap<String, String>();
							records = new ArrayList<DraftListBO>();
							object.put("CBType", "" + strCbtype);
							object.put("cuName", etName.getText().toString());
							object.put("cuNum", etNumber.getText().toString());
							if (null == rpc) {
								rpc = new RemotePageConfig();
								rpc.setCurrentPage(1);
								rpc.setPageSize(20);
							}
							jsonRequest(rpc.getCurrentPage(), "ͨ�ýӿ�",
									JsonRemoteBO.reqParam(
											ConstDefined.SearchDraftList,
											object, rpc));
							// adapter.notifyDataSetChanged();
							// �رմ���
							TaskSearchDialog.this.dismiss();
						}
					});
			findViewById(R.id.mytask_search_closeBtn).setOnClickListener(
					new android.view.View.OnClickListener() {
						@Override
						public void onClick(View v) {
							TaskSearchDialog.this.dismiss();
						}
					});
		}

	}

	class DraftListAdapter extends UITableViewAdapter {
		@Override
		public int rowsInSection(int section) {
			return records.size();
		}

		@Override
		public int viewTypeCount() {
			return 1;
		}

		@Override
		public int getItemViewType(IndexPath path) {
			return 0;
		}

		@Override
		public View viewWithType(int type) {
			return mInflater.inflate(R.layout.v_mytask_draft_listitem, null);
		}

		@Override
		public void initViewHolder(int type, View itemView,
				ViewHolder viewHolder) {
			viewHolder.holderView(itemView.findViewById(R.id.no));
			viewHolder.holderView(itemView.findViewById(R.id.pname));
			viewHolder.holderView(itemView.findViewById(R.id.cardNum));
			viewHolder.holderView(itemView.findViewById(R.id.cusName));
			viewHolder.holderView(itemView.findViewById(R.id.moneyConut));
			viewHolder.holderView(itemView.findViewById(R.id.date));
			viewHolder.holderView(itemView.findViewById(R.id.deadline));
		}

		String cbtype = null;

		@Override
		public void initViewForRowAtIndexPath(IndexPath path, int type,
				ViewHolder viewHolder) {
			TextView no = (TextView) viewHolder.findViewById(R.id.no);
			TextView pname = (TextView) viewHolder.findViewById(R.id.pname);
			TextView cusname = (TextView) viewHolder.findViewById(R.id.cusName);
			TextView cardNum = (TextView) viewHolder.findViewById(R.id.cardNum);
			TextView moneyConut = (TextView) viewHolder
					.findViewById(R.id.moneyConut);
			TextView date = (TextView) viewHolder.findViewById(R.id.date);
			TextView deadline = (TextView) viewHolder
					.findViewById(R.id.deadline);

			DraftListBO cus = records.get(path.row);
			if (cus.getCBType().equals("10")) {
				cbtype = "ƽ���ű����Ѵ���";
			} else if (cus.getCBType().equals("12")) {
				cbtype = "�����޵�Ѻ���Ѵ�";
			} else {
				cbtype = "�����޵�Ѻ��λ��";
			}
			no.setText((path.row + 1) + "");
			pname.setText(stringFormat(cbtype));
			cusname.setText(stringFormat(cus.getCUName()));
			cardNum.setText(stringFormat(cus.getCUNum()));
			moneyConut.setText(stringFormat(cus.getCAmount()));
			date.setText(stringFormat(cus.getCADate()));
			deadline.setText(stringFormat(cus.getCLDate()));
		}

		private String stringFormat(String s) {
			if (null == s)
				return "";
			if ("null".equals(s))
				return "";
			return s;
		}

		@Override
		protected int isSlideToDoSuport(IndexPath path) {
			return R.layout.v_todo_draft_cancel;
		}

		@Override
		protected void initSlideToDoEvent(View todoView) {
			todoView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					final IndexPath path = (IndexPath) v
							.getTag(UITableViewAdapter.SLIDEVIEWTag);
					final AlertDialog.Builder builder = new AlertDialog.Builder(
							getActivity());
					builder.setCancelable(false);
					builder.setMessage("��Ҫɾ�������ݸ����");
					builder.setPositiveButton("��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int whichButton) {
									// ɾ���ݸ��
									DraftListBO bo = records.get(path.row);
									jsonRequest(10000 + path.row, "ͨ�ýӿ�",
											JsonRemoteBO.reqParam(
													ConstDefined.DeleteDraft,
													bo));
								}
							});
					builder.setNegativeButton("��", null);
					builder.show();
				}
			});
		}

		@Override
		public void itemSelectedAtIndexPath(IndexPath path) {
			// ���ĳһ��ȡ��������
			DraftListBO bo = records.get(path.row);
			String tmp = ConstDefined.loanGRWDYUrl;
			if (null != bo.getCBType() && "10".equals(bo.getCBType()))
				tmp = ConstDefined.loanPAXBUrl;

			// ����ݸ���ڸ�ʱ��û�в���Ȩ�ޣ���������ת
			if (!BeaAppSettings.getMenuRight(tmp)) {
				XCToolkit.showToast("��ʱ���û�в���Ȩ�ޣ����迪ͨ�������Ա��ϵ��");
				return;
			}
			Map<String, String> object = new HashMap<String, String>();
			object.put("mainid", bo.getMainid());
			jsonRequest(20001, "ͨ�ýӿ�", JsonRemoteBO.reqParam(
					ConstDefined.SearchDraftDetail, object));
		}
	}

	List<DraftListBO> records = new ArrayList<DraftListBO>();

	@SuppressWarnings("unchecked")
	@Override
	public void onResponsSuccess(int actionTag, Object resObject) {
		try {
			if (actionTag < 10000 && null != resObject) {// �ݸ���б���������ɹ�
				List<DraftListBO> temp = XCJsonHelper.parseArray(
						resObject.toString(), DraftListBO.class);
				if (null != temp) {
					records.addAll(temp);
					adapter.notifyDataSetChanged();
				}
			} else if (actionTag >= 10000 && actionTag < 20000) {// ɾ���ݸ���ɹ�
				XCToolkit.showToast("�ݸ��ɾ���ɹ���");
				DraftListBO bo = records.get(actionTag - 10000);
				records.remove(actionTag - 10000);
				adapter.notifyDataSetChanged();

				List<String> files = new ArrayList<String>();
				if (null != bo.getPhotoZipUrl()) {
					files.add(bo.getPhotoZipUrl());
				}
				if (files.size() > 0) {
					jsonRequest(20000, "�ļ�ɾ��", JsonRemoteBO.reqParam(0, files));
				}
			} else if (actionTag == 20000) {// ɾ���ļ��ɹ�

			} else if (actionTag == 20001 && null != resObject) {// �ݸ���������ݻ��
				DraftListBO bo = XCJsonHelper.parseObject(resObject.toString(),
						DraftListBO.class);
				GrwdyHomeBO detail = bo.getListinfo().clone(false);

				detail.setMainid(bo.getMainid());
				detail.setIsCrmQueryed(null == bo.getIsCrmQueryed() ? "0" : bo
						.getIsCrmQueryed());
				detail.setIsReaded(bo.getIsReaded());
				if (null != bo.getIsFormatErrorFlag()
						&& "1".equals(bo.getIsFormatErrorFlag()))
					detail.setIsSMSValid(false);
				Message msg = new Message();
				msg.what = ConstDefined.GRWDYCreditActionTag;
				msg.obj = detail;
				sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void requestTableData(RemotePageConfig arg0) {
		Map<String, String> object = new HashMap<String, String>();
		// 00 ȫ�� 10 ƽ���ű� 11 ��ʱ��
		object.put("CBType", "00");
		jsonRequest(arg0.getCurrentPage(), "ͨ�ýӿ�", JsonRemoteBO.reqParam(
				ConstDefined.SearchDraftList, object, arg0));
		rpc = arg0;
	}

	@Override
	protected UITableViewAdapter setTableViewAdapter() {
		return new DraftListAdapter();
	}

}