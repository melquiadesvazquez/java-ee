package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.Libro;
import modelo.LibrosEjbLocal;

@WebServlet("/ComprarAction")
public class ComprarAction extends HttpServlet {
	
	@EJB
	LibrosEjbLocal dLibros;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int isbn = Integer.parseInt(request.getParameter("isbn"));
		Libro libro = dLibros.getLibro(isbn);
				
		HttpSession session = request.getSession();
		ArrayList<Libro> cesta = (ArrayList<Libro>)session.getAttribute("cesta");
			
		cesta.add(libro);		
		session.setAttribute("cesta", cesta);
	}
}
