package modelo.dao;

import javax.ejb.Local;

import entidades.Cliente;

@Local
public interface DaoClientesEjbLocal {

	int registrarCliente(Cliente cliente);
	
	Cliente buscar(int idCliente);

	int autenticarCliente(String usuario, String password);

}
