package modelo.dao;

import java.util.List;

import javax.ejb.Local;

import entidades.Tema;
@Local
public interface DaoTemasEjbLocal {

	List<Tema> obtenerTemas();

}