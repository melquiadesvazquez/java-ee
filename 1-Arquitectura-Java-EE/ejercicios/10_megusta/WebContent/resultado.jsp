<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<% if (application.getAttribute("likes") != null) { %>
			Total de megusta: <%= application.getAttribute("likes") %>
		<% } else { %>
			A nadie le gusta aún
		<% } %>
		</h1>
		<a href="menu.html" class="btn btn-secondary btn-lg">Volver</a>
	</div>
</body>
</html>