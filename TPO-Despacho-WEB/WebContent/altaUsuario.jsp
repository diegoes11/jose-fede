<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IA TPO - Despacho - Alta de Usuarios - Bustamante, Lerner</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/jquery.dataTables.css">
<link rel="stylesheet" href="css/jquery.dataTables_themeroller.css">
<link rel="stylesheet" href="css/toastr.css">
<link rel="stylesheet" href="css/despacho.css">
</head>
<body>
<h1>Despacho - Alta de Usuarios</h1>
<div id="tabs">
	<ul>
		<li><a href="#tabs-0">Alta de Usuarios</a></li>
		<li><a href="#tabs-1">Administraci&oacute;n de Usuarios</a></li>
		<li><a href="#tabs-2">Administraci&oacute;n de Art&iacute;culos</a></li>
		<li><a href="#tabs-3">Administraci&oacute;n de &Oacute;rdenes de despacho</a></li>
	</ul>
	<div id="tabs-0">
		<% String error = (String)request.getAttribute("error");
		if(error != null) { %>
		<div class="ui-widget">
			<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
				<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
				<strong>Error:</strong> <%= error %></p>
			</div>
		</div>
		<% } %>
		<form action="AltaUsuario" method="POST">
		<p>
		<label for="apellido">DNI</label>
		<input type="number" name="dni" id="dni" min="1" required />
		</p>
		<p>
		<label for="nombre">Nombre</label>
		<input type="text" name="nombre" id="nombre" required />
		</p>
		<p>
		<label for="apellido">Apellido</label>
		<input type="text" name="apellido" id="apellido" required />
		</p>
		<p>
		<input class="ui-button-text" type="submit" name="enviar" id="enviar" value="Enviar" />
		</p>
		</form>
	</div>
	<div id="tabs-1">
		<a href="AltaUsuario">Alta de Usuarios</a><br />
		<a href="ListarUsuarios">Listado de Usuarios</a>
	</div>
	<div id="tabs-2">
		<a href="ListarArticulos">Listado de Art&iacute;culos</a>
	</div>
	<div id="tabs-3">
		<a href="ListarOrdenesDeDespacho">Listado de &Oacute;rdenes de Despacho</a>
	</div>
</div>

<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.dataTables.js"></script>
<script src="js/toastr.js"></script>
<script src="js/despacho.js"></script>
</body>
</html>