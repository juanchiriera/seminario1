package srv;

import java.util.Date;
import java.util.Vector;

import dao.HibernateNovedadDAO;
import negocio.Novedad;

public class NovedadSRV {
	private static HibernateNovedadDAO dao;
	static{
		dao = HibernateNovedadDAO.getInstancia();
	}
	public static Vector<Novedad> recuperarNovedades(String tipo, String motivo, Date fechaInicio, Date fechaHasta) {
		// TODO Auto-generated method stub
		return null;
	}
}
