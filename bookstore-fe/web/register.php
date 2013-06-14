<?PHP

$fname = "";
$lname = "";
$email = "";
$uname = "";
$pword1 = "";
$pword2 = "";
$emailErr=$pwordErr=$lengthErr=$unameErr=$error="";
$errorMessage = 0;
$num_rows = 0;

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

	//====================================================================
	//	GET THE CHOSEN U AND P, AND CHECK IT FOR DANGEROUS CHARCTERS
	//====================================================================
	$fname=$_POST['firstName'];
	$lname=$_POST['lastName'];
	$email=$_POST['email'];
	
	if (!(filter_var($email, FILTER_VALIDATE_EMAIL))) {
		$emailErr="Indirizzo email non valido.";
		$errorMessage=1;
	}
	
	$uname=$_POST['username'];
	$pword1=$_POST['password1'];
	$pword2=$_POST['password2'];
	$pLength1 = strlen($pword1);
	
	if ($pLength1 < 6 ) {
		$lengthErr = "La password deve avere almeno 6 caratteri.";
		$errorMessage = 1;
	}
	if ($pword1 != $pword2) {
		$pwordErr = "Le password inserite devono essere uguali.";
		$errorMessage = 1;
	}
	
	//test to see if $errorMessage is blank
	//if it is, then we can go ahead with the rest of the code
	//if it's not, we can display the error

	//====================================================================
	//	Write to the database
	//====================================================================
	if ($errorMessage == 0) {

		include 'scripts/connect-sql.php';

		if ($db_found) {

			//====================================================================
			//	CHECK THAT THE USERNAME IS NOT TAKEN
			//====================================================================

			$SQL = "SELECT * FROM users WHERE username = '$uname'";
			$result = mysql_query($SQL);
			$num_rows = mysql_num_rows($result);
			if ($num_rows > 0) {
				 $unameErr = "Lo username non è disponibile";
			} 
			else {
				
				$SQL = "INSERT INTO users (name, surname, email, username, password, role_id) VALUES ('$fname', '$lname', '$email', '$uname', '$pword1', 2)";

				$result = mysql_query($SQL);

				mysql_close($db_handle);

				//=================================================================================
				//	START THE SESSION AND PUT SOMETHING INTO THE SESSION VARIABLE CALLED login
				//	SEND USER TO A DIFFERENT PAGE AFTER SIGN UP
				//=================================================================================

				session_start();
				$_SESSION['login'] = "1";
				
				header("Location: home.php");

			}

		} else {
			$error = "Database Not Found";
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
          </header>
        </div>
      </div>
    </div>

    <div class="container">
      <header class="hero-unit" itemscope="http://schema.org/Thing">
        <h1 itemprop="name">Book-store</h1>
        <br />
        <h4 itemprop="description"><i>"Le letture non si consigliano, se non ai principianti del leggere. Ognuno deve trovare le 
        	proprie letture con l'istinto, che − nel lettore abituato − diventa quasi sempre infallibile."</i></br>
		- Massimo Bontempelli, Il Bianco e il Nero, 1987
		</h4>
      </header>

      <div class="row">
 	  	<section class="span9">
 	  		<header>
	        	<h2>Registrati</h2>
	        </header>
			<form class="form-horizontal" method="POST" action="register.php">
				<div class="controls">
						<span class="error"><?php echo $pwordErr;?></span><br>
						<span class="error"><?php echo $lengthErr;?></span>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputNome">Nome</label>
					<div class="controls">
						<input type="text" name="firstName" value="<?PHP print $fname;?>" maxlength="20" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputCognome">Cognome</label>
					<div class="controls">
						<input type="text" id="inputCognome" name="lastName" value="<?PHP print $lname;?>" maxlength="20" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputEmail">Email</label>
					<div class="controls">
						<input type="text" id="inputEmail" name="email" value="<?PHP print $email;?>" required>
						<span class="error"><?php echo $emailErr;?></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputUsername">Username</label>
					<div class="controls">
						<input type="text" id="inputUsername" name="username" value="<?PHP print $uname;?>" maxlength="20" required>
						<span class="error"><?php echo $unameErr;?></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputPassword">Password</label>
					<div class="controls">
						<input type="password" id="inputPassword" name="password1" value="<?PHP print $pword1;?>" maxlength="16" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputConfermaPassword">Conferma Password</label>
					<div class="controls">
						<input type="password" id="inputConfermaPassword" name="password2" value="<?PHP print $pword2;?>" maxlength="16" required>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn" name="register" value="Register">
							Registrati
						</button>
					</div>
				</div>
			</form>
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
	<script src="js/bootstrap.js"></script>

  </body>
</html>