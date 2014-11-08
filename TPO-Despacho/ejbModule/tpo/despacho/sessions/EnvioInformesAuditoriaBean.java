package tpo.despacho.sessions;

import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tpo.despacho.entidades.LogisticaYMonitoreo;
import tpo.ia.vos.VOInformeAuditoria;

@Stateless
public class EnvioInformesAuditoriaBean implements EnvioInformesAuditoria {

	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
    public EnvioInformesAuditoriaBean() {
    }

	public boolean EnviarInforme(VOInformeAuditoria informe) {
		try{
			// Obtengo los módulos de Logistica y Monitoreo
			String query = "SELECT lym FROM LogisticaYMonitoreo lym";
	    	List<LogisticaYMonitoreo> modulos = (List<LogisticaYMonitoreo>)manager.createQuery(query, LogisticaYMonitoreo.class).getResultList();
	    	// Por cada módulo
	    	for(LogisticaYMonitoreo lym : modulos){
	    		// Sincrónico: JMS
	    		if(lym.isInformeAsincronico()){
	    			return enviarInformeAsync(lym, informe);
	    		}
	    		// Asincrónico: WebService
	    		else{
	    			return enviarInformeSync(lym, informe);
	    		}
	    			
	    	}
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean enviarInformeAsync(LogisticaYMonitoreo logisticaYMonitoreo, VOInformeAuditoria informe) {
    	try {
        		final Properties env = new Properties();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
				env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "remote://" + logisticaYMonitoreo.getIp() +":4447"));
				env.put(Context.SECURITY_PRINCIPAL, logisticaYMonitoreo.getColaInformes().getUsuario());
				env.put(Context.SECURITY_CREDENTIALS, logisticaYMonitoreo.getColaInformes().getPassword());
				
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
	
	private boolean enviarInformeSync(LogisticaYMonitoreo logisticaYMonitoreo, VOInformeAuditoria informe) {
    	try {
        		// ACA LLAMAR AL WEB SERVICE
				return true;
        	
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}

}
