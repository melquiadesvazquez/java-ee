package ejbs;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import modelo.Venta;
import modelo.VentasEjbLocal;

@MessageDriven(
activationConfig = { 
	@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/libroscola"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})

public class LibrosMdb implements MessageListener {
	
	@EJB
	VentasEjbLocal dVentas;

	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message; 
		try {						
			Venta venta = (Venta)objectMessage.getObject();				
			List<Integer> ventas = dVentas.crearVentas(venta.getIdCliente(), venta.getCesta());
			
			System.out.println("++++++++++++++");
			System.out.println("Venta realizada");
			System.out.println(ventas);
			System.out.println("++++++++++++++");						
		} 
		catch (JMSException e) {
			e.printStackTrace();
		}

    }

}
