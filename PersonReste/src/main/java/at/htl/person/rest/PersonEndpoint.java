package at.htl.person.rest;

import at.htl.person.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("person")
public class PersonEndpoint {

    public PersonEndpoint(){}

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Person> findAll() {
        return em
                .createNamedQuery("Person.findAll",Person.class)
                .getResultList();
    }

/*    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person findByName(@QueryParam("name") String name) {
        return em
                .createNamedQuery("Person.findByName",Person.class)
                .setParameter("NAME", name)
                .getSingleResult();
    }*/

    @GET
    @Path("demo")
    public Response returnResponse() {
        List<Person> person = em.
                createNamedQuery("Person.findAll", Person.class)
                .getResultList();
        return Response.status(Response.Status.OK)
                .header("Chiara", "Christoph")
                .entity(person).build();
    }
    //Paht nich nötig weil nur eine psot methode exisitiert
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPerson(@Context HttpHeaders httpHeaders, Person person){
        Person p = em.merge(person);
        return Response.accepted().header("operation","object create").build();
    }
}
