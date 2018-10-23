package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.Libro;

@WebServlet("/EliminarAction")
public class EliminarAction extends HttpServlet {
		
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index = Integer.parseInt(request.getParameter("index"));
		
		HttpSession session = request.getSession();
		ArrayList<Integer> cesta = (ArrayList<Integer>)session.getAttribute("cesta");
		cesta.remove(index);		
		session.setAttribute("cesta", cesta);
	}
}
