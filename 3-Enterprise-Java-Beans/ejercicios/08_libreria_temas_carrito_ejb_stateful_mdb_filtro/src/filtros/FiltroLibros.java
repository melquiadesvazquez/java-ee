package filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroLibros
 */
@WebFilter(urlPatterns = {"/Controller"})
public class FiltroLibros implements Filter {

   
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("++++++++++++++++");
		System.out.println("ENTRANDO A FILTRO 2");
		System.out.println("++++++++++++++++");
		
		HttpServletRequest hsr = ((HttpServletRequest)request);
		HttpSession s = hsr.getSession();
		String q = hsr.getQueryString();
		
		//if (s.getAttribute("cliente") != null) {	
		if (q.equals("op=doTemas") && s.getAttribute("cliente") == null) {
			((HttpServletResponse)response).sendRedirect("login.jsp");
			
		}
		else {
			chain.doFilter(request, response); // seguir adelante
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("++++++++++++++++");
		System.out.println("ENTRANDO A FILTRO 1");
		System.out.println("++++++++++++++++");
	}

}
