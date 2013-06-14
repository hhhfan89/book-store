<?PHP

$uname = "";
$pword = "";
$errorMessage = "";
$loginErr="";

if (isset($_POST['login'])){
	$uname = $_POST['username'];
	$pword = $_POST['password'];

	//==========================================
	//	CONNECT TO THE LOCAL DATABASE
	//==========================================
	include 'scripts/connect-sql.php';

	if ($db_found) {
				
		$SQL = "SELECT * FROM users WHERE username = '$uname' AND password = '$pword'";	
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
				$loginErr = "Errore nel login. Controllare username e password.";
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
        	<article itemscope itemtype="http://schema.org/BlogPosting"> <!-- altri eventuali microdati qui -->
	        	<header>
	        		<hgroup>
	        			<h2 itemprop="name">BIBLIOTECA ONLINE</h2>
	        			<h3 itemprop="description">Progetto per il Corso di Sistemi Informativi Web Aziendali</h3>
	        			<h4 itemprop="dateCreated">A.A. 2012/2013</h4>
	        		</hgroup>
	        	</header>
	        	<div itemprop="articleBody">
	        		<section>
	        			<header>
	        				<h5 itemprop="name">PARTE A: Servizio di Front-End applicazione</h5>
	        			</header>
	        			<p>Sviluppare mediante HTML5 , Java/PHP e JS/JQUERY due pagine web su un server APACHE (server denominato “FE”):</p>
	        			<ol>
	        					<li>Pagina "Home" per il login utente (due campi);</li>
	        					Dopo aver effettuato il login l’hp presenterà il motore di ricerca per i video o libri.
								I risultati devono prevedere tramite informazioni strutturate dettagli, 
								viene fornita la possibilità di scaricare il contenuto testuale (icone casuali di testo univoche).
	        					<li>Pagina con le informazioni del gruppo di sviluppo.</li>
								<li>Implementazione di un mini widget cross-browser da fornire in modalità mash-up con l’obiettivo di 
									trasferire sul server X (immaginato fuori dal dominio e diverso ovviamente dagli altri due server di sviluppo) 
									il minor numero di informazioni per poter ottenere la funzionalità di ricerca e visualizzazione dei contenuti.
								</li>
						</ol>
	        			<p>Il sistema deve colloquiare con un DB MySQL contenente le informazioni.</p>
	        		</section>
	        		<section>
	        			<header>
	        				<h5 itemprop="name">PARTE B: Servizio di Back-End applicazione</h5>
	        			</header>
	        			<p>Sviluppare un servizio di Back-End (GUI n°2) che dovrà fornire le seguenti funzionalità:</p>
	        			<ol>
	        				<li>Creazione di un database MySQL per lo storage dei dati.</li>
	        				<li>Interazione con il database ( attraverso Hibernate ) per creare, leggere, aggiornare e cancellare (CRUD-Create Read Update Delete) 
	        					ciascuna business entity dell’applicazione nel database.</li>
	        			</ol>
	        		</section>
	        	</div>
	        </article>
        </section>

        <aside class="span3">
	       <section>
	          <h4>Login</h4>
	          <form method="POST" action="index.php">
	          <div class="controls">
			    	<span class="error"><?php echo $loginErr;?></span>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="inputEmail">Username</label>
			    <div class="controls">
			      <input type="text" id="inputEmail" name="username" value="<?PHP print $uname;?>" autocomplete="on">
			    </div>
			  </div>
			  <div class="control-group">
			    <label class="control-label" for="inputPassword">Password</label>
			    <div class="controls">
			      <input type="password" id="inputPassword" name="password" value="<?PHP print $pword;?>" autocomplete="off">
			    </div>
			  </div>
			  <div class="control-group">
			    <div class="controls">
			      <button type="submit" name="login" class="btn">Login</button>
			    </div>
			  </div>
			  </form>	
			  <h5 float="right">Non hai un account?</h5>
			  <a class="btn" href="register.php">Registrati</a>
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

  </body>
</html>