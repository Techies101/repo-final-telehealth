package com.fujitsu.telehealth.model;

public class AppRequestByPatient {

	public String th_doctor;
	public String th_date;
	public String th_time;
	public String th_status;
	public String th_comment;
	public String th_link;
	public String th_doctor_remarks;
	public String th_patient_name;
	public String th_uid;

	public AppRequestByPatient(String th_doctor, String th_patient_name, String th_date, String th_time,
			String th_comment, String th_uid) {
		super();
		this.th_doctor = th_doctor;
		this.th_date = th_date;
		this.th_time = th_time;
		this.th_comment = th_comment;
		this.th_patient_name = th_patient_name;
		this.th_uid = th_uid;
	}

	public AppRequestByPatient(String th_doctor, String th_date, String th_time, String th_status, String th_comment,
			String th_doctor_remarks, String th_link) {
		super();
		this.th_doctor = th_doctor;
		this.th_date = th_date;
		this.th_time = th_time;
		this.th_status = th_status;
		this.th_comment = th_comment;
		this.th_doctor_remarks = th_doctor_remarks;
	}

	public String getTh_link() {
		return th_link;
	}

	public void setTh_link(String th_link) {
		this.th_link = th_link;
	}

	public String getTh_doctor_remarks() {
		return th_doctor_remarks;
	}

	public void setTh_doctor_remarks(String th_doctor_remarks) {
		this.th_doctor_remarks = th_doctor_remarks;
	}

	public String getTh_patient_name() {
		return th_patient_name;
	}

	public void setTh_patient_name(String th_patient_name) {
		this.th_patient_name = th_patient_name;
	}

	public String getTh_uid() {
		return th_uid;
	}

	public void setTh_uid(String th_uid) {
		this.th_uid = th_uid;
	}

	public String getTh_doctor() {
		return th_doctor;
	}

	public void setTh_doctor(String th_doctor) {
		this.th_doctor = th_doctor;
	}

	public String getTh_date() {
		return th_date;
	}

	public void setTh_date(String th_date) {
		this.th_date = th_date;
	}

	public String getTh_time() {
		return th_time;
	}

	public void setTh_time(String th_time) {
		this.th_time = th_time;
	}

	public String getTh_status() {
		return th_status;
	}

	public void setTh_status(String th_status) {
		this.th_status = th_status;
	}

	public String getTh_comment() {
		return th_comment;
	}

	public void setTh_comment(String th_comment) {
		this.th_comment = th_comment;
	}

}
