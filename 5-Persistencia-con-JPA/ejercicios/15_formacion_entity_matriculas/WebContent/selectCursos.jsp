<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Matricular alumnos</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<div class="jumbotron" style="width:90%;margin:20px auto;">
		<h1>Cursos</h1>
		<div class="jumbotron" style="width:700px;margin:20px auto;">
		<form action="Controller?op=doCrearMatricula" method="POST">	
		  <input type="hidden" name="usuario" value="${param.usuario}">	  
		  <div class="form-group row">
		    <label for="nombre" class="col-sm-2 col-form-label">Curso</label>
		    <div class="col-sm-10">
		      <select class="form-control" id="idCurso" name="idCurso">
		      	<c:set var="cursos" value="${requestScope.cursos}" />
				<c:choose>
					<c:when test="${!empty cursos}">
						<c:forEach var="curso" items="${cursos}">
							<option value="${curso.idCurso}">${curso.nombre}</option>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<option value="">No hay cursos</option>
					</c:otherwise>
				</c:choose>		      	
		      </select>
		    </div>
		  </div>		  
		  <button type="submit" class="btn btn-primary btn-lg">Enviar</button>
		  <a href="Controller?op=doMostrarAlumnosSelect" class="btn btn-secondary btn-lg">Volver</a>
		</form>
	</div>
		
	</div>
</body>
</html>