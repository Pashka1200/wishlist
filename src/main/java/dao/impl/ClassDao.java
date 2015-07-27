package dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import table.User;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;

/**
 * Created by pavlo on 18.07.15.
 */
public class ClassDao<T> extends AbstractDao<T> {
    public ClassDao(Class<T> class1) {
        super(class1);
    }



}
