<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="modelo.DaoContactos,beans.Contacto, java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contactos</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<div class="jumbotron" style="width:500px;margin:20px auto;">
		<table class="table">
			<tr>
				<th>Nombre</th>
				<th>Email</th>
				<th>Edad</th>
				<th></th>
			</tr>
			<%
			DaoContactos dcontactos = new DaoContactos();
			List<Contacto> contactos = dcontactos.listarContactos();
			
			if (contactos != null && contactos.size() > 0) {
				for(Contacto c:contactos) {
			%>
			<tr>
				<td><%= c.getNombre() %></td>
				<td><%= c.getEmail() %></td>
				<td><%= c.getEdad() %></td>
				<td><a href="Eliminar?id=<%= c.getId() %>">Borrar</a></td>
			</tr>
			<%
				}
			}
			%>
		</table>
		<a href="menu.html" class="btn btn-secondary btn-lg">Volver</a>
	</div>
</body>
</html>