package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabeans.Libro;
import javabeans.Tema;

public class DaoTemas {
		
	public List<Tema> listarTemas() {
		List<Tema> result = new ArrayList<Tema>();
		try (Connection cn = Datos.obtenerConexion();) {			
			String sql = "Select * from temas";
			
			PreparedStatement ps = cn.prepareStatement(sql);  	
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Tema l = new Tema(rs.getInt("idTema"), rs.getString("tema"));
				result.add(l);		        
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		//System.out.println(result);
		return result;
	}
}
