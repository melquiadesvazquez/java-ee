package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DaoClientes;

@WebServlet("/RegistrarAction")
public class RegistrarAction extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoClientes dCliente = new DaoClientes();
		if (dCliente.registrarCliente(request.getParameter("usuario"), request.getParameter("password"), request.getParameter("email"), Integer.parseInt(request.getParameter("telefono"))) > 0) {
			request.setAttribute("url", "listar.jsp");
		}
		else {
			request.setAttribute("url", "registro.html");
		}
	}

}