package com.bea.remote.models.grwdy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xc.android.remote.json.JsonModel;
import xc.android.utils.XCJsonHelper;

/**
 * @author cuiyuguo
 *
 */
@JsonModel
public final class GrwdyHomeBO implements Serializable {
	//crm是否查询
	private String isCrmQueryed = "0";
	//是否已经阅读章程
	private String isReaded = "1";
	//新的进件填写0(主键)
	private String mainid = "0";
		
	//借款人个人资料
	private BorrowerInforBO borrowerInfor;
	//是否必填，此是辅助页面用的，不会传输到服务器，因为此没有get/set方法
	public boolean isBorrowerInforRequired;
	//借款人职业资料
	private ProfessionInforBO professionInfor;
	//是否必填，此是辅助页面用的，不会传输到服务器，因为此没有get/set方法
	public boolean isProfessionInforRequired;
	//共同借款人个人资料
	private JointInforBO jointInfor;
	//是否必填，此是辅助页面用的，不会传输到服务器，因为此没有get/set方法
	public boolean isJointInforRequired;
	//联系人信息
	private ContactInforBO contactInfor;
	//是否必填，此是辅助页面用的，不会传输到服务器，因为此没有get/set方法
	public boolean isContactInforRequired;
	//借款人资产/银行/信用资料
	private CreditInforBO creditInfor;
	//是否必填，此是辅助页面用的，不会传输到服务器，因为此没有get/set方法
	public boolean isCreditInforRequired;
	//配偶信息
	private MateInforBO mateInfor;
	
	//是否必填，此是辅助页面用的，不会传输到服务器，因为此没有get/set方法
	public boolean isMateInforRequired;
	//申请贷款及费用信息
	private ApplyLoanInforBO applyLoanInfor;
	//是否必填，此是辅助页面用的，不会传输到服务器，因为此没有get/set方法
	public boolean isApplyLoanInforRequired;

	//影像采集数据
	private TakePhotosBO takePhotos;
	//是否必填，此是辅助页面用的，不会传输到服务器，因为此没有get/set方法
	public boolean isTakePhotosRequired; 
	//批量时个人基本资料
	private BasicInfoBO basicInfo;
	//是否必填，此是辅助页面用的，不会传输到服务器，因为此没有get/set方法
	public boolean isBasicInfoRequired;
		
	//是否是批量数据
	public boolean isPartRequiredData;
	//是否是平安信保
	public boolean isPinAnXinBaoData;

	//保单号(只有平安信保有)
	private String busiCode;
	
	//是否已经通过短信验证
	private boolean isSMSValid;
	//客户经理填写
	private CustManagerInputBO custManagerInput;
	//是否必填，此是辅助页面用的，不会传输到服务器，因为此没有get/set方法
	public boolean isCustManagerInputRequired;
	//原始数据，因为有reset事件，所以在刚刚进入编辑页面时保留一份拷贝
	public GrwdyHomeBO originHomeBO;
	
