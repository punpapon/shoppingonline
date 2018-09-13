package com.somapait.shoppingonline.core.shopping.admin.sale.domain;

import com.somapait.domain.SearchCriteria;

/**
 * @description Class สำหรับเก็บเงื่อนไขการค้นหาจากหน้า ค้นหารายการสั่งซื้อ
 * @author -
 */
public class AdminSaleSearchCriteria extends SearchCriteria{
	private static final long serialVersionUID = 7517991211013069896L;
	
	private String orderId;				// เลขที่ใบสั่งซื้อ
	private String statusProduct;		// สถานะสินค้า
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStatusProduct() {
		return statusProduct;
	}
	public void setStatusProduct(String statusProduct) {
		this.statusProduct = statusProduct;
	}
	
}
