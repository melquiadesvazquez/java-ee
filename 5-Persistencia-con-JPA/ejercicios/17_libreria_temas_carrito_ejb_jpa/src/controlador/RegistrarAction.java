package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import modelo.ClientesEjbLocal;

@WebServlet("/RegistrarAction")
public class RegistrarAction extends HttpServlet {
	
	@EJB
	ClientesEjbLocal dCliente;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = new Cliente(0, request.getParameter("email"), request.getParameter("password"), Integer.parseInt(request.getParameter("telefono")), request.getParameter("usuario"), null, null);
		if (dCliente.registrarCliente(cliente) > 0) {
			request.setAttribute("url", "temas.jsp");
		}
		else {
			request.setAttribute("url", "registro.html");
		}
	}

}