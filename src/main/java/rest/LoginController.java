package rest;

import dao.UserDao;
import logic.Responses;
import org.codehaus.jettison.json.JSONObject;
import table.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Path("login")
public class LoginController
{
    @Context
    private ServletContext context;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context HttpServletRequest request) {
            return context.getResourceAsStream("/WEB-INF/pages/login.html");
    }

    @POST
    @Path("check")
    @Consumes(MediaType.APPLICATION_JSON)
    public String getLogin(@Context HttpServletRequest request, String data)
    {

        JSONObject jsonObject = new JSONObject();
        try
        {
            JSONObject json = new JSONObject(data);
            long fb_id = json.getLong("fb_id");
            String date_of_birth = "222";
            UserDao userDao =  new UserDao(User.class);
            String result = userDao.checkUser(fb_id,date_of_birth);

            if (result.equals("new"))
                jsonObject.put("status",result);
            else
                jsonObject.put("status",result);

        }
        catch (Exception e)
        {
            return Responses.JSON_RESPONSE_FALSE;
        }
        return jsonObject.toString();
    }

}