package com.somapait.domain;

import java.io.Serializable;

public class Transaction implements Serializable {

	private static final long serialVersionUID = -5059872355175070364L;

	private String createUser;
	private String createDate;
	private String createRemark;

	private String updateUser;
	private String updateDate;
	private String updateRemark;

	private String createStation;
	private String updateStation;
	private String deleteRemark;

	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateStation() {
		return createStation;
	}
	public void setCreateStation(String createStation) {
		this.createStation = createStation;
	}
	public String getUpdateStation() {
		return updateStation;
	}
	public void setUpdateStation(String updateStation) {
		this.updateStation = updateStation;
	}
	public String getCreateRemark() {
		return createRemark;
	}
	public void setCreateRemark(String createRemark) {
		this.createRemark = createRemark;
	}
	public String getUpdateRemark() {
		return updateRemark;
	}
	public void setUpdateRemark(String updateRemark) {
		this.updateRemark = updateRemark;
	}
	public String getDeleteRemark() {
		return deleteRemark;
	}
	public void setDeleteRemark(String deleteRemark) {
		this.deleteRemark = deleteRemark;
	}
}
