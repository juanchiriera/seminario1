package hbt;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import negocio.*;


 
public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(Cargo.class);
             config.addAnnotatedClass(Clase.class);
             config.addAnnotatedClass(ConCargo.class);
             config.addAnnotatedClass(Empleado.class);
             config.addAnnotatedClass(Escuela.class);
             config.addAnnotatedClass(Licencia.class);
             config.addAnnotatedClass(Liquidacion.class);
             config.addAnnotatedClass(Novedad.class);
             config.addAnnotatedClass(SinCargo.class);
             config.addAnnotatedClass(LicenciaEmpleado.class);
             config.addAnnotatedClass(Usuario.class);
             
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
