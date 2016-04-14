package servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Sistema;

/**
 * Servlet implementation class LiquidarSueldosServlet
 */
@WebServlet("/LiquidarSueldosServlet")
public class LiquidarSueldosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LiquidarSueldosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private static final int BYTES_DOWNLOAD = 1024;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int mes = Integer.parseInt(request.getParameter("mes"));
		int nroEscuela = Integer.parseInt(request.getParameter("escuela"));
		
		String liquidacion = Sistema.getInstancia().liquidarMes(mes, nroEscuela);
		InputStream is = new ByteArrayInputStream(liquidacion.getBytes("UTF8"));
		
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment;filename=liquidacion.txt");
		ServletContext ctx = getServletContext();
//		InputStream is = ctx.getResourceAsStream("C:/Users/juancruz/Documents/archivo.txt");

		int read = 0;
		byte[] bytes = new byte[BYTES_DOWNLOAD];
		OutputStream os = response.getOutputStream();

		while ((read = is.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();
		response.sendRedirect("home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
