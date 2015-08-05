package general;

import dao.InterfaseDao;
import dao.ItemDao;
import dao.ReserveDao;
import dao.UserDao;
import dao.impl.ClassDao;
import table.Item;
import table.Reserve;
import table.User;

import javax.management.Query;
import javax.naming.InsufficientResourcesException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by pavlo on 14.07.15.
 */
public class Project {
    static Factory factory = Factory.getInstance();

    public static void main(String[] args) throws SQLException {


//       UserDao userDao = new UserDao(User.class);
//
//        System.out.println(userManipulation.checkUser(1212, "1993-07-12"));
//
//        InterfaseDao interfaseDao = factory.getInerfaseDao(User.class);
//
//        interfaseDao.get(10);


       // InterfaseDao interfaseDao = factory.getInerfaseDao(Reserve.class);
//
//        interfaseDao = factory.getInerfaseDao(Item.class);
//        List<Item> items = interfaseDao.getAll();
//        for (Item item : items) {
//            System.out.println(item.getTitle());
//        }

      ItemDao itemDao = new ItemDao();
////       ReserveDao reserveDao = new ReserveDao(Reserve.class);
//        long id = 858306037591360L;
//        List<Item> items = itemDao.getItems(id);
//        for (Item item : items) {
//            System.out.println(item.getId() + " " + item.getTitle() + " " + item.getDescription());
//        }

//        System.out.println(itemDao.addMyItem(1001, "iPhone 5S", "", "iPhone 5S"));
        System.out.println(itemDao.updateMyItems(65,"Sony","","Xperia Z","pic"));
//        itemDao.delMyItem(15);
//        reserveDao.delBuyItem(1001,20);

       // InterfaseDao interfaseDaoUser = factory.getInerfaseDao(User.class);

//        System.out.println(userDao.findOneByFacebookId(1003).getId());
//        System.out.println(userDao.checkUser(3333, "12-12-1212"));
//        System.out.println(userDao.updateUser(1234, 4321));

//        List<Reserve> reserves = reserveDao.findReservesByFacebookId(1001);
//        for (Reserve reserve : reserves) {
//            System.out.println(reserve.getItem().getTitle());
//        }
//
//        System.out.println(reserveDao.delBuyItem(1001));
//        System.out.println(reserveDao.addBuyer(23,1003));
//        System.out.println(reserveDao.delBuyer(23));
//        System.out.println(reserveDao.checkBuyer(23));
//        System.out.println(reserveDao.isBuyed(23, 0));
//        System.out.println(reserveDao.checkIsBuy(23));

    }
}
