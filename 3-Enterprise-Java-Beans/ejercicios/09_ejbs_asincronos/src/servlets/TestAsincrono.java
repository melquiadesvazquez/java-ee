package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejbs.CalculoEjbLocal;


@WebServlet("/TestAsincrono")
public class TestAsincrono extends HttpServlet {
	
	@EJB
	CalculoEjbLocal calculo;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			Future<Integer> ft = calculo.sumaNumeros(50);
			
			out.println("<html><body>");
			
			while(!ft.isDone()) {
				System.out.println("esperando!!!");
			}
			
			int n = ft.get();		
			out.println("<h1>La suma es: " + n + "</h1>");
			
			out.println("</body></html>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
