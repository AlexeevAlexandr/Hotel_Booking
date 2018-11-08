package commands;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class Commands {
    public List selectAllCategory() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ListRooms").list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public List selectPremiumCategory() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ListRooms WHERE category = 'premium'").list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public List selectAverageCategory() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ListRooms WHERE category = 'average'").list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public List selectBudgetCategory() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ListRooms WHERE category = 'budget'").list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public List selectOrder(int number) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Orders WHERE number = " + number).list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }
}
