package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Saludo")
public class Saludo extends HttpServlet {
		
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recogida del parametro
		String valor = request.getParameter("alias");
		String[] horas = request.getParameterValues("cursos");
		int totalHoras = 0;
		
		if (horas != null) {
			for(String h:horas) {
				totalHoras += Integer.parseInt(h);
			}
		}		
		
		// generar respuesta
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Bienvenido " + valor + "</h1>");
		out.println("<p>Horas completadas " + totalHoras + "</p>");		
		out.println("<p><a href='/02_saludo_personalizado/inicio.html'>Volver</a>");
		out.println("</body></html>");
	}

}
