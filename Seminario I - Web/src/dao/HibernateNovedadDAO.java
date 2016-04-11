package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hbt.HibernateUtil;
import negocio.Novedad;
import negocio.Usuario;

public class HibernateNovedadDAO {
	private static HibernateNovedadDAO instancia = null;
	private static SessionFactory sf = null;

	public static HibernateNovedadDAO getInstancia() {
		if (instancia == null) {
			instancia = new HibernateNovedadDAO();
			sf = HibernateUtil.getSessionFactory();
		}
		return instancia;
	}

	public List<Novedad> getNovedadesEmpleado(String dni) {
		Session session = sf.openSession();
		Query q = session.createQuery("select e.novedades from Empleado e where e.dni=:dni");
		q.setParameter("dni", dni);
		List<Novedad> n = (List<Novedad>) q.list();
		session.close();
		return n;
	}

	public void crearNovedad(Novedad novedad) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.persist(novedad);
		session.flush();
		session.getTransaction().commit();
		session.close();
	}
}
