package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported = true, urlPatterns = { "/Asincrono" })
public class Asincrono extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		PrintWriter out = response.getWriter();
		
		AsyncContext contexto = request.startAsync();
		contexto.setTimeout(1000);
		contexto.start(new Runnable() {
			@Override	
			public void run() {
				for (int i = 1; i <= 10; i++) {
					System.out.println("Tarea en segundo plano " + i);
					try {			
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
 				}
				contexto.complete();
			};
		});

		contexto.addListener(new AsyncListener() {

			@Override
			public void onComplete(AsyncEvent arg0) throws IOException {
				System.out.println("Tarea completada");
				
			}

			@Override
			public void onError(AsyncEvent arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStartAsync(AsyncEvent arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTimeout(AsyncEvent arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		out.println("<html><body>");
		out.println("<h1>Servlet ejecutado asincronamente<h1>");
		out.println("</body></html>");
		out.close();
	}

}
