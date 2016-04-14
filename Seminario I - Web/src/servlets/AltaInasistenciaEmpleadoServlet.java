package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;
import negocio.Clase;
import negocio.Licencia;

/**
 * Servlet implementation class AltaInasistenciaServlet
 */
@WebServlet("/AltaInasistenciaEmpleadoServlet")
public class AltaInasistenciaEmpleadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaInasistenciaEmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fecha = request.getParameter("classDate");
		String dni = request.getParameter("dni");
		int codigoLicencia =Integer.parseInt(request.getParameter("licencia"));
		int cantDiasAusente = Integer.parseInt(request.getParameter("cantDiasAusente"));
		String [] dateAux = fecha.split("-");
		int year = Integer.parseInt(dateAux[0]);
		int month = Integer.parseInt(dateAux[1]);
		int day = Integer.parseInt(dateAux[2]);
		Date fechaF = new Date(year-1900,month-1,day);
		Licencia licencia = Sistema.getInstancia().buscarLicencia(codigoLicencia);
		Sistema.getInstancia().cargarInasistenciaEmpleado(dni, fechaF, licencia, 0, cantDiasAusente);
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
