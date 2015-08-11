
package logic;

import dao.ItemDao;
        import org.codehaus.jettison.json.JSONObject;
        import table.Item;
import table.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 31.07.2015.
 */
public class ItemLogic {

    private List<Item> list;
    private Map<Item, User> userItemMap;
    private org.codehaus.jettison.json.JSONArray jsonObject = new org.codehaus.jettison.json.JSONArray();
    private JSONObject json;
    private JSONObject jsonObject1 = new JSONObject();
    private Item item;
    private User user;

    public String getItemList(int page, long facebook_id, int buy_status) {
        try {
            list = new ItemDao().getItems(facebook_id, buy_status);

            if (list.isEmpty())
            {
                return Responses.JSON_RESPONSE_FALSE;
            }
            int size = list.size();
            int startPosition = page * 10;
            int i;

            if (startPosition < size && size > 10) {
                if (size - startPosition > 10) {
                    for (i = startPosition; i < startPosition + 10; i++) {
                        json = new JSONObject();
                        item = list.get(i);
                        json.put("id", item.getId());
                        json.put("title", item.getTitle());
                        json.put("url", item.getUrl());
                        json.put("description", item.getDescription());
                        json.put("picture",item.getPicture());
                        jsonObject1.put("isEnd", false);
                        jsonObject.put(json);
                        json = null;
                    }
                } else if (size - startPosition < 10) {
                    for (i = startPosition; i < size; i++) {
                        json = new JSONObject();
                        item = list.get(i);
                        json.put("id", item.getId());
                        json.put("title", item.getTitle());
                        json.put("url", item.getUrl());
                        json.put("description", item.getDescription());
                        json.put("picture",item.getPicture());
                        jsonObject1.put("isEnd", false);
                        jsonObject.put(json);
                        json = null;
                    }
                }
            }
            else if (size <= 10 && startPosition < 10)
            {
                for (i = 0; i < size; i++) {
                    json = new JSONObject();
                    item = list.get(i);
                    json.put("id", item.getId());
                    json.put("title", item.getTitle());
                    json.put("url", item.getUrl());
                    json.put("description", item.getDescription());
                    json.put("picture",item.getPicture());
                    jsonObject1.put("isEnd", false);
                    jsonObject.put(json);
                    json = null;
                }
            }

            jsonObject1.put("items", jsonObject);
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
        return jsonObject1.toString();
    }

    public String getToBuyItemsList(int page, long facebook_id, int buy_status) {
        try {
            userItemMap = new ReserveLogic().findCleintsItemsByBuyer(facebook_id, buy_status);

            if (userItemMap.isEmpty())
            {
                return Responses.JSON_RESPONSE_FALSE;
            }
            int size = userItemMap.size();
            int startPosition = page * 10;
            int i;
            Map.Entry entry;

            if (startPosition < size && size > 10) {
                if (size - startPosition > 10) {
                    for (i = startPosition; i < startPosition + 10; i++) {
                        json = new JSONObject();
                        entry = (Map.Entry) userItemMap.entrySet();
                        item = (Item) entry.getKey();
                        json.put("id", item.getId());
                        json.put("title", item.getTitle());
                        json.put("url", item.getUrl());
                        json.put("description", item.getDescription());
                        json.put("picture",item.getPicture());
                        user = (User) entry.getValue();
                        json.put("facebook_id", user.getFacebookId());
                        jsonObject1.put("isEnd", false);
                        jsonObject.put(json);
                        json = null;
                    }
                } else if (size - startPosition < 10) {
                    for (i = startPosition; i < size; i++) {
                        json = new JSONObject();
                        entry = (Map.Entry) userItemMap.entrySet();
                        item = (Item) entry.getKey();
                        json.put("id", item.getId());
                        json.put("title", item.getTitle());
                        json.put("url", item.getUrl());
                        json.put("description", item.getDescription());
                        json.put("picture",item.getPicture());
                        user = (User) entry.getValue();
                        json.put("facebook_id", user.getFacebookId());
                        jsonObject1.put("isEnd", false);
                        jsonObject.put(json);
                        json = null;
                    }
                }
            }
            else if (size <= 10 && startPosition < 10)
            {
                for (i = 0; i < size; i++) {
                    json = new JSONObject();
                    entry = (Map.Entry) userItemMap.entrySet();
                    item = (Item) entry.getKey();
                    json.put("id", item.getId());
                    json.put("title", item.getTitle());
                    json.put("url", item.getUrl());
                    json.put("description", item.getDescription());
                    json.put("picture",item.getPicture());
                    user = (User) entry.getValue();
                    json.put("facebook_id", user.getFacebookId());
                    jsonObject1.put("isEnd", false);
                    jsonObject.put(json);
                    json = null;
                }
            }

            jsonObject1.put("items", jsonObject);
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
        return jsonObject1.toString();
    }

//    public String getReserveItemsList(int page, long facebook_id) {
//        try {
//            list = new ItemDao().findCleintsItemsByBuyer(facebook_id);
//
//            if (list.isEmpty())
//            {
//                return Responses.JSON_RESPONSE_FALSE;
//            }
//            int size = list.size();
//            int startPosition = page * 10;
//            int i;
//
//            if (startPosition < size && size > 10) {
//                if (size - startPosition > 10) {
//                    for (i = startPosition; i < startPosition + 10; i++) {
//                        json = new JSONObject();
//                        item = list.get(i);
//                        json.put("id", item.getId());
//                        json.put("title", item.getTitle());
//                        json.put("url", item.getUrl());
//                        json.put("description", item.getDescription());
//                        json.put("picture",item.getPicture());
//                        jsonObject1.put("isEnd", false);
//                        jsonObject.put(json);
//                        json = null;
//                    }
//                } else if (size - startPosition < 10) {
//                    for (i = startPosition; i < size; i++) {
//                        json = new JSONObject();
//                        item = list.get(i);
//                        json.put("id", item.getId());
//                        json.put("title", item.getTitle());
//                        json.put("url", item.getUrl());
//                        json.put("description", item.getDescription());
//                        json.put("picture",item.getPicture());
//                        jsonObject1.put("isEnd", false);
//                        jsonObject.put(json);
//                        json = null;
//                    }
//                }
//            }
//            else if (size <= 10 && startPosition < 10)
//            {
//                for (i = 0; i < size; i++) {
//                    json = new JSONObject();
//                    item = list.get(i);
//                    json.put("id", item.getId());
//                    json.put("title", item.getTitle());
//                    json.put("url", item.getUrl());
//                    json.put("description", item.getDescription());
//                    json.put("picture",item.getPicture());
//                    jsonObject1.put("isEnd", false);
//                    jsonObject.put(json);
//                    json = null;
//                }
//            }
//
//            jsonObject1.put("items", jsonObject);
//        }
//        catch (Exception e) {
//            return Responses.JSON_RESPONSE_FALSE;
//        }
//        return jsonObject1.toString();
//    }

    public String getItem(long item_id) {

        try {
            item = new ItemDao().getItem(item_id);
            json = new JSONObject();
            json.put("id", item.getId());
            json.put("title", item.getTitle());
            json.put("url", item.getUrl());
            json.put("description", item.getDescription());
            json.put("picture",item.getPicture());
           // jsonObject.put(json);
            jsonObject1.put("item", json);
            json = null;
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
        return jsonObject1.toString();
    }
}
