<%@ page import="java.util.List" %>
<%@ page import="tpo.ia.vos.UsuarioVO" %>

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
	List<UsuarioVO> usuarios = (List<UsuarioVO>)request.getAttribute("usuarios");
%>
<h1>Despacho - Listado de Usuarios</h1>

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
	for(UsuarioVO u : usuarios)
	{
%>
	<tr>
		<td><%= u.getDni() %></td>
		<td><%= u.getNombre() %></td>
		<td><%= u.getApellido() %></td>
		<td>
			<a href="SetEstadoActivoUsuario?dni=<%=u.getDni()%>&activo=<%=!u.isActivo()%>">
				<%= u.isActivo() ? "activo" : "inactivo" %>
			</a>
		</td>
	</tr>
<%
	}
%>
</tbody>
</table>

<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.dataTables.js"></script>
<script src="js/despacho.js"></script>
</body>
</html>