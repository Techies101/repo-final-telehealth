package com.fujitsu.telehealth.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fujitsu.telehealth.model.AppointmentModel;
import com.fujitsu.telehealth.model.AppointmentModel2;
import com.fujitsu.telehealth.model.LabModel;
import com.fujitsu.telehealth.model.PatientModel;
import com.fujitsu.telehealth.utils.DBConnection;
import com.fujitsu.telehealth.utils.SQLQuery;

public class AppDoctorImplementation extends SQLQuery implements AppDoctorInterface {

	// Display Request
	@Override
	public List<AppointmentModel2> displayMeeting(String th_uid) throws SQLException {
		List<AppointmentModel2> meeting = new ArrayList<>();
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement;
			preparedStatement = con.prepareStatement(SQL_SELECT_APPOINTMENT);
			preparedStatement.setString(1, th_uid);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String doctor = rs.getString("th_doctor");
				String patient = rs.getString("th_patient");
				String date = rs.getString("th_date");
				String time = rs.getString("th_time");
				String status = rs.getString("th_status");
				String link = rs.getString("th_link");
				String comment = rs.getString("th_comment");
				String remarks = rs.getString("th_remarks");
				int number = rs.getInt("th_id");
				Blob blob = rs.getBlob("th_image");
				// byte byteArray[] = blob.getBytes(1, (int) blob.length());
				// response.setContentType("image/gif");
				// OutputStream os = r.getOutputStream();
				// os.write(byteArray);
				// os.flush();
				// os.close();
				// Part image = rs.getInt("th_id");

				meeting.add(new AppointmentModel2(doctor, patient, date, time, status, link, comment, remarks, number,
						blob));
			}
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}

		return meeting;
	}

	// Update Meeting
	@Override
	public boolean updateMeeting(int id, String link) throws SQLException {
		boolean result = false;
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement = con.prepareStatement(SQL_UPDATE_LINK);
			preparedStatement.setString(1, link);
			preparedStatement.setInt(2, id);
			int num = preparedStatement.executeUpdate();
			result = num > 0;
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}
		return result;

	}

	// Drop meeting
	@Override
	public boolean dropMeeting(int id, String message) throws SQLException {
		boolean result = false;
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement = con.prepareStatement(SQL_DROP_MEETING);
			preparedStatement.setString(1, message);
			preparedStatement.setFloat(2, id);
			int num = preparedStatement.executeUpdate();
			result = num > 0;
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}
		return result;
	}

	public void approveMeeting(int id, String type) throws SQLException {
		String command = null;
		Connection con = null;
		if (type.equals("Pending"))
			command = SQL_PENDING_STATUS;
		if (type.equals("Payment"))
			command = SQL_PAYMENT_STATUS;

		try {
			con = DBConnection.connect();
			PreparedStatement stmt = con.prepareStatement(command);
			stmt.setFloat(1, id);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}

	}

	@Override
	public List<PatientModel> selectAllPatients() throws SQLException {
		List<PatientModel> tbl_patient = new ArrayList<>();
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement = con.prepareStatement(SQL_SELECT_ALL_PATIENT);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String th_patientid = rs.getString("th_patientid");
				String th_email = rs.getString("th_email");
				String th_contact = rs.getString("th_contact");
				String th_fullname = rs.getString("th_fullname");
				String th_uid = rs.getString("th_uid");
				String th_role = rs.getString("th_role");
				tbl_patient.add(new PatientModel(th_patientid, th_email, th_contact, th_fullname, th_uid, th_role));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return tbl_patient;
	}

	@Override
	public PatientModel selectPatient(String uid) throws SQLException {
		PatientModel tbl_patient = null;
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_PATIENT_BY_UID);
			preparedStatement.setString(1, uid);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String th_patientid = rs.getString("th_patientid");
				String th_email = rs.getString("th_email");
				String th_fname = rs.getString("th_fname");
				String th_middle_name = rs.getString("th_middle_name");
				String th_lname = rs.getString("th_lname");
				String th_address = rs.getString("th_address");
				String th_age = rs.getString("th_age");
				String th_gender = rs.getString("th_gender");
				String th_contact = rs.getString("th_contact");
				String th_conditon = rs.getString("th_condition");
				tbl_patient = new PatientModel(th_patientid, th_email, th_fname, th_middle_name, th_lname, th_address,
						th_age, th_gender, th_contact, th_conditon, uid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return tbl_patient;
	}

	@Override
	public List<AppointmentModel> selectConsultation(String uid) throws SQLException {
		List<AppointmentModel> tbl_appointment = new ArrayList<>();
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_PATIENT_CONSULTATION_BY_UID);
			preparedStatement.setString(1, uid);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String th_doctor = rs.getString("th_doctor");
				String th_patient = rs.getString("th_patient");
				String th_date = rs.getString("th_date");
				String th_time = rs.getString("th_time");
				String th_status = rs.getString("th_status");
				String th_link = rs.getString("th_link");
				String th_comment = rs.getString("th_comment");
				String th_remarks = rs.getString("th_remarks");
				int th_id = rs.getInt("th_id");
				Blob th_image = rs.getBlob("th_image");

				tbl_appointment.add(new AppointmentModel(th_doctor, th_patient, th_date, th_time, th_status, th_link,
						th_comment, th_remarks, th_id, uid, th_image));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return tbl_appointment;
	}

	@Override
	public List<LabModel> labImageList(String th_uid) throws SQLException {
		List<LabModel> lab = new ArrayList<>();
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement = con.prepareStatement(SQL_LAB_BY_PATIENT);
			preparedStatement.setString(1, th_uid);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("th_id");
				String date = rs.getString("th_date");
				String time = rs.getString("th_time");
				String uid = rs.getString("th_uid");
				Blob blob = rs.getBlob("th_image");
				lab.add(new LabModel(id, date, time, uid, blob));
			}
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}
		return lab;
	}

}
