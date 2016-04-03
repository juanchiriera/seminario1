package dao;

import org.hibernate.SessionFactory;

import hbt.HibernateUtil;

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
	
	
}
