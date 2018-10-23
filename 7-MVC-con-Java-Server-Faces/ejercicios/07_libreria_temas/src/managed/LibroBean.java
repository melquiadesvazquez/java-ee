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
public class LibroBean {
	@EJB
	ServiceLibreriaEjbLocal serviceLibreria;
	
	private Libro libro;
	
	@ManagedProperty("#{librosBean}")
	private LibrosBean lbean;
	
	public Libro getLibro() {
		libro = lbean.getLibroElegido();
		System.out.println("------------------------");
		System.out.println(libro);
		System.out.println("------------------------");
		return libro;
	}
	
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public LibrosBean getLbean() {
		return lbean;
	}

	public void setLbean(LibrosBean lbean) {
		this.lbean = lbean;
	}
	
	public String actualizar(Libro libro) {
		serviceLibreria.actualizarLibro(libro);
		return "libros";
	}

}
