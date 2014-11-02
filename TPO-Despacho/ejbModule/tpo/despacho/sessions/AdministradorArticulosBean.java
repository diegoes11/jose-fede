package tpo.despacho.sessions;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tpo.despacho.entidades.Articulo;
import tpo.despacho.entidades.SolicitudDeArticulo;
import tpo.despacho.vos.ArticuloVO;

@Stateless
public class AdministradorArticulosBean implements AdministradorArticulos {
	
	// Atributos
	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
	// Constructor
    public AdministradorArticulosBean() {
    	
    }
    
    private SolicitudDeArticulo buscarSolicitudDeArticulo(int idSolicitudDeArticulo){
    	SolicitudDeArticulo solicitudDeArticulo = manager.find(SolicitudDeArticulo.class, idSolicitudDeArticulo);
    	if(solicitudDeArticulo != null)
    		return solicitudDeArticulo;
    	return null;
    }

	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad) {
		SolicitudDeArticulo solicitudDeArticulo = buscarSolicitudDeArticulo(idSolicitudDeArticulo);
		if(solicitudDeArticulo != null){
			solicitudDeArticulo.actualizarCantidad(cantidad);
			return true;
		}
		return false;
	}

	public List<ArticuloVO> obtenerArticulos() {
    	String query = "SELECT a FROM Articulo a";
    	List<Articulo> articulos = (List<Articulo>)manager.createQuery(query, Articulo.class).getResultList();
    	List<ArticuloVO> articulosVO = new ArrayList<ArticuloVO>(articulos.size());
    	// Convierto la lista de Articulo a ArticuloVO
    	for(Articulo a : articulos){
    		ArticuloVO articuloVO = a.getArticuloVO();
    		articulosVO.add(articuloVO);
    	}
    	return articulosVO;
	}
}
