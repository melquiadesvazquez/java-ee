package modelo;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Cliente;
import entidades.Cuenta;

@Stateless
@LocalBean
public class CuentasEjb implements CuentasEjbLocal {
	@PersistenceContext(unitName="cajero_PU")
	EntityManager em;
	
	// obtener todas las cuentas de un cliente
	public List<Cuenta> cuentasCliente(int dni) {
		// No hace falta porque dni es la clave primaria
		// String jpql = "Select c from Cuentas c JOIN Clientes t Where t.dni = ?1 ";
		Cliente c = em.find(Cliente.class, dni);
		return c.getCuentas();
	}
  
	// obtener todos los clientes que tengan cuentas cuyo saldo sea superior a X
	public List<Cliente> obtenerclientesPorSaldo(double saldo) {
		TypedQuery<Cliente> tq = em.createQuery("Select DISTINC(c) from Clientes c JOIN Cuentas t Where t.saldo > ?1", Cliente.class);
		tq.setParameter(1, saldo);
		return tq.getResultList();
	}
}
