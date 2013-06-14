<style type="text/css">
	.widget_content{font-family:trebuchet ms;font-size:12px;}
	.divider{clear:both;height:1px;background:#ccc;width:100%;margin-top:5px;margin-bottom:5px}
</style>
<div class="widget_content">

<table>
<?php

	include '../scripts/connect-sql.php';
	//termine da ricercare nel database
	$term = $_GET['term'];
	
	//query per la ricerca del termine
	$query = "SELECT *, MATCH(title, description) AGAINST('*".$term."*' IN BOOLEAN MODE) AS attinenza 
					FROM book_search 
					WHERE MATCH(title, description) AGAINST('*".$term."*' IN BOOLEAN MODE) ORDER BY attinenza DESC";
					
	$result=mysql_query($query);
	
	if($row=mysql_num_rows($result)>0){
		//visualizzazione risultati ricerca
		while($row=mysql_fetch_array($result)){
			?>
			<tr>
			<b><h3><?php echo $row['title']; ?></h3></b><br/>
			<i><?php echo $row['description']; ?></i><br/>
			<div class="divider"></div>
			</tr>
			<?php
		}
	}
	else{
		echo "Nessun risultato trovato!";
	}
	?>
	</table>
</div>