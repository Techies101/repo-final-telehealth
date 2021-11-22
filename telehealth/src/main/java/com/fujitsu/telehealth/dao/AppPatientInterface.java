package com.fujitsu.telehealth.dao;

import java.sql.SQLException;
import java.util.List;

import com.fujitsu.telehealth.model.AppRequestByPatient;
import com.fujitsu.telehealth.model.AppointmentModel;
import com.fujitsu.telehealth.model.AppointmentModel2;
import com.fujitsu.telehealth.model.LoginModel;
import com.fujitsu.telehealth.model.PatientModel;

public interface AppPatientInterface {

	PatientModel validate(LoginModel cred) throws SQLException;

	boolean createNewUser(PatientModel userInfo) throws SQLException;

	boolean checkUserExist(String email) throws SQLException;

	boolean sendMessage(String fullname, String email, String message) throws SQLException;

	PatientModel getUserInfo(String email) throws SQLException;
	
	boolean updateAccount(PatientModel patient) throws SQLException;
	
	boolean requestAppointment(AppRequestByPatient requestInfo) throws SQLException;
	
	List<AppointmentModel2> requestedAppointment(String th_uid) throws SQLException;
	
	List<AppointmentModel> selectLaboratory(String th_uid) throws SQLException;
	
	
}
