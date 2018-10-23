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
import modelo.dao.DaoLibrosEjbLocal;
import modelo.service.CarritoEjbLocal;

@WebServlet("/ListarCarritoAction")
public class ListarCarritoAction extends HttpServlet {
	
	@EJB
	DaoLibrosEjbLocal dLibros;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		CarritoEjbLocal carrito = (CarritoEjbLocal)request.getSession().getAttribute("carrito");
		request.setAttribute("cesta", carrito.get());
	}
}
