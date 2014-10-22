package tpo.despacho.ws;

import javax.ejb.Remote;

import tpo.despacho.vos.OrdenDeDespachoVO;

@Remote
public interface AdministradorOrdenDeDespacho {
	public boolean recepcionOrdenDeDespacho(OrdenDeDespachoVO ordenDeDespachoVO);
}
