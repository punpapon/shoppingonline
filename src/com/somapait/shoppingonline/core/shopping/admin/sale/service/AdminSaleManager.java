package com.somapait.shoppingonline.core.shopping.admin.sale.service;

import java.sql.Connection;
import java.util.List;
import java.util.Locale;

import com.somapait.abstracts.AbstractManager;
import com.somapait.common.CommonUser;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSale;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleSearch;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleSearchCriteria;

/**
 * @description Class สำหรับจัดการการทำงานต่างๆ แยกตาม module
 * @author -
 *
 */
public class AdminSaleManager extends AbstractManager<AdminSaleSearchCriteria, AdminSaleSearch, AdminSale, CommonUser, Locale>{

	private AdminSaleService service = null;
	
	public AdminSaleManager(Connection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.service = new AdminSaleService(conn, user, locale);
	}

	@Override
	public List<AdminSaleSearch> search(AdminSaleSearchCriteria criteria)
			throws Exception {
		List<AdminSaleSearch> listResult;
		try {
			listResult = service.search(conn, criteria);
		} catch (Exception e) {
			throw e;
		}
		return listResult;
	}

	@Override
	public AdminSaleSearch searchById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public AdminSale searchById2(String id) throws Exception {
		// TODO Auto-generated method stub
		return service.searchById(conn, id);
	}

	@Override
	public int add(AdminSale obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edit(AdminSale obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	//TODO method search(AdminSaleSearchCriteria criteria) สำหรับค้นหารายการสั่งซื้อทั้งหมด ตามเงื่อนไขที่ได้รับจากหน้าจอ


	//TODO method searchById(String id) สำหรับไปค้นหารายการสั่งซื้อที่เลือกจากหน้าค้นหา แล้วนำไปแสดง


	//TODO method edit(AdminSale adminSale) สำหรับจัดส่ง


	//TODO method updateActive(String ids, String activeFlag) สำหรับยกเลิกรายการสั่งซื้อที่เลือกจากหน้าค้นหา
}
