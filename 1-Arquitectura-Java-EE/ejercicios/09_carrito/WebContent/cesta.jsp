<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList,beans.Order" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cesta</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<div class="jumbotron" style="width:500px;margin:20px auto;">
		<table class="table">
			<tr>
				<th>Producto</th>
				<th>Precio</th>
				<th>Unidades</th>
				<th>Total</th>
				<th></th>
			</tr>
			<%
			Object auxCesta = session.getAttribute("cesta");
			ArrayList<Order> cesta = (ArrayList<Order>)session.getAttribute("cesta");
			int total = 0;
			int index = 0;
			
			/* Sin HttpSessionListener necesitamos preguntar si existe el atributo
			if (cesta != null && cesta.size() > 0) {
			*/
			if (cesta.size() > 0) {
				for(Order o:cesta) {
					Double auxTotal = o.getUnidades()*o.getPrecio();
					total += auxTotal;
			%>
			<tr>
				<td><%= o.getNombre() %></td>
				<td><%= o.getPrecio() %> $</td>
				<td><%= o.getUnidades() %></td>
				<td><%= auxTotal %> $</td>
				<td><a href="Delete?o=<%=index++ %>">Borrar</a></td>
			</tr>
			<%
			 	}
			 }
			%>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><%= total %> $</td>
				<td></td>
			</tr>
		</table>
		<a href="menu.html" class="btn btn-secondary btn-lg">Volver</a>
		<a href="EmptyCart" class="btn btn-secondary btn-lg">Vaciar cesta</a>
	</div>
</body>
</html>