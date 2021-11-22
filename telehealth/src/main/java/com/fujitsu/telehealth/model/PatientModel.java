package com.fujitsu.telehealth.model;

public class PatientModel {

	private String th_patientID = "PT-";
	private String th_email;
	private String th_fname;
	private String th_middle_name;
	private String th_lname;
	private String th_address;
	private String th_age;
	private String th_gender;
	private String th_contact;
	private String th_password;
	private String th_condition;
	private String th_fullname;
	private String th_uid;
	private String role;
	private String code;

	public PatientModel() {}

	public PatientModel(String th_patientID, String th_email, String th_contact, String th_fullname, String th_uid) {
		super();
		this.th_patientID = th_patientID;
		this.th_email = th_email;
		this.th_contact = th_contact;
		this.th_fullname = th_fullname;
		this.th_uid = th_uid;
	}

	public PatientModel(String th_email, String th_fullname, String th_uid, String th_role) {
		super();
		this.th_email = th_email;
		this.th_fullname = th_fullname;
		this.th_uid = th_uid;
		this.role = th_role;
	}
	
	public PatientModel(String th_email, String th_fname, String th_middle_name, String th_lname, String th_address,
			String th_age, String th_gender, String th_contact, String th_condition, String th_uid) {
		super();
		this.th_email = th_email;
		this.th_fname = th_fname;
		this.th_middle_name = th_middle_name;
		this.th_lname = th_lname;
		this.th_address = th_address;
		this.th_age = th_age;
		this.th_gender = th_gender;
		this.th_contact = th_contact;
		this.th_condition = th_condition;
		this.th_uid = th_uid;
	}


	public PatientModel(String th_email, String th_fname, String th_middle_name, String th_lname, String th_address,
			String th_age, String th_gender, String th_contact, String th_password, String th_condition,
			String th_fullname, String th_uid) {
		super();
		this.th_email = th_email;
		this.th_fname = th_fname;
		this.th_middle_name = th_middle_name;
		this.th_lname = th_lname;
		this.th_address = th_address;
		this.th_age = th_age;
		this.th_gender = th_gender;
		this.th_contact = th_contact;
		this.th_password = th_password;
		this.th_condition = th_condition;
		this.th_fullname = th_fullname;
		this.th_uid = th_uid;
	}
	
	public PatientModel(String th_patientID, String th_email, String th_fname, String th_middle_name, String th_lname,
			String th_address, String th_age, String th_gender, String th_contact, String th_password,
			String th_condition) {
		super();
		this.th_patientID = th_patientID;
		this.th_email = th_email;
		this.th_fname = th_fname;
		this.th_middle_name = th_middle_name;
		this.th_lname = th_lname;
		this.th_address = th_address;
		this.th_age = th_age;
		this.th_gender = th_gender;
		this.th_contact = th_contact;
		this.th_password = th_password;
		this.th_condition = th_condition;
	}
	
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	public String getTh_uid() {
		return th_uid;
	}

	public void setTh_uid(String th_uid) {
		this.th_uid = th_uid;
	}


	public String getTh_patientID() {
		return th_patientID;
	}

	public void setTh_patientID(String th_patientID) {
		this.th_patientID = th_patientID;
	}
	
	

	public String getTh_email() {
		return th_email;
	}


	public void setTh_email(String th_email) {
		this.th_email = th_email;
	}

	public String getTh_fname() {
		return th_fname;
	}

	public void setTh_fname(String th_fname) {
		this.th_fname = th_fname;
	}

	public String getTh_middle_name() {
		return th_middle_name;
	}

	public void setTh_middle_name(String th_middle_name) {
		this.th_middle_name = th_middle_name;
	}

	public String getTh_lname() {
		return th_lname;
	}

	public void setTh_lname(String th_lname) {
		this.th_lname = th_lname;
	}

	public String getTh_address() {
		return th_address;
	}

	public void setTh_address(String th_address) {
		this.th_address = th_address;
	}

	public String getTh_age() {
		return th_age;
	}

	public void setTh_age(String th_age) {
		this.th_age = th_age;
	}

	public String getTh_gender() {
		return th_gender;
	}

	public void setTh_gender(String th_gender) {
		this.th_gender = th_gender;
	}

	public String getTh_contact() {
		return th_contact;
	}

	public void setTh_contact(String th_contact) {
		this.th_contact = th_contact;
	}

	public String getTh_password() {
		return th_password;
	}

	public void setTh_password(String th_password) {
		this.th_password = th_password;
	}

	public String getTh_condition() {
		return th_condition;
	}

	public void setTh_condition(String th_condition) {
		this.th_condition = th_condition;
	}

	public String getTh_fullname() {
		return th_fullname;
	}

	public void setTh_fullname(String th_fullname) {
		this.th_fullname = th_fullname;
	}

}
