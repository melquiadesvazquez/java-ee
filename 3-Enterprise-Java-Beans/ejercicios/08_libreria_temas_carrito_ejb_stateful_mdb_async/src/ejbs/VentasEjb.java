package ejbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
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
	public int crearVentas(int idCliente, List<Libro> cesta) {
		int result = 0;
		try (Connection cn = ds.getConnection();) {			
			String sql = "Insert into ventas(idCliente, idLibro, fecha) values (?, ?, now())";
			PreparedStatement ps = cn.prepareStatement(sql);
						
			for (Libro libro : cesta) {
				ps.setInt(1, idCliente);
				ps.setInt(2, libro.getIsbn());
				ps.addBatch();
				result += libro.getPrecio();
	        }
								
			ps.executeBatch();
			
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	@Asynchronous
	public void crearHistorico(int idCliente, int cantidad) {		
		try (Connection cn = ds.getConnection();) {			
			String sql = "Insert into historico(idCliente, cantidad) values (?, ?)";
			
			PreparedStatement ps = cn.prepareStatement(sql);
			ps.setInt(1, idCliente);
			ps.setInt(2, cantidad);
			ps.execute();
			
			try {
				Thread.sleep(10000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("++++++++++++++");
			System.out.println("1");
			System.out.println("++++++++++++++");
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
