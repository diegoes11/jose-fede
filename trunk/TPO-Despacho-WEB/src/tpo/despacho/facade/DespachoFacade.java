package tpo.despacho.facade;

import java.util.List;

import javax.ejb.Remote;

import tpo.despacho.vos.ArticuloVO;
import tpo.despacho.vos.OrdenDeDespachoCompletaVO;
import tpo.despacho.vos.UsuarioVO;

@Remote
public interface DespachoFacade {
	public int altaUsuario(UsuarioVO usuarioVO);
	public List<UsuarioVO> obtenerUsuarios();
	public boolean setEstadoActivoUsuario(UsuarioVO usuarioVO);
	public List<ArticuloVO> obtenerArticulos();
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad);
	public List<OrdenDeDespachoCompletaVO> obtenerOrdenesDeDespacho();
	public OrdenDeDespachoCompletaVO obtenerOrdenDeDespacho(int id, String nombrePortalWeb);
}
