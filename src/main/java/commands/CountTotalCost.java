package commands;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountTotalCost{

    public int selectPrice(int number) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            return Integer.parseInt(session.createSQLQuery("SELECT price FROM list_rooms WHERE number = " + number).list().toString().replaceAll("[\\[\\]]", ""));
        } catch (HibernateException e) {
            e.getMessage();
        }
        return 0;
    }
}
