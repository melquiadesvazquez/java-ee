package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Autenticar")
public class Autenticar extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("user");
		String password = request.getParameter("pwd");
			
		if (usuario.equals("curso") && password.equals("curso")) {
			response.sendRedirect("Entrada?user=" + usuario);
		}
		else {
			response.sendRedirect("/error.html");
		}
	}

}
