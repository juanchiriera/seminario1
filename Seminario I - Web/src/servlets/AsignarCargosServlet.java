package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;

/**
 * Servlet implementation class AsignarCargosServlet
 */
@WebServlet("/AsignarCargosServlet")
public class AsignarCargosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsignarCargosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			request.setAttribute("empleados", Sistema.getInstancia().recuperarEmpleadosConCargo());
			request.setAttribute("cargos", Sistema.getInstancia().recuperarCargos());
			
			request.getRequestDispatcher("/AsignarCargos.jsp").forward(request, response);
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
