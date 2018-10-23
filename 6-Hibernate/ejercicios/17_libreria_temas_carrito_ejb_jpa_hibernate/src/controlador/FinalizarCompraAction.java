package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Libro;
import modelo.Pedido;
import modelo.CarritoEjbLocal;
import modelo.VentasEjbLocal;

@WebServlet("/FinalizarCompraAction")
public class FinalizarCompraAction extends HttpServlet {
	
	@EJB
	VentasEjbLocal dVentas;
	
	@Resource(mappedName="jms/librosfactory", type=ConnectionFactory.class)
	ConnectionFactory cf;
	
	@Resource(mappedName="jms/libroscola", type=Queue.class)
	Queue cola;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		CarritoEjbLocal carrito = (CarritoEjbLocal)request.getSession().getAttribute("carrito");
		
		if (carrito != null) {
			List<Libro> cesta = carrito.get();
			int cliente = (Integer)request.getSession().getAttribute("cliente");	
					        
	        try(Connection cn=cf.createConnection()) {
				Session s = cn.createSession();			
				MessageProducer mp = s.createProducer(cola);
						
				ObjectMessage message = s.createObjectMessage(new Pedido(cliente, cesta));
				mp.send(message);
			
				carrito.set(new ArrayList<Libro>());
			}
			catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
