package commands;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Commands {
    public List select() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ListRooms").list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }
}
