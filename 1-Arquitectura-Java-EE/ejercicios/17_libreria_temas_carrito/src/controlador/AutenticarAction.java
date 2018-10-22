package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.Libro;
import modelo.DaoClientes;

@WebServlet("/AutenticarAction")
public class AutenticarAction extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoClientes dCliente = new DaoClientes();
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
