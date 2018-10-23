package controlador;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Libro;
import modelo.VentasEjbLocal;

@WebServlet("/TestAction")
public class TestAction extends HttpServlet {
	
	@EJB
	VentasEjbLocal dVentas;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Libro> libros = dVentas.getLibrosByCliente(33);
		request.setAttribute("libros", libros);
	}

}