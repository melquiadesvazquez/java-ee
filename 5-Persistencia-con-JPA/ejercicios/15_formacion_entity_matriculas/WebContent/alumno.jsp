<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alumno</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	
	<c:set var="usuario" value="" />
	<c:set var="nombre" value="" />
	<c:set var="email" value="" />
	<c:set var="password" value="" />
	<c:set var="edad" value="" />
	
	<c:if test="${not empty requestScope.alumno}" >       
		<c:set var="alumno" value="${requestScope.alumno}" />
		<c:set var="usuario" value="${alumno.usuario}" />
		<c:set var="nombre" value="${alumno.nombre}" />
		<c:set var="email" value="${alumno.email}" />
		<c:set var="password" value="${alumno.password}" />
		<c:set var="edad" value="${alumno.edad}" />
    </c:if>
   
	<div class="jumbotron" style="width:700px;margin:20px auto;">
		<form action="Controller?op=doActualizarAlumno" method="POST">
		  <div class="form-group row">
		    <label for="usuario" class="col-sm-2 col-form-label">Usuario</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="usuario" name="usuario" value="${usuario}">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="nombre" name="nombre" value="${nombre}">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="email" class="col-sm-2 col-form-label">Email</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="email" name="email" value="${email}">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="password" class="col-sm-2 col-form-label">Password</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="password" name="password" value="${password}">
		    </div>
		  </div>
		  <div class="form-group row">
		    <label for="name" class="col-sm-2 col-form-label">Edad</label>
		    <div class="col-sm-10">
		      <input type="number" class="form-control" id="name" name="edad" value="${edad}">
		    </div>
		  </div>
		  <button type="submit" class="btn btn-primary btn-lg">Enviar</button>
		  <a href="Controller?op=doMostrarAlumnos" class="btn btn-secondary btn-lg">Alumnos</a>
		  <a href="Controller?op=toMenu" class="btn btn-secondary btn-lg">Menu</a>
		</form>
	</div>
</body>
</html>
