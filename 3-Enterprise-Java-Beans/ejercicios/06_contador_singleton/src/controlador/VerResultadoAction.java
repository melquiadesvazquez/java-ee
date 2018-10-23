package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejbs.ContadorEjbLocal;

@WebServlet("/VerResultadoAction")
public class VerResultadoAction extends HttpServlet {
	
	@EJB
	ContadorEjbLocal contadorEjb;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setAttribute("valor", contadorEjb.getValor());	
	}

}
