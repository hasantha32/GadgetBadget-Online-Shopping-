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


 <!-- Start Main Top -->
    <div class="main-top">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="custom-select-box">
                        <select id="basic" class="selectpicker show-tick form-control" data-placeholder="$ USD">
							<option>¥ JPY</option>
							<option>$ USD</option>
							<option>€ EUR</option>
						</select>
                    </div>
                    <div class="right-phone-box">
                        <p>Call US :- <a href="#"> +11 900 800 100</a></p>
                    </div>
                    <div class="our-link">
                        <ul>
                            <li><a href="my-account.html"><i class="fa fa-user s_color"></i> My Account</a></li>
                            <li><a href="#"><i class="fas fa-location-arrow"></i> Feedback for products</a></li>
                            <li><a href="contact-us.html"><i class="fas fa-headset"></i> Contact Us</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="login-box">
					       <div class="col-12 d-flex shopping-box"><a href="my-account.html" class="ml-auto btn hvr-hover">Logout</a> </div>
						
						
					
						
						
					</div>
                    <div class="text-slid-box">
                        <div id="offer-box" class="carouselTicker">
                            <ul class="offer-box">
                                <li>
                                    <i class="fab fa-opencart"></i> 20% off Entire Purchase Promo code: offT80
                                </li>
                                <li>
                                    <i class="fab fa-opencart"></i> 50% - 80% off on Beets headphones
                                </li>
                                <li>
                                    <i class="fab fa-opencart"></i> Off 10%! Shop drone cameras
                                </li>
                                <li>
                                    <i class="fab fa-opencart"></i> Off 50%! Shop Now
                                </li>
                                <li>
                                    <i class="fab fa-opencart"></i> Off 10%! Shop  CANON cameras
                                </li>
                                <li>
                                    <i class="fab fa-opencart"></i> 50% - 80% off on Samsung phones
                                </li>
                                <li>
                                    <i class="fab fa-opencart"></i> 20% off Entire Purchase Promo code: offT30
                                </li>
                                <li>
                                    <i class="fab fa-opencart"></i> Off 50%! Shop Now 
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Main Top -->
  <!-- Start Main Top -->
    <header class="main-header">
        <!-- Start Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light navbar-default bootsnav">
            <div class="container">
                <!-- Start Header Navigation -->
                <div class="navbar-header">
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-menu" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>
                    <a class="navbar-brand" href="index.jsp"><img src="images/logo.png" class="logo" alt=""></a>
                </div>
                <!-- End Header Navigation -->


                <!-- Collect the nav links, forms, and other content for toggling -->
                         <div class="collapse navbar-collapse" id="navbar-menu">
                    <ul class="nav navbar-nav ml-auto" data-in="fadeInDown" data-out="fadeOutUp">
                        <li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="my-account.html">Accounts</a></li>
                     
                       
                    </ul>
                </div>
                <!-- /.navbar-collapse -->

                <!-- Start Atribute Navigation -->
                <div class="attr-nav">
                    <ul>
                        <li class="search"><a href="#"><i class="fa fa-search"></i></a></li>
                        <li class="side-menu"><a href="#">
						<i class="fa fa-shopping-bag"></i>
                            <span class="badge">3</span>
							<p>My Cart</p>
					</a></li>
                    </ul>
                </div>
                <!-- End Atribute Navigation -->
            </div>
        
        </nav>
        <!-- End Navigation -->
    </header>
    <!-- End Main Top -->

    <!-- Start Top Search -->
    <div class="top-search">
        <div class="container">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-search"></i></span>
                <input type="text" class="form-control" placeholder="Search">
                <span class="input-group-addon close-search"><i class="fa fa-times"></i></span>
            </div>
        </div>
    </div>
    <!-- End Top Search -->








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