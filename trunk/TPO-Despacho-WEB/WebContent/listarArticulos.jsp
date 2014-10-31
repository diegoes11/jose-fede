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
	List<ArticuloVO> electrodomesticos = (List<ArticuloVO>)request.getAttribute("electrodomesticos");
	List<ArticuloVO> moda = (List<ArticuloVO>)request.getAttribute("moda");
	List<ArticuloVO> muebles = (List<ArticuloVO>)request.getAttribute("muebles");
	List<ArticuloVO> ninos = (List<ArticuloVO>)request.getAttribute("ninos");
%>
<h1>Despacho - Listado de Art&iacute;culos</h1>

<!-- Agregar filtro por: Deposito o Codigo -->
<h1>Agregar filtro por: Deposito o Codigo</h1>

<div id="electrodomesticos">
	<h1>Electrodomesticos</h1>
	<table border="1">
	<thead>
		<tr>
			<th>C&oacute;odigo</th>
			<th>Nombre</th>
			<th>Descripci&oacute;n</th>
			<th>Marca</th>
			<th>Precio</th>
			<th>Foto</th>
			<th>Origen</th>
			<th>Ficha T&eacute;cnica</th>
		</tr>
	</thead>
	<tbody>
		<tr>
<%
	for(ArticuloVO e : electrodomesticos)
	{
%>
			<td><%= e.getCodigo() %></td>
			<td><%= e.getNombre() %></td>
			<td><%= e.getDescripcion() %></td>
			<td><%= e.getMarca() %></td>
			<td>$<%= e.getPrecio() %></td>
			<td><img src="<%= e.getFoto() %>" alt="<%= e.getNombreFoto() %>" /></td>
			<td><%= e.getOrigen() %></td>
			<td>
				<table>
					<tbody>
						<%
							for(FichaTecnicaVO ft : e.getFichasTecnicas())
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
		</tr>
<%
	}
%>
	</tbody>
	</table>
</div>

<div id="moda">
	<h1>Moda</h1>
	<table border="1">
	<thead>
		<tr>
			<th>C&oacute;odigo</th>
			<th>Nombre</th>
			<th>Descripci&oacute;n</th>
			<th>Marca</th>
			<th>Precio</th>
			<th>Foto</th>
			<th>Origen</th>
			<th>Color</th>
			<th>Talle</th>
		</tr>
	</thead>
	<tbody>
<%
	for(ArticuloVO m : moda)
	{
%>
		<tr>
			<td><%= m.getCodigo() %></td>
			<td><%= m.getNombre() %></td>
			<td><%= m.getDescripcion() %></td>
			<td><%= m.getMarca() %></td>
			<td>$<%= m.getPrecio() %></td>
			<td><img src="<%= m.getFoto() %>" alt="<%= m.getNombreFoto() %>" /></td>
			<td><%= m.getOrigen() %></td>
			<td><%= m.getDetalleFichaTecnicaPorAtributo("Color") %></td>
			<td><%= m.getDetalleFichaTecnicaPorAtributo("Talle") %></td>
		</tr>
<%
	}
%>
	</tbody>
	</table>
</div>

<div id="muebles">
	<h1>Muebles</h1>
	<table border="1">
	<thead>
		<tr>
			<th>C&oacute;odigo</th>
			<th>Nombre</th>
			<th>Descripci&oacute;n</th>
			<th>Marca</th>
			<th>Precio</th>
			<th>Foto</th>
			<th>Origen</th>
			<th>Material</th>
		</tr>
	</thead>
	<tbody>
<%
	for(ArticuloVO mu : muebles)
	{
%>
		<tr>
			<td><%= mu.getCodigo() %></td>
			<td><%= mu.getNombre() %></td>
			<td><%= mu.getDescripcion() %></td>
			<td><%= mu.getMarca() %></td>
			<td>$<%= mu.getPrecio() %></td>
			<td><img src="<%= mu.getFoto() %>" alt="<%= mu.getNombreFoto() %>" /></td>
			<td><%= mu.getOrigen() %></td>
			<td><%= mu.getFichasTecnicas().get(0).getDetalle() %></td>
		</tr>
<%
	}
%>
	</tbody>
	</table>
</div>

<div id="niños">
	<h1>Niños</h1>
	<table border="1">
	<thead>
		<tr>
			<th>C&oacute;odigo</th>
			<th>Nombre</th>
			<th>Descripci&oacute;n</th>
			<th>Marca</th>
			<th>Precio</th>
			<th>Foto</th>
			<th>Origen</th>
			<th>Edad recomendada</th>
		</tr>
	</thead>
	<tbody>
<%
	for(ArticuloVO n : ninos)
	{
%>
		<tr>
			<td><%= n.getCodigo() %></td>
			<td><%= n.getNombre() %></td>
			<td><%= n.getDescripcion() %></td>
			<td><%= n.getMarca() %></td>
			<td>$<%= n.getPrecio() %></td>
			<td><img src="<%= n.getFoto() %>" alt="<%= n.getNombreFoto() %>" /></td>
			<td><%= n.getOrigen() %></td>
			<td><%= n.getFichasTecnicas().get(0).getDetalle() %></td>
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