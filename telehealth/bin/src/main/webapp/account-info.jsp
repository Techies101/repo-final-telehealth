<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Account Personalisation</title>

	<%@include file="includes/_linkshead.jsp"%>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="assets/stylesheet/header.css">
	<link rel="stylesheet" href="assets/stylesheet/accountstyles.css">

</head>
<body>
	<%@include file="includes/_header.jsp"%>

	<div class="container rounded bg-white mt-3 mb-5  ">
		<div class="container updateform">
		<form action="update" id="update-form" method="post">
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
							<h4 class="text-right">Account</h4>
						</div>
					
						<div class="row mt-3">
							<div>
								<c:if test="${patient != null}">
								<input type="hidden" name="id" value="<c:out value='${patient.th_uid}' />" />
								</c:if>
							</div>
							<!--<div class="col-md-6">
								<label class="labels">Patient ID</label>
								<input type="text"
								value="<c:out value='${patient.patient_uid}' />" 
								class="form-control"
								name=patient_uid required="required" >
							</div>-->
							<div class="col-md-6">
								<label class="labels">Fisrtname</label>
								<input
								type="text" value="<c:out value='${myinfo.th_fname}' />"
								class="form-control" name=th_fname>
							</div>
							<div class="col-md-6">
								<label class="labels">Middlename</label>
								<input
								type="text" value="<c:out value='${myinfo.th_middle_name}' />"
								class="form-control" name=th_mname>
							</div>
							<div class="col-md-6">
								<label class="labels">Lastname</label>
								<input
								type="text" value="<c:out value='${myinfo.th_lname}' />"
								class="form-control" name=th_lname>
							</div>
							<div class="col-md-6">
								<label class="labels">Age</label>
								<input type="text"
								value="<c:out value='${myinfo.th_age}' />" class="form-control"
								name=th_age>
							</div>
							<div class="col-md-6">
								<label class="labels">Gender</label>
								<input
								type="text" value="<c:out value='${myinfo.th_gender}' />"
								class="form-control" name=th_gender>
							</div>
							<div class="col-md-6">
								<label class="labels">Birthday</label>
								<input
								type="text" value="<c:out value='${myinfo.th_bday}' />"
								class="form-control" name=th_bday>
							</div>
						
						
							<div class="col-md-6">
								<label class="labels">Address</label>
								<input
								type="text" value="<c:out value='${myinfo.th_address}' />"
								class="form-control" name=th_address>
							</div>
							<div class="col-md-6">
								<label class="labels">Contact Number</label>
								<input
								type="text" value="<c:out value='${myinfo.th_contact}' />"
								class="form-control" name=th_contact>
							</div>
							<div class="col-md-6">
								<label class="labels">Email Address</label>
								<input type="email"
								value="<c:out value='${myinfo.th_email}' />"
								class="form-control" name=th_email>
							</div>
							<div class="col-md-6">
								<label class="labels">Password</label>
								<input
								type="password" value="<c:out value='${myinfo.th_password}' />"
								class="form-control" name=th_password>
							</div>
						</div>
							<div class="col-md-12">
								<label class="labels">Health Condition</label>
								<textarea
									value="" 
									class="form-control"
									name=condition required="required" > <c:out value='${myinfo.th_condition}' />
									
								</textarea>
							</div>
						
						
						<div class="mt-2 text-center">
							<button class="btn btn-save  profile-button" type="submit">Save Changes </button></div>
					</div>
					</div>
				</div>
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
