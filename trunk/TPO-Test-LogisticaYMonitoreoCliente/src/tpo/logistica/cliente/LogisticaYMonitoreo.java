package tpo.logistica.cliente;

import java.util.List;

import tpo.logistica.wscliente.*;

public class LogisticaYMonitoreo {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        AdministradorOrdenDeDespachoBeanService service1 = new AdministradorOrdenDeDespachoBeanService();
	        System.out.println("Create Web Service...");
	        AdministradorOrdenDeDespachoBean port1 = service1.getAdministradorOrdenDeDespachoBeanPort();
	        System.out.println("Call Web Service Operation...");
	        
	        // CREO UNA ORDEN DE DESPACHO
	        OrdenDeDespachoVO odd = new OrdenDeDespachoVO();
	        odd.setIdOrdenDeDespacho(4);
	        odd.setNombrePortalWeb("PORTALWEB1");
	        odd.setNombreLogisticaYMonitoreo("LOGISTICA1");
	        
	        List<DetalleOrdenDeDespachoVO> detalles = odd.getDetallesOrdenDeDespachoVO();
	        DetalleOrdenDeDespachoVO dodd1 = new DetalleOrdenDeDespachoVO();
	        dodd1.setCodigoArticulo(1351953);
	        dodd1.setCantidad(48);
	        detalles.add(dodd1);
	        DetalleOrdenDeDespachoVO dodd2 = new DetalleOrdenDeDespachoVO();
	        dodd2.setCodigoArticulo(1607594);
	        dodd2.setCantidad(29);
	        detalles.add(dodd2);
	        
	        System.out.println("Server said: " + port1.recepcionOrdenDeDespacho(odd));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        //System.out.println("Create Web Service...");
	        //AdministradorOrdenDeDespachoBean port2 = service1.getAdministradorOrdenDeDespachoBeanPort();
	        //System.out.println("Call Web Service Operation...");
	        //System.out.println("Server said: " + port2.recepcionOrdenDeDespacho(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
