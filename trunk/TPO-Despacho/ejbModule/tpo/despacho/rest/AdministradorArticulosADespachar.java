package tpo.despacho.rest;

import javax.ws.rs.*;

@Path("/articulosadespachar")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class AdministradorArticulosADespachar {
    @GET
    @Path("/recepcion/{idSolicitudDeArticulo}")
    public boolean recepcionArticulo(@PathParam("idSolicitudDeArticulo") int idSolicitudDeArticulo, @QueryParam("cantidad") int cantidad) {
    	
    	return true;
    }
}
