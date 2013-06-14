//funzione per la ricerca nel database 
function localSearch() {
	
	//cancella gli eventuali risultati della ricerca per categoria
	$("#catResult").empty();
	
	//invia il termine da ricercare alla localSearchFunction.php
	var request = $.get('scripts/localSearchFunction.php', 'term=' + $('input[id=inputRicerca]').val(), function(response) {
	});
	
	//se la richiesta è andata a buon fine, viene visualizzato il risultato
	request.done(function(msg) {
		$("#mybox").html(msg);
	});

	//se la richiesta non è andata a buon fine, viene visualizzato un alert di errore
	request.fail(function(jqXHR, textStatus) {
		alert("Request failed: " + textStatus);
	});
}

//funzione per la visualizzazione del codice del widget
function widgetText() {
	window.open("widgetPage.html");
}

//funzione per la ricerca con il widget
function widgetSearch() {
	
	//cancella gli eventuali risultati della ricerca per categoria
	$("#catResult").empty();
	
	//termire da ricercare
	var term1 = $('#inputRicerca').val();
	
	//richiede la visualizzazione del risultato della ricerca nel widget
	var request = init_widget(750, 500, term1, "<h4>Risultati widget</h4>");
}