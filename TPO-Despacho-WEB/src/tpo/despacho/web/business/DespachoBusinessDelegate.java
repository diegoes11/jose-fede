package tpo.despacho.web.business;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

import tpo.despacho.facade.DespachoFacade;
import tpo.ia.vos.VOArticuloCompleto;
import tpo.ia.vos.VOOrdenDeDespachoCompleta;
import tpo.ia.vos.VOUsuario;

public class DespachoBusinessDelegate {
	// Atributos
	private static DespachoBusinessDelegate instancia;
	private DespachoFacade despachoFacade;
	private static final Logger LOGGER = Logger.getLogger(DespachoBusinessDelegate.class);
	
	// Constructor
	private DespachoBusinessDelegate(){
		obtenerConexion();
	}
	
	// Métodos
	public static DespachoBusinessDelegate getInstancia(){
		if(instancia == null)
			instancia = new DespachoBusinessDelegate();
		return instancia;
	}
	
	private void obtenerConexion(){
		try {
			LOGGER.info("Obtener conexión...");
			/*Properties jndiProps = new Properties();
   		 	jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
   		 	jndiProps.put(Context.PROVIDER_URL,"remote://127.0.0.1:4447");
   		 	jndiProps.put(Context.SECURITY_CREDENTIALS, "test1234.");
	   		jndiProps.put("jboss.naming.client.ejb.context", true);
	   		Context context = new InitialContext(jndiProps);
			despachoFacade =(DespachoFacade)context.lookup("TPO-DespachoEAR/TPO-Despacho/DespachoFacadeBean!tpo.despacho.facade.DespachoFacade");*/
			Context context = new InitialContext();
			despachoFacade =(DespachoFacade)context.lookup("java:global/TPO-DespachoEAR/TPO-Despacho/DespachoFacadeBean!tpo.despacho.facade.DespachoFacade");
			LOGGER.info("Obtener conexión: OK");
	   		
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Obtener conexión: Error desconocido - " + e.getStackTrace());
		}
	}
	
	public int altaUsuario(VOUsuario usuarioVO){
		return despachoFacade.altaUsuario(usuarioVO);
	}
	
	public List<VOUsuario> obtenerUsuarios(){
		return despachoFacade.obtenerUsuarios();
	}
	
	public boolean setEstadoActivoUsuario(VOUsuario usuarioVO){
		return despachoFacade.setEstadoActivoUsuario(usuarioVO);
	}
	
	public List<VOArticuloCompleto> obtenerArticulos(){
		return despachoFacade.obtenerArticulos();
	}
	
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad){
		return despachoFacade.recepcionDeArticulos(idSolicitudDeArticulo, cantidad);
	}
	
	public List<VOOrdenDeDespachoCompleta> obtenerOrdenesDeDespacho(){
		return despachoFacade.obtenerOrdenesDeDespacho();
	}
	
	public VOOrdenDeDespachoCompleta obtenerOrdenDeDespacho(int id, String nombrePortalWeb){
		return despachoFacade.obtenerOrdenDeDespacho(id, nombrePortalWeb);
	}
}
