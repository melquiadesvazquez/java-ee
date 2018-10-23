package modelo;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Tema;

@Stateless
@LocalBean
public class TemasEjb implements TemasEjbLocal {
	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;
	
	@Override
	public List<Tema> listarTemas() {
		TypedQuery<Tema> tq = em.createNamedQuery("Tema.findAll", Tema.class);
		return tq.getResultList();		
	}

}
