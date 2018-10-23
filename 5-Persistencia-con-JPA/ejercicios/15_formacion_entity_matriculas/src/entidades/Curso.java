package entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the cursos database table.
 * 
 */
@Entity
@Table(name="cursos")
@NamedQueries({
	@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c"),
	@NamedQuery(name="Curso.findByName", query="Select c from Curso c Where c.nombre Like ?1")	
})
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCurso;

	private String documento;

	private int duracion;

	private String nombre;
	
	//bi-directional many-to-many association to Cliente
	@ManyToMany(mappedBy="cursos")
	private List<Alumno> alumnos;

	public Curso() {
	}
	
	public Curso(int idCurso, String documento, int duracion, String nombre) {
		super();
		this.idCurso = idCurso;
		this.documento = documento;
		this.duracion = duracion;
		this.nombre = nombre;
	}

	public int getIdCurso() {
		return this.idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		
	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}
	
	public void setCursos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

}