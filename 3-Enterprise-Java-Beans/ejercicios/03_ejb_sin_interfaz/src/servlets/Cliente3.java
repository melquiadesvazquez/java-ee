package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbs.SaludoEjb;

@WebServlet("/Cliente3")
public class Cliente3 extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Context ct = new InitialContext();
			SaludoEjb miejb = (SaludoEjb)ct.lookup("java:global/03_ejb_sin_interfaz/SaludoEjb!ejbs.SaludoEjb");
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>" + miejb.getSaludo("profe3") + "</h1>");
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
