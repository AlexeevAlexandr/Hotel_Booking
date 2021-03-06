package commands;

import dataBaseConnect.ListRooms;
import dataBaseConnect.Orders;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Commands {
    private Transaction transaction = null;

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

    public List selectOrder(String name) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Orders WHERE name = '" + name + "'").list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public void add(int number, String dateFrom, String dateTill, String name, int cost, String clean, String breakfast, String date) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            session.save(new Orders(number, dateFrom, dateTill, name, cost, clean, breakfast, date));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
    }

    public List selectAllOrders() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Orders").list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public List selectByName(String name) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Orders WHERE name = '" + name + "'").list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public List checkRoomNumber() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT id FROM ListRooms").list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public void clearTable(String table) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE "+ table).executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE hibernate_sequence restart").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
    }

    public void addToListRooms(String category, int price) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            session.save(new ListRooms(category,price));
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
    }

    public int selectPrice(int number) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return Integer.parseInt(session.createSQLQuery("SELECT price FROM listRooms WHERE number = " + number).list().toString().replaceAll("[\\[\\]]", ""));
        } catch (HibernateException e) {
            e.getMessage();
        }
        return 0;
    }
}
