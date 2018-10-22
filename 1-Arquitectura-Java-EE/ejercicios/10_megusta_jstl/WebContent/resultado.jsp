<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Resultado</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>	
	<div class="jumbotron" style="width:500px;margin:20px auto;">
		<h1>
		<c:choose>
			<c:when test="${!empty ApplicationScope.likes}">
			Total de megusta: ${ApplicationScope.likes}
			</c:when>
			<c:otherwise>
			A nadie le gusta aún
			</c:otherwise>
		</c:choose>
		</h1>
		<a href="menu.html" class="btn btn-secondary btn-lg">Volver</a>
	</div>
</body>
</html>