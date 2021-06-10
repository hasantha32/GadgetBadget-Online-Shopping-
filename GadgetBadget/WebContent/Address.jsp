<%@page import="com.Ship"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/addr.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6"> 
		
				<h1>Address Details</h1>
		
				<form id="formItem" name="formItem">
					Account ID: 
		 			<input id="accountID" name="accountID" type="text" 
		 					class="form-control form-control-sm">
		 					
		 			<br>First Name: 
		 			<input id="firstName" name="firstName" type="text" 
		 					class="form-control form-control-sm">
		 
		 			<br> Last Name: 
		 			<input id="lastName" name="lastName" type="text" 
		 					class="form-control form-control-sm">
		 
		 			<br> User Name: 
		 			<input id="userName" name="userName" type="text" 
		 					class="form-control form-control-sm">
		 
		 			<br> Email: 
		 			<input id="email" name="email" type="text" 
		 					class="form-control form-control-sm">
		 					
		 			<br> Address 1: 
		 			<input id="address1" name="address1" type="text" 
		 					class="form-control form-control-sm">
		 					
		 			<br> Address 2: 
		 			<input id="address2" name="address2" type="text" 
		 					class="form-control form-control-sm">
		 					
		 			<br> Country: 
		 			<input id="country" name="country" type="text" 
		 					class="form-control form-control-sm">
		 					
		 			<br> State: 
		 			<input id="state" name="state" type="text" 
		 					class="form-control form-control-sm">
		 					
		 			<br> Zip Code: 
		 			<input id="zipCode" name="zipCode" type="text" 
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
						 	Ship shipObj = new Ship(); 
						 	out.print(shipObj.readItems()); 
						 %>
					</div>
				</div>
			</div>
		</div>

</body>
</html>