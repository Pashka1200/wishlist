package logic;

import dao.ItemDao;
import dao.ReserveDao;
import org.codehaus.jettison.json.JSONObject;
import table.Item;
import table.Reserve;

import java.util.List;

/**
 * Created by admin on 31.07.2015.
 */
public class ItemLogic {
    private List<Item> list;
    private org.codehaus.jettison.json.JSONArray jsonObject = new org.codehaus.jettison.json.JSONArray();
    private JSONObject json;
    private JSONObject jsonObject1 = new JSONObject();
    private Item item;

    ItemDao itemDao = new ItemDao(Item.class);
    ReserveDao reserveDao = new ReserveDao(Reserve.class);

    public String getItemList(int page, int facebook_id) {
        try {
            list = itemDao.getItems(facebook_id);

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
}