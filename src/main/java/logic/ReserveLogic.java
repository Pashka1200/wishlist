package logic;

import dao.ReserveDao;
import table.Item;
import table.Reserve;
import table.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pavlo on 11.08.15.
 */
public class ReserveLogic {


    ///////////!!!!!!!!!!
    ///// W A R N I N G
    /////
    /////
    public Map<Item, User> findCleintsItemsByBuyer(long facebook_id, int buy_status) throws SQLException {
        Map itemUser = new HashMap<Item, User>();
        List<Reserve> reserves  = new ReserveDao()
                .findClientByBuyer(facebook_id, buy_status);
        for (Reserve reserve : reserves) {
             itemUser.put(reserve.getItem(), reserve.getClient());
        }
        return itemUser;
    }
}
