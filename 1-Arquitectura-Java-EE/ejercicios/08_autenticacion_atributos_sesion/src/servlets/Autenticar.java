package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Autenticar")
public class Autenticar extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("user");
		String password = request.getParameter("pwd");
		
		RequestDispatcher rd;
		
		if (usuario.equals("curso") && password.equals("curso")) {
			HttpSession s = request.getSession();
			s.setAttribute("usuario", usuario);
			
			rd = request.getRequestDispatcher("menu.html");
		}
		else {
			rd = request.getRequestDispatcher("error.html");
		}
		
		rd.forward(request, response);
	}

}
