package modelo;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import entidades.Contactos;
import modelo.utilidades.HibernateUtil;

@Stateless
public class ContactosEjb implements ContactosEjbLocal {
	@Override
	public void guardarContacto(String nombre, String email, int edad) {
		Contactos con = new Contactos(0, nombre, email, edad);
		Transaction tx = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			// Abrimos la transaccion
			tx = s.beginTransaction();
			s.save(con);
			// Cerramos la transaccion
			tx.commit();
			// No hace falta cerrar la sesion porque es un try con recursos
			// s.close();
		}
		catch(Exception ex) {
			if (tx!=null) {
				tx.rollback();
			}
			ex.printStackTrace();
		}		
	}
	
	@Override
	public void eliminarContacto(int id) {
		Contactos con = null;
		Transaction tx = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			con = s.get(Contactos.class, id);
			tx = s.beginTransaction();
			if (con != null) {
				s.delete(con);
			}
			tx.commit();
		}
		catch(Exception ex) {
			if (tx!=null) {
				tx.rollback();
			}
			ex.printStackTrace();
		}
	}
	
	@Override
	public void eliminarContacto(String nombre) {
		Transaction tx = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {			
			Query q = s.createQuery("Delete from Contacto c where c.nombre=?");
			q.setParameter(0, nombre);
			tx = s.beginTransaction();
			q.executeUpdate();
			tx.commit();
		}
		catch(Exception ex) {
			if (tx!=null) {
				tx.rollback();
			}
			ex.printStackTrace();
		}
	}
	
	@Override
	public List<Contactos> listarContactos() {
		List<Contactos> resultado = null;
		try (Session s = HibernateUtil.getSessionFactory().openSession()) {
			Query<Contactos> q = s.createQuery("Select c from Contactos c", Contactos.class);
			resultado = q.list();
		}
		return resultado;
	}
}
