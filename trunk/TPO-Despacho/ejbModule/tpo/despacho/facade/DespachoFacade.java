package tpo.despacho.facade;

import java.util.List;

import javax.ejb.Remote;

import tpo.despacho.vos.UsuarioVO;

@Remote
public interface DespachoFacade {
	public int altaUsuario(UsuarioVO usuarioVO);
	public List<UsuarioVO> obtenerUsuarios();
	public boolean setEstadoActivoUsuario(UsuarioVO usuarioVO);
}
