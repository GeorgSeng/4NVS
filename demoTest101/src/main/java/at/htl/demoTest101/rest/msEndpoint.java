package at.htl.demoTest101.rest;

import at.htl.demoTest101.model.Track;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("ms")
public class msEndpoint {
    public msEndpoint(){}

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("all")
    public Response returnResponse(){

        return null;
    }
}
