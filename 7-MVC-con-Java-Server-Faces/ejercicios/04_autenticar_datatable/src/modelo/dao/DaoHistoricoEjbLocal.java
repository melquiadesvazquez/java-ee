package modelo.dao;

import javax.ejb.Local;

import entidades.Historico;

@Local
public interface DaoHistoricoEjbLocal {

	void guardar(Historico h);

}
