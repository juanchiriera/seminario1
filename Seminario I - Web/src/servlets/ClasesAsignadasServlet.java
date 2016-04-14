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

import negocio.Clase;

/**
 * Servlet implementation class ClasesAsignadasServlet
 */
@WebServlet("/ClasesAsignadasServlet")
public class ClasesAsignadasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClasesAsignadasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String dniEmpleado = request.getParameter("dni");
			Collection<Clase> listaClases = Sistema.getInstancia().buscarClasesDeEmpleado(dniEmpleado);
			request.setAttribute("clases", listaClases);
			getServletContext().getRequestDispatcher("/ListaClases.jsp").forward(request, response);
		}catch(Exception e){
			getServletContext().getRequestDispatcher("/Error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
