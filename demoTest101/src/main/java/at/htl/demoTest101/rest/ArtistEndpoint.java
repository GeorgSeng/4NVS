package at.htl.demoTest101.rest;

import at.htl.demoTest101.model.Artist;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("artist")
public class ArtistEndpoint {

    public ArtistEndpoint(){}

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public List<Artist> findAll() {
        return em
                .createNamedQuery("Artist.findAll",Artist.class)
                .getResultList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAll")
    public Response returnResponse(){
        List<Artist> artists =em
                .createNamedQuery("Artist.findAll", Artist.class)
                .getResultList();

        return Response.status(Response.Status.OK)
                .header("It","Gos")
                .entity(artists).build();
    }
}
