/* DATA TABLES */
$(document).ready(function() {
	$(".estado").on("click", function (o){
		var dni = o.currentTarget.attributes[2].value;
		var estadoActual = o.currentTarget.childNodes[0].data.trim();
		var estadoNuevo;
		if(estadoActual === "inactivo"){
			estadoNuevo = true;
		}
		else{
			estadoNuevo = false;
		}
		//$.ajax({
		//	  url: "SetEstadoActivoUsuario",
		//	  context: document.body
		//	}).done(function() {
		//	  $( this ).addClass( "done" );
		//	});
		alert("EA");
	});
	
	$('.img-zoom').hover(function() {
        $(this).addClass('transition');
 
    }, function() {
        $(this).removeClass('transition');
    });
	
    $('#tabla').DataTable({
        language: {
        	"emptyTable":     "No hay información disponible en la tabla",
            "info":           "Mostrando los registros _START_ al _END_ de _TOTAL_",
            "infoEmpty":      "Mostrando los registros 0 al 0 de 0",
            "infoFiltered":   "(filtrado de _MAX_ registros totales)",
            "infoPostFix":    "",
            "thousands":      ".",
            "lengthMenu":     "Mostrar _MENU_ registros",
            "loadingRecords": "Cargando...",
            "processing":     "Procesando...",
            "search":         "Buscar:",
            "zeroRecords":    "No hay registros que coincidan con la búsqueda ingresada",
            "paginate": {
                "first":      "Primero",
                "last":       "Último",
                "next":       "Siguiente",
                "previous":   "Anterior"
            },
            "aria": {
                "sortAscending":  ": activar para ordenar la comunma en orden ascendiente",
                "sortDescending": ": activar para ordenar la comunma en orden descendiente"
            }
        }
    });
} );

/* JQUERY-UI */
$( "#accordion" ).accordion();

var availableTags = [
	"ActionScript",
	"AppleScript",
	"Asp",
	"BASIC",
	"C",
	"C++",
	"Clojure",
	"COBOL",
	"ColdFusion",
	"Erlang",
	"Fortran",
	"Groovy",
	"Haskell",
	"Java",
	"JavaScript",
	"Lisp",
	"Perl",
	"PHP",
	"Python",
	"Ruby",
	"Scala",
	"Scheme"
];
$( "#autocomplete" ).autocomplete({
	source: availableTags
});



$( "#button" ).button();
$( "#radioset" ).buttonset();



$( "#tabs" ).tabs();



$( "#dialog" ).dialog({
	autoOpen: false,
	width: 400,
	buttons: [
		{
			text: "Ok",
			click: function() {
				$( this ).dialog( "close" );
			}
		},
		{
			text: "Cancel",
			click: function() {
				$( this ).dialog( "close" );
			}
		}
	]
});

// Link to open the dialog
$( "#dialog-link" ).click(function( event ) {
	$( "#dialog" ).dialog( "open" );
	event.preventDefault();
});



$( "#datepicker" ).datepicker({
	inline: true
});



$( "#slider" ).slider({
	range: true,
	values: [ 17, 67 ]
});



$( "#progressbar" ).progressbar({
	value: 20
});



$( "#spinner" ).spinner();



$( "#menu" ).menu();



$( "#tooltip" ).tooltip();



$( "#selectmenu" ).selectmenu();


// Hover states on the static widgets
$( "#dialog-link, #icons li" ).hover(
	function() {
		$( this ).addClass( "ui-state-hover" );
	},
	function() {
		$( this ).removeClass( "ui-state-hover" );
	}
);