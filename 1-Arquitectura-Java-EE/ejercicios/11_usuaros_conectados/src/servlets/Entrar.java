package servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Entrar")
public class Entrar extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		ServletContext application = this.getServletContext();
		HttpSession session = request.getSession();
		
		synchronized(application) {
	        int visitas = (Integer)application.getAttribute("visitas");
	        application.setAttribute("visitas", visitas+1);
		}
		
		// Crear cookie con fecha de visita
		String fecha = LocalDateTime.now().toString();
		Cookie ck = new Cookie("fecha", fecha);
		
		// Darle fecha de expiracion
		ck.setMaxAge(Integer.MAX_VALUE);
		
		// Añadirla al response
		response.addCookie(ck);

		request.getRequestDispatcher("menu.html").forward(request, response);
	}
}
