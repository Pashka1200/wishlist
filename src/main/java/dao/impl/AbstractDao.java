package dao.impl;

import dao.InterfaseDao;
import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pavlo on 18.07.15.
 */
public abstract class AbstractDao<T> implements InterfaseDao<T> {

    static protected Session session = null;

    private Class class1;

    AbstractDao(Class<T> class1) {
        this.class1 = class1;
    }

    public void add(T t) throws SQLException {
//        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
    }

    public void delete(T t) throws SQLException {
//        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }

    }

    public T get(long id) throws SQLException {
        T result = null;
//        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (T) session.get(class1, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
        return result;
    }

    public List<T> getAll() throws SQLException {
        List<T> units = null;

//        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            units = session.createCriteria(class1).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }

        return units;
    }

    public void update(T t) throws SQLException {
//        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
    }

}
