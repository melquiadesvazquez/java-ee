package entidades;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the matriculas database table.
 * 
 */
@Entity
@Table(name="matriculas")
@NamedQuery(name="Matricula.findAll", query="SELECT m FROM Matricula m")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MatriculaPK id;

	private double nota;

	//bi-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name="usuario", referencedColumnName="usuario", insertable=false, updatable=false)
	private Alumno alumno;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="idCurso", referencedColumnName="idCurso", insertable=false, updatable=false)
	private Curso curso;

	public Matricula() {
	}
	
	public Matricula(MatriculaPK id, double nota) {
		super();
		this.id = id;
		this.nota = nota;
	}
	

	public Matricula( double nota, Alumno alumno, Curso curso) {
		super();
		this.nota = nota;
		this.alumno = alumno;
		this.curso = curso;
		this.id = new MatriculaPK(alumno.getUsuario(), curso.getIdCurso());
	}
	
	public Matricula(MatriculaPK id, double nota, Alumno alumno, Curso curso) {
		super();
		this.id = id;
		this.nota = nota;
		this.alumno = alumno;
		this.curso = curso;
	}

	public MatriculaPK getId() {
		return this.id;
	}

	public void setId(MatriculaPK id) {
		this.id = id;
	}

	public double getNota() {
		return this.nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}