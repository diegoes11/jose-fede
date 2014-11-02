package tpo.despacho.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tpo.despacho.vos.OrdenDeDespachoCompletaVO;
import tpo.despacho.web.business.DespachoBusinessDelegate;

@WebServlet("/ListarOrdenesDeDespacho")
public class ListarOrdenesDeDespacho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListarOrdenesDeDespacho() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<OrdenDeDespachoCompletaVO> ordenesDeDespacho = DespachoBusinessDelegate.getInstancia().obtenerOrdenesDeDespacho();
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listarOrdenesDeDespacho.jsp");
		request.setAttribute("ordenesDeDespacho", ordenesDeDespacho);
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
