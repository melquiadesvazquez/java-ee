package modelo;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Libro;
import entidades.Tema;

@Stateless
@LocalBean
public class LibrosEjb implements LibrosEjbLocal {
	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;
	
	public List<Libro> getLibrosByTemaId(int idTema) {
		Tema t = em.find(Tema.class, idTema);
		return t.getLibros();
	}
	
	public List<Libro> getLibrosByTemaName(String tema) {
		TypedQuery<Libro> tq = em.createQuery("Select l From Libro l Where l.tema.tema=?1", Libro.class);
		tq.setParameter(1, tema);
		return tq.getResultList();
		
	}
	
	public Tema getTema(int idTema) {
		Tema t = em.find(Tema.class, idTema);
		em.detach(t);
		return t;
	}
	
	public void deleteTema(int idTema) {
		Tema t = em.find(Tema.class, idTema);
		em.remove(t);
	}
}
