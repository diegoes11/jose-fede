package tpo.despacho.sessions;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import tpo.despacho.entidades.Articulo;
import tpo.despacho.entidades.Deposito;
import tpo.despacho.entidades.IdArticulo;
import tpo.despacho.entidades.SolicitudDeArticulo;
import tpo.despacho.facade.DespachoFacade;
import tpo.ia.vos.VOArticulo;
import tpo.ia.vos.VOArticuloCompleto;
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
    
    // M�todos auxiliares
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

    // M�todos
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad) {
		try{
			LOGGER.info("Recepci�n de art�culos...");
			SolicitudDeArticulo solicitudDeArticulo = buscarSolicitudDeArticulo(idSolicitudDeArticulo);
			if(solicitudDeArticulo != null){
				solicitudDeArticulo.actualizarCantidad(cantidad);
				LOGGER.info("Recepci�n de art�culos: OK");
				// Si el detalle de la orden de despacho est� completo, informe a auditor�a
				if(solicitudDeArticulo.getDetalleOrdenDeDespacho().estaCompleto())
				{
					despachoFacade.EnviarInforme(new VOInformeAuditoria(solicitudDeArticulo.getDetalleOrdenDeDespacho().obtenerInformeCompletitud()));
				}
				// Si la orden de despacho est� completa, informo a los modulos correspondientes
				if(solicitudDeArticulo.getDetalleOrdenDeDespacho().getOrdenDeDespacho().estaCompleta())
				{
					despachoFacade.EnviarInforme(new VOInformeAuditoria(solicitudDeArticulo.getDetalleOrdenDeDespacho().getOrdenDeDespacho().obtenerInformeCompletitud()));
					// ENVIO JMS A LOGISTICA
					// ENVIO WEBSERVICE A PORTAL
				}
				return true;
			}
			LOGGER.error("Recepci�n de art�culos: No existe una solicitud con el id recibido.");
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Recepci�n de art�culos: Error desconocido - " + e.getStackTrace());
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
			LOGGER.info("Alta de art�culo...");
			Articulo articulo = buscarArticulo(articuloVO.getCodigo());
			Deposito deposito = buscarDeposito(articuloVO.getDeposito());
			if(articulo == null && deposito != null){
				articulo = new Articulo();
				articulo.setArticuloVO(articuloVO);
				articulo.setId(new IdArticulo(articuloVO.getCodigo(), deposito));
				manager.persist(articulo);
				LOGGER.info("Alta de art�culo: OK");
				despachoFacade.EnviarInforme(new VOInformeAuditoria(articulo.obtenerInformeAlta()));
				return true;
			}
			LOGGER.error("Alta de art�culo: No existe el dep�sito o el art�culo ingresado.");
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Alta de art�culo: Error desconocido - " + e.getStackTrace());
			return false;
		}
	}
}
