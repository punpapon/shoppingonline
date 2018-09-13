package com.somapait.shoppingonline.core.security.login.domain;


import com.somapait.common.CommonModel;
import com.somapait.domain.SearchCriteria;

public class LoginModel extends CommonModel{

	private static final long serialVersionUID = 8977348480303537403L;

	private String title;
	private String userId; // รหัสผู้ใช้
	private String username; //ชื่อผู้ใช้
	private String fullName; //ชื่อ
	private String password; // รหัสผ่านเดิม
	private String captcha; // ตัวอักษร

	// Object สำหรับเก็บเงื่อนการค้นหา
    private LoginCriteria criteria = new LoginCriteria();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
}
