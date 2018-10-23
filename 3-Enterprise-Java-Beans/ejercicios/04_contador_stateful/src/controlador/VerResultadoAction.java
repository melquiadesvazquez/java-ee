package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejbs.ContadorEjbLocal;

@WebServlet("/VerResultadoAction")
public class VerResultadoAction extends HttpServlet {
			
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		ContadorEjbLocal contadorEjb = (ContadorEjbLocal)s.getAttribute("contador");
		
		if (contadorEjb != null) {
			request.setAttribute("valor", contadorEjb.getValor());
		}
		else {
			request.setAttribute("valor", "no hay contador");
		}		
	}

}
