package managed;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entidades.Libro;
import modelo.service.ServiceLibreriaEjbLocal;

@RequestScoped
@ManagedBean(name="loginBean")
public class LoginBean {
	@EJB
	ServiceLibreriaEjbLocal serviceLibreria;
	
	private String usuario;
	private String password;
	private List<Libro> libros;
	
	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	//controlador de acción
	public String login() {
		if (serviceLibreria.recuperarCliente(usuario, password) != null) {
			libros=serviceLibreria.obtenerLibros(0);
			return "bienvenida";
		}
		else {
			return "error";
		}
	}
}
