package modelo.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import entidades.Cliente;
import entidades.Libro;
import entidades.Venta;

/**
 * Session Bean implementation class PedidosEjb
 */
@Stateless
@LocalBean
public class DaoVentasEjb implements DaoVentasEjbLocal {

	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;
	@Override
	public void registrar(Venta v) {		   	        
            em.persist(v);
	}
	
	/*
	public List<Cliente> pedidosFecha(Date fecha){
		String jpql="Select distinct(c) From Cliente c ";
		jpql+="join c.ventas v where v.fecha>?1";
		TypedQuery<Cliente> tq=em.createQuery(jpql,Cliente.class);
		tq.setParameter(1, fecha,TemporalType.TIMESTAMP);
		return tq.getResultList();
	}

	public List<Libro> pedidosCliente(int idCliente){
		String jpql="Select l From Libro l ";
		jpql+="join l.ventas v where v.cliente.idCliente=?1";
		TypedQuery<Libro> tq=em.createQuery(jpql,Libro.class);
		tq.setParameter(1, idCliente);
		return tq.getResultList();		
	}*/
	
}
