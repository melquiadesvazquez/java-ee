package modelo.dao;

import java.util.List;

import javax.ejb.Local;

import entidades.Producto;

@Local
public interface DaoProductosEjbLocal {

	List<Producto> obtenerProductosSeccion(int idSeccion);

	void eliminarProducto(int idProducto);

}
