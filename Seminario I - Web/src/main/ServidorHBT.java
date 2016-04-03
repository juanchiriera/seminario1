package main;

import hbt.HibernateUtil;
import negocio.Usuario;
import srv.UsuarioSRV;

public class ServidorHBT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ServidorHBT();
	
	}

	public ServidorHBT(){
		HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
