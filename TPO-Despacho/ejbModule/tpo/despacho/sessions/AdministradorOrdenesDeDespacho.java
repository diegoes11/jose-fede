package tpo.despacho.sessions;

import java.util.List;

import javax.ejb.Local;

import tpo.ia.vos.VOOrdenDeDespachoCompleta;
import tpo.ia.vos.VOOrdenDeDespacho;

@Local
public interface AdministradorOrdenesDeDespacho {
	public List<VOOrdenDeDespachoCompleta> obtenerOrdenesDeDespacho();
	public VOOrdenDeDespachoCompleta obtenerOrdenDeDespacho(int id, String nombrePortalWeb);
	public boolean recepcionOrdenDeDespacho(VOOrdenDeDespacho ordenDeDespachoVO);
}
