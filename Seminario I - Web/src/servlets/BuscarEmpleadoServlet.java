package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;
import negocio.Empleado;
import negocio.Escuela;

/**
 * Servlet implementation class BuscarEmpleadoServlet
 */
@WebServlet("/BuscarEmpleadoServlet")
public class BuscarEmpleadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarEmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{	
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String cuil = request.getParameter("cuil");
			String dni = request.getParameter("dni");
			boolean estado = Boolean.parseBoolean(request.getParameter("estado"));
			String [] escuelaAux = request.getParameter("escuela").split("-");
			int nroEscuela = Integer.parseInt(escuelaAux[1]);
			Escuela escuela = Sistema.getInstancia().buscarEscuela(nroEscuela);
			
			List<Empleado> empleados = Sistema.getInstancia().buscarEmpleados(nombre , apellido, cuil, dni, estado, escuela);
			request.setAttribute("empleados", empleados);
			getServletContext().getRequestDispatcher("/ListaEmpleados.jsp").forward(request, response);
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
