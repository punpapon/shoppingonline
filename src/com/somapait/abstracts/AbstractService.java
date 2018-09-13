package com.somapait.abstracts;

import java.sql.Connection;
import java.util.Locale;

import com.somapait.common.CommonUser;

public class AbstractService {
	protected Connection conn = null;
	protected CommonUser user = null;
	protected Locale locale = null;

	/**
	 * @param conn
	 */
	public AbstractService(Connection conn, CommonUser user, Locale locale) {
		this.conn = conn;
		this.user = user;
		this.locale = locale;
	}
}
