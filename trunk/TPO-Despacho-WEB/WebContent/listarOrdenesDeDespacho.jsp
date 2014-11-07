<%@ page import="java.util.List" %>
<%@ page import="tpo.ia.vos.VOOrdenDeDespachoCompleta" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IA TPO - Despacho - Listado de Órdenes de Despacho - Bustamante, Lerner</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/jquery.dataTables.css">
<link rel="stylesheet" href="css/jquery.dataTables_themeroller.css">
<link rel="stylesheet" href="css/toastr.css">
<link rel="stylesheet" href="css/despacho.css">
</head>
<body>
<%
	List<VOOrdenDeDespachoCompleta> ordenesDeDespacho = (List<VOOrdenDeDespachoCompleta>)request.getAttribute("ordenesDeDespacho");
%>
<h1>Despacho - Listado de &Oacute;rdenes de Despacho</h1>
<div id="tabs">
	<ul>
		<li><a href="#tabs-0">Listado de &Oacute;rdenes de Despacho</a></li>
		<li><a href="#tabs-1">Administraci&oacute;n de Usuarios</a></li>
		<li><a href="#tabs-2">Administraci&oacute;n de Art&iacute;culos</a></li>
		<li><a href="#tabs-3">Administraci&oacute;n de &Oacute;rdenes de despacho</a></li>
	</ul>
	<div id="tabs-0">
		<div id="ordenesDeDespacho">
			<h1>&Oacute;rdenes de Despacho</h1>
			<br />
			<table id="tabla" class="display cell-border compact" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>ID</th>
					<th>Estado</th>
					<th>Fecha de recepci&oacute;n</th>
					<th>Fecha de entrega</th>
					<th>Portal Web</th>
					<th>Log&iacute;stica y Monitoreo</th>
					<th>Detalle</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th>ID</th>
					<th>Estado</th>
					<th>Fecha de recepci&oacute;n</th>
					<th>Fecha de entrega</th>
					<th>Portal Web</th>
					<th>Log&iacute;stica y Monitoreo</th>
					<th>Detalle</th>
				</tr>
			</tfoot>
			<tbody>
		<%
			for(VOOrdenDeDespachoCompleta odd : ordenesDeDespacho)
			{
		%>
				<tr>
					<td><%= odd.getIdOrdenDeDespacho() %></td>
					<td><%= odd.getEstado() %></td>
					<td><%= odd.getFechaRecepcion() %></td>
					<td><%= ((odd.getFechaEntrega() == null) ? "-" : odd.getFechaEntrega()) %></td>
					<td><%= odd.getNombrePortalWeb() %></td>
					<td><%= odd.getNombreLogisticaYMonitoreo() %></td>
					<td><a href="DetalleOrdenDeDespacho?id=<%= odd.getIdOrdenDeDespacho() %>&portalWeb=<%= odd.getNombrePortalWeb() %>">Ver</a></td>
				</tr>
		<%
			}
		%>
			</tbody>
			</table>
		</div>
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