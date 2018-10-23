package ejbs;

import java.util.List;

import javax.ejb.Local;

import javabeans.Libro;

@Local
public interface VentasEjbLocal {

	int crearVentas(int idCliente, List<Libro> cesta);

	void crearHistorico(int idCliente, int cantidad);
}
