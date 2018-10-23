package modelo;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Alumno;

@Stateless
@LocalBean
public class AlumnoEjb implements AlumnoEjbLocal {
	@PersistenceContext(unitName="formacion_PU")
	EntityManager em;
	
	@Override
	public List<Alumno> getAlumnos() {
		TypedQuery<Alumno> tq = em.createQuery("Select a from Alumno a", Alumno.class);
		return tq.getResultList();
	}
	
	@Override
	public Alumno getAlumno(String usuario) {
		return em.find(Alumno.class, usuario);
	}
	
	public List<String> getUsuariosFromAlumnoByName(String nombre) {
		//Query q = em.createQuery("Select a.usuario from Alumno a Where a.nombre Like Binary ?1", Alumno.class);
		// Uso de native query
		Query q = em.createNativeQuery("Select usuario from alumnos Where nombre Like Binary ?", Alumno.class);
		q.setParameter(1, "%"+nombre+"%");
		List<Object[]> lista = (List<Object[]>)q.getResultList();
		return lista.stream().map(o->(String)o[0]).collect(Collectors.toList());
	}
	
	@Override
	public String getPassword(String usuario) {
		Query q = em.createNamedQuery("Alumno.findPwd");
		q.setParameter(1, usuario);
		Object[] resultado = (Object[])q.getSingleResult();
		return (String)resultado[0];
	}
	
	@Override
	public Alumno crearAlumno(String usuario, int edad, String email, String nombre, String password) {
		Alumno alumno = null;
		
		if (getAlumno(usuario) == null) {
			alumno = new Alumno(usuario, edad, email, nombre, password);
			em.persist(alumno);
		}
		
		return alumno;
	}
	
	@Override
	public void actualizarAlumno(String usuario, int edad, String email, String nombre, String password) {
		Alumno alumno = em.find(Alumno.class, usuario);
		alumno.setEdad(edad);
		alumno.setEmail(email);
		alumno.setNombre(nombre);
		alumno.setPassword(password);
		em.merge(alumno);
	}
}
