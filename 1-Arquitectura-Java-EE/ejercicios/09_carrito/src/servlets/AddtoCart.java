package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Order;

@WebServlet("/AddtoCart")
public class AddtoCart extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		Double precio = Double.parseDouble(request.getParameter("precio"));
		int unidades = Integer.parseInt(request.getParameter("unidades"));
		Order ord = new Order(nombre, precio, unidades);
		
		HttpSession session = request.getSession();
		ArrayList<Order> cesta = (ArrayList<Order>)session.getAttribute("cesta");
		/* Sin HttpSessionListener necesitamos preguntar si existe el atributo
		if (cesta == null) cesta = new ArrayList<Order>();
		*/
		cesta.add(ord);
				
		session.setAttribute("cesta", cesta);
		
		response.sendRedirect("producto.html");
		//RequestDispatcher rd = request.getRequestDispatcher("producto.html");		
		//rd.forward(request, response);
	}

}
