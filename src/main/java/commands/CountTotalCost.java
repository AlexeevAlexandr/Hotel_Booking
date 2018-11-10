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

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse("2015-12-23");
        Date date2 = simpleDateFormat.parse("2015-12-25");
        long diff = (date2.getTime() - date1.getTime());
        System.out.println(date1.getDate() +" "+ date2.getDate());
        System.out.println(diff);
    }
}
