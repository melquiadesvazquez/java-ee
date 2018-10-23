package ejbs;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import javabeans.Libro;

/**
 * Session Bean implementation class CarritoEjb
 */
@Stateful
@LocalBean
public class CarritoEjb implements CarritoEjbLocal {

	private List<Libro> cesta;
	
	@PostConstruct
	public void init() {
		cesta = new ArrayList<Libro>();
	}
	
	@Override
	public void add(Libro libro) {
		cesta.add(libro);	
	}
		
	@Override
	public void remove(int index) {
		cesta.remove(index);
	}
	
	@Override
	public List<Libro> get() {
		return cesta;
	}
		
	@Override
	public void set(List<Libro> cesta) {
		this.cesta = cesta;
	}

	@Remove
	@Override
	public void finalizar() {
		System.out.println("Se destruira la instancia");
	}
	
	@PrePassivate
	public void pasivacion() {
		System.out.println("Pasivacion");
	}
   	
	@PostActivate
	public void activacion() {
		System.out.println("Activacion");
	}
}
