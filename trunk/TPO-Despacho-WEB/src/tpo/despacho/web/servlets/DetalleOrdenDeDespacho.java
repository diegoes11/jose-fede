package tpo.despacho.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tpo.despacho.vos.OrdenDeDespachoCompletaVO;
import tpo.despacho.web.business.DespachoBusinessDelegate;

@WebServlet("/DetalleOrdenDeDespacho")
public class DetalleOrdenDeDespacho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetalleOrdenDeDespacho() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		String portalWeb = request.getParameter("portalWeb");
		if ((idString != null && !idString.equals("")) && (portalWeb != null && !portalWeb.equals("")))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			OrdenDeDespachoCompletaVO ordenDeDespacho = DespachoBusinessDelegate.getInstancia().obtenerOrdenDeDespacho(id, portalWeb);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/detalleOrdenDeDespacho.jsp");
			request.setAttribute("ordenDeDespacho", ordenDeDespacho);
		    dispatcher.forward(request, response);
		}
		else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		    dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
