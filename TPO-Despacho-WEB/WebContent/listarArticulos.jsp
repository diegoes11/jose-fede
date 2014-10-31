<%@ page import="java.util.List" %>
<%@ page import="tpo.despacho.vos.ArticuloVO" %>
<%@ page import="tpo.despacho.vos.FichaTecnicaVO" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IA TPO - Despacho - Listado de Artículos - Bustamante, Lerner</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/despacho.css">
</head>
<body>
<%
	List<ArticuloVO> articulos = (List<ArticuloVO>)request.getAttribute("articulos");
%>
<h1>Despacho - Listado de Art&iacute;los</h1>

<div id="articulos">
	<h1>Articulos</h1>
	<input id="filtro" type="text">
	<br />
	<table id="tablaArticulos" border="1">
	<thead>
		<tr>
			<th>C&oacute;odigo</th>
			<th>Tipo</th>
			<th>Nombre</th>
			<th>Descripci&oacute;n</th>
			<th>Marca</th>
			<th>Precio</th>
			<th>Foto</th>
			<th>Origen</th>
			<th>Ficha T&eacute;cnica</th>
			<th>Dep&oacute;sito</th>
		</tr>
	</thead>
	<tbody>
		<tr>
<%
	for(ArticuloVO a : articulos)
	{
%>
			<td><%= a.getCodigo() %></td>
			<td><%= a.getTipo() %></td>
			<td><%= a.getNombre() %></td>
			<td><%= a.getDescripcion() %></td>
			<td><%= a.getMarca() %></td>
			<td>$<%= a.getPrecio() %></td>
			<td><img src="<%= a.getFoto() %>" alt="<%= a.getNombreFoto() %>" /></td>
			<td><%= a.getOrigen() %></td>
			<td>
				<table>
					<tbody>
						<%
							for(FichaTecnicaVO ft : a.getFichasTecnicas())
							{
						%>
							<tr>
								<td><i><%= ft.getAtributo() %>:</i></td>
								<td><%= ft.getDetalle() %></td>
							<tr>
						<%
							}
						%>
					</tbody>
				</table>
			</td>
			<td><%= a.getDeposito() %></td>
		</tr>
<%
	}
%>
	</tbody>
	</table>
</div>

<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/despacho.js"></script>
</body>
</html>