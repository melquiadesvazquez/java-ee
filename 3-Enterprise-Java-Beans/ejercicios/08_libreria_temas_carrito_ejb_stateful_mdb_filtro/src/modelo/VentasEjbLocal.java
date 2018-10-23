package modelo;

import java.util.List;

import javax.ejb.Local;

import javabeans.Libro;

@Local
public interface VentasEjbLocal {

	List<Integer> crearVentas(int idCliente, List<Libro> cesta);

}
