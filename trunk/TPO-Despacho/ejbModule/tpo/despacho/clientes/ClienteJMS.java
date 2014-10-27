package tpo.despacho.clientes;

import java.io.Serializable;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ClienteJMS {
	Session session;
	MessageProducer producer;
	
	private ClienteJMS(){
		
	}
	
	public boolean setContext(String ip, String cola, String usuario, String password){
		try{
				final Properties env = new Properties();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
				env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "remote://" + ip +":4447"));
				Context context = new InitialContext(env);
				String connectionFactoryString = System.getProperty("connection.factory", "jms/RemoteConnectionFactory");
				ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
				String destinationString = System.getProperty("destination", cola);
				Destination  destination = (Destination) context.lookup(destinationString);
				// crear la connection y la session a partir de la connection
				Connection connection = connectionFactory.createConnection(System.getProperty("username", usuario), System.getProperty("password", password));
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				connection.start();
				// crear un producer para enviar mensajes usando la session
				producer = session.createProducer(destination);
				return true;
			}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean enviarObjectMessage(Serializable objeto){
		try {
			ObjectMessage message = session.createObjectMessage();
			message.setObject(objeto);
			producer.send(message);
			return true;
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void cerrarConexion(){
		try {
			producer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
