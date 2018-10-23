package modelo;

import javax.ejb.Local;

@Local
public interface ClientesEjbLocal {

	int registrarCliente(String usuario, String password, String email, int telefono);

	int autenticarCliente(String usuario, String password);

}
