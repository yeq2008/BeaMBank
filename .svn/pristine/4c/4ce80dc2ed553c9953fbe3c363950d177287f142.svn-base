package com.bea.mbank.edit.step1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;
import com.bea.application.BeaAppSettings;
import com.bea.application.ConstDefined;
import com.bea.mbank.R;
import com.bea.mbank.edit.crm.CRMViewFragment;
import com.bea.mbank.edit.step1.PersonInforDialog.personInfoDialogListener;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.crm.CrmCustDetail;
import com.bea.remote.models.crm.CrmPersonInfo;
import com.bea.remote.models.grwdy.BasicInfoBO;
import com.bea.remote.models.grwdy.BorrowerInforBO;
import com.bea.remote.models.grwdy.GrwdyHomeBO;

import xc.android.fragment.XCBaseHttpFragment;
import xc.android.utils.XCJsonHelper;
import xc.android.utils.XCLog;
import xc.android.utils.XCToolkit;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author fanglinhao
 *	黑名单
 */
public final class BlackListPanelFragment extends XCBaseHttpFragment {
	@ViewInject(R.id.listview)
	ListView listView;
	List<String> list;
	Map<String, Object> map;
	@Override
	protected View onCreateView(LayoutInflater inflater, ViewGroup group) {
		return inflater.inflate(R.layout.fm_grwdy_blacklist_panel, null);
	}

	@Override
	protected void onInitContentView(View arg0) {
		list = new ArrayList<String>();
		list.add("NDS监察信息");
		list.add("警示信息");
		list.add("关联信息");
		list.add("附属信息");
		PreviewAdapter adapter = new PreviewAdapter();
		listView.setAdapter(adapter);
	}
	
	/**
	 * 下一步
	 */
	@OnClick(R.id.pannelNext)
	public void nextStep(View view){
		crmQuery();
	}
	
	@OnClick(R.id.fgbp_iv01)
	public void returnback(View view){
		popupTopFragmentController();
	}
	
	private void crmQuery(){
		BeaAppSettings.instance();
		if(null != BeaAppSettings.blankListMap){
			BeaAppSettings.instance();
			map = BeaAppSettings.blankListMap;
			jsonRequest(0, "通用接口", JsonRemoteBO.reqParam(4001, map),Integer.MAX_VALUE);
		}
	}
	
	class PreviewAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = mInflater.inflate(R.layout.v_grwdy_preview_item,null);
			TextView textView = (TextView) convertView.findViewById(R.id.vgpi_tv01);
			textView.setText(list.get(position));
			convertView.findViewById(R.id.vgpi_v01).setVisibility(View.GONE);
			return convertView;
		}
	}

	@Override
	public void onResponsSuccess(int actionTag, Object obj) {
		try {
			JSONObject o = (JSONObject)obj;
			String org = o.get("org").toString();
			String object = o.get("object").toString();
			if("10".equals(org)){
				CrmCustDetail codeBO = XCJsonHelper.parseObject(object.toString(), CrmCustDetail.class);
				if(null == codeBO){
					XCToolkit.showToast("查无信息");
					return;
				}
				codeBO.setCH_NAME(map.get("custom_name").toString());//名称
				codeBO.setGOVE_IDEN_NUM(map.get("global_id").toString());//证件号
				codeBO.setIDEN_NUM_TYPE(map.get("global_type").toString());//证件类型
				pushFragmentController(new CRMViewFragment(codeBO,map));
			}else if("11".equals(org)){
				CrmPersonInfo info = XCJsonHelper.parseObject(object.toString(), CrmPersonInfo.class);
				if(null != info && null != info.getCheckResult()){
					if("02".equals(info.getCheckResult())){
						XCToolkit.showToast("身份号码与姓名不匹配");
					} else if("03".equals(info.getCheckResult())){
						XCToolkit.showToast("身份号码不存在");
					} else if("04".equals(info.getCheckResult())){
						XCToolkit.showToast("查询出错");
					} else{//00 01
						info.setName(map.get("custom_name").toString());//名称
						info.setCreditCard(map.get("global_id").toString());//证件号
						info.setNation(map.get("global_con").toString());//国家
						info.setCardType(map.get("global_type").toString());//证件类型
						PersonInforDialog inforDialog = new PersonInforDialog(getActivity(),info);
						inforDialog.setDialogListener(new personInfoDialogListener() {
							@Override
							public void click(String flag, String card,String country,String cardType,String username) {
								GrwdyHomeBO detail = BeaAppSettings.instance().mGrwdyHomeBO;
								BeaAppSettings.setOriginData(null);
								if(null != flag && "query".equals(flag)){
									String xin = "";
									String ming = "";
									if(null != username && username.length()>1){
										try{
											xin = username.substring(0, 1);
											ming = username.substring(1, username.length());
										}catch(Exception e){}
									}
									
									if(!detail.isPartRequiredData){
										if(null == detail.getBorrowerInfor())
											detail.setBorrowerInfor(new BorrowerInforBO());
										detail.getBorrowerInfor().setMCNum(card);
										detail.getBorrowerInfor().setMCountry(country);
										detail.getBorrowerInfor().setMCType(cardType);
										detail.getBorrowerInfor().setMFName(xin);
										detail.getBorrowerInfor().setMLName(ming);
									}else{
										if(null == detail.getBasicInfo())
											detail.setBasicInfo(new BasicInfoBO());
										detail.getBasicInfo().setLCNum(card);
										detail.getBasicInfo().setLCType(cardType);
										detail.getBasicInfo().setLCC(country);
										detail.getBasicInfo().setLCFName(xin);
										detail.getBasicInfo().setLCLName(ming);
									}
									
								}
								Message msg = new Message();
								msg.what = ConstDefined.GRWDYCreditActionTag;
								msg.obj = detail.clone(true);
								sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
							}
						});
						inforDialog.show();
					}
				}
			}
		} catch (Exception e) {
			XCLog.e("error", "crm响应错误："+e.getMessage());
		}
	}
}
