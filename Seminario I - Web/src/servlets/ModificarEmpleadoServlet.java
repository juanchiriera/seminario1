package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;
import negocio.Empleado;

/**
 * Servlet implementation class ModificarEmpleadoServlet
 */
@WebServlet("/ModificarEmpleadoServlet")
public class ModificarEmpleadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarEmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
		Empleado empleado = Sistema.getInstancia().buscarEmpleado(dni);
		request.setAttribute("empleado", empleado);
		getServletContext().getRequestDispatcher("/ModificarEmpleado.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
