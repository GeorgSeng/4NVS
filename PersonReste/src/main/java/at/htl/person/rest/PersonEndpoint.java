package at.htl.person.rest;

import at.htl.person.model.Person;

import javax.json.JsonArray;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("person")
public class PersonEndpoint {

    public PersonEndpoint(){}

    @PersistenceCo
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


    public Response createPerson(final @Context UriInfo uriInfo, JsonValue jsonValue){

        if(jsonValue.getValueType() == JsonValue.ValueType.ARRAY){
            JsonArray jsonArray = jsonValue.asJsonArray();
            for (JsonValue value : jsonArray){
                System.out.println(value.toString());
                String name = value.asJsonObject().getString("name");
                System.out.println("Name: " + name);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
                LocalDate dateOfBirth = LocalDate.parse(value.asJsonObject().getString("dob"), dtf);
                System.out.println("DayOfBirth: " + dateOfBirth);
                em.merge(new Person(name,dateOfBirth));
            }
        } else {
            System.out.println("I am a Object");
        }
        return Response.ok().build();
    }

    //Paht nich nötig weil nur eine psot methode exisitiert
    @POST
    @Path("x")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createPerson2(final @Context UriInfo uriInfo, Person person){
        Person p = em.merge(person);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/" + p.getId())
                .build();
        return Response.created(uri).header("operation","object create").build();
    }
}
