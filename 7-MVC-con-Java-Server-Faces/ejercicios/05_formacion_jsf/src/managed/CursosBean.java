package managed;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import entidades.Curso;
import modelo.CursoEjbLocal;

@ManagedBean
public class CursosBean {
	@EJB
	CursoEjbLocal serviceCurso;
	
	private List<Curso> cursos;
	
	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	public String mostrarCursos() {
		 cursos = serviceCurso.getCursos();
		 return "cursos";
	}
	
}
