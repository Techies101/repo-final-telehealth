<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
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

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Online Telehealth Services</title>
	<%@include file="includes/_linkshead.jsp"%>
	<script src="assets/js/modal.js"></script>
	<link rel="stylesheet" href="assets/stylesheet/header.css">
</head>

<body>	
	<%@include file="includes/_header.jsp" %>
		<!-- second container -->
		<div class="container-search">
			<h5 class="text-left">My Laboratory History</h5>
			<br>

			<!--table-->
			<div class="table-responsive">
				<table class="table  table-hover " id="appointment">
					<thead>
						<tr>
							<th class="thbg-color">Date</th>
							<th class="thbg-color">Time</th>
							<th class="thbg-color">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="a" items="${patientLab}">
							<tr>
								<td><c:out value="${a.th_date}" /></td>
								<td><c:out value="${a.th_time}" /></td>

								<c:if test="${a.th_image != null}">
									<td><button id="view${a.th_id}" class="btn btn-primary"><i class="fa fa-eye"></i> View</button>

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
							</script></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
	
				<div align="right">
					<button id="uploadLab1" class="btn btn-primary"><i class="fa fa-upload"></i> Upload</button>
				</div>
				<script>
				
				
        	
		//FOR UPLOADING									
        document.getElementById('uploadLab'+1).addEventListener('click', function(){
        	(async () => {

        		const { value: file } = await Swal.fire({
        		  title: 'Select Lab Result Image',
        		  input: 'file',
            	  confirmButtonColor: '#1c87c9',
        		  inputAttributes: {
        		    'accept': 'image/*',
        		    'aria-label': 'Upload Your Lab Image'
        		  }
        		})

        		if (file) {
        		  const reader = new FileReader()
        		  
        		  reader.onload = (e) => {
        		    Swal.fire({
        		      title: 'Your Uploaded Lab Image',
        		      imageUrl: e.target.result,
        		      imageAlt: 'The Uploaded Image'
        		    })
        		    
        				var formData = new FormData();
		    			formData.append("image", file);
		    			fetch ('http://localhost:8080/telehealth/uploadLab',{
		    				method: 'POST',
							body: formData
		    			}).then((result) => {
		    				if (result.status === 200) {
		    					location.reload()
		    				}else {
		    					modal('error', "Upload failed!", "Something went wrong!")
		    				}
		    			})
    					
        		  }
        		  reader.readAsDataURL(file)
        		}
			
        		})()
        		
        	});
</script>
			</div>
		</div>

		<footer class="py-3 my-4 mt-5 systemfooter ">
			<ul class="nav justify-content-center pb-3 mb-3">
				<li>
					<p class="text-center">Service you can always count on</p>
				</li>
			</ul>
			<p class="text-center text-muted">&copy; 2021 Online Telehealth
				Services, Inc</p>
		</footer>
</body>
</html>