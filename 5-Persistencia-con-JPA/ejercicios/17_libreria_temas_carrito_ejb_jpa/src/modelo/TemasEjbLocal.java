package modelo;

import java.util.List;

import javax.ejb.Local;

import entidades.Tema;

@Local
public interface TemasEjbLocal {

	List<Tema> listarTemas();

}
