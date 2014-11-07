package tpo.despacho.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tpo.despacho.web.business.DespachoBusinessDelegate;
import tpo.ia.vos.VOUsuario;

@WebServlet("/SetEstadoActivoUsuario")
public class SetEstadoActivoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SetEstadoActivoUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			// Obtengo los datos
			String dniString = request.getParameter("dni");
			String estado = request.getParameter("activo");
			// Verifico que se hayan ingresado todos los datos
			if(!(dniString.length() == 0) && !(estado.length() == 0)){
				// Parseo el dni a long
				long dni = Long.parseLong(dniString);
				boolean activo = false;
				// Parseo el estado
				if(estado.equals("true"))
					activo = true;
				else {
					// Si el estado que pas� no es ni true ni false, no lo guardo..
					if(!estado.equals("false"))
					{
						response.setContentType("text/plain");
						PrintWriter pw=response.getWriter();
						pw.write("false");
					}
				}
				VOUsuario usuarioVO = new VOUsuario();
				usuarioVO.setDni(dni);
				usuarioVO.setActivo(activo);
				boolean estadoSeteado = DespachoBusinessDelegate.getInstancia().setEstadoActivoUsuario(usuarioVO);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("ListarUsuarios");
//			    dispatcher.forward(request, response);
				response.setContentType("text/plain");
				PrintWriter pw=response.getWriter();
				if(estadoSeteado){
					pw.write("true");
				}
				else{
					pw.write("false");
				}
			}
			else{ // Vuelvo al listado de usuarios
//				RequestDispatcher dispatcher = request.getRequestDispatcher("ListarUsuarios");
//			    dispatcher.forward(request, response);
				response.setContentType("text/plain");
				PrintWriter pw=response.getWriter();
				pw.write("false");
			}
		}
		catch(Exception e){
			response.setContentType("text/plain");
			PrintWriter pw=response.getWriter();
			pw.write("false");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
