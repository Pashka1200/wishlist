package dao;

import dao.InterfaseDao;
import dao.impl.ClassDao;
import general.Factory;
import org.hibernate.Query;
import org.hibernate.Session;
import table.User;
import util.HibernateUtil;

import java.sql.SQLException;

/**
 * Created by pavlo on 20.07.15.
 */


public class UserDao<T> extends ClassDao<T> {
    static Factory factory = Factory.getInstance();

    public UserDao(Class<T> class1) {
        super(class1);
    }

        InterfaseDao interfaseDao = factory.getInerfaseDao(User.class);


    //find user by facebook id
    public User findOneByFacebookId(int facebook_id) throws SQLException{
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findOneUserByFacebookId");
        query.setInteger("facebook_id", facebook_id);
        User user = (User) query.uniqueResult();
        return user;
    }

    //check users in table user for contains user with this facebook id
    public String checkUser(int facebook_id, String date_of_birth) throws SQLException{

        User newUser = new User();
        newUser.setFacebookId(facebook_id);
        newUser.setDate_of_birth(date_of_birth);
        if (findOneByFacebookId(facebook_id) == null) {
            interfaseDao.add(newUser);
            return "new";
        } else {
            return "registered";
        }
    }

    //delete user by facebook id
    public void deleteUser(int facebook_id) throws SQLException{
        interfaseDao.delete(findOneByFacebookId(facebook_id));
      }










    //find id by facebook id
    public int findByFacebookId(int facebook_id) throws SQLException{
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findUserByFacebookId");
        query.setInteger("facebook_id", facebook_id);
        int id = (Integer) query.uniqueResult();
        return id;
    }

}
