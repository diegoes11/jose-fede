package tpo.despacho.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.logging.Logger;

import tpo.despacho.web.business.DespachoBusinessDelegate;
import tpo.ia.vos.VOOrdenDeDespachoCompleta;

@WebServlet("/ListarOrdenesDeDespacho")
public class ListarOrdenesDeDespacho extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(ListarOrdenesDeDespacho.class);
       
    public ListarOrdenesDeDespacho() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LOGGER.info("Listar ordenes de despacho...");
			List<VOOrdenDeDespachoCompleta> ordenesDeDespacho = DespachoBusinessDelegate.getInstancia().obtenerOrdenesDeDespacho();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/listarOrdenesDeDespacho.jsp");
			request.setAttribute("ordenesDeDespacho", ordenesDeDespacho);
		    dispatcher.forward(request, response);
		    LOGGER.info("Listar ordenes de despacho: OK");
		}
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Listar ordenes de despacho: Error desconocido - " + e.getStackTrace());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
