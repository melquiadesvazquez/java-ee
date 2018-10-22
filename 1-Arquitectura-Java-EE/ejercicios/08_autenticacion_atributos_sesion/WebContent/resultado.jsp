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
// HttpSession s = request.getSession();
// String usuario = s.getAttribute("usuario"); 
String usuario = (String)session.getAttribute("usuario"); 
%>
<p><%= usuario %> estas en <%= request.getParameter("opcion") %></p>
<p><a href="menu.html">Volver</a></p>
<p><a href="Desconectar">Desconectar</a></p>
</body>
</html>