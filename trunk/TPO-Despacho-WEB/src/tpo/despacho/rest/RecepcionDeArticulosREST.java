package tpo.despacho.rest;

import javax.ws.rs.*;

import tpo.despacho.web.business.DespachoBusinessDelegate;

@Path("/RecepcionDeArticulos")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class RecepcionDeArticulosREST {

	//URL REST: http://localhost:8080/TPO-Despacho-WEB/RecepcionDeArticulos/ID?cantidad=CANT
	@POST
	@Path("/{id}")
	public boolean actualizarCantidadSolicitudDeArticulo(@PathParam("id")int idSolicitudDeArticulo, @QueryParam("cantidad")int cantidad) {
		return DespachoBusinessDelegate.getInstancia().recepcionDeArticulos(idSolicitudDeArticulo, cantidad);
	}
}
