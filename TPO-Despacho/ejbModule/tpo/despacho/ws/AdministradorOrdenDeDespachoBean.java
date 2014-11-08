package tpo.despacho.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.jboss.logging.Logger;

import tpo.despacho.facade.DespachoFacade;
import tpo.ia.vos.VOOrdenDeDespacho;

// URL WSDL: http://localhost:8080/TPO-Despacho/AdministradorOrdenDeDespachoBean?wsdl

@Stateless
@WebService
public class AdministradorOrdenDeDespachoBean implements AdministradorOrdenDeDespacho {

	private static final Logger LOGGER = Logger.getLogger(AdministradorOrdenDeDespachoBean.class);
	
	@EJB
	private DespachoFacade despachoFacade;
	
    public AdministradorOrdenDeDespachoBean() {
    }

    @WebMethod
	public boolean recepcionOrdenDeDespacho(VOOrdenDeDespacho ordenDeDespachoVO) {
    	try{
    		LOGGER.info("Recepción de orden de despacho: OK");
    		return despachoFacade.recepcionOrdenDeDespacho(ordenDeDespachoVO);
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		LOGGER.error("Recepción de orden de despacho: Error desconocido - " + e.getStackTrace());
    		return false;
    	}
	}
}
