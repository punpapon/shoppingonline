package com.somapait.shoppingonline.core.shopping.admin.sale.domain;

import java.util.ArrayList;
import java.util.List;

import com.somapait.common.CommonModel;
import com.somapait.domain.SearchCriteria;

public class AdminSaleModel extends CommonModel{

	private static final long serialVersionUID = 4131603807845961292L;

	// Object สำหรับเก็บเงื่อนไขการค้นหา
	private AdminSaleSearchCriteria criteria = new AdminSaleSearchCriteria();
	
	// Object สำหรับใช้ในหน้า Add , Edit
	private AdminSale adminSale = new AdminSale();
	
	
	private AdminSaleSearch adminSaleSearch = new AdminSaleSearch();
	
	private List<AdminSale> listResult = new ArrayList<>();
	private List<AdminSale> listOrder = new ArrayList<>();
	private List<AdminSaleSearch> lstResultO = new ArrayList<>();
	
	@Override
	public AdminSaleSearchCriteria getCriteria() {
		// TODO Auto-generated method stub
		return criteria;
	}
	
	@Override
	public void setCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		this.criteria = (AdminSaleSearchCriteria) criteria;
	}

	public AdminSaleSearch getAdminSaleSearch() {
		return adminSaleSearch;
	}

	public void setAdminSaleSearch(AdminSaleSearch adminSaleSearch) {
		this.adminSaleSearch = adminSaleSearch;
	}



	public AdminSale getAdminSale() {
		return adminSale;
	}

	public void setAdminSale(AdminSale adminSale) {
		this.adminSale = adminSale;
	}

	

	public List<AdminSale> getListResult() {
		return listResult;
	}

	public void setListResult(List<AdminSale> listResult) {
		this.listResult = listResult;
	}

	public List<AdminSale> getListOrder() {
		return listOrder;
	}

	public void setListOrder(List<AdminSale> listOrder) {
		this.listOrder = listOrder;
	}

	public List<AdminSaleSearch> getLstResultO() {
		return lstResultO;
	}

	public void setLstResultO(List<AdminSaleSearch> lstResultO) {
		this.lstResultO = lstResultO;
	}



	

	
}
