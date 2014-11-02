package tpo.despacho.sessions;

import java.util.List;

import javax.ejb.Local;

import tpo.despacho.vos.OrdenDeDespachoCompletaVO;

@Local
public interface AdministradorOrdenesDeDespacho {
	public List<OrdenDeDespachoCompletaVO> obtenerOrdenesDeDespacho();
	public OrdenDeDespachoCompletaVO obtenerOrdenDeDespacho(int id, String nombrePortalWeb);
}
