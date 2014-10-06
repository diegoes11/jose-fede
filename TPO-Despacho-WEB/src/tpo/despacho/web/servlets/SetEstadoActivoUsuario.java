package tpo.despacho.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tpo.despacho.vos.UsuarioVO;
import tpo.despacho.web.business.DespachoBusinessDelegate;

@WebServlet("/SetEstadoActivoUsuario")
public class SetEstadoActivoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SetEstadoActivoUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setDni(dni);
			usuarioVO.setActivo(activo);
			boolean estadoSeteado = DespachoBusinessDelegate.getInstancia().setEstadoActivoUsuario(usuarioVO);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListarUsuarios");
		    dispatcher.forward(request, response);
		}
		else{ // Vuelvo al listado de usuarios
			RequestDispatcher dispatcher = request.getRequestDispatcher("ListarUsuarios");
		    dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
