<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<title>OTS Patient Details</title>

<%@include file="includes/_linkshead.jsp"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="assets/stylesheet/header.css">
<link rel="stylesheet" href="assets/stylesheet/accountstyles.css">
</head>

</head>
<body>
	
	<%@include file="includes/_header.jsp" %>
	<!-- account -->
	<!--<div class="container-search ">
		<div class="patient-details">
			<h4 class="text-left border-bottom">Account Information</h4>
				<tr>
					<th>First name:</th>
					<td><c:out value="${detailsPatient.th_fname}" /></td>
					<br>
					<th>Middle Name:</th>
					<td><c:out value="${detailsPatient.th_middle_name}" /></td>
					<br>
					<th>Last Name:</th>
					<td><c:out value="${detailsPatient.th_lname}" /></td>
					<br>
					<th>Age:</th>
					<td><c:out value="${detailsPatient.th_age}" /></td>
					<br>
					<th>Gender:</th>
					<td><c:out value="${detailsPatient.th_gender}" /></td>
					<br>
					<th>Address:</th>
					<td><c:out value="${detailsPatient.th_address}" /></td>
					<br>
					<th>Email:</th>
					<td><c:out value="${detailsPatient.th_email}" /></td>
					<br>
					<th>Contact Number:</th>
					<td><c:out value="${detailsPatient.th_contact}" /></td>
				</tr>
		</div>
	</div>-->	
	<br>
	<div class="container rounded bg-white mt-3 mb-5  ">
		<div class="container updateform">
			<div class="row">
				<div class="col-md-3 col-sm- 12 border border-right bgblue ">
					<div class="d-flex flex-column align-items-center text-center p-3 py-5 white">
						<i class="fas fa-user-circle fa-6x mt-3 "></i>
						<p> <label> <c:out value='${myinfo.th_fname}' /> </label> <br> 
							<label> <c:out value='${myinfo.th_email}' /> </label></p>
					</div>
				</div>
				<div class="col-md-8  col-sm- 12 mx-auto border-right">
					<div class="p-3 py-5">
						<div class="d-flex justify-content-between align-items-center mb-3">
							<h4 class="text-right">Patient Details</h4>
						</div>
					
						<div class="row mt-3">
							<div class="col-md-6">
								<label class="labels">Fisrtname</label>
								<input
								type="text" value="<c:out value='${detailsPatient.th_fname}' />"
								class="form-control">
							</div>
							<div class="col-md-6">
								<label class="labels">Middlename</label>
								<input
								type="text" value="<c:out value='${detailsPatient.th_middle_name}' />"
								class="form-control" >
							</div>
							<div class="col-md-6">
								<label class="labels">Lastname</label>
								<input
								type="text" value="<c:out value='${detailsPatient.th_lname}' />"
								class="form-control" >
							</div>
							<div class="col-md-6">
								<label class="labels">Age</label>
								<input type="text"
								value="<c:out value='${detailsPatient.th_age}' />" class="form-control"
								>
							</div>
							<div class="col-md-6">
								<label class="labels">Gender</label>
								<input
								type="text" value="<c:out value='${detailsPatient.th_gender}' />"
								class="form-control" >
							</div>
							
							<div class="col-md-6">
								<label class="labels">Address</label>
								<input
								type="text" value="<c:out value='${detailsPatient.th_address}' />"
								class="form-control" >
							</div>
							<div class="col-md-6">
								<label class="labels">Email Address</label>
								<input type="email"
								value="<c:out value='${detailsPatient.th_email}' />"
								class="form-control">
							</div>
							<div class="col-md-6">
								<label class="labels">Contact Number</label>
								<input
								type="text" value="<c:out value='${detailsPatient.th_contact}' />"
								class="form-control">
							</div>


						</div>
					</div>
					</div>
				</div>
			</div>
	</div>
	</div>


</body>
</html>