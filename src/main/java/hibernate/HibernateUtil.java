package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;


public class HibernateUtil  {

    private static final SessionFactory sessionFactory = bulildSessionFactory();

    private static SessionFactory bulildSessionFactory() {
        try{
            //Configuration cfg = new Configuration();
            //
            SessionFactory sessionFactory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
                    //cfg.configure().buildSessionFactory();
            return sessionFactory;

        }catch(Exception e){
            System.out.println("Session Factory oluşturulamadı." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
