package rest;

import dao.ItemDao;
import logic.ItemLogic;
import logic.Responses;
import org.codehaus.jettison.json.JSONObject;
import table.Item;

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
@Path("item")
public class ItemController {

    @Context
    private ServletContext context;


    @POST
    @Path("getItems")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItems(@Context HttpServletRequest request, String data)
    {
        try {
            JSONObject json = new JSONObject(data);
            int page = json.getInt("page");
            int fb_id = json.getInt("fb_id");
            return new ItemLogic().getItemList(page,fb_id);
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
            int fb_id = json.getInt("fb_id");
            String title = json.getString("title");
            String url = json.getString("url");
            String description = json.getString("description");
            String picture = json.getString("picture");
            int itemId = new ItemDao(Item.class).addMyItem(fb_id,title,url,description,picture);
            jsonObject.put("itemId",itemId);
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
            return jsonObject.toString();

    }

}
