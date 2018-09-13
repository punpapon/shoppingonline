package com.somapait.shoppingonline.core.shopping.admin.sale.domain;

import com.somapait.common.CommonDomain;
import com.somapait.common.CommonModel;

/**
 * @description Class สำหรับแสดงรายละเอียดของการสั่งซื้อจากรายการที่เลือกในหน้าค้นหา และนำข้อมูลดังกล่าวไปใช้บันทึกตอนจัดส่งด้วย
 * @author -
 */
public class AdminSale extends CommonDomain{

	private static final long serialVersionUID = 1L;
	
	private String orderId;
	private String fullName;
	private String statusProduct;
	private String date;
	private String shipNo;
	private String remark;
	private String productId;
	private String productDetail;
	private String count;
	private String value;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getStatusProduct() {
		return statusProduct;
	}
	public void setStatusProduct(String statusProduct) {
		this.statusProduct = statusProduct;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getShipNo() {
		return shipNo;
	}
	public void setShipNo(String shipNo) {
		this.shipNo = shipNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
