package com.bea.mbank.paxb;

import java.util.List;

import org.json.JSONObject;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.android.uitableview.UITableView;
import com.android.uitableview.UITableViewAdapter;
import com.android.uitableview.ViewHolder;
import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import com.bea.remote.models.paxb.WaitToSelectTask;
import com.bea.remote.models.user.UserInfoBO;

import xc.android.fragment.XCBaseListFragment;
import xc.android.remote.RemotePageConfig;
import xc.android.utils.XCToolkit;

/**
 * 平安信保 我的任务
 * @author fanglinhao
 */
public class PaxbWdrwFragment extends XCBaseListFragment{
	
	List<WaitToSelectTask> list;
	int currentIndex = -1;
	public PaxbWdrwFragment(List<WaitToSelectTask> yxlist) {
		this.list = yxlist;
	}

	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup group) {
		return inflater.inflate(R.layout.fm_grwdy_paxb_wdrw, null);
	}

	@Override
	protected void onInitContentView(View view) {
		tableView = (UITableView)view.findViewById(R.id.fgpw_lv);
	}
	
	@Override
	protected UITableViewAdapter setTableViewAdapter() {
		return new MyAdaptor();
	}
	
	class MyAdaptor extends UITableViewAdapter{
		@Override
		public int rowsInSection(int section) {
			return list.size();
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
			return mInflater.inflate(R.layout.fm_grwdy_paxb_item, null);
		}
	
		@Override
		public void initViewHolder(int type, View itemView, ViewHolder viewHolder) {
			viewHolder.holderView(itemView.findViewById(R.id.no));
			viewHolder.holderView(itemView.findViewById(R.id.fgpi_tv_policyno));
			viewHolder.holderView(itemView.findViewById(R.id.fgpi_tv_idtypename));
			viewHolder.holderView(itemView.findViewById(R.id.fgpi_tv_id));
			viewHolder.holderView(itemView.findViewById(R.id.fgpi_tv_custname));
			viewHolder.holderView(itemView.findViewById(R.id.fgpi_tv_applycurrency));
			viewHolder.holderView(itemView.findViewById(R.id.fgpi_tv_applamt));
		}
	
		@Override
		public void initViewForRowAtIndexPath(IndexPath path, int type,
				ViewHolder viewHolder) {
			TextView no = (TextView) viewHolder.findViewById(R.id.no);
			TextView tvpolicyno = (TextView) viewHolder.findViewById(R.id.fgpi_tv_policyno);
			TextView tvidtypename = (TextView) viewHolder.findViewById(R.id.fgpi_tv_idtypename);
			TextView tvid = (TextView) viewHolder.findViewById(R.id.fgpi_tv_id);
			TextView tvcustname = (TextView) viewHolder.findViewById(R.id.fgpi_tv_custname);
			TextView tvapplycurrency = (TextView) viewHolder.findViewById(R.id.fgpi_tv_applycurrency);
			TextView tvapplamt = (TextView) viewHolder.findViewById(R.id.fgpi_tv_applamt);
			
			no.setText((path.row+1)+"");
			final WaitToSelectTask myTest =  list.get(path.row);
			tvpolicyno.setText("保单编号："+myTest.getPolicyno());
			
			if ((myTest.getIDType()).equals("1")) {
				tvidtypename.setText("境内居民身份证");
			}else {
				tvidtypename.setText(myTest.getIDType());
			}
			tvid.setText(myTest.getID());
			tvcustname.setText(myTest.getCustName());
			if ((myTest.getAPPLYCURRENCY()).equals("01")) {
				tvapplycurrency.setText("元");
			}else {
				tvapplycurrency.setText(myTest.getAPPLYCURRENCY());
			}
			tvapplamt.setText(myTest.getApplAmt());
		}
		
		@Override
		protected void initSlideToDoEvent(View todoView) {
			todoView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					final IndexPath path = (IndexPath)v.getTag(UITableViewAdapter.SLIDEVIEWTag);
					WaitToSelectTask task = list.get(path.row);
					UserInfoBO user = BeaAppSettings.getUserInfo();
					if (null != user) {
						jsonRequest(path.row+10000, "通用接口", 
						JsonRemoteBO.reqParam(ConstDefined.PAXB_TaskRecede, task));
					} else {
						XCToolkit.showToast("请先登录！");
					}
				}
			});
		}
		
		@Override
		public void itemSelectedAtIndexPath(IndexPath path) {
			currentIndex = path.row;
			jsonRequest(path.row, "通用接口", JsonRemoteBO.reqParam(ConstDefined.PAXB_Command2, 
					list.get(path.row)));
		}

		@Override
		protected int isSlideToDoSuport(IndexPath path) {
			return R.layout.v_todo_paxb_yxrw;
		}
	}
	
	@Override
	public void onResponsSuccess(int tag, Object obj) {
		if(tag >= 10000){
			WaitToSelectTask tmp = list.get(tag-10000);
			sendMessage(ConstDefined.paxbYxrwFragment, tmp);
			list.remove(tmp);
			PaxbHomeFragment.WDRWlistNum-=1;
			adapter.notifyDataSetChanged();
		}else if(tag < 10000){
			WaitToSelectTask tmp = list.get(tag);
			if (((JSONObject)obj).has("listinfo")) {
				try {
					obj = ((JSONObject)obj).get("listinfo");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			GrwdyHomeBO homeBO  = GrwdyHomeBO.clone(obj.toString());
			homeBO.setIsPinAnXinBaoData(true);
			homeBO.setBusiCode(tmp.getPolicyno());
			Message msg = new Message();
			msg.what = ConstDefined.GRWDYCreditActionTag;
			msg.obj = homeBO;
			msg.arg1 = 1;
			sendMessage(ConstDefined.APPHomeChangeActionKey,msg);
		}
	}

	@Override
	protected void requestTableData(RemotePageConfig arg0) {}
}
