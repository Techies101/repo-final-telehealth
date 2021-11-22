<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Account Personalisation</title>

	<%@include file="includes/_linkshead.jsp"%>
	<link rel="stylesheet" href="assets/stylesheet/header.css">
	<link rel="stylesheet" href="assets/stylesheet/accountstyles.css">

</head>
<body>
	<%@include file="includes/_header.jsp"%>
	<!-- EDITTABLE FORM FOR ACCOUNT DETAILS UPDATE -->

	<%
	String uid = (String) session.getAttribute("uid");
	if (uid == null) {
		response.sendRedirect("login");
	}
	%>
	<div class="account-center">
		<div class="testbox">
			<form action="update" id="update-form" class="border my-info">
				<fieldset>
					<legend class="rounded-3">Update Details</legend>
					<div class="colums">

						<div class="item">
							<label for="fname">First Name<span>*</span></label> <input
								type="text" value="<c:out value="${myinfo.th_fname}" />"
								class="form-control" name=th_fname>
						</div>

						<div class="item">
							<label for="mname">Middle Name<span>*</span></label> <input
								type="text" value="<c:out value='${myinfo.th_middle_name}' />"
								class="form-control" name=th_mname>
						</div>

						<div class="item">
							<label for="lname">Last Name<span>*</span></label> <input
								type="text" value="<c:out value='${myinfo.th_lname}' />"
								class="form-control" name=th_lname>
						</div>

						<div class="item">
							<label for="bday">Birthday</label> <input type="text"
								value="<c:out value='${myinfo.th_bday}' />" class="form-control"
								name=th_bday>
							<label for="age">Age</label> <input type="text"
								value="<c:out value='${myinfo.th_age}' />" class="form-control"
								name=th_age>
						</div>

						<div class="item">
							<label for="gender">Gender<span>*</span></label> <input
								type="text" value="<c:out value='${myinfo.th_gender}' />"
								class="form-control" name=th_gender>
						</div>

						<div class="item">
							<label for="address">Address<span>*</span></label> <input
								type="text" value="<c:out value='${myinfo.th_address}' />"
								class="form-control" name=th_address>
						</div>

						<div class="item">
							<label for="contact">Contact<span>*</span></label> <input
								type="text" value="<c:out value='${myinfo.th_contact}' />"
								class="form-control" name=th_contact>
						</div>

						<div class="item">
							<label for="condition">Condition<span>*</span></label> <input
								type="text" value="<c:out value='${myinfo.th_condition}' />"
								class="form-control" name=th_condition>
						</div>

						<div class="item">
							<label for="email">Email</label> <input type="email"
								value="<c:out value='${myinfo.th_email}' />"
								class="form-control" name=th_email>
						</div>

						<div class="item">
							<label for="password">Password<span>*</span></label> <input
								type="password" value="<c:out value='${myinfo.th_password}' />"
								class="form-control" name=th_password>
						</div>

					</div>
				</fieldset>
				<br />
				<div class="btn-block">
					<button type="submit"
						class="btn custom_btn-background-blue form-control">Save
						Changes</button>
				</div>

			</form>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript" src="assets/js/modal.js"></script>

	<script>
		$("#update-form").submit(function(e) {
			e.preventDefault()
			var form = $(this);
			var url = form.attr('action');

			$.ajax({
				type : "POST",
				url : url,
				data : form.serialize(),
				success : function(result) {
						if (result == "success"){
							modal(result, "Update successfully")
						}else {
							modal(result, "Update failed!")
						}
					}
				});
		})
	</script>
</body>
</html>
