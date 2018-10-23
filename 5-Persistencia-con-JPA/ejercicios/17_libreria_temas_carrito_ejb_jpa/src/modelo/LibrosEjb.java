package modelo;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Libro;

@Stateless
@LocalBean
public class LibrosEjb implements LibrosEjbLocal {
	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;
	
	@Override
	public Libro getLibro(int id) {
		return em.find(Libro.class, id);
	}
	
	@Override
	public List<Libro> listarLibros() {		
		TypedQuery<Libro> tq = em.createNamedQuery("Libro.findAll", Libro.class);
		return tq.getResultList();
	}
	
	@Override
	public List<Libro> listarLibros(String tema) {
		TypedQuery<Libro> tq = em.createQuery("Select l From Libro l Where l.tema.tema=?1", Libro.class);
		tq.setParameter(1, tema);
		return tq.getResultList();
	}
	
	@Override
	public List<Libro> listarLibros(int pageNumber, int pageSize) {		
		TypedQuery<Libro> tq = em.createNamedQuery("Libro.findAll", Libro.class);
		// tq.setFirstResult((pageNumber * pageSize) + 1); 
		tq.setFirstResult((pageNumber-1) * pageSize); 
		tq.setMaxResults(pageSize);
		return tq.getResultList();
	}	
}
