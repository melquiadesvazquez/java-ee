package managed;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.Libro;
import entidades.Tema;
import modelo.service.ServiceLibreriaEjbLocal;

@RequestScoped
@ManagedBean
public class TemasBean {
	@EJB
	ServiceLibreriaEjbLocal serviceLibreria;
	
	private List<Tema> temas;
	private int temaElegido;
	
	public int getTemaElegido() {
		return temaElegido;
	}
	
	public void setTemaElegido(int tema) {
		this.temaElegido = tema;
	}
	
	public List<Tema> getTemas() {
		temas=serviceLibreria.obtenerTemas();
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}
	
}
