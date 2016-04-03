package srv;

import java.util.List;
import java.util.Vector;

import dao.HibernateClaseDAO;
import negocio.Clase;

public class ClaseSRV {
	private static HibernateClaseDAO dao;
	static{
		dao = HibernateClaseDAO.getInstancia();
	}
	public static Vector<Clase> recuperarClases(int numero, String nombre, String curso, String division) {
		// TODO Implementar Busqueda
		return null;
	}
	public static Clase buscarClase(int numero) {
		return HibernateClaseDAO.getInstancia().buscarClase(numero);
	}
	public static void guardarClase(Clase clase) {
		// TODO Auto-generated method stub
		
	}
	public static List<Clase> cargarClases() {
		return HibernateClaseDAO.getInstancia().recuperarTodasLasClases();
	}
}
