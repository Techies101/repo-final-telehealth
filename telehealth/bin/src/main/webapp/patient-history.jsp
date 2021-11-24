<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<title>Online Telehealth Services</title>
		<%@include file="includes/_linkshead.jsp"%>
	<script src="assets/js/modal.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="assets/stylesheet/header.css">
	<link rel="stylesheet" href="assets/stylesheet/dashboard.css">
</head>

<body>	
	<%@include file="includes/_header.jsp" %>
		
		<div class="container container-search col-lg-7">
			<div class="row">
				<div class="col py-3" align="left">
					<h5 class="text-left">My Laboratory History</h5>
				</div>
				<div class="col py-3" align="right">
					<button id="uploadLab1" class="btn btn-primary"><i class="fa fa-upload"></i> Upload</button>
				</div>
			</div>
			<!--table-->
			<div class="table-responsive">
				<table class="table " id="appointment">
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
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>