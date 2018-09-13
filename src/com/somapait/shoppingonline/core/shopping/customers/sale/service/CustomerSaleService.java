package com.somapait.shoppingonline.core.shopping.customers.sale.service;

import java.sql.Connection;
import java.util.List;
import java.util.Locale;

import util.log.LogUtil;

import com.somapait.common.CommonUser;
import com.somapait.shoppingonline.core.config.parameter.domain.SQLPath;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSale;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSaleSearch;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSaleSearchCriteria;
import com.somapait.abstracts.AbstractService;

/**
 * @description Class สำหรับจัดการ logic or algorithm (ถ้ามี)
 * @author -
 *
 */
public class CustomerSaleService extends AbstractService{
	 
	private CustomerSaleDAO dao = null;

	public CustomerSaleService(Connection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.dao = new CustomerSaleDAO();
		this.dao.setSqlPath(SQLPath.SELECT_ITEM_SQL);
	}
	
	protected CustomerSaleSearch searchById(Connection conn,String Id) throws Exception{
	    try {
	    	// ค้นหาข้อมูลโดยระบุ id
	        return dao.searchById(conn, Id, user, locale);
	    } catch (Exception e) {
	        throw e;
	    }
	}
	//service ของการสืบค้นข้อมูล
		protected List<CustomerSaleSearch> search(Connection conn, CustomerSaleSearchCriteria criteria) throws Exception {
		  List<CustomerSaleSearch> listResult;
		  
		  try {
			  listResult = dao.search(conn, criteria, user, locale);
		  } catch(Exception e) {
			  //LogUtil.SEC.error("",e);
			  throw e;
		  }
			return listResult;
		 }
		
		//service ค้นหาข้อมูลในตะกร้า
		protected List<CustomerSale> searchChart(Connection conn, String id) throws Exception {
			  List<CustomerSale> listResult;		  
			  try {
				  listResult = dao.searchChart(conn, id, user, locale);
			  } catch(Exception e) {
				  //LogUtil.SEC.error("",e);
				  throw e;
			  }
				return listResult;
			 }
		
		//service ของการนับข้อมูล
		protected long countData(CustomerSaleSearchCriteria criteria) throws Exception {
			try {
				return dao.countData(conn, criteria, user, locale);
			} catch(Exception e){
				LogUtil.SELECTOR.error("",e);
				throw e;
			}
		}
		
		// add chart
		protected int addChart(Connection conn, CustomerSale obj) throws Exception {
			try{
				return dao.add(conn, obj, user, locale);
						
			} catch(Exception e){
				throw e;
			}
		}

}
