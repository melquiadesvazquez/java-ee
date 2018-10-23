package modelo;

import java.util.List;

import javax.ejb.Local;

import javabeans.Libro;

@Local
public interface LibrosEjbLocal {

	List<Libro> listarLibros(String tema);

	List<Libro> listarLibros();

	Libro getLibro(int id);

}
