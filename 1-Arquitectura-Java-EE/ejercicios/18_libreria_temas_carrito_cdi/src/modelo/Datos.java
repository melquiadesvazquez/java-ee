package modelo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Datos {
	static DataSource ds;
	static {
		try {
			Context ct = new InitialContext();
			ds=(DataSource)ct.lookup("java:comp/env/aliaslibros");
		} 
		catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	
	static Connection obtenerConexion() {
		Connection cn = null;
		try {
			cn = ds.getConnection();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return cn;
	}
}
