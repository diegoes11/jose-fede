package tpo.despacho.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tpo.despacho.entidades.Articulo;
import tpo.despacho.entidades.DetalleOrdenDeDespacho;
import tpo.despacho.entidades.OrdenDeDespacho;
import tpo.despacho.entidades.PortalWeb;
import tpo.despacho.vos.DetalleOrdenDeDespachoVO;
import tpo.despacho.vos.OrdenDeDespachoVO;

@Stateless
@WebService
public class AdministradorOrdenDeDespachoBean implements AdministradorOrdenDeDespacho {

	@PersistenceContext(unitName="DespachoBD")
	private EntityManager manager;
	
    public AdministradorOrdenDeDespachoBean() {
    }

    @WebMethod
	public boolean recepcionOrdenDeDespacho(OrdenDeDespachoVO ordenDeDespachoVO) {
    	PortalWeb portalWeb = manager.find(PortalWeb.class, ordenDeDespachoVO.getNombrePortalWeb());
    	if(portalWeb != null)
    	{
    		OrdenDeDespacho ordenDeDespacho = new OrdenDeDespacho();
    		ordenDeDespacho.setIdOrdenDeDespacho(ordenDeDespachoVO.getIdOrdenDeDespacho());
    		List<DetalleOrdenDeDespachoVO> detallesOrdenDeDespachoVO = ordenDeDespachoVO.getDetallesOrdenDeDespachoVO();
    		List<DetalleOrdenDeDespacho> detallesOrdenDeDespacho = new ArrayList<DetalleOrdenDeDespacho>();
    		for(DetalleOrdenDeDespachoVO doddvo : detallesOrdenDeDespachoVO)
    		{
    			Articulo articulo = manager.find(Articulo.class, doddvo.getCodigoArticulo());
    			if(articulo != null)
    			{
        			DetalleOrdenDeDespacho dodd = new DetalleOrdenDeDespacho();
    				dodd.setArticulo(articulo);
    				dodd.setCantidad(doddvo.getCantidad());
    				dodd.setEstado("incompleto");
    				detallesOrdenDeDespacho.add(dodd);
    			}
    		}
    		ordenDeDespacho.setDetallesOrdenDeDespacho(detallesOrdenDeDespacho);
    		
    		//-	Por cada artículo, se debe obtener el Deposito que lo administra y solicitarlo asincrónicamente
    		//-	Se debe registrar la solicitud de articulo por Deposito

    		
    		
    	}
    	
    	return false;
	}
}
