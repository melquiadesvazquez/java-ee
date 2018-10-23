package modelo;

import java.util.List;

import javax.ejb.Local;

import javabeans.Tema;

@Local
public interface TemasEjbLocal {

	List<Tema> listarTemas();

}
