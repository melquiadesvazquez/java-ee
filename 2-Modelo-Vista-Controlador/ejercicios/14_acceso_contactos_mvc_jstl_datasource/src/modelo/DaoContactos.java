package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.Contacto;

public class DaoContactos {
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
	
	public void guardarContacto(String nombre, String email, int edad) {
		//try {
		// Todas las variables que tienen un metodo close se cierran al acabar el try 
		// Utilizamos un try con recursos
		try (Connection cn=obtenerConexion();) {
			// cargar el driver
			//Class.forName("com.mysql.jdbc.Driver");
			// lo ponemos en un block static
			
			// crear una conexion con la base de datos
			//Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "root");
			// lo ponemos en una function
			
			String sql = "Insert into contactos(nombre, email, edad) values (?, ?, ?)";
			
			// crear PreparedStatement
			PreparedStatement ps = cn.prepareStatement(sql);
			
			// establecer valores de parámetros, empieza en 1, no en 0
			ps.setString(1,  nombre);
			ps.setString(2,  email);
			ps.setInt(3,  edad);
			
			// ejecutamos la instruccion
			ps.execute();
			
			// cerrar conexion con base de datos
			//cn.close();
			// la cerramos en el try con recursos al acabar
		}
		//catch(ClassNotFoundException|SQLException ex) {
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void eliminarContacto(int id) {
		try (Connection cn=obtenerConexion();) {			
			String sql = "Delete from contactos where idContacto=?";
			
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1,  id);			
			ps.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void eliminarContacto(String nombre) {
		try (Connection cn=obtenerConexion();) {			
			String sql = "Delete from contactos where nombre=?";
			
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setString(1,  nombre);			
			ps.execute();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Contacto> listarContactos() {
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
	}
}
