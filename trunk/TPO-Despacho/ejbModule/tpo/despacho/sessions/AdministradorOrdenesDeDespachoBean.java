package tpo.despacho.sessions;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tpo.despacho.entidades.OrdenDeDespacho;
import tpo.despacho.vos.OrdenDeDespachoCompletaVO;

@Stateless
public class AdministradorOrdenesDeDespachoBean implements AdministradorOrdenesDeDespacho {

	// Atributos
	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
    public AdministradorOrdenesDeDespachoBean() {
    	
    }
    
    public List<OrdenDeDespachoCompletaVO> obtenerOrdenesDeDespacho(){
		try{
			String query = "SELECT odd FROM OrdenDeDespacho odd";
	    	List<OrdenDeDespacho> ordenesDeDespacho = (List<OrdenDeDespacho>)manager.createQuery(query, OrdenDeDespacho.class).getResultList();
	    	List<OrdenDeDespachoCompletaVO> ordenesDeDespachoVO = new ArrayList<OrdenDeDespachoCompletaVO>(ordenesDeDespacho.size());
	    	// Convierto la lista de OrdenDeDespacho a OrdenDeDespachoCompletaVO
	    	for(OrdenDeDespacho odd : ordenesDeDespacho){
	    		OrdenDeDespachoCompletaVO ordenDeDespachoVO = odd.getOrdenDeDespachoVO();
	    		ordenesDeDespachoVO.add(ordenDeDespachoVO);
	    	}
	    	return ordenesDeDespachoVO;
		}
		catch(Exception e){
			e.printStackTrace();
			return new ArrayList<OrdenDeDespachoCompletaVO>();
		}
	}

}
