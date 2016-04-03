package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import hbt.HibernateUtil;
import negocio.Usuario;

public class HibernateUsuarioDAO {
	private static HibernateUsuarioDAO instancia = null;
	private static SessionFactory sf = null;

	public static HibernateUsuarioDAO getInstancia(){
		if(instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new HibernateUsuarioDAO();
		} 
		return instancia;
	}

	public Usuario buscarUsuario(String nombreUsuario) {
		Session session = sf.openSession();
		Query q = session.createQuery("FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario");
		q.setParameter("nombreUsuario", nombreUsuario);
		Usuario u = (Usuario) q.uniqueResult();
		session.close();
		return u;
	}
}
