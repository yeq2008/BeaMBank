package com.bea.mbank.paxb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.paxb.WaitToSelectTask;
import com.bea.remote.models.user.UserInfoBO;
import com.cyg.viewinject.annotation.ViewInject;

import xc.android.fragment.XCBaseHttpFragment;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCLog;

/**
 * 平安信保
 * @author fanglinhao
 */
public class PaxbHomeFragment extends XCBaseHttpFragment{

	@ViewInject(R.id.fgp_rg)
	RadioGroup radioGroup;
	@ViewInject(R.id.fgp_rb01)
	RadioButton radioButton1;
	@ViewInject(R.id.fgp_rb02)
	RadioButton radioButton2;
	List<WaitToSelectTask> dxlist;
	List<WaitToSelectTask> yxlist; 
	ImageButton imBtn;
	boolean currentpage = true;
	//平安信保我的任务列表条数
	public static int  WDRWlistNum = 0;
	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup group) {
		return inflater.inflate(R.layout.fm_grwdy_paxb, null);
	}

	@Override
	protected void onInitContentView(View view) {
		observeMessage(ConstDefined.paxbDxrwFragment);
		observeMessage(ConstDefined.paxbYxrwFragment);
		dxlist = new ArrayList<WaitToSelectTask>();
		yxlist = new ArrayList<WaitToSelectTask>();
		imBtn = (ImageButton) findViewById(R.id.paxb_reset);
		requestData();
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(R.id.fgp_rb01 == checkedId){
					currentpage =true;
					radioButton1.setTextColor(Color.BLACK);
					radioButton2.setTextColor(Color.WHITE);
					replaceFragmentController(R.id.gfp_fl, new PaxbDxrwFragment(dxlist));
				}else{
					currentpage = false;
					radioButton1.setTextColor(Color.WHITE);
					radioButton2.setTextColor(Color.BLACK);
					replaceFragmentController(R.id.gfp_fl, new PaxbWdrwFragment(yxlist));
				}
			}
		});
		imBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				dxlist = new ArrayList<WaitToSelectTask>();
				yxlist = new ArrayList<WaitToSelectTask>();
				requestData();
				
			}
		});
	}

	@Override
	public void onReceiveMessage(String msgkey, Object msgObject) {
		if(msgkey.equals(ConstDefined.paxbDxrwFragment)){
			yxlist.add((WaitToSelectTask)msgObject);
			//dxlist.remove((WaitToSelectTask)msgObject);
		}
		if(msgkey.equals(ConstDefined.paxbYxrwFragment)){
			dxlist.add((WaitToSelectTask)msgObject);
			//yxlist.remove((WaitToSelectTask)msgObject);
		}
	}

	private void requestData() {
		UserInfoBO userInfo = BeaAppSettings.getUserInfo();
		if(null == userInfo)
			return;
		Map<String, String> object = new HashMap<String, String>();
		object.put("UserID", userInfo.getEmployee_mark());
		object.put("OrgID", userInfo.getOrg_id());
		try {
			jsonRequest(101, "通用接口", JsonRemoteBO.reqParam(ConstDefined.PAXB_Command1, object));
		} catch (Exception e) {
			XCLog.e("平安信保数据获取异常", e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void onResponsSuccess(int actionTag, Object resObject) {
		if(101 == actionTag){
			List<WaitToSelectTask> list = XCJsonHelper.parseArray(resObject.toString(), WaitToSelectTask.class);
			if(null !=list && list.size()>0){
				for(WaitToSelectTask t : list){
					if("0".equals(t.getTaskflag())){
						dxlist.add(t);
					}else{
						yxlist.add(t);
					}
				}
			}
		}
		if (currentpage) {
			replaceFragmentController(R.id.gfp_fl, new PaxbDxrwFragment(dxlist));
		}else {
			replaceFragmentController(R.id.gfp_fl, new PaxbWdrwFragment(yxlist));
		}
		WDRWlistNum = yxlist.size()+1;
	}

}
