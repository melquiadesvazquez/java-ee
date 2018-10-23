package controlador;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Producto;
import modelo.dao.DaoProductosEjbLocal;


@WebServlet("/EliminarAction")
public class EliminarAction extends HttpServlet {
	@EJB
	DaoProductosEjbLocal daoProductos;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idProducto = Integer.parseInt(request.getParameter("idProducto"));
		daoProductos.eliminarProducto(idProducto);
	}

}
