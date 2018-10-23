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
 * Session Bean implementation class ServiceLibreriaEjb
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
		
		return daoTemas.obtenerTemas();
	}

	@Override
	public List<Libro> obtenerLibros(int idTema) {
		if(idTema==0) {
			return daoLibros.recuperarLibros();
		}else {
			return daoLibros.recuperarLibros(idTema);
		}
	}

	@Override
	public Libro obtenerLibro(int isbn) {
		// TODO Auto-generated method stub
		return daoLibros.recuperarLibroPorIsbn(isbn);
	}

	@Override
	public Cliente recuperarCliente(String user, String pwd) {
		
		return daoClientes.autenticar(user, pwd);
	}

	@Override
	public boolean altaCliente(String usuario, String password, String email, int telefono) {
		if(daoClientes.autenticar(usuario, password)!=null) {
			return false;
		}else {
			Cliente c=new Cliente(0,email,password,telefono,usuario,null,null);
			daoClientes.registrar(c);
			return true;
		}
	}

	@Override
	public void registrarVenta(int idCliente, List<Libro> libros) {
		double cant=0;
		Cliente c=daoClientes.buscar(idCliente);
		for(Libro lib:libros) {
			daoVentas.registrar(new Venta(0,new Date(),c,lib));
			cant+=lib.getPrecio();
		}
		
		daoHistorico.guardar(new Historico(0,cant,c));
		
		
		
	}

	@Override
	public void actualizarLibro(Libro lib) {
		if (daoLibros.recuperarLibroPorIsbn(lib.getIsbn()) != null) {
			daoLibros.actualizarLibro(lib);
		}
	}
    

}
