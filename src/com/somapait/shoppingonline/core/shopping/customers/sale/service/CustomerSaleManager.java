package com.somapait.shoppingonline.core.shopping.customers.sale.service;

import java.sql.Connection;
import java.util.List;
import java.util.Locale;

import util.log.LogUtil;

import com.somapait.abstracts.AbstractManager;
import com.somapait.common.CommonUser;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSale;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSaleSearch;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSaleSearchCriteria;

/**
 * @description Class สำหรับจัดการการทำงานต่างๆ แยกตาม module
 * @author -
 *
 */
public class CustomerSaleManager extends AbstractManager<CustomerSaleSearchCriteria, CustomerSaleSearch, CustomerSale, CommonUser, Locale>{

	private CustomerSaleService service = null; 
	
	public CustomerSaleManager(Connection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.service = new CustomerSaleService(conn, user, locale);
	}

	@Override
	public List<CustomerSaleSearch> search(CustomerSaleSearchCriteria criteria)
			throws Exception {
		List<CustomerSaleSearch> listResult;
		try {
			//นับจำนวนรายการที่ค้นพบ
			criteria.setTotalResult(service.countData(criteria));
			LogUtil.SELECTOR.debug("Count data[" +criteria.getTotalResult() + "]record.");
					
			listResult = service.search(conn, criteria);
			
		}			
		catch (Exception e) {
			throw e;
		}
		return listResult;
	}
	
	public List<CustomerSale> searchChart(String id) throws Exception {
		List<CustomerSale> listResult;
		try{
			listResult = service.searchChart(conn, id);
		}catch(Exception e) {
			throw e;
		}
		return listResult;
	}

	@Override
	public CustomerSaleSearch searchById(String id) throws Exception {
		//return null;
		return service.searchById(conn, id);
	}

	@Override
	public int add(CustomerSale obj) throws Exception {
		try{
			service.addChart(conn, obj);
		} catch (Exception e) {
			throw e;
		}
		return 0;
	}

	@Override
	public int edit(CustomerSale obj) throws Exception {
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

	//TODO method searchProductList(CustomerSearchCriteria criteria) สำหรับค้นหาและแสดงรายการสินค้าแต่ละประเภทที่เลือกจากเมนู


	//TODO method add(CustomerSale customerSale) สำหรับทำรายการสั่งซื้อ

}
