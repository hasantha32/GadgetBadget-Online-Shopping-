<%@page import="com.Registry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/registry.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6"> 
		
				<h1>SignUp</h1>
		
				<form id="formItem" name="formItem">
		 			First Name: 
		 			<input id="firstName" name="firstName" type="text" 
		 					class="form-control form-control-sm">
		 
		 			<br> Last Name: 
		 			<input id="lastName" name="lastName" type="text" 
		 					class="form-control form-control-sm">
		 
		 			<br> Email: 
		 			<input id="email" name="email" type="text" 
		 					class="form-control form-control-sm">
		 
		 			<br> Password: 
		 			<input id="password" name="password" type="text" 
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
						 	Registry regObj = new Registry(); 
						 	out.print(regObj.readItems()); 
						 %>
					</div>
				</div>
			</div>
		</div>

</body>
</html>