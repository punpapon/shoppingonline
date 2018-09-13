package com.somapait.shoppingonline.core.shopping.admin.sale.service;

import java.sql.Connection;
import java.util.List;
import java.util.Locale;

import com.somapait.abstracts.AbstractService;
import com.somapait.common.CommonUser;
import com.somapait.shoppingonline.core.config.parameter.domain.SQLPath;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSale;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleSearch;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleSearchCriteria;

/**
 * @description Class สำหรับจัดการ logic or algorithm (ถ้ามี)
 * @author -
 *
 */
public class AdminSaleService extends AbstractService{

	private AdminSaleDAO dao = null;
	
	public AdminSaleService(Connection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.dao = new AdminSaleDAO();
		this.dao.setSqlPath(SQLPath.SELECT_ITEM_SQL);
	}

	// service สืบค้นข้อมูล
	protected List<AdminSaleSearch> search(Connection conn, AdminSaleSearchCriteria criteria) throws Exception {
		List<AdminSaleSearch> listResult;
		  try {
			  listResult = dao.search(conn, criteria, user, locale);
		  } catch(Exception e) {
			  //LogUtil.SEC.error("",e);
			  throw e;
		  }
		return listResult;	
	}
	
	// service view
	protected AdminSale searchById(Connection conn,String Id) throws Exception {
		try {
	    	// ค้นหาข้อมูลโดยระบุ id
	        return dao.searchByIdT(conn, Id, user, locale);
	    } catch (Exception e) {
	        throw e;
	    } 
	}
}
