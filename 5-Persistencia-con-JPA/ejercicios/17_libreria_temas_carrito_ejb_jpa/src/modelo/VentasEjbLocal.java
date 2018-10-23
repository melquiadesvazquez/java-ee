package modelo;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entidades.Cliente;
import entidades.Libro;

@Local
public interface VentasEjbLocal {

	int crearVentas(int idCliente, List<Libro> cesta);

	void crearHistorico(int idCliente, int cantidad);

	List<Libro> getLibrosByCliente(int idCliente);

	List<Cliente> getClientesByDate(Date fecha);

}
