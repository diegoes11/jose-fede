package tpo.deposito.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import tpo.despacho.vos.ArticuloVO;
import tpo.despacho.vos.FichaTecnicaVO;

public class Deposito {
	public static void main(String[] args) {
		altaArticulo();
	} 

	private static void altaArticulo() {

		final Properties env = new Properties();
		  env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		  env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "remote://localhost:4447"));

		  try {
		
		Context context = new InitialContext(env);
			  
		// buscar la Connection Factory en JNDI
		String connectionFactoryString = System.getProperty("connection.factory", "jms/RemoteConnectionFactory");
		ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
		// buscar la Cola en JNDI
		String destinationString = System.getProperty("destination", "jms/queue/Articulos");
		Destination  destination = (Destination) context.lookup(destinationString);
		// crear la connection y la session a partir de la connection
		Connection connection = connectionFactory.createConnection(System.getProperty("username", "test2"), System.getProperty("password", "test1234."));
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();
		// crear un producer para enviar mensajes usando la session
		 MessageProducer producer = session.createProducer(destination);
		 
		 // CREO EL ARTICULO
		 FichaTecnicaVO ft1 = new FichaTecnicaVO();
		 ft1.setId(1);
		 ft1.setDetalle("Detalle1");
		 ft1.setAtributo("Atributo1");
		 
		 FichaTecnicaVO ft2 = new FichaTecnicaVO();
		 ft2.setId(2);
		 ft2.setDetalle("Detalle2");
		 ft2.setAtributo("Atributo2");
		 
		 List<FichaTecnicaVO> fichas = new ArrayList<FichaTecnicaVO>();
		 fichas.add(ft1);
		 fichas.add(ft2);
		 
		 ArticuloVO art = new ArticuloVO();
		 art.setCodigo(1);
		 art.setDeposito("Deposito1");
		 art.setDescripcion("Desc");
		 art.setEstado("Est");
		 art.setFichasTecnicas(fichas);
		 art.setMarca("Marca");
		 art.setNombre("Nom");
		 art.setNombreFoto("NomFoto");
		 art.setOrigen("Origen");
		 art.setPrecio(123f);
		 art.setStock(2);
		 art.setTipo("Tipo");
		 
		// crear un mensaje de tipo text y setearle el contenido
		ObjectMessage message = session.createObjectMessage();
		message.setObject(art);
		
		// enviar el mensaje
		producer.send(message);
		
		
		// TODO: recordar cerrar la session y la connection en un bloque “finally”
		System.out.print("Envió el mensaje...");
		connection.close();

		} catch (Exception e) {
			System.out.println("Error al efectuar pedido: " + e);
			e.printStackTrace();
		}

	}
}
