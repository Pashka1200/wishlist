package dao;

import dao.impl.AbstractDao;
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
public class ReserveDao extends ClassDao {
    static Factory factory = Factory.getInstance();
//    static Session session = null;
    public ReserveDao() {
        super(Reserve.class);
    }

    static InterfaseDao interfaseDao = factory.getInterfaseDao(Reserve.class);

    static UserDao userDao = new UserDao();

    //get wish list by facebook id
    public List<Reserve> findReservesByFacebookId(long facebook_id, int buy_status) throws SQLException{
//        Session session = null;
        List<Reserve> reserves = new ArrayList<Reserve>();
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesUserByClientFacebookId");
        query.setLong("userId", userDao
                .findOneByFacebookId(facebook_id).getId());
        query.setLong("status", buy_status);
        reserves = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
        return reserves;
    }

    //get not reserved wish list by facebook id
    public List<Reserve> findNotReservedByFacebookId(long facebook_id, int buy_status) throws SQLException{
//        Session session = null;
        List<Reserve> reserves = new ArrayList<Reserve>();
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findNotReservedFacebookId");
        query.setLong("userId", userDao
                .findOneByFacebookId(facebook_id).getId());
        query.setLong("status", buy_status);
        reserves = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
        return reserves;
    }

    //get freinds list by facebook id
    public List<Reserve> findClientByBuyer(long facebook_id, int buy_status) throws SQLException{
//        Session session = null;
        List<Reserve> reserves = new ArrayList<Reserve>();
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesUserByBuyerFacebookId");
        query.setLong("userId", userDao
                .findOneByFacebookId(facebook_id).getId());
        query.setLong("status", buy_status);
        reserves = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
        return reserves;
    }

    //use this method when you want to DELETE interconnected user-item connection
    //for deleting item in item table
    //this method call delMyItem(item_id) from ItemDao
    public String delBuyItem(long item_id) throws SQLException {
//        Session session = null;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setLong("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        interfaseDao.delete(reserve);
        new ItemDao().delMyItem(item_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
        return "true";
    }

    //create connection from user to item
    //called from addMyItem
    public void addConnection (long facebook_id, Item item) throws SQLException {
        User user = new UserDao().findOneByFacebookId(facebook_id);
        Reserve reserve = new Reserve();
        reserve.setItem(item);
        reserve.setClient(user);
        interfaseDao.add(reserve);
    }

    //add buyer unique id to reserve table
    public String addBuyer (long item_id, long buyer_id) throws SQLException{
//        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("findReservesByItemId");
            query.setLong("itemId", item_id);
            Reserve reserve = (Reserve) query.uniqueResult();
            reserve.setBuyer_id(userDao
                    .findOneByFacebookId(buyer_id).getId());
            interfaseDao.update(reserve);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
        return "true";
    }

    //delete buyer id and set NULL into buyer_id field
    public String delBuyer (long item_id) throws SQLException {
//        Session session = null;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setLong("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        reserve.setBuyer_id(null);
        interfaseDao.update(reserve);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
        return "true";
    }

    //check buyer_id field and return "empty" if it contain null
    public String checkBuyer(long item_id) throws SQLException {
//        Session session = null;
        String status = "empty";
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setLong("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        if(reserve.getBuyer_id()!=null) {status =  "true";}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
        return status;
    }

    //modifies the is_buy field
    public String isBought(long item_id, int buy_status) throws SQLException{
//        Session session = null;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setLong("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        reserve.setIs_buy(buy_status);
        interfaseDao.update(reserve);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
        return "true";
    }

    //check is_buy field
    public String checkIsBuy(long item_id) throws SQLException{
//        Session session = null;
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("findReservesByItemId");
        query.setLong("itemId", item_id);
        Reserve reserve = (Reserve) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if((session != null) && (session.isOpen())) session.close();
        }
        return "true";
    }

}
