package modelo.dao;

import java.util.List;

import javax.ejb.Local;

import entidades.Seccion;

@Local
public interface DaoSeccionesEjbLocal {

	List<Seccion> obtenerSecciones();

}
