package com.somapait.shoppingonline.core.shopping.customers.sale.domain;

import com.somapait.common.CommonModel;

/**
 * @description Class สำหรับเก็บผลลัพธ์จากการค้นหารายการสินค้าแต่ละประเภทที่เลือกจากเมนู
 * @author -
 */
public class CustomerSaleSearch extends CommonModel{
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String product_desc;
	private String price;
	private String stock_num;
	private String image_name;
	private String type_id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStock_num() {
		return stock_num;
	}
	public void setStock_num(String stock_num) {
		this.stock_num = stock_num;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
}
