package modelo;

import java.util.List;

import javax.ejb.Local;

import entidades.Alumno;

@Local
public interface AlumnoEjbLocal {
	
	List<Alumno> getAlumnos();
	
	Alumno getAlumno(String usuario);
	
	Alumno crearAlumno(String usuario, int edad, String email, String nombre, String password);
	
	void actualizarAlumno(String usuario, int edad, String email, String nombre, String password);

	String getPassword(String usuario);

	void matricularAlumno(String usuario, int idCurso);

	void desmatricularAlumno(String usuario, int idCurso);

}
