package com.fujitsu.telehealth.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.lang.RandomStringUtils;

import com.fujitsu.telehealth.dao.AppDoctorImplementation;
import com.fujitsu.telehealth.dao.AppPatientImplementation;
import com.fujitsu.telehealth.dao.Image2DAO;
import com.fujitsu.telehealth.dao.ImageDAO;
import com.fujitsu.telehealth.dao.ImageListDAO;
import com.fujitsu.telehealth.model.AppRequestByPatient;
import com.fujitsu.telehealth.model.AppointmentModel;
import com.fujitsu.telehealth.model.AppointmentModel2;
import com.fujitsu.telehealth.model.HtmlTemplate;
import com.fujitsu.telehealth.model.LabModel;
import com.fujitsu.telehealth.model.LoginModel;
import com.fujitsu.telehealth.model.PatientModel;
import com.fujitsu.telehealth.model.SendMail;

public class PatientController {

	AppPatientImplementation AppPatientImpl = new AppPatientImplementation();
	AppDoctorImplementation AppDoctorImpl = new AppDoctorImplementation();
	ImageDAO imageDao = new ImageDAO();
	Image2DAO imageDao2 = new Image2DAO();
	ImageListDAO imageListDao = new ImageListDAO();
	HtmlTemplate template = new HtmlTemplate();
	SendMail mail = new SendMail();

	// Page Dispatcher
	public void dispatcher(String page, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	// Response template
	public void responseText(HttpServletResponse res, String text) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.print(text);
	}

	// Sending Message
	public void sendMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		String fname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String message = request.getParameter("message");

