package managed;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import entidades.Cliente;
import modelo.service.ServiceLibreriaEjbLocal;

@ManagedBean
public class RegistroBean {
	@EJB
	ServiceLibreriaEjbLocal serviceLibreria;
	
	private Cliente cliente;
	private String mensajeError;
	
	public String getMensajeError() {
		return mensajeError;
	}


	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}


	public RegistroBean() {
		cliente = new Cliente();
	}
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	//controlador de acción
	public String registrar() {
		if (serviceLibreria.altaCliente(cliente.getUsuario(),
										cliente.getPassword(),
										cliente.getEmail(), 
										cliente.getTelefono())) {
			return "bienvenida";
		}
		else {
			mensajeError="El usuario no es valido";
			return "registro";
		}
	}
}
