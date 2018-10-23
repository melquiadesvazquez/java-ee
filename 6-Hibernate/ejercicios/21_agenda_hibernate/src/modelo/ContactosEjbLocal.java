package modelo;

import java.util.List;

import javax.ejb.Local;

import entidades.Contactos;

@Local
public interface ContactosEjbLocal {

	void guardarContacto(String nombre, String email, int edad);

	void eliminarContacto(int id);

	void eliminarContacto(String nombre);

	List<Contactos> listarContactos();

}