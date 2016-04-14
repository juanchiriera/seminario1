package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;

/**
 * Servlet implementation class AltaEmpleadoServlet
 */
@WebServlet("/AltaEmpleadoServlet")
public class AltaEmpleadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaEmpleadoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String apellido = request.getParameter("apellido");
			String nombre = request.getParameter("nombre");
			String cuil = request.getParameter("cuil");
			String fechaNacim = request.getParameter("classDate");
			String dni = request.getParameter("dni");
			int antiguedad = Integer.parseInt(request.getParameter("antiguedad"));
			String [] dateAux = fechaNacim.split("-");
			int year = Integer.parseInt(dateAux[0])-1900;
			int month = Integer.parseInt(dateAux[1])-1;
			int day = Integer.parseInt(dateAux[2]);
			Date fechaNacimiento = new Date(year,month,day);
			String [] escuelaAux = request.getParameter("escuela").split("-");
			int nroEscuela = Integer.parseInt(escuelaAux[1]);
			Sistema.getInstancia().altaEmpleado(nombre, apellido, dni, cuil, fechaNacimiento, antiguedad, nroEscuela);
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
