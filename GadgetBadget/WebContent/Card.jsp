<%@page import="com.Payee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Item handling</title>
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Site Metas -->
    <title>Gadget-Badget</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">



	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/pay.js"></script>
	

    <!-- Site Icons -->
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/custom.css">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6"> 
		
				<h1>Card Details</h1>
		
				<form id="formItem" name="formItem">
					Account ID: 
		 			<input id="accountID" name="accountID" type="text" 
		 					class="form-control form-control-sm">
		 					
		 			<br>Card Type: 
		 			<input id="cardType" name="cardType" type="text" 
		 					class="form-control form-control-sm">
		 
		 			<br> Name On Card: 
		 			<input id="nameOnCard" name="nameOnCard" type="text" 
		 					class="form-control form-control-sm">
		 
		 			<br> Card No: 
		 			<input id="cardNo" name="cardNo" type="text" 
		 					class="form-control form-control-sm">
		 
		 			<br> Expire Date: 
		 			<input id="expireDate" name="expireDate" type="text" 
		 					class="form-control form-control-sm">
		 					
		 			<br> CVV: 
		 			<input id="cvv" name="cvv" type="text" 
		 					class="form-control form-control-sm">
		 
		 			<br>
		 			<input id="btnSave" name="btnSave" type="button" value="Save" 
		 					class="btn btn-primary">
		 			<input type="hidden" id="hidItemIDSave" 
		 					name="hidItemIDSave" value="">
				</form>
					<br>
					
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>

					<br>
					<div id="divItemsGrid">
						 <%
						 	Payee payObj = new Payee(); 
						 	out.print(payObj.readItems()); 
						 %>
					</div>
				</div>
			</div>
		</div>

</body>
</html>