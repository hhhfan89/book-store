<?PHP
header('Content-type: text/html; charset=utf-8');

//check if user is logged in, if not, redirect to index page where there is login form
session_start();

if (!(isset($_SESSION['login']) && $_SESSION['login'] != '')) {

header ("Location: index.php");

}

$term = "";
$termine = "";
$category = "";
$book_id = "";
$errorMessage = "";
$row = 0;
$generalSearchResult = "";
$categorySearchResult = "";
$term1 = "";

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
	
	if(isset($_GET['categoryName'])){
		//categoria da cercare, scelta dal menu a tendina
		$category = $_GET['categoryName'];
		include 'scripts/connect-sql.php';
	
		if ($db_found) {
			//query per la ricerca per categoria
			$SQL = "SELECT b.book_id, b.price, b.numpages, b.title, b.description, b.image 
					FROM books b, boca01 bc, categories c 
					WHERE c.name = '$category' AND bc.category_id = c.category_id AND b.book_id = bc.book_id";
			
			//risultati della query
			$categorySearchResult = mysql_query($SQL);
			
		} else {
			$errorMessage = "Database Not Found";
		}
	}
	
}



//funzione per ottenere dinamicamente i campi del menu a tendina per la ricerca per categoria
function getCategories() {

	include 'scripts/connect-sql.php';

	if ($db_found) {

		$SQL = "SELECT * FROM categories";
		$categoryResult = mysql_query($SQL);

		if ($categoryResult == null) {
			print "errore";
		} else {
			return $categoryResult;
		}

	} else {
		$errorMessage = "database null";
	}
	mysql_close($db_handle);
}



//funzione che, dato in input i risultati della query sulla categoria selezionata dall'utente,
//restituisce i libri appartenenti a quella categoria
function getCategorySearchResults($categorySearchResult){
	
	if($row=mysql_num_rows($categorySearchResult)==0){
		echo "Nessun risultato trovato!";
	}
	else{
	?>	
	
	<table class="table table-hover">
		<thead>  
			<tr>  
				<th>Title</th>  
		        <th>Description</th>
		    </tr>  
	    </thead>  
	    <tbody>  <?php
        	while ( $row = mysql_fetch_assoc($categorySearchResult)) {
        		$id = $row['book_id']; ?>
				<tr>
				<?php echo "<td width=\"150px\"><a href=scheda.php?id=".$id.">" . $row['title'] . "</a></td>" ?>
				<td> <?php echo $row['description'] ?> </td>
				</tr> 
			<?php } ?>
	</table>			
	<?php
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
              <li itemscope itemtype="http://data-vocabolary.org/Breadcrumb" class="active"><a href="home.php" itemprop="url">Home</a></li>
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
        	<section class="span9"> <!-- section generale per la ricerca: divisa in 2 sotto section -->
        		<header>
	        		<h2>Ricerca un libro</h2>
	        	</header>
	        	<section> 
	        	
	        		<!--section per l'inserimento del termine/categoria da cercare -->
	        		<form class="form-horizontal" method="GET">
						<div class="control-group">
							<label class="control-label" for="inputCodice">Termine da ricercare</label>
							<div class="controls">
								<div class="input-append">
			    					<input type="text" id="inputRicerca" name="term" value="<?PHP print $term; ?>">
			    					<div class="btn-group">
									  <a id="ricerca" class="btn" href="#">Tipo ricerca</a>
									  <a class="btn dropdown-toggle" data-toggle="dropdown" href="#"><span class="caret"></span></a>
									  <ul class="dropdown-menu">
									    <li><a id="ricercaLocale" href="#" onclick="localSearch()">Ricerca locale</a></li>
									    <li><a id="ricercaWidget" href="#" onclick="widgetSearch()">Ricerca widget</a></li>
									  </ul>
								</div>
								</div>
							</div>
						</div>
					</form>
					<form class="form-horizontal" method="GET" >
						<div class="control-group">
							<label class="control-label" for="inputCodice">Categoria</label>
							<div class="controls">
								
								<select name="categoryName" id="categoryId" method="GET" >
									<?PHP $categoryResult = getCategories();
									echo "<option></option>";
									while ($db_field = mysql_fetch_assoc($categoryResult)) {
										echo "<option>" . $db_field['name'] . "</option>";
									}
									?>
								</select>
							
								<button type="submit" class="btn" name="search" value="Search">
									Ricerca categoria
								</button>
						</div>
						</div>
				 	</form>
				</section>
			
				<!--section separata per i risultati -->
				<section id="catResult">
	        		<?PHP 
			        if($categorySearchResult!=null){
						getCategorySearchResults($categorySearchResult);
						$categorySearchResult=null;
					}
					
					?> 
				</section>
			
				<!--section per i risultati della ricerca locale e ricerca con il widget -->
				<section id="mybox">
	        	</section>
	        	
	        	<!--script per il widget -->
	        	<script type="text/javascript" 
	        		src="http://localhost/bookstore-fe-definitivo/web/widgets/widget.js">
	        	</script>
	        	<script type="text/javascript">
	        		init_widget(0, 0, "", "Prova anche il widget di ricerca cross-domain!</br>Clicca su"+ 
	        		" Ricerca widget, ed avrai i risultati in tempo brevissimo!");
	        	</script>
	        	
				<button type="submit" class="btn" name="widgetCode" value="Widget_code" onclick="widgetText()">
					Ottieni il widget per il tuo sito!
				</button>
			
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
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
 	<script src="https://github.com/twitter/bootstrap/blob/master/docs/assets/js/application.js" ></script>
	<script src="js/bootstrap.js"> </script>
	<script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/homeScripts.js"></script>
	
   </body>
</html>