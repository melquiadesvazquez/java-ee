package ejbs;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Timeout;
import javax.ejb.Timer;

import javabeans.Libro;

@Local
public interface VentasEjbLocal {

	int crearVentas(int idCliente, List<Libro> cesta);

	void crearHistorico(int idCliente, int cantidad);

	void empezarAContarVentas(long retardo, long periodo, String adicional);
	
	void dejarDeContarVentas();
}
