<%@ page import="java.util.List" %>
<%@ page import="tpo.ia.vos.VOUsuario" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IA TPO - Despacho - Listado de Usuarios - Bustamante, Lerner</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/jquery.dataTables.css">
<link rel="stylesheet" href="css/jquery.dataTables_themeroller.css">
<link rel="stylesheet" href="css/despacho.css">
</head>
<body>
A HACER: UPDATE DEL ESTADO POR AJAX!
<%
	List<VOUsuario> usuarios = (List<VOUsuario>)request.getAttribute("usuarios");
%>
<h1>Despacho - Listado de Usuarios</h1>
<div id="tabs">
	<ul>
		<li><a href="#tabs-0">Listado de Usuarios</a></li>
		<li><a href="#tabs-1">Administraci&oacute;n de Usuarios</a></li>
		<li><a href="#tabs-2">Administraci&oacute;n de Art&iacute;culos</a></li>
		<li><a href="#tabs-3">Administraci&oacute;n de &Oacute;rdenes de despacho</a></li>
	</ul>
	<div id="tabs-0">
		<table  id="tabla" class="display cell-border compact" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Estado</th>
			</tr>
		</thead>
		<tfoot>
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Estado</th>
			</tr>
		</tfoot>
		<tbody>
		<%
			for(VOUsuario u : usuarios)
			{
		%>
			<tr>
				<td><%= u.getDni() %></td>
				<td><%= u.getNombre() %></td>
				<td><%= u.getApellido() %></td>
				<td>
					<a href="#" class="estado" data-dni="<%= u.getDni() %>">
						<%= u.isActivo() ? "activo" : "inactivo" %>
					</a>
				</td>
			</tr>
		<%
			}
		%>
		</tbody>
		</table>
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
<script src="js/despacho.js"></script>
</body>
</html>