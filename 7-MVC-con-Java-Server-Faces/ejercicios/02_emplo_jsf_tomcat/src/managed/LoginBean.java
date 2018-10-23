package managed;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name="loginBean")
public class LoginBean {
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
		if (password.equals("curso")) {
			return "bienvenida";
		}
		else {
			return "error";
		}
	}
}
