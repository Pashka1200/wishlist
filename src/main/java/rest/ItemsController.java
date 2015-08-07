package rest;

import dao.ItemDao;
import dao.ReserveDao;
import logic.ItemLogic;
import logic.Responses;
import org.codehaus.jettison.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by admin on 31.07.2015.
 */
@Path("items")
public class ItemsController {

    @Context
    private ServletContext context;

    static ItemDao itemDao = new ItemDao();
    static ReserveDao reserveDao = new ReserveDao();

    @POST
    @Path("getItems")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItems(@Context HttpServletRequest request, String data)
    {
        try {
            JSONObject json = new JSONObject(data);
            int page = json.getInt("page");
            long fb_id = json.getLong("fb_id");
            int buy_status = 1;
            return new ItemLogic().getItemList(page,fb_id,buy_status);
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }

    }

    @POST
    @Path("addItem")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAddItem(@Context HttpServletRequest request, String data)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject json = new JSONObject(data);
            long fb_id = json.getLong("fb_id");
            String title = json.getString("title");
            String url = json.getString("url");
            String description = json.getString("description");
            String picture = json.getString("picture");
            long itemId = itemDao.addMyItem(fb_id, title, url, description, picture);
            jsonObject.put("itemId",itemId);
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
            return jsonObject.toString();

    }

    @POST
    @Path("delItem")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDelItem(@Context HttpServletRequest request, String data)
    {
        try {
            JSONObject json = new JSONObject(data);
            //int fb_id = json.getInt("fb_id");
            long item_id = json.getLong("item_id");
            reserveDao.delBuyItem(item_id);
            return Responses.JSON_RESPONSE_TRUE;
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
    }

    @POST
    @Path("updateItem")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUpdateItem(@Context HttpServletRequest request, String data)
    {
        try {
            JSONObject json = new JSONObject(data);
            //int fb_id = json.getInt("fb_id");
            long item_id = json.getLong("item_id");
            String title = json.getString("title");
            String url = json.getString("url");
            String description = json.getString("description");
            String picture = json.getString("picture");
            System.out.println(item_id);
            String result = itemDao.updateMyItems(item_id, title, url, description, picture);
            if (result.equals("true"))
                return Responses.JSON_RESPONSE_TRUE;
            else
                return Responses.JSON_RESPONSE_FALSE;
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
    }


}
