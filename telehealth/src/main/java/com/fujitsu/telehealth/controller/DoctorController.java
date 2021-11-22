package com.fujitsu.telehealth.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fujitsu.telehealth.dao.AppDoctorImplementation;
import com.fujitsu.telehealth.model.AppointmentModel2;

public class DoctorController {

	private AppDoctorImplementation appDao = new AppDoctorImplementation();

	// Page Dispatcher
	public void dispatcher(String page, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	// Display Meeting
	public void displayMeeting(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String selected = request.getParameter("dropdown");
		if(selected == null) {
			selected = "All";
		}
		List<AppointmentModel2> meeting = null;
		
		HttpSession session = request.getSession();
		String uid = null;
		uid = (String) session.getAttribute("uid");
		
		meeting = appDao.displayMeeting(uid);
		request.setAttribute("meeting", meeting);
		request.setAttribute("dropdown", selected);

		// SEND DATA BACK TO JSP
		dispatcher("doctor-dashboard.jsp", request, response);
		// response.sendRedirect("_patientappointment.jsp");
	}

	// Update Meeting Link
	public void updateMeeting(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("edit"));
		String link = request.getParameter("link");

		if (appDao.updateMeeting(id, link)) {
			dispatcher("doctor-dashboard.jsp", request, response);
		} else {
			System.out.println("Failed to update meeting link");
		}
	}

	// Decline Patient Request
	public void dropMeeting(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("drop"));
		String message = request.getParameter("message");

		if (appDao.dropMeeting(id, message)) {
			String dropdown = request.getParameter("dropdown");
			request.setAttribute("dropdown", dropdown);
			dispatcher("doctor-dashboard.jsp", request, response);
		} else {
			System.out.println("Drop Meeting failed!");
		}

	}
	
	//Approve Request 
	public void approveMeeting(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("approve"));
		String type = request.getParameter("approvetype");
		appDao.approveMeeting(id, type);
		dispatcher("doctor-dashboard.jsp", request, response);
//		response.sendRedirect("/TelehealthService/display.jsp");
	}
 // Hello World!
}
