package dao;

import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import hbt.HibernateUtil;
import negocio.ConCargo;
import negocio.Empleado;
import negocio.SinCargo;

public class HibernateEmpleadoDAO {
	private static HibernateEmpleadoDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateEmpleadoDAO getInstancia() {
		if(instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new HibernateEmpleadoDAO();
		}
		return instancia;
	}

	public Empleado buscarEmpleado(String dni) {
		Session session = sf.openSession();
		Query q = session.createQuery("from Empleado e where e.dni = :dni");
		q.setParameter("dni", dni);
		try{
		Empleado e = (Empleado) (q.list()).get(0);
		session.flush();
		session.close();
		return e;
		}catch(Exception e){
			session.flush();
			session.close();
			return null;
		}

	}

	public void guardarEmpleado(Empleado empleado) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(empleado);
		session.getTransaction().commit();
		session.flush();
		session.close();
	}

	public List<Empleado> buscarEmpleados(String apellido, String nombre, String cuil, String dni, int nroEscuela,
			boolean estado) {
//		if(apellido != null & apellido = ' ')
		
		Session session = sf.openSession();
		Query q = session.createQuery("from ConCargo e where ((e.dni=:dni or :dni='')"
				+ " and (e.apellido=:apellido or :apellido='')"
				+ " and (e.nombre=:nombre or :nombre='')"
				+ " and (e.cuil=:cuil or :cuil=''))");
//				+ " and (e.estado=:estado or :estado='')" 
//				+ " and (e.escuela.nro=:nroEscuela or :escuela='' )");
		q.setParameter("dni", dni);
		q.setParameter("apellido", apellido);
		q.setParameter("nombre", nombre);
		q.setParameter("cuil", cuil);
//		q.setParameter("estado", estado);
//		q.setParameter("nroEscuela", nroEscuela);
		
		List<Empleado> e = (List<Empleado>) q.list();
		session.flush();
		session.close();
		return e;
	}

	public List<Empleado> buscarTodosLosEmpleados() {
		Session session = sf.openSession();
		Query q = session.createQuery("from Empleado");
		List<Empleado> e = (List<Empleado>) q.list();
		session.flush();
		session.close();
		return e;
	}

	public List<Empleado> buscarProfesores(String apellido, String nombre, String cuil, String dni, String materia,
			String curso, String division, boolean estado) {
		Session session = sf.openSession();
		Query q = session.createQuery("from SinCargo e where ((e.dni = :dni or :dni='')"
				+ "and (e.apellido = :apellido or :apellido='')"
				+ "and (e.nombre=:nombre or :nombre='')"
				+ "and (e.cuil=:cuil or :cuil='')"
				+ "and (e.estado=:estado or :estado=''))");
		q.setParameter("dni", dni);
		q.setParameter("apellido", apellido);
		q.setParameter("nombre", nombre);
		q.setParameter("cuil", cuil);
		q.setParameter("estado", estado);
//		q.setParameter("materia", materia);
//		q.setParameter("curso", curso);
//		q.setParameter("division", division);
		
		List<Empleado> e = (List<Empleado>) q.list();
		session.flush();
		session.close();
		return e;
	}

	public void actualizarEmpleado(Empleado empleado) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(empleado);
		session.getTransaction().commit();
		session.flush();
		session.close();
	}

	public ConCargo buscarEmpleadoConCargo(String dni) {
		Session session = sf.openSession();
		Query q = session.createQuery("from ConCargo c where c.dni=:dni");
		q.setParameter("dni", dni);
		ConCargo empleado = (ConCargo) q.uniqueResult();
		session.flush();
		session.close();
		return empleado;
	}
	
	public SinCargo buscarEmpleadoSinCargo(String dni) {
		Session session = sf.openSession();
		Query q = session.createQuery("from SinCargo c where c.dni=:dni");
		q.setParameter("dni", dni);
		SinCargo empleado = (SinCargo) q.uniqueResult();
		session.flush();
		session.close();
		return empleado;
	}

	public List<ConCargo> buscarTodosLosEmpleadosConCargo() {
		Session session = sf.openSession();
		Query q = session.createQuery("from ConCargo");
		List<ConCargo> e = (List<ConCargo>) q.list();
		session.flush();
		session.close();
		return e;
	}

	public List<SinCargo> buscarTodosLosProfesores() {
		Session session = sf.openSession();
		Query q = session.createQuery("from SinCargo");
		List<SinCargo> e = (List<SinCargo>) q.list();
		session.flush();
		session.close();
		return e;
	}

	public void eliminarEmpleado(ConCargo empleado) {
		Session session = sf.openSession();
//		Query q = session.createQuery("delete Empleado where dni = :dni");
//		q.setParameter("dni", empleado.getDni());
		session.delete(empleado);
//		q.executeUpdate();
//		session.beginTransaction();
//		session.close();
	}

	public Empleado buscarEmpleadoActivo(String dni) {
		Session session = sf.openSession();
		Query q = session.createQuery("from Empleado e where e.dni = :dni and e.estado=true");
		q.setParameter("dni", dni);
		Empleado e = (Empleado) (q.list()).get(0);
		session.close();
		return e;
	}

	public List<ConCargo> recuperarEmpleadosPorEscuela(int nroEscuela) {
		Session session = sf.openSession();
		Query q = session.createQuery("from ConCargo c where c.escuela.nro = :nroEscuela and c.estado=true");
		q.setParameter("nroEscuela", nroEscuela);
		List<ConCargo> e = (List<ConCargo>) q.list();
		session.close();
		return e;
	}

	public List<SinCargo> recuperarDocentesPorEscuela(int nroEscuela) {
		Session session = sf.openSession();
		Query q = session.createQuery("from SinCargo c where c.escuela.nro = :nroEscuela and c.estado=true");
		q.setParameter("nroEscuela", nroEscuela);
		List<SinCargo> e = (List<SinCargo>) q.list();
		session.close();
		return e;
	}
}
