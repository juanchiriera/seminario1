package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controlador.Sistema;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String nombreUsuario = request.getParameter("usuario");
			String contraseņa = request.getParameter("contrasenia");
			String res = Sistema.getInstancia().iniciarSesion(nombreUsuario, contraseņa);
			if(res!=null){
				HttpSession session = request.getSession();
				session.setAttribute("login", "ok");
				session.setAttribute("usuario", Sistema.getInstancia().buscarUsuario(nombreUsuario));
				if(nombreUsuario.equals("admin")){
					session.setAttribute("hide", false);
				}else{
					session.setAttribute("hide", true);
				}
				response.sendRedirect("home.jsp");
			}else{
				
				request.setAttribute("error", "Incorrect password");
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
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
