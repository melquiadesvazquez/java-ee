package servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestMdb")
public class TestMdb extends HttpServlet {
	
	@Resource(mappedName="jms/ejemplofactory", type=ConnectionFactory.class)
	ConnectionFactory cf;
	
	@Resource(mappedName="jms/ejemplocola", type=Queue.class)
	Queue cola;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(Connection cn=cf.createConnection()) {
			// establecemos una sesion
			Session s = cn.createSession();
			
			// creamos un productor de mensajes asociado a la cola
			MessageProducer mp = s.createProducer(cola);
			
			// creamos mensaje y lo enviamos
			TextMessage tm = s.createTextMessage("profe");
			
			// enviamos el mensaje
			mp.send(tm);
		}
		catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
