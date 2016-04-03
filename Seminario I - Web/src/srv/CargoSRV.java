package srv;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import dao.HibernateCargoDAO;
import negocio.Cargo;

public class CargoSRV {
	private static HibernateCargoDAO dao;
	static{
		dao = HibernateCargoDAO.getInstancia();
	}
	public static Vector<Cargo> recuperarCargos(String nombreCargo, float sueldoBasico, int horasTrabajo) {
		// TODO Implementar busqueda
		return null;
	}
	public static Cargo buscarCargo(String nombreCargo) {
		// TODO Implementar busqueda
		return null;
	}
	public static void guardarCargo(Cargo cargo) {
		// TODO Implementar guardar
		
	}
	public static List<Cargo> recuperarTodosLosCargos() {
		return HibernateCargoDAO.getInstancia().buscarTodosLosCargos();
	}
	public static Collection<Cargo> recuperarCargosEmpleado(String dni) {
		return HibernateCargoDAO.getInstancia().getCargasEmpleado(dni);
	}
}
