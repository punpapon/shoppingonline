package com.somapait.shoppingonline.web.config.parameter.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.log.LogUtil;

import com.somapait.shoppingonline.core.config.parameter.service.ParameterManager;

public class ParameterServlet extends HttpServlet {

	private static final long serialVersionUID = -1326931967347560900L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogUtil.INITIAL.info("");
		try {
			init();
		} catch (Exception e) {
			LogUtil.INITIAL.error("", e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogUtil.INITIAL.info("");
		try {
			init();
		} catch (Exception e) {
			LogUtil.INITIAL.error("", e);
		}
	}

	public void init() throws ServletException {
		try {
			String realPath = "";

			if (getInitParameter("parameterfile") != null) {
				realPath = getServletContext().getRealPath(getInitParameter("parameterfile").toString());
				if (realPath == null){
					realPath = getInitParameter("parameterfileServer").toString();
				}
			}

			LogUtil.INITIAL.debug("Parameter path :- " + realPath);
			new ParameterManager().init(realPath);
			LogUtil.INITIAL.debug("Load parameter completed.");
		} catch (Exception e) {
			LogUtil.INITIAL.error("Can't load paramter!!!");
			LogUtil.INITIAL.error("", e);
			System.exit(-1);
		}
	}

}
