<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>OTS Patient Consultation History</title>

<style>
@import
	url('https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,400;0,500;0,600;0,700;0,800;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800&display=swap');	

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

body {
height: 100vh !important;
}
<%@include file="assets/stylesheet/main.css"%>
</style>	<%@include file="includes/_linkshead.jsp"%>
	<link rel="stylesheet" href="assets/stylesheet/header.css">
	
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100;0,200;0,400;0,500;0,600;0,700;0,800;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800&display=swap');	

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

body {
height: 100vh !important;
}

<%@include file="assets/stylesheet/main.css"%>
</style>
</head>
<body>
	<%@include file="includes/_header.jsp"%>
	<div class="container-search">
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

	<footer class="py-3 my-4 mt-5 systemfooter ">
		<ul class="nav justify-content-center pb-3 mb-3">
			<p class="text-center">Service you can always count on</p>
		</ul>
		<p class="text-center text-muted">&copy; 2021 Online Telehealth
			Services, Inc</p>
	</footer>
</body>
</html>