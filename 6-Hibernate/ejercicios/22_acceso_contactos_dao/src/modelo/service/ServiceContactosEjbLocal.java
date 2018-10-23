package modelo.service;

import java.util.List;

import javax.ejb.Local;

import entidades.Contacto;

@Local
public interface ServiceContactosEjbLocal {

	void guardarContacto(String nombre, String email, int edad);

	void eliminarContacto(int id);

	void eliminarContacto(String nombre);

	List<Contacto> listarContactos();

}