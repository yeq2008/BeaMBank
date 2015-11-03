package com.bea.mbank.mytask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.android.uitableview.UITableView;
import com.android.uitableview.UITableViewAdapter;
import com.android.uitableview.ViewHolder;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.draft.CollecteListBO;
import com.cyg.viewinject.annotation.ViewInject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;
import xc.android.fragment.XCBaseListFragment;
import xc.android.remote.RemotePageConfig;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCToolkit;

public class CollecteListFragment extends XCBaseListFragment {
	// ������Ҫ��ʾ�Ĵ�������
	@ViewInject(R.id.mytask_collecteNumber)
	TextView collecteNum;
	// ������Ҫ��ʾ�Ĵ�������
	int cNum = 0;
	RemotePageConfig rpc;

	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_mytask_collecte, null);
	}

	@Override
	protected void onInitContentView(View arg0) {
		observeMessage(ConstDefined.cuishouDetailFragment);
		tableView = (UITableView) arg0
				.findViewById(R.id.mytask_collecte_UiTableView);
		findViewById(R.id.collecte_FindButton).setOnClickListener(
				new OnClickListener() {
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

		// ��ѯ����
		EditText etName;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			LayoutParams params = new LayoutParams(
					(int) (XCToolkit.getScreenWidth() * 0.5656f),
					(int) (XCToolkit.getScreenHeight() * 0.4925f));
			View contentView = mInflater.inflate(
					R.layout.pop_mytask_collecte_search, null);
			setContentView(contentView, params);

			etName = (EditText) findViewById(R.id.Search_editeName);
			adapter = new CollecteListAdapter();
			findViewById(R.id.mytask_search_confirmBtn).setOnClickListener(
					new android.view.View.OnClickListener() {
						@Override
						public void onClick(View v) {
							Map<String, String> object = new HashMap<String, String>();
							records = new ArrayList<CollecteListBO>();
							object.put("selCond", etName.getText().toString());
							if (null == rpc) {
								rpc = new RemotePageConfig();
								rpc.setCurrentPage(1);
								rpc.setPageSize(20);
							}
							jsonRequest(rpc.getCurrentPage(), "ͨ�ýӿ�",
									JsonRemoteBO.reqParam(
											ConstDefined.CollecteList, object,
											rpc));
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

	/**
	 * ListAdapter
	 * 
	 * @author PersonLi
	 * 
	 */
	class CollecteListAdapter extends UITableViewAdapter {

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
			return mInflater.inflate(R.layout.v_mytask_collecte_listitem, null);
		}

		@Override
		public void initViewHolder(int type, View itemView,
				ViewHolder viewHolder) {
			viewHolder.holderView(itemView
					.findViewById(R.id.collecte_listitem_type));
			viewHolder.holderView(itemView
					.findViewById(R.id.collecte_listitem_name));
			viewHolder.holderView(itemView
					.findViewById(R.id.collecte_listitem_phonenumber));
			viewHolder.holderView(itemView
					.findViewById(R.id.collecte_listitem_Grade));
			viewHolder.holderView(itemView
					.findViewById(R.id.collecte_listitem_Repayment));
			viewHolder.holderView(itemView
					.findViewById(R.id.collecte_listitem_id));
		}

		@Override
		public void initViewForRowAtIndexPath(IndexPath path, int type,
				ViewHolder viewHolder) {

			TextView loantype = (TextView) viewHolder
					.findViewById(R.id.collecte_listitem_type);
			TextView name = (TextView) viewHolder
					.findViewById(R.id.collecte_listitem_name);
			TextView phonenumber = (TextView) viewHolder
					.findViewById(R.id.collecte_listitem_phonenumber);
			TextView grade = (TextView) viewHolder
					.findViewById(R.id.collecte_listitem_Grade);
			TextView repayment = (TextView) viewHolder
					.findViewById(R.id.collecte_listitem_Repayment);
			TextView id = (TextView) viewHolder
					.findViewById(R.id.collecte_listitem_id);

			CollecteListBO cus = records.get(path.row);
			// ҵ��Ʒ��
			loantype.setText(cus.getBusinessType());
			// �ͻ�����
			name.setText(cus.getCustomerName());
			// �ͻ��绰
			phonenumber.setText(cus.getLenderPhoneNumber());
			// �弶������
			grade.setText(cus.getClassifyResultName());
			// ����Ӧ������
			repayment.setText(cus.getAllBalance());
			// ��ݱ��
			id.setText(cus.getSerialNo());
		}

		@Override
		public void itemSelectedAtIndexPath(IndexPath path) {
			CollecteListBO bo = records.get(path.row);
			pushFragmentController(new CollecteDetailFragment(bo == null ? ""
					: bo.getSerialNo()));
		}
	}

	List<CollecteListBO> records = new ArrayList<CollecteListBO>();

	@SuppressWarnings("unchecked")
	@Override
	public void onResponsSuccess(int actionTag, Object resObject) {
		if (null != resObject && resObject instanceof JSONArray) {
			List<CollecteListBO> temp = XCJsonHelper.parseArray(
					resObject.toString(), CollecteListBO.class);
			records.addAll(temp);
			adapter.notifyDataSetChanged();
		}
	}

	RemotePageConfig pageConfig;

	@Override
	protected void requestTableData(RemotePageConfig arg0) {
		pageConfig = arg0;
		requestData(pageConfig);
	}

	private void requestData(RemotePageConfig config) {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("selCond", "");
		jsonRequest(config.getCurrentPage(), "ͨ�ýӿ�", JsonRemoteBO.reqParam(
				ConstDefined.CollecteList, reqMap, config));
	}

	@Override
	protected UITableViewAdapter setTableViewAdapter() {
		return new CollecteListAdapter();
	}

	@Override
	public void onManagementPager(RemotePageConfig pager) {
		collecteNum.setText("" + pager.getTotalRow());
		super.onManagementPager(pager);
	}

	@Override
	public void onReceiveMessage(String msgkey, Object msgObject) {
		if (msgkey.equals(ConstDefined.cuishouDetailFragment)) {
			records = new ArrayList<CollecteListBO>();
			requestData(pageConfig);
		}
	}
}
