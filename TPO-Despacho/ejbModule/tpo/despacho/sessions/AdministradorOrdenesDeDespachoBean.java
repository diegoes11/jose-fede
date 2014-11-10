package tpo.despacho.sessions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
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

import org.jboss.logging.Logger;

import tpo.despacho.entidades.Articulo;
import tpo.despacho.entidades.Deposito;
import tpo.despacho.entidades.DetalleOrdenDeDespacho;
import tpo.despacho.entidades.IdOrdenDeDespacho;
import tpo.despacho.entidades.LogisticaYMonitoreo;
import tpo.despacho.entidades.OrdenDeDespacho;
import tpo.despacho.entidades.PortalWeb;
import tpo.despacho.entidades.SolicitudDeArticulo;
import tpo.ia.vos.VODetalleOrdenDeDespacho;
import tpo.ia.vos.VOOrdenDeDespachoCompleta;
import tpo.ia.vos.VOOrdenDeDespacho;

@Stateless
public class AdministradorOrdenesDeDespachoBean implements AdministradorOrdenesDeDespacho {

	// Atributos
	private static final Logger LOGGER = Logger.getLogger(AdministradorOrdenesDeDespachoBean.class);
	
	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
	@EJB
	private EnvioInformesAuditoria envioInformes;
	
    public AdministradorOrdenesDeDespachoBean() {
    	
    }
    
    // Métodos auxiliares
    @SuppressWarnings("unchecked")
	private Articulo buscarArticulo(int codigo){
    	List<Articulo> articulos = manager.createQuery("SELECT a FROM Articulo a WHERE a.id.codigo =:c").setParameter("c", codigo).getResultList();
    	if(articulos.size() == 0) {
    		return null;
    	}
    	return articulos.get(0);
    }
    
