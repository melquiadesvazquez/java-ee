package ejbs;

import javax.ejb.Local;

@Local
public interface SaludoEjbLocal {

	String getSaludo(String nombre);

}
