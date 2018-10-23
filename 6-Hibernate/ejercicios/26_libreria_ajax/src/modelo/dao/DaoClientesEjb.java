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
	public int autenticarCliente(String usuario, String password) {		
		int result = 0;
		TypedQuery<Cliente> tq = em.createQuery("Select c from Cliente c where c.usuario=?1 and c.password=?2", Cliente.class);
		tq.setParameter(1, usuario);
		tq.setParameter(2, password);
		
		/*
		List<Cliente> todos = tq.getResultList();
		if (todos!=null && todos.size()>0) {
			result = todos.get(0).getIdCliente();
		}
		*/
		
		try {
			Cliente c = tq.setMaxResults(1).getSingleResult();
			result = c.getIdCliente();
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
