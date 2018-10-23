package managed;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import modelo.service.ServiceLibreriaEjbLocal;
import utilidades.Contexto;

@RequestScoped
@ManagedBean(name="loginBean")
public class LoginBean {
	@EJB
	ServiceLibreriaEjbLocal serviceLibreria;
	
	private String usuario;
	private String password;

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
			Contexto.guardarAtributoSesion("usuario", usuario);
			return "temas";
		}
		else {
			return "error";
		}
	}
}
