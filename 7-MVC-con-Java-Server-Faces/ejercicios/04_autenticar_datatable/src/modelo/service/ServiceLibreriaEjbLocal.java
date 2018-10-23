package modelo.service;

import java.util.List;

import javax.ejb.Local;

import entidades.Cliente;
import entidades.Libro;
import entidades.Tema;

@Local
public interface ServiceLibreriaEjbLocal {
	//devuelve lista de temas
	List<Tema> obtenerTemas();
	//devuelve lista de libros. Si el par�metro es 0, devuelve todos
	//sino, el conjunto de libros asociado a ese tema
	
	List<Libro> obtenerLibros(int idTema);
	//recupera un libro a partir de su ISBN
	Libro obtenerLibro(int isbn);
	//recupera un cliente a partir de usuario y contrase�a, si no existe, 
	//devuelve null
	Cliente recuperarCliente(String user, String pwd);
	//da de alta un cliente si no existe esa combinaci�n de usuario y contrase�a,
	//pero si ya existe devuelve false
	boolean altaCliente(String usuario, String password, String email, int telefono);
	
	//registra una venta y tambi�n en el historico
	void registrarVenta(int idCliente, List<Libro> libros);
}
