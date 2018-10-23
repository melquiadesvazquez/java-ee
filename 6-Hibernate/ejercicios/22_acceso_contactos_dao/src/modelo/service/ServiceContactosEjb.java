package modelo.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import entidades.Contacto;
import modelo.dao.DaoContactosEjbLocal;

@Stateless
public class ServiceContactosEjb implements ServiceContactosEjbLocal {
	@EJB
	DaoContactosEjbLocal daoContactos;
	
	@Override
	public void guardarContacto(String nombre, String email, int edad) {
		if (daoContactos.buscarPorNombre(nombre) == null) {
			Contacto con = new Contacto(0, edad, email, nombre);
			daoContactos.guardar(con);
		}
	}
	
	@Override
	public void eliminarContacto(int id) {
		Contacto con = daoContactos.buscarPorClave(id);
		if (con != null) {
			daoContactos.eliminar(con);
		}
	}
	
	@Override
	public void eliminarContacto(String nombre) {
		Contacto con = daoContactos.buscarPorNombre(nombre);
		if (con != null) {
			daoContactos.eliminar(con);
		}
	}
	
	@Override
	public List<Contacto> listarContactos() {
		return daoContactos.recuperarTodos();
	}
}
