package com.fujitsu.telehealth.model;

import java.sql.Blob;

public class AppointmentModel {

	public String th_doctor;
	public String th_patient;
	public String th_date;
	public String th_time;
	public String th_status;
	public String th_link;
	public String th_comment;
	public String th_remarks;
	public int th_id;
	public String th_uid;
	public Blob th_image;
	
	public AppointmentModel(String th_doctor, String th_patient, String th_date, String th_time, String th_status,
			String th_link, String th_comment, String th_remarks, int th_id, String th_uid, Blob th_image) {
		super();
		this.th_doctor = th_doctor;
		this.th_patient = th_patient;
		this.th_date = th_date;
		this.th_time = th_time;
		this.th_status = th_status;
		this.th_link = th_link;
		this.th_comment = th_comment;
		this.th_remarks = th_remarks;
		this.th_id = th_id;
		this.th_uid = th_uid;
		this.th_image = th_image;
	}
	
	public AppointmentModel(String th_doctor, String th_patient, String th_date, String th_time, String th_status,
			String th_link, String th_comment, String th_remarks, int th_id, Blob th_image) {
		super();
		this.th_doctor = th_doctor;
		this.th_patient = th_patient;
		this.th_date = th_date;
		this.th_time = th_time;
		this.th_status = th_status;
		this.th_link = th_link;
		this.th_comment = th_comment;
		this.th_remarks = th_remarks;
		this.th_id = th_id;
		this.th_image = th_image;
	}



	public String getTh_doctor() {
		return th_doctor;
	}
	public void setTh_doctor(String th_doctor) {
		this.th_doctor = th_doctor;
	}
	public String getTh_patient() {
		return th_patient;
	}
	public void setTh_patient(String th_patient) {
		this.th_patient = th_patient;
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
	public String getTh_link() {
		return th_link;
	}
	public void setTh_link(String th_link) {
		this.th_link = th_link;
	}
	public String getTh_comment() {
		return th_comment;
	}
	public void setTh_comment(String th_comment) {
		this.th_comment = th_comment;
	}
	public String getTh_remarks() {
		return th_remarks;
	}
	public void setTh_remarks(String th_remarks) {
		this.th_remarks = th_remarks;
	}
	public int getTh_id() {
		return th_id;
	}
	public void setTh_id(int th_id) {
		this.th_id = th_id;
	}
	public String getTh_uid() {
		return th_uid;
	}
	public void setTh_uid(String th_uid) {
		this.th_uid = th_uid;
	}
	public Blob getTh_image() {
		return th_image;
	}
	public void setTh_image(Blob th_image) {
		this.th_image = th_image;
	}

}