package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String url = "listado.jsp";
		
		switch (op) {
			case "doGuardar":
				request.getRequestDispatcher("GuardarAction").include(request, response);
				request.getRequestDispatcher("ListarAction").include(request, response);
				url = "listado.jsp";
				break;
			case "doEliminar":
				request.getRequestDispatcher("EliminarAction").include(request, response);
				request.getRequestDispatcher("ListarAction").include(request, response);
				url = "listado.jsp";
				break;
			case "doListar":
				request.getRequestDispatcher("ListarAction").include(request, response);
				url = "listado.jsp";
				break;
			case "toRegistro":
				url = "registro.html";
				break;
			case "toEliminar":
				url = "eliminar.html";
				break;
			case "toMenu":
				url = "menu.html";
				break;
			default:
				url = "listado.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
