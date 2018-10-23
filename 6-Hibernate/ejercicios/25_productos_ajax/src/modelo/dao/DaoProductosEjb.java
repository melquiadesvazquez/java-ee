package modelo.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidades.Producto;
import entidades.Seccion;


@Stateless
@LocalBean
public class DaoProductosEjb implements DaoProductosEjbLocal {
	@PersistenceContext(unitName="productos_PU")
	EntityManager em;
	
	@Override
	public List<Producto> obtenerProductosSeccion(int idSeccion) {
		Seccion s = em.find(Seccion.class,  idSeccion);
		em.refresh(s);
		return s.getProductos();
	}
	
	@Override
	public void eliminarProducto(int idProducto) {
		Producto producto = em.find(Producto.class, idProducto);
		em.remove(producto);
	}
}
