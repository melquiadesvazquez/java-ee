package modelo;

import javax.ejb.Local;

import entidades.Cliente;

@Local
public interface ClientesEjbLocal {

	int registrarCliente(Cliente cliente);

	int autenticarCliente(String usuario, String password);

}
