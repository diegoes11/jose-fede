package tpo.despacho.facade;

import java.util.List;

import javax.ejb.Remote;

import tpo.ia.vos.VOArticulo;
import tpo.ia.vos.VOArticuloCompleto;
import tpo.ia.vos.VOOrdenDeDespachoCompleta;
import tpo.ia.vos.VOOrdenDeDespacho;
import tpo.ia.vos.VOUsuario;

@Remote
public interface DespachoFacade {
	public int altaUsuario(VOUsuario usuarioVO);
	public List<VOUsuario> obtenerUsuarios();
	public boolean setEstadoActivoUsuario(VOUsuario usuarioVO);
	public List<VOArticuloCompleto> obtenerArticulos();
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad);
	public List<VOOrdenDeDespachoCompleta> obtenerOrdenesDeDespacho();
	public VOOrdenDeDespachoCompleta obtenerOrdenDeDespacho(int id, String nombrePortalWeb);
	public boolean altaArticulo(VOArticulo articuloVO);
	public boolean recepcionOrdenDeDespacho(VOOrdenDeDespacho ordenDeDespachoVO);
}
