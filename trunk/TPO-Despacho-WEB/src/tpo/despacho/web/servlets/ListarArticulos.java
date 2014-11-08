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
import tpo.ia.vos.VOArticuloCompleto;

@WebServlet("/ListarArticulos")
public class ListarArticulos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ListarArticulos.class);

    public ListarArticulos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LOGGER.info("Listar articulos...");
			List<VOArticuloCompleto> articulos = DespachoBusinessDelegate.getInstancia().obtenerArticulos();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/listarArticulos.jsp");
			request.setAttribute("articulos", articulos);
		    dispatcher.forward(request, response);
		    LOGGER.info("Listar articulos: OK");
		}
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Listar articulos: Error desconocido - " + e.getStackTrace());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
