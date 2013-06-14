<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8"/>
	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame Remove this if you use the .htaccess -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>Add Editor - Bookstore</title>
	<meta name="description" content=""/>
	<meta name="author" content="Silvia Naro, Daniele Pasquini, Mary Angeni Uminga, Stefano Di Vito"/> 
	<meta name="viewport" content="width=device-width; initial- scale=1.0"/>
	<link
	href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">
	<link
	href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">
	<link
	href="<%=request.getContextPath()%>/resources/bootstrap/css/styleHome.css"
	rel="stylesheet">
	<style type="text/css">
		body {
		padding-top: 40px;
		padding-bottom: 40px;
		background-color: #f5f5f5;
		}
	</style>

		
</head>

<body>
<header class="headerAdd"><h2>Add editor</h2></header>
<section  class="row">
	<div class="span8">
		<form name="modulo" action="addEditor" method="post" class="form-horizontal" id="billingform" accept-charset="utf-8" onSubmit="return controllo();">
			<div class="control-group">
				<label for="name" class="control-label">	
					Name 
				</label>
				<div class="controls">
					<input name="name" type="text" value="" id="name" required>
				</div>
			</div>
			<div class="form-actions" id="formAddEditor">
				<button id="button_AddEditor" type="submit" class="btn btn-large btn-primary">Save editor information</button>
			</div>
		</form>
		<a href="/book-store/home">
			<button id="button_homeAddEditor" class="btn btn-large btn-success" >
				Go to Home
						<i class="icon-home icon-white"></i>
			</button>
		</a>
	</div> <!-- .span8 -->
	<p>${msg}</p>
</section>
	<footer>
		&copy; Copyright 2013 Bookstore by Silvia Naro, Daniele Pasquini, Mary Angeni Uminga, Stefano Di Vito
	</footer>
</body>
</html>