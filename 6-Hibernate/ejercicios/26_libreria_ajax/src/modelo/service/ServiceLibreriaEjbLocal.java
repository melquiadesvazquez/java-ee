package modelo.service;

import java.util.List;

import javax.ejb.Local;

import entidades.Cliente;
import entidades.Libro;
import entidades.Tema;

@Local
public interface ServiceLibreriaEjbLocal {
	// devuelve la lista de temas
	List<Tema> obtenerTemas();
	
	// devuelve la lista de libros. Si el parámetro es  -1, devuelve todos
	// si no, el conjunto de libros asociado a ese tema
	List<Libro> obtenerLibros(int idTema);
	
	// recuperar un libro a partir de su ISBN
	Libro obtenerLibro(int isbn);
	
	// recuperar un cliente a partir de usuario y contraseña
	// si no existe devuelve null
	int recuperarCliente(String user, String pwd);
	
	// da de alta un cliente si no existe esa combinación de usuario y contraseña
	// pero si ya existe devuelve false
	boolean altacliente(String usuario, String pwd, String email, int telefono);
	
	// registra una venta y tambien en el historico
	void registrarVenta(int idCliente, List<Libro> libros);
}
