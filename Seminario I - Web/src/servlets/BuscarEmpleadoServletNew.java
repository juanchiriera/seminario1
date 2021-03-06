package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;
import negocio.Escuela;

/**
 * Servlet implementation class BuscarEmpleadoServletNew
 */
@WebServlet("/BuscarEmpleadoServletNew")
public class BuscarEmpleadoServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarEmpleadoServletNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{	
			List<Escuela> escuelas = Sistema.getInstancia().recuperarEscuelas();
			request.setAttribute("escuelas", escuelas);
			getServletContext().getRequestDispatcher("/BuscarEmpleado.jsp").forward(request, response);
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
