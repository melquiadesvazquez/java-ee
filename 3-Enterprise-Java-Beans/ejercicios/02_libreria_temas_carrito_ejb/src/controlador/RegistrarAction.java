package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ClientesEjbLocal;

@WebServlet("/RegistrarAction")
public class RegistrarAction extends HttpServlet {
	
	@EJB
	ClientesEjbLocal dCliente;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (dCliente.registrarCliente(request.getParameter("usuario"), request.getParameter("password"), request.getParameter("email"), Integer.parseInt(request.getParameter("telefono"))) > 0) {
			request.setAttribute("url", "temas.jsp");
		}
		else {
			request.setAttribute("url", "registro.html");
		}
	}

}