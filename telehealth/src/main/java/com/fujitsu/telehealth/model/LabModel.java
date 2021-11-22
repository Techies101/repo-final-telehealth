package com.fujitsu.telehealth.model;

import java.sql.Blob;

public class LabModel {
	
	public int th_id;
	public String th_date;
	public String th_time;
	public String th_uid;
	public Blob th_image;
	
	public LabModel(int th_id, String th_date, String th_time, String th_uid, Blob th_image) {
		super();

		this.th_id = th_id;
		this.th_date = th_date;
		this.th_time = th_time;
		this.th_uid = th_uid;
		this.th_image = th_image;
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
	public void setTh_image(Blob th_image) {
		this.th_image = th_image;
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


}