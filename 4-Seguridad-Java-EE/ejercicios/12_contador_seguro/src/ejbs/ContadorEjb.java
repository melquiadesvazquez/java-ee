package ejbs;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
@LocalBean
public class ContadorEjb implements ContadorEjbLocal {
	
	private int valor;
	
	@PostConstruct
	public void init() {
		valor = 100;
	}
	
	@RolesAllowed("{usuarios}")
	@Override
	public void incrementar() {
		valor++;
		
	}

	@Override
	public void decrementar() {
		valor--;		
	}

	@Override
	public int getValor() {
		return valor;
	}

	@Override
	public void inicializar(int valor) {
		this.valor = valor;
	}

	@Override
	@Remove
	public void finalizar() {
		System.out.println("Se destruira la instancia");
	}
	
	@Override
	@PrePassivate
	public void pasivacion() {
		System.out.println("Pasivacion");
	}
   	
	@Override
	@PostActivate
	public void activacion() {
		System.out.println("Activacion");
	}
   	
}
