<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

	<title>
		Patient Consultation History
	</title>

<%@include file="includes/_linkshead.jsp"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="assets/stylesheet/header.css">
<link rel="stylesheet" href="assets/stylesheet/dashboard.css">
	
</head>
<body>
	
	<%@include file="includes/_header.jsp" %>

	<div class="container container-search col-lg-7">
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


	<script src="assets/js/modal.js"></script>
</body>
</html>