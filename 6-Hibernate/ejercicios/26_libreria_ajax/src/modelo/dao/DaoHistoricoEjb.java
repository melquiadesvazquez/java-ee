package modelo.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Historico;
@Stateless
@LocalBean
public class DaoHistoricoEjb implements DaoHistoricoEjbLocal {
	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;

	@Override
	public void crearHistorico(Historico h) {
		em.persist(h);
	}
	
}
