package tpo.despacho.sessions;

import javax.ejb.Local;

@Local
public interface AdministradorArticulos {
	public void actualizarCantidadSolicitudDeArticulo(int idSolicitudDeArticulo, int cantidad);
}
