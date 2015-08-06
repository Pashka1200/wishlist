package rest;

import logic.ItemLogic;
import logic.Responses;
import org.codehaus.jettison.json.JSONObject;

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

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getPage(@Context HttpServletRequest request,
                               @PathParam("id") long id) {
        return context.getResourceAsStream("/WEB-INF/pages/item.html");
    }
    @POST
    @Path("getItem{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getItem(@Context HttpServletRequest request, @PathParam("id") long id)
    {

        return new ItemLogic().getItemList(id);


    }



}
