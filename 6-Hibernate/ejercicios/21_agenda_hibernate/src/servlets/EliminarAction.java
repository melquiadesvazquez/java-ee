package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ContactosEjbLocal;

@WebServlet("/EliminarAction")
public class EliminarAction extends HttpServlet {
	@EJB
	ContactosEjbLocal dcontactos;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
				
		if (request.getParameter("id") != null) {
			dcontactos.eliminarContacto(Integer.parseInt(request.getParameter("id")));
		}
		else {
			dcontactos.eliminarContacto(nombre);
		}
	}


}
