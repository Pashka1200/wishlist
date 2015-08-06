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
@Path("item{id}")
public class ItemController {

    @Context
    private ServletContext context;
    static public long item_id;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context HttpServletRequest request,
                               @PathParam("id") long id) {
        item_id = id;
        return context.getResourceAsStream("/WEB-INF/pages/item.html");
    }

    @GET
    @Path("getItem")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItem(@Context HttpServletRequest request)
    {
        return new ItemLogic().getItem(item_id);
    }



}
