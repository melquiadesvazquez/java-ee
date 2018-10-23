package modelo.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Cliente;

/**
 * Session Bean implementation class ClientesEjb
 */
@Stateless
@LocalBean
public class DaoClientesEjb implements DaoClientesEjbLocal {
	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;
	@Override
	public Cliente autenticar(String user, String pass) {
		Cliente cl=null;
		String jpql="Select c From Cliente c Where c.usuario=?1 and c.password=?2";
		TypedQuery<Cliente> tq=em.createQuery(jpql,Cliente.class);
		tq.setParameter(1, user);
		tq.setParameter(2, pass);
		List<Cliente> todos=tq.getResultList();
		if(todos!=null&&todos.size()>0) {
			cl=todos.get(0);
		}
		/*try {
			cl=tq.getSingleResult();
		}
		catch(NoResultException ex) {
			ex.printStackTrace();
		}*/
		return cl;
	}

	@Override
	public void registrar(Cliente c) {
		em.persist(c); 
		
	}
	
	@Override
	public Cliente buscar(int idCliente) {
		return em.find(Cliente.class, idCliente);
	}

}