	private void enviarSolcitudesDeArticuloAsync(OrdenDeDespacho ordenDeDespacho) {
    	try {
    		LOGGER.info("Envio de solicitudes de articulos...");
    		// Obtengo los depositos
        	List<Deposito> depositos = ordenDeDespacho.obtenerDepositosOrden();
        	// Por cada deposito
        	for(Deposito d : depositos)
        	{
        		final Properties env = new Properties();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
				env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, d.generarUrlRecepcionSolicitudDeArticulos()));
				env.put(Context.SECURITY_PRINCIPAL, d.getRecepcionDeSolicitudes().getUsuario());
				env.put(Context.SECURITY_CREDENTIALS, d.getRecepcionDeSolicitudes().getPassword());
				
				Context context = new InitialContext(env);
					  
				// buscar la Connection Factory en JNDI
				String connectionFactoryString = System.getProperty("connection.factory", "jms/RemoteConnectionFactory");
				ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
				// buscar la Cola en JNDI
				String destinationString = System.getProperty("destination", "jms/queue/" + d.getRecepcionDeSolicitudes().getNombre());
				Destination  destination = (Destination) context.lookup(destinationString);
				// crear la connection y la session a partir de la connection
				Connection connection = connectionFactory.createConnection(System.getProperty("username", d.getRecepcionDeSolicitudes().getUsuario()), System.getProperty("password", d.getRecepcionDeSolicitudes().getPassword()));
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
				LOGGER.info("Envio de solicitudes de articulos: OK");
				connection.close();
				}
        	
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Envio de solicitudes de articulos: Error desconocido - " + e.getStackTrace());
			}
	}
    
    // Métodos
    public List<VOOrdenDeDespachoCompleta> obtenerOrdenesDeDespacho(){
		try{
			LOGGER.info("Búsqueda de órdenes de despacho...");
			String query = "SELECT odd FROM OrdenDeDespacho odd";
	    	List<OrdenDeDespacho> ordenesDeDespacho = (List<OrdenDeDespacho>)manager.createQuery(query, OrdenDeDespacho.class).getResultList();
	    	List<VOOrdenDeDespachoCompleta> ordenesDeDespachoVO = new ArrayList<VOOrdenDeDespachoCompleta>(ordenesDeDespacho.size());
	    	// Convierto la lista de OrdenDeDespacho a OrdenDeDespachoCompletaVO
	    	for(OrdenDeDespacho odd : ordenesDeDespacho){
	    		VOOrdenDeDespachoCompleta ordenDeDespachoVO = odd.getOrdenDeDespachoVO();
	    		ordenesDeDespachoVO.add(ordenDeDespachoVO);
	    	}
	    	LOGGER.info("Búsqueda de órdenes de despacho: OK");
	    	return ordenesDeDespachoVO;
		}
		catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Búsqueda de órdenes de despacho: Error desconocido - " + e.getStackTrace());
			return new ArrayList<VOOrdenDeDespachoCompleta>();
		}
	}

    public VOOrdenDeDespachoCompleta obtenerOrdenDeDespacho(int id, String nombrePortalWeb){
    	try{
    		LOGGER.info("Búsqueda de una orden de despacho...");
    		PortalWeb portalWeb = manager.find(PortalWeb.class, nombrePortalWeb);
    		if (portalWeb != null){
            	IdOrdenDeDespacho idOrden = new IdOrdenDeDespacho(id, portalWeb);
        		OrdenDeDespacho ordenDeDespacho = manager.find(OrdenDeDespacho.class, idOrden);
        		if(ordenDeDespacho != null){
        			LOGGER.info("Búsqueda de una orden de despacho: OK");
        			return ordenDeDespacho.getOrdenDeDespachoVO();
        		}
        		LOGGER.error("Búsqueda de una orden de despacho: No existe una orden con el id recibido.");
    		}
    		LOGGER.error("Búsqueda de una orden de despacho: No existe un portal web con el nombre recibido.");
    		return null;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		LOGGER.error("Búsqueda de una orden de despacho: Error desconocido - " + e.getStackTrace());
    		return null;
    	}
	}
    
    public boolean recepcionOrdenDeDespacho(VOOrdenDeDespacho ordenDeDespachoVO){
    	try{
    		LOGGER.info("Recepcion de orden de despacho...");
    		PortalWeb portalWeb = manager.find(PortalWeb.class, ordenDeDespachoVO.getNombrePortalWeb());
        	if(portalWeb != null)
        	{
        		LogisticaYMonitoreo logisticaYMonitoreo = manager.find(LogisticaYMonitoreo.class, ordenDeDespachoVO.getNombreLogisticaYMonitoreo());
        		if(logisticaYMonitoreo != null)
        		{
    	    		// Creo la orden de despacho
    	    		OrdenDeDespacho ordenDeDespacho = new OrdenDeDespacho();
    	    		ordenDeDespacho.setId(new IdOrdenDeDespacho(ordenDeDespachoVO.getIdOrdenDeDespacho(), portalWeb));
    	    		ordenDeDespacho.setIdVenta(ordenDeDespachoVO.getIdVenta());
    	    		ordenDeDespacho.setLogisticaYMonitoreo(logisticaYMonitoreo);
    	    		ordenDeDespacho.setEstado("pendiente de entrega");
    	    		ordenDeDespacho.setFechaRecepcion(Calendar.getInstance().getTime());
    	    		List<VODetalleOrdenDeDespacho> detallesOrdenDeDespachoVO = ordenDeDespachoVO.getDetallesOrdenDeDespachoVO();
    	    		// Creo los detalles de la orden de despacho
    	    		for(VODetalleOrdenDeDespacho doddvo : detallesOrdenDeDespachoVO)
    	    		{
    	    			Articulo articulo = buscarArticulo(doddvo.getCodigoArticulo());
    	    			if(articulo != null)
    	    			{
    	        			DetalleOrdenDeDespacho dodd = new DetalleOrdenDeDespacho();
    	    				ordenDeDespacho.agregarDetalle(dodd);
    	    				dodd.setArticulo(articulo);
    	    				dodd.setCantidad(doddvo.getCantidad());
    	    				dodd.setEstado("incompleto");
    	    				dodd.setOrdenDeDespacho(ordenDeDespacho);
    	    			}
    	    			LOGGER.error("Recepcion de orden de despacho: No existe articulo con el codigo recibido.");
    	    		}
    	    		List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho = ordenDeDespacho.getDetallesOrdenDeDespacho();
    	    		// Por cada detalle/item de la orden de despacho, creo la solicitud de articulo
    	    		for(DetalleOrdenDeDespacho dodd : detallesOrdenDeDespacho)
    	    		{
    	    			SolicitudDeArticulo solicitudDeArticulo = new SolicitudDeArticulo();
    	    			dodd.setSolicitudDeArticulo(solicitudDeArticulo);
    	    			solicitudDeArticulo.setDetalleOrdenDeDespacho(dodd);
    	    			solicitudDeArticulo.setDeposito(dodd.getArticulo().getId().getDeposito());
    	    			solicitudDeArticulo.setCantidadRestante(dodd.getCantidad());
    	    			solicitudDeArticulo.setFechaPedido(Calendar.getInstance().getTime());
    	    		}

    	    		manager.persist(ordenDeDespacho);
    	    		
    				// ENVIAR ASINCRONICAMENTE LAS SOLICITUDES DE ARTICULO AL DEPOSITO CORRESPONDIENTE.
    	    		// INTEGRACIÓN DEPOSITO
    	    		// enviarSolcitudesDeArticuloAsync(ordenDeDespacho);
    	    		

    	    		LOGGER.info("Recepcion de orden de despacho: OK");
    	    		
    	    		// Por cada detalle/item de la orden de despacho, înformo de la solicitud de articulo a LyM
    	    		for(DetalleOrdenDeDespacho dodd : detallesOrdenDeDespacho)
    	    		{
    	    			SolicitudDeArticulo sda = dodd.getSolicitudDeArticulo();
    	    			// INTEGRACIÓN LOGISTICA
    	    			envioInformes.EnviarInforme(sda.obtenerInformeSolicitud());
    	    		}

    	    		return true;
        		}
        		LOGGER.error("Recepcion de orden de despacho: No existe modulo de logistica y monitoreo con el nombre recibido.");
        	}
        	LOGGER.error("Recepcion de orden de despacho: No existe portal web con el nombre recibido.");
        	return false;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		LOGGER.error("Recepcion de orden de despacho: Error desconocido - " + e.getStackTrace());
    		return false;
    	}
    }
}
