package dao;

import dao.InterfaseDao;
import dao.impl.ClassDao;
import general.Factory;
import org.hibernate.Query;
import org.hibernate.Session;
import table.Item;
import table.Reserve;
import table.User;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavlo on 21.07.15.
 */
public class ReserveDao<T> extends ClassDao<T> {
    static Factory factory = Factory.getInstance();

    public ReserveDao(Class<T> class1) {
        super(class1);
    }

    InterfaseDao interfaseDao = factory.getInerfaseDao(Reserve.class);

    public List<Reserve> findReservesByFacebookId(int facebook_id) throws SQLException{
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesUserByFacebookId");
        query.setInteger("userId", new UserDao(User.class)
                .findOneByFacebookId(facebook_id).getId());
        List<Reserve> reserves = query.list();
        return reserves;
    }

    //use this method when you want to DELETE interconnected user-item connection
    //for deleting item in item table
    //this method call delMyItem(item_id) from ItemDao
    public String delBuyItem(int facebook_id, int item_id) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setInteger("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        System.out.println(reserve.getId());
        interfaseDao.delete(reserve);
        new ItemDao(Item.class).delMyItem(item_id);
        return "true";
    }

    //create connection from user to item
    //called from addMyItem
    public void addConnection (int facebook_id, int item_id) throws SQLException {
        User user = new UserDao(User.class).findOneByFacebookId(facebook_id);
        System.out.println(user.getId());
        Reserve reserve = new Reserve();
        reserve.setItem((Item) factory.getInerfaseDao(Item.class).get(item_id));
        reserve.setClient(user);
        interfaseDao.add(reserve);
    }

    //add buyer unique id to reserve table
    public String addBuyer (int item_id, int buyer_id) throws SQLException{
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setInteger("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        System.out.println(reserve.getId());
        reserve.setBuyer_id(new UserDao(User.class)
                .findOneByFacebookId(buyer_id).getId());
        interfaseDao.update(reserve);
        return "true";
    }

    //delete buyer id and set NULL into buyer_id field
    public String dellBuyer (int item_id) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setInteger("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        reserve.setBuyer_id(null);
        interfaseDao.update(reserve);
        return "true";
    }

    //check buyer_id field and return "empty" if it contain null
    public String checkBuyer(int item_id) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setInteger("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        System.out.println(reserve.getId());
        if(reserve.getBuyer_id()==null) {
            return "empty";
        } else {
        return "true";
        }


    }

    //modifies the is_buy field
    public String isBuyed(int item_id, int buy_status) throws SQLException{
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setInteger("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        reserve.setIs_buy(buy_status);
        interfaseDao.update(reserve);
        return "true";
    }

    //check is_buy field
    public String checkIsBuy(int item_id) throws SQLException{
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setInteger("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        System.out.println(reserve.getIs_buy());
        return "true";
    }

}
