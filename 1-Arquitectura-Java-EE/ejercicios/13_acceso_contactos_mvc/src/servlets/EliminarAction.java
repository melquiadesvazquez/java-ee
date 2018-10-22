package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DaoContactos;

@WebServlet("/EliminarAction")
public class EliminarAction extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		
		DaoContactos dcontactos = new DaoContactos();
		
		if (request.getParameter("id") != null) {
			dcontactos.eliminarContacto(Integer.parseInt(request.getParameter("id")));
		}
		else {
			dcontactos.eliminarContacto(nombre);
		}
		
		// Esto lo pasamos al front controller
		//request.getRequestDispatcher("listar.jsp").forward(request, response);
	}


}
