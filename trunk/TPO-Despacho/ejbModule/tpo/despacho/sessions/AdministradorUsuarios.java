package tpo.despacho.sessions;

import java.util.List;

import javax.ejb.Local;

import tpo.ia.vos.VOUsuario;

@Local
public interface AdministradorUsuarios {
	public int altaUsuario(VOUsuario usuarioVO);
	public List<VOUsuario> obtenerUsuarios();
	public boolean setEstadoActivoUsuario(VOUsuario usuarioVO);
}
