package com.fujitsu.telehealth.model;

public class NotificationModel {
	
	private String time;
	private String date;
	private String message;
	private String doctor;
	
	public NotificationModel() {};
	
	
	public NotificationModel(String doctor) {
		super();
		this.doctor = doctor;
	}

	public NotificationModel(String time, String date, String message, String doctor) {
		super();
		this.time = time;
		this.date = date;
		this.message = message;
		this.doctor = doctor;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	
	
}
