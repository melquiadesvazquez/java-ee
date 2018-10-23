package modelo.dao;

import java.util.List;

import javax.ejb.Local;

import entidades.Libro;
@Local
public interface DaoLibrosEjbLocal {

	List<Libro> recuperarLibros();

	List<Libro> recuperarLibros(int idTema);

	Libro recuperarLibroPorIsbn(int isbn);

	List<Libro> recuperarLibrosPaginas(int pagina);

}