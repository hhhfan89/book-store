<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8"/>
	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame Remove this if you use the .htaccess -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>Delete User - Bookstore</title>
	<meta name="description" content=""/>
	<meta name="author" content="Silvia Naro, Daniele Pasquini, Mary Angeni Uminga, Stefano Di Vito"/> 
	<meta name="viewport" content="width=device-width; initial- scale=1.0"/>
	<link
	href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet">
	<link
	href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">
	<style type="text/css">
		body {
			padding-top: 40px;
			padding-bottom: 40px;
			margin-right:100px;
			margin-left: 100px;
		}
		#intestazione_tabella{
			background-color:none;
		}
		#button_home{
	  		margin-top: 15px;
	  		margin-bottom: 10px;
	  		margin-left: 380px;
		}
	</style>
	
	<script src="http://code.jquery.com/jquery.js"></script>
</head>

<body>
<header ><h1>Delete user</h1></header>
<section>
	<div class="row-fluid">
      <div class="span6"><h1>User List</h1></div>
      <div class="span6">
      <a href="/book-store/home"><button id="button_home" class="btn btn-success" >
			Go to Home <i class="icon-home icon-white"></i>
		</button></a></div>
    </div>
 	<table class="table table-hover">
 	<tr id="intestazione_tabella">
		<td>
		 	<b>Id</b>
		</td>
		<td>
			<b>Name</b>
		</td>
		<td>
			<b>Surname</b>
		</td>
		<td>
			<b>Username</b>
		</td>
		<td>
			<b>Email</b>
		</td>
		<td>
			<b>Role</b>
		</td>
		<td>
		
		</td>
	</tr>
	<c:forEach items="${userList}" var="user">
	<tr>
		<td>
		<c:out  value="${user.userId}"/>
		</td>
		<td>
		<c:out  value="${user.name}"/>
		</td>
		<td>
		<c:out  value="${user.surname}"/>
		</td>
		<td>
		<c:out  value="${user.username}"/>
		</td>
		<td>
		<c:out  value="${user.email}"/>
		</td>
		<td>
		<c:out  value="${user.role.title}"/>
		</td>
		<td>
		<a href="<c:url value="/home/deleteUser/${user.userId}" />">
				<button type="submit" class="btn" >
					
					<i class="icon-trash"></i>
				</button>
				</a>
		</td>
	</tr>
	<tr></tr>
	</c:forEach>
	</table>
</section>
<footer>
		&copy; Copyright 2013 Bookstore by Silvia Naro, Daniele Pasquini, Mary Angeni Uminga, Stefano Di Vito
</footer>
</body>
</html>