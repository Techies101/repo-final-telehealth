<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="includes/_linkshead.jsp"%>
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="assets/stylesheet/header.css">
	<link rel="stylesheet" href="assets/stylesheet/dashboard.css">

</head>

<body>
	
	<%@include file="includes/_header.jsp" %>
	
	<!-- first container -->
	<br>
	<div class="container  col-lg-8">
		<h5 class="text-left">Patient List</h5>
		<br>
		<!--table-->
	
		<div class="table-responsive">
			<table class="table table-hover " id="appointment">
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



	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" "></script>
</body>
</html>

