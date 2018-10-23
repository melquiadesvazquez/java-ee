package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Alumno;
import entidades.Curso;

@Stateless
@LocalBean
public class CursoEjb implements CursoEjbLocal {
	@PersistenceContext(unitName="formacion_PU")
	EntityManager em;
	
	@Override
	public List<Curso> getCursos() {
		// TypedQuery<Curso> tq = em.createQuery("Select c from Curso c", Curso.class);
		// Uso de named query
		TypedQuery<Curso> tq = em.createNamedQuery("Curso.findAll", Curso.class);
		return tq.getResultList();
	}
	
	@Override
	public List<Curso> getCursos(String nombre) {
		List<Curso> result = new ArrayList<Curso>();
		
		if (nombre.trim().equals("")) {
			result = getCursos();
		}
		else {
			// TypedQuery<Curso> tq = em.createQuery("Select c from Curso c Where c.nombre Like ?1", Curso.class); 
			// Uso de named query
			TypedQuery<Curso> tq = em.createNamedQuery("Curso.findByName", Curso.class); 
			tq.setParameter(1, "%"+nombre+"%");
			result = tq.getResultList();
		}
		
		return result;
	}
	
	public int getCountCursos(String nombre) {
		Query q = em.createQuery("Select count(c) from Curso c Where c.nombre Like ?1");
		q.setParameter(1, nombre);
		Object resultado = ((Object[])q.getSingleResult())[0];
		return (Integer)resultado;
	}
	
	@Override
	public Curso getCurso(int idCurso) {
		return em.find(Curso.class, idCurso);
	}
	
	@Override
	public Curso crearCurso(String documento, int duracion, String nombre) {
		Curso curso = new Curso(0, documento, duracion, nombre);
		em.persist(curso);
		return curso;
	}
	
	@Override
	public void actualizarCurso(int idCurso, String documento, int duracion, String nombre) {
		Curso curso = em.find(Curso.class, idCurso);
		curso.setDocumento(documento);
		curso.setDuracion(duracion);
		curso.setNombre(nombre);
		em.merge(curso);
	}

}
