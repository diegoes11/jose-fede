package tpo.despacho.facade;

import javax.ejb.Remote;

import tpo.despacho.vos.UsuarioVO;

@Remote
public interface DespachoFacade {
	public int altaUsuario(UsuarioVO usuarioVO);
}
