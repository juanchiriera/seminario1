package srv;

import java.util.Vector;

import dao.HibernateLicenciaDAO;
import negocio.Licencia;

public class LicenciaSRV {
	private static HibernateLicenciaDAO dao;
	static{
		dao = HibernateLicenciaDAO.getInstancia();
	}
	
	public static Vector<Licencia> recuperarLicencias(String codigo, String tipo, String motivo) {
		// TODO Implementar Busqueda
		return null;
	}

	public static Licencia buscarLicencia(String codigo) {
		// TODO Implementar Busqueda
		return null;
	}

	public static void guardarLicencia(Licencia lic) {
		// TODO Implementar grabar
		
	}

	public static void eliminarLicencia(Licencia lic) {
		// TODO Implementar eliminar
		
	}
}
