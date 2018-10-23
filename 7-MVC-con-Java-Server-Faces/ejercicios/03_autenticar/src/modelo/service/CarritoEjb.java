package modelo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import entidades.Libro;

/**
 * Session Bean implementation class CarritoEjb
 */
@Stateful
@LocalBean
public class CarritoEjb implements CarritoEjbLocal {

    List<Libro> libros;
    @PostConstruct
    public void init() {
    	libros=new ArrayList<>();
    }
    
    @Override
	public void agregarCarrito(Libro lib) {
    	libros.add(lib);
    }
    @Override
	public void eliminarLibro(int pos) {
    	libros.remove(pos);
    }
    @Override
	public List<Libro> recuperarCarrito(){
    	return libros;
    }
    
    @Override
	@Remove
    public void eliminar() {
    	
    }
}
