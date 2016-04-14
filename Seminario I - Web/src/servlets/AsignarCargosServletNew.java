package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;
import negocio.Cargo;
import negocio.Clase;

/**
 * Servlet implementation class AsignarClasesServlet
 */
@WebServlet("/AsignarCargosServletNew")
public class AsignarCargosServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignarCargosServletNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String dniEmpleadoAux = request.getParameter("empleado");
			String [] empleadoArray = dniEmpleadoAux.split("-");
			String dni = empleadoArray[1].trim();
			
			String idCargoAux = request.getParameter("cargo");
			String [] cargoArray = idCargoAux.split("-");
			int idCargo = Integer.parseInt(cargoArray[0].trim());
			
			Cargo cargo = Sistema.getInstancia().buscarCargo(idCargo);
			Sistema.getInstancia().asignarCargo(dni, cargo);
			response.sendRedirect("home.jsp");
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
