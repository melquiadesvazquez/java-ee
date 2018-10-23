package listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ejbs.ContadorEjbLocal;

@WebListener
public class EscuchadorSession implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }
    
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	// obtenemos el objeto de la interfaz de negocio
    	// para llmar al metodo finalizar y que el contenedor elimine la instancia
    	ContadorEjbLocal contadorEjb = (ContadorEjbLocal)arg0.getSession().getAttribute("contador");
    	
    	if (contadorEjb != null) {
    		contadorEjb.finalizar();
    	}
    }
	
}
