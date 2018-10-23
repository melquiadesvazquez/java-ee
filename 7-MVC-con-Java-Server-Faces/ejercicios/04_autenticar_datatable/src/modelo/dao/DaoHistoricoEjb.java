package modelo.dao;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Cliente;
import entidades.Historico;
import entidades.Venta;


@Stateless
@LocalBean
public class DaoHistoricoEjb implements DaoHistoricoEjbLocal {

	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;
	@Override
	@Asynchronous
	public void guardar(Historico h) {
		em.persist(h);
	}
}
