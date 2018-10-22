<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
//recogida del parametro
String valor = request.getParameter("alias");
String[] horas = request.getParameterValues("cursos");
int totalHoras = 0;

if (horas != null) {
	for(String h:horas) {
		totalHoras += Integer.parseInt(h);
	}
}

//generar respuesta
%>

<h1>Bienvenido<%= valor %></h1>
<p>Horas completadas <%= totalHoras %></p>	
<p><a href='inicio.html'>Volver</a>

</body>
</html>