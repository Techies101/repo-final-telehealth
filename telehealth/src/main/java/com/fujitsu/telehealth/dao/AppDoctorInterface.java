package com.fujitsu.telehealth.dao;

import java.sql.SQLException;
import java.util.List;

import com.fujitsu.telehealth.model.AppointmentModel;
import com.fujitsu.telehealth.model.AppointmentModel2;
import com.fujitsu.telehealth.model.LabModel;
import com.fujitsu.telehealth.model.PatientModel;

public interface AppDoctorInterface {

	public List<AppointmentModel2> displayMeeting(String uid) throws SQLException;

	public boolean updateMeeting(int id, String link) throws SQLException;

	public boolean dropMeeting(int id, String message) throws SQLException;
	
	public void approveMeeting(int id, String type) throws SQLException;
	
	List<PatientModel> selectAllPatients() throws SQLException;
	
	PatientModel selectPatient(String uid) throws SQLException;
	
	List<AppointmentModel> selectConsultation(String uid) throws SQLException;
	
	List<LabModel> labImageList(String uid) throws SQLException;
	
}
	