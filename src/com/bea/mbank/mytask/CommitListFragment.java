package com.bea.mbank.mytask;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.uitableview.UITableView;
import com.android.uitableview.UITableViewAdapter;
import com.android.uitableview.ViewHolder;
import com.bea.application.ConstDefined;
import com.bea.database.CommittedDataBO;
import com.bea.mbank.R;
import com.bea.mbank.util.FileUtils;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.draft.CommitListBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;
import com.bea.remote.models.grwdy.TakePhotosBO;
import com.cyg.viewinject.annotation.ViewInject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import xc.android.database.DbQueryManager;
import xc.android.file.XCFile;
import xc.android.file.XCFileInputStream;
import xc.android.fragment.XCBaseListFragment;
import xc.android.remote.RemotePageConfig;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCToolkit;

public class CommitListFragment extends XCBaseListFragment {

	@ViewInject(R.id.taskTitle)
	TextView tView; 
	@Override
	protected View onCreateView(LayoutInflater arg0, ViewGroup arg1) {
		return arg0.inflate(R.layout.fm_mytask_draftlist, null);
	}

	@Override
	protected void onInitContentView(View arg0) {
		tView.setText("待发");
		tableView = (UITableView) arg0.findViewById(R.id.uITableView1);  
		findViewById(R.id.searchview).setVisibility(View.INVISIBLE);
	}
	class CommitListAdapter extends UITableViewAdapter {
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
		public boolean isNoneSelected(IndexPath path) {
			return true;
		}

		@Override
		public void initViewHolder(int type, View itemView, ViewHolder viewHolder) {
			viewHolder.holderView(itemView.findViewById(R.id.no));
			viewHolder.holderView(itemView.findViewById(R.id.pname));
			viewHolder.holderView(itemView.findViewById(R.id.cardNum));
			viewHolder.holderView(itemView.findViewById(R.id.cusName));
			viewHolder.holderView(itemView.findViewById(R.id.moneyConut));
			viewHolder.holderView(itemView.findViewById(R.id.date));
			viewHolder.holderView(itemView.findViewById(R.id.deadline));
		}

		@Override
		public void initViewForRowAtIndexPath(IndexPath path, int type, ViewHolder viewHolder) {
			TextView no = (TextView) viewHolder.findViewById(R.id.no);
			TextView pname = (TextView) viewHolder.findViewById(R.id.pname);
			TextView cusname = (TextView) viewHolder.findViewById(R.id.cusName);
			TextView cardNum = (TextView) viewHolder.findViewById(R.id.cardNum);
			TextView moneyConut = (TextView) viewHolder.findViewById(R.id.moneyConut);
			TextView date = (TextView) viewHolder.findViewById(R.id.date);
			TextView deadline = (TextView) viewHolder.findViewById(R.id.deadline);

			no.setText((path.row+1)+"");
			CommitListBO cus = records.get(path.row);
			if ((cus.getCBType()).equals("10")) {
				pname.setText("平安信保消费贷款");
			}else if ((cus.getCBType()).equals("11")) {
				pname.setText("新时贷消费贷款");
			} 
			cusname.setText(cus.getCUName());
			cardNum.setText(cus.getCUNum());
			moneyConut.setText(cus.getCAmount());
			date.setText(cus.getCADate());
			deadline.setText(cus.getCLDate());
		}

		@Override
		protected int isSlideToDoSuport(IndexPath path) {
			return R.layout.v_todo_draft_send;
		}

		@Override
		protected void initSlideToDoEvent(View todoView) {
			todoView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					final IndexPath path = (IndexPath) v.getTag(UITableViewAdapter.SLIDEVIEWTag);
					if (records.get(path.row) instanceof CommittedDataBO) {//从本地草稿件读取的
						try {
							homeBO = XCJsonHelper.parseObject(records.get(path.row).getListinfo(), GrwdyHomeBO.class);
							saveData2Server(false, path.row);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {//从服务端读取的待发件
						// 发送待发件
						jsonRequest(10000 + path.row, "通用接口",
						JsonRemoteBO.reqParam(ConstDefined.OutgoingSending, records.get(path.row)));
					}
				}
			});
		}

