package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import hbt.HibernateUtil;
import negocio.Clase;


public class HibernateClaseDAO {
	private static HibernateClaseDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateClaseDAO getInstancia() {
		if(instancia == null){
			instancia = new HibernateClaseDAO();
			sf = HibernateUtil.getSessionFactory();
		}
		return instancia;
	}

	public List<Clase> recuperarTodasLasClases() {
		Session session = sf.openSession();
		Query q = session.createQuery("from Clase");
		List<Clase> c = (List<Clase>) q.list();
		session.close();
		return c;
	}

	public Clase buscarClase(int numero) {
		Session session = sf.openSession();
		Query q = session.createQuery("from Clase c where c.nroClase=:numero");
		q.setParameter("numero", numero);
		Clase c = (Clase) q.uniqueResult();
		session.close();
		return c;
	}
	
	
}
