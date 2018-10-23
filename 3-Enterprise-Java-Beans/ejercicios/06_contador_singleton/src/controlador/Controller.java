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
		String url = "menu.html";
		
		switch (op) {
			case "doContar":
				request.getRequestDispatcher("ContarAction").include(request, response);
				break;
			case "doVerResultado":
				request.getRequestDispatcher("VerResultadoAction").include(request, response);
				url = "resultado.jsp";
				break;
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
}

