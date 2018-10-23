package managed;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import entidades.Alumno;
import modelo.AlumnoEjbLocal;

@ManagedBean
public class AlumnosBean {
	@EJB
	AlumnoEjbLocal serviceAlumno;
	
	private List<Alumno> alumnos;
	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public String mostrarAlumnos() {
		 alumnos = serviceAlumno.getAlumnos();
		 return "alumnos";
	}
	
}
