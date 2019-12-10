package at.htl.demoTest101.rest;

import at.htl.demoTest101.model.Album;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("album")
public class AlbumEndpoint {
    public AlbumEndpoint(){}

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML})
    public List<Album> findAll(){
        return em
                .createNamedQuery("Album.findAll", Album.class)
                .getResultList();
    }
}
