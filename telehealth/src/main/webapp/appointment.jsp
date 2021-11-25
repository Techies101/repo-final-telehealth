<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Appointment</title>

<!-- Custom Style -->
<link rel="stylesheet" href="assets/stylesheet/style.css"
	type="text/css">
<link rel="stylesheet" href="assets/stylesheet/header.css"
type="text/css">
<%@include file="includes/_linkshead.jsp"%>
<!-- Calendar CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
</head>

<body>

<%
	String uid = (String) session.getAttribute("uid");
	String role = (String) session.getAttribute("role");
		if (uid == null) 
			response.sendRedirect("login");
		
		if (role.equals("doctor")) 
			response.sendRedirect("doctor-dashboard.jsp");
%>
	
	<%@include file="includes/_header.jsp"%>
	<div class="card_wrapper mt-4">
		<div class="container border pb-3 pt-5">
			<h4 class="text-center form_name">Request Appointment</h4>
			<h6 class="text-center form_text">Form</h6>
			<div class="mb-3"></div>
			
			<form class="form_appointment" action="book-appointment" id="form-appointment">
				<div class="input-group mb-3">
				
					<select name="docname" class="form-select" id="inputGroupSelect01" required>
					
					<c:if test="${param.docname == null}">
					<option  selected="selected" disabled>Find Doctor</option>
					</c:if>
					
					<c:if test="${param.docname != null}">
					<option  selected="selected" value="<%=request.getParameter("docname")%>">Dr. <%=request.getParameter("docname")%></option>
					</c:if>			    
					
					    <c:forEach items="${listPatient}" var="a">
					    		<c:if test="${a.role == 'doctor'}">
					            <option value="${a.th_uid} ${a.th_fullname}">Dr. ${a.th_fullname}</option>
					            
					            <script>
								function reload()
								{
									let text = document.getElementById('inputGroupSelect01').value;
									let date = document.getElementById('inputGroupSelect05').value;
									var uid = "<%= session.getAttribute( "uid" ) %>";

									const myArray = text.split(" ", 1);
									const myArray2 = text.substr(text.indexOf(" ") + 1);;
									//const myArray2 = Arrays.toString(text.split(" ", 2));
											
									var doc = myArray;
									var doc2 = myArray2;
									//var docname = myArray2;
									self.location='listdocsched?did='+doc+'&date='+date+'&uid='+uid+'&docname='+doc2
								}
															
								</script>
								
					    		</c:if>
					        
					    </c:forEach>	
					    				
					</select>
							<input type="hidden" name="did" value="<%=request.getParameter("did")%>" />
							<input type="hidden" name="datee" value="<%=request.getParameter("date")%>" />
				</div>
				
				<div class="input-group mb-3">
					<input name="date" type="datetime-local" onChange="reload()" class="form-control" id="inputGroupSelect05" value="<%=request.getParameter("date")+"T13:00"%>"
						placeholder="Select Date.." required />
				</div>

				<div class="input-group mb-3">

					<select name="time" class="form-select" required>
					
						<option selected disabled>Select Time</option>
						
					    <c:forEach items="${schedule}" var="a">

				   				<c:if test="${a.th_taken eq 'False'}">
							    <option> ${a.th_time}</option>
							    </c:if>
							    
					    </c:forEach>					
					</select>
					
					
				</div>

				<div class="input-group mb-3">
					<textarea name="condition" class="form-control"
						style="height: 100%;" rows="8" id="validationTextarea"
						placeholder="Please tell us your current condition." ></textarea>
				</div>
				<button class="btn form-control custom_btn-background-blue" type="submit">Book</button>
			</form>
		</div>
	</div>
	
	
	<%@include file="includes/_linksfooter.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript" src="assets/js/modal.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
	<script>
		dateConfig = {
			minDate: "today",
			dateFormat: "Y-m-d"
		}
		timeConfig = {
			noCalendar: true,
			enableTime: true,
			dateFormat: "h:i K"
		}
		flatpickr("input[name=date]", dateConfig);
		flatpickr("input[name=time]", timeConfig);
		console.log(dateConfig)
		$("#form-appointment").submit(function(e) {
			e.preventDefault();
			
			var form = $(this);
			var url = form.attr('action');
			
			$.ajax({
				type: 'POST',
				url: 'book-appointment',
				data: form.serialize(),
				success: function(result){
					if (result == 'success'){
						modal(result, "Appointment Booked Success", "Please wait the doctor confirmation thank you!")
						$('input[type="text"],texatrea, select', this).val('');
					}else {
						modal(result, "Appointment Booked Failed", "Please Try again");
						$('input[type="text"],texatrea, select', this).val('');
					}
	    			location.reload()
				}
			})
		})
	</script>
</body>
</html>