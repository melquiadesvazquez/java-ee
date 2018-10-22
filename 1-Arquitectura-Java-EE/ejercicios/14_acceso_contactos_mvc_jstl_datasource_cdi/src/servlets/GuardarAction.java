package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DaoContactos;
import modelo.IDaoContactos;

@WebServlet("/GuardarAction")
public class GuardarAction extends HttpServlet {
	@Inject
	IDaoContactos dcontactos;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		int edad = Integer.parseInt(request.getParameter("edad"));
		
		dcontactos.guardarContacto(nombre, email, edad);
		
		// Esto lo pasamos al front controller
		//request.getRequestDispatcher("listar.jsp").forward(request, response);
	}


}
