package com.fujitsu.telehealth.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
@MultipartConfig
public class WebServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PatientController PatientController;
	private DoctorController DoctorController;

	public void init() {
		PatientController = new PatientController();
		DoctorController = new DoctorController();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		try {
			switch (path) {
			// POST METHOD PATIENT SIDE
			case "/usercheck":
				PatientController.checkUser(request, response);
				break;
			case "/insert":
				PatientController.createNewUser(request, response);
				break;
			case "/signin":
				PatientController.userLogin(request, response);
				break;
			case "/message":
				PatientController.sendMessage(request, response);
				break;
			case "/logout":
				PatientController.logoutMe(request, response);
				break;
			case "/update":
				PatientController.updateAccount(request, response);
				break;
			case "/book-appointment":
				PatientController.requestAppointment(request, response);
				break;
				

			// GET METHOD PATIENT
			case "/login":
				PatientController.dispatcher("login.jsp", request, response);
				break;
			case "/patient-dashboard":
				PatientController.listRequest(request, response);
				break;
			case "/appointment":
				PatientController.dispatcher("appointment.jsp", request, response);
				break;
			case "/register":
				PatientController.dispatcher("register.jsp", request, response);
				break;
			case "/account-info":
				PatientController.accountInfo(request, response);
				break;
			case "/payment":
				PatientController.dispatcher("payment.jsp", request, response);
				break;
			case "/verify":
				PatientController.verifyToken(request, response);
				
			// GET METHOD DOCTOR
			case "/doctor-dashboard":
				DoctorController.displayMeeting(request, response);
				break;
				
			// POST METHOD DOCTOR
			case "/update-link":
				DoctorController.updateMeeting(request, response);
				break;
			case "/drop":
				DoctorController.dropMeeting(request, response);
				break;
			case "/approve":
				DoctorController.approveMeeting(request, response);
				break;
			case "/list":
				PatientController.listPatient(request, response);
				break;
			case "/listdoc":
				PatientController.listDoctor(request, response);
				break;
			case "/select":
				PatientController.detailsPatient(request, response);
				break;
			case "/view":
				PatientController.patientConsultation(request, response);
				break;
			case "/choose":
				PatientController.patientLaboratory(request, response);
				break;
			case "/history":
				PatientController.patientsideLaboratory(request, response);
				break;
			case "/uploadLab":
				PatientController.patientLabUpload(request, response);
				break;
			case "/uploadImage":
				PatientController.uploadImage(request, response);
				break;
			default: 
				PatientController.listRequest(request, response);
				break;
			}
		} catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
