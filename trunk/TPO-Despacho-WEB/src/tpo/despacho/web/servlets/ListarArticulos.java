package tpo.despacho.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tpo.despacho.web.business.DespachoBusinessDelegate;
import tpo.ia.vos.VOArticulo;

@WebServlet("/ListarArticulos")
public class ListarArticulos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListarArticulos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<VOArticulo> articulos = DespachoBusinessDelegate.getInstancia().obtenerArticulos();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listarArticulos.jsp");
		request.setAttribute("articulos", articulos);
	    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
