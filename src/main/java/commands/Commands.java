package commands;

import dataBaseConnect.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Commands {
    private Transaction transaction = null;
    private Order order = new Order();

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

    public List selectOrder() {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Orders WHERE number = " + order.getNumber()).list();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }

    public void add() {
        int number = 1;
        String dateFrom = getDateTime();
        String dateTill = getDateTime();
        String name = "nameeeee";
        int cost = 10101;
        String clean = "yes";
        String breakfast = "no";
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            dataBaseConnect.Orders orders = new dataBaseConnect.Orders(number, dateFrom, dateTill, name, cost, clean, breakfast,getDateTime());
            session.save(orders);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
    }

    private String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
