package tpo.despacho.sessions;

import java.util.List;

import javax.ejb.Local;

import tpo.ia.vos.VOArticulo;
import tpo.ia.vos.VOArticuloCompleto;

@Local
public interface AdministradorArticulos {
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad);
	public List<VOArticuloCompleto> obtenerArticulos();
	public boolean altaArticulo(VOArticulo articuloVO);
}
