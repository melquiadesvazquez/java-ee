<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar cursos</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<div class="jumbotron" style="width:700px;margin:20px auto;">
		<h1>Buscar</h1>
		<form action="Controller?op=doBuscarCursos" method="POST">
		  <div class="form-group row">
		    <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="nombre" name="nombre" value="">
		    </div>
		  </div>		  
		  <button type="submit" class="btn btn-primary btn-lg">Buscar</button>
		</form>
		<br><br>
		<h1>Cursos</h1>
		<table class="table">
			<tr>
				<th>Nombre</th>
				<th>Duración</th>
				<th></th>
			</tr>
			
			<c:set var="cursos" value="${requestScope.cursos}" />
			<c:choose>
				<c:when test="${!empty cursos}">
					<c:forEach var="curso" items="${cursos}">					
						<tr>
							<td>${curso.nombre}</td>
							<td>${curso.duracion}</td>
							<td><a href="Controller?op=doMostrarCurso&idCurso=${curso.idCurso}">Modificar</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3">No hay cursos</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>

		<a href="Controller?op=toMenu" class="btn btn-secondary btn-lg">Volver</a>
	</div>
</body>
</html>