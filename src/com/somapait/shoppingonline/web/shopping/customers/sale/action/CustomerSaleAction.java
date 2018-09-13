package com.somapait.shoppingonline.web.shopping.customers.sale.action;

import java.sql.Connection;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import util.database.ConnectionProvider;
import util.database.ConnectionUtil;
import util.log.LogUtil;
import util.web.SessionUtil;

import com.opensymphony.xwork2.ModelDriven;
import com.somapait.common.CommonAction;
import com.somapait.domain.Transaction;
import com.somapait.exception.AuthorizationException;
import com.somapait.interfaces.InterfaceAction;
import com.somapait.shoppingonline.core.config.parameter.domain.DBLookup;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSale;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSaleModel;
import com.somapait.shoppingonline.core.shopping.customers.sale.domain.CustomerSaleSearch;
import com.somapait.shoppingonline.core.shopping.customers.sale.service.CustomerSaleManager;
import com.sun.xml.internal.ws.client.RequestContext;

/**
 * @description Class ที่ใช้สำหรับ action ของลูกค้าท่านั้น
 * @author -
 *
 */
public class CustomerSaleAction extends CommonAction implements ModelDriven<CustomerSaleModel>{
	private static final long serialVersionUID = 1L;

	private CustomerSaleModel model = new CustomerSaleModel();
	
	@Override
	public CustomerSaleModel getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	//TODO method searchProductList() สำหรับค้นหาและแสดงรายการสินค้าแต่ละประเภทที่เลือกจากเมนู โดยรับค่าประเภทสินค้าจาก url แล้วแสดงรายการตามประเภทที่เลือก
	public String searchProductList() throws Exception{
		String result 		= null;
		Connection conn 	= null;	
		CustomerSaleManager manager = null;
		
		try {
			LogUtil.COMMON.debug(getUser());
			
			//[1] สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new ConnectionProvider().getConnection(conn, DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup());
			
			//[2] รับค่าประเภทที่ค้นหาผ่านทาง parameter
			HttpServletRequest req = ServletActionContext.getRequest();
			String x = req.getParameter("pagee");
			String id = req.getParameter("product");
			SessionUtil.setAttribute("ProductId", id);					
			String product = (String) SessionUtil.get("ProductId");		
			//[3] ค้นหาประเภทสินค้า
			manager = new CustomerSaleManager(conn, getUser(), getLocale());
			model.getCriteria().setTypeId(x);
			List<CustomerSaleSearch> lstResult = manager.search(model.getCriteria());   
			
			manager = new CustomerSaleManager(conn, getUser(), getLocale());
			CustomerSaleSearch customerSaleSearch = manager.searchById(id);
			
			System.out.println("XXX :" + product);
			
			//เพิ่มสินค้าในตะกร้า TEST
			
			if(id!=null) {
			manager = new CustomerSaleManager(conn, getUser(), getLocale());
			model.getCustomerSale().setProductId(customerSaleSearch.getId());
			model.getCustomerSale().setCount("1");
			model.getCustomerSale().setSumValue(customerSaleSearch.getPrice());
			model.getCustomerSale().setOrderId((String)SessionUtil.get("userId"));
			manager.add(model.getCustomerSale());	
			model.setCustomerSale(new CustomerSale());
			}
			
			//[4] จัดการผลลัพธ์และข้อความถ้าไม่พบข้อมูล
			manageSearchResult(model, lstResult);
			
			for (CustomerSaleSearch item : lstResult) {
			    System.out.println(item);
			}
			result = "search";
			
		} catch (Exception e) {
			LogUtil.COMMON.error("", e);
			//manageException(conn, PF_CODE.getSearchFunction(), this, e, model);
			
		} finally {
			ConnectionUtil.close(conn);
		}

		LogUtil.COMMON.debug("[ ###RESULT ] : " + result);
		return result;
		
	}

	


	//TODO method addToCartAjax() สำหรับจัดการเก็บรายการสินค้าที่ต้องการลงบน session
	

	//TODO method gotoMyCartAdd() สำหรับแสดงรายการสินค้าที่อยู่ใน session โดยกำหนดให้ default จำนวนรายการเป็น 1 จำนวน
	public String gotoMyCartAdd() throws Exception {
		String result 		= null;
		Connection conn 	= null;	
		CustomerSaleManager manager = null;
		try{
			//[1] สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new ConnectionProvider().getConnection(conn, DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup());
			
			manager = new CustomerSaleManager(conn, getUser(), getLocale());
			List<CustomerSale> listResultChart = manager.searchChart((String)SessionUtil.get("userId"));
			getModel().setListResult(listResultChart);
			
			
			
			//[4] จัดการผลลัพธ์และข้อความถ้าไม่พบข้อมูล
			manageSearchResult(model, listResultChart);
			
			for (CustomerSale item : listResultChart) {
			    System.out.println(item);
			}
			
			result = "mycart";
			
		} catch (Exception e) {
			throw e;
		} finally {
			ConnectionUtil.close(conn);
		}
		return result;
		
	}

	//TODO method add() สำหรับทำรายการสั่งซื้อ
	public String orderComplete() throws Exception {
		String result = null;
		Connection conn 	= null;	
		//CustomerSaleManager manager = null;
		try{
			result = "orderComplete";
			
		} catch(Exception e) {
			throw e;
		} finally {
			ConnectionUtil.close(conn);
		}
		return result;
	}
	
	//method thumbnail() โชว์สินค้า
	public String thumbnail() {
		
		return "showPic";
		
	}
}
