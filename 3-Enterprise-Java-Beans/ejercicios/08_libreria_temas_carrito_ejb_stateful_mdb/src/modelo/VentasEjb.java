package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import javabeans.Libro;

@Stateless
@LocalBean
public class VentasEjb implements VentasEjbLocal {
	@Resource(mappedName = "java:comp/env/aliaslibros", type = javax.sql.DataSource.class)
	DataSource ds;

	@Override
	public List<Integer> crearVentas(int idCliente, List<Libro> cesta) {
		List<Integer> result = new ArrayList<Integer>();
		try (Connection cn = ds.getConnection();) {			
			String sql = "Insert into ventas(idCliente, idLibro, fecha) values (?, ?, now())";
			
			PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						
			for (Libro libro : cesta) {
				ps.setInt(1, idCliente);
				ps.setInt(2, libro.getIsbn());
				ps.addBatch();
	        }
								
			ps.executeBatch();
			
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()){
				result.add(rs.getInt(1));
			}
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println(result);
		return result;
	}

}
