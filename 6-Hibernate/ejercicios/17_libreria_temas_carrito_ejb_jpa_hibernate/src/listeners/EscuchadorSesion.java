package listeners;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import modelo.CarritoEjbLocal;

@EJB(name="carritoRef", beanInterface=modelo.CarritoEjbLocal.class)
@WebListener
public class EscuchadorSesion implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se)  {
		try {
			Context ct=new InitialContext();
			//CarritoEjbLocal carrito = (CarritoEjbLocal)ct.lookup("java:global/05_libreria_temas_carrito_ejb_stateful/CarritoEjb!ejbs.CarritoEjbLocal");
			CarritoEjbLocal carrito = (CarritoEjbLocal)ct.lookup("java:comp/env/carritoRef");
			se.getSession().setAttribute("carrito", carrito);
		}
		catch(NamingException ex) {
			ex.printStackTrace();
		}
		
        System.out.println("++++++++++++++");
        System.out.println("carrito creado");
        System.out.println("++++++++++++++");

    }
    
    public void sessionDestroyed(HttpSessionEvent se)  {    	
    	CarritoEjbLocal carrito = (CarritoEjbLocal)se.getSession().getAttribute("carrito");
    	
    	if (carrito != null) {
    		carrito.finalizar();
    	}
    	
        System.out.println("++++++++++++++");
        System.out.println("carrito destruido");
        System.out.println("++++++++++++++");
    }
}
