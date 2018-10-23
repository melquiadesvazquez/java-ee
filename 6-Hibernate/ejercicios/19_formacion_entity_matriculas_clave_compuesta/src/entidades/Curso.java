package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCurso;

	private String documento;

	private int duracion;

	private String nombre;

	//bi-directional many-to-one association to Matricula
	@OneToMany(mappedBy="curso")
	private List<Matricula> matriculas;

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

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setCurso(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setCurso(null);

		return matricula;
	}

}