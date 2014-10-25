package tpo.despacho.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tpo.despacho.vos.ArticuloVO;
import tpo.despacho.web.business.DespachoBusinessDelegate;

@WebServlet("/ListarArticulos")
public class ListarArticulos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListarArticulos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ArticuloVO> articulos = DespachoBusinessDelegate.getInstancia().obtenerArticulos();
		List<ArticuloVO> electrodomesticos = obtenerArticulosPorTipo(articulos, "Electrodomesticos");
		List<ArticuloVO> moda = obtenerArticulosPorTipo(articulos, "Moda");
		List<ArticuloVO> muebles = obtenerArticulosPorTipo(articulos, "Muebles");
		List<ArticuloVO> ninos = obtenerArticulosPorTipo(articulos, "Ninos");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listarArticulos.jsp");
		request.setAttribute("electrodomesticos", electrodomesticos);
		request.setAttribute("moda", moda);
		request.setAttribute("muebles", muebles);
		request.setAttribute("ninos", ninos);
	    dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private List<ArticuloVO> obtenerArticulosPorTipo(List<ArticuloVO> articulos, String tipo){
		List<ArticuloVO> arts = new ArrayList<ArticuloVO>();
		for(ArticuloVO a : articulos){
			if(a.getTipo().equals(tipo))
			{
				arts.add(a);
			}
		}
		return arts;
	}
}
