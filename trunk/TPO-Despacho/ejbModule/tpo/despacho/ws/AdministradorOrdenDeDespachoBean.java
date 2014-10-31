package tpo.despacho.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tpo.despacho.entidades.Articulo;
import tpo.despacho.entidades.DetalleOrdenDeDespacho;
import tpo.despacho.entidades.IdOrdenDeDespacho;
import tpo.despacho.entidades.LogisticaYMonitoreo;
import tpo.despacho.entidades.OrdenDeDespacho;
import tpo.despacho.entidades.PortalWeb;
import tpo.despacho.entidades.SolicitudDeArticulo;
import tpo.despacho.vos.ArticuloVO;
import tpo.despacho.vos.DetalleOrdenDeDespachoVO;
import tpo.despacho.vos.FichaTecnicaVO;
import tpo.despacho.vos.OrdenDeDespachoVO;

// URL WSDL: http://localhost:8080/TPO-Despacho/AdministradorOrdenDeDespachoBean?wsdl

@Stateless
@WebService
public class AdministradorOrdenDeDespachoBean implements AdministradorOrdenDeDespacho {

	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
    public AdministradorOrdenDeDespachoBean() {
    }

    @WebMethod
	public boolean recepcionOrdenDeDespacho(OrdenDeDespachoVO ordenDeDespachoVO) {
    	PortalWeb portalWeb = manager.find(PortalWeb.class, ordenDeDespachoVO.getNombrePortalWeb());
    	if(portalWeb != null)
    	{
    		LogisticaYMonitoreo logisticaYMonitoreo = manager.find(LogisticaYMonitoreo.class, ordenDeDespachoVO.getNombreLogisticaYMonitoreo());
    		if(logisticaYMonitoreo != null)
    		{
	    		// Creo la orden de despacho
	    		OrdenDeDespacho ordenDeDespacho = new OrdenDeDespacho();
	    		ordenDeDespacho.setId(new IdOrdenDeDespacho(ordenDeDespachoVO.getIdOrdenDeDespacho(), portalWeb));
	    		ordenDeDespacho.setLogisticaYMonitoreo(logisticaYMonitoreo);
	    		List<DetalleOrdenDeDespachoVO> detallesOrdenDeDespachoVO = ordenDeDespachoVO.getDetallesOrdenDeDespachoVO();
	    		List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho = new ArrayList<DetalleOrdenDeDespacho>();
	    		// Creo los detalles de la orden de despacho
	    		for(DetalleOrdenDeDespachoVO doddvo : detallesOrdenDeDespachoVO)
	    		{
	    			Articulo articulo = manager.find(Articulo.class, doddvo.getCodigoArticulo());
	    			if(articulo != null)
	    			{
	        			DetalleOrdenDeDespacho dodd = new DetalleOrdenDeDespacho();
	    				dodd.setArticulo(articulo);
	    				dodd.setCantidad(doddvo.getCantidad());
	    				dodd.setEstado("incompleto");
	    				detallesOrdenDeDespacho.add(dodd);
	    			}
	    		}
	    		ordenDeDespacho.setDetallesOrdenDeDespacho(detallesOrdenDeDespacho);
	    		// Por cada detalle/item de la orden de despacho, creo la solicitud de articulo
	    		for(DetalleOrdenDeDespacho dodd : detallesOrdenDeDespacho)
	    		{
	    			SolicitudDeArticulo solicitudDeArticulo = new SolicitudDeArticulo();
	    			solicitudDeArticulo.setDeposito(dodd.getArticulo().getId().getDeposito());
	    			solicitudDeArticulo.setDetalleOrdenDeDespacho(dodd);
	    			solicitudDeArticulo.setCantidadRestante(dodd.getCantidad());
	    			
	    			dodd.setSolicitudDeArticulo(solicitudDeArticulo);
	    		}
	    		
				// ENVIAR ASINCRONICAMENTE LAS SOLICITUDES DE ARTICULO AL DEPOSITO CORRESPONDIENTE.
	    		enviarSolcitudesDeArticuloAsync(ordenDeDespacho);
	    		
	    		manager.persist(ordenDeDespacho);
    		}
    	}
    	
    	return false;
	}
    
    private static void enviarSolcitudesDeArticuloAsync(OrdenDeDespacho ordenDeDespacho) {

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
		 
		 // POR CADA DETALLE DE LA ORDEN DE DESPACHO
		 for(DetalleOrdenDeDespacho dodd : ordenDeDespacho.getDetallesOrdenDeDespacho())
		 {
			// Crear un mensaje de tipo Object Message y setearle el contenido
			ObjectMessage message = session.createObjectMessage();
			message.setObject(dodd.getSolicitudDeArticulo().getSolicitudDeArticuloVO());
			
			// Enviar el mensaje
			producer.send(message);
		 }		
		
		// TODO: recordar cerrar la session y la connection en un bloque “finally”
		System.out.print("Envió el mensaje...");
		connection.close();

		} catch (Exception e) {
			System.out.println("Error al efectuar pedido: " + e);
			e.printStackTrace();
		}
	}
}
