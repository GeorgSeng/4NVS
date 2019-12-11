import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class ArtistEndpointTest {
    private Client client;
    WebTarget webTarget;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8080/ms/api/artist/getAll");
    }

    @Test
    public void fetchArtist(){
        Response response = this.webTarget.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
        String payload = response.readEntity(String.class);
        System.out.println("Request: " + payload);
        assertTrue(payload.startsWith("[{\"id\":1,\"name\":\"AC/DC\"}"));
    }
}
