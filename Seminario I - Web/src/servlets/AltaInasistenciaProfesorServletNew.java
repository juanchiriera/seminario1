package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;
import negocio.Clase;
import negocio.Empleado;
import negocio.Escuela;
import negocio.Licencia;
import negocio.SinCargo;

/**
 * Servlet implementation class AltaInasistenciaServletNew
 */
@WebServlet("/AltaInasistenciaProfesorServletNew")
public class AltaInasistenciaProfesorServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaInasistenciaProfesorServletNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			List<SinCargo> docentes = Sistema.getInstancia().recuperarProfesores();
			request.setAttribute("docentes", docentes);
			List<Licencia> licencias = Sistema.getInstancia().recuperarLicencias();
			request.setAttribute("licencias", licencias);
			List<Clase> clases = Sistema.getInstancia().getClases();
			request.setAttribute("clases", clases);
			getServletContext().getRequestDispatcher("/AltaInasistenciaProfesor.jsp").forward(request, response);
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
