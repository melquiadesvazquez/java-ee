package modelo.dao;

import java.util.List;

import javax.ejb.Local;

import entidades.Contacto;

@Local
public interface DaoContactosEjbLocal {
	void guardar(Contacto c);
	void actualizar(Contacto c);
	Contacto buscarPorClave(int idContacto);
	Contacto buscarPorNombre(String nombre);
	void eliminar(Contacto contacto);
	void eliminarPorNombre(String nombre);
	List<Contacto> recuperarTodos();
}
