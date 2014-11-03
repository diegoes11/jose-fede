package tpo.despacho.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tpo.despacho.sessions.AdministradorArticulos;
import tpo.despacho.sessions.AdministradorOrdenesDeDespacho;
import tpo.despacho.sessions.AdministradorUsuarios;
import tpo.ia.vos.ArticuloVO;
import tpo.ia.vos.OrdenDeDespachoCompletaVO;
import tpo.ia.vos.OrdenDeDespachoVO;
import tpo.ia.vos.UsuarioVO;

@Stateless
public class DespachoFacadeBean implements DespachoFacade {
	
	// Atributos
	@EJB
	private AdministradorUsuarios administradorUsuarios;
	@EJB
	private AdministradorArticulos administradorArticulos;
	@EJB
	private AdministradorOrdenesDeDespacho administradorOrdenesDeDespacho;

    public DespachoFacadeBean() {
    }
    
    // Administración de usuarios
    public int altaUsuario(UsuarioVO usuarioVO){
    	return administradorUsuarios.altaUsuario(usuarioVO);
    }
    
    public List<UsuarioVO> obtenerUsuarios(){
    	return administradorUsuarios.obtenerUsuarios();
    }
    
    public boolean setEstadoActivoUsuario(UsuarioVO usuarioVO){
    	return administradorUsuarios.setEstadoActivoUsuario(usuarioVO);
    }

	public List<ArticuloVO> obtenerArticulos() {
		return administradorArticulos.obtenerArticulos();
	}
	
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad){
		return administradorArticulos.recepcionDeArticulos(idSolicitudDeArticulo, cantidad);
	}
	
	public List<OrdenDeDespachoCompletaVO> obtenerOrdenesDeDespacho(){
		return administradorOrdenesDeDespacho.obtenerOrdenesDeDespacho();
	}
	
	public OrdenDeDespachoCompletaVO obtenerOrdenDeDespacho(int id, String nombrePortalWeb){
		return administradorOrdenesDeDespacho.obtenerOrdenDeDespacho(id, nombrePortalWeb);
	}
	
	public boolean altaArticulo(ArticuloVO articuloVO){
		return administradorArticulos.altaArticulo(articuloVO);
	}
	
	public boolean recepcionOrdenDeDespacho(OrdenDeDespachoVO ordenDeDespachoVO){
		return administradorOrdenesDeDespacho.recepcionOrdenDeDespacho(ordenDeDespachoVO);
	}
}
