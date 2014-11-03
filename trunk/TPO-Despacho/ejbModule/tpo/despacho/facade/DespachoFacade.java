package tpo.despacho.facade;

import java.util.List;

import javax.ejb.Remote;

import tpo.ia.vos.ArticuloVO;
import tpo.ia.vos.OrdenDeDespachoCompletaVO;
import tpo.ia.vos.OrdenDeDespachoVO;
import tpo.ia.vos.UsuarioVO;

@Remote
public interface DespachoFacade {
	public int altaUsuario(UsuarioVO usuarioVO);
	public List<UsuarioVO> obtenerUsuarios();
	public boolean setEstadoActivoUsuario(UsuarioVO usuarioVO);
	public List<ArticuloVO> obtenerArticulos();
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad);
	public List<OrdenDeDespachoCompletaVO> obtenerOrdenesDeDespacho();
	public OrdenDeDespachoCompletaVO obtenerOrdenDeDespacho(int id, String nombrePortalWeb);
	public boolean altaArticulo(ArticuloVO articuloVO);
	public boolean recepcionOrdenDeDespacho(OrdenDeDespachoVO ordenDeDespachoVO);
}
