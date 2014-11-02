<%@ page import="java.util.List" %>
<%@ page import="tpo.despacho.vos.OrdenDeDespachoCompletaVO" %>
<%@ page import="tpo.despacho.vos.DetalleOrdenDeDespachoCompletaVO" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IA TPO - Despacho - Detalle de Orden de Despacho - Bustamante, Lerner</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/jquery.dataTables.css">
<link rel="stylesheet" href="css/jquery.dataTables_themeroller.css">
<link rel="stylesheet" href="css/despacho.css">
</head>
<body>
<%
	OrdenDeDespachoCompletaVO ordenDeDespacho = (OrdenDeDespachoCompletaVO)request.getAttribute("ordenDeDespacho");
	List<DetalleOrdenDeDespachoCompletaVO> detalles = ordenDeDespacho.getDetallesOrdenDeDespachoVO();
%>
<h1>Despacho - Detalle de Orden de Despacho</h1>

<div id="ordenDeDespacho">
	<h1>Orden de Despacho #<%= ordenDeDespacho.getIdOrdenDeDespacho() %></h1>
	<br />
	<div id="datosOrden">
		<u>Estado</u>: <%= ordenDeDespacho.getEstado() %><br />
		<u>Fecha de recepci&oacute;n</u>: <%= ordenDeDespacho.getFechaRecepcion() %><br />
		<u>Fecha de entrega</u>: <%= ((ordenDeDespacho.getFechaEntrega() == null) ? "-" : ordenDeDespacho.getFechaEntrega()) %><br />
		<u>Portal Web</u>: <%= ordenDeDespacho.getNombrePortalWeb() %><br />
		<u>Log&iacute;stica y Monitoreo</u>: <%= ordenDeDespacho.getNombreLogisticaYMonitoreo() %>
	</div>
	<br />
	<h2>Detalles</h2>
	<table id="tabla" class="display cell-border compact" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th>ID</th>
			<th>C&oacute;digo Art&iacute;culo</th>
			<th>Nombre Art&iacute;culo</th>
			<th>Cantidad Recibida</th>
			<th>Cantidad Solicitada</th>
			<th>Estado</th>
			<th>Fecha de pedido</th>
			<th>Fecha de completitud</th>
			<th>Dep&oacute;sito</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th>ID</th>
			<th>C&oacute;digo Art&iacute;culo</th>
			<th>Nombre Art&iacute;culo</th>
			<th>Cantidad Recibida</th>
			<th>Cantidad Solicitada</th>
			<th>Estado</th>
			<th>Fecha de pedido</th>
			<th>Fecha de completitud</th>
			<th>Dep&oacute;sito</th>
		</tr>
	</tfoot>
	<tbody>
<%
	for(DetalleOrdenDeDespachoCompletaVO dodd : detalles)
	{
%>
		<tr>
			<td><%= dodd.getIdDetalleOrdenDeDespacho() %></td>
			<td><%= dodd.getCodigoArticulo() %></td>
			<td><%= dodd.getNombreArticulo() %></td>
			<td><%= dodd.getCantidadRecibida() %></td>
			<td><%= dodd.getCantidadSolicitada() %></td>
			<td><%= dodd.getEstado() %></td>
			<td><%= dodd.getFechaPedido() %></td>
			<td><%= ((dodd.getFechaCompletitud() == null) ? "-" : dodd.getFechaCompletitud()) %></td>
			<td><%= dodd.getNombreDeposito() %></td>
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