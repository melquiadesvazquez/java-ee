package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EmptyCart")
public class EmptyCart extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("cesta");
		//s.invalidate();
		
		response.sendRedirect("cesta.jsp");
		//RequestDispatcher rd = request.getRequestDispatcher("cesta.jsp");
		//rd.forward(request, response);
	}

}
