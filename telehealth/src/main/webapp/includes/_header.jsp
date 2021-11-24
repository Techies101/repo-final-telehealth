<%@ page language="java" pageEncoding="UTF-8"
	import="com.fujitsu.telehealth.controller.WebServiceController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--Navigation-->
<div class="navigation border">
	<nav
		class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 px-4">

		<a href=""
			class="  d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
			<i class="fas fa-laptop-medical fa-2x logo"></i> <b class="logo">
				&nbsp; Online Telehealth Services</b>
		</a>

		<ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
			<c:choose>
				<c:when test="${role == null}">
					<li class="nav-item"><a href="#home"
						class="nav-link px-2 actived  ">Home</a></li>
					<li class="nav-item"><a href="#services"
						class="nav-link px-2 ">Services</a></li>
					<li class="nav-item"><a href="#contact" class="nav-link px-2 ">Contact</a></li>
				</c:when>
				<c:when test="${role == 'patient'}">
					<li class="nav-item"><a href="history"
						class="nav-link px-2 actived  ">History</a></li>
					<li class="nav-item"><a href="patient-dashboard"
						class="nav-link px-2 ">Appointment</a></li>
					<li class="nav-item"><a href="account-info"
						class="nav-link px-2 ">Account</a></li>
				</c:when>
				<c:when test="${role == 'doctor'}">
					<li class="nav-item"><a href="doctor-dashboard"
						class="nav-link px-2 actived  ">Appointment</a></li>
					<li class="nav-item"><a href="list" class="nav-link px-2 ">Patient
							List</a></li>
				</c:when>
			</c:choose>
		</ul>

		<div class="col-md-3 text-end">
			<button type="button" class="icon-button">
				<span class="material-icons" style="margin-top: 5px;">
					notifications </span> <span class="icon-button__badge" id="notif_number">2</span>
			</button>
			<form class="d-inline" action="${uid == null ? 'login' : 'logout'}"
				method="POST" id="form-logout-login">
				<button id="log-btn" type="submit" class="btn btn-login me-2">${uid != null ? "Logout" : "Login"}</button>
				<div id="uid" data-uid="${uid}"></div>
			</form>
		</div>
	</nav>
</div>



