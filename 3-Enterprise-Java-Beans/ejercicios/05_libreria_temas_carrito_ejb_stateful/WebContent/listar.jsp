<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Libros</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<div class="jumbotron" style="width:500px;margin:20px auto;">
		<h1>Catálogo</h1>
		<table class="table">
			<tr>
				<th>Titulo</th>
				<th>Precio</th>
				<th>Autor</th>
				<th></th>
			</tr>
			
			<c:set var="libros" value="${requestScope.libros}" />
			<c:choose>
				<c:when test="${!empty libros}">
					<c:forEach var="libro" items="${libros}">					
						<tr>
							<td>${libro.titulo}</td>
							<td>${libro.precio}</td>
							<td>${libro.author}</td>
							<td><a href="Controller?op=doComprar&tema=${param.tema}&isbn=${libro.isbn}">Comprar</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3">No hay libros</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		
		<h1>Compra</h1>
		<table class="table">
			<tr>
				<th>Titulo</th>
				<th>Precio</th>
				<th>Autor</th>
				<th></th>
			</tr>
			
			<c:set var="libros" value="${requestScope.cesta}" />
			<c:choose>
				<c:when test="${!empty libros}">
					<c:forEach var="libro" varStatus="vs" items="${libros}">					
						<tr>
							<td>${libro.titulo}</td>
							<td>${libro.precio}</td>
							<td>${libro.author}</td>
							<td><a href="Controller?op=doEliminar&index=${vs.index}">Eliminar</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3">No hay libros</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<a href="Controller?op=doFinalizarCompra" class="btn btn-primary btn-lg">Finalizar compra</a>
		<a href="Controller?op=doListarTemas" class="btn btn-secondary btn-lg">Volver</a>
	</div>
</body>
</html>