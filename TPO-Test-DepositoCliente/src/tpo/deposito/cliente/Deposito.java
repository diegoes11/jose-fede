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
		 
		 // CREO EL ARTICULO nº1 y lo envio
//		 FichaTecnicaVO ft1 = new FichaTecnicaVO();
//		 ft1.setId(1);
//		 ft1.setAtributo("Tipo");
//		 ft1.setDetalle("Split");
//		 FichaTecnicaVO ft2 = new FichaTecnicaVO();
//		 ft2.setId(2);
//		 ft2.setAtributo("Color");
//		 ft2.setDetalle("Blanco");
//		 FichaTecnicaVO ft3 = new FichaTecnicaVO();
//		 ft3.setId(3);
//		 ft3.setAtributo("Modo");
//		 ft3.setDetalle("Frio/Calor");
//		 List<FichaTecnicaVO> fichas = new ArrayList<FichaTecnicaVO>();
//		 fichas.add(ft1);
//		 fichas.add(ft2);
//		 fichas.add(ft3);
//		 ArticuloVO art = new ArticuloVO();
//		 art.setCodigo(1351953);
//		 art.setDeposito("DEPOSITO1");
//		 art.setTipo("Electrodomesticos");
//		 art.setDescripcion("4 modos de operación diferentes: Frío, deshumidificación, ventilación y calor");
//		 art.setEstado("Estado");
//		 art.setFichasTecnicas(fichas);
//		 art.setMarca("Whirlpool");
//		 art.setNombre("Aire Acondicionado Split WBC 12B-13B 2645 F/C");
//		 art.setNombreFoto("SplitFoto");
//		 art.setOrigen("Argentina");
//		 art.setPrecio(4633f);
//		 art.setStock(7);
//		 
//		ObjectMessage message = session.createObjectMessage();
//		message.setObject(art);
//		
//		producer.send(message);
		
		/***************/
		
		 // CREO EL ARTICULO nº2 y lo envio
//		 FichaTecnicaVO ft4 = new FichaTecnicaVO();
//		 ft4.setId(4);
//		 ft4.setAtributo("Capacidad");
//		 ft4.setDetalle("202 Lt");
//		 List<FichaTecnicaVO> fichas2 = new ArrayList<FichaTecnicaVO>();
//		 fichas2.add(ft4);
//		 ArticuloVO art2 = new ArticuloVO();
//		 art2.setCodigo(1739701);
//		 art2.setDeposito("DEPOSITO1");
//		 art2.setTipo("Electrodomesticos");
//		 art2.setDescripcion("202 Lt");
//		 art2.setEstado("Estado");
//		 art2.setFichasTecnicas(fichas2);
//		 art2.setMarca("Gafa");
//		 art2.setNombre("Freezer Horizontal Eternity M210");
//		 art2.setNombreFoto("FreezerFoto");
//		 art2.setOrigen("Argentina");
//		 art2.setPrecio(2855f);
//		 art2.setStock(4);
//		 
//		ObjectMessage message2 = session.createObjectMessage();
//		message2.setObject(art2);
//		
//		producer.send(message2);
		
		/***************/
		
		 // CREO EL ARTICULO nº3 y lo envio
		 FichaTecnicaVO ft5 = new FichaTecnicaVO();
		 ft5.setId(5);
		 ft5.setAtributo("Color");
		 ft5.setDetalle("Celeste");
		 FichaTecnicaVO ft6 = new FichaTecnicaVO();
		 ft6.setId(6);
		 ft6.setAtributo("Talle");
		 ft6.setDetalle("M");
		 List<FichaTecnicaVO> fichas3 = new ArrayList<FichaTecnicaVO>();
		 fichas3.add(ft5);
		 fichas3.add(ft6);
		 ArticuloVO art3 = new ArticuloVO();
		 art3.setCodigo(3122);
		 art3.setDeposito("DEPOSITO1");
		 art3.setTipo("Moda");
		 art3.setDescripcion("Musculosa Basement con Breteles");
		 art3.setEstado("Estado");
		 art3.setFichasTecnicas(fichas3);
		 art3.setMarca("Basement");
		 art3.setNombre("Musculosa Pale");
		 art3.setNombreFoto("RemeraFoto");
		 art3.setOrigen("Brasil");
		 art3.setPrecio(79f);
		 art3.setStock(9);
		 
		ObjectMessage message3 = session.createObjectMessage();
		message3.setObject(art3);
		
		producer.send(message3);
		
		 // CREO EL ARTICULO nº4 y lo envio
		 FichaTecnicaVO ft7 = new FichaTecnicaVO();
		 ft7.setId(7);
		 ft7.setAtributo("Color");
		 ft7.setDetalle("Negro");
		 FichaTecnicaVO ft8 = new FichaTecnicaVO();
		 ft8.setId(8);
		 ft8.setAtributo("Talle");
		 ft8.setDetalle("Largo del reloj: 2");
		 List<FichaTecnicaVO> fichas4 = new ArrayList<FichaTecnicaVO>();
		 fichas4.add(ft7);
		 fichas4.add(ft8);
		 ArticuloVO art4 = new ArticuloVO();
		 art4.setCodigo(1741046);
		 art4.setDeposito("DEPOSITO1");
		 art4.setTipo("Moda");
		 art4.setDescripcion("Modelo: MZ-24");
		 art4.setEstado("Estado");
		 art4.setFichasTecnicas(fichas4);
		 art4.setMarca("Montreal");
		 art4.setNombre("Reloj Negro Hombre");
		 art4.setNombreFoto("RemeraFoto");
		 art4.setOrigen("Argentina");
		 art4.setPrecio(345f);
		 art4.setStock(9);
		 
		ObjectMessage message4 = session.createObjectMessage();
		message4.setObject(art4);
		
		producer.send(message4);
		
		
		// TODO: recordar cerrar la session y la connection en un bloque “finally”
		System.out.print("Envió el mensaje...");
		connection.close();

		} catch (Exception e) {
			System.out.println("Error al efectuar pedido: " + e);
			e.printStackTrace();
		}

	}
}
