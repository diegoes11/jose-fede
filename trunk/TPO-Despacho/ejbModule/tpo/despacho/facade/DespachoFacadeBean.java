package tpo.despacho.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tpo.despacho.sessions.AdministradorArticulos;
import tpo.despacho.sessions.AdministradorUsuarios;
import tpo.despacho.vos.ArticuloVO;
import tpo.despacho.vos.UsuarioVO;

@Stateless
public class DespachoFacadeBean implements DespachoFacade {
	
	// Atributos
	@EJB
	private AdministradorUsuarios administradorUsuarios;
	@EJB
	private AdministradorArticulos administradorArticulos;

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
}
