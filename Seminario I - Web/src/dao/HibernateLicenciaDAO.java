package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import hbt.HibernateUtil;
import negocio.Licencia;

public class HibernateLicenciaDAO {
	private static HibernateLicenciaDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateLicenciaDAO getInstancia() {
		if(instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new HibernateLicenciaDAO();
		}
		return instancia;
	}

	public List<Licencia> recuperarLicencias() {
		Session session = sf.openSession();
		Query q = session.createQuery("from Licencia");
		List<Licencia> l = (List<Licencia>) q.list();
		session.close();
		return l;
	}

	public Licencia buscarLicencia(String codigo) {
		Session session = sf.openSession();
		Query q = session.createQuery("from Licencia l where l.codigo=:codigo");
		q.setParameter("codigo", codigo);
		Licencia l = (Licencia) q.uniqueResult();
		session.close();
		return l;
	}
	
	
}
