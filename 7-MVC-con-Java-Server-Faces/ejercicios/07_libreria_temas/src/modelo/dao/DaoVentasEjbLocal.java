package modelo.dao;

import java.util.List;

import javax.ejb.Local;

import entidades.Venta;

@Local
public interface DaoVentasEjbLocal {

	

	void registrar(Venta v);

}
