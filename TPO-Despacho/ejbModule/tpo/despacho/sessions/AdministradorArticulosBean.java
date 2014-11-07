package tpo.despacho.sessions;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import tpo.despacho.entidades.Articulo;
import tpo.despacho.entidades.Deposito;
import tpo.despacho.entidades.IdArticulo;
import tpo.despacho.entidades.SolicitudDeArticulo;
import tpo.ia.vos.VOArticulo;
import tpo.ia.vos.VOArticuloCompleto;

@Stateless
public class AdministradorArticulosBean implements AdministradorArticulos {
	
	// Atributos
	private static final Logger LOGGER = Logger.getLogger(AdministradorArticulosBean.class);
	
	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
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
			LOGGER.info("Recepción de articulos...");
			SolicitudDeArticulo solicitudDeArticulo = buscarSolicitudDeArticulo(idSolicitudDeArticulo);
			if(solicitudDeArticulo != null){
				solicitudDeArticulo.actualizarCantidad(cantidad);
				LOGGER.info("Recepción de articulos: OK");
				return true;
			}
			LOGGER.error("Recepción de articulos: No existe una solicitud con el id recibido.");
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Recepción de articulos: Error desconocido - " + e.getStackTrace());
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
			LOGGER.info("Alta de articulo...");
			Articulo articulo = buscarArticulo(articuloVO.getCodigo());
			Deposito deposito = buscarDeposito(articuloVO.getDeposito());
			if(articulo == null && deposito != null){
				articulo = new Articulo();
				articulo.setArticuloVO(articuloVO);
				articulo.setId(new IdArticulo(articuloVO.getCodigo(), deposito));
				manager.persist(articulo);
				LOGGER.info("Alta de articulo: OK");
				return true;
			}
			LOGGER.error("Alta de articulo: No existe el depósito o el artículo ingresado.");
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Alta de articulo: Error desconocido - " + e.getStackTrace());
			return false;
		}
	}
}
