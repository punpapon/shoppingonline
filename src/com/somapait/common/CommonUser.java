package com.somapait.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.somapait.domain.Operator;

public class CommonUser implements Serializable {

	private static final long serialVersionUID = 8392198685797505086L;

	public static final String DEFAULT_SESSION_ATTRIBUTE = "user";

	private String userId;
	private String userName;
	private String fullName;
	private String comId; // รหัสของผู้ประกอบการ

	private String startDate;
	private String endDate;
	private String active;
	private String lockStatus;
	private Date passwordDate;

	private Locale locale;

	private String email;

	private List<Operator> lstMenu = new ArrayList<Operator>();
	private LinkedHashMap<String, Operator> lstFunction = new LinkedHashMap<String, Operator>();
	
	private Map<String, Operator> mapOperator = new LinkedHashMap<String, Operator>();		// ใช้สหระบเช็คสิทธ์ และวาด menu
	private Map<String, Operator> mapMenu = new LinkedHashMap<String, Operator>();			// ใช้สำหรับวาด system navigate

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public List<Operator> getLstMenu() {
		return lstMenu;
	}

	public void setLstMenu(List<Operator> lstMenu) {
		this.lstMenu = lstMenu;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LinkedHashMap<String, Operator> getLstFunction() {
		return lstFunction;
	}

	public void setLstFunction(LinkedHashMap<String, Operator> lstFunction) {
		this.lstFunction = lstFunction;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public Date getPasswordDate() {
		return passwordDate;
	}

	public void setPasswordDate(Date passwordDate) {
		this.passwordDate = passwordDate;
	}

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public Map<String, Operator> getMapOperator() {
		return mapOperator;
	}

	public void setMapOperator(Map<String, Operator> mapOperator) {
		this.mapOperator = mapOperator;
	}

	public Map<String, Operator> getMapMenu() {
		return mapMenu;
	}

	public void setMapMenu(Map<String, Operator> mapMenu) {
		this.mapMenu = mapMenu;
	}

}