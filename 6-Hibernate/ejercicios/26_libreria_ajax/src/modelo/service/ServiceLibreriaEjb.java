package modelo.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import entidades.Cliente;
import entidades.Historico;
import entidades.Libro;
import entidades.Tema;
import entidades.Venta;
import modelo.dao.DaoClientesEjbLocal;
import modelo.dao.DaoHistoricoEjbLocal;
import modelo.dao.DaoLibrosEjbLocal;
import modelo.dao.DaoTemasEjbLocal;
import modelo.dao.DaoVentasEjbLocal;

/**
 * Session Bean implementation class ServiceLibreriaEjbLocal
 */
@Stateless
@LocalBean
public class ServiceLibreriaEjb implements ServiceLibreriaEjbLocal {
	@EJB
	DaoTemasEjbLocal daoTemas;
	
	@EJB
	DaoLibrosEjbLocal daoLibros;
	
	@EJB
	DaoClientesEjbLocal daoClientes;
		
	@EJB
	DaoVentasEjbLocal daoVentas;
	
	@EJB
	DaoHistoricoEjbLocal daoHistorico;
	
	@Override
	public List<Tema> obtenerTemas() {
		return daoTemas.listarTemas();
	}

	@Override
	public List<Libro> obtenerLibros(int idTema) {
		return daoLibros.listarLibros(idTema);
	}

	@Override
	public Libro obtenerLibro(int isbn) {
		return daoLibros.getLibro(isbn);
	}

	@Override
	public int recuperarCliente(String user, String pwd) {
		return daoClientes.autenticarCliente(user, pwd);
	}

	@Override
	public boolean altacliente(String usuario, String pwd, String email, int telefono) {
		Cliente c = new Cliente(0, email, pwd, telefono, usuario, null, null);
		return (daoClientes.registrarCliente(c) != 0);
	}

	@Override
	public void registrarVenta(int idCliente, List<Libro> libros) {
		double cant=0;
		Cliente c = daoClientes.buscar(idCliente);
		for (Libro libro : libros) {
			daoVentas.crearVentas(idCliente, libros);
			cant += libro.getPrecio();
		}
		
		daoHistorico.crearHistorico(new Historico(0, cant, c));
		
	}

}
