package com.somapait.shoppingonline.web.interceptor;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import util.log.LogUtil;
import util.web.SessionUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.somapait.common.CommonAction;
import com.somapait.common.CommonUser;
import com.somapait.shoppingonline.core.config.parameter.domain.ParameterConfig;

public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = -1921944000166163942L;

	@Override
	public void destroy() {
		LogUtil.INTERCEPTOR.info("");
	}

	@Override
	public void init() {
		LogUtil.INTERCEPTOR.info("");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = CommonAction.ReturnType.HOME.getResult();

		String actinoName = null;
		CommonUser commonUser = null;
		String sessionId = null;
		try {
			actinoName = invocation.getProxy().getActionName();
			commonUser = (CommonUser) SessionUtil.get(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
			sessionId = SessionUtil.getId();
			LogUtil.INTERCEPTOR.debug("Call: " + actinoName + " User: " + commonUser + " SessionId: " + sessionId);
		} catch (Exception e) {
			LogUtil.INTERCEPTOR.error("", e);
		}

		try {
			if (actinoName.indexOf("Login") > 0) {
				LogUtil.INTERCEPTOR.debug("This login system them skip check");
				if (commonUser == null) {
					LogUtil.INTERCEPTOR.debug("Not have user in session");
					invocation.getInvocationContext().setLocale(ParameterConfig.getApplication().getApplicationLocale());
				} else {
					LogUtil.INTERCEPTOR.debug("User is alive");
					invocation.getInvocationContext().setLocale(commonUser.getLocale());
				}
				setNoCache(invocation);
				return invocation.invoke();
			}
		} catch (Exception e) {
			LogUtil.INTERCEPTOR.error("", e);
		}

		try {
			LogUtil.INTERCEPTOR.debug("The system require check user");
			if (commonUser == null) {
				LogUtil.INTERCEPTOR.debug("Not have user in session");
				invocation.getInvocationContext().setLocale(ParameterConfig.getApplication().getApplicationLocale());

				LogUtil.INTERCEPTOR.debug("The session " + sessionId + " is expired.");
				String[] messages = { CommonAction.MessageType.WARING.getType(), "Session expired.", null };
				SessionUtil.put(CommonAction.MESSAGE, messages);
			} else {
				LogUtil.INTERCEPTOR.debug("User is alive");
				invocation.getInvocationContext().setLocale(commonUser.getLocale());
				setNoCache(invocation);
				result = invocation.invoke();
			}

		} catch (Exception e) {
			LogUtil.INTERCEPTOR.error("", e);
		}
		return result;
	}

	private void setNoCache(ActionInvocation invocation) {
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	}
}
