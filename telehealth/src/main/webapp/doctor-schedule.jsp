<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Available Time</title>
	
	<%@include file="includes/_linkshead.jsp"%>
	<link rel="stylesheet" href="assets/stylesheet/header.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
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
.btn-danger{
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

	<%
	String uid = (String) session.getAttribute("uid");
	String role = (String) session.getAttribute("role");
		if (uid == null) 
			response.sendRedirect("login");
		
		if (role.equals("patient")) 
			response.sendRedirect("patient-dashboard.jsp");
	%>
	
	<%@include file="includes/_header.jsp"%>
	<div class="container-search ">

	<table id="appointment" class="groove border">
		<thead>
			<tr>
				<th>Date</th>
				<th>Time</th>
				<th>Status</th>
				<th>Action</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="a" items="${schedule}">
					<tr>
						<td><c:out value="${a.th_date}" /></td>
						<td><c:out value="${a.th_time}" /></td>
						
						<td>
						<c:choose>
						
						<c:when test="${a.th_taken eq 'True'}">
						<c:out value="Spot Taken" />
						</c:when>
						
						<c:otherwise>
						<c:out value="Spot Available" />
						</c:otherwise>
						</c:choose>
						</td>
						
						<td>
						<c:choose>
						
						<c:when test="${a.th_taken eq 'False'}">
						<button id="drop${a.th_id}" class="btn btn-danger"><i class="fa fa-trash"></i> Remove</button>		
						
							<script>
		//FOR REMOVING SCHEDULED TIME
        document.getElementById('drop'+${a.th_id}).addEventListener('click', function(){

        	Swal.fire({
        	  title: 'Are you sure?',
        	  text: "Scheduled time will be deleted",
        	  inputAttributes: {
        		    autocapitalize: 'off'
        		  },
        	  icon: 'warning',
        	  showCancelButton: true,
        	  cancelButtonColor: '#1c87c9',
        	  confirmButtonColor: '#d33',
        	  confirmButtonText: 'Remove'
        	}).then((result) => {
        	  if (result.isConfirmed) {
        	    Swal.fire(
        	      'Removed!',
        	      'the scheduled time has been removed.',
        	      'success'
        	    )
        	    
        	    var formData = new FormData();
    			
    			formData.append("drop", ${a.th_id});
    			
    			fetch ('http://localhost:8080/telehealth/dropSchedule',{
    				method: 'POST',
					body: formData
    			})
    			location.reload()
        	  }

        	});
        })
</script>
						
						</c:when>
						
						<c:otherwise>
						<c:out value="" />
						</c:otherwise>
						
						</c:choose>
						</td>
						
					</tr>
			</c:forEach>
		</tbody>
	</table>

							<div class="d-flex">
						<button id="schedule" class="btn btn-primary"><i class="fa fa-clock"></i> Add Schedule</button>
						</div>
						
						<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
						<script>
						//FOR DECLINE W/ REASON
				        document.getElementById('schedule').addEventListener('click', function(){
				        	let flatpickrInstance

				        	
				        	Swal.fire({
				        		title: 'Add your available time',
				        		  html: '<input class="swal2-input" id="time">',
				        		  stopKeydownPropagation: false,
				        		  preConfirm: () => {
									const time = Swal.getPopup().querySelector('#time')
				        		  },
				        		  willOpen: () => {
				        		    flatpickrInstance = flatpickr(Swal.getPopup().querySelector('#time'),		
				        		    		
				        		    {
				        		        enableTime: true,
				        		        minDate: "today",
				        		        dateFormat: "Y-m-d H:i",
				        		        "disable": [
				        		            function(date) {
				        		                // return true to disable
				        		                return (date.getDay() === 0 || date.getDay() === 6);

				        		            }
				        		        ],
				        		        "locale": {
				        		            "firstDayOfWeek": 1 // start week on Monday
				        		        }
				        		    }
				        		    )
				        		    return flatpickrInstance
				        		    console.log(flatpickrInstance)
				        		  },
				        		  icon: 'question',
				            	  showCancelButton: true,
				            	  confirmButtonColor: '#1c87c9',
				            	  cancelButtonColor: '#d33',
				            	  confirmButtonText: 'Add'
				        	}).then((result) => {
				        	  if (result.isConfirmed) {
				        	    Swal.fire(
				        	      'Added!',
				        	      'Your available time has been added.',
				        	      'success'
				        	    )
				        	    
				        	    var formData = new FormData();
				    			
			    			formData.append("date", flatpickrInstance.selectedDates[0].getUTCFullYear()+"-"+(flatpickrInstance.selectedDates[0].getUTCMonth()+1)+"-"+flatpickrInstance.selectedDates[0].getDate());
			    			formData.append("time", flatpickrInstance.selectedDates[0].getHours()+":"+flatpickrInstance.selectedDates[0].getMinutes());
				    			formData.append("uid", uid);
			        		    console.log(flatpickrInstance.selectedDates[0].getDate())

				    			fetch ('http://localhost:8080/telehealth/addSchedule',{
				    				method: 'POST',
									body: formData
				    			})
				    			location.reload()
				        	  }
				
				        	});
        })
</script>
</div>

</body>
</html>