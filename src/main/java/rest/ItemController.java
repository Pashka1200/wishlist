package rest;

import dao.ItemDao;
import logic.ItemLogic;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
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

    static ItemDao itemDao = new ItemDao();


    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context HttpServletRequest request,
                               @PathParam("id") long id) {
        return context.getResourceAsStream("/WEB-INF/pages/item.html");
    }

    @GET
    @Path("getItem{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItem(@Context HttpServletRequest request, @PathParam("id") long id)
    {
        return new ItemLogic().getItem(id);
    }



}
