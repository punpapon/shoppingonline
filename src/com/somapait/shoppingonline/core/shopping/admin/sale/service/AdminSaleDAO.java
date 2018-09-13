package com.somapait.shoppingonline.core.shopping.admin.sale.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;





import util.database.ConnectionUtil;
import util.database.SQLUtil;
import util.log.LogUtil;
import util.string.StringUtil;
import util.type.StringType.ResultType;

import com.somapait.abstracts.AbstractDAO;
import com.somapait.common.CommonUser;
import com.somapait.shoppingonline.core.config.parameter.domain.DBLookup;
import com.somapait.shoppingonline.core.config.parameter.domain.ParameterConfig;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSale;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleModel;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleSearch;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleSearchCriteria;

/**
 * @description Class สำหรับติดต่อกับฐานข้อมูล
 * @author -
 *
 */
public class AdminSaleDAO extends AbstractDAO<AdminSaleSearchCriteria, AdminSaleSearch, AdminSale, CommonUser, Locale>{

	@Override
	protected int countData(Connection conn, AdminSaleSearchCriteria criteria,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected List<AdminSaleSearch> search(Connection conn,
			AdminSaleSearchCriteria criteria, CommonUser user, Locale locale)
			throws Exception {
		LogUtil.SELECTOR.debug("search");
		List<AdminSaleSearch> listResult = new ArrayList<AdminSaleSearch>();
		int paramIndex = 0; 
		Object[] params = new Object[2];
		params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getOrderId(), null, ResultType.NULL); 
		params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getStatusProduct(), null, ResultType.NULL);
	/*	params[paramIndex++] = StringUtil.nullToString(criteria.getStart());
		params[paramIndex] = StringUtil.nullToString(criteria.getLinePerPage());*/

		String sql = SQLUtil.getSQLString(
				ParameterConfig.getDatabase(DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup()).getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchOrder"
				, params );
		LogUtil.SELECTOR.debug("SQL search :" +sql);
		
		Statement stmt = null;
		ResultSet rst = null;	
		try{
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while(rst.next()){
				AdminSaleSearch result = new AdminSaleSearch();
				result.setOrderId(StringUtil.nullToString(rst.getString("NO")));
				result.setOrderDate(StringUtil.nullToString(rst.getString("ORDER_DATE")));
				result.setFullName(StringUtil.nullToString(rst.getString("FIRST_NAME")));
				result.setTotalValue(StringUtil.nullToString(rst.getString("TOTAL_PRICE")));
				result.setStatusShip(StringUtil.nullToString(rst.getString("SHIP")));
				listResult.add(result);  
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			ConnectionUtil.closeAll(rst, stmt);
		}
		return listResult;
	}

	@Override
	protected AdminSale searchByIdT(Connection conn, String id,
			CommonUser user, Locale locale) throws Exception {
		LogUtil.COMMON.debug("searchById");
		
		int paramIndex = 0;		
		Object[] params = new Object[1];
		params[paramIndex++] = id;
		String sql = SQLUtil.getSQLString(
				ParameterConfig.getDatabase(DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup()).getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "viewById"
				, params );
		LogUtil.SELECTOR.debug("SQL search :" +sql);
		
		AdminSale adminSale = null;
		List<AdminSale> listResult = new ArrayList<AdminSale>();
		Statement stmt = null;
		ResultSet rst = null;
		AdminSaleModel model = new AdminSaleModel();
		try{
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				adminSale = new AdminSale();
				adminSale.setOrderId(StringUtil.nullToString(rst.getString("NO")));
				adminSale.setFullName(StringUtil.nullToString(rst.getString("FULLNAME")));
				adminSale.setStatusProduct(StringUtil.nullToString(rst.getString("SHIP")));
				adminSale.setDate(StringUtil.nullToString(rst.getString("SHIP_DATE")));
				adminSale.setShipNo(StringUtil.nullToString(rst.getString("TRACKING_NO")));
				adminSale.setRemark(StringUtil.nullToString(rst.getString("NOTE")));
				adminSale.setProductId(StringUtil.nullToString(rst.getString("CODE")));
				adminSale.setProductDetail(StringUtil.nullToString(rst.getString("PRODUCT_DESC")));
				adminSale.setCount(StringUtil.nullToString(rst.getString("TOTAL_NUM")));
				adminSale.setValue(StringUtil.nullToString(rst.getString("TOTAL_PRICE")));
				listResult.add(adminSale);
				model.setListOrder(listResult);
				
				//model.setListResult(listResult);
			
			}			
		}catch (Exception e) {
			throw e;
		}finally {
			ConnectionUtil.close(conn);
		}
		return adminSale;
	}

	@Override
	protected int add(Connection conn, AdminSale obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int edit(Connection conn, AdminSale obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int delete(Connection conn, String ids, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int updateActive(Connection conn, String ids, String activeFlag,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean checkDup(Connection conn, AdminSale obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected AdminSaleSearch searchById(Connection conn, String id,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
