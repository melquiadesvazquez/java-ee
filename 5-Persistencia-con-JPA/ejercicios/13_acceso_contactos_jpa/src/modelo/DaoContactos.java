package modelo;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entidades.Contacto;

@Named("IDaoContactos")
@RequestScoped
public class DaoContactos implements IDaoContactos {
	static EntityManager em;
	static {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("13_acceso_contactos_jpa");
		em = factory.createEntityManager();
	}
		
	/*
	static DataSource ds;
	static {
		try {
			Context ct = new InitialContext();
			ds=(DataSource)ct.lookup("java:comp/env/aliasagenda");
		} 
		catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	
	private Connection obtenerConexion() {
		Connection cn = null;
		try {
			cn = ds.getConnection();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return cn;
	}
	*/
	
	@Override
	public void guardarContacto(String nombre, String email, int edad) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Contacto con = new Contacto(0, edad, email, nombre);
		em.persist(con);
		tx.commit();
		
		/*
		try (Connection cn=obtenerConexion();) {		
			String sql = "Insert into contactos(nombre, email, edad) values (?, ?, ?)";

			// crear PreparedStatement
			PreparedStatement ps = cn.prepareStatement(sql);
			
			// establecer valores de parámetros, empieza en 1, no en 0
			ps.setString(1,  nombre);
			ps.setString(2,  email);
			ps.setInt(3,  edad);
			
			// ejecutamos la instruccion
			ps.execute();			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		*/
	}
	
	@Override
	public void eliminarContacto(int id) {
		Contacto con = em.find(Contacto.class, id);
		if (con != null) {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.remove(con);
			tx.commit();
		}
		
		/*
		try (Connection cn=obtenerConexion();) {			
			String sql = "Delete from contactos where idContacto=?";
			
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1,  id);			
			ps.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		*/
	}
	
	@Override
	public void eliminarContacto(String nombre) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		TypedQuery<Contacto> qr = em.createQuery("Delete from Contacto c where c.nombre='" + nombre + "'", Contacto.class);
		qr.executeUpdate();
		tx.commit();
		
		//TypedQuery<Contacto> qr = em.createQuery("Select c from Contacto c where c.nombre='" + nombre + "'", Contacto.class);
		//Contacto con = qr.getSingleResult();
		//em.remove(con);
		
		/*
		try (Connection cn=obtenerConexion();) {			
			String sql = "Delete from contactos where nombre=?";
			
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1,  nombre);			
			ps.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		*/
	}
	
	@Override
	public List<Contacto> listarContactos() {
		TypedQuery<Contacto> qr = em.createQuery("Select c from Contacto c", Contacto.class);
		return qr.getResultList();
		
		/*
		List<Contacto> result = new ArrayList<Contacto>();
		try (Connection cn=obtenerConexion();) {			
			String sql = "Select * from contactos";
			
			PreparedStatement ps = cn.prepareStatement(sql);  	
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {				
				Contacto c = new Contacto(rs.getInt("idContacto"), rs.getString("nombre"), rs.getString("email"), rs.getInt("edad"));
				result.add(c);		        
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
		*/
	}
}
