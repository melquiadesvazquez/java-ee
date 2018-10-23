package listeners;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import javabeans.Libro;

@WebListener
public class EscuchadorSesion implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se)  { 
         HttpSession session = se.getSession();
         session.setAttribute("cesta", new ArrayList<Libro>());
         
         System.out.println("++++++++++++++");
         System.out.println("Cesta creada");
         System.out.println("++++++++++++++");
    }
    
    public void sessionDestroyed(HttpSessionEvent se)  {
    
    }
}
