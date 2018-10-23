package modelo.service;

import java.util.List;

import javax.ejb.Local;

import entidades.Libro;

@Local
public interface CarritoEjbLocal {

	List<Libro> recuperarCarrito();

	void eliminarLibro(int pos);

	void agregarCarrito(Libro lib);

	void eliminar();

}
