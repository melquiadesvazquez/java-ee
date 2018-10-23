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

import javabeans.Tema;

@Stateless
@LocalBean
public class TemasEjb implements TemasEjbLocal {
	@Resource(mappedName = "java:comp/env/aliaslibros", type = javax.sql.DataSource.class)
	DataSource ds;

	@Override
	public List<Tema> listarTemas() {
		List<Tema> result = new ArrayList<Tema>();
		try (Connection cn = ds.getConnection();) {			
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
