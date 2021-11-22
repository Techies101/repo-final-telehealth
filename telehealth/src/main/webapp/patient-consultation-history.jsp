<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,400;0,500;0,600;0,700;0,800;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800&display=swap');	


#appointment, #dropdown {
	margin: auto;
	width: 80%;
}

.btn-primary, .btn-primary:hover, .btn-primary:active, .btn-primary:visited {
    	background-color: #1c87c9 !important;
    	font-family: 'Montserrat' !important;
}

table td{
	font-family: 'Montserrat', sans-serif !important;
	
}

#appointment th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #1c87c9;	
	color: white;
}

<%@include file="assets/stylesheet/main.css" %>
</style>
	<%@include file="includes/_linkshead.jsp"%>
	<script src="assets/js/modal.js"></script>
	<link rel="stylesheet" href="assets/stylesheet/header.css">
</head>

</head>
<body>
	
	<%@include file="includes/_header.jsp" %>

	<div class="container">
		<h5 class="text-left">Consultation History</h5>
		<br>


		<!--table-->
		<div class="table-responsive">
			<table class="table  table-hover " id="appointment">
				<thead>
					<tr>
						<th class="thbg-color">Date</th>
						<th class="thbg-color">Time</th>
						<th class="thbg-color">Doctor</th>
						<th class="thbg-color">Status</th>
						<th class="thbg-color">Remarks</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="a" items="${patientConsultation}">
						<tr>
							<td><c:out value="${a.th_date}" /></td>
							<td><c:out value="${a.th_time}" /></td>
							<td><c:out value="${a.th_doctor}" /></td>
							<td><c:out value="${a.th_status}" /></td>
							<td><c:out value="${a.th_remarks}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<footer class="py-3 my-4 mt-5 systemfooter ">
		<ul class="nav justify-content-center pb-3 mb-3">
			<p class="text-center">Service you can always count on</p>
		</ul>
		<p class="text-center text-muted">&copy; 2021 Online Telehealth
			Services, Inc</p>
	</footer>
</body>
</html>