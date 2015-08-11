package rest;

import logic.ItemLogic;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

/**
 * Created by admin on 31.07.2015.
 */
@Path("friend{id}")
public class FriendController {

    @Context
    private ServletContext context;
    static public long friend_id;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context HttpServletRequest request,
                               @PathParam("id") long id) {
        friend_id = id;
        return context.getResourceAsStream("/WEB-INF/pages/friend.html");
    }

    @GET
    @Path("getFriendItems")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItem(@Context HttpServletRequest request)
    {
        int page = 0;
        int buy_status = 0;
        return new ItemLogic().getItemList(page,friend_id,buy_status);
    }



}
