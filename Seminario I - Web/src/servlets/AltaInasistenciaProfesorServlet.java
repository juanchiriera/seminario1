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
@WebServlet("/AltaInasistenciaProfesorServlet")
public class AltaInasistenciaProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaInasistenciaProfesorServlet() {
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
		float horasCatedraAusente = Float.parseFloat(request.getParameter("horasCatedraAusente"));
		int cantDiasAusente = Integer.parseInt(request.getParameter("cantDiasAusente"));
		String [] dateAux = fecha.split("-");
		int year = Integer.parseInt(dateAux[0]);
		int month = Integer.parseInt(dateAux[1]);
		int day = Integer.parseInt(dateAux[2]);
		Date fechaF = new Date(year-1900,month-1,day);
		int codigoClase = Integer.parseInt(request.getParameter("clase"));
		Clase clase = Sistema.getInstancia().buscarClase(codigoClase);
		Sistema.getInstancia().cantidadSemandasMes(fechaF.getMonth());
		Licencia licencia = Sistema.getInstancia().buscarLicencia(codigoLicencia);
//		TODO Ver que onda los parametros que paso
//		Sistema.getInstancia().cargarInasistenciaEmpleado(dni, fechaF, licencia, horasCatedra, cantClasesAusente);
		Sistema.getInstancia().cargarInasistenciaDocente(dni, fechaF, licencia, horasCatedraAusente, cantDiasAusente, clase);
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
