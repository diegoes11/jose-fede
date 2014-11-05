package tpo.despacho.ws;

import javax.ejb.Remote;

import tpo.ia.vos.VOOrdenDeDespacho;

@Remote
public interface AdministradorOrdenDeDespacho {
	public boolean recepcionOrdenDeDespacho(VOOrdenDeDespacho ordenDeDespachoVO);
}
