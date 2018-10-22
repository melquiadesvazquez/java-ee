<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Temas</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<div class="jumbotron" style="width:500px;margin:20px auto;">
		<form action="Controller?op=doListarLibrosPorTema" method="POST">
		  <div class="form-group row">
		    <label for="usuario" class="col-sm-6 col-form-label">Seleccione un tema</label>
		    <div class="col-sm-6">
		    	<select class="form-control" id="tema" name="tema">
		    		<option value="">todos</option>
		    		<c:set var="temas" value="${requestScope.temas}" />
					<c:choose>
						<c:when test="${!empty temas}">
							<c:forEach var="tema" items="${temas}">		
								<option value="${tema.idTema}">${tema.tema}</option>
							</c:forEach>
							</c:when>
							<c:otherwise>
								<option>No hay temas</option>
						</c:otherwise>
					</c:choose>
			   </select>
		    </div>
		  </div>		  
		  <button type="submit" class="btn btn-primary btn-lg">Mostrar libros</button>
		  <a href="Controller?op=toLogin" class="btn btn-secondary btn-lg">Volver</a>
		</form>		
	</div>
</body>
</html>