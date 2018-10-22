package controlador;

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
			case "doAutenticar":
				request.getRequestDispatcher("AutenticarAction").include(request, response);
				if ((String)request.getAttribute("url") == "listar.jsp") {
					request.getRequestDispatcher("ListarAction").include(request, response);				
				}
				url = (String)request.getAttribute("url");
				break;
			case "doRegistrar":
				request.getRequestDispatcher("RegistrarAction").include(request, response);
				if ((String)request.getAttribute("url") == "listar.jsp") {
					request.getRequestDispatcher("ListarAction").include(request, response);				
				}
				url = (String)request.getAttribute("url");
				break;
			case "doListarLibros":
				request.getRequestDispatcher("ListarAction").include(request, response);
				url = "listar.jsp";
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

