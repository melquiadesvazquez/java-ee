<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<th>indice</th>
			</tr>
						
			<c:set var="contactos" value="${requestScope.contactos}" />
			<c:choose>
			<c:when test="${!empty contactos}">
				<c:forEach var="con" items="${contactos}" varStatus="vs">
			<tr>
				<td>${con.nombre}</td>
				<td>${con.email}</td>
				<td>${con.edad}</td>
				<td><a href="Controller?op=doEliminar&id=${con.id}">Borrar</a></td>
				<td>${vs.index}</td>
			</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
			<tr>
				<td colspan="5">No hay contactos</td>
			</tr>
			</c:otherwise>
			</c:choose>
			
		</table>
		<a href="Controller?op=toMenu" class="btn btn-secondary btn-lg">Volver</a>
	</div>
</body>
</html>