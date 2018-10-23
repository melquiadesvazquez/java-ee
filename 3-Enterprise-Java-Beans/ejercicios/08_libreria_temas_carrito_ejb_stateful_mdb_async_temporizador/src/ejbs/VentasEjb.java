package ejbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.sql.DataSource;

import javabeans.Libro;

@Stateless
@LocalBean
public class VentasEjb implements VentasEjbLocal {
	@Resource(mappedName = "java:comp/env/aliaslibros", type = javax.sql.DataSource.class)
	DataSource ds;
	
	@Resource
	SessionContext sc;
	
	Timer tm;

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
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Timeout
	void contarVentas(Timer t) {
		int result = 0;
		try (Connection cn = ds.getConnection();) {
			String sql = "Update resumen set totalUnidades = (Select count(idVenta) from ventas)";
			PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);						
			ps.execute();
			
			ps = cn.prepareStatement("Select totalUnidades from resumen");						
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {				
				result = rs.getInt("totalUnidades");	        
			}
						
			System.out.println("++++++++++++++");
			System.out.println((String)t.getInfo() + result);
			System.out.println("++++++++++++++");
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
    }
	
	public void empezarAContarVentas(long retardo, long periodo, String adicional) {
		if (tm == null) {
			tm = sc.getTimerService().createTimer(retardo, periodo, adicional);
			System.out.println("++++++++++++++");
			System.out.println("Inicio de temporizador");
			System.out.println("++++++++++++++");
		}
	}
	
	public void dejarDeContarVentas() {
		if (tm != null) {
			tm.cancel();
			tm = null;
			System.out.println("++++++++++++++");
			System.out.println("Cancelacion de temporizador");
			System.out.println("++++++++++++++");
		}
	}

}
