package com.somapait.shoppingonline.core.security.login.server;

import java.sql.Connection;
import java.util.List;
import java.util.Locale;

import util.web.SessionUtil;

import com.somapait.abstracts.AbstractManager;
import com.somapait.common.CommonUser;
import com.somapait.shoppingonline.core.config.parameter.domain.SQLPath;
import com.somapait.shoppingonline.core.security.login.domain.LoginModel;

public class LoginManager extends AbstractManager<Object, Object, LoginModel, CommonUser, Locale>{
	private Connection conn = null;
	private LoginDAO dao = null;
	
	public LoginManager(Connection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.conn = conn;
		this.dao = new LoginDAO();
		this.dao.setSqlPath(SQLPath.LOGIN_SQL);
	}

	@Override
	public List<Object> search(Object criteria) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginModel searchById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(LoginModel obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edit(LoginModel obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean checkLogin(LoginModel obj) throws Exception {			
		return dao.checkDup(conn, obj, user, locale);	
	}

	
	
	public CommonUser searchUserLogin(LoginModel obj) throws Exception {
		CommonUser result = new CommonUser();
		try{
			result = dao.searchUserLogin(conn, obj);
		}catch (Exception e) {
			throw e;
		}
		return result;
	}
}
