import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class App {
    public static void main(String[] args){
        Client client = ClientBuilder.newClient();
        WebTarget tut = client.target("http://localhost:8080/restprimer/api/time");

        Response response = tut.request(MediaType.TEXT_PLAIN).get();
        String payload = response.readEntity(String.class);
        System.out.println("Request: " + payload);

    }
}
