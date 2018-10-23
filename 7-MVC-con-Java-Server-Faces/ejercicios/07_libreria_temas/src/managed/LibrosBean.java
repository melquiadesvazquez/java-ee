package managed;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import entidades.Libro;
import modelo.service.ServiceLibreriaEjbLocal;

@RequestScoped
@ManagedBean
public class LibrosBean {
	@EJB
	ServiceLibreriaEjbLocal serviceLibreria;
	
	private List<Libro> libros;
	private Libro libroElegido;
	
	@ManagedProperty("#{temasBean}")
	private TemasBean tbean;

	
	public Libro getLibroElegido() {
		return libroElegido;
	}

	public void setLibroElegido(Libro libroElegido) {
		this.libroElegido = libroElegido;
	}

	public List<Libro> getLibros() {
		libros=serviceLibreria.obtenerLibros(tbean.getTemaElegido());
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public TemasBean getTbean() {
		return tbean;
	}

	public void setTbean(TemasBean tbean) {
		this.tbean = tbean;
	}
	
	public String editar(Libro libro) {
		libroElegido = libro;
		return "libro";
	}
}
