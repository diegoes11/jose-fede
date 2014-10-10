package tpo.despacho.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tpo.despacho.entidades.SolicitudDeArticulo;

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

	public void actualizarCantidadSolicitudDeArticulo(int idSolicitudDeArticulo, int cantidad) {
		SolicitudDeArticulo solicitudDeArticulo = buscarSolicitudDeArticulo(idSolicitudDeArticulo);
		if(solicitudDeArticulo != null){
			solicitudDeArticulo.actualizarCantidad(cantidad);
		}
	}
}
