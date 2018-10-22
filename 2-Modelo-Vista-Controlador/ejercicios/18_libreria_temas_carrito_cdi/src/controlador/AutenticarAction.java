package controlador;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.IDaoClientes;

@WebServlet("/AutenticarAction")
public class AutenticarAction extends HttpServlet {
	@Inject
	IDaoClientes dCliente;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cliente = dCliente.autenticarCliente(request.getParameter("usuario"), request.getParameter("password"));
		
		if (cliente > 0) {
			request.getSession().setAttribute("cliente", cliente);
		 	request.setAttribute("url", "temas.jsp");
		}
		else {
			request.setAttribute("url", "login.html");
		}
	}

}
