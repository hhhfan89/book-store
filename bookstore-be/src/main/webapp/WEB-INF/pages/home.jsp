<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html> 
<html lang="en">
<head>
	<meta charset="utf-8"/>
	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame Remove this if you use the .htaccess -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<title>Bookstore Admin Panel</title>
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
</head>
<body>  
	<header class="page-header">
		<hgroup>
			<h1>BOOKSTORE ADMIN PANEL</h1> 
		</hgroup>		
	<nav>
		Logged in as ${username}
		<a href="home"><button class="btn">Home</button></a>
		<a href="<c:url value="/j_spring_security_logout" />"><button class="btn"> Logout</button></a>
		<a href="mailto:psqdnl@hotmail.it"><button class="btn">Contacts</button></a>
	</nav>
	</header>
	
	
	<section>
	<section>
		<h1>Book Manager</h1>
		<p>
			Management of the books in the bookstore.
      	</p>
      	<form class="formAddBook" action="home/showAddBookPage" method="get">
				<button id="buttonAdd" type="submit" class="btn" >Add a new book</button>
		</form>
		<form class="formDelete" action="home/showDeleteBookPage" method="get">
				<button id="buttonDelete" type="submit" class="btn" >Update/Delete a book</button>
		</form>
	</section> 
		<section>
		<h1>User Manager</h1>
		<p>
			Management of the users in the bookstore.
      	</p>
      	<form class="formAddUser" action="home/showAddUserPage" method="get">
				<button id="buttonAdd" type="submit" class="btn" >Add a new user</button>
		</form>
		<form class="formDelete" action="home/showDeleteUserPage" method="get">
				<button id="buttonDelete" type="submit" class="btn" >Delete a user</button>
		</form>
	</section> 
	<section>
		<h1>Author Manager</h1>
		<p>
			Management of the authors in the bookstore.
      </p>
      <form class="formAddAuthor" action="home/showAddAuthorPage" method="get">
				<button id="buttonAdd" type="submit" class="btn" >Add a new author</button>
		</form>
		<form class="formDelete" action="home/showDeleteAuthorPage" method="get">
				<button id="buttonDelete" type="submit" class="btn" >Delete an author</button>
		</form>
	</section> 
	<section>
		<h1>Category Manager</h1>
		<p>
			Management of the categories in the bookstore.
      </p>
      <form class="formAddCategory" action="home/showAddCategoryPage" method="get">
				<button id="buttonAdd" type="submit" class="btn" >Add a new category</button>
		</form>
		<form class="formDelete" action="home/showDeleteCategoryPage" method="get">
				<button id="buttonDelete" type="submit" class="btn" >Delete a category</button>
		</form>
	</section>
	<section>
		<h1>Editor Manager</h1>
		<p>
			Management of the editors in the bookstore.
      </p>
      <form class="formAddEditor" action="home/showAddEditorPage" method="get">
				<button id="buttonAdd" type="submit" class="btn" >Add a new editor</button>
		</form>
		<form class="formDelete" action="home/showDeleteEditorPage" method="get">
				<button id="buttonDelete" type="submit" class="btn" >Delete an editor</button>
		</form>
	</section>
	</section>
	<footer>
		&copy; Copyright 2013 by Silvia Naro, Daniele Pasquini, Mary Angeni Uminga, Stefano Di Vito
	</footer>
</body> 
</html>
