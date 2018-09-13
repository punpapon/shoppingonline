package com.somapait.common;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;

import util.log.LogUtil;
import util.web.SessionUtil;

import com.somapait.common.CommonUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.somapait.common.CommonModel.PageType;
import com.somapait.domain.GlobalVariable;
import com.somapait.domain.SearchCriteria;
import com.somapait.exception.AuthenticateException;
import com.somapait.exception.AuthorizationException;
import com.somapait.exception.DuplicateException;
import com.somapait.exception.MaxExceedException;
import com.somapait.shoppingonline.core.config.parameter.domain.ParameterConfig;
import com.somapait.shoppingonline.core.security.authorization.domain.Authorize;
import com.somapait.shoppingonline.core.security.authorization.domain.PFCode;
import com.somapait.shoppingonline.core.security.authorization.service.AuthorizationService;


public class CommonAction extends ActionSupport implements Serializable {

	private static final long serialVersionUID = 2843485154046138037L;

	private Logger log = loggerInititial();

	protected Logger loggerInititial() {
		return LogUtil.COMMON;
	}

	public Locale LOCALE;
	public CommonUser USER;
	public String[] LPP = ParameterConfig.getApplication().getLpp();
	
	public Authorize ATH = new Authorize();
	public PFCode PF_CODE;
	public String P_CODE;
	public String F_CODE;
	
	// เพิ่มเติม
	public String captchaEnable;

	private boolean useAddMenu = true;
	private boolean useProfileMenu = true;
	private boolean useHomeMenu = true;
	private boolean useRefreshMenu = true;
	private String alertMaxExceed = MaxExceedType.NORMAL.getType();

	private int LIMIT_LINE_ERROR = 10;
	public static String MESSAGE = "message";

	public AuthorizationService AUTHORIZATION_SERVICE = new AuthorizationService();
	
	public enum MaxExceedType {
		ALERT("A"), CONFIRM("C"), NORMAL("N");

		private String type;

		private MaxExceedType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}

	/**
	 * @Description: Constuctor for load user and locale
	 */
	public CommonAction() {

		if (SessionUtil.get(CommonUser.DEFAULT_SESSION_ATTRIBUTE) == null) {
			LOCALE = ParameterConfig.getApplication().getApplicationLocale();
		} else {
			USER = (CommonUser) SessionUtil.get(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
			LOCALE = USER.getLocale();
		}

		SessionUtil.put(com.opensymphony.xwork2.interceptor.I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, LOCALE);
		ActionContext.getContext().setLocale(LOCALE);

		if (SessionUtil.get(MESSAGE) == null) {
			clearMessages();
		} else {
			clearMessages();
			Object object = SessionUtil.get(MESSAGE);
			if ((object instanceof String[]) == true) {
				String[] messages = (String[]) object;
				for (String message : messages) {
					addActionMessage(message);
				}
			} else {
				addActionMessage(object.toString());
			}
			SessionUtil.remove(MESSAGE);
		}
	}

	/**
	 * @Description: Class enum for return result
	 */
	public enum ReturnType {
		MAINPAGE("mainpage"), INIT("init"), SEARCH("search"), SEARCH_DO("searchDo"), DOWNLOAD("download"), UPLOAD("upload"), ADD_EDIT("addEdit"), ADD_DO("addDo"), EDIT_DO("editDo"), HOME("home"), THIS(
				null);

		private String result;

		private ReturnType(String result) {
			this.result = result;
		}

		public String getResult() {
			return result;
		}
	}

	/**
	 * @Description: Class enum for message type
	 */
	public enum MessageType {
		ERROR("E"), SUCCESS("S"), WARING("W");

		private String type;

		private MessageType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}

	/**
	 * @Description: Class enum for return result
	 */
	public enum ResultType {
		CHAIN("chain"), BASIC("");

		private String type;

		private ResultType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}
	
	/**
	 * @Description: Load authorization
	 * @Return: void
	 */
	public void getAuthorization(PFCode pfcode) throws Exception {

		if (getUser() == null) {
			log.debug("Session expired. , sessionId: " + SessionUtil.getId() + " , user: " + getUser());
			throw new AuthorizationException(GlobalVariable.Message.SESSION_EXPIRED.getValue());
		}
		this.ATH = AUTHORIZATION_SERVICE.checkAuthorize(pfcode, getUser().getMapOperator());
		this.P_CODE = pfcode.getProgramCode();
		this.PF_CODE = pfcode;
	}

	public void clearSearchCriteria(String className) {
		try {
			Map<String, Object> sessions = SessionUtil.get();
			for (String key : sessions.keySet()) {
				log.debug("Key :- " + key);
				log.debug("Value :- " + sessions.get(key));
				log.debug("Class :- [" + sessions.get(key).getClass().getName() + " == " + className + "]");
				if (sessions.get(key).getClass().getName().equals(className)) {
					log.debug("Remove :- [" + key + "]");
					SessionUtil.remove(key);
				}
			}
			log.debug("------");
		} catch (Exception e) {
			log.error("", e);
		}
	}

	public String getErrorMessage(Exception e) {
		String error = e.getMessage() + "<br>";
		for (int i = 0; i < LIMIT_LINE_ERROR; i++) {
			if (e.getStackTrace().length <= i) {
				break;
			} else {
				error += e.getStackTrace()[i] + "<br>";
			}
		}
		return error;
	}

	public void setMessage(MessageType type, String subject, ResultType resultType) {
		setMessage(type, subject, null, resultType);
	}

	public void setMessage(MessageType type, String subject, String detail, ResultType resultType) {
		if (resultType.equals(ResultType.CHAIN)) {
			String[] messages = { type.getType(), subject, detail };
			SessionUtil.put(MESSAGE, messages);
		} else {
			clearMessagesException();
			addActionMessage(type.getType());
			addActionMessage(subject);
			addActionMessage(detail);
		}
	}

	private void clearMessagesException() {
		SessionUtil.remove(MESSAGE);
		clearMessages();
	}

	private void setMessageConfrimMaxExceed(String message) {
		alertMaxExceed = MaxExceedType.CONFIRM.getType();
		clearMessagesException();
		addActionMessage(message);
	}

	protected void setMessageAlertMaxExceed(String message) {
		alertMaxExceed = MaxExceedType.ALERT.getType();
		clearMessagesException();
		addActionMessage(message);
	}

	public String getP_CODE() {
		return P_CODE;
	}

	public void setP_CODE(String p_CODE) {
		P_CODE = p_CODE;
	}

	public String getF_CODE() {
		return F_CODE;
	}

	public void setF_CODE(String f_CODE) {
		F_CODE = f_CODE;
	}

	public boolean isUseAddMenu() {
		return useAddMenu;
	}

	public void setUseAddMenu(boolean useAddMenu) {
		this.useAddMenu = useAddMenu;
	}

	public boolean isUseProfileMenu() {
		return useProfileMenu;
	}

	public void setUseProfileMenu(boolean useProfileMenu) {
		this.useProfileMenu = useProfileMenu;
	}

	public boolean isUseHomeMenu() {
		return useHomeMenu;
	}

	public void setUseHomeMenu(boolean useHomeMenu) {
		this.useHomeMenu = useHomeMenu;
	}

	public boolean isUseRefreshMenu() {
		return useRefreshMenu;
	}

	public void setUseRefreshMenu(boolean useRefreshMenu) {
		this.useRefreshMenu = useRefreshMenu;
	}

	/**
	 * Arunya.k ยุบ Code มาไว้ที่เดียว Action ที่ Extend ไปได้มี Code ไม่ยาว
	 *
	 * @throws AuthorizationException
	 * */
	public String manageSearch(Connection conn, CommonModel model, SearchCriteria criteriaModel, String function) throws AuthorizationException {
		String result = ReturnType.SEARCH.getResult();

		if ((model.getCriteria().getCriteriaKey() == null) || (model.getCriteria().getCriteriaKey().equals(""))) {
			model.getCriteria().setDefaultHeaderSorts();
		}

		model.setPage(CommonModel.PageType.SEARCH);
		if ((criteriaModel.getCriteriaKey() == null) || (criteriaModel.getCriteriaKey().equals(""))) {
			log.debug("3. Check criteria...[GENERATE]");
			clearSearchCriteria(criteriaModel.getClass().getName());
			String criteriaKey = String.valueOf(Calendar.getInstance().getTimeInMillis());
			criteriaModel.setCriteriaKey(criteriaKey);

			criteriaModel.setStart(1);
			criteriaModel.setCheckMaxExceed(true);

			SessionUtil.put(criteriaModel.getCriteriaKey(), criteriaModel);
		} else {
			log.debug("3. Check criteria...[LOAD]");
			SearchCriteria criteriaSession = (SearchCriteria) SessionUtil.get(criteriaModel.getCriteriaKey());
			criteriaSession.setCriteriaKey(criteriaModel.getCriteriaKey());

			if (criteriaModel.getStart() > 0) {
				criteriaSession.setStart(criteriaModel.getStart());
				criteriaSession.setCheckMaxExceed(criteriaModel.isCheckMaxExceed());
			}

			log.debug("   Check header sorts select...[" + criteriaModel.getHeaderSortsSelect() + "]");
			log.debug("   Check header sorts...[" + criteriaModel.getHeaderSorts().length + "]");
			if (criteriaModel.getHeaderSortsSelect() != null) {
				criteriaSession.setHeaderSorts(criteriaModel.getHeaderSorts());
				criteriaSession.setHeaderSortsSelect(criteriaModel.getHeaderSortsSelect());
			}

			criteriaModel = criteriaSession;
			model.setCriteria(criteriaModel);

		}

		return result;
	}

	public void manageSearchResult(CommonModel model, List<?> lstResult) throws Exception {
		try {
			if (lstResult == null || lstResult.size() == 0) {
				setMessage(CommonAction.MessageType.WARING, getText("30011"), ResultType.BASIC);
			} else {
				model.setLstResult(lstResult);
			}
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * จัดการ Error ต่างๆ
	 * @param conn
	 * @param operatorId คือ function code
	 * @param className คือ class ของ error นั้นๆ
	 * @param e คือ exception error
	 * @param model
	 * @throws AuthorizationException
	 */
	public void manageException(Connection conn, String operatorId, Object className, Exception e, CommonModel model) throws AuthorizationException {

		if (e instanceof MaxExceedException) {
			setMessageConfrimMaxExceed(getText("30014").replace("xxx", String.valueOf(model.getCriteria().getTotalResult())));
		} else if (e instanceof DuplicateException) {
			setMessage(CommonAction.MessageType.WARING, getText("10003"), ResultType.BASIC);
		} else if (e instanceof AuthenticateException) {
			setMessage(CommonAction.MessageType.ERROR, getText("10035"), ResultType.BASIC);
		} else if (e instanceof AuthorizationException) {
			throw (AuthorizationException) e;
		} else {
			if (model.getPage().equals(PageType.ADD)) {
				setMessage(CommonAction.MessageType.ERROR, getText("30007"), getErrorMessage(e), ResultType.BASIC);
			} else if (model.getPage().equals(PageType.EDIT)) {
				setMessage(CommonAction.MessageType.ERROR, getText("30008"), getErrorMessage(e), ResultType.BASIC);
			} else if (model.getPage().equals(PageType.SEARCH) && model.getCriteria() != null) {
				if (model.getCriteria().getStatusForUpdate().equals("Y")) {
					// กรณีไม่สามารถทำการเปลี่ยนเป็นสถานะใช้งานได้
					setMessage(CommonAction.MessageType.ERROR, getText("30012"), getErrorMessage(e), ResultType.CHAIN);
				} else if (model.getCriteria().getStatusForUpdate().equals("N")) {
					// กรณีไม่สามารถทำการเปลี่ยนเป็นสถานะยกเลิกได้
					setMessage(CommonAction.MessageType.ERROR, getText("30013"), getErrorMessage(e), ResultType.CHAIN);
				} else {
					setMessage(CommonAction.MessageType.ERROR, getText("30010"), getErrorMessage(e), ResultType.BASIC);
				}
			} else {
				setMessage(CommonAction.MessageType.ERROR, getText("30010"), getErrorMessage(e), ResultType.BASIC);
			}
		}

	}

	/*
	 * initial ระบบกรณีที่ Initial ที่มีเงื่อนไข
	 */
	public String manageInitial(Connection conn, CommonModel model, SearchCriteria criteria, String function, PageType pageType) throws Exception {

		String result = ReturnType.SEARCH.getResult();

		model.setPage(pageType);

		if (model.getCriteria().getCriteriaKey() != null && (model.getCriteria().getCriteriaKey().isEmpty() == false)) {
			SessionUtil.remove(model.getCriteria().getCriteriaKey());
			model.getCriteria().setCriteriaKey("");
		}

		model.setCriteria(criteria);

		model.getCriteria().setStart(1);
		model.getCriteria().setLinePerPage(Integer.parseInt(LPP[0]));
		model.getCriteria().setCheckMaxExceed(true);

		return result;
	}

	/*
	 * initial ระบบกรณีที่ต้องการหน้าแรกไม่มี criteria เงื่อนไข
	 */
	public String manageInitial(Connection conn, CommonModel model, String function, PageType pageType) throws Exception {
		String result = ReturnType.SEARCH.getResult();
		model.setPage(pageType);
		return result;
	}

	public String manageGotoAdd(Connection conn, CommonModel model) throws AuthorizationException {
		String result = ReturnType.ADD_EDIT.getResult();
		model.setPage(CommonModel.PageType.ADD);
		return result;
	}

	public String manageAdd(Connection conn, CommonModel model) throws AuthorizationException {
		setMessage(CommonAction.MessageType.SUCCESS, getText("30003"), ResultType.BASIC);
		String result = ReturnType.ADD_EDIT.getResult();
		model.setPage(CommonModel.PageType.ADD);
		return result;
	}

	public String manageEdit(Connection conn, CommonModel model) throws AuthorizationException {
		setMessage(CommonAction.MessageType.SUCCESS, getText("30004"), ResultType.CHAIN);
		String result = ReturnType.ADD_EDIT.getResult();
		model.setPage(CommonModel.PageType.EDIT);
		return result;
	}

	public String manageUpdateActive(Connection conn, CommonModel model) throws AuthorizationException {
		if (model.getCriteria().getStatusForUpdate().equals("Y")) {
			setMessage(CommonAction.MessageType.SUCCESS, getText("30001"), ResultType.CHAIN);
		} else if (model.getCriteria().getStatusForUpdate().equals("N")) {
			setMessage(CommonAction.MessageType.SUCCESS, getText("30002"), ResultType.CHAIN);
		}
		String result = ReturnType.SEARCH_DO.getResult();
		return result;
	}

	public String manageDelete(Connection conn, CommonModel model) throws AuthorizationException {
		setMessage(CommonAction.MessageType.SUCCESS, getText("30005"), ResultType.CHAIN);
		String result = ReturnType.SEARCH_DO.getResult();
		return result;
	}

	public String manageGotoView(Connection conn, CommonModel model) throws AuthorizationException {
		String result = ReturnType.ADD_EDIT.getResult();
		model.setPage(CommonModel.PageType.VIEW);
		return result;
	}

	public String manageGotoEdit(Connection conn, CommonModel model) throws AuthorizationException {
		String result = ReturnType.ADD_EDIT.getResult();
		model.setPage(CommonModel.PageType.EDIT);
		return result;
	}

	public String getAlertMaxExceed() {
		return alertMaxExceed;
	}

	public void setAlertMaxExceed(String alertMaxExceed) {
		this.alertMaxExceed = alertMaxExceed;
	}

	private String getUserIdFromSession() {
		String userId = null;
		if (USER != null) {
			userId = USER.getUserId();
		}
		return userId;
	}

	public String getCaptchaEnable() {
		return captchaEnable;
	}

	public void setCaptchaEnable(String captchaEnable) {
		this.captchaEnable = captchaEnable;
	}
	
	// เพิ่มเติม
	public CommonUser getUser() {
		return (CommonUser) SessionUtil.get(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
	}

}
