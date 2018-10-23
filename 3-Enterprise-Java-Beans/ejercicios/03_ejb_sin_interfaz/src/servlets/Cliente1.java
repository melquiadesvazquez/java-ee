package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbs.SaludoEjb;

@WebServlet("/Cliente1")
public class Cliente1 extends HttpServlet {

	// inyeccion de dependencias desde Java EE 5
	@EJB
	SaludoEjb miejb;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>" + miejb.getSaludo("profe1") + "</h1>");
	}

}
