
package com.fujitsu.telehealth.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fujitsu.telehealth.model.AppRequestByPatient;
import com.fujitsu.telehealth.model.AppointmentModel;
import com.fujitsu.telehealth.model.AppointmentModel2;
import com.fujitsu.telehealth.model.LoginModel;
import com.fujitsu.telehealth.model.PatientModel;
import com.fujitsu.telehealth.utils.DBConnection;
import com.fujitsu.telehealth.utils.Encryption.Encrypt;
import com.fujitsu.telehealth.utils.SQLQuery;

public class AppPatientImplementation extends SQLQuery implements AppPatientInterface {

	// Validate User
	@Override
	public PatientModel validate(LoginModel userCredentials) throws SQLException {
		PatientModel userInfo = null;
		Connection con = null;
		try {
			Boolean status = false;
			con = DBConnection.connect();
			PreparedStatement stmt;
			stmt = con.prepareStatement(SQL_SELECT_USER);
			stmt.setString(1, userCredentials.getTh_email());
			ResultSet rs = stmt.executeQuery();
			boolean result = rs.next();

			System.out.println(result);

			if (result) {

				System.out.println(rs.getString("th_password"));
				String saltvalue = rs.getString("th_salt");
				System.out.println(saltvalue);
				status = Encrypt.verifyUserPassword(userCredentials.getTh_password(), rs.getString("th_password"),
						saltvalue);
				if (status) {
					String th_email = rs.getString("th_email");
					String th_fullname = rs.getString("th_fullname");
					String th_uid = rs.getString("th_uid");
					String th_role = rs.getString("th_role");
					userInfo = new PatientModel(th_email, th_fullname, th_uid, th_role);
				}
				String th_email = rs.getString("th_email");
				String th_fullname = rs.getString("th_fullname");
				String th_uid = rs.getString("th_uid");
				String th_role = rs.getString("th_role");
				userInfo = new PatientModel(th_email, th_fullname, th_uid, th_role);

			}
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}
		return userInfo;
	}

	// Insert new user
	@Override
	public boolean createNewUser(PatientModel userInfo) throws SQLException {
		boolean result = false;
		Connection con = null;
		String saltvalue = Encrypt.getSaltvalue(30);
		String encryptedpassword = Encrypt.generateSecurePassword(userInfo.getTh_password(), saltvalue);
		try {
			con = DBConnection.connect();
			PreparedStatement stmt;
			stmt = con.prepareStatement(SQL_CREATE_NEW_USER);
			stmt.setString(1, userInfo.getTh_email());
			stmt.setString(2, userInfo.getTh_fname());
			stmt.setString(3, userInfo.getTh_middle_name());
			stmt.setString(4, userInfo.getTh_lname());
			stmt.setString(5, userInfo.getTh_address());
			stmt.setString(6, userInfo.getTh_age());
			stmt.setString(7, userInfo.getTh_gender());
			stmt.setString(8, userInfo.getTh_contact());
			stmt.setString(9, encryptedpassword);
			stmt.setString(10, userInfo.getTh_condition());
			stmt.setString(11, userInfo.getTh_patientID());

			stmt.setString(12, saltvalue);
			stmt.setString(12, userInfo.getTh_bday());

			int num = stmt.executeUpdate();
			result = num > 0;
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}
		return result;
	}

	// Check User exist
	@Override
	public boolean checkUserExist(String email) throws SQLException {
		Connection con = null;
		boolean res = false;
		try {
			con = DBConnection.connect();
			PreparedStatement stmt;
			stmt = con.prepareStatement(SQL_FIND_BY_EMAIL);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			res = rs.next();
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}
		return res;
	}

	// Get User info
	@Override
	public PatientModel getUserInfo(String email) throws SQLException {
		PatientModel userInfo = null;
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement stmt;
			stmt = con.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String th_email = rs.getString("th_email");
				String th_fname = rs.getString("th_fname");
				String th_middle_name = rs.getString("th_middle_name");
				String th_lname = rs.getString("th_lname");
				String th_address = rs.getString("th_address");
				String th_age = rs.getString("th_age");
				String th_gender = rs.getString("th_gender");
				String th_contact = rs.getString("th_contact");
				String th_password = rs.getString("th_password");
				String th_condition = rs.getString("th_condition");
				String th_fullname = rs.getString("th_fullname");
				String th_uid = rs.getString("th_uid");
				userInfo = new PatientModel(th_email, th_fname, th_middle_name, th_lname, th_address, th_age, th_gender,
						th_contact, th_password, th_condition, th_fullname, th_uid);
			}
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}

		return userInfo;
	}

	// Send Message
	@Override
	public boolean sendMessage(String fullname, String email, String message) throws SQLException {
		boolean success = false;
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement stmt;
			stmt = con.prepareStatement(SQL_SEND_MESSAGE_FROM_GUEST);
			stmt.setString(1, fullname);
			stmt.setString(2, email);
			stmt.setString(3, message);
			int num = stmt.executeUpdate();
			success = num > 0;
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		}
		return success;
	}

	// Update Account
	public boolean updateAccount(PatientModel patient) throws SQLException {
		boolean result = false;
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement stmt;
			stmt = con.prepareStatement(SQL_UPDATE_ACCOUNT_SQL);
			stmt.setString(1, patient.getTh_email());
			stmt.setString(2, patient.getTh_fname());
			stmt.setString(3, patient.getTh_middle_name());
			stmt.setString(4, patient.getTh_lname());
			stmt.setString(5, patient.getTh_address());
			stmt.setString(6, patient.getTh_age());
			stmt.setString(7, patient.getTh_gender());
			stmt.setString(8, patient.getTh_contact());
			stmt.setString(9, patient.getTh_password());
			stmt.setString(10, patient.getTh_condition());
			stmt.setString(11, patient.getTh_patientID());
			int num = stmt.executeUpdate();
			result = num > 0;
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}
		return result;
	}

	@Override
	public boolean requestAppointment(AppRequestByPatient requestInfo) throws SQLException {

		boolean result = false;
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement stmt;

			String parts[] = requestInfo.getTh_doctor().split(" ", 2);

			stmt = con.prepareStatement(SQL_REQUEST_APPOINTMENT);
			stmt.setString(1, String.format("%s", parts[1]));
			stmt.setString(2, requestInfo.getTh_patient_name());
			stmt.setString(3, requestInfo.getTh_date());
			stmt.setString(4, requestInfo.getTh_time());
			stmt.setString(5, "Pending");
			stmt.setString(6, requestInfo.getTh_comment());
			stmt.setString(7, requestInfo.getTh_uid());
			stmt.setString(8, String.format("%s", parts[0]));
			int rs = stmt.executeUpdate();
			result = rs > 0;
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}

		return result;
	}

	@Override
	public List<AppointmentModel2> requestedAppointment(String th_uid) throws SQLException {
		List<AppointmentModel2> listRequest = new ArrayList<>();
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement;
			preparedStatement = con.prepareStatement(SELECT_ALL_APPOINTMENT_BY_PATIENT);
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

				listRequest.add(new AppointmentModel2(doctor, patient, date, time, status, link, comment, remarks,
						number, blob));
			}
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}

		return listRequest;
	}

	@Override
	public List<AppointmentModel> selectLaboratory(String th_uid) throws SQLException {
		List<AppointmentModel> tbl_appointment = new ArrayList<>();
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement preparedStatement;
			preparedStatement = con.prepareStatement(SELECT_PATIENT_LABORATORY_BY_UID);
			preparedStatement.setString(1, th_uid);
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
						th_comment, th_remarks, th_id, th_uid, th_image));
			}
		} catch (SQLException e) {
			DBConnection.printSQLException(e);
		} finally {
			con.close();
		}
		return tbl_appointment;
	}

	public boolean updateUserStatus(String email) throws SQLException {
		Connection con = null;
		try {
			con = DBConnection.connect();
			PreparedStatement stmt = con.prepareStatement(SQL_UPDATE_STATUS);
			stmt.setString(1, "True");
			stmt.setString(2, email);
			int rs = stmt.executeUpdate();
			return rs > 0;
		} catch (SQLException ex) {
			DBConnection.printSQLException(ex);
		} finally {
			con.close();
		}
		return false;
	}

}
