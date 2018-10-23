package ejbs;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remove;

import javabeans.Libro;

@Local
public interface CarritoEjbLocal {

	List<Libro> get();

	void remove(int index);

	void add(Libro libro);

	void finalizar();

	void set(List<Libro> cesta);
}