		@Override
		public void itemSelectedAtIndexPath(IndexPath path) {}
	}

	List<CommitListBO> records = new ArrayList<CommitListBO>();
	@SuppressWarnings("unchecked")
	@Override
	public void onResponsSuccess(int actionTag, Object resObject) {
		try {
			if (30000 <= actionTag && actionTag < 40000) {// 保存数据成功
				XCToolkit.showToast("待发件提交成功！");
				CommitListBO itemBo = records.get(actionTag-30000);
				DbQueryManager.deleteDB(itemBo);
				records.remove(itemBo);
				adapter.notifyDataSetChanged();
			}
			if (20000 <= actionTag && actionTag < 3000) {// 保存图片成功
				TakePhotosBO photos = homeBO.getTakePhotos();
				String remotePath = ((JSONObject) resObject).optString("path");
				File zipFile = XCFile.create(zipPath);
				if (zipFile.exists()) {
					// 本地端的压缩包名改成和服务器给的名字一致，这样下次先判断本地有没有，没有再去下载
					String newPath = FileUtils.photoPath(FileUtils.getFileName(remotePath));
					zipFile.renameTo(XCFile.create(newPath));
				}
				photos.setZipFileUrl(remotePath);
				saveData2Server(true, actionTag-20000);
			} else if (actionTag >= 10000 && actionTag < 20000) {
				XCToolkit.showToast("待发件提交成功！");
				records.remove(actionTag-10000);
				adapter.notifyDataSetChanged();
			} else if (null != resObject && resObject instanceof JSONArray) {
				List<CommitListBO> temp = XCJsonHelper.parseArray(resObject.toString(), CommitListBO.class);
				records.addAll(temp);
				Collections.sort(records, new Comparator<CommitListBO>() {
					@Override
					public int compare(CommitListBO lhs, CommitListBO rhs) {
						return lhs.getCLDate().compareTo(rhs.getCLDate());
					}
				});
				adapter.notifyDataSetChanged();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void requestTableData(RemotePageConfig arg0) {
		Map<String, String> object = new HashMap<String, String>();
		object.put("CBType", "00");
		jsonRequest(0, "通用接口", JsonRemoteBO.reqParam(
				ConstDefined.SearchOutgoingList, object, arg0));
	}

	@Override
	protected UITableViewAdapter setTableViewAdapter() {
		return new CommitListAdapter();
	}

	GrwdyHomeBO homeBO = null;
	protected String zipPath = null;
	protected boolean savePhotos(int row) {
		// 有拍照信息，先把拍照的照片传到服务器
		TakePhotosBO photos = homeBO.getTakePhotos();
		String zipFileUrl = photos.getZipFileUrl();
		if (null != zipFileUrl && zipFileUrl.endsWith("@local")) {
			try {
				zipPath = zipFileUrl.replace("@local", "");
				//头里面携带信息，此包为压缩包，如有原来包则将原来包删除，以便于服务器节省硬盘资源
				Map<String, String> header = new HashMap<String, String>();
				header.put("isZipFile", "1");
				uploadFile(20000+row, "文件上传", XCFileInputStream.create(zipPath),
						XCFile.create(zipPath).length(), header);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	protected void saveData2Server(boolean sendData, int row) {
		// 先检查图片，有图片的打成压缩包放到服务器上去
		if (!sendData && savePhotos(row)) {
			return;
		}

		//数据拷贝
		int commId = homeBO.isPinAnXinBaoData  ? ConstDefined.PAXB_Command3 : 
			         homeBO.isPartRequiredData ? ConstDefined.SendPiMCollection :
			        	                         ConstDefined.SendMData;
		//此放置服务器提交到YRL时失败，服务器将其移到草稿件，此时还可以输入手机验证码重新验证及编辑
		homeBO.setIsSMSValid(false);
		jsonRequest(30000+row, "通用接口", JsonRemoteBO.reqParam(commId, homeBO));
	}
	
}