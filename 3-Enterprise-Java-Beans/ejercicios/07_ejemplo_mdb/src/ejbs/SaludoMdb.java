package ejbs;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: SaludoMdb
 */
@MessageDriven(
activationConfig = { 
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/ejemplocola")
})

public class SaludoMdb implements MessageListener {

    public void onMessage(Message message) {
    	TextMessage texto=(TextMessage)message;
    	try {
			System.out.println("Bienvenido al MDB " + texto.getText());
		}
    	catch (JMSException e) {
			e.printStackTrace();
		}
    }

}
