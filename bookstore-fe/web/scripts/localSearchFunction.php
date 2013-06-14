<table class="table">
			
<?php
//termine della ricerca
$term = $_GET['term'];
include '../scripts/connect-sql.php';

if ($db_found) {
	
	//query per la ricerca del termine nel database
	$SQL = "SELECT *, MATCH(title, description) AGAINST('*$term*' IN BOOLEAN MODE) AS attinenza
			FROM book_search
			WHERE MATCH(title, description) AGAINST('*$term*' IN BOOLEAN MODE) ORDER BY attinenza DESC";
	
	//risultato della query
	$generalSearchResult = mysql_query($SQL);
	
	//se la query ha trovato almeno un risultato, li mostrerà in una tabella
	if(mysql_num_rows($generalSearchResult)>0) {?>
			
		<thead> 
			<tr>  
		    	<th>Title</th>  
		        <th>Description</th>  
		    </tr>  
	    </thead>  
	    <tbody> <?php
        	while ( $row = mysql_fetch_array($generalSearchResult)) {
				$id = $row['book_id'] ?>
				<tr>
				<?php echo "<td width=\"150px\"><a href=scheda.php?id=".$id.">" . $row['title'] . "</a></td>" ?>
				<td> <?php echo $row['description'] ?> </td>
				</tr> 
			<?php	} 	
	}
	//altrimenti avviserà l'utente che nessun risultato è stato trovato
	else{
		echo "Nessun risultato trovato!";
	}
				
} else {
	$errorMessage = "Database Not Found";
}
?>

</table>
