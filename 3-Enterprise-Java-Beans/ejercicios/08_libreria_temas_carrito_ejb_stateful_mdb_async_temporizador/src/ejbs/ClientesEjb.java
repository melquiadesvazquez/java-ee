package ejbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
@LocalBean
public class ClientesEjb implements ClientesEjbLocal {
	@Resource(mappedName = "java:comp/env/aliaslibros", type = javax.sql.DataSource.class)
	DataSource ds;

	@Override
	public int autenticarCliente(String usuario, String password) {
		int result = 0;
		try (Connection cn = ds.getConnection();) {			
			String sql = "Select idCliente from clientes where usuario=? and password=?";
			
			PreparedStatement ps = cn.prepareStatement(sql);			
			ps.setString(1,  usuario);
			ps.setString(2,  password);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {				
				result = rs.getInt("idCliente");	        
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	

	@Override
	public int registrarCliente(String usuario, String password, String email, int telefono) {
		int result = 0;
		try (Connection cn = ds.getConnection();) {
			String sql = "Insert into clientes(usuario, password, email, telefono) values (?, ?, ?, ?)";
			
			PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);			
			ps.setString(1,  usuario);
			ps.setString(2,  password);
			ps.setString(3,  email);
			ps.setInt(4,  telefono);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()){
				result=rs.getInt(1);
			}
			
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		//System.out.println(result);
		return result;
	}

}
