package tpo.despacho.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import tpo.despacho.facade.DespachoFacade;
import tpo.ia.vos.OrdenDeDespachoVO;

// URL WSDL: http://localhost:8080/TPO-Despacho/AdministradorOrdenDeDespachoBean?wsdl

@Stateless
@WebService
public class AdministradorOrdenDeDespachoBean implements AdministradorOrdenDeDespacho {

	@EJB
	private DespachoFacade despachoFacade;
	
    public AdministradorOrdenDeDespachoBean() {
    }

    @WebMethod
	public boolean recepcionOrdenDeDespacho(OrdenDeDespachoVO ordenDeDespachoVO) {
    	try{
    		return despachoFacade.recepcionOrdenDeDespacho(ordenDeDespachoVO);
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return false;
    	}
	}
}
