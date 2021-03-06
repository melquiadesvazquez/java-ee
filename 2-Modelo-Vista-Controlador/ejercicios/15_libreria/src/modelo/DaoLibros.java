package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabeans.Libro;

public class DaoLibros {
		
	public List<Libro> listarLibros() {
		List<Libro> result = new ArrayList<Libro>();
		try (Connection cn = Datos.obtenerConexion();) {			
			String sql = "Select * from libros";
			
			PreparedStatement ps = cn.prepareStatement(sql);  	
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Libro l = new Libro(rs.getInt("isbn"), rs.getString("titulo"), rs.getString("autor"), rs.getDouble("precio"), rs.getInt("paginas"), rs.getInt("idTema"));
				result.add(l);		        
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
}
