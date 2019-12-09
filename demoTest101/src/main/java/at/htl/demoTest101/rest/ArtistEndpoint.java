package at.htl.demoTest101.rest;

import at.htl.demoTest101.model.Artist;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("artist")
public class ArtistEndpoint {

    public ArtistEndpoint(){}

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Artist> findDall(){
        return em
                .createNamedQuery("Artist.findAll", Artist.class)
                .getResultList();
    }
}
