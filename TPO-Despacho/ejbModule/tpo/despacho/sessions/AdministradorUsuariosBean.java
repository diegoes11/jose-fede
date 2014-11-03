package tpo.despacho.sessions;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tpo.despacho.entidades.Usuario;
import tpo.ia.vos.UsuarioVO;

@Stateless
public class AdministradorUsuariosBean implements AdministradorUsuarios {
	// Atributos
	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
	// Constructor
    public AdministradorUsuariosBean() {
    }
    
    // Métodos
    private Usuario buscarUsuario(long dni){
    	Usuario usuario = (Usuario)manager.find(Usuario.class, dni);
    	return usuario;
    }
    
    public int altaUsuario(UsuarioVO usuarioVO){
    	// Verifico que no exista un usuario con el DNI ingresado
    	Usuario usuario = buscarUsuario(usuarioVO.getDni());
    	
    	// Si no existe, lo creo y lo guardo en la BD.
    	if (usuario == null){
    		usuario = new Usuario(usuarioVO.getDni(), usuarioVO.getNombre(), usuarioVO.getApellido());
    		manager.persist(usuario);
    		return 0; // 0: Usuario creado con éxito
    	}
    	return 1; // 1: Ya existe un usuario con el mismo nombre y password.
    }
    
	public List<UsuarioVO> obtenerUsuarios(){
    	String query = "SELECT u FROM Usuario u";
    	List<Usuario> usuarios = (List<Usuario>)manager.createQuery(query, Usuario.class).getResultList();
    	List<UsuarioVO> usuariosVO = new ArrayList<UsuarioVO>(usuarios.size());
    	// Convierto la lista de Usuario a UsuarioVO
    	for(Usuario u : usuarios){
    		UsuarioVO usuarioVO = u.getUsuarioVO();
    		usuariosVO.add(usuarioVO);
    	}
    	return usuariosVO;
    }
	
	public boolean setEstadoActivoUsuario(UsuarioVO usuarioVO){
		try{
			Usuario usuario = buscarUsuario(usuarioVO.getDni());
			if(usuario == null) // Si no existe, retorno error.
				return false;
			usuario.setActivo(usuarioVO.isActivo());
			manager.merge(usuario);
			return true;
		}
		catch(Exception e){
			return false; // Error
		}
	}
	
}
