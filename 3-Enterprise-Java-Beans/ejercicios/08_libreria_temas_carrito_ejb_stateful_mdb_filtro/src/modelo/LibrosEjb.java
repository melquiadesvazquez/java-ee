package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import javabeans.Libro;

@Stateless
@LocalBean
public class LibrosEjb implements LibrosEjbLocal {
	@Resource(mappedName = "java:comp/env/aliaslibros", type = javax.sql.DataSource.class)
	DataSource ds;
	
	@Override
	public Libro getLibro(int id) {
		Libro result = null;
		try (Connection cn = ds.getConnection();) {			
			String sql = "Select * from libros where isbn=?";
			
			PreparedStatement ps = cn.prepareStatement(sql); 
			ps.setInt(1,  id);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				result = new Libro(rs.getInt("isbn"), rs.getString("titulo"), rs.getString("autor"), rs.getDouble("precio"), rs.getInt("paginas"), rs.getInt("idTema"));		        
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		//System.out.println(result);
		return result;
	}
	
	@Override
	public List<Libro> listarLibros() {
		List<Libro> result = new ArrayList<Libro>();
		try (Connection cn = ds.getConnection();) {			
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
		//System.out.println(result);
		return result;
	}
	
	@Override
	public List<Libro> listarLibros(String tema) {
		List<Libro> result = new ArrayList<Libro>();
		try (Connection cn = ds.getConnection();) {			
			String sql = "Select * from libros where idTema = ?";
			
			PreparedStatement ps = cn.prepareStatement(sql);  
			ps.setString(1,  tema);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Libro l = new Libro(rs.getInt("isbn"), rs.getString("titulo"), rs.getString("autor"), rs.getDouble("precio"), rs.getInt("paginas"), rs.getInt("idTema"));
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
