package com.somapait.shoppingonline.core.shopping.admin.sale.domain;

import com.somapait.common.CommonModel;

/**
 * @description Class สำหรับเก็บผลลัพธ์ที่ได้จากการค้นหารายการสั่งซื้อ ในหน้าค้นหารายการสั่งซื้อ
 * @author -
 */
public class AdminSaleSearch extends CommonModel{
	private static final long serialVersionUID = 1L;

	private String orderId;
	private String orderDate;
	private String fullName;
	private String totalValue;
	private String statusShip;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	public String getStatusShip() {
		return statusShip;
	}
	public void setStatusShip(String statusShip) {
		this.statusShip = statusShip;
	}
	
}
