package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.Curso;
import negocio.Division;
import negocio.Materia;

/**
 * Servlet implementation class BuscarProfesorServletNew
 */
@WebServlet("/BuscarProfesorServletNew")
public class BuscarProfesorServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarProfesorServletNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Materia> materias = new Vector<Materia>();
		for (Materia materia : Materia.values()) {
			materias.add(materia);
		}
		List<Curso> cursos = new Vector<Curso>();
		for (Curso curso : Curso.values()) {
			cursos.add(curso);
		}
		List<Division> divisiones = new Vector<Division>();
		for (Division division : Division.values()) {
			divisiones.add(division);
		}
		request.setAttribute("materias", materias);
		request.setAttribute("cursos", cursos);
		request.setAttribute("divisiones", divisiones);
		
		getServletContext().getRequestDispatcher("/BuscarProfesor.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
