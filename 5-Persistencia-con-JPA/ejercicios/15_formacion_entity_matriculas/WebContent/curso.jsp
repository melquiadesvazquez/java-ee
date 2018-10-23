<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curso</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	
	<c:set var="idCurso" value="" />
	<c:set var="nombre" value="" />
	<c:set var="duracion" value="" />
	<c:set var="documento" value="" />
	
	<c:if test="${not empty requestScope.curso}" >       
		<c:set var="curso" value="${requestScope.curso}" />
		<c:set var="idCurso" value="${curso.idCurso}" />
		<c:set var="nombre" value="${curso.nombre}" />
		<c:set var="duracion" value="${curso.duracion}" />
		<c:set var="documento" value="${curso.documento}" />
    </c:if>
	
	<div class="jumbotron" style="width:700px;margin:20px auto;">
		<form action="Controller?op=doActualizarCurso" method="POST">		  
		  <div class="form-group row">
		    <label for="idCurso" class="col-sm-2 col-form-label">IdCurso</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="idCurso" name="idCurso" value="${idCurso}">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="nombre" name="nombre" value="${nombre}">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="duracion" class="col-sm-2 col-form-label">Duracion</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" id="duracion" name="duracion" value="${duracion}">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="documento" class="col-sm-2 col-form-label">Documento</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="documento" name="documento" value="${documento}">
		    </div>
		  </div>
		  <button type="submit" class="btn btn-primary btn-lg">Enviar</button>
		  <a href="Controller?op=doMostrarCursos" class="btn btn-secondary btn-lg">Cursos</a>
		  <a href="Controller?op=toMenu" class="btn btn-secondary btn-lg">Menu</a>
		</form>
	</div>
</body>
</html>
