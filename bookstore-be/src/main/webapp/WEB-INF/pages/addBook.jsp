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
	<title>Add Book - Bookstore</title>
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
<header class="headerAdd"> <h2>Add a book</h2></header>
<section class="row">
	<div class="span8">
		<form:form action="addBook" method="post" class="form-horizontal" commandName="book" enctype="multipart/form-data">
 
  			<div class="control-group">
				<label for="bookId" class="control-label">	
					ISBN
				</label>
				<div class="controls">
					<form:input path="bookId" required = "true"/>
				</div>
			</div>
			
 			<div class="control-group">
				<label for="title" class="control-label">	
					Title
				</label>
				<div class="controls">
					<form:input path="title" required = "true"/>
				</div>
			</div>
			
			<div class="control-group">
				<label for="price" class="control-label">	
					Price 
				</label>
				<div class="controls">
					<form:input path="price" type="number" min="0" step="any" required = "true"/>
				</div>
			</div>
			
			<div class="control-group">
				<label for="numpages" class="control-label">	
					Pages number 
				</label>
				<div class="controls">
					<form:input path="numpages" type="number" min="0" required = "true"/>
				</div>
			</div>
			
			
			<div class="control-group">
				<label for="description" class="control-label">	
					Description 
				</label>
				<div class="controls">
					<form:input path="description" required = "true"/>
				</div>
			</div>
			
			<div class="control-group">
				<label for="edition" class="control-label">	
					Edition 
				</label>
				<div class="controls">
					<form:input path="edition" type="number" required = "true"/>
				</div>
			</div>
			
			<div class="control-group">
				<label for="Editor" class="control-label">	
					Editor 
				</label>
				<div class="controls">
					<form:select path="listEditor" items="${listEditor}" itemValue="name" itemLabel="name" required = "true"/>
				</div>
			</div>
			
			<div class="control-group">
				<label for="Author" class="control-label">	
					Author 
				</label>
				<div class="controls">
					<form:select path="listAuthor" items="${listAuthor}" itemValue="surname" itemLabel="surname" multiple="true" required = "true"/>
				</div>
			</div>
			
			<div class="control-group">
				<label for="Category" class="control-label">	
					Category 
				</label>
				<div class="controls">
					<form:select path="listCategory" items="${listCategory}" itemValue="name" itemLabel="name" multiple="true" required = "true"/>
				</div>
			</div>
				
			<div class="control-group">
				<label for="Image" class="control-label">	
					Upload Image 
				</label>
				<div class="controls">
					<form:input type="file" path="imageFile" accept="image/jpg, image/png" required="true"/>
				</div>
			</div>
			
			<div class="control-group">
				<label for="Text" class="control-label">	
					Upload Text 
				</label>
			<div class="controls">
					<form:input type="file" path="textFile" accept="text/doc, text/pdf, text/rtf, text/txt" required="true"/>
				</div>
			</div>
				
			<div class="form-actions" id="formAddBook">
				<input id="button_AddBook" class="btn btn-large btn-primary" type="submit" value="Save Book" />
			</div>
			
		</form:form>
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