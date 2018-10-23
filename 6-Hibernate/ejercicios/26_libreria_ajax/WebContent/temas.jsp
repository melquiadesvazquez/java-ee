<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Temas</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<style>
#catalogo .btnEliminar{display:none;}
#compra .btnComprar{display:none;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

function mostrarLibros(libros) {
	const html = JSON.parse(libros).map((libro, idex) => {
		return `					
			<tr>
				<td>\${libro.titulo}</td>
				<td>\${libro.precio}</td>
				<td>\${libro.autor}</td>
				<td><a href="#" class="btnComprar" data-isbn="\${libro.isbn}">Comprar</a></td>
				<td><a href="#" class="btnEliminar" data-index="\${idex}">Eliminar</td>
			</tr>	
		`;
	}).join('');
	
	return  `<table class="table">
			<tr>
			<th>Titulo</th>
			<th>Precio</th>
			<th>Autor</th>
			<th></th>
			</tr>
			\${html}
			</table>`;
}


function pedirLibros() {
	var tema = $("#tema").val();	
	$.get("Controller", {"op": "doListarLibrosPorTema", "tema": tema}, function(data) {
		document.getElementById("catalogo").innerHTML = mostrarLibros(data);	  	
	});		
}


function pedirCarrito() {
	var tema = $("#tema").val();	
	$.get("Controller", {"op": "doListarCarrito"}, function(data) {
		document.getElementById("compra").innerHTML = mostrarLibros(data);	  	
	});		
}

function comprarLibro(isbn, tema) {	
	$.get("Controller", {"op": "doComprar", "isbn": isbn, "tema": tema}, function(data) {
		document.getElementById("compra").innerHTML = mostrarLibros(data);	  	
	});		
}

function eliminarLibro(index, tema) {	
	$.get("Controller", {"op": "doEliminar", "index": index, "tema": tema}, function(data) {
		document.getElementById("compra").innerHTML = mostrarLibros(data);	  	
	});		
}


document.addEventListener('click',function(e) {
    if(e.target && e.target.className == 'btnComprar'){
    	e.preventDefault();
    	comprarLibro(e.target.dataset.isbn, $("#tema").val());
    } 
    else if(e.target && e.target.className == 'btnEliminar'){
    	e.preventDefault();
    	eliminarLibro(e.target.dataset.index, $("#tema").val());
    }
});


</script>
</head>
<body>
	<div class="jumbotron" style="width:500px;margin:20px auto;">
		<form action="Controller?op=doListarLibrosPorTema" method="POST">
		  <div class="form-group row">
		    <label for="usuario" class="col-sm-6 col-form-label">Seleccione un tema</label>
		    <div class="col-sm-6">
		    	<select class="form-control" id="tema" name="tema" onChange="pedirLibros();">>
		    		<option value="0">todos</option>
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
		  <a href="Controller?op=toLogin" class="btn btn-secondary btn-lg">Volver</a>
		</form>		
	</div>
	
	<div class="jumbotron" style="width:500px;margin:20px auto;">
		<h1>Catálogo</h1>
		<div id="catalogo"></div>
		
		<h1>Compra</h1>
		<div id="compra"></div>
		
		<a href="Controller?op=doFinalizarCompra&tema=${param.tema}" class="btn btn-primary btn-lg">Finalizar compra</a>
		<a href="Controller?op=doListarTemas" class="btn btn-secondary btn-lg">Volver</a>
	</div>
	
	<script>
	pedirLibros();
	pedirCarrito();
	</script>
</body>
</html>