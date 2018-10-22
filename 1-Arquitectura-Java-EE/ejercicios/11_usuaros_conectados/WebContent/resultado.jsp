<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />
<title>Menu</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<div class="jumbotron" style="width:500px;margin:20px auto;">
		<p>Total visitas realizas: <%= (Integer)application.getAttribute("visitas") %></p>
		<p>Usuarios conectados: <%= (Integer)application.getAttribute("activos") %></p>
		<%
			String fecha = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for(Cookie ck:cookies) {
					if (ck.getName().equals("fecha")) {
						fecha = ck.getValue();
					}
				}
			}
		%>
		
		<p>Última visita: <%= fecha %></p>
		<p><a href="menu.html" class="btn btn-secondary btn-lg float-right">Volver</a></p>
	</div>
</body>
</html>