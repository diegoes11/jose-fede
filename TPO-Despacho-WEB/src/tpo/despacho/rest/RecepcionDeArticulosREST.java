package tpo.despacho.rest;

import javax.ws.rs.*;

import tpo.despacho.web.business.DespachoBusinessDelegate;
import tpo.ia.vos.RecepcionSolicitudDeArticuloVO;

@Path("/RecepcionDeArticulos")
public class RecepcionDeArticulosREST {

	//URL REST: http://localhost:8080/TPO-Despacho-WEB/RecepcionDeArticulos/ID?cantidad=CANT
	@POST
	@Path("/")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public boolean actualizarCantidadSolicitudDeArticulo(RecepcionSolicitudDeArticuloVO json) {
		return DespachoBusinessDelegate.getInstancia().recepcionDeArticulos(json.getId(), json.getCantidad());
	}
}
