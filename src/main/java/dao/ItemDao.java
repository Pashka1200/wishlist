package dao;

import dao.impl.ClassDao;
import general.Factory;
import table.Item;
import table.Reserve;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavlo on 21.07.15.
 */
public class ItemDao extends ClassDao {
    static Factory factory = Factory.getInstance();

    public ItemDao() {
        super(Item.class);
    }

    static InterfaseDao interfaseDaoForItem = factory.getInterfaseDao(Item.class);
    static ReserveDao reserveDao = new ReserveDao();


    //call this method for receive list of items (client or friends)
    public List<Item> getItems(long facebook_id) throws SQLException{
        List<Item> items = new ArrayList<Item>();
            List<Reserve> reserves = new ReserveDao()
                    .findReservesByFacebookId(facebook_id);
        for(Reserve reserve : reserves) {
            items.add(reserve.getItem());
        }
            return items;
    }


    //add new item to the item table
    //call addConnection in ReserveDao
    public long addMyItem(long facebook_id, String title,
                                  String url, String description, String picture) throws  SQLException{

        Item item = new Item();

        //add item with received title, url and description with picture
        item.setTitle(title);
        item.setUrl(url);
        item.setDescription(description);
        item.setPicture(picture);
        interfaseDaoForItem.add(item);

        reserveDao.addConnection(facebook_id, item);

        return item.getId();
    }

    public long addMyItem(long facebook_id, String title,
                                 String url, String description) throws  SQLException{

        Item item = new Item();

        //add item with received title, url and description without picture
        item.setTitle(title);
        item.setUrl(url);
        item.setDescription(description);
        interfaseDaoForItem.add(item);

        reserveDao.addConnection(facebook_id, item);

        return item.getId();
    }


    //update some item in the table item
    public String updateMyItems(long item_id, String title,
                                String url, String description, String picture) throws  SQLException{
        Item item = (Item) interfaseDaoForItem.get(item_id);
        item.setTitle(title);
        item.setUrl(url);
        item.setDescription(description);
        item.setPicture(picture);
        interfaseDaoForItem.update(item);
        return "true";
    }

    public String updateMyItems(long item_id, String title,
                                String url, String description) throws  SQLException{
        Item item = (Item) interfaseDaoForItem.get(item_id);
        item.setTitle(title);
        item.setUrl(url);
        item.setDescription(description);
        interfaseDaoForItem.update(item);
        return "true";
    }


    //thi method delete some item in item table by item_id
    public void delMyItem(long item_id) throws SQLException{
        interfaseDaoForItem.delete(interfaseDaoForItem.get(item_id));
    }




}
