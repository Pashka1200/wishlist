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


//       UserDao userDao = new UserDao();
//        System.out.println(userDao.updateUser(3333, "12-12-1212"));

//        ItemDao itemDao = new ItemDao();
//        long id = 1021432071201151L;
//        List<Item> items = itemDao.findCleintsItemsByBuyer(3333, 0);
//        for (Item item : items) {
//            System.out.println(item.getTitle() + " " + item.getDescription()+ " " + item.getUrl() );
//        }
//

//            List<Item> items = itemDao.getNotReservedItems(3333, 0);
//            for (Item item : items) {
//            System.out.println(item.getTitle() + " " + item.getDescription()+ " " + item.getUrl() );
//        }


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

          ReserveDao reserveDao = new ReserveDao();
//        long id = 858306037591360L;
//        List<Reserve> reserves = reserveDao.findClientByBuyer(id);
//                for(Reserve reserve : reserves) {
//                    System.out.println(reserve.getBuyer_id());
//                }
//        System.out.println("Bought items: ");
//        List<Item> items = itemDao.getItems(id, 1);
//        for (Item item : items) {
//            System.out.println(item.getId() + " " + item.getTitle() + " " + item.getDescription());
//        }
//
//        List<Item> items1 = itemDao.getItems(3333, 0);
//        System.out.println("I want: ");
//        for (Item item : items1) {
//            System.out.println(item.getId() + " " + item.getTitle() + " " + item.getDescription());
//        }

//        System.out.println(itemDao.addMyItem(3333, "Samsung", "", "phone"));
//        System.out.println(itemDao.updateMyItems(27,"ZHOPA","","zhopen","(_!_)"));
//        itemDao.delMyItem(27);
        reserveDao.delBuyItem(26);

       // InterfaseDao interfaseDaoUser = factory.getInerfaseDao(User.class);

//        System.out.println(userDao.findOneByFacebookId(1003).getId());
//        System.out.println(userDao.checkUser(5, "12-12-1212"));
//        System.out.println(userDao.updateUser(1234, 4321));

//        List<Reserve> res : reserves) {
//            System.out.println(reserve.getItem().getTitle());
//        }
//
//        System.out.println(reserveDao.delBuyItem(1001));
//        System.out.println(reserveDao.addBuyer(25,3333));
//        System.out.println(erves = reserveDao.findReservesByFacebookId(1001);
//        for (Reserve reservereserveDao.delBuyer(23));
//        System.out.println(reserveDao.checkBuyer(23));
//        System.out.println(reserveDao.isBuyed(23, 0));
//        System.out.println(reserveDao.checkIsBuy(23));

    }
}
