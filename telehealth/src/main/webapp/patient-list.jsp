<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="includes/_linkshead.jsp"%>
<link rel="stylesheet" href="assets/stylesheet/header.css">
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,400;0,500;0,600;0,700;0,800;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800&display=swap');	

.thead-custom-background {
	background: #28d;
	color: #fff;
}

.swal2-input {
	border: 1px !important;
}

.btn-primary, .btn-primary:hover, .btn-primary:active, .btn-primary:visited {
    	background-color: #1c87c9 !important;
    	font-family: 'Montserrat' !important;
    	margin-left: .5rem;
}

table td{
	font-family: 'Montserrat', sans-serif !important;
	
}


.container-search {
	width: 60rem;
	padding-top: 2rem;
	margin: 0 auto;
}

#appointment th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #1c87c9;	
	color: white;
}

<%@include file="assets/stylesheet/main.css"%>
</style>
</head>

<body>
	
	<%@include file="includes/_header.jsp" %>
	
	<!-- first container -->
	<div class="container">
		<h5 class="text-left">Patient List</h5>
		<br>


		<!--table-->
		<div class="table-responsive">
			<table class="table  table-hover " id="appointment">
				<thead>
					<tr>
						<th class="thbg-color">ID</th>
						<th class="thbg-color">Full Name</th>
						<th class="thbg-color">Email</th>
						<th class="thbg-color">Contact Number</th>
						<th class="thbg-color">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="patient" items="${listPatient}">
						<tr>
							<td><c:out value="${patient.th_uid}" /></td>
							<td><c:out value="${patient.th_fullname}" /></td>
							<td><c:out value="${patient.th_email}" /></td>
							<td><c:out value="${patient.th_contact}" /></td>
							
							<!--  CHANGED HREF LINK TO BUTTON -->
							<td>
							<input type="button" onclick="location.href='select?id=<c:out value='${patient.th_uid}' />';" class="btn btn-primary" value="Account"/>
							<input type="button" onclick="location.href='view?id=<c:out value='${patient.th_uid}' />';" class="btn btn-primary" value="Consultation"/>
							<input type="button" onclick="location.href='choose?id=<c:out value='${patient.th_uid}' />';" class="btn btn-primary" value="Lab"/>
							</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div>
		<footer class="py-3 my-4 mt-5 systemfooter bottomfixed ">
			<ul class="nav justify-content-center pb-3 mb-3">
				<li>
					<p class="text-center">Service you can always count on</p>
				</li>
			</ul>
			<p class="text-center text-muted">&copy; 2021 Online Telehealth
				Services, Inc</p>
		</footer>
	</div>

	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

