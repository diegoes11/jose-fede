package tpo.despacho.sessions;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import tpo.despacho.entidades.Usuario;
import tpo.despacho.facade.DespachoFacade;
import tpo.ia.vos.VOInformeAuditoria;
import tpo.ia.vos.VOUsuario;

@Stateless
public class AdministradorUsuariosBean implements AdministradorUsuarios {
	// Atributos
	private static final Logger LOGGER = Logger.getLogger(AdministradorUsuariosBean.class);
	
	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
	@EJB
	private DespachoFacade despachoFacade;
	
	// Constructor
    public AdministradorUsuariosBean() {
    }
    
    // Métodos
    private Usuario buscarUsuario(long dni){
    	Usuario usuario = (Usuario)manager.find(Usuario.class, dni);
    	return usuario;
    }
    
    public int altaUsuario(VOUsuario usuarioVO){
    	try {
    		LOGGER.info("Alta usuario...");
    		// Verifico que no exista un usuario con el DNI ingresado
        	Usuario usuario = buscarUsuario(usuarioVO.getDni());
        	
        	// Si no existe, lo creo y lo guardo en la BD.
        	if (usuario == null){
        		usuario = new Usuario(usuarioVO.getDni(), usuarioVO.getNombre(), usuarioVO.getApellido());
        		manager.persist(usuario);
        		LOGGER.info("Alta usuario: OK");
        		// INTEGRACIÓN
        		//despachoFacade.EnviarInforme(new VOInformeAuditoria(usuario.obtenerInformeAlta()));
        		return 0; // 0: Usuario creado con éxito
        	}
        	LOGGER.error("Alta usuario: Existe un usuario con el DNI recibido.");
        	return 1;
    	} 
    	catch (Exception e) {
    		e.printStackTrace();
    		LOGGER.error("Alta usuario: Error desconocido - " + e.getStackTrace());
    		return 1; // 1: Ya existe un usuario con el mismo nombre y password.
    	}
    }
    
	public List<VOUsuario> obtenerUsuarios() {
		try {
			LOGGER.info("Búsqueda de usuarios...");
			String query = "SELECT u FROM Usuario u";
	    	List<Usuario> usuarios = (List<Usuario>)manager.createQuery(query, Usuario.class).getResultList();
	    	List<VOUsuario> usuariosVO = new ArrayList<VOUsuario>(usuarios.size());
	    	// Convierto la lista de Usuario a UsuarioVO
	    	for(Usuario u : usuarios){
	    		VOUsuario usuarioVO = u.getUsuarioVO();
	    		usuariosVO.add(usuarioVO);
	    	}
	    	LOGGER.info("Búsqueda de usuarios: OK");
	    	return usuariosVO;
		}
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Búsqueda de usuarios: Error desconocido - " + e.getStackTrace());
			return null;
		}
    	
    }
	
	public boolean setEstadoActivoUsuario(VOUsuario usuarioVO){
		try{
			LOGGER.info("Establecer estado activo a un usuario...");
			Usuario usuario = buscarUsuario(usuarioVO.getDni());
			if(usuario == null) // Si no existe, retorno error.
				return false;
			usuario.setActivo(usuarioVO.isActivo());
			manager.merge(usuario);
			LOGGER.info("Establecer estado activo a un usuario: OK");
			// INTEGRACIÓN
			// despachoFacade.EnviarInforme(new VOInformeAuditoria(usuario.obtenerInformeCambioEstado()));
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Establecer estado activo a un usuario: Error desconocido - " + e.getStackTrace());
			return false; // Error
		}
	}
	
}
