<?PHP

$book_id = "";
$errorMessage = "";
$row = 0;
$result = "";
$uname = "";
$pword = "";
$errorMessage = "";
$loginErr="";


function quote_smart($value, $handle) {

	if (get_magic_quotes_gpc()) {
		$value = stripslashes($value);
	}

	if (!is_numeric($value)) {
		$value = "'" . mysql_real_escape_string($value, $handle) . "'";
	}
	return $value;
}


if ($_SERVER['REQUEST_METHOD'] == 'GET'){
 	
	if(isset($_GET['id'])){
			
	$book_id = $_GET['id'];
	include 'scripts/connect-sql.php';
	
	if ($db_found) {
		
		 $SQL = "SELECT b.book_id, b.price, b.numpages, b.title, b.description, b.image, b.text, b.edition, a.name as authorName, a.surname, e.name as editorName
		  		 FROM books b, authors a, editors e, boau01 ba, boed01 be
		  		 WHERE b.book_id = $book_id 
		  		 	   AND ba.book_id = b.book_id AND a.author_id = ba.author_id
		  		 	   AND be.book_id = b.book_id AND e.editor_id = be.editor_id";
					   
		 $result = mysql_query($SQL);
		 
		 if($result==null){
			 $errorMessage = $errorMessage . "Nessun risultato trovato" . "<BR>";
		 }
		
		 mysql_close($db_handle);
		
	 }
	 else {
	 	$errorMessage = "Database Not Found";
	 }
	 
	}  

 }





?>


<!DOCTYPE html>
<html lang="it">
	<head>
		<meta charset="utf-8">
    <title>Book-store</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="../assets/ico/favicon.png">
  </head>

  <body>
     <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <header>
          <a class="brand">Book-store</a>
          <nav class="nav-collapse collapse">
            <ul class="nav">
              <li itemscope itemtype="http://data-vocabolary.org/Breadcrumb"><a href="home.php" itemprop="url">Home</a></li>
              <li itemscope itemtype="http://data-vocabolary.org/Breadcrumb"><a href="chisiamo.php" itemprop="url">Chi siamo</a></li>
            </ul>
            <ul class="nav pull-right">
            	<li><a href="scripts/logout.php">Logout</a></li>
            </ul>
          </nav><!--/.nav-collapse -->
          </header>
        </div>
      </div>
    </div>

    <div class="container">
      <!-- Main hero unit for a primary marketing message or call to action -->
      <header class="hero-unit" itemscope="http://schema.org/Thing">
        <h1 itemprop="name">Book-store</h1>
        <br />
        <h4 itemprop="description"><i>"Le letture non si consigliano, se non ai principianti del leggere. Ognuno deve trovare le 
        	proprie letture con l'istinto, che − nel lettore abituato − diventa quasi sempre infallibile."</i></br>
		- Massimo Bontempelli, Il Bianco e il Nero, 1987
		</h4>
      </header>
      

      <!-- Example row of columns -->
      <div class="row">
        <section class="span9">
        	<header>
         	 <h2>Risultato ricerca</h2>
         </header> 
        	<article>
		
    	<table class="table table-hover">
			<thead>  
			    <tr>  
		            <th> </th>  
		            <th><h4>Informazioni libro</h4></th>
		        </tr>  
	        </thead>  
	        <tbody>  
          		<?PHP while ( $row = mysql_fetch_assoc($result)) {
                  $imageUrl = $row['image'];
				  $bookUrl = $row['text'];
                  $comment = $row['title'];
				  echo "<tr>"; 
				  echo "<th>Titolo</th>";  
			      echo "<td>" . $row['title'] . "</td>";
			      echo "</tr>";
				  echo "<tr>"; 
				  echo "<th>Autore</th>";  
			      echo "<td>" . $row['surname'] . ", " . $row['authorName'] . "</td>";
			      echo "</tr>";
			      echo "<tr>";
				  echo "<th>Prezzo (€)</th>";  
			      echo "<td>" . $row['price'] . "</td>";
			      echo "</tr>";
			      echo "<tr>"; 
			      echo "<th>Num. pagine</th>";  
			      echo "<td>" . $row['numpages'] . "</td>";
			      echo "</tr>";
				  echo "<tr>"; 
			      echo "<th>Editore</th>";  
			      echo "<td>" . $row['editorName'] . "</td>";
			      echo "</tr>";
				  echo "<tr>"; 
				  echo "<th>Edizione</th>";  
			      echo "<td>" . $row['edition'] . "</td>";
			      echo "</tr>";
			      echo "<tr>"; 
				  echo "<th>Descrizione</th>";  
			      echo "<td>" . $row['description'] . "</td>";
			      echo "</tr>"; 
			      echo "<tr>";
				  echo "<th>Copertina</th>";  
			      echo "<td><img src=\"$imageUrl\" alt=\"$comment\" width=\"150\" height=\"100\"></td>";
			      echo "</tr>"; 
				  echo "<tr>";
				  echo "<th></th>";  
				  echo "<td>";
				  echo "<form action=\"$bookUrl\">";
    			  echo "<input type=\"submit\" value=\"Scarica questo libro\">";
				  echo "</form>";
				  echo "</td>";
			      echo "</tr>"; 
			    } ?>
			</tbody>
			</table>
			</article>
        </section>

        <aside class="span3">
	     	<section>
				<div>
					<img src="img/images (1).jpg"/>					
				</div>
				<hr>
				<div>
					<img src="img/images.jpg"/>					
				</div>
			</section>
        </aside>
        
      </div>

      <hr>
		
		
      
      <footer>
        <p>&copy; Book Store</p>
      </footer>

    </div> <!-- /container -->

		<!-- Le javascript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="jquery.js"></script>
		<script src="bootstrap-transition.js"></script>
		<script src="bootstrap-alert.js"></script>
		<script src="bootstrap-modal.js"></script>
		<script src="bootstrap-dropdown.js"></script>
		<script src="bootstrap-scrollspy.js"></script>
		<script src="bootstrap-tab.js"></script>
		<script src="bootstrap-tooltip.js"></script>
		<script src="bootstrap-popover.js"></script>
		<script src="bootstrap-button.js"></script>
		<script src="bootstrap-collapse.js"></script>
		<script src="bootstrap-carousel.js"></script>
		<script src="bootstrap-typeahead.js"></script>

		<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
		<script src="https://github.com/twitter/bootstrap/blob/master/docs/assets/js/application.js" ></script>
		<script src="js/bootstrap.js"></script>

	</body>
</html>