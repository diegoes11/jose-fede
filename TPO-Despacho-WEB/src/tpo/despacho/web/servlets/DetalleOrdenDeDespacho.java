package tpo.despacho.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.logging.Logger;

import tpo.despacho.web.business.DespachoBusinessDelegate;
import tpo.ia.vos.VOOrdenDeDespachoCompleta;

@WebServlet("/DetalleOrdenDeDespacho")
public class DetalleOrdenDeDespacho extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DetalleOrdenDeDespacho.class);
       
    public DetalleOrdenDeDespacho() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LOGGER.info("Listar detalle orden de despacho...");
			String idString = request.getParameter("id");
			String portalWeb = request.getParameter("portalWeb");
			if ((idString != null && !idString.equals("")) && (portalWeb != null && !portalWeb.equals("")))
			{
				int id = Integer.parseInt(request.getParameter("id"));
				VOOrdenDeDespachoCompleta ordenDeDespacho = DespachoBusinessDelegate.getInstancia().obtenerOrdenDeDespacho(id, portalWeb);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/detalleOrdenDeDespacho.jsp");
				request.setAttribute("ordenDeDespacho", ordenDeDespacho);
			    dispatcher.forward(request, response);
			    LOGGER.info("Listar detalle orden de despacho: OK");
			}
			else{
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			    dispatcher.forward(request, response);
			    LOGGER.error("Listar detalle orden de despacho: No existen todos los datos solicitados.");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Listar detalle orden de despacho: Error desconocido - " + e.getStackTrace());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
