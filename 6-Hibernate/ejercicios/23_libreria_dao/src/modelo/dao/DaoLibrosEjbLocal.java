package modelo.dao;

import java.util.List;

import javax.ejb.Local;

import entidades.Libro;

@Local
public interface DaoLibrosEjbLocal {

	List<Libro> listarLibros(String tema);

	List<Libro> listarLibros();

	List<Libro> listarLibros(int pageNumber, int pageSize);
	
	Libro getLibro(int id);

	List<Libro> listarLibros(int tema);

}
