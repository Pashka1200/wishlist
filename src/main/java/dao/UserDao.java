package dao;

import dao.impl.ClassDao;
import general.Factory;
import org.hibernate.Query;
import org.hibernate.Session;
import table.User;
import util.HibernateUtil;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.sql.SQLException;

/**
 * Created by pavlo on 20.07.15.
 */

@Stateless
@Named
public class UserDao extends ClassDao {
    static Factory factory = Factory.getInstance();

    public UserDao() {
        super(User.class);
    }

        static InterfaseDao interfaseDao = factory.getInerfaseDao(User.class);


    //find user by facebook id
    public User findOneByFacebookId(long facebook_id) throws SQLException{
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findOneUserByFacebookId");
        query.setLong("facebook_id", facebook_id);
        User user = (User) query.uniqueResult();

        return user;
    }

    //check users in table user for contains user with this facebook id
    public String checkUser(long facebook_id, String date_of_birth) throws SQLException{

        User newUser = new User();
        newUser.setFacebookId(facebook_id);
        //newUser.setDate_of_birth(date_of_birth);
        if (findOneByFacebookId(facebook_id) == null) {
            interfaseDao.add(newUser);
            return "new";
        } else {
            return "registered";
        }
    }

    //delete user by facebook id
    public void deleteUser(long facebook_id) throws SQLException{
        interfaseDao.delete(findOneByFacebookId(facebook_id));
      }

}
