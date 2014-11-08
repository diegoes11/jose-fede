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
import tpo.ia.vos.VOUsuario;

@WebServlet("/AltaUsuario")
public class AltaUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(AltaUsuario.class);
       
    public AltaUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/altaUsuario.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try{
			LOGGER.info("Alta usuario...");
			String dniString = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			// Verifico que se hayan ingresado todos los datos
			if(dniString.length() == 0 || nombre.length() == 0 || apellido.length() == 0){
				request.setAttribute("dni", dniString);
				request.setAttribute("nombre", nombre);
				request.setAttribute("apellido", apellido);
				request.setAttribute("error", "Se deben ingresar todos los datos solicitados.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/altaUsuario.jsp");
			    dispatcher.forward(request, response);
			    LOGGER.error("Alta usuario: No se ingresaron todos los datos solicitados.");
			}
			else{
				// Parseo el dni a long
				long dni = Long.parseLong(dniString);
				VOUsuario usuarioVO = new VOUsuario(dni, nombre, apellido);
				// Proceso el alta
				int altaProcesada = DespachoBusinessDelegate.getInstancia().altaUsuario(usuarioVO);
				if(altaProcesada == 0) // 0: Alta exitosa
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				    dispatcher.forward(request, response);
				    LOGGER.info("Alta usuario: OK");
				}
				else{ // Procesar errores
					request.setAttribute("nombre", nombre);
					request.setAttribute("error", "Ya existe un usuario con el DNI ingresado.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/altaUsuario.jsp");
				    dispatcher.forward(request, response);
				    LOGGER.error("Alta usuario: Existe un usuario con el DNI ingresado.");
				}
			}
		}
		// Caso: ERROR al parsear el DNI.
		catch(NumberFormatException e){
			String dniString = request.getParameter("dni");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			request.setAttribute("dni", dniString);
			request.setAttribute("nombre", nombre);
			request.setAttribute("apellido", apellido);
			request.setAttribute("error", "El DNI ingresado es incorrecto. Debe contener solamente números.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/altaUsuario.jsp");
		    dispatcher.forward(request, response);
		    LOGGER.error("Alta usuario: El DNI ingresado es incorrecto - " + e.getStackTrace());
		}
		catch(Exception e){
			e.printStackTrace();
			LOGGER.error("Alta usuario: Error desconocido - " + e.getStackTrace());
		}
	}

}
