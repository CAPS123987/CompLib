package me.caps123987.database;

import me.caps123987.entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 *         <code>
 *         User u = new User();
 *         u.setName("admin");
 *         u.setPassword("admin");
 *         u.setRole("admin");
 *
 *         session.persist(u);
 *
 *         Query<User> query = session.createNamedQuery("User.findByUserName");
 *         query.setParameter("name", "tester");
 *
 *         System.out.println(query.getSingleResult());
 *
 *         session.remove(u);
 *         </code>
 */

public class DBManager {
    Configuration cfg;
    SessionFactory factory = null;
    Session session = null;

    public DBManager() {
        cfg = new Configuration();
        cfg.configure();


        for(Class<?> clazz:getAvaibleClasses()) {
            cfg.addAnnotatedClass(clazz);
        }

        setupConnection();
    }

    public void setupConnection() {

        try {
            factory = cfg.buildSessionFactory();
        } catch (HibernateException e) {
            return;
        }

        session = factory.openSession();
    }

    public Session getSession() {
        if(factory==null) {
            throw new IllegalStateException("SessionFactory is not initialized. Call setupConnection() first.");
        }
        if(session==null || !session.isOpen()) {
            session = factory.openSession();
        }
        return session;
    }

    public boolean isSetup() {
        return factory != null;
    }

    public List<Class<?>> getAvaibleClasses() {
        return List.of(
                User.class
        );
    }
}
