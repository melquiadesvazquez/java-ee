package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Producto;
import util.Utilidades;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		String op = request.getParameter("op");
		
		switch(op) {
			case "doAreas":
				request.getRequestDispatcher("AreasAction").include(request, response);
				url = "panel.jsp";
				break;
			case "doEliminar":
				request.getRequestDispatcher("EliminarAction").include(request, response);
			case "doProductos":
				request.getRequestDispatcher("ProductosAction").include(request, response);
				List<Producto> productos = (List<Producto>) request.getAttribute("productos");
				String cadenaJSON = Utilidades.transformarProductosJson(productos);
				response.setContentType("text/plain");
				response.getWriter().println(cadenaJSON);
				return;
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
