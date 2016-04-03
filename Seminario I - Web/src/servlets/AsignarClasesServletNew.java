package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;
import negocio.Clase;

/**
 * Servlet implementation class AsignarClasesServlet
 */
@WebServlet("/AsignarClasesServletNew")
public class AsignarClasesServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignarClasesServletNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dniProfesorAux = request.getParameter("profesor");
		String [] profesorArray = dniProfesorAux.split("-");
		String dni = profesorArray[1].trim();
		
		String nroClaseAux = request.getParameter("clase");
		String [] claseArray = nroClaseAux.split("-");
		int nroClase = Integer.parseInt(claseArray[0]);
		
		Clase clase = Sistema.getInstancia().buscarClase(nroClase);
		boolean estado = Boolean.parseBoolean(request.getParameter("estado"));
		Sistema.getInstancia().altaClaseEmpleado(dni, clase, estado);
		response.sendRedirect("home.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
