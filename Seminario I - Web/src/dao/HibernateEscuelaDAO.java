package dao;

import java.util.List;
import java.util.Vector;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import hbt.HibernateUtil;
import negocio.Empleado;
import negocio.Escuela;

public class HibernateEscuelaDAO {
	private static HibernateEscuelaDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateEscuelaDAO getInstancia() {
		if(instancia == null){
			instancia = new HibernateEscuelaDAO();
			sf = HibernateUtil.getSessionFactory();
		}
		return instancia;
	}

	public List<Escuela> recuperarEscuelas() {
		Session session = sf.openSession();
		Query q = session.createQuery("from Escuela");
		List<Escuela> e = (List<Escuela>) q.list();
		session.close();
		return e;
	}

	public Escuela getEscuela(int numero) {
		Session session = sf.openSession();
		Query q = session.createQuery("from Escuela e where e.nro=:numero");
		q.setParameter("numero", numero);
		Escuela e = (Escuela) q.uniqueResult();
		session.close();
		return e;
	}
}
