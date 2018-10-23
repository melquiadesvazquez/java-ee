package modelo.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Producto;
import entidades.Seccion;


@Stateless
@LocalBean
public class DaoSeccionesEjb implements DaoSeccionesEjbLocal {
	@PersistenceContext(unitName="productos_PU")
	EntityManager em;
	
	@Override
	public List<Seccion> obtenerSecciones() {
		TypedQuery<Seccion> seciones = em.createNamedQuery("Seccion.findAll", Seccion.class);
		return seciones.getResultList();
	}

}
