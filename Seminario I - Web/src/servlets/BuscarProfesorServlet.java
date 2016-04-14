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

/**
 * Servlet implementation class BuscarEmpleadoServlet
 */
@WebServlet("/BuscarProfesorServlet")
public class BuscarProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarProfesorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try{	
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String cuil = request.getParameter("cuil");
			String dni = request.getParameter("dni");
			boolean estado = Boolean.parseBoolean(request.getParameter("estado"));
			String materia = request.getParameter("materia");
			String curso = request.getParameter("curso");
			String division = request.getParameter("division");
			List<Empleado> docentes = Sistema.getInstancia().buscarDocentes(apellido, nombre, cuil, dni, materia, curso, division, estado);
			request.setAttribute("docentes", docentes);
			getServletContext().getRequestDispatcher("/ListaDocentes.jsp").forward(request, response);
//		}catch(Exception e){
//			getServletContext().getRequestDispatcher("/Error.jsp").forward(request, response);
//		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
