<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<%@include file="includes/_linkshead.jsp"%>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<style>
.error-text {
	background: #ffada1;
	border: 1 px solid #f35843;
	color: #233;
	text-align: left !important;
}
</style>
<link rel="stylesheet" href="assets/stylesheet/register.css">
</head>
<body>
	<%
	String uid = (String) session.getAttribute("uid");
	if (uid != null) {
		response.sendRedirect("patient-dashboard");
	}
	%>
	
	<h1><c:out value="${valid}"></c:out></h1>
	
	<div class="container contact pt-5 pb-5 mt-5 ">
		<div class="loginForm  center mt-5">

			<form class="px-3" action="signin" id="form-login">
				<div class="toplogo mt-3">
					<i class="fas fa-laptop-medical fa-2x logo mb-2"></i> <br>
					Login to your Account
				</div>

				<div class="error-text py-1 px-2  mb-2 fw-bold border"
					id="login-error"></div>
				<div class="inputBox">
					<input type="email" id="username" name="th_email"
						required="required"> <span>Email</span>
				</div>
				<div class="inputBox">
					<input type="password" id="password" name="th_password"
						required="required"> <span>Password </span><i
						class="material-icons" id="visibility"> visibility </i>
				</div>
				<div class="inputBox">
					<div class="col-auto">
						<button type="submit" class="btn btn-login form-control">Login</button>
						<br> <a href="register" class="nav-link custom_signin-btn">
							Don't have an account?<br>Sign Up here
						</a>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript" src="./assets/js/modal.js"></script>
	<script>
		$("#login-error").hide();
		var pass = true;
		var p = document.getElementById("password")
		var e = document.getElementById("visibility")

		e.addEventListener('click', function() {
			if (pass) {
				p.setAttribute("type", "text");
				e.innerHTML = "visibility_off"
			} else {
				p.setAttribute("type", "password");
				e.innerHTML = "visibility"
			}
			pass = !pass;
		})

		$("#form-login").submit(
				function(e) {
					e.preventDefault()
					var form = $(this);
					var url = form.attr('action');

					$.ajax({
						type : "POST",
						url : url,
						data : form.serialize(),
						success : function(result) {
							if (result == 'success') {
								modalRedirect("Login Successfull",
										"Please wait while redirecting you.")
							} else {
								$("#login-error").html(
										"Invalid email or password.").show(
										"slow").delay(3000).hide("slow");
							}
						}
					});

				})
	</script>
</body>

</html>