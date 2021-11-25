<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Online Telehealth System</title>
<%@include file="includes/_linkshead.jsp" %>
<link href="assets/stylesheet/payment.css" rel="stylesheet">

</head>
<body>
	<div class="container contact pt-2 pb-2 mt-5 ">
        <div class="digital-payment  center ">
        <div class="dg-inside">
            <div class="toplogo bg-blue ">
                <i class="fas fa-laptop-medical fa-2x logo mb-2"></i>
                <br> <b> Choose a payment method</b>  
            </div>
            <br><br>
            <div class="row">
            	<a href="https://getpaid.gcash.com/checkout/10f1958c888d9d9bc3d7a9d48f32ac25?testmode=true" target="_blank">
                <div class="col-md-6 col-sm-12">
                  <div class="card dgm">
                    <img class="digital-method" src="./img/GCash-logo.jpg">
                  </div>
                  </a>
                </div>
                
                <div class="col-md-6 col-sm-12">
                  <a href="https://www.paypal.com/myaccount/transfer/homepage" target="_blank">
                  <div class="card dgm">
                    <img class="digital-method" src="./img/paypal-logo.png">
                  </div>
                  </a>
                </div>
            </div> <br> 
            
            <div class="container">
              <p> Pay via Gcash <br>
                 For payment thru Gcash, please provide your full name, <i>own registered mobile/gcash number,</i>
                and email and click the Pay Now button.
              </p> 
              
              <p> Pay via Paypal  <br>
                  For payment thru Paypal, please login to your account first. After logging in,
                 please enter our email <i> onlinetelehealthservices@gmail.com </i> then click next. 
                 You will see the form to enter an amount and a note, please enter PHP 300.00 as amount
                 then enter your name, email and mobile number as note. After this, click Continue.
              </p>
              
            </div>
            <center>  <a class="btn btn-done mt-2" href="patient-dashboard"> Back to Dashboard </a> 
            </center>
            </div>
         </div>      
    </div>
</body>
</html>