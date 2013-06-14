<?PHP
//check if user is logged in, if not, redirect to index page where there is login form
session_start();

if (!(isset($_SESSION['login']) && $_SESSION['login'] != '')) {

header ("Location: index.php");

}


$uname = "";
$pword = "";
$errorMessage = "";
$loginErr = "";

if (isset($_POST['login'])){
	$uname = $_POST['username'];
	$pword = $_POST['password'];

	//==========================================
	//	CONNECT TO THE LOCAL DATABASE
	//==========================================
	include 'scripts/connect-sql.php';

	if ($db_found) {

		$uname = quote_smart($uname, $db_handle);
		$pword = quote_smart($pword, $db_handle);
		
		$SQL = "SELECT * FROM users WHERE username = $uname AND password = $pword";	
		$result = mysql_query($SQL);
		$num_rows = mysql_num_rows($result);
	//====================================================
	//	CHECK TO SEE IF THE $result VARIABLE IS TRUE
	//====================================================

		if ($result) {
			if ($num_rows > 0) {
				session_start();
				$_SESSION['login'] = "1";
				header ("Location: home.php");
			}
			else {
				session_start();
				$_SESSION['login'] = "";
				header ("Location: register.php");
			}	
		}
		else {
			$errorMessage = "Error logging on";
		}

	mysql_close($db_handle);

	}

	else {
		$errorMessage = "Error logging on";
	}

}


?>

<!DOCTYPE html>
<html lang="en">
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
              <li itemscope itemtype="http://data-vocabolary.org/Breadcrumb" class="active"><a href="#about" itemprop="url">Chi siamo</a></li>
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
        	
	        	<div id="hcard-Stefano-Di-Vito" class="vcard">
					<img style="float:left; margin-right:4px" src="img/divito.jpg" alt="photo of Stefano" class="photo" width="100" height="100"/>
					<a class="url fn n" href="http://www.sslazio.it"> <span class="given-name">Stefano</span> <span class="additional-name"></span> <span class="family-name">Di Vito</span> </a>
					<div class="org">
						SDV Software
					</div>
					<a class="email" href="mailto:divitostefano@yahoo.it">divitostefano@yahoo.it</a>
					<div class="adr">
						<div class="street-address">
							Via vattelapesca, 14
						</div>
						<span class="locality">Roma</span>
						,
						<span class="region">RM</span>
						,
						<span class="postal-code">00172</span>
						<span class="country-name">Italy</span>
					</div>
			 	</div>
				<hr />
				<div id="hcard-Silvia-Naro" class="vcard">
					<img style="float:left; margin-right:4px" src="img/naro.jpg" alt="photo of Silvia" class="photo" width="100" height="100"/>
					<a class="url fn n" href="http://www.sslazio.it"> <span class="given-name">Silvia</span> <span class="additional-name"></span> <span class="family-name">Naro</span> </a>
					<div class="org">
						SDV Software
					</div>
					<a class="email" href="mailto:naro.silvia@libero.it">naro.silvia@libero.it</a>
					<div class="adr">
						<div class="street-address">
							Via vattelapesca, 14
						</div>
						<span class="locality">Roma</span>
						,
						<span class="region">RM</span>
						,
						<span class="postal-code">00172</span>
						<span class="country-name">Italy</span>
					</div>
				</div>
        		<hr />
        		<div id="hcard-Daniele-Pasquini" class="vcard">
					<img style="float:left; margin-right:4px" src="img/pasquini.jpg" alt="photo of Daniele" class="photo" width="100" height="100"/>
					<a class="url fn n" href="http://www.sslazio.it"> <span class="given-name">Daniele</span> <span class="additional-name"></span> <span class="family-name">Pasquini</span> </a>
					<div class="org">
						SDV Software
					</div>
					<a class="email" href="mailto:psqdnl@hotmail.it">psqdnl@hotmail.it</a>
					<div class="adr">
						<div class="street-address">
							Via vattelapesca, 14
						</div>
						<span class="locality">Roma</span>
						,
						<span class="region">RM</span>
						,
						<span class="postal-code">00172</span>
						<span class="country-name">Italy</span>
					</div>
				</div>
				<hr />
        		<div id="hcard-Mary-Angeni-Uminga" class="vcard">
					<img style="float:left; margin-right:4px" src="img/uminga.jpg" alt="photo of Angeni" class="photo" width="100" height="100"/>
					<a class="url fn n" href="http://www.sslazio.it"> <span class="given-name">Mary Angeni</span> <span class="additional-name"></span> <span class="family-name">Uminga</span> </a>
					<div class="org">
						SDV Software
					</div>
					<a class="email" href="mailto:angenie13@hotmail.com">angenie13@hotmail.com</a>
					<div class="adr">
						<div class="street-address">
							Via vattelapesca, 14
						</div>
						<span class="locality">Roma</span>
						,
						<span class="region">RM</span>
						,
						<span class="postal-code">00172</span>
						<span class="country-name">Italy</span>
					</div>
				</div>
        	
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
	<script src="js/bootstrap.js"> </script>

  </body>
</html>