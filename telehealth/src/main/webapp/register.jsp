<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Patient Registration</title>
<%@include file="includes/_linkshead.jsp"%>
<link rel="stylesheet" href="assets/stylesheet/header.css">
<link rel="stylesheet" href="assets/stylesheet/register.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
</head>
<body>

	<%
	String uid = (String) session.getAttribute("uid");
	if (uid != null) {
		response.sendRedirect("patient-dashboard");
	}
	%>
	<!--Insert-->
	<div class="container pt-5 pb-5">
		<div class="regForm mt-2">
			<form class="px-3" id="register-form" action="insert">
				<div class="toplogo mt-3">
					<i class="fas fa-laptop-medical fa-2x logo mb-2"></i> <br>
					Patient Registration
				</div>
				<div class="container">
					<div class="row  row-cols-1 row-cols-md-2">

						<div class="inputBox col">
							<input type="text" name="th_first_name" required="required"
								id="fname">
							<span>Patient's First Name</span>
						</div>

						<div class="inputBox col">
							<input type="text" name="th_middle_name" required="required"
								id="mname">
							<span>Patient's Middle Name</span>
						</div>

						<div class="inputBox col">
							<input type="text" name="th_last_name" required="required"
								id="lname">
							<span>Patient's Last Name</span>
						</div>

						<div class="inputBox col">
							<input type="text" name="th_age" required="required" id="age"
								onkeypress="return /[0-9]/i.test(event.key)"
								onkeyup="return calcAge(this)"> <span>Age</span>
							<p id="errorAge" class="error-text"></p>
						</div>

						<div class="inputBox col">
							<select id="gender" name="th_gender"
								class="custom_select-padding required" required="required">
								<option selected value="" hidden=true>Gender</option>
								<option value="Male">Male</option>
								<option value="Female">Female</option>
							</select>
						</div>

						<div class="inputBox col">
							<input type="text" id="contact" name="th_contact"
								required="required" onkeyup="return isValidPhone(this)">
							<span class="number">Contact Number</span>
							<p id="errorContact" class="error-text" style="display: none;"></p>
						</div>
						<div class="inputBox col">
							<input type="text" name="th_address" required="required"
								id="address"> <span>Address</span>
						</div>

						<div class="inputBox col">
							<input id="email" type="email" name="th_email"
								required="required"> <span>Email</span>
							<p id="errorEmail" class="error-text" style="color: #f55;"></p>
						</div>

						<div class="inputBox">
							<input type="password" id="password" name="th_password"
								required="required"> <span>Password </span>
						</div>

						<div class="inputBox">
							<input type="password" id="re-type-pass"
								name="th_retype_password" required="required"
								onkeyup="return isPasswordSame(this)"> <span>Re-type
								password </span> <i class="material-icons" id="visibility">
								visibility </i>
							<p id="errorPass" class="error-text"></p>
						</div>
					</div>

					<div class="inputBox col">
						<input type="text" name="th_condition" required="required">
						<span>Condition</span>
					</div>

					<div style="font-size: 12px; color: red; text-align: left;"
						class="mb-3 mt-2">
						<b>Note: </b> Please make sure that your <b>Patient Name is
							correct</b>. This will be used in all medical records and is <b>Not
							Editable</b>
					</div>

					<div class="col-auto">
						<button type="submit" class="btn btn-login form-control">Register</button>
						<br> <a href="login"
							class="text-center nav-link custom_signin-btn"> Have an
							account? Sign In here </a>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/cleave.min.js"></script>
	<script type="text/javascript" src="assets/js/cleave-phone.ph.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript" src="assets/js/modal.js"></script>
	<script type="text/javascript" src="assets/js/utils.js"></script>
</body>

</html>