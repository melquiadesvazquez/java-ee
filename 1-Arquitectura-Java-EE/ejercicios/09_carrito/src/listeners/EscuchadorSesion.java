package listeners;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import beans.Order;

@WebListener
public class EscuchadorSesion implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se)  { 
         HttpSession session = se.getSession();
         session.setAttribute("cesta", new ArrayList<Order>());
    }


}
