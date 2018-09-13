package com.somapait.shoppingonline.web.interceptor;

import util.log.LogUtil;
import util.web.SessionUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.somapait.common.CommonAction;

public class TokenInterceptor extends org.apache.struts2.interceptor.TokenInterceptor {

	private static final long serialVersionUID = 5521505366657681585L;

	protected String handleInvalidToken(ActionInvocation invocation) {
		String actionName = invocation.getProxy().getActionName();
		String returnvalue = CommonAction.ReturnType.HOME.getResult();

		try {
			LogUtil.INTERCEPTOR.debug("actionName :- " + actionName);
			if (actionName.startsWith("add")) {
				returnvalue = CommonAction.ReturnType.ADD_DO.getResult();

			} else if (actionName.startsWith("edit")) {
				returnvalue = CommonAction.ReturnType.SEARCH_DO.getResult();

			} else if (actionName.startsWith("update")) {
				returnvalue = CommonAction.ReturnType.SEARCH_DO.getResult();
			}
		} catch (Exception e) {
			LogUtil.INTERCEPTOR.error("", e);
		}
		LogUtil.INTERCEPTOR.debug("Forward to :- [" + returnvalue + "]");
		SessionUtil.put(CommonAction.MESSAGE, "Double post.");
		return returnvalue;
	}

	protected String handleValidToken(ActionInvocation invocation) {
		String returnvalue = CommonAction.ReturnType.HOME.getResult();
		try {
			returnvalue = invocation.invoke();
		} catch (Exception e) {
			LogUtil.INTERCEPTOR.error("", e);
		}
		return returnvalue;
	}
}
