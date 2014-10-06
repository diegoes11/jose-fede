package tpo.despacho.sessions;

import java.util.List;

import javax.ejb.Local;

import tpo.despacho.vos.UsuarioVO;

@Local
public interface AdministradorUsuarios {
	public int altaUsuario(UsuarioVO usuarioVO);
	public List<UsuarioVO> obtenerUsuarios();
	public boolean setEstadoActivoUsuario(UsuarioVO usuarioVO);
}
