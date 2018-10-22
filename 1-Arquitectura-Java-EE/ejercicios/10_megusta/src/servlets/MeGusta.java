package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MeGusta")
public class MeGusta extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// Para que solo se vote una vez
		ServletContext sc = this.getServletContext();
		int likes = 0;
		if (request.getSession().getAttribute("liked")==null) {
			// Para que si dos usuarios votan a la vez, lo cuente bien
			synchronized(sc) {	
				if (sc.getAttribute("likes") != null) {
					likes = (Integer)sc.getAttribute("likes");
				}
				likes++;
				sc.setAttribute("likes", likes);
			}
			request.getSession().setAttribute("liked", true);
		}
		
		//request.getRequestDispatcher("menu.html").forward(request, response);
		response.sendRedirect("resultado.jsp");
	}

}
