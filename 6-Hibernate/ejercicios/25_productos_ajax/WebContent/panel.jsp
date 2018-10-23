<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	function mostrarTabla(data) {
		const html = JSON.parse(data).map(p => {
			return `
				<tr>
					<td>\${p.precio}</td><td>\${p.producto}</td><td><a href="#" class="btnEliminar" data-id="\${p.idProducto}">Eliminar</a></td>
				</tr>
			`;
		}).join('');
	  	
		$("#productos").html(`<table class="table">\${html}</table>`);		
	}

	function lanzarPeticion() {
		var idSeccion = $("#area").val();
		
		$.get("Controller", {"op": "doProductos", "idSeccion": idSeccion}, function(data) {
			mostrarTabla(data);	  	
		});		

	}
	
	function eliminar(idProducto) {
		var idSeccion = $("#area").val();
		
		$.get("Controller", {"op": "doEliminar", "idSeccion": idSeccion, "idProducto": idProducto}, function(data) {
			mostrarTabla(data);
		});
	}
	
	document.addEventListener('click',function(e){
	    if(e.target && e.target.className == 'btnEliminar'){
	    	eliminar(e.target.dataset.id);
	    }
	 })
</script>
</head>
<body>
	<center>
		Seleccione Área:
		<select id="area" onChange="lanzarPeticion();">
			<c:forEach var="a" items="${requestScope.secciones}">
				<option value="${a.idSeccion}">${a.seccion}</option>
			</c:forEach>
		</select>
		<br>
		<div id="productos">
		</div>
	</center>
</body>
</html>