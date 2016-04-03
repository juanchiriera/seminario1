package dao;

import org.hibernate.SessionFactory;

import hbt.HibernateUtil;

public class HibernateNovedadDAO {
	private static HibernateNovedadDAO instancia = null;
	private static SessionFactory sf = null;
	
	public static HibernateNovedadDAO getInstancia(){
		if (instancia == null){
			instancia = new HibernateNovedadDAO();
			sf = HibernateUtil.getSessionFactory();
		}
		return instancia;
	}
}
