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

import ejbs.SaludoEjbLocal;

@WebServlet("/Cliente3")
public class Cliente3 extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Context ct = new InitialContext();
			SaludoEjbLocal miejb = (SaludoEjbLocal)ct.lookup("java:global/01_ejemplo_ejb/SaludoEjb!ejbs.SaludoEjbLocal");
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h1>" + miejb.getSaludo("profe3") + "</h1>");
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
