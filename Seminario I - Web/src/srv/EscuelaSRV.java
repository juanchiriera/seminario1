package srv;

import java.util.List;
import java.util.Vector;

import dao.HibernateEscuelaDAO;
import negocio.Escuela;

public class EscuelaSRV {
	private static HibernateEscuelaDAO dao;
	static{
		dao = HibernateEscuelaDAO.getInstancia();
	}
	
	public static Escuela buscarEscuela(int numero) {
		return HibernateEscuelaDAO.getInstancia().getEscuela(numero);
	}

	public static Vector<Escuela> recuperarEscuelas(int numero, String nombre) {
		// TODO Implementar busqueda
		return null;
	}

	public static void guardarEscuela(Escuela esc) {
		// TODO Implementar guardar
		
	}

	public static List<Escuela> recuperarEscuelas() {
		return HibernateEscuelaDAO.getInstancia().recuperarEscuelas();
	}
}
