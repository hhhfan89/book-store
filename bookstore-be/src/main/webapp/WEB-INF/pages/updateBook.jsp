<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8"/>
	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame Remove this if you use the .htaccess -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>Update Book - Bookstore</title>
	<meta name="description" content=""/>
	<meta name="author" content="Silvia Naro, Daniele Pasquini, Mary Angeni Uminga, Stefano Di Vito"/> 
	<meta name="viewport" content="width=device-width initial-scale=1.0"/>
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
<header class="headerAdd"> <h2>Update a book</h2></header>
<section class="row">
	<div class="span8">
		<form name="moduloUpdateBook" action="updateBook"  method="post" class="form-horizontal" id="billingform" accept-charset="utf-8" >
 
  			<div class="control-group">
				<label for="bookId" class="control-label">	
					ISBN
				</label>
				<div class="controls">
					<c:out  value="${book.bookId}"/>
					<input type="hidden" name="bookId" value="${book.bookId}"/>
				</div>
			</div>
			
 			<div class="control-group">
				<label for="title" class="control-label">	
					Title
				</label>
				<div class="controls">
					<c:out  value="${book.title}"/>
					<input type="hidden" name="title" value="${book.title}"/>
				</div>
			</div>
			
			<div class="control-group">
				<label for="price" class="control-label">	
					Price 
				</label>
				<div class="controls">
					<input name="price" type="number" min="0" step="any" value="${book.price}" id="price" required>
				</div>
			</div>
			
			<div class="control-group">
				<label for="numpages" class="control-label">	
					Pages number 
				</label>
				<div class="controls">
					<c:out  value="${book.numpages}"/>
					<input type="hidden" name="numpages" value="${book.numpages}"/>
				</div>
			</div>
			
			
			<div class="control-group">
				<label for="description" class="control-label">	
					Description 
				</label>
				<div class="controls">
					<input name="description" type="text" value="${book.description}" id="description" required>
				</div>
			</div>
			
			<div class="control-group">
				<label for="edition" class="control-label">	
					Edition 
				</label>
				<div class="controls">
					<c:out  value="${book.edition}"/>
					<input type="hidden" name="edition" value="${book.edition}"/>
				</div>
			</div>
			
			
				
			<div class="control-group">
				<label for="Image" class="control-label">	
					Upload Image 
				</label>
				<div class="controls">
					<c:out  value="${book.image}"/>
					<input type="hidden" name="image" value="${book.image}"/>
				</div>
			</div>
			
			<div class="control-group">
				<label for="Text" class="control-label">	
					Upload Text 
				</label>
			<div class="controls">
					<c:out  value="${book.text}"/>
					<input type="hidden" name="text" value="${book.text}"/>
				</div>
			</div>
				
			<div class="form-actions" id="formAddBook">
				<input id="button_AddBook" class="btn btn-large btn-primary" type="submit" value="Update Book" />
			</div>
			
		</form>
		<a href="/book-store/home">
		<button id="button_homeAddBook" class="btn btn-large btn-success" >
		Go to Home
		<i class="icon-home icon-white"></i>
		</button>
		</a>
	</div>
	<p>${msg}</p>
  </section>
  <footer>
		&copy; Copyright 2013 Bookstore by Silvia Naro, Daniele Pasquini, Mary Angeni Uminga, Stefano Di Vito
	</footer>
</body>
</html>