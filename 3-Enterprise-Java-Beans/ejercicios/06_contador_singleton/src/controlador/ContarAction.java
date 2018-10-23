package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbs.ContadorEjbLocal;

@WebServlet("/ContarAction")
public class ContarAction extends HttpServlet {
	
	@EJB
	ContadorEjbLocal contadorEjb;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		contadorEjb.incrementar();
	}

}
