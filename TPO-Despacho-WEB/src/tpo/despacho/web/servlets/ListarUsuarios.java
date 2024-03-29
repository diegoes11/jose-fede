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
import tpo.ia.vos.VOUsuario;

@WebServlet("/ListarUsuarios")
public class ListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ListarUsuarios.class);
	
    public ListarUsuarios() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LOGGER.info("Listar usuarios...");
			List<VOUsuario> usuarios = DespachoBusinessDelegate.getInstancia().obtenerUsuarios();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/listarUsuarios.jsp");
			request.setAttribute("usuarios", usuarios);
		    dispatcher.forward(request, response);
		    LOGGER.info("Listar usuarios: OK");
		}
		catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Listar usuarios: Error desconocido - " + e.getStackTrace());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
