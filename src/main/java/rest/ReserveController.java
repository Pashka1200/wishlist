package rest;

import dao.ReserveDao;
import logic.Responses;
import org.codehaus.jettison.json.JSONObject;
import table.Reserve;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by admin on 02.08.2015.
 */
@Path("reserve")
public class ReserveController {

    @Context
    private ServletContext context;

    static ReserveDao reserveDao = new ReserveDao();

    @POST
    @Path("addBuyer")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAddBuyer(@Context HttpServletRequest request, String data)
    {
        try {
            JSONObject json = new JSONObject(data);
            long buyer_id = json.getLong("buyer_id");
            int item_id = json.getInt("item_id");
            String result = reserveDao.addBuyer(item_id,buyer_id);
            if (result.equals("true"))
                return Responses.JSON_RESPONSE_TRUE;
            else
                return Responses.JSON_RESPONSE_FALSE;
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
    }

    @POST
    @Path("delBuyer")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDelBuyer(@Context HttpServletRequest request, String data)
    {
        try {
            JSONObject json = new JSONObject(data);
            int item_id = json.getInt("item_id");
            String result = reserveDao.delBuyer(item_id);
            if (result.equals("true"))
                return Responses.JSON_RESPONSE_TRUE;
            else
                return Responses.JSON_RESPONSE_FALSE;
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
    }

    @POST
    @Path("checkBuyer")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCheckBuyer(@Context HttpServletRequest request, String data)
    {
        try {
            JSONObject json = new JSONObject(data);
            int item_id = json.getInt("item_id");
            String result = reserveDao.checkBuyer(item_id);
            if (result.equals("true"))
                return Responses.JSON_RESPONSE_TRUE;
            else if (result.equals("empty"))
                return "{\"status\": \"empty\"}";
            else
                return Responses.JSON_RESPONSE_FALSE;
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
    }

    @POST
    @Path("isBuyer")
    @Produces(MediaType.APPLICATION_JSON)
    public String getIsBuyer(@Context HttpServletRequest request, String data)
    {
        try {
            JSONObject json = new JSONObject(data);
            int item_id = json.getInt("item_id");
            int buy_status = json.getInt("buy_status");
            String result = reserveDao.isBuyed(item_id,buy_status);
            if (result.equals("true"))
                return Responses.JSON_RESPONSE_TRUE;
            else
                return Responses.JSON_RESPONSE_FALSE;
        }
        catch (Exception e) {
            return Responses.JSON_RESPONSE_FALSE;
        }
    }
    @POST
    @Path("checkIsBuy")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCheckIsBuy(@Context HttpServletRequest request, String data)
    {
        try {
            JSONObject json = new JSONObject(data);
            int item_id = json.getInt("item_id");
            String result = reserveDao.checkIsBuy(item_id);
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
