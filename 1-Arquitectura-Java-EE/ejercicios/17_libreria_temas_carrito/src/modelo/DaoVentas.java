package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javabeans.Libro;
import javabeans.Tema;

public class DaoVentas {
		
	public ArrayList<Integer> crearVentas(int idCliente, ArrayList<Libro> cesta) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		try (Connection cn = Datos.obtenerConexion();) {			
			String sql = "Insert into ventas(idCliente, idLibro, fecha) values (?, ?, now())";
			
			PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  	
						
			for (Libro libro : cesta) {
				ps.setInt(1, idCliente);
				ps.setInt(2, libro.getIsbn());
				ps.addBatch();
				ps.executeBatch();
	        }
					
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			while (rs.next()){
				result.add(rs.getInt(1));
			}

		
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		//System.out.println(result);
		return result;
	}
}
