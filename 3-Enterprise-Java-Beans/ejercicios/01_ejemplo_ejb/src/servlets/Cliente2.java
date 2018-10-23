package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbs.SaludoEjbLocal;

@WebServlet("/Cliente2")
public class Cliente2 extends HttpServlet {

	// inyeccion de dependencias desde Java EE 7
	@Inject
	SaludoEjbLocal miejb;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>" + miejb.getSaludo("profe2") + "</h1>");
	}

}