	public List<AddressBO> address;
	public List<AddressBO> getAddress() {
		List<AddressBO> addr = new ArrayList<AddressBO>();
		//从借款人资料中拷贝
		if (null != borrowerInfor && null != borrowerInfor.homeAddr) {
			addr.add(borrowerInfor.homeAddr);
		}
		//从借款人职业资料中拷贝
		if (null != professionInfor && null != professionInfor.compAddres) {
			if (null != borrowerInfor && null != borrowerInfor.homeAddr) {
				if ("1".equals(borrowerInfor.homeAddr.getMRAddr())) {
					professionInfor.compAddres.setMRAddr("2");
				} else {
					professionInfor.compAddres.setMRAddr("1");
				}
			}
			addr.add(professionInfor.compAddres);
		}
		//数据拷贝：临时对应＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
		if (isPinAnXinBaoData && null != borrowerInfor) {
			//这个必须深拷贝，否则在原基础上改会把源也给改掉
			String json = XCJsonHelper.toJSONString(borrowerInfor.homeAddr);
			try {
				AddressBO address = XCJsonHelper.parseObject(json, AddressBO.class);
				address.setMAddrName("永久地址");
				address.setMAddr("3");
				address.setMRAddr("2");
				addr.add(address);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return addr;
	}

	public void setAddress(List<AddressBO> address) {
		this.address = address;
	}

	private void resetRequired() {	
		isBorrowerInforRequired = (null != borrowerInfor && !isPartRequiredData) || isPinAnXinBaoData;
		isProfessionInforRequired = (null != professionInfor && !isPartRequiredData)||isPinAnXinBaoData;
		isContactInforRequired = (null != contactInfor && !isPartRequiredData)||isPinAnXinBaoData;
		isCreditInforRequired = (null != creditInfor && !isPartRequiredData)||isPinAnXinBaoData;
		isApplyLoanInforRequired = (null != applyLoanInfor && !isPartRequiredData)||isPinAnXinBaoData;
		isTakePhotosRequired = true;
		isMateInforRequired = null != mateInfor && !isPartRequiredData;
		isJointInforRequired = null != jointInfor && !isPartRequiredData;
		isBasicInfoRequired = null != basicInfo && isPartRequiredData && !isPinAnXinBaoData;
		isCustManagerInputRequired = null != custManagerInput;
	}
	
	private void trimRequiredData() {
		setBorrowerInfor(isBorrowerInforRequired?borrowerInfor:null);
		setBasicInfo(isBasicInfoRequired?basicInfo:null);
		setProfessionInfor(isProfessionInforRequired?professionInfor:null);
		setApplyLoanInfor(isApplyLoanInforRequired?applyLoanInfor:null);
		setMateInfor(isMateInforRequired?mateInfor:null);
		setCreditInfor(isCreditInforRequired?creditInfor:null);
		setContactInfor(isContactInforRequired?contactInfor:null);
		setJointInfor(isJointInforRequired?jointInfor:null);
		setTakePhotos(isTakePhotosRequired?takePhotos:null);
		setCustManagerInput(isCustManagerInputRequired?custManagerInput:null);
	}
	
	public boolean isRequired(Object o) {
		if (null == o) {
			return false;
		} else if (o.getClass() == BorrowerInforBO.class) {
			return isBorrowerInforRequired;
		} else if (o.getClass() == ProfessionInforBO.class) {
			return isProfessionInforRequired;
		} else if (o.getClass() == JointInforBO.class) {
			return isJointInforRequired;
		} else if (o.getClass() == ContactInforBO.class) {
			return isContactInforRequired;
		} else if (o.getClass() == CreditInforBO.class) {
			return isCreditInforRequired;
		} else if (o.getClass() == MateInforBO.class) {
			return isMateInforRequired;
		} else if (o.getClass() == ApplyLoanInforBO.class) {
			return isApplyLoanInforRequired;
		} else if (o.getClass() == TakePhotosBO.class) {
			return isTakePhotosRequired;
		} else if (o.getClass() == BasicInfoBO.class) {
			return isBasicInfoRequired;
		} else if (o.getClass() == CustManagerInputBO.class){
			return isCustManagerInputRequired;
		} else {
			return false;
		}
	}
	public BorrowerInforBO getBorrowerInfor() {
		return borrowerInfor;
	}
	public void setBorrowerInfor(BorrowerInforBO borrowerInfor) {
		this.borrowerInfor = borrowerInfor;
	}
	public ProfessionInforBO getProfessionInfor() {
		return professionInfor;
	}
	public void setProfessionInfor(ProfessionInforBO professionInfor) {
		this.professionInfor = professionInfor;
	}
	public JointInforBO getJointInfor() {
		return jointInfor;
	}
	public void setJointInfor(JointInforBO jointInfor) {
		this.jointInfor = jointInfor;
	}
	public ContactInforBO getContactInfor() {
		return contactInfor;
	}
	public void setContactInfor(ContactInforBO contactInfor) {
		this.contactInfor = contactInfor;
	}
	public CreditInforBO getCreditInfor() {
		return creditInfor;
	}
	public void setCreditInfor(CreditInforBO creditInfor) {
		this.creditInfor = creditInfor;
	}
	public MateInforBO getMateInfor() {
		return mateInfor;
	}
	public void setMateInfor(MateInforBO mateInfor) {
		this.mateInfor = mateInfor;
	}
	public ApplyLoanInforBO getApplyLoanInfor() {
		return applyLoanInfor;
	}
	public void setApplyLoanInfor(ApplyLoanInforBO applyLoanInfor) {
		this.applyLoanInfor = applyLoanInfor;
	}
	public TakePhotosBO getTakePhotos() {
		return takePhotos;
	}
	public void setTakePhotos(TakePhotosBO takePhotos) {
		this.takePhotos = takePhotos;
	}
	public BasicInfoBO getBasicInfo() {
		return basicInfo;
	}
	public void setBasicInfo(BasicInfoBO basicInfo) {
		this.basicInfo = basicInfo;
	}

	public boolean getIsPartRequiredData() {
		return isPartRequiredData;
	}

	public void setIsPartRequiredData(boolean isPartRequiredData) {
		this.isPartRequiredData = isPartRequiredData;
	}

	public boolean getIsPinAnXinBaoData() {
		return isPinAnXinBaoData;
	}

	public void setIsPinAnXinBaoData(boolean isPinAnXinBaoData) {
		this.isPinAnXinBaoData = isPinAnXinBaoData;
	}

	public CustManagerInputBO getCustManagerInput() {
		return custManagerInput;
	}

	public void setCustManagerInput(CustManagerInputBO custManagerInput) {
		this.custManagerInput = custManagerInput;
	}

	public boolean getIsSMSValid() {
		return isSMSValid;
	}
	public void setIsSMSValid(boolean isSMSValid) {
		this.isSMSValid = isSMSValid;
	}

	public String getMainid() {
		return mainid;
	}

	public void setMainid(String mainid) {
		this.mainid = mainid;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}
	
	public String getIsCrmQueryed() {
		return isCrmQueryed;
	}

	public void setIsCrmQueryed(String isCrmQueryed) {
		this.isCrmQueryed = isCrmQueryed;
	}

	public String getIsReaded() {
		return isReaded;
	}

	public void setIsReaded(String isReaded) {
		this.isReaded = isReaded;
	}

	private GrwdyHomeBO address() {
		if (null != address && address.size() >= 2) {
			if (null != getBorrowerInfor()) {
				getBorrowerInfor().homeAddr = address.get(0);
			}
			if (null != getProfessionInfor()) {
				getProfessionInfor().compAddres = address.get(1);
			}
		} else if (null != address && address.size() == 1) {
			if (null != getBorrowerInfor()) {
				getBorrowerInfor().homeAddr = address.get(0);
			}
		}
		address = null;
		return this;
	}
	//数据拷贝
	public GrwdyHomeBO clone(boolean needTrim) {
		GrwdyHomeBO tBo = clone(XCJsonHelper.toJSONString(this.address()));
		if (needTrim) {
			tBo.isMateInforRequired = isMateInforRequired && !isPartRequiredData;
			tBo.isJointInforRequired = isJointInforRequired && !isPartRequiredData;
			tBo.isCreditInforRequired = isCreditInforRequired && (!isPartRequiredData || isPinAnXinBaoData);
			tBo.trimRequiredData();
		}
		return tBo;
	}

	public static GrwdyHomeBO clone(String jsonString) {
		GrwdyHomeBO tBo = null;
		try {
			tBo = XCJsonHelper.parseObject(jsonString, GrwdyHomeBO.class);
			tBo.address();
			tBo.resetRequired();
			tBo.trimRequiredData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tBo;
	}
}
