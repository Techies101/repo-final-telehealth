package com.fujitsu.telehealth.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JsonObject;

import com.fujitsu.telehealth.dao.AppDoctorImplementation;
import com.fujitsu.telehealth.model.AppointmentModel2;
import com.fujitsu.telehealth.model.NotificationModel;

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
		HttpSession session = request.getSession();
		String selected = request.getParameter("dropdown");
		String uid = (String) session.getAttribute("uid");
		String role = (String) session.getAttribute("role");
		
		if (uid == null)  {
			response.sendRedirect("login");
			return;
		}
			
		if (role.equals("patient")) {
			response.sendRedirect("patient-dashboard");
			return;
		}
		
		if(selected == null) {
			selected = "All";
		}
		
		List<AppointmentModel2> meeting = null;
		meeting = appDao.displayMeeting(uid);
		request.setAttribute("meeting", meeting);
		request.setAttribute("dropdown", selected);
		// SEND DATA BACK TO JSP
		dispatcher("doctor-dashboard.jsp", request, response);
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
	}
	
	
	// Notification
	public void reminder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ParseException {
		
		HttpSession session = request.getSession();
		String th_duid = session.getAttribute("th_uid").toString();
		JsonObject json = new JsonObject();
		NotificationModel notifModel = new NotificationModel();
		PrintWriter writer = response.getWriter();
		
		notifModel = appDao.getSchedule(th_duid);
		
		if ( notifModel != null ) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.setStatus(HttpServletResponse.SC_OK);
			json.put("doctor", notifModel.getDoctor());
			json.put("time", notifModel.getDate());
			json.put("time", notifModel.getTime());
			writer.print(json);
			writer.flush();
		}
		
	}
}
