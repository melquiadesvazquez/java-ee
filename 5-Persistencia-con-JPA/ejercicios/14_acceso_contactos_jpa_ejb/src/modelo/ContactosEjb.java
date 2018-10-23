package modelo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Contacto;

@Stateless
public class ContactosEjb implements ContactosEjbLocal {
	// Inyección del EntityManager
	@PersistenceContext(unitName="contactos_PU")
	EntityManager em;

	@Override
	public void guardarContacto(String nombre, String email, int edad) {
		Contacto con = new Contacto(0, edad, email, nombre);
		em.persist(con);
	}
	
	@Override
	public void eliminarContacto(int id) {
		Contacto con = em.find(Contacto.class, id);
		if (con != null) {
			em.remove(con);
		}
	}
	
	@Override
	public void eliminarContacto(String nombre) {
		// TypedQuery<Contacto> tq = em.createQuery("Delete from Contacto c where c.nombre=?1", Contacto.class);
		// tq.setParameter(1, nombre);		
		TypedQuery<Contacto> tq = em.createQuery("Delete from Contacto c where c.nombre=:nom", Contacto.class);
		tq.setParameter("nom", nombre);
		tq.executeUpdate();
	}
	
	@Override
	public List<Contacto> listarContactos() {
		TypedQuery<Contacto> tq = em.createQuery("Select c from Contacto c", Contacto.class);
		return tq.getResultList();
	}
}
