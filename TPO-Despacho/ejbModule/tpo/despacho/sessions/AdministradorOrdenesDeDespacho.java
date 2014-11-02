package tpo.despacho.sessions;

import java.util.List;

import javax.ejb.Local;

import tpo.despacho.vos.OrdenDeDespachoCompletaVO;
import tpo.despacho.vos.OrdenDeDespachoVO;

@Local
public interface AdministradorOrdenesDeDespacho {
	public List<OrdenDeDespachoCompletaVO> obtenerOrdenesDeDespacho();
	public OrdenDeDespachoCompletaVO obtenerOrdenDeDespacho(int id, String nombrePortalWeb);
	public boolean recepcionOrdenDeDespacho(OrdenDeDespachoVO ordenDeDespachoVO);
}
