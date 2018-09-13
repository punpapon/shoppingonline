package com.somapait.shoppingonline.web.security.login.action;

import java.sql.Connection;
import java.util.Locale;

import util.database.ConnectionProvider;
import util.database.ConnectionUtil;
import util.log.LogUtil;
import util.web.SessionUtil;

import com.opensymphony.xwork2.ModelDriven;
import com.somapait.common.CommonAction;
import com.somapait.common.CommonUser;
import com.somapait.domain.Operator;
import com.somapait.exception.AuthorizationException;
import com.somapait.shoppingonline.core.config.parameter.domain.DBLookup;
import com.somapait.shoppingonline.core.security.login.domain.LoginModel;
import com.somapait.shoppingonline.core.security.login.server.LoginManager;

public class LoginAction extends CommonAction implements ModelDriven<LoginModel>{

	private static final long serialVersionUID = 6876443944132207688L;

	private LoginModel model = new LoginModel();

	public LoginAction() {
		super();
		try {
			LogUtil.LOGIN.debug("Language :- " + LOCALE.getLanguage());

		} catch (Exception e) {
			LogUtil.LOGIN.error("", e);
		}
	}

	public String init() throws AuthorizationException {
		Connection conn = null;
		try {
			conn = new ConnectionProvider().getConnection(conn, DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup());
					
			
			captchaEnable = "Y";
		} catch (Exception e) {
			manageException(conn, "0", this, e, model);
		} finally {
			ConnectionUtil.close(conn);
		}

		return ReturnType.INIT.getResult();
	}

	
	public String check() {
		LogUtil.LOGIN.info("");
		String result = ReturnType.INIT.getResult();
		Connection conn = null;
		Boolean checklog = null;
		CommonUser user = null;
		try {
			//[1] สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new ConnectionProvider().getConnection(conn, DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup());
			
			LoginManager manager = new LoginManager(conn, getUser(), getLocale());
			/*CommonUser user = new CommonUser();
			user.setUserId("1");
			user.setLocale(new Locale("th"));*/
					
			// ตรวจสอบรหัสผ่านก่อนเข้าไป
			checklog = manager.checkLogin(getModel());
			
			
			// เก็บข้อมูลผู้ล็อคอิน
			user = manager.searchUserLogin(getModel());
			user.setLocale(new Locale("th"));
			
			System.out.println("CAPTCHA : " + SessionUtil.get("captcha"));
			System.out.println("CAPTCHA : " + model.getCaptcha());
			System.out.println("USER : " + user.getUserId());
			String cap = (String) SessionUtil.get("captcha");
			if(checklog == true && model.getCaptcha().equals(cap)) {			 
				SessionUtil.put(CommonUser.DEFAULT_SESSION_ATTRIBUTE, user);
				SessionUtil.put("userId", user.getUserId());
				result = ReturnType.MAINPAGE.getResult();
			}
			else {			
				result = ReturnType.INIT.getResult();
			}
			
		} catch (Exception e) {
			LogUtil.LOGIN.error("", e);
			setMessage(CommonAction.MessageType.ERROR, "Message error form server.", getErrorMessage(e), ResultType.BASIC);
		} finally {
			ConnectionUtil.close(conn);
		}
		return result;
	}

	public String logout(){
		try{
			SessionUtil.removes();
			captchaEnable = "Y";
		} catch(Exception e) {
			throw e;
		}
		return ReturnType.INIT.getResult();
	}

	@Override
	public LoginModel getModel() {
		return model;
	}

}
