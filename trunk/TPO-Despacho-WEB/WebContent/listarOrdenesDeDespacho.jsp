<%@ page import="java.util.List" %>
<%@ page import="tpo.ia.vos.OrdenDeDespachoCompletaVO" %>

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
<link rel="stylesheet" href="css/despacho.css">
</head>
<body>
<%
	List<OrdenDeDespachoCompletaVO> ordenesDeDespacho = (List<OrdenDeDespachoCompletaVO>)request.getAttribute("ordenesDeDespacho");
%>
<h1>Despacho - Listado de &Oacute;rdenes de Despacho</h1>

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
	for(OrdenDeDespachoCompletaVO odd : ordenesDeDespacho)
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

<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.dataTables.js"></script>
<script src="js/despacho.js"></script>
</body>
</html>