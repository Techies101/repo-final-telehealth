<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Telehealth</title>
<%@include file="./includes/_linkshead.jsp"%>
<link rel="stylesheet" href="assets/stylesheet/landingpage.css">
<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />

</head>
<body>

	<%@include file="./includes/_header.jsp"%>
	<%@include file="./includes/_showcase.jsp"%>
	<%@include file="./includes/_footer.jsp"%>

	<%@include file="./includes/_linksfooter.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
	<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript" src="assets/js/modal.js"></script>

	<script>
		AOS.init();
		$('.carousel')

		$("#send-message")
				.submit(
						function(e) {
							e.preventDefault();
							var form = $(this);
							var url = form.attr('action')

							$
									.ajax({
										type : "POST",
										url : url,
										data : form.serialize(),
										success : function(result) {
											if (result == 'success') {
												modal(result, "Message Sent",
														"Thank you for subscribing to us we will email you shortly")
												$('input[type="text"],textarea')
														.val('');
											} else {
												modal(result, "Sending failed",
														"Something went wrong! please try again.")
											}
										}
									});
						})

	</script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script type="text/javascript" src="assets/js/notif.js"></script>
</body>

</html>