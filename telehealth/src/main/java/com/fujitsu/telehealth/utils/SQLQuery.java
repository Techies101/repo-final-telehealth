package com.fujitsu.telehealth.utils;

public class SQLQuery {
	// PATIENT SIDE QUERY
	public static final String SQL_CREATE_NEW_USER = "INSERT INTO "
			+ "										tbl_patient(th_email, th_fname, th_middle_name, th_lname, "
			+ "													th_address, th_age, th_gender, th_contact,"
			+ "													th_password, th_condition, th_uid, th_salt, th_bday)"
			+ "										 VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	public static final String SQL_SELECT_USER = "SELECT * FROM tbl_patient WHERE th_email=? AND th_activated='True'";
	public static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM tbl_patient WHERE th_email=?";
	public static final String SQL_FIND_BY_EMAIL = "SELECT th_email FROM tbl_patient WHERE th_email=?";
	public static final String SQL_SEND_MESSAGE_FROM_GUEST = "INSERT INTO tbl_contactus (th_fullname, th_email, th_message) VALUES (?,?,?)";
	public static final String SQL_UPDATE_ACCOUNT_SQL = "UPDATE tbl_patient SET th_email=?, th_fname=?, th_middle_name=?, th_lname=?, th_address=?, th_age=?, th_gender=?, th_contact=?, th_password=?, th_condition=?,th_bday=? WHERE th_uid=?";

	public static final String SQL_REQUEST_APPOINTMENT = "INSERT INTO tbl_appointment(th_doctor, th_patient, th_date, th_time, th_status, th_comment, th_uid, th_did) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String SQL_UPDATE_TIME = "UPDATE tbl_schedule set th_taken=? WHERE th_date=? AND th_time= ?";
	public static final String SELECT_ALL_APPOINTMENT_BY_PATIENT = "SELECT th_doctor, th_patient, th_date, th_time, th_status, th_link, th_comment, th_remarks, th_id, th_image FROM tbl_appointment WHERE th_uid=?";
	public static final String SQL_UPLOAD_PROOF_PAYMENT = "UPDATE tbl_appointment set th_image=? where th_id= ?";
	public static final String SQL_AGE = "select cast(DATEDIFF(curdate(),th_bday) / 365.25 AS UNSIGNED) AS age from tbl_patient where th_id= ?";
	public static final String SQL_UPDATE_STATUS = "UPDATE tbl_patient SET th_activated=? WHERE th_email=?";
	
	// DOCTOR SIDE QUERY
	public static final String SQL_SELECT_APPOINTMENT = "SELECT * FROM tbl_appointment WHERE th_did=?";
	public static final String SQL_SELECT_SCHEDULE_APPOINTMENT = "SELECT * FROM tbl_appointment WHERE th_did=? AND th_date=?";
	public static final String SQL_SELECT_SCHEDULE = "SELECT * FROM tbl_schedule WHERE th_uid=?";
	public static final String SQL_SELECT_TIME = "SELECT * FROM tbl_schedule WHERE th_uid=? AND th_date=?";
	public static final String SQL_ADD_SCHEDULE = "INSERT tbl_schedule (th_uid, th_time, th_date) values (?,?,?)";
	public static final String SQL_UPDATE_LINK = "UPDATE tbl_appointment SET th_link=? WHERE th_id= ?";
	public static final String SQL_DROP_MEETING = "UPDATE tbl_appointment SET th_status='Declined', th_comment=? WHERE th_id=?";
	public static final String SQL_DROP_SCHEDULE = "DELETE FROM tbl_schedule WHERE th_id=?";
	public static final String SQL_PENDING_STATUS = "UPDATE tbl_appointment set th_status='Payment' where th_id=?";
	public static final String SQL_PAYMENT_STATUS = "UPDATE tbl_appointment set th_status='Done' where th_id= ?";
	public static final String SQL_UPLOAD_LAB_HISTORY = "INSERT tbl_lab (th_uid, th_image, th_date, th_time) values (?,?,?,?)";
	public static final String SQL_LAB_BY_PATIENT = "SELECT * from tbl_lab WHERE th_uid=?";
	public static final String SQL_SELECT_ALL_PATIENT = "SELECT * from tbl_patient";
	public static final String SELECT_PATIENT_BY_UID = "SELECT th_email, th_fname, th_middle_name, th_lname, th_address,th_age, th_gender, th_contact, th_condition, th_fullname, th_uid from tbl_patient WHERE th_uid=?";
	public static final String SELECT_PATIENT_CONSULTATION_BY_UID = "select th_doctor, th_patient, th_date, th_time, th_status, th_link, th_comment, th_remarks, th_id, th_uid, th_image from tbl_appointment where th_uid=?";
	public static final String SELECT_PATIENT_LABORATORY_BY_UID = "select th_doctor, th_patient, th_date, th_time, th_status, th_link, th_comment, th_remarks, th_id, th_uid, th_image from tbl_appointment where th_uid=?";
	public static final String SELECT_SCHEDULE_BY_DOCTOR = "SELECT * FROM tbl_appointment WHERE th_did=? AND th_status='Payment'";

}
