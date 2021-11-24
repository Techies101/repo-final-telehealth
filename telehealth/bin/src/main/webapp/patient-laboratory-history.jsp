<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<title>OTS Patient Consultation History</title>
	<%@include file="includes/_linkshead.jsp"%>
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="assets/stylesheet/header.css">
	<link rel="stylesheet" href="assets/stylesheet/dashboard.css">
</head>
<body>
	<%@include file="includes/_header.jsp"%>
	
	<div class="container container-search col-lg-7">
		<h5 class="text-left">Laboratory History</h5>
		<br>
		<!--table-->
		<div class="table-responsive">
			<table class="table  table-hover " id="appointment">
				<thead>
					<tr>
						<th class="thbg-color">Date</th>
						<th class="thbg-color">Time</th>
						<th class="thbg-color">Image</th>
						
					</tr>
				</thead>
				<tbody>
				<c:forEach var="a" items="${patientLaboratory}">
						<tr>
							<td><c:out value="${a.th_date}" /></td>
							<td><c:out value="${a.th_time}" /></td>
							<td>
							
							<button id="view${a.th_id}" class="btn btn-primary">View</button>
							<script>
									//FOR VIEWING									
							        document.getElementById('view'+${a.th_id}).addEventListener('click', function(){
							
							        	Swal.fire({
							        		  title: 'Lab Result Image',
							        		  imageUrl: '_getLabImage.jsp?id='+${a.th_id},
							        		  imageAlt: 'No Image Uploaded Yet',
							            	  confirmButtonColor: '#1c87c9'
							        		})
							
							        	});
							</script>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript" src="assets/js/modal.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" "></script>
</body>
</html>