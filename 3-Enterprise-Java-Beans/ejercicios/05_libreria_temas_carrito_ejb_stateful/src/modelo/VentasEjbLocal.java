package modelo;

import java.util.ArrayList;

import javax.ejb.Local;

import javabeans.Libro;

@Local
public interface VentasEjbLocal {

	ArrayList<Integer> crearVentas(int idCliente, ArrayList<Libro> cesta);

}
