package modelo.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import entidades.Cliente;
import entidades.Historico;
import entidades.Libro;
import entidades.Venta;

@Stateless
@LocalBean
public class DaoVentasEjb implements DaoVentasEjbLocal {
	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;

	@Override
	public int crearVentas(int idCliente, List<Libro> cesta) {
		int result = 0;
		
		for (Libro libro : cesta) {
			Libro l = em.find(Libro.class, libro.getIsbn());
			Cliente c = em.find(Cliente.class, idCliente);
			Venta v = new Venta(0, new Date(), l, c);
			em.persist(v);
			em.flush();
			result += l.getPrecio();
		}
		
		return result;
	}
	
	public void crearVenta(Venta v) {
		em.persist(v);
	}
		
	// Data una fecha, obtener todos los clientes que hayan hecho pedidos a partir de dicha fecha
	@Override
	public List<Cliente> getClientesByDate(Date fecha) {
		TypedQuery<Cliente> tq = em.createQuery("Select DISTINC(c) From Cliente c JOIN c.ventas v Where v.fecha > ?1", Cliente.class);
		tq.setParameter(1, fecha, TemporalType.TIMESTAMP);
		
		return tq.getResultList();
	}
	
	// Obtener los libros que ha comprado un cliente a partir de su id
	@Override
	public List<Libro> getLibrosByCliente(int idCliente) {
		TypedQuery<Libro> tq = em.createQuery("Select DISTINC(l) From Libros l JOIN l.ventas v Where v.cliente.idCliente = ?1", Libro.class);
		tq.setParameter(1, idCliente);
		
		return tq.getResultList();
	}
}
