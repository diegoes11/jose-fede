package tpo.despacho.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import tpo.despacho.web.business.DespachoBusinessDelegate;

@Path("/RecepcionDeArticulos")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class RecepcionDeArticulosREST {

	@GET
	@Path("/{id}")
	public boolean actualizarCantidadSolicitudDeArticulo(@PathParam("id")int idSolicitudDeArticulo, @QueryParam("cantidad")int cantidad) {
		return DespachoBusinessDelegate.getInstancia().recepcionDeArticulos(idSolicitudDeArticulo, cantidad);
	}
}
