package controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejbs.ContadorEjbLocal;

@EJB(name="aliasejb", beanInterface=ejbs.ContadorEjbLocal.class)
@WebServlet("/ContarAction")
public class ContarAction extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession();
		ContadorEjbLocal contadorEjb = (ContadorEjbLocal)s.getAttribute("contador");
		
		if (contadorEjb == null) {
			try {
				Context ct = new InitialContext();
				contadorEjb = (ContadorEjbLocal)ct.lookup("java:comp/env/aliasejb");
				s.setAttribute("contador", contadorEjb);
			}
			catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
		contadorEjb.incrementar();
	}

}