		if (AppPatientImpl.sendMessage(fname, email, message)) {
			responseText(response, "success");
		} else {
			responseText(response, "error");
		}

	}


	// Check user Exist
	public void checkUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		if (AppPatientImpl.checkUserExist(email)) {
			responseText(response, "error");
		} else {
			responseText(response, "success");
		}
	}

	// User login
	public void userLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		String th_email = request.getParameter("th_email").trim();
		String th_password = request.getParameter("th_password").trim();
		System.out.println(th_password);
		LoginModel user = new LoginModel(th_email, th_password);
		PatientModel userInfo = AppPatientImpl.validate(user);
		
		
		if (userInfo != null) {
			if (uid == null) {
				session.setAttribute("email", userInfo.getTh_email());
				session.setAttribute("fullname", userInfo.getTh_fullname());
				session.setAttribute("uid", userInfo.getTh_uid());
				session.setAttribute("link", "login");
				session.setAttribute("role", userInfo.getRole());
			} else {
				session.setAttribute("link", "logout");
			}
			responseText(response, "success");
		} else {
			responseText(response, "error");
		}

	}

	public static String createToken() {
		SecureRandom secureRandom = new SecureRandom();
		Base64.Encoder base64Encoder = Base64.getUrlEncoder();
		byte[] randomBytes = new byte[32];
		secureRandom.nextBytes(randomBytes);
		return base64Encoder.encodeToString(randomBytes);
	}

	// Create new User
	public void createNewUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();

		// Get the value from each parameter
		String th_email = request.getParameter("th_email").trim();
		String th_first_name = request.getParameter("th_first_name").trim();
		String th_middle_name = request.getParameter("th_middle_name").trim();
		String th_last_name = request.getParameter("th_last_name").trim();
		String th_age = request.getParameter("th_age");
		String th_gender = request.getParameter("th_gender").trim();
		String th_contact = request.getParameter("th_contact").replace(" ", "");
		String th_password = request.getParameter("th_password").trim();
		String th_condition = request.getParameter("th_condition");
		String th_address = request.getParameter("th_address");
		String th_patientID = new PatientModel().getTh_patientID() + Integer.parseInt(generateUniqueID());
		String th_bday = request.getParameter("th_bday");
		
		PatientModel userInfo = new PatientModel(th_patientID, th_email, th_first_name, th_middle_name, th_last_name,
				th_address, th_age, th_gender, th_contact, th_password, th_condition,th_bday);

		if (AppPatientImpl.createNewUser(userInfo)) {
			String token = createToken().replace("=", "");
			session.setAttribute("verificationToken", token);
			session.setAttribute("email", userInfo.getTh_email());
			template.setHtmlContent(token, userInfo.getTh_fname() + " " + userInfo.getTh_lname());
			mail.setUserEmail(userInfo.getTh_email());
			mail.sendEmail(template.getHtmlContent(), "onlinetelehealthservices@gmail.com", "Fujitsu2021!");
			responseText(response, "success");
		} else {
			responseText(response, "error");
		}
	}

	public void verifyToken(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		String userToken = request.getParameter("token");
		String userEmail = (String) session.getAttribute("email");
		String verificationToken = (String) session.getAttribute("verificationToken");
		if (userToken.equals(verificationToken)) {
			if (AppPatientImpl.updateUserStatus(userEmail)) {
				session.setAttribute("valid", "true");
				session.invalidate();
			}
		} else {
			System.out.println("Invalid Token");
		}
	}
	
	public void patientDashboard(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException{
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		String role = (String) session.getAttribute("role");
		
		if (uid == null) {
			response.sendRedirect("login");
			return;
		}
		if (role.equals("doctor")) {
			response.sendRedirect("doctor-dashboard.jsp");
			return;
		}
		
		List<PatientModel> listPatient = AppDoctorImpl.selectAllPatients();
		request.setAttribute("listPatient", listPatient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("appointment.jsp");
		dispatcher.forward(request, response);
	}

	// Get Profile Info
	public void accountInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		
		if (uid == null) {
			response.sendRedirect("login");
			return;
		}
		
		String email = (String) session.getAttribute("email");
		PatientModel userInfo = AppPatientImpl.getUserInfo(email);
		request.setAttribute("myinfo", userInfo);
		dispatcher("account-info.jsp", request, response);
	}

	// Update User info
	public void updateAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		String th_uid = (String) session.getAttribute("uid");
		String th_email = request.getParameter("th_email");
		String th_fname = request.getParameter("th_fname");
		String th_middle_name = request.getParameter("th_mname");
		String th_lname = request.getParameter("th_lname");
		String th_age = request.getParameter("th_age");
		String th_address = request.getParameter("th_address");
		String th_gender = request.getParameter("th_gender");
		String th_contact = request.getParameter("th_contact").replace(" ", "");
		String th_password = request.getParameter("th_password");
		String th_condition = request.getParameter("th_condition");
		String th_bday = request.getParameter("th_bday");
		PatientModel userInfo = new PatientModel(th_uid, th_email, th_fname, th_middle_name, th_lname, th_address,th_age,
				th_gender, th_contact, th_password, th_condition, th_bday);

		if (AppPatientImpl.updateAccount(userInfo)) {
			responseText(response, "success");
		} else {
			responseText(response, "error");
		}

	}

	// Request Appointment
	public void requestAppointment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		String fullname = (String) session.getAttribute("fullname");
		String doctor = request.getParameter("doctor");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String condition = request.getParameter("condition");

		AppRequestByPatient appRequest = new AppRequestByPatient(doctor, fullname, date, time, condition, uid);

		if (AppPatientImpl.requestAppointment(appRequest)) {
			responseText(response, "success");
		} else {
			responseText(response, "error");
		}

	}

	// List Request
	public void listRequest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		String selected = request.getParameter("dropdown");
		
		if (uid == null) {
			response.sendRedirect("login");
			return;
		}
		
		if (selected == null) {
			selected = "All";
		}

		List<AppointmentModel2> meeting = null;
		meeting = AppPatientImpl.requestedAppointment(uid);
		request.setAttribute("meeting", meeting);
		request.setAttribute("dropdown", selected);
		// SEND DATA BACK TO JSP
		dispatcher("patient-dashboard.jsp", request, response);
	}

	// Generate UniqueID
	private String generateUniqueID() {
		return RandomStringUtils.randomNumeric(5);
	}

	// Logout Me
	public void logoutMe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login");
	}

	public void uploadImage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("imageId"));
		System.out.println(request.getPart("image"));
		Part part = request.getPart("image");
		imageDao.paymentImage(id, part);
		dispatcher("patient-dashboard.jsp", request, response);
	}

	// List of Doctors for Request Appointment
	public void listDoctor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<PatientModel> listPatient = AppDoctorImpl.selectAllPatients();
		request.setAttribute("listPatient", listPatient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("appointment.jsp");
		dispatcher.forward(request, response);
	}

	// List of Patient
	public void listPatient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		
		if (uid == null) {
			response.sendRedirect("login");
			return;
		}else {
			List<PatientModel> listPatient = AppDoctorImpl.selectAllPatients();
			request.setAttribute("listPatient", listPatient);
			RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
			dispatcher.forward(request, response);
		}
	}

	// Details of Patient
	public void detailsPatient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		
		if (uid == null) {
			response.sendRedirect("login");
			return;
		}
		
		PatientModel detailsPatient = AppDoctorImpl.selectPatient(request.getParameter("id"));
		request.setAttribute("detailsPatient", detailsPatient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("patient-details.jsp");
		dispatcher.forward(request, response);
	}

	// Consultation History of Patient
	public void patientConsultation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<AppointmentModel> patientConsultation = AppDoctorImpl.selectConsultation(request.getParameter("id"));
		request.setAttribute("patientConsultation", patientConsultation);
		RequestDispatcher dispatcher = request.getRequestDispatcher("patient-consultation-history.jsp");
		dispatcher.forward(request, response);
	}

	// Laboratory History of Patient
	public void patientLaboratory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String th_uid = (String) session.getAttribute("uid");
		List<LabModel> patientLaboratory = imageListDao.labImageList(th_uid);
		request.setAttribute("patientLaboratory", patientLaboratory);
		RequestDispatcher dispatcher = request.getRequestDispatcher("patient-laboratory-history.jsp");
		dispatcher.forward(request, response);

	}

	// Laboratory History of Patient (Patient Side)
	public void patientsideLaboratory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		
		if (uid == null) {
			response.sendRedirect("login");
			return;
		}
		
		List<LabModel> patientLab = AppDoctorImpl.labImageList(uid);
		request.setAttribute("patientLab", patientLab);
		RequestDispatcher dispatcher = request.getRequestDispatcher("patient-history.jsp");
		dispatcher.forward(request, response);
	}

	// Upload Lab Image (Patient Side)
	public void patientLabUpload(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("uid");
		Part part = request.getPart("image");
		imageDao2.labImage(uid, part);
		RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
		dispatcher.forward(request, response);
	}
}