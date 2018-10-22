package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class EscuchadorEventos implements ServletContextListener, HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se)  {
    	ServletContext application = se.getSession().getServletContext();
    	synchronized(application) {
	    	int activos = (Integer)application.getAttribute("activos");
	    	application.setAttribute("activos", activos+1);
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  {
    	ServletContext application = se.getSession().getServletContext();
    	synchronized(application) {
	    	int activos = (Integer)se.getSession().getServletContext().getAttribute("activos");
	    	application.setAttribute("activos", activos-1);
    	}
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	synchronized(sce) {
	    	sce.getServletContext().setAttribute("visitas", 0);
	    	sce.getServletContext().setAttribute("activos", 0);
    	}
    }
	
}
