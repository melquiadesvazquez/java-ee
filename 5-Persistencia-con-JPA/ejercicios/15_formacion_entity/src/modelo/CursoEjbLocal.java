package modelo;

import java.util.List;

import javax.ejb.Local;

import entidades.Curso;

@Local
public interface CursoEjbLocal {

	List<Curso> getCursos();
	
	List<Curso> getCursos(String name);

	Curso getCurso(int idCurso);
	
	void actualizarCurso(int idCurso, String documento, int duracion, String nombre);

	Curso crearCurso(String documento, int duracion, String nombre);

}
