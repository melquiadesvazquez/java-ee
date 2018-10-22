package modelo;

import java.util.List;

import javabeans.Libro;

public interface IDaoLibros {

	Libro getLibro(int id);

	List<Libro> listarLibros();

	List<Libro> listarLibros(String tema);

}