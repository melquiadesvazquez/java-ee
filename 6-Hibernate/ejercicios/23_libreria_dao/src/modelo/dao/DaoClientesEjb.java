package modelo.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Cliente;

@Stateless
@LocalBean
public class DaoClientesEjb implements DaoClientesEjbLocal {
	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;
	
	@Override
	public Cliente obtenerCliente(String usuario, String password) {		
		Cliente result = null;
		TypedQuery<Cliente> tq = em.createQuery("Select c from Cliente c where c.usuario=?1 and c.password=?2", Cliente.class);
		tq.setParameter(1, usuario);
		tq.setParameter(2, password);
				
		try {
			result = tq.setMaxResults(1).getSingleResult();
		}
		catch(NoResultException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public Cliente buscar(int idCliente) {
		return em.find(Cliente.class, idCliente);
	}
	
	@Override
	public int registrarCliente(Cliente cliente) {		
		em.persist(cliente);
		em.flush();
		return cliente.getIdCliente();
	}

}
