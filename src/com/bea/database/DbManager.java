/**
 * 
 */
package com.bea.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xc.android.database.DbQueryManager;
import xc.android.sqlite.sqlite.Selector;

import com.bea.database.codemodel.MMP_LOAN_RATE;
import com.bea.database.codemodel.MMP_PRODUCT;
import com.bea.database.codemodel.MMP_PRODUCT_ATT;
import com.bea.database.codemodel.MMP_PRODUCT_CATEGORY;
import com.bea.database.codemodel.SYS_AC_AREA;
import com.bea.database.codemodel.SYS_AC_ORG;
import com.bea.database.codemodel.YRL_BASIC_DATA;
import com.bea.remote.models.user.ModuelInfoBO;

/**
 * @author cuiyuguo
 * 
 */
public class DbManager {
	// 获得总管理表数据
	@SuppressWarnings("unchecked")
	public static List<SqliteSynBO> getSynRecords() {
		return (List<SqliteSynBO>) DbQueryManager.searchDB(SqliteSynBO.class,
				null);
	}

	// 获得码表数据
	@SuppressWarnings("unchecked")
	public static List<YRL_BASIC_DATA> codeDatas(String type) {
		if (null != type) {
			Selector selector = Selector.from(YRL_BASIC_DATA.class);
			selector.where("OPR_TYPE", "=", "Y");
			selector.and("DATA_TYPE", "=", type);
			return new DbArrayList(selector);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<YRL_BASIC_DATA> codeAyncDatas(String type) {
		if (null != type) {
			Selector selector = Selector.from(YRL_BASIC_DATA.class);
			selector.where("OPR_TYPE", "=", "Y");
			selector.and("DATA_TYPE", "=", type);

			return (List<YRL_BASIC_DATA>) DbQueryManager.searchDB(selector);
		} else {
			return null;
		}
	}

	// 精确获得码表数据单元
	public static YRL_BASIC_DATA codeData(String type, String NO) {
		if (null != type && null != NO) {
			Map<String, Object> where = new HashMap<String, Object>();
			where.put("DATA_TYPE", type);
			where.put("OPR_TYPE", "Y");
			where.put("NO", NO);

			List<?> l = DbQueryManager.searchDB(YRL_BASIC_DATA.class, where);
			if (null != l && l.size() > 0) {
				return (YRL_BASIC_DATA) l.get(0);
			}
		}
		return null;
	}

	// 精确获得码表数据单元名称
	public static String codeDataName(String type, String NO) {
		YRL_BASIC_DATA d = codeData(type, NO);
		if (null != d && null != d.getNAME()) {
			return d.getNAME();
		}
		return "";
	}

	// 获得省市区码表数据
	@SuppressWarnings("unchecked")
	public static List<SYS_AC_AREA> zoneDatas(String parentId) {
		Selector selector = Selector.from(SYS_AC_AREA.class);
		selector.where("OPR_TYPE", "=", "Y");
		if (null != parentId) {
			selector.and("PARENT_ID", "=", parentId);
		} else {
			selector.and("PARENT_ID", "=", "0");
		}
		selector.orderBy("VIEW_ORDER", false);

		return (List<SYS_AC_AREA>) DbQueryManager.searchDB(selector);
	}

	@SuppressWarnings("unchecked")
	public static List<SYS_AC_AREA> zoneData(String zoneNo) {
		if (null == zoneNo || zoneNo.length() < 6) {
			return null;
		}
		List<SYS_AC_AREA> list = new ArrayList<SYS_AC_AREA>();
		try {
			// 先查省
			Selector selector = Selector.from(SYS_AC_AREA.class);
			selector.where("OPR_TYPE", "=", "Y");
			selector.and("PARENT_ID", "=", "0");
			selector.and("AREA_NO", "=", zoneNo.substring(0, 2));
			selector.limit(1);
			List<SYS_AC_AREA> ll1 = (List<SYS_AC_AREA>) DbQueryManager.searchDB(selector);
			if(null == ll1 || ll1.size() == 0)
				return null;
			SYS_AC_AREA tmp = null;
			tmp = ll1.get(0);
			list.add(tmp);
				
			// 再查市
			selector = Selector.from(SYS_AC_AREA.class);
			selector.where("OPR_TYPE", "=", "Y");
			selector.and("PARENT_ID", "=", tmp.getAREA_ID());
			selector.and("AREA_NO", "=", zoneNo.substring(2, 4));
			selector.limit(1);
			List<SYS_AC_AREA> ll2 = (List<SYS_AC_AREA>) DbQueryManager.searchDB(selector);
			if(null == ll2 || ll2.size() == 0)
				return list;
			tmp = ll2.get(0);
			list.add(tmp);
			// 再查区
			selector = Selector.from(SYS_AC_AREA.class);
			selector.where("OPR_TYPE", "=", "Y");
			selector.and("PARENT_ID", "=", tmp.getAREA_ID());
			selector.and("AREA_NO", "=", zoneNo.substring(4, 6));
			selector.limit(1);
			List<SYS_AC_AREA> ll3 = (List<SYS_AC_AREA>) DbQueryManager.searchDB(selector);
			if(null == ll3 || ll3.size() == 0)
				return list;
			tmp = ll3.get(0);
			list.add(tmp);
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String zoneName(String no){
		List<SYS_AC_AREA> list = zoneData(no);
		if(null != list){
			String tmp = "";
			for(SYS_AC_AREA o : list){
				tmp += null==o.getAREA_NAME()?"":o.getAREA_NAME();
			}
			return tmp;
		}
		return "";
	}

	/**
	 * 根据机构id获取机构名称
	 * 
	 * @param orgid
	 * @return
	 */
	public static String getOrgNameById(String orgid) {
		if (null == orgid) {
			return "";
		}
		Selector selector = Selector.from(SYS_AC_ORG.class);
		selector.where("ORG_ID", "=", orgid);
		selector.and("OPR_TYPE", "=", "Y");
		selector.limit(1);
		List<SYS_AC_ORG> orgs = (List<SYS_AC_ORG>) DbQueryManager
				.searchDB(selector);
		if (null != orgs && orgs.size() > 0) {
			return orgs.get(0).getORG_NAME();
		} else {
			return "";
		}
	}
	
	public static List<SYS_AC_ORG> getOrgs(){
		Selector selector = Selector.from(SYS_AC_ORG.class);
		selector.where("OPR_TYPE", "=", "Y");
		return (List<SYS_AC_ORG>) DbQueryManager.searchDB(selector);
	}

	// 添加收藏产品的标志
	public static void addProductCollect(Object obj) {
		DbQueryManager.saveOrUpdate(obj);
	}

	// 获得所有的产品
	@SuppressWarnings("unchecked")
	public static List<MMP_PRODUCT> productCollects(int COLLECT_FLAG) {
		Selector selector = Selector.from(MMP_PRODUCT.class);
		selector.where("OPR_TYPE", "=", "Y");
		selector.and("COLLECT_FLAG", "=", COLLECT_FLAG);

		return (List<MMP_PRODUCT>) DbQueryManager.searchDB(selector);
	}

	// 获得产品的所有分类信息(0:;cate)
	@SuppressWarnings("unchecked")
	public static List<MMP_PRODUCT_CATEGORY> productCategorys(String parentId) {
		Selector selector = Selector.from(MMP_PRODUCT_CATEGORY.class);
		selector.where("OPR_TYPE", "=", "Y");
		selector.and("PARENT_ID", "=", parentId);
		selector.orderBy("VIEW_ORDER", false);
		return (List<MMP_PRODUCT_CATEGORY>) DbQueryManager.searchDB(selector);
	}

	// 获得产品分类下的所有产品列表
	@SuppressWarnings("unchecked")
	public static List<MMP_PRODUCT> products(String CATEGORY_ID) {
		if (null != CATEGORY_ID) {
			Selector selector = Selector.from(MMP_PRODUCT.class);
			selector.where("OPR_TYPE", "=", "Y");
			if (null != CATEGORY_ID) {
				selector.and("CATEGORY_ID", "=", CATEGORY_ID);
			}

			return (List<MMP_PRODUCT>) DbQueryManager.searchDB(selector);
		} else {
			return null;
		}
	}

	// 获得产品的所有附件
	@SuppressWarnings("unchecked")
	public static List<MMP_PRODUCT_ATT> productATT(String PRODUCT_ID) {
		if (null != PRODUCT_ID) {
			Selector selector = Selector.from(MMP_PRODUCT_ATT.class);
			selector.where("OPR_TYPE", "=", "Y");
			if (null != PRODUCT_ID) {
				selector.and("PRODUCT_ID", "=", PRODUCT_ID);
			}

			return (List<MMP_PRODUCT_ATT>) DbQueryManager.searchDB(selector);
		} else {
			return null;
		}
	}

	// 获得利率
	/*@SuppressWarnings("unchecked")
	public static MMP_LOAN_RATE getLoanRate(String RATE_ID) {
		if (RATE_ID == null)
			return null;

		Selector selector = Selector.from(MMP_LOAN_RATE.class);
		selector.where("RATENO", "=", RATE_ID);	
		selector.limit(1);
		List<MMP_LOAN_RATE> rates = (List<MMP_LOAN_RATE>) DbQueryManager.searchDB(selector);
		if (rates != null)
			return rates.get(0);
		else 
			return null;

	}*/
	
	//根据贷款期限获取利率
//	public static String getRateByLimit(String limit){
//		if(null == limit || "".equals(limit))
//			return "";
//		Selector selector = Selector.from(MMP_LOAN_RATE.class);
//		selector.where("MINTERM", "<", limit);	
//		selector.and("MAXTERM", ">=", limit);
//		selector.and("OPR_TYPE", "=", "Y");
//		selector.limit(1);
//		List<MMP_LOAN_RATE> list = (List<MMP_LOAN_RATE>) DbQueryManager.searchDB(selector);
//		if(null != list && list.size()>0)
//			return list.get(0).getRATEVALUE();
//		return "";
//	}
	
	//根据贷款期限和利率种类获取利率
	public static String getRateByLimitAndRateType(String limit,String ratetype){
		if(null == limit || "".equals(limit))
			return "";
		Selector selector = Selector.from(MMP_LOAN_RATE.class);
		selector.where("MINTERM", "<", Integer.parseInt(limit));	
		selector.and("MAXTERM", ">=", Integer.parseInt(limit));
		selector.and("RATETYPE", "like", ratetype+"%");
		selector.and("OPR_TYPE", "=", "Y");
		selector.limit(1);
		List<MMP_LOAN_RATE> list = (List<MMP_LOAN_RATE>) DbQueryManager.searchDB(selector);
		if(null != list && list.size()>0)
			return list.get(0).getRATEVALUE();
		return "";
	}
	
	// 获得所有利率
	@SuppressWarnings("unchecked")
	public static List<MMP_LOAN_RATE> getAllLoanRate() {
		Selector selector = Selector.from(MMP_LOAN_RATE.class);	
		selector.where("OPR_TYPE", "=", "Y");
		return (List<MMP_LOAN_RATE>) DbQueryManager.searchDB(selector);

	}
	
	// 获得用户权限数据
	@SuppressWarnings("unchecked")
	public static List<ModuelInfoBO> userModuels(String parentId) {
		Selector selector = Selector.from(ModuelInfoBO.class);
		selector.where("parent_id", "=", parentId);
		selector.orderBy("view_order", false);
		
		List<?> l = DbQueryManager.searchDB(selector);
		if (null != l) {
			return (List<ModuelInfoBO>)l;
		} else {
			return new ArrayList<ModuelInfoBO>();
		}
	}
	// 获得用户权限数据
	@SuppressWarnings("unchecked")
	public static ModuelInfoBO userPModuel(String childUrl) {
		Selector selector = Selector.from(ModuelInfoBO.class);
		selector.where("url", "=", childUrl);
		
		ModuelInfoBO child = (ModuelInfoBO)DbQueryManager.searchDB(selector).get(0);
		selector = Selector.from(ModuelInfoBO.class);
		selector.where("module_id", "=", child.getParent_id());
		
		return (ModuelInfoBO)DbQueryManager.searchDB(selector).get(0);
	}
}
