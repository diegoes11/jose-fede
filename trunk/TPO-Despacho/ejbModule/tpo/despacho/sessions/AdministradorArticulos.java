package tpo.despacho.sessions;

import java.util.List;

import javax.ejb.Local;

import tpo.despacho.vos.ArticuloVO;

@Local
public interface AdministradorArticulos {
	public boolean recepcionDeArticulos(int idSolicitudDeArticulo, int cantidad);
	public List<ArticuloVO> obtenerArticulos();
}
