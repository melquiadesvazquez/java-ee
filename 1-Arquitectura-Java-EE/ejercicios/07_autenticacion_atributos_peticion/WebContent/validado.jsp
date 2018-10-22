<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="beans.Persona" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Persona pr = (Persona)request.getAttribute("per");
%>

<p>Bienvenido <%= request.getParameter("user") %></p>
<p>Nombre <%= pr.getNombre() %></p>
<p>Email <%= pr.getEmail() %></p>
<p>Edad <%= pr.getEdad() %></p>

<p><a href="login.html">Volver</a></p>
</body>
</html>