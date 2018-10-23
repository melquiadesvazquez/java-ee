package utilidades;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Contexto {
	public static void guardarAtributoSesion(String nombre, Object valor) {
		ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession s = (HttpSession)ex.getSession(true);
		s.setAttribute(nombre, valor);
	}
}
