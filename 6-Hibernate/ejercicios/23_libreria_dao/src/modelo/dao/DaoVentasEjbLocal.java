package modelo.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entidades.Cliente;
import entidades.Libro;

@Local
public interface DaoVentasEjbLocal {

	int crearVentas(int idCliente, List<Libro> cesta);

	List<Libro> getLibrosByCliente(int idCliente);

	List<Cliente> getClientesByDate(Date fecha);

}
