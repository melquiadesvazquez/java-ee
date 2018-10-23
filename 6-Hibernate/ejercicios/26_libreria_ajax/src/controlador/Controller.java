package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Libro;
import util.Utilidades;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String url = "listar.jsp";
		String cadenaJSON = "";
		switch (op) {
			case "doAutenticar":
				request.getRequestDispatcher("AutenticarAction").include(request, response);
				if ((String)request.getAttribute("url") == "temas.jsp") {
					request.getRequestDispatcher("ListarTemasAction").include(request, response);				
				}
				url = (String)request.getAttribute("url");
				break;
			case "doRegistrar":
				request.getRequestDispatcher("RegistrarAction").include(request, response);
				if ((String)request.getAttribute("url") == "temas.jsp") {
					request.getRequestDispatcher("ListarTemasAction").include(request, response);				
				}
				url = (String)request.getAttribute("url");
				break;
			case "doListarTemas":
				request.getRequestDispatcher("ListarTemasAction").include(request, response);				
			case "doListarLibros":
				request.getRequestDispatcher("ListarAction").include(request, response);
				url = "temas.jsp";
				break;
			case "doListarLibrosPorTema":
				request.getRequestDispatcher("ListarPorTemaAction").include(request, response);
				List<Libro> libros = (List<Libro>) request.getAttribute("libros");
				cadenaJSON = Utilidades.transformarLibrosJson(libros);
				response.setContentType("text/plain");
				response.getWriter().println(cadenaJSON);
				return;	
			case "doComprar":
				request.getRequestDispatcher("ComprarAction").include(request, response);
			case "doListarCarrito":
				request.getRequestDispatcher("ListarCarritoAction").include(request, response);
				cadenaJSON = Utilidades.transformarLibrosJson((List<Libro>) request.getAttribute("cesta"));
				response.setContentType("text/plain");
				response.getWriter().println(cadenaJSON);
				return;
			case "doEliminar":
				request.getRequestDispatcher("EliminarAction").include(request, response);
				request.getRequestDispatcher("ListarCarritoAction").include(request, response);
				cadenaJSON = Utilidades.transformarLibrosJson((List<Libro>) request.getAttribute("cesta"));
				response.setContentType("text/plain");
				response.getWriter().println(cadenaJSON);
				return;
			case "doFinalizarCompra":
				request.getRequestDispatcher("FinalizarCompraAction").include(request, response);
				url = "compra.jsp";
				break;
			case "toLogin":
				url = "login.html";
				break;
			case "toRegistro":
				url = "registro.html";
				break;
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}

