package tpo.despacho.sessions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.jboss.logging.Logger;

import tpo.despacho.entidades.Articulo;
import tpo.despacho.entidades.Deposito;
import tpo.despacho.entidades.IdArticulo;
import tpo.despacho.entidades.OrdenDeDespacho;
import tpo.despacho.entidades.SolicitudDeArticulo;
import tpo.despacho.facade.DespachoFacade;
import tpo.ia.vos.VOArticulo;
import tpo.ia.vos.VOArticuloCompleto;
import tpo.ia.vos.VOEnvioOrdenDeDespachoLista;
import tpo.ia.vos.VOInformeAuditoria;

@Stateless
public class AdministradorArticulosBean implements AdministradorArticulos {
	
	// Atributos
	private static final Logger LOGGER = Logger.getLogger(AdministradorArticulosBean.class);
	
	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
	@EJB
	private DespachoFacade despachoFacade;
	
	// Constructor
    public AdministradorArticulosBean() {
    	
    }
    
    // Métodos auxiliares
    private SolicitudDeArticulo buscarSolicitudDeArticulo(int idSolicitudDeArticulo){
    	SolicitudDeArticulo solicitudDeArticulo = manager.find(SolicitudDeArticulo.class, idSolicitudDeArticulo);
    	if(solicitudDeArticulo != null)
    		return solicitudDeArticulo;
    	return null;
    }
    
    @SuppressWarnings("unchecked")
	private Articulo buscarArticulo(int codigo){
    	List<Articulo> articulos = manager.createQuery("SELECT a FROM Articulo a WHERE a.id.codigo =:c")
    								.setParameter("c", codigo).getResultList();
    	if(articulos.size() == 0) {
    		return null;
    	}
    	return articulos.get(0);
    }
    
    private Deposito buscarDeposito(String nombre){
    	Deposito deposito = manager.find(Deposito.class, nombre);
    	return deposito;
    }

    // Métodos
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad) {
		try{
			LOGGER.info("Recepción de artículos...");
			SolicitudDeArticulo solicitudDeArticulo = buscarSolicitudDeArticulo(idSolicitudDeArticulo);
			if(solicitudDeArticulo != null){
				solicitudDeArticulo.actualizarCantidad(cantidad);
				// Si el detalle de la orden de despacho está completo, informe a auditoría
				if(solicitudDeArticulo.getDetalleOrdenDeDespacho().estaCompleto())
				{
					// INTEGRACIÓN
					// despachoFacade.EnviarInforme(new VOInformeAuditoria(solicitudDeArticulo.getDetalleOrdenDeDespacho().obtenerInformeCompletitud()));
				}
				// Si la orden de despacho está completa, informo a los modulos correspondientes
				if(solicitudDeArticulo.getDetalleOrdenDeDespacho().getOrdenDeDespacho().estaCompleta())
				{
					OrdenDeDespacho o = solicitudDeArticulo.getDetalleOrdenDeDespacho().getOrdenDeDespacho();
					// Envio informe a Logistica y Monitoreo (SYNC/ASYNC)
					// INTEGRACIÓN
					// despachoFacade.EnviarInforme(new VOInformeAuditoria(o.obtenerInformeCompletitud()));
					// ENVIO WEBSERVICE A PORTAL
					// Envio informe de cambio de destado a Logistica y Monitoreo (REST)
					// INTEGRACIÓN
					// informarOrdenDeDespachoListaSync(o.getLogisticaYMonitoreo().getUrlRecepcionEstadoOrdenDeDesapcho(), new VOEnvioOrdenDeDespachoLista(o.getId().getIdOrdenDeDespacho()));
					// Envio informe de cambio de estado a Portal Web (WEB SERVICE)
					// IMPLEMENTAR WS
				}
				LOGGER.info("Recepción de artículos: OK");
				return true;
			}
			LOGGER.error("Recepción de artículos: No existe una solicitud con el id recibido.");
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Recepción de artículos: Error desconocido - " + e.getStackTrace());
			return false;
		}
		
	}

	public List<VOArticuloCompleto> obtenerArticulos() {
    	String query = "SELECT a FROM Articulo a";
    	List<Articulo> articulos = (List<Articulo>)manager.createQuery(query, Articulo.class).getResultList();
    	List<VOArticuloCompleto> articulosVO = new ArrayList<VOArticuloCompleto>(articulos.size());
    	// Convierto la lista de Articulo a ArticuloVO
    	for(Articulo a : articulos){
    		VOArticuloCompleto articuloVO = a.getArticuloVOCompleto();
    		articulosVO.add(articuloVO);
    	}
    	return articulosVO;
	}
	
	public boolean altaArticulo(VOArticulo articuloVO){
		try{
			LOGGER.info("Alta de artículo...");
			Articulo articulo = buscarArticulo(articuloVO.getCodigo());
			Deposito deposito = buscarDeposito(articuloVO.getDeposito());
			if(articulo == null && deposito != null){
				articulo = new Articulo();
				articulo.setArticuloVO(articuloVO);
				articulo.setId(new IdArticulo(articuloVO.getCodigo(), deposito));
				manager.persist(articulo);
				LOGGER.info("Alta de artículo: OK");
				// INTEGRACIÓN
				// despachoFacade.EnviarInforme(new VOInformeAuditoria(articulo.obtenerInformeAlta()));
				return true;
			}
			LOGGER.error("Alta de artículo: No existe el depósito o el artículo ingresado.");
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Alta de artículo: Error desconocido - " + e.getStackTrace());
			return false;
		}
	}
	
	private boolean informarOrdenDeDespachoListaSync (String urlString, VOEnvioOrdenDeDespachoLista voEnvioOrdenDeDespachoLista) {
    	try {
    		LOGGER.info("Informar orden de despacho lista...");
    		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    		String json = ow.writeValueAsString(voEnvioOrdenDeDespachoLista);
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setAllowUserInteraction(false);
			con.setRequestProperty("Content-Type", "application/json; charset=utf8");
			OutputStream os = con.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.close();
			
			if (con.getResponseCode() != 200){
				throw new IOException(con.getResponseMessage());
			}
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = rd.readLine()) != null){
				sb.append(line);
			}
			rd.close();
			
			con.disconnect();
			String respuesta = sb.toString();
			if (respuesta.equals("OK")) {
				LOGGER.info("Informar orden de despacho lista: OK");
				return true;
			}
			else {
				LOGGER.error("Informar orden de despacho lista: La respuesta no fue OK.");
				return false;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Informar orden de despacho lista: Error desconocido - " + e.getStackTrace());
			return false;
		}
    }
}
