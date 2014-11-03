<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IA TPO - Despacho - Alta Usuario - Bustamante, Lerner</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/jquery.dataTables.css">
<link rel="stylesheet" href="css/jquery.dataTables_themeroller.css">
<link rel="stylesheet" href="css/despacho.css">
</head>
<body>
<h1>Despacho - Men&uacute; principal</h1>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1">Administraci&oacute;n de Usuarios</a></li>
		<li><a href="#tabs-2">Administraci&oacute;n de Art&iacute;culos</a></li>
		<li><a href="#tabs-3">Administraci&oacute;n de &Oacute;rdenes de despacho</a></li>
	</ul>
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
<script src="js/despacho.js"></script>
</body>
</html>