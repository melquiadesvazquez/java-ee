package modelo.dao;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Cliente;

@Local
public interface DaoClientesEjbLocal {

	

	void registrar(Cliente c);

	Cliente buscar(int idCliente);

	Cliente autenticar(String user, String pass);

}
