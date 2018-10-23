package ejbs;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remove;

import javabeans.Libro;

@Local
public interface CarritoEjbLocal {

	ArrayList<Libro> get();

	void remove(int index);

	void add(Libro libro);

	void finalizar();

	void set(ArrayList<Libro> cesta);
}
