<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Appointment</title>
	
	<%@include file="includes/_linkshead.jsp"%>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="assets/stylesheet/header.css">
	<link rel="stylesheet" href="assets/stylesheet/dashboard.css">
	

</head>
<body>

	<%@include file="includes/_header.jsp"%>
	
	<div class="container col-lg-8 mt-3 ">
		<form action="patient-dashboard" class="py-3 right" method="post">
			<div class="row ">
				<select name="dropdown" id="list" class="form-select mt-1 ml-1 col">
					<option value="All" selected
						<%if ((request.getAttribute("dropdown") != null) && request.getAttribute("dropdown").equals("Pending")) {%>
						<%}%>>All</option>
					<option value="Pending"
						<%if ((request.getAttribute("dropdown") != null) && request.getAttribute("dropdown").equals("Pending")) {%>
						selected <%}%>>Pending</option>
					<option value="Payment"
						<%if ((request.getAttribute("dropdown") != null) && request.getAttribute("dropdown").equals("Payment")) {%>
						selected <%}%>>Payment</option>
					<option value="Done"
						<%if ((request.getAttribute("dropdown") != null) && request.getAttribute("dropdown").equals("Done")) {%>
						selected <%}%>>Done</option>
					<option value="Declined"
						<%if ((request.getAttribute("dropdown") != null) && request.getAttribute("dropdown").equals("Declined")) {%>
						selected <%}%>>Declined</option>
				</select>
			
				<button type="submit" class="btn btn-primary col mt-1" ><i class="fa fa-search"></i> Search</button>
			</div>
		</form>
	</div>


	<div class="container col-lg-8">
		<div class="table-responsive w-100 ">
			<table id="appointment" class="table table-hover">
				<thead>
					<tr>
						<th>Patient</th>
						<th>Date</th>
						<th>Time</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${meeting}">
						<c:if test="${dropdown == a.meetingStatus || dropdown == 'All'}">
							<tr>
								<td><c:out value="${a.meetingPatient}" /></td>
								<td><c:out value="${a.meetingDate}" /></td>
								<td><c:out value="${a.meetingTime}" /></td>
								
								<c:choose>
								<c:when test="${a.meetingStatus == 'Payment' && a.meetingImage != null}">
								<td style="align-items: center;"><c:out value="Payed"/></td>
								</c:when>
								
								<c:when test="${a.meetingStatus == 'Payment' && a.meetingImage == null}">
								<td style="align-items: center;"><c:out value="For Payment"/></td>
								</c:when>
								
								<c:otherwise>
								<td style="align-items: center;"><c:out value="${a.meetingStatus}"/></td>
								</c:otherwise>						
								</c:choose>
														
								<c:choose>
								<c:when test="${a.meetingStatus == 'Pending' || a.meetingStatus == 'Payment'}">
								<td>
									<button id="approve${a.meetingNumber}" class="btn btn-primary"><i class="fa fa-check"></i> Approve</button>
											
								<c:if test="${a.meetingStatus == 'Payment'}">
								
								<button id="view${a.meetingNumber}" class="btn btn-primary"><i class="fa fa-eye"></i> View</button>					 	
								</c:if>
								<button id="cancel${a.meetingNumber}" class="btn btn-danger"><i class="fa fa-times"></i> Cancel</button>
											
											
									<script>
											var a = document.getElementById('view'+${a.meetingNumber});
											if(a){
															
										//FOR VIEWING									
										document.getElementById('view'+${a.meetingNumber}).addEventListener('click', function(){

											Swal.fire({
												title: 'Proof of payment',
												imageUrl: '_getImage.jsp?id='+${a.meetingNumber},
												imageAlt: 'No Image Uploaded Yet',
												confirmButtonColor: '#1c87c9'
												})
											});
																	}
									</script>
											
								<script>
								//FOR APPROVE
																	
										var type = "";
										
										if(${a.meetingStatus == 'Pending'})
											type = "Pending";
										else if(${a.meetingStatus == 'Payment'})
											type = "Payment";

																	
										document.getElementById('approve'+${a.meetingNumber}).addEventListener('click', function(){
											Swal.fire({
												icon: 'success',
												title: 'Appointment status approved',
												showConfirmButton: false,
												timer: 1500
												})
												
												var formData = new FormData();
												
												formData.append("approve", ${a.meetingNumber});
												formData.append("approvetype", type);
												
												fetch ('http://localhost:8080/telehealth/approve',{
													method: 'POST',
													body: formData
												})
												location.reload()
											});
								</script>
																	
								<script>
										//FOR DECLINE W/ REASON
										document.getElementById('cancel'+${a.meetingNumber}).addEventListener('click', function(){

											Swal.fire({
											title: 'Are you sure?',
											text: "Reason for appointment cancellation",
											input: 'text',
											inputAttributes: {
													autocapitalize: 'off'
												},
											icon: 'warning',
											showCancelButton: true,
											confirmButtonColor: '#1c87c9',
											cancelButtonColor: '#d33',
											confirmButtonText: 'Cancel Meeting'
											}).then((result) => {
											if (result.isConfirmed) {
												Swal.fire(
												'Cancelled!',
												'Your appointment has been cancelled.',
												'success'
												)
												
												var formData = new FormData();
												
												formData.append("message", result.value);
												formData.append("drop", ${a.meetingNumber});
												
												fetch ('http://localhost:8080/telehealth/drop',{
													method: 'POST',
													body: formData
												})
												location.reload()
											}

											});
										})
								</script>

									</td>
								</c:when>
								<c:when test="${a.meetingStatus == 'Declined'}">
								<td><c:out value="${a.meetingComment}" /></td>
								</c:when>
								
								
								<c:otherwise>
								<c:if test="${a.meetingLink != null && a.meetingStatus != 'Done' }">
								<td><c:out value="" /></td>
								</c:if>
								</c:otherwise>
								
								</c:choose>
								
								<c:if test="${a.meetingStatus == 'Done'}">
								<td>
								<input type="button" onclick="location.href='https://${a.meetingLink}';" class="btn btn-primary" value="Link"/>
								
								<button id="update${a.meetingNumber}" class="btn btn-primary"><i class="fa fa-edit"></i> Edit</button>
								
								<script>
										document.getElementById('update'+${a.meetingNumber}).addEventListener('click', function(){

											Swal.fire({
											title: 'Enter Meeting Link',
											text: "(i.e. Google Meets, Microsoft Teams, Zoom, etc)",
											input: 'text',
											inputAttributes: {
													autocapitalize: 'off'
												},
											icon: 'question',
											showCancelButton: true,
											confirmButtonColor: '#1c87c9',
											cancelButtonColor: '#d33',
											confirmButtonText: 'Update'
											}).then((result) => {
											if (result.isConfirmed) {
												Swal.fire(
												'Updated!',
												'Meeting link has been updated.',
												'success'
												)
												
												var formData = new FormData();
												
												formData.append("link", result.value);
												formData.append("edit", ${a.meetingNumber});

												fetch ('http://localhost:8080/telehealth/update-link',{
													method: 'POST',
													body: formData
												})
												
												location.reload()
												
											}

											});
										})
								</script>
								</td>
								</c:if>

							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
		
</body>
</html>