package com.somapait.domain;

import java.io.Serializable;

public class Operator implements Serializable {

	private static final long serialVersionUID = 7707784123126207916L;

	private String path;

	private String url;
	private String operatorId;
	private String parentId;

	private String systemName;
	private String systemNameTh;
	private String systemNameEn;

	private String menuName;
	private String menuNameTh;
	private String menuNameEn;

	private String functionName;
	private String functionNameTh;
	private String functionNameEn;



	private String type;

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getSystemNameTh() {
		return systemNameTh;
	}
	public void setSystemNameTh(String systemNameTh) {
		this.systemNameTh = systemNameTh;
	}
	public String getSystemNameEn() {
		return systemNameEn;
	}
	public void setSystemNameEn(String systemNameEn) {
		this.systemNameEn = systemNameEn;
	}
	public String getMenuNameTh() {
		return menuNameTh;
	}
	public void setMenuNameTh(String menuNameTh) {
		this.menuNameTh = menuNameTh;
	}
	public String getMenuNameEn() {
		return menuNameEn;
	}
	public void setMenuNameEn(String menuNameEn) {
		this.menuNameEn = menuNameEn;
	}
	public String getFunctionNameTh() {
		return functionNameTh;
	}
	public void setFunctionNameTh(String functionNameTh) {
		this.functionNameTh = functionNameTh;
	}
	public String getFunctionNameEn() {
		return functionNameEn;
	}
	public void setFunctionNameEn(String functionNameEn) {
		this.functionNameEn = functionNameEn;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
