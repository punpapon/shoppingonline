package com.somapait.shoppingonline.core.security.login.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

import org.apache.tomcat.jni.User;

import util.database.ConnectionUtil;
import util.database.SQLUtil;
import util.log.LogUtil;
import util.string.StringUtil;
import util.type.DatabaseType.DbType;
import util.type.StringType.ResultType;

import com.somapait.abstracts.AbstractDAO;
import com.somapait.common.CommonUser;
import com.somapait.shoppingonline.core.config.parameter.domain.DBLookup;
import com.somapait.shoppingonline.core.config.parameter.domain.ParameterConfig;
import com.somapait.shoppingonline.core.config.xml.domain.Parameter;
import com.somapait.shoppingonline.core.security.login.domain.LoginCriteria;
import com.somapait.shoppingonline.core.security.login.domain.LoginModel;

public class LoginDAO extends AbstractDAO<LoginModel, LoginModel, LoginModel,CommonUser , Locale>{

	@Override
	protected LoginModel searchById(Connection conn, String id,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int add(Connection conn, LoginModel obj, CommonUser user,
			Locale locale) throws Exception {
		
		return 0;
	}

	@Override
	protected int edit(Connection conn, LoginModel obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int delete(Connection conn, String ids, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int updateActive(Connection conn, String ids, String activeFlag,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean checkDup(Connection conn, LoginModel obj,
			CommonUser user, Locale locale) throws Exception {
		boolean checkDup = false;
		int count = 0 ;
		int paramIndex = 0;

		Object[] params = new Object[2];
		params[paramIndex++] = obj.getUsername();
		params[paramIndex++] = obj.getPassword();
		
		String sql = SQLUtil.getSQLString(
				ParameterConfig.getDatabase(DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup()).getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "checkLoginDup"
				, params );
		
		LogUtil.LOGIN.debug("SQL checkDup :" +sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				count = rst.getInt("TOT");
			}
			if(count > 0){
				checkDup = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			ConnectionUtil.closeAll(rst, stmt);
		}
		return checkDup;
		
	}

	
	@Override
	protected int countData(Connection conn, LoginModel criteria,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected List<LoginModel> search(Connection conn, LoginModel criteria,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	protected CommonUser searchUserLogin(Connection conn, LoginModel obj) throws Exception  {
		int paramIndex = 0;
	    Object[] params = new Object[2];
	    params[paramIndex++] = obj.getUsername();
        params[paramIndex++] = obj.getPassword();
        
        String sql = SQLUtil.getSQLString(
        		ParameterConfig.getDatabase(DBLookup.MYSQL_PG_EXAM_SHOPPING.getLookup()).getSchemas()
	            , getSqlPath().getClassName()
	            , getSqlPath().getPath()
	            , "searchAdmin"
	            , params);
	    LogUtil.LOGIN.debug("SQL : " + sql);
	    
	    CommonUser user = new CommonUser();
	    Statement stmt = null;
		ResultSet rst = null;
		try{
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				user.setUserId(StringUtil.nullToString(rst.getString("USER_ID")));
				user.setFullName(StringUtil.nullToString(rst.getString("FULLNAME")));
				user.setActive(StringUtil.nullToString(rst.getString("ADMIN")));
			 }
			
		} catch (Exception e) {
			throw e;
		} finally {
			ConnectionUtil.closeAll(rst, stmt);
		}
				return user;
		
	}

	@Override
	protected LoginModel searchByIdT(Connection conn, String id,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
