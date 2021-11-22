package com.fujitsu.telehealth.model;

public class LoginModel {

	private String th_email;
	private String th_password;
	
	public LoginModel() {}
	
	public LoginModel(String th_email, String th_password) {
		super();
		this.th_email = th_email;
		this.th_password = th_password;
	}
	
	public String getTh_email() {
		return th_email;
	}
	public void setTh_email(String th_email) {
		this.th_email = th_email;
	}
	public String getTh_password() {
		return th_password;
	}
	public void setTh_password(String th_password) {
		this.th_password = th_password;
	}
	
	
}
