package ejbs;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class ContadorEjb implements ContadorEjbLocal {
	
	private int valor;
	
	@PostConstruct
	public void init() {
		valor = 100;
	}
	
	@Lock
	@Override
	public void incrementar() {
		valor++;		
	}
	
	@Lock
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
	
	@PreDestroy
	public void fin() {
	
	}

}
