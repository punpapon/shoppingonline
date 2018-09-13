package com.somapait.shoppingonline.web.shopping.admin.sale.action;

import java.sql.Connection;
import java.util.List;

import util.database.ConnectionProvider;
import util.database.ConnectionUtil;
import util.log.LogUtil;

import com.opensymphony.xwork2.ModelDriven;
import com.somapait.common.CommonAction;
import com.somapait.common.CommonModel.PageType;
import com.somapait.shoppingonline.core.config.parameter.domain.DBLookup;
import com.somapait.shoppingonline.core.security.authorization.domain.PFCode;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSale;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleModel;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleSearch;
import com.somapait.shoppingonline.core.shopping.admin.sale.domain.AdminSaleSearchCriteria;
import com.somapait.shoppingonline.core.shopping.admin.sale.service.AdminSaleManager;

/**
 * @description Class ที่ใช้สำหรับ action ของเจ้าหน้าที่เท่านั้น
 * @author -
 *
 */
public class AdminSaleAction extends CommonAction implements ModelDriven<AdminSaleModel>{
	private static final long serialVersionUID = 1L;

	private AdminSaleModel model = new AdminSaleModel();
	
	public AdminSaleAction() {
		try {
	        //Load authorization
	        getAuthorization(PFCode.SHOPPING);
	    } catch (Exception e) {
	        LogUtil.COMMON.error("", e);
	    }
	}
	
	@Override
	public AdminSaleModel getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	public String init() throws Exception {
		String result = null;
		Connection conn = null;
		try{
			LogUtil.INITIAL.debug("init");
			
			//[1] สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new ConnectionProvider().getConnection(conn, DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup());
			
			//2.ตรวจสอบสิทธิ์การใช้งาน และกำหนดค่าเริ่มต้นให้กับหน้าค้นหาของระบบ
			//result = manageInitial(conn, model, new AdminSaleSearchCriteria(), PF_CODE.getSearchFunction(), PageType.SEARCH);
			result="search";
		} catch (Exception e) {;
			throw e;
		} finally {
	        ConnectionUtil.close(conn);
		}
		return result;
		
	}
	//TODO method search() สำหรับค้นหารายการสั่งซื้อทั้งหมด ตามเงื่อนไขที่ได้รับจากหน้าจอ
	public String search() throws Exception{
		String result = null;		
		Connection conn = null;	
		AdminSaleManager manager = null;
		try{
			//[1] สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new ConnectionProvider().getConnection(conn, DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup());
			
			//[2] ค้นหาข้อมูล 
			manager = new AdminSaleManager(conn, getUser(), getLocale());
			List<AdminSaleSearch> lstResult = manager.search(model.getCriteria());
			model.setLstResultO(lstResult);
			model.getCriteria().setTotalResult(lstResult.size());
			//[3] จัดการผลลัพธ์และข้อความถ้าไม่พบข้อมูล
			manageSearchResult(model, lstResult);
			
			for (AdminSaleSearch item : lstResult) {
			    System.out.println(item);
			}

			
			result = "search";
		} catch(Exception e) {
			LogUtil.COMMON.error("", e);
		} finally {
			ConnectionUtil.close(conn);
		}
		return result;		
	}

	//TODO method gotoEdit() สำหรับไปค้นหารายการสั่งซื้อที่เลือกจากหน้าค้นหา แล้วนำไปแสดงที่หน้าแก้ไข
	

	//TODO method gotoView() สำหรับไปค้นหารายการสั่งซื้อที่เลือกจากหน้าค้นหา แล้วนำไปแสดงที่หน้าแสดง
	public String gotoView() throws Exception {
		String result = null;
		Connection conn = null;	
		try {
			//[1] สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new ConnectionProvider().getConnection(conn, DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup());
			result = "gotoView";
			
			 AdminSaleManager manager = new AdminSaleManager(conn, getUser(), getLocale());
		     LogUtil.COMMON.debug("View id: " + model.getAdminSale().getId());
			 AdminSale adminSale = manager.searchById2(model.getAdminSale().getId());
			 model.setAdminSale(adminSale);
		     
		     //System.out.println("VIEWADMON :" + model.getAdminSale().g);
		} catch (Exception e) {
			throw e;
		} finally {
			ConnectionUtil.close(conn);
		}
		return result;
		
	}

	//TODO method updateActive() สำหรับยกเลิกรายการสั่งซื้อที่เลือกจากหน้าค้นหา
}
