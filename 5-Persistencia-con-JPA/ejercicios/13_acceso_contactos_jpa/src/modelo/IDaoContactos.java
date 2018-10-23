package modelo;

import java.util.List;

import entidades.Contacto;

public interface IDaoContactos {

	void guardarContacto(String nombre, String email, int edad);

	void eliminarContacto(int id);

	void eliminarContacto(String nombre);

	List<Contacto> listarContactos();

}