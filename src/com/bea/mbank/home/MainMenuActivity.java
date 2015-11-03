package com.bea.mbank.home;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.android.uitableview.UITableView;
import com.android.uitableview.UITableViewAdapter;
import com.android.uitableview.ViewHolder;
import com.bea.application.BeaAppSettings;
import com.bea.application.BeaApplication;
import com.bea.application.ConstDefined;
import com.bea.database.DbManager;
import com.bea.database.codemodel.MMP_PRODUCT_CATEGORY;
import com.bea.mbank.R;
import com.bea.remote.JsonRemoteBO;
import com.bea.remote.models.user.ModuelInfoBO;
import com.bea.remote.models.user.UserInfoBO;
import com.cyg.viewinject.annotation.ViewInject;
import com.cyg.viewinject.event.OnClick;

import xc.android.activity.XCBaseActivity;
import xc.android.activity.XCBaseHttpActivity;
import xc.android.application.XCApplication;
import xc.android.utils.XCToolkit;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author �����
 *  ���˵�
 */
public final class MainMenuActivity extends XCBaseActivity {
	@Override
	protected View onCreateView(LayoutInflater arg0) {
		return arg0.inflate(R.layout.ac_appmenu, null);
	}

	String mActionUrl = null;
	String mOldActionUrl = null;
	
	@ViewInject(R.id.userName)TextView username;
	@ViewInject(R.id.loginDate)TextView loginDate;
	@ViewInject(R.id.listview)UITableView menuView;
	@ViewInject(R.id.Textversion)TextView versionView;
	MenuViewAdaptor adaptor = null;
	List<ModuelInfoBO> userModues;
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onInitContentView() {
		// �������˵���Ϣ�¼�
		observeMessage(ConstDefined.MenuClickResKey);
		//�˵��������½���ʾ�汾��	
		versionView.setText("V"+BeaApplication.getPackageInfo().versionName);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		loginDate.setText(format.format(calendar.getTime()));

		UserInfoBO user = BeaAppSettings.getUserInfo();
		if (null != user) {
			username.setText(user.getReal_name());
		}
		
		userModues = DbManager.userModuels("0");
		//�˵���ֵ
		products = DbManager.productCategorys("0");
		adaptor = new MenuViewAdaptor();
		adaptor.setUITableView(menuView);
		menuView.setAdapter(adaptor);
		menuView.setOnItemClickListener(adaptor.itemClickListener);
		
        //ע���°汾֪ͨ�㲥  
		IntentFilter intentFilter = new IntentFilter();  
		intentFilter.addAction(ConstDefined.APPNewVersionActionKey);  
        LocalBroadcastManager.getInstance(XCApplication.getInstance()).
        		registerReceiver(mBroadcastReceiver, intentFilter); 
	}
	
	private Handler msgHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (1 == msg.what) {
				onHasNewAppVersion((String)msg.obj);
			}
			super.handleMessage(msg);
		}
	};
	
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){  
        @Override  
        public void onReceive(Context context, Intent intent) {  
        	String action = intent.getAction();  
        	if(action.equals(ConstDefined.APPNewVersionActionKey)){  
        		Message msg = new Message();
        		msg.what = 1;
        		msg.obj = intent.getStringExtra("versionInfo");
        		msgHandler.sendMessage(msg);
            }
        }   
    }; 
    
    @ViewInject(R.id.newVersionTip)TextView versionTip;
    JSONObject newAppVersion = null;
    private void onHasNewAppVersion(String verString) {
		try {
			newAppVersion = new JSONObject(verString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		versionTip.setVisibility(View.VISIBLE);
		versionTip.setText("���°汾�ˣ��Ͻ����°ɣ�");
	}
    @OnClick(R.id.newVersionTip)
    public void onUpdateNewVersion(View v) {
    	sendMessage(ConstDefined.APPNewVersionActionKey, null);
    }
    
	@OnClick(R.id.setting)
	public void onSettingButtonEvent(View v) {
		Message msg = new Message();
		msg.what = ConstDefined.SettingTag;
		sendMessage(ConstDefined.APPHomeChangeActionKey, msg);
	}
	
	List<MMP_PRODUCT_CATEGORY> products = new ArrayList<MMP_PRODUCT_CATEGORY>();
	class MenuViewAdaptor extends UITableViewAdapter {
		@Override
		public int sectionCount() {
			return null != userModues ? userModues.size() : 0;
		}
		@Override
		public boolean hasSectionTitle(int section) {
			return true;
		}
		@Override
		public int rowsInSection(int section) {
			ModuelInfoBO mInfo = userModues.get(section);
			if (mInfo.isStatue()) {
				//��Ʒģ��
				if (ConstDefined.productUrl.equals(mInfo.getUrl())) {
					return 1+products.size();
				}
				//��Ʒģ��֮���ģ��
				else {
					return getChildModues(mInfo.getModule_id()).size();
				}
			}
			return 0;
		}

		@Override
		public int viewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(IndexPath path) {
			if (path.row < 0) {//���Ⲽ������
				return 0;
			} else {
				return 1;
			}
		}

		@Override
		public View viewWithType(int type) {
			if (0 == type) {
				View v = mInflater.inflate(R.layout.v_appmenu_parent, null);
				return v;
			} else {
				View v = mInflater.inflate(R.layout.v_appmenu_child, null);
				v.setPadding(0, 0, 0, 0);
				return v;
			}
		}

		@Override
		public void initViewHolder(int type, View itemView, ViewHolder viewHolder) {
			if (0 == type) {
				viewHolder.holderView(itemView.findViewById(R.id.ac_appmenu_parent_iv));
				viewHolder.holderView(itemView.findViewById(R.id.ac_appmenu_parent_tv));
			} else {
				viewHolder.holderView(itemView.findViewById(R.id.ac_appmenu_child_tv01));
				viewHolder.holderView(itemView.findViewById(R.id.ac_appmenu_child_tv02));
			}
		}

		@Override
		public void initViewForHeaderInSection(int section, int type, ViewHolder viewHolder) {
			ImageView imageView = (ImageView)viewHolder.findViewById(R.id.ac_appmenu_parent_iv);
			TextView titleView = (TextView)viewHolder.findViewById(R.id.ac_appmenu_parent_tv);
			int iconId = 0;
			ModuelInfoBO mInfo = userModues.get(section);
			titleView.setSelected(mInfo.isStatue());
			if (ConstDefined.mytaskUrl.equals(mInfo.getUrl())) {//�ҵ�����
				titleView.setText(mInfo.getModule_name());
				iconId = mInfo.isStatue() ? R.drawable.ac_appmenu_parent_wdrw_c:
					                           R.drawable.ac_appmenu_parent_wdrw_o;
				imageView.setImageResource(iconId);
			} else if (ConstDefined.productUrl.equals(mInfo.getUrl())) {//��Ʒ����
				titleView.setText(mInfo.getModule_name());
				iconId = mInfo.isStatue() ? R.drawable.ac_appmenu_parent_cpzx_c:
                                               R.drawable.ac_appmenu_parent_cpzx_o;
                imageView.setImageResource(iconId);
			} else if (ConstDefined.loanUrl.equals(mInfo.getUrl())) {//���۴���ҵ��
				titleView.setText(mInfo.getModule_name());
				iconId = mInfo.isStatue() ? R.drawable.ac_appmenu_parent_lsdyw_c:
                                               R.drawable.ac_appmenu_parent_lsdyw_o;
                imageView.setImageResource(iconId);
			} else if (ConstDefined.utilsUrl.equals(mInfo.getUrl())) {//��������
				titleView.setText(mInfo.getModule_name());
				iconId = mInfo.isStatue() ? R.drawable.ac_appmenu_parent_fzgn_c:
                                               R.drawable.ac_appmenu_parent_fzgn_o;
                imageView.setImageResource(iconId);
			}
		}
		@Override
		public void initViewForRowAtIndexPath(IndexPath path, int type, ViewHolder viewHolder) {
			TextView titleView = (TextView)viewHolder.findViewById(R.id.ac_appmenu_child_tv01);
			ModuelInfoBO mInfo = userModues.get(path.section);
			//��Ʒģ��
			if (ConstDefined.productUrl.equals(mInfo.getUrl())) {
				if (0 == path.row) {//��ѡ���ͻ��˱�����ӣ������û�Ȩ�޿���
					titleView.setText("��ѡ");
				} else {
					titleView.setText(products.get(path.row-1).getCATEGORY_NAME());
				}
				if ((ConstDefined.productUrl+path.row).equals(mActionUrl)) {
					((View)titleView.getParent()).setBackgroundColor(0x19ffffff);
				} else {
					((View)titleView.getParent()).setBackgroundColor(0);
				}
			}
			//��Ʒģ��֮���ģ��
			else {
				List<ModuelInfoBO> l = getChildModues(mInfo.getModule_id());
				titleView.setText(l.get(path.row).getModule_name());
				if (l.get(path.row).getUrl().equals(mActionUrl)) {
					((View)titleView.getParent()).setBackgroundColor(0x19ffffff);
				} else {
					((View)titleView.getParent()).setBackgroundColor(0);
				}
			}
		}

		@Override
		public void sectionHeaderSelected(int section) {
			ModuelInfoBO mInfo = userModues.get(section);
			for (ModuelInfoBO m : userModues) {
				m.setStatue(m == mInfo && !m.isStatue());
			}

			//��Ʒģ��
			if (ConstDefined.productUrl.equals(mInfo.getUrl())) {
				//��Ϊ���ͬ�����ں�̨���еģ��и��¼��ӳٹ��̣��п����û�����ȽϿ죬����͵�¼�ˣ�����ʱ���ͬ�����
				//���ܻ�û�д���������Ϊ���������⣬����ÿ�ε㿪��Ʒ���ķ���ʱ�����¶�ȡ��ǰ�������
				products = DbManager.productCategorys("0");
			}
			adaptor.notifyDataSetChanged();
		}
		
		@Override
		public void itemSelectedAtIndexPath(IndexPath path) {
			ModuelInfoBO mInfo = userModues.get(path.section);
			
			//�ж��Ƿ���Ե��
			if (1 == mInfo.getEnable()) {
				MenueMessage msg = null;
				if (ConstDefined.productUrl.equals(mInfo.getUrl())) {//��Ʒ���ģ��������ݹ�ȥ
					msg = new MenueMessage(ConstDefined.productUrl+path.row, mOldActionUrl);
					if (path.row>0) {
						msg.msgObject = products.get(path.row-1);
					}
				} else {
					ModuelInfoBO subInfo = getChildModues(mInfo.getModule_id()).get(path.row);
					 if (!BeaAppSettings.getMenuRight(subInfo.getUrl())) {
						XCToolkit.showToast("��ǰʱ�β��ʴ˴β�����");
						return;
					} else {
						msg = new MenueMessage(subInfo.getUrl(), mOldActionUrl);
					}
				}
				sendMessage(ConstDefined.MenuClickReqKey, msg);
			} else {
				XCToolkit.showToast("�����˻�û�и���Ĳ���Ȩ�ޣ����迪ͨ�������Ա��ϵ��");
			}
		}
		
		private void refreshMenuStatus(Object msgObject) {
			MenueMessage msg = (MenueMessage)msgObject;
			mActionUrl = mOldActionUrl = msg.actionUrl;
			
			String urlString = null;
			if (null == mActionUrl) {	
			} else if (mActionUrl.contains(ConstDefined.productUrl)) {
				urlString = ConstDefined.productUrl;
			} else if (mActionUrl.equals(ConstDefined.mytaskDraftUrl)||
					   mActionUrl.equals(ConstDefined.mytaskOutgoUrl)||
					   mActionUrl.equals(ConstDefined.mytaskCollectUrl)) {
				urlString = ConstDefined.mytaskUrl;
			} else if (mActionUrl.equals(ConstDefined.loanGRWDYUrl)||
					   mActionUrl.equals(ConstDefined.loanPAXBUrl)) {
				urlString = ConstDefined.loanUrl;
			} else if (mActionUrl.equals(ConstDefined.utilsCalculatorUrl)) {
				urlString = ConstDefined.utilsUrl;
			}

			for (ModuelInfoBO m : userModues) {
				m.setStatue(m.getUrl().equals(urlString));
			}
			adaptor.notifyDataSetChanged();
		}
	}

	public class MenueMessage {
		public Object msgObject;
		public String actionUrl;
		public String actionOldUrl;
		public MenueMessage(String actionUrl, String actionOldUrl) {
			this.actionUrl = actionUrl;
			this.actionOldUrl = actionOldUrl;
		}
	}
	
	public static MenueMessage menueMessage(String actionUrl) {
		return new MainMenuActivity().createMessage(actionUrl);
	}
	public MenueMessage createMessage(String actionUrl) {
		return new MenueMessage(actionUrl, null);
	}
	
	@Override
	public void onReceiveMessage(String mkey, Object msgObject) {
		adaptor.refreshMenuStatus(msgObject);
	}
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if(keyCode == KeyEvent.KEYCODE_BACK){  
        	return false;
        }
        return super.onKeyDown(keyCode, event); 
	}

	
	private Map<String, List<ModuelInfoBO>> moduels = new HashMap<String, List<ModuelInfoBO>>();
	private List<ModuelInfoBO> getChildModues(String moduleId) {
		if (moduels.containsKey(moduleId)) {
			return moduels.get(moduleId);
		} else {
			List<ModuelInfoBO> l = DbManager.userModuels(moduleId);
			moduels.put(moduleId, l);
			return l;
		}
	}
}
