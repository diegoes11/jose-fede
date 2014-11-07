<%@ page import="java.util.List" %>
<%@ page import="tpo.ia.vos.VOArticuloCompleto" %>
<%@ page import="tpo.ia.vos.VOFichaTecnica" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IA TPO - Despacho - Listado de Artículos - Bustamante, Lerner</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/jquery.dataTables.css">
<link rel="stylesheet" href="css/jquery.dataTables_themeroller.css">
<link rel="stylesheet" href="css/toastr.css">
<link rel="stylesheet" href="css/despacho.css">
</head>
<body>
<%
	List<VOArticuloCompleto> articulos = (List<VOArticuloCompleto>)request.getAttribute("articulos");
%>
<h1>Despacho - Listado de Art&iacute;culos</h1>
<div id="tabs">
	<ul>
		<li><a href="#tabs-0">Listado de Art&iacute;culos</a></li>
		<li><a href="#tabs-1">Administraci&oacute;n de Usuarios</a></li>
		<li><a href="#tabs-2">Administraci&oacute;n de Art&iacute;culos</a></li>
		<li><a href="#tabs-3">Administraci&oacute;n de &Oacute;rdenes de despacho</a></li>
	</ul>
	<div id="tabs-0">
		<div id="articulos">
			<h1>Articulos</h1>
			<br />
			<table id="tabla" class="display cell-border compact" cellspacing="0" width="100%">
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
			<tfoot>
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
			</tfoot>
			<tbody>
		<%
			for(VOArticuloCompleto a : articulos)
			{
		%>
				<tr>
					<td><%=a.getCodigo()%></td>
					<td><%=a.getTipo()%></td>
					<td><%=a.getNombre()%></td>
					<td><%=a.getDescripcion()%></td>
					<td><%=a.getMarca()%></td>
					<td>$<%=a.getPrecio()%></td>
					<td>
						<%
							if(a.getFoto() != null)
							{
						%>
								<img class="img-zoom" src="data:image/jpg;base64,<%=a.obtenerFotoEnString()%>" alt="<%=a.getNombreFoto()%>" />
						<%
							}
							else
							{
						%>
								-
						<%
							}
						%>
					</td>
					<td><%=a.getOrigen()%></td>
					<td>
						<table>
							<tbody>
								<%
									for(VOFichaTecnica ft : a.getFichasTecnicas())
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