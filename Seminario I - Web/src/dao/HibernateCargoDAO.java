package dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import hbt.HibernateUtil;
import negocio.Cargo;
import negocio.Empleado;

public class HibernateCargoDAO {
	private static HibernateCargoDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateCargoDAO getInstancia() {
		if(instancia == null){
			instancia = new HibernateCargoDAO();
			sf = HibernateUtil.getSessionFactory();
		}
		return instancia;
	}

	public List<Cargo> getCargasEmpleado(String dni) {
		Session session = sf.openSession();
		Query q = session.createQuery("select Cargo from Empleado e join e.cargos where e.dni = :dni");
		q.setParameter("dni", dni);
		List<Cargo> cargos = (List<Cargo>) q.list();
		session.close();
		return cargos;
	}

	public List<Cargo> buscarTodosLosCargos() {
		Session session = sf.openSession();
		Query q = session.createQuery("from Cargo");
		List<Cargo> c = (List<Cargo>) q.list();
		session.close();
		return c;
	}
	
	
}
