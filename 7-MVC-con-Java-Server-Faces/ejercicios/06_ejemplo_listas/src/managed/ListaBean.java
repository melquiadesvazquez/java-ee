package managed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import beans.Ciudad;

@ManagedBean
public class ListaBean {
	private List<Ciudad> ciudades;
	private int codigo;
	
	public List<Ciudad> getCiudades() {
		return ciudades;
	}
	
	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ListaBean() {
		ciudades = new ArrayList<>();
		ciudades.add(new Ciudad("madrid", 28, 27.9));
		ciudades.add(new Ciudad("huelva", 24, 31.3));
		ciudades.add(new Ciudad("teruel", 40, 16.9));
	}
	
}
