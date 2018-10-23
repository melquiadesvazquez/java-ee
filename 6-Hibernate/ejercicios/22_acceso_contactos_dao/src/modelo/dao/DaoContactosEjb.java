package modelo.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Contacto;

@Stateless
@LocalBean
public class DaoContactosEjb implements DaoContactosEjbLocal {
	@PersistenceContext(unitName="contactos_PU")
	EntityManager em;

	@Override
	public void guardar(Contacto c) {
		em.persist(c);		
	}

	@Override
	public void actualizar(Contacto c) {
		em.merge(c);		
	}

	@Override
	public Contacto buscarPorClave(int idContacto) {
		return em.find(Contacto.class, idContacto);
	}

	@Override
	public Contacto buscarPorNombre(String nombre) {
		TypedQuery<Contacto> tq = em.createQuery("Select c from Contacto c where c.nombre=?1", Contacto.class);
		tq.setParameter(1, nombre);
		Contacto c = null;
		try {
			c = tq.getSingleResult();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return c;
	}

	@Override
	public void eliminar(Contacto contacto) {
		em.remove(contacto);		
	}

	@Override
	public void eliminarPorNombre(String nombre) {
		Query q = em.createQuery("Delete from Contacto c where c.nombre=?1");
		q.setParameter("nom", nombre);
		q.executeUpdate();
	}

	@Override
	public List<Contacto> recuperarTodos() {
		TypedQuery<Contacto> tq = em.createQuery("Select c from Contacto c", Contacto.class);
		return tq.getResultList();		
	}

}
