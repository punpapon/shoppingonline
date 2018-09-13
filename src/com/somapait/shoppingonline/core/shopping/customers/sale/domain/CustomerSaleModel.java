package com.somapait.shoppingonline.core.shopping.customers.sale.domain;

import java.util.ArrayList;
import java.util.List;

import com.somapait.common.CommonModel;
import com.somapait.domain.SearchCriteria;

public class CustomerSaleModel extends CommonModel {
	private static final long serialVersionUID = -1549695679974322090L;
	
	// Object สำหรับเก็บเงื่อนการค้นหา
	private CustomerSaleSearchCriteria criteria = new CustomerSaleSearchCriteria(); 

	// Object สำหรับใช้ในการ add edit view
	private CustomerSale customerSale = new CustomerSale();
	
	private List<CustomerSale> listResult = new ArrayList<>();
	
	@Override
	public CustomerSaleSearchCriteria getCriteria() {
		// TODO Auto-generated method stub
		return criteria;
	}
	
	@Override
	public void setCriteria(SearchCriteria criteria) {
		// TODO Auto-generated method stub
		this.criteria = (CustomerSaleSearchCriteria) criteria;
	}

	public CustomerSale getCustomerSale() {
		return customerSale;
	}

	public void setCustomerSale(CustomerSale customerSale) {
		this.customerSale = customerSale;
	}

	public List<CustomerSale> getListResult() {
		return listResult;
	}

	public void setListResult(List<CustomerSale> listResult) {
		this.listResult = listResult;
	}
	
	
}
