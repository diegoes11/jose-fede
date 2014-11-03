package tpo.despacho.sessions;

import java.util.ArrayList;
import java.util.Calendar;
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

import tpo.despacho.entidades.Articulo;
import tpo.despacho.entidades.DetalleOrdenDeDespacho;
import tpo.despacho.entidades.IdOrdenDeDespacho;
import tpo.despacho.entidades.LogisticaYMonitoreo;
import tpo.despacho.entidades.OrdenDeDespacho;
import tpo.despacho.entidades.PortalWeb;
import tpo.despacho.entidades.SolicitudDeArticulo;
import tpo.ia.vos.DetalleOrdenDeDespachoVO;
import tpo.ia.vos.OrdenDeDespachoCompletaVO;
import tpo.ia.vos.OrdenDeDespachoVO;

@Stateless
public class AdministradorOrdenesDeDespachoBean implements AdministradorOrdenesDeDespacho {

	// Atributos
	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
    public AdministradorOrdenesDeDespachoBean() {
    	
    }
    
    // M�todos auxiliares
    @SuppressWarnings("unchecked")
	private Articulo buscarArticulo(int codigo){
    	List<Articulo> articulos = manager.createQuery("SELECT a FROM Articulo a WHERE a.id.codigo =:c").setParameter("c", codigo).getResultList();
    	if(articulos.size() == 0) {
    		return null;
    	}
    	return articulos.get(0);
    }
    
    @SuppressWarnings("unused")
	private static void enviarSolcitudesDeArticuloAsync(OrdenDeDespacho ordenDeDespacho) {

		final Properties env = new Properties();
		  env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		  env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "remote://172.16.164.38:4447"));
		  env.put(Context.SECURITY_PRINCIPAL, "deposito1");
		  env.put(Context.SECURITY_CREDENTIALS, "deposito1.");

		  try {
		
		Context context = new InitialContext(env);
			  
		// buscar la Connection Factory en JNDI
		String connectionFactoryString = System.getProperty("connection.factory", "jms/RemoteConnectionFactory");
		ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryString);
		// buscar la Cola en JNDI
		String destinationString = System.getProperty("destination", "jms/queue/SolicitudArticulos");
		Destination  destination = (Destination) context.lookup(destinationString);
		// crear la connection y la session a partir de la connection
		Connection connection = connectionFactory.createConnection(System.getProperty("username", "deposito1"), System.getProperty("password", "deposito1."));
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
		
		// TODO: recordar cerrar la session y la connection en un bloque �finally�
		System.out.print("Envi� el mensaje...");
		connection.close();

		} catch (Exception e) {
			System.out.println("Error al efectuar pedido: " + e);
			e.printStackTrace();
		}
	}
    
    // M�todos
    public List<OrdenDeDespachoCompletaVO> obtenerOrdenesDeDespacho(){
		try{
			String query = "SELECT odd FROM OrdenDeDespacho odd";
	    	List<OrdenDeDespacho> ordenesDeDespacho = (List<OrdenDeDespacho>)manager.createQuery(query, OrdenDeDespacho.class).getResultList();
	    	List<OrdenDeDespachoCompletaVO> ordenesDeDespachoVO = new ArrayList<OrdenDeDespachoCompletaVO>(ordenesDeDespacho.size());
	    	// Convierto la lista de OrdenDeDespacho a OrdenDeDespachoCompletaVO
	    	for(OrdenDeDespacho odd : ordenesDeDespacho){
	    		OrdenDeDespachoCompletaVO ordenDeDespachoVO = odd.getOrdenDeDespachoVO();
	    		ordenesDeDespachoVO.add(ordenDeDespachoVO);
	    	}
	    	return ordenesDeDespachoVO;
		}
		catch(Exception e){
			e.printStackTrace();
			return new ArrayList<OrdenDeDespachoCompletaVO>();
		}
	}

    public OrdenDeDespachoCompletaVO obtenerOrdenDeDespacho(int id, String nombrePortalWeb){
    	try{
    		PortalWeb portalWeb = manager.find(PortalWeb.class, nombrePortalWeb);
    		if (portalWeb != null){
            	IdOrdenDeDespacho idOrden = new IdOrdenDeDespacho(id, portalWeb);
        		OrdenDeDespacho ordenDeDespacho = manager.find(OrdenDeDespacho.class, idOrden);
        		if(ordenDeDespacho != null){
        			return ordenDeDespacho.getOrdenDeDespachoVO();
        		}
    		}
    		return null;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
	}
    
    public boolean recepcionOrdenDeDespacho(OrdenDeDespachoVO ordenDeDespachoVO){
    	try{
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
    	    		ordenDeDespacho.setEstado("pendiente de entrega");
    	    		ordenDeDespacho.setFechaRecepcion(Calendar.getInstance().getTime());
    	    		List<DetalleOrdenDeDespachoVO> detallesOrdenDeDespachoVO = ordenDeDespachoVO.getDetallesOrdenDeDespachoVO();
    	    		List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho = new ArrayList<DetalleOrdenDeDespacho>();
    	    		// Creo los detalles de la orden de despacho
    	    		for(DetalleOrdenDeDespachoVO doddvo : detallesOrdenDeDespachoVO)
    	    		{
    	    			Articulo articulo = buscarArticulo(doddvo.getCodigoArticulo());
    	    			if(articulo != null)
    	    			{
    	        			DetalleOrdenDeDespacho dodd = new DetalleOrdenDeDespacho();
    	    				dodd.setArticulo(articulo);
    	    				dodd.setCantidad(doddvo.getCantidad());
    	    				dodd.setEstado("incompleto");
    	    				dodd.setOrdenDeDespacho(ordenDeDespacho);
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
    	    			solicitudDeArticulo.setFechaPedido(Calendar.getInstance().getTime());
    	    			
    	    			dodd.setSolicitudDeArticulo(solicitudDeArticulo);
    	    		}

    	    		//manager.persist(ordenDeDespacho);
    	    		
    				// ENVIAR ASINCRONICAMENTE LAS SOLICITUDES DE ARTICULO AL DEPOSITO CORRESPONDIENTE.
    	    		enviarSolcitudesDeArticuloAsync(ordenDeDespacho);
    	    		return true;
        		}
        	}
        	return false;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    }
}