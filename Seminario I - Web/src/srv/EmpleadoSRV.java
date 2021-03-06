package srv;

import java.util.List;
import java.util.Vector;

import dao.HibernateEmpleadoDAO;
import negocio.Cargo;
import negocio.ConCargo;
import negocio.Empleado;
import negocio.SinCargo;

public class EmpleadoSRV {
	private static HibernateEmpleadoDAO dao;
	static{
		dao = HibernateEmpleadoDAO.getInstancia();
	}
	public static List<Empleado> buscarEmpleados(String apellido, String nombre, String cuil, String dni,
			String materia, String curso, String division, boolean estado) {
		return HibernateEmpleadoDAO.getInstancia().buscarProfesores(apellido, nombre, cuil, dni, materia, curso, division, estado);
	}
	public static Empleado buscarEmpleado(String dni) {
		// TODO Implementar busqueda
		return HibernateEmpleadoDAO.getInstancia().buscarEmpleado(dni);
	}
	public static Vector<Cargo> buscarEmpConCargoActivos(String dni) {
		// TODO Implementar busqueda
		return null;
	}
	public static void grabarEmpleado(Empleado empleado) {
		HibernateEmpleadoDAO.getInstancia().guardarEmpleado(empleado);
		
	}
	public static SinCargo buscarEmpleadoSinCargo(String dni) {
		return HibernateEmpleadoDAO.getInstancia().buscarEmpleadoSinCargo(dni);
	}
	
	public static ConCargo buscarEmpleadoConCargo (String dni){
		return HibernateEmpleadoDAO.getInstancia().buscarEmpleadoConCargo(dni);
	}
	public static List<Empleado> buscarEmpleados(String apellido, String nombre, String cuil, String dni, int nroEscuela,
			boolean estado) {
		return HibernateEmpleadoDAO.getInstancia().buscarEmpleados(apellido, nombre, cuil, dni, nroEscuela, estado);
		
	}
	public static List<Empleado> recuperarEmpleados() {
		return HibernateEmpleadoDAO.getInstancia().buscarTodosLosEmpleados();
	}
	public static void actualizarEmpleado(Empleado empleado) {
		HibernateEmpleadoDAO.getInstancia().actualizarEmpleado(empleado);
		
	}
	public static List<ConCargo> recuperarEmpleadosConCargo() {
		return HibernateEmpleadoDAO.getInstancia().buscarTodosLosEmpleadosConCargo();
	}
	public static List<SinCargo> recuperarProfesores() {
		return HibernateEmpleadoDAO.getInstancia().buscarTodosLosProfesores();
	}
	public static void eliminarEmpleado(ConCargo empleado) {
		HibernateEmpleadoDAO.getInstancia().eliminarEmpleado(empleado);
	}
	public static Empleado buscarEmpleadoActivo(String dni) {
		return HibernateEmpleadoDAO.getInstancia().buscarEmpleadoActivo(dni);
	}
	public static List<ConCargo> recuperarEmpleadosPorEscuela(int nroEscuela) {
		return HibernateEmpleadoDAO.getInstancia().recuperarEmpleadosPorEscuela(nroEscuela);
	}
	public static List<SinCargo> recuperarDocentesPorEscuela(int nroEscuela) {
		return HibernateEmpleadoDAO.getInstancia().recuperarDocentesPorEscuela(nroEscuela);
	}
	
	
}
