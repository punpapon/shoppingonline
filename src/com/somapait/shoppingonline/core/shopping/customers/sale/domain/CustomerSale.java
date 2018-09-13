package com.somapait.shoppingonline.core.shopping.customers.sale.domain;

import com.somapait.common.CommonDomain;

/**
 * @description Class สำหรับเก็บรายการสินค้าที่ต้องการสั่งซื้อ
 * @author -
 */
public class CustomerSale extends CommonDomain{

	private static final long serialVersionUID = 1L;

	private String productId;
	private String pic;
	private String productDesc;
	private String value;
	private String count;
	private String sumValue;
	private String typeId;
	private String orderId;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getSumValue() {
		return sumValue;
	}
	public void setSumValue(String sumValue) {
		this.sumValue = sumValue;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
