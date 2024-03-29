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

import tpo.ia.vos.VOArticulo;
import tpo.ia.vos.VOFichaTecnica;

public class Deposito {
	public static void main(String[] args) {
		altaArticulo();
	}

	private static void altaArticulo() {

		final Properties env = new Properties();
		  env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		  env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "remote://localhost:4447"));
		  //env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "remote://172.16.164.34:4447"));

		  try {
		
		Context context = new InitialContext(env);
			  
		// buscar la Connection Factory en JNDI
		String connectionFactoryString = System.getProperty("connection.factory", "jms/RemoteConnectionFactory");
		ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
		// buscar la Cola en JNDI
		String destinationString = System.getProperty("destination", "jms/queue/Articulos");
		Destination  destination = (Destination) context.lookup(destinationString);
		// crear la connection y la session a partir de la connection
		Connection connection = connectionFactory.createConnection(System.getProperty("username", "despacho"), System.getProperty("password", "despacho1."));
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();
		// crear un producer para enviar mensajes usando la session
		MessageProducer producer = session.createProducer(destination);
		 
		 // CREO EL ARTICULO n�1 y lo envio
		 VOFichaTecnica ft1 = new VOFichaTecnica();
		 ft1.setId(1);
		 ft1.setAtributo("Tipo");
		 ft1.setDetalle("Split");
		 VOFichaTecnica ft2 = new VOFichaTecnica();
		 ft2.setId(2);
		 ft2.setAtributo("Color");
		 ft2.setDetalle("Blanco");
		 VOFichaTecnica ft3 = new VOFichaTecnica();
		 ft3.setId(3);
		 ft3.setAtributo("Modo");
		 ft3.setDetalle("Frio/Calor");
		 List<VOFichaTecnica> fichas = new ArrayList<VOFichaTecnica>();
		 fichas.add(ft1);
		 fichas.add(ft2);
		 fichas.add(ft3);
		 VOArticulo art = new VOArticulo();
		 art.setCodigo(1351953);
		 art.setDeposito("DEPOSITO1");
		 art.setTipo("Electrodomesticos");
		 art.setDescripcion("4 modos de operaci�n diferentes: Fr�o, deshumidificaci�n, ventilaci�n y calor");
		 art.setEstado("Estado");
		 art.setFichasTecnicas(fichas);
		 art.setMarca("Whirlpool");
		 art.setNombre("Aire Acondicionado Split WBC 12B-13B 2645 F/C");
		 art.setNombreFoto("SplitFoto");
		 art.setOrigen("Argentina");
		 art.setPrecio(4633f);
		 art.setStock(7);
		 
		ObjectMessage message = session.createObjectMessage();
		message.setObject(art);
		
		producer.send(message);
		
		/***************/
		
		 // CREO EL ARTICULO n�2 y lo envio
		 VOFichaTecnica ft4 = new VOFichaTecnica();
		 ft4.setId(4);
		 ft4.setAtributo("Capacidad");
		 ft4.setDetalle("202 Lt");
		 List<VOFichaTecnica> fichas2 = new ArrayList<VOFichaTecnica>();
		 fichas2.add(ft4);
		 VOArticulo art2 = new VOArticulo();
		 art2.setCodigo(1739701);
		 art2.setDeposito("DEPOSITO1");
		 art2.setTipo("Electrodomesticos");
		 art2.setDescripcion("202 Lt");
		 art2.setEstado("Estado");
		 art2.setFichasTecnicas(fichas2);
		 art2.setMarca("Gafa");
		 art2.setNombre("Freezer Horizontal Eternity M210");
		 art2.setNombreFoto("FreezerFoto");
		 art2.setOrigen("Argentina");
		 art2.setPrecio(2855f);
		 art2.setStock(4);
		 
		ObjectMessage message2 = session.createObjectMessage();
		message2.setObject(art2);
		
		producer.send(message2);
		
		/***************/
		
		 // CREO EL ARTICULO n�3 y lo envio
		 VOFichaTecnica ft5 = new VOFichaTecnica();
		 ft5.setId(5);
		 ft5.setAtributo("Color");
		 ft5.setDetalle("Celeste");
		 VOFichaTecnica ft6 = new VOFichaTecnica();
		 ft6.setId(6);
		 ft6.setAtributo("Talle");
		 ft6.setDetalle("M");
		 List<VOFichaTecnica> fichas3 = new ArrayList<VOFichaTecnica>();
		 fichas3.add(ft5);
		 fichas3.add(ft6);
		 VOArticulo art3 = new VOArticulo();
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
		
		 // CREO EL ARTICULO n�4 y lo envio
		 VOFichaTecnica ft7 = new VOFichaTecnica();
		 ft7.setId(7);
		 ft7.setAtributo("Color");
		 ft7.setDetalle("Negro");
		 VOFichaTecnica ft8 = new VOFichaTecnica();
		 ft8.setId(8);
		 ft8.setAtributo("Talle");
		 ft8.setDetalle("Largo del reloj: 2");
		 List<VOFichaTecnica> fichas4 = new ArrayList<VOFichaTecnica>();
		 fichas4.add(ft7);
		 fichas4.add(ft8);
		 VOArticulo art4 = new VOArticulo();
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
		
		/***************/
		
		 // CREO EL ARTICULO n�5 y lo envio
		 VOFichaTecnica ft9 = new VOFichaTecnica();
		 ft9.setId(9);
		 ft9.setAtributo("Material");
		 ft9.setDetalle("MDF");
		 List<VOFichaTecnica> fichas5 = new ArrayList<VOFichaTecnica>();
		 fichas5.add(ft9);
		 VOArticulo art5 = new VOArticulo();
		 art5.setCodigo(1857363);
		 art5.setDeposito("DEPOSITO1");
		 art5.setTipo("Muebles");
		 art5.setDescripcion("Mesa para Tv");
		 art5.setEstado("Estado");
		 art5.setFichasTecnicas(fichas5);
		 art5.setMarca("Mica");
		 art5.setNombre("Mesa para TV 21' wengue");
		 art5.setNombreFoto("MesaFoto");
		 art5.setOrigen("Argentina");
		 art5.setPrecio(409f);
		 art5.setStock(2);
		 
		ObjectMessage message5 = session.createObjectMessage();
		message5.setObject(art5);
		
		producer.send(message5);
		
		 // CREO EL ARTICULO n�6 y lo envio
		 VOFichaTecnica ft10 = new VOFichaTecnica();
		 ft10.setId(10);
		 ft10.setAtributo("Material");
		 ft10.setDetalle("Rattan de Pvc");
		 List<VOFichaTecnica> fichas6 = new ArrayList<VOFichaTecnica>();
		 fichas6.add(ft10);
		 VOArticulo art6 = new VOArticulo();
		 art6.setCodigo(1607594);
		 art6.setDeposito("DEPOSITO1");
		 art6.setTipo("Muebles");
		 art6.setDescripcion("2 Sofa 1 Cuerpo");
		 art6.setEstado("Estado");
		 art6.setFichasTecnicas(fichas6);
		 art6.setMarca("Mica");
		 art6.setNombre("Juego de Jard�n Humbert Chocolate");
		 art6.setNombreFoto("JuegoJardinFoto");
		 art6.setOrigen("Argentina");
		 art6.setPrecio(3.29f);
		 art6.setStock(9);
		 
		ObjectMessage message6 = session.createObjectMessage();
		message6.setObject(art6);
		
		producer.send(message6);
		
		/***************/
		
		 // CREO EL ARTICULO n�7 y lo envio
		 VOFichaTecnica ft11 = new VOFichaTecnica();
		 ft11.setId(11);
		 ft11.setAtributo("Edad recomendada");
		 ft11.setDetalle("8 a 14 a�os");
		 List<VOFichaTecnica> fichas7 = new ArrayList<VOFichaTecnica>();
		 fichas7.add(ft11);
		 VOArticulo art7 = new VOArticulo();
		 art7.setCodigo(1750150);
		 art7.setDeposito("DEPOSITO1");
		 art7.setTipo("Ni�os");
		 art7.setDescripcion("Madera con lija");
		 art7.setEstado("Estado");
		 art7.setFichasTecnicas(fichas7);
		 art7.setMarca("Falabella");
		 art7.setNombre("Skateboard PRO");
		 art7.setNombreFoto("SkateFoto");
		 art7.setOrigen("Argentina");
		 art7.setPrecio(399f);
		 art7.setStock(6);
		 
		ObjectMessage message7 = session.createObjectMessage();
		message7.setObject(art7);
		
		producer.send(message7);
		
		 // CREO EL ARTICULO n�8 y lo envio
		 VOFichaTecnica ft12 = new VOFichaTecnica();
		 ft12.setId(12);
		 ft12.setAtributo("Edad recomendada");
		 ft12.setDetalle("9 meses a 3 a�os");
		 List<VOFichaTecnica> fichas8 = new ArrayList<VOFichaTecnica>();
		 fichas8.add(ft12);
		 VOArticulo art8 = new VOArticulo();
		 art8.setCodigo(1858018);
		 art8.setDeposito("DEPOSITO1");
		 art8.setTipo("Ni�os");
		 art8.setDescripcion("Coche Paraguas");
		 art8.setEstado("Estado");
		 art8.setFichasTecnicas(fichas8);
		 art8.setMarca("Love");
		 art8.setNombre("Coche Paraguas Gris");
		 art8.setNombreFoto("CarritoFoto");
		 art8.setOrigen("China");
		 art8.setPrecio(529f);
		 art8.setStock(5);
		 
		ObjectMessage message8 = session.createObjectMessage();
		message8.setObject(art8);
		
		producer.send(message8);
		
		
		// TODO: recordar cerrar la session y la connection en un bloque �finally�
		System.out.print("Envi� el mensaje...");
		connection.close();

		} catch (Exception e) {
			System.out.println("Error al efectuar pedido: " + e);
			e.printStackTrace();
		}

	}
}
