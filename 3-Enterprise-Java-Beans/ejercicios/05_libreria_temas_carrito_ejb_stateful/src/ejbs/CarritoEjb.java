package ejbs;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import javabeans.Libro;

/**
 * Session Bean implementation class CarritoEjb
 */
@Stateful
@LocalBean
public class CarritoEjb implements CarritoEjbLocal {

	private ArrayList<Libro> cesta;
	
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
	public ArrayList<Libro> get() {
		return cesta;
	}
		
	@Override
	public void set(ArrayList<Libro> cesta) {
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
