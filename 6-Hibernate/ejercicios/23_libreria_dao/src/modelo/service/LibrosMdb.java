package modelo.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import modelo.dao.DaoVentasEjbLocal;
import modelo.dao.Pedido;

@MessageDriven(
activationConfig = { 
	@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/libroscola"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})

public class LibrosMdb implements MessageListener {
	@EJB
	DaoVentasEjbLocal dVentas;
	
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message; 
				
		try {			
			List<Venta> ventas = (List<Venta>)objectMessage.getObject();
			for (Venta v: ventas) {
				dVentas.crearVentas(idCliente, cesta)
			}
			
			
			
			Pedido pedido = (Pedido)objectMessage.getObject();							
			int cantidad = dVentas.crearVentas(pedido.getIdCliente(), pedido.getCesta());
			
			dVentas.crearHistorico(pedido.getIdCliente(), cantidad);				
			
			System.out.println("++++++++++++++");
			System.out.println("Venta realizada");
			System.out.println(cantidad);
			System.out.println("++++++++++++++");						
		} 
		catch (JMSException e) {
			e.printStackTrace();
		}

    }

}
