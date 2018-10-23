package ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class SaludoEjb {

	public String getSaludo(String nombre) {
	   return "Bienvenido a los EJBs " + nombre;
	}

}
