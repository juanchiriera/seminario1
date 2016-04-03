package srv;

import java.util.List;

import dao.HibernateUsuarioDAO;
import negocio.Usuario;


public class UsuarioSRV {
	private static HibernateUsuarioDAO dao;

	static {
		dao = HibernateUsuarioDAO.getInstancia();
	}

	public static Usuario buscarUsuario(String nombreUsuario) {
		// TODO Implementacion de la busqueda en HibernateUsuarioDAO
		return dao.buscarUsuario(nombreUsuario);
	}

	
}
