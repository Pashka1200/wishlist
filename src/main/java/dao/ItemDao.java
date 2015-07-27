package dao;

import dao.InterfaseDao;
import dao.impl.ClassDao;
import general.Factory;
import table.Item;
import table.Reserve;
import table.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavlo on 21.07.15.
 */
public class ItemDao<T> extends ClassDao<T> {
    static Factory factory = Factory.getInstance();

    public ItemDao(Class<T> class1) {
        super(class1);
    }

    InterfaseDao interfaseDaoForItem = factory.getInerfaseDao(Item.class);
    InterfaseDao interfaseDaoForReserves = factory.getInerfaseDao(Reserve.class);
    InterfaseDao interfaseDaoForUser = factory.getInerfaseDao(User.class);

    //call this method for receive list of items (client or friends)
    public List<Item> getItems(int facebook_id) throws SQLException{
        List<Item> items = new ArrayList<Item>();
            List<Reserve> reserves = new ReserveDao(Reserve.class)
                    .findReservesByFacebookId(facebook_id);
        for(Reserve reserve : reserves) {
            items.add(reserve.getItem());
        }
            return items;
    }

    //add new item to the item table
    //call addConnection in ReserveDao
    public String addMyItem(int facebook_id, String title,
                            String url, String description) throws  SQLException{

        Item item = new Item();

        //add item with received title, url and description
        item.setTitle(title);
        item.setUrl(url);
        item.setDescription(description);
        interfaseDaoForItem.add(item);

        new ReserveDao(Reserve.class).addConnection(facebook_id, item.getId());

        return "true " + item.getId();
    }

    //update some item in the table item
    public String updateMyItems(int item_id, String title, String url, String description) throws  SQLException{
        Item item = new Item();
        item = (Item) interfaseDaoForItem.get(item_id);
        item.setTitle(title);
        item.setUrl(url);
        item.setDescription(description);
        interfaseDaoForItem.update(item);
        return "true";
    }

    //thi method delete some item in item table by item_id
    public void delMyItem(int item_id) throws SQLException{
        interfaseDaoForItem.delete(interfaseDaoForItem.get(item_id));
    }




}