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
import negocio.ConCargo;
import negocio.Empleado;
import negocio.Escuela;
import negocio.Licencia;
import negocio.SinCargo;

/**
 * Servlet implementation class AltaInasistenciaServletNew
 */
@WebServlet("/AltaInasistenciaEmpleadoServletNew")
public class AltaInasistenciaEmpleadoServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaInasistenciaEmpleadoServletNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			List<ConCargo> empleados = Sistema.getInstancia().recuperarEmpleadosConCargo();
			request.setAttribute("empleados", empleados);
			List<Licencia> licencias = Sistema.getInstancia().recuperarLicencias();
			request.setAttribute("licencias", licencias);
			getServletContext().getRequestDispatcher("/AltaInasistenciaEmpleado.jsp").forward(request, response);
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
