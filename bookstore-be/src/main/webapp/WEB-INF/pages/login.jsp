<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8"/>
	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame Remove this if you use the .htaccess -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>Login - Bookstore</title>
	<meta name="description" content=""/>
	<meta name="author" content="Silvia Naro, Daniele Pasquini, Mary Angeni Uminga, Stefano Di Vito"/> 
	<meta name="viewport" content="width=device-width; initial- scale=1.0"/>
	<link
	href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">
	<link
	href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">
	
	<style>
		body {
			padding-top: 40px;
			padding-bottom: 40px;
			background-color: #f5f5f5;
		}

		.form-signin {
			max-width: 300px;
			padding: 19px 29px 29px;
			margin: 0 auto 0 auto;
			background-color: #fff;
			border: 1px solid #e5e5e5;
			-webkit-border-radius: 5px;
			-moz-border-radius: 5px;
			border-radius: 5px;
			-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
			-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
			box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
		}

		.form-signin .form-signin-heading,.form-signin .checkbox {
			margin-bottom: 10px;
		}

		.form-signin input[type="text"],.form-signin input[type="password"] {
			font-size: 16px;
			height: auto;
			margin-bottom: 15px;
			padding: 7px 9px;
		}

		#formLogin {
			margin: 120 150 120 150;
		}

		#errorTextView {
			margin: 0 auto 0 auto;
		}

</style>

</head>

<body onload='document.f.j_username.focus();'>
		
	<div class="span10" id="formLogin">
		
		<c:if test="${not empty error}">
			<div class="errorblock" id="errorTextView">
				Your login attempt was not successful, try again.<br /> Caused :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>

		<form class="form-signin" name='f'
			action="<c:url value='j_spring_security_check' />" method='POST'>
			<h2 class="form-signin-heading">Login</h2>
			<!-- Name of input as j_username follow Spring Security standard -->
			<label>Username</label> <input type="text" name="j_username"
				class="span3" required>
			<label>Password</label> <input type="password" name="j_password"
				class="span3" required>
			<div>
				<input type="submit" value="Login" class="btn btn-primary">
			</div>
		</form>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>

</body>

</html>