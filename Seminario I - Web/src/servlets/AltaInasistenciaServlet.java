package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;
import negocio.Licencia;

/**
 * Servlet implementation class AltaInasistenciaServlet
 */
@WebServlet("/AltaInasistenciaServlet")
public class AltaInasistenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaInasistenciaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fecha = request.getParameter("classDate");
		String dni = request.getParameter("dni");
		String codigoLicencia = request.getParameter("codigo");
		int semanas = Integer.parseInt(request.getParameter("semanas"));
		float horasCatedra = Float.parseFloat(request.getParameter("horasCatedra"));
		int cantClasesAusente = Integer.parseInt(request.getParameter("cantClasesAusente"));
		String [] dateAux = fecha.split("-");
		int year = Integer.parseInt(dateAux[0]);
		int month = Integer.parseInt(dateAux[1]);
		int day = Integer.parseInt(dateAux[2]);
		Date fechaF = new Date(year,month,day);
		Licencia licencia = Sistema.getInstancia().buscarLicencia(codigoLicencia);
//		TODO Ver que onda los parametros que paso
		Sistema.getInstancia().cargarInasistenciaEmpleado(dni, fechaF, licencia, semanas, horasCatedra, cantClasesAusente);
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
