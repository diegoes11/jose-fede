package tpo.despacho.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tpo.despacho.sessions.AdministradorArticulos;
import tpo.despacho.sessions.AdministradorOrdenesDeDespacho;
import tpo.despacho.sessions.AdministradorUsuarios;
import tpo.despacho.sessions.EnvioInformesAuditoria;
import tpo.ia.vos.VOArticulo;
import tpo.ia.vos.VOArticuloCompleto;
import tpo.ia.vos.VOInformeAuditoria;
import tpo.ia.vos.VOOrdenDeDespachoCompleta;
import tpo.ia.vos.VOOrdenDeDespacho;
import tpo.ia.vos.VOUsuario;

@Stateless
public class DespachoFacadeBean implements DespachoFacade {
	
	// Atributos
	@EJB
	private AdministradorUsuarios administradorUsuarios;
	@EJB
	private AdministradorArticulos administradorArticulos;
	@EJB
	private AdministradorOrdenesDeDespacho administradorOrdenesDeDespacho;
	@EJB
	private EnvioInformesAuditoria envioInformesAuditoria;

    public DespachoFacadeBean() {
    }
    
    // Administración de usuarios
    public int altaUsuario(VOUsuario usuarioVO){
    	return administradorUsuarios.altaUsuario(usuarioVO);
    }
    
    public List<VOUsuario> obtenerUsuarios(){
    	return administradorUsuarios.obtenerUsuarios();
    }
    
    public boolean setEstadoActivoUsuario(VOUsuario usuarioVO){
    	return administradorUsuarios.setEstadoActivoUsuario(usuarioVO);
    }

	public List<VOArticuloCompleto> obtenerArticulos() {
		return administradorArticulos.obtenerArticulos();
	}
	
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad){
		return administradorArticulos.recepcionDeArticulos(idSolicitudDeArticulo, cantidad);
	}
	
	public List<VOOrdenDeDespachoCompleta> obtenerOrdenesDeDespacho(){
		return administradorOrdenesDeDespacho.obtenerOrdenesDeDespacho();
	}
	
	public VOOrdenDeDespachoCompleta obtenerOrdenDeDespacho(int id, String nombrePortalWeb){
		return administradorOrdenesDeDespacho.obtenerOrdenDeDespacho(id, nombrePortalWeb);
	}
	
	public boolean altaArticulo(VOArticulo articuloVO){
		return administradorArticulos.altaArticulo(articuloVO);
	}
	
	public boolean recepcionOrdenDeDespacho(VOOrdenDeDespacho ordenDeDespachoVO){
		return administradorOrdenesDeDespacho.recepcionOrdenDeDespacho(ordenDeDespachoVO);
	}
	
	public boolean EnviarInforme(VOInformeAuditoria informe) {
		return envioInformesAuditoria.EnviarInforme(informe);
	}
}
