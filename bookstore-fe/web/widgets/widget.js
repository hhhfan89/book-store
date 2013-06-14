function init_widget(params){
	document.getElementById("mybox").innerHTML=
	'<div class="container">'+arguments[3]+
	'</div><iframe src="http://localhost/bookstore-fe-definitivo/web/widgets/widget.php?term='
	+arguments[2]+'" width="'+arguments[0]+'" height="'+arguments[1]
	+'" style="border:0px solid #ccc" border="0"></iframe>';
}
