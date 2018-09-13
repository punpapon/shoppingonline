package com.somapait.shoppingonline.core.shopping.customers.sale.service;

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
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSale;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSaleSearch;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSaleSearchCriteria;

/**
 * @description Class สำหรับติดต่อกับฐานข้อมูล
 * @author -
 *
 */
public class CustomerSaleDAO extends AbstractDAO<CustomerSaleSearchCriteria, CustomerSaleSearch, CustomerSale, CommonUser, Locale>{

	@Override
	protected int countData(Connection conn,
			CustomerSaleSearchCriteria criteria, CommonUser user, Locale locale)
			throws Exception {
		int total = 0 ;
		int paramIndex = 0;
		Object[] params = new Object[1];
		params[paramIndex++] = criteria.getTypeId();
		String sql = SQLUtil.getSQLString(
				ParameterConfig.getDatabase(DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup()).getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "countProduct"
				, params );
		LogUtil.SELECTOR.debug("SQL searchById :" +sql);
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				total = rst.getInt("TOT");
			}
			} catch (Exception e) {
				throw e;
			} finally {
				ConnectionUtil.closeAll(rst, stmt);
			}
		return total;
	}

	@Override
	protected List<CustomerSaleSearch> search(Connection conn,
			CustomerSaleSearchCriteria criteria, CommonUser user, Locale locale)
			throws Exception {
		LogUtil.SELECTOR.debug("searchById");	
		List<CustomerSaleSearch> listResult = new ArrayList<CustomerSaleSearch>();
		int paramIndex = 0; 
		Object[] params = new Object[1];
		params[paramIndex++] = criteria.getTypeId();

		String sql = SQLUtil.getSQLString(
				ParameterConfig.getDatabase(DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup()).getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchById"
				, params );
		LogUtil.SELECTOR.debug("SQL searchById :" +sql);
		
		Statement stmt = null;
		ResultSet rst = null;

		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			 while (rst.next()) {
                 CustomerSaleSearch result = new CustomerSaleSearch();
                 result.setId(StringUtil.nullToString(rst.getString("ID")));
                 result.setCode(StringUtil.nullToString(rst.getString("CODE")));
                 result.setProduct_desc(StringUtil.nullToString(rst.getString("PRODUCT_DESC")));
                 result.setPrice(StringUtil.nullToString(rst.getString("PRICE")));
                 result.setStock_num(StringUtil.nullToString(rst.getString("STOCK_NUM")));
                 result.setImage_name(StringUtil.nullToString(rst.getString("IMAGE")));
                 result.setType_id(StringUtil.nullToString(rst.getString("TYPE_ID")));            
                 listResult.add(result);              
            }            
		} catch (Exception e) {
			throw e;
		} finally {
			ConnectionUtil.closeStatement(stmt);
		}
		return listResult;
	}

	protected List<CustomerSale> searchChart(Connection conn,
			String id, CommonUser user, Locale locale)
			throws Exception {
		LogUtil.SELECTOR.debug("searchById");	
		List<CustomerSale> listResult = new ArrayList<CustomerSale>();
		int paramIndex = 0; 
		Object[] params = new Object[1];
		params[paramIndex++] = id;

		String sql = SQLUtil.getSQLString(
				ParameterConfig.getDatabase(DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup()).getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchByIdChart"
				, params );
		LogUtil.SELECTOR.debug("SQL searchById :" +sql);
		
		CustomerSale customerSale = null;
		Statement stmt = null;
		ResultSet rst = null;

		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				customerSale = new CustomerSale();
				customerSale.setProductId(StringUtil.nullToString(rst.getString("CODE")));
				customerSale.setPic(StringUtil.nullToString(rst.getString("IMAGE_NAME")));
				customerSale.setProductDesc(StringUtil.nullToString(rst.getString("PRODUCT_DESC")));
				customerSale.setValue(StringUtil.nullToString(rst.getString("PRICE")));
				customerSale.setCount(StringUtil.nullToString(rst.getString("TOTAL_NUM")));
				customerSale.setSumValue(StringUtil.nullToString(rst.getString("TOTAL_PRICE")));
				customerSale.setTypeId(StringUtil.nullToString(rst.getString("TYPE_ID")));
				listResult.add(customerSale);
			}     
		} catch (Exception e) {
			throw e;
		} finally {
			ConnectionUtil.closeStatement(stmt);
		}
		return listResult;
	}
	
	@Override
	protected CustomerSaleSearch searchById(Connection conn, String id,
			CommonUser user, Locale locale) throws Exception {
		LogUtil.SELECTOR.debug("searchById");		
		int paramIndex = 0;
		Object[] params = new Object[1];
		params[paramIndex++] = id;

		String sql = SQLUtil.getSQLString(
				ParameterConfig.getDatabase(DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup()).getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchIdProduct"
				, params );
		LogUtil.SELECTOR.debug("SQL searchById :" +sql);
		
		CustomerSaleSearch customerSaleSearch = null;
		Statement stmt = null;
		ResultSet rst = null;

		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				customerSaleSearch = new CustomerSaleSearch();
				customerSaleSearch.setId(StringUtil.nullToString(rst.getString("ID")));
				customerSaleSearch.setCode(StringUtil.nullToString(rst.getString("CODE")));
				customerSaleSearch.setProduct_desc(StringUtil.nullToString(rst.getString("PRODUCT_DESC")));
				customerSaleSearch.setPrice(StringUtil.nullToString(rst.getString("PRICE")));
				customerSaleSearch.setStock_num(StringUtil.nullToString(rst.getString("STOCK_NUM")));			
				customerSaleSearch.setType_id(StringUtil.nullToString(rst.getString("TYPE_ID")));

			}

		} catch (Exception e) {
			throw e;
		} finally {
			ConnectionUtil.closeStatement(stmt);
		}
		
		return customerSaleSearch;
	}

	@Override
	protected int add(Connection conn, CustomerSale obj, CommonUser user,
			Locale locale) throws Exception {
		int paramIndex = 0;
		int id = 0;

		Object[] params = new Object[4];	
		params[paramIndex++] = obj.getProductId();
		params[paramIndex++] = obj.getCount();
		params[paramIndex++] = obj.getSumValue();
		params[paramIndex++] = obj.getOrderId();
		
		String sql = SQLUtil.getSQLString(
				ParameterConfig.getDatabase(DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup()).getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "insertChart"
				, params );
		LogUtil.SELECTOR.debug("SQL Insert Chart :" +sql);
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
		} catch (Exception e) {
			throw e;
		} finally {
			ConnectionUtil.closeStatement(stmt);
			
		}
		return id;
	}

	@Override
	protected int edit(Connection conn, CustomerSale obj, CommonUser user,
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
	protected boolean checkDup(Connection conn, CustomerSale obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected CustomerSale searchByIdT(Connection conn, String id,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	//protected List<CustomerSale> searchBasket(Connection conn,)

}
