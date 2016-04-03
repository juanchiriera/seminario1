package servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;
import negocio.Cargo;

/**
 * Servlet implementation class CargosAsignadosServlet
 */
@WebServlet("/CargosAsignadosServlet")
public class CargosAsignadosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargosAsignadosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dniEmpleado = request.getParameter("dni");
		Collection<Cargo> listaCargos = Sistema.getInstancia().buscarCargosEmpleado(dniEmpleado);
		request.setAttribute("cargos", listaCargos);
		getServletContext().getRequestDispatcher("/ListaCargos.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
