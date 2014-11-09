package tpo.clientes.auditoria.jms;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import tpo.despacho.entidades.Cola;
import tpo.despacho.entidades.LogisticaYMonitoreo;
import tpo.ia.vos.VOInformeAuditoria;

public class ClienteLogisticaEnviarInformeJMS 
{
	public static void main(String[] args) {
		LogisticaYMonitoreo l = new LogisticaYMonitoreo();
		Cola cola = new Cola();
		l.setIp("172.16.164.32");
		cola.setNombre("colaInformeAuditoria");
		cola.setUsuario("jmsuser");
		cola.setPassword("cristian10!");
		l.setColaInformes(cola);
		VOInformeAuditoria informe = new VOInformeAuditoria();
		informe.setDescripcion("Prueba JMS Despacho1");
		informe.setFechaYHora(2);
		enviarInformeAsync(l, informe);
	}

	private static boolean enviarInformeAsync(LogisticaYMonitoreo logisticaYMonitoreo, VOInformeAuditoria informe) {
    	try {
        		final Properties env = new Properties();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
				env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "remote://172.16.164.32:4447"));
				env.put(Context.SECURITY_PRINCIPAL, "jmsuser");
				env.put(Context.SECURITY_CREDENTIALS, "cristian10!");
				
				Context context = new InitialContext(env);
					  
				// buscar la Connection Factory en JNDI
				String connectionFactoryString = System.getProperty("connection.factory", "jms/RemoteConnectionFactory");
				ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
				// buscar la Cola en JNDI
				String destinationString = System.getProperty("destination", "jms/queue/" + logisticaYMonitoreo.getColaInformes().getNombre());
				Destination  destination = (Destination) context.lookup(destinationString);
				// crear la connection y la session a partir de la connection
				Connection connection = connectionFactory.createConnection(System.getProperty("username", logisticaYMonitoreo.getColaInformes().getUsuario()), System.getProperty("password", logisticaYMonitoreo.getColaInformes().getPassword()));
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				connection.start();
				// crear un producer para enviar mensajes usando la session
				MessageProducer producer = session.createProducer(destination);
				 
				// Crear un mensaje de tipo Object Message y setearle el contenido
				ObjectMessage message = session.createObjectMessage();
				message.setObject(informe);
				
				// Enviar el mensaje
				producer.send(message);	
				
				// TODO: recordar cerrar la session y la connection en un bloque “finally”
				connection.close();
				return true;
        	
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}
}
