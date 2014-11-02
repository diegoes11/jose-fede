package tpo.despacho.web.business;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import tpo.despacho.facade.DespachoFacade;
import tpo.despacho.vos.ArticuloVO;
import tpo.despacho.vos.OrdenDeDespachoCompletaVO;
import tpo.despacho.vos.UsuarioVO;

public class DespachoBusinessDelegate {
	// Atributos
	private static DespachoBusinessDelegate instancia;
	private DespachoFacade despachoFacade;
	
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
			/*Properties jndiProps = new Properties();
   		 	jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
   		 	jndiProps.put(Context.PROVIDER_URL,"remote://127.0.0.1:4447");
   		 	jndiProps.put(Context.SECURITY_CREDENTIALS, "test1234.");
	   		jndiProps.put("jboss.naming.client.ejb.context", true);
	   		Context context = new InitialContext(jndiProps);
			despachoFacade =(DespachoFacade)context.lookup("TPO-DespachoEAR/TPO-Despacho/DespachoFacadeBean!tpo.despacho.facade.DespachoFacade");*/
			Context context = new InitialContext();
			despachoFacade =(DespachoFacade)context.lookup("java:global/TPO-DespachoEAR/TPO-Despacho/DespachoFacadeBean!tpo.despacho.facade.DespachoFacade");
	   		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int altaUsuario(UsuarioVO usuarioVO){
		return despachoFacade.altaUsuario(usuarioVO);
	}
	
	public List<UsuarioVO> obtenerUsuarios(){
		return despachoFacade.obtenerUsuarios();
	}
	
	public boolean setEstadoActivoUsuario(UsuarioVO usuarioVO){
		return despachoFacade.setEstadoActivoUsuario(usuarioVO);
	}
	
	public List<ArticuloVO> obtenerArticulos(){
		return despachoFacade.obtenerArticulos();
	}
	
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad){
		return despachoFacade.recepcionDeArticulos(idSolicitudDeArticulo, cantidad);
	}
	
	public List<OrdenDeDespachoCompletaVO> obtenerOrdenesDeDespacho(){
		return despachoFacade.obtenerOrdenesDeDespacho();
	}
	
	public OrdenDeDespachoCompletaVO obtenerOrdenDeDespacho(int id, String nombrePortalWeb){
		return despachoFacade.obtenerOrdenDeDespacho(id, nombrePortalWeb);
	}
}
