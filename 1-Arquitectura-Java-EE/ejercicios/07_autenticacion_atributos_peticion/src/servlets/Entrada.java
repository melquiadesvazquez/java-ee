package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Persona;

@WebServlet("/Entrada")
public class Entrada extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		Persona pr = (Persona)request.getAttribute("per");
		
		out.println("<html><body>");
		out.println("<p>Bienvenido " + request.getParameter("user") + "</p>");
		out.println("<p>Nombre " + pr.getNombre() + "</p>");
		out.println("<p>Email " + pr.getEmail() + "</p>");
		out.println("<p>Edad " + pr.getEdad() + "</p>");
		out.println("</body></html>");
	}

}
