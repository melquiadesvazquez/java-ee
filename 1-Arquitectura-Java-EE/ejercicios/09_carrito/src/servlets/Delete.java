package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Order;

@WebServlet("/Delete")
public class Delete extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Order> cesta = (ArrayList<Order>)session.getAttribute("cesta");
		if (cesta == null) cesta = new ArrayList<Order>();
		cesta.remove(Integer.parseInt(request.getParameter("o")));
		
		response.sendRedirect("cesta.jsp");
		//RequestDispatcher rd = request.getRequestDispatcher("cesta.jsp");
		//rd.forward(request, response);
	}

}
