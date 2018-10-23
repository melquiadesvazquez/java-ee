<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscar matriculas</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<div class="jumbotron" style="width:90%;margin:20px auto;">
		<h1>Matriculas</h1>
		<table class="table">
			<tr>
				<th>Nombre</th>
				<th>Curso</th>
				<th></th>
			</tr>
			
			<c:set var="alumnos" value="${requestScope.alumnos}" />
			<c:choose>
				<c:when test="${!empty alumnos}">
					<c:forEach var="alumno" items="${alumnos}">				
						<c:set var="matriculas" value="${alumno.matriculas}" />						
						<c:if test="${!empty matriculas}">
							<c:forEach var="matricula" items="${matriculas}">
								<tr>
									<td>${matricula.alumno.nombre}</td>
									<td>${matricula.curso.nombre}</td>
									<td><a href="Controller?op=doEliminarMatricula&usuario=${matricula.alumno.usuario}&idCurso=${matricula.curso.idCurso}">Eliminar</td>
								</tr>
							</c:forEach>												
						</c:if>	
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">No hay matriculas</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>

		<a href="Controller?op=toMenu" class="btn btn-secondary btn-lg">Menu</a>
	</div>
</body>
</html>