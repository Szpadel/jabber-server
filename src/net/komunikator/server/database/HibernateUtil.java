package net.komunikator.server.database;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * @author Reut Michał<michalreut0@gmail.com>
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            SessionFactory sessionFactory = new Configuration()
                    .configure("/hibernate.cfg.xml")
                    .addResource("/net/komunikator/server/mappingFile.hbm.xml")
                    .buildSessionFactory();

            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("SessionFactory  = failed");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}

