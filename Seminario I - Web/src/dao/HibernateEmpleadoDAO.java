package dao;

import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import hbt.HibernateUtil;
import negocio.Empleado;

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
		Empleado e = (Empleado) q.uniqueResult();
		session.close();
		return e;
	}

	public void guardarEmpleado(Empleado empleado) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(empleado);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	public List<Empleado> buscarEmpleados(String apellido, String nombre, String cuil, String dni, int nroEscuela,
			boolean estado) {
		Session session = sf.openSession();
		Query q = session.createQuery("from ConCargo e where e.dni = :dni or "
				+ "e.apellido = :apellido or e.nombre=:nombre or e.cuil=:cuil or e.estado=:estado or e.escuela.nro=:nroEscuela");
		q.setParameter("dni", dni);
		q.setParameter("apellido", apellido);
		q.setParameter("nombre", nombre);
		q.setParameter("cuil", cuil);
		q.setParameter("estado", estado);
		q.setParameter("nroEscuela", nroEscuela);
		
		List<Empleado> e = (List<Empleado>) q.list();
		session.close();
		return e;
	}

	public List<Empleado> buscarTodosLosEmpleados() {
		Session session = sf.openSession();
		Query q = session.createQuery("from Empleado");
		List<Empleado> e = (List<Empleado>) q.list();
		session.close();
		return e;
	}

	public List<Empleado> buscarProfesores(String apellido, String nombre, String cuil, String dni, String materia,
			String curso, String division, boolean estado) {
		Session session = sf.openSession();
		Query q = session.createQuery("from SinCargo e where e.dni = :dni or "
				+ "e.apellido = :apellido or e.nombre=:nombre or e.cuil=:cuil or e.estado=:estado "
				+ "or e.clases.nombre=:materia or e.clases.curso=curso or e.clases.division=:division");
		q.setParameter("dni", dni);
		q.setParameter("apellido", apellido);
		q.setParameter("nombre", nombre);
		q.setParameter("cuil", cuil);
		q.setParameter("estado", estado);
		q.setParameter("materia", materia);
		q.setParameter("curso", curso);
		q.setParameter("division", division);
		
		List<Empleado> e = (List<Empleado>) q.list();
		session.close();
		return e;
	}

	public void actualizarEmpleado(Empleado empleado) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(empleado);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
	
	
}
