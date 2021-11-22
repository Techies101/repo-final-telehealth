package com.fujitsu.telehealth.model;

import java.sql.Blob;

public class AppointmentModel2 {

	private int meetingNumber;
	private String meetingPatient;
	private String meetingDoctor;
	private String meetingDate;
	private String meetingTime;
	private String meetingStatus;
	private String meetingLink;
	private String meetingComment;
	private String meetingRemarks;
	private Blob meetingImage;

	public AppointmentModel2(String meetingDoctor, String meetingPatient, String meetingDate, String meetingTime,
			String meetingStatus, String meetingLink, String meetingComment, String meetingRemarks, int meetingNumber,
			Blob meetingImage) {
		super();
		this.meetingDoctor = meetingDoctor;
		this.meetingPatient = meetingPatient;
		this.meetingDate = meetingDate;
		this.meetingTime = meetingTime;
		this.meetingStatus = meetingStatus;
		this.meetingLink = meetingLink;
		this.meetingComment = meetingComment;
		this.meetingRemarks = meetingRemarks;
		this.meetingNumber = meetingNumber;
		this.meetingImage = meetingImage;
	}

	public Blob getMeetingImage() {
		return meetingImage;
	}

	public void setMeetingImage(Blob meetingImage) {
		this.meetingImage = meetingImage;
	}

	public String getMeetingComment() {
		return meetingComment;
	}

	public void setMeetingComment(String meetingComment) {
		this.meetingComment = meetingComment;
	}

	public String getMeetingPatient() {
		return meetingPatient;
	}

	public void setMeetingPatient(String meetingPatient) {
		this.meetingPatient = meetingPatient;
	}

	public String getMeetingDoctor() {
		return meetingDoctor;
	}

	public void setMeetingDoctor(String meetingDoctor) {
		this.meetingDoctor = meetingDoctor;
	}

	public int getMeetingNumber() {
		return meetingNumber;
	}

	public void setMeetingNumber(int meetingNumber) {
		this.meetingNumber = meetingNumber;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getMeetingLink() {
		return meetingLink;
	}

	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}

	public String getMeetingRemarks() {
		return meetingRemarks;
	}

	public void setMeetingRemarks(String meetingRemarks) {
		this.meetingRemarks = meetingRemarks;
	}

	public String getMeetingStatus() {
		return meetingStatus;
	}

	public void setMeetingStatus(String meetingStatus) {
		this.meetingStatus = meetingStatus;
	}

}
