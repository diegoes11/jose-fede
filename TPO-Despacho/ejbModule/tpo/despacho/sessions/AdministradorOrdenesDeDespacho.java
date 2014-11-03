package tpo.despacho.sessions;

import java.util.List;

import javax.ejb.Local;

import tpo.ia.vos.OrdenDeDespachoCompletaVO;
import tpo.ia.vos.OrdenDeDespachoVO;

@Local
public interface AdministradorOrdenesDeDespacho {
	public List<OrdenDeDespachoCompletaVO> obtenerOrdenesDeDespacho();
	public OrdenDeDespachoCompletaVO obtenerOrdenDeDespacho(int id, String nombrePortalWeb);
	public boolean recepcionOrdenDeDespacho(OrdenDeDespachoVO ordenDeDespachoVO);
}
