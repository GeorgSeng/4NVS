package at.htl.vehicle.rest;

import org.junit.Before;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class VehicleEndpointIT {
    private Client client;
    private WebTarget target;

    @Before
    public void initClient() {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/vehiscle/api/vehicle");
    }

    @Test
    public void fetchVehicle01(){
        Response response = this.target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        String payload = response.readEntity(String.class);
        System.out.println("payload =  " + payload);
        fail();
    }

    @Test
    public void fetchVehicle02(){
        Response response = this.target.request(MediaType.APPLICATION_XML).get();
        assertThat(response.getStatus(), is(200));
        String payload = response.readEntity(String.class);
        System.out.println("payload =  " + payload);
        fail();
    }

    @Test
    public void fetchVehicle03(){
        Response response = this.target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println("payload =  " + payload);

        JsonObject vehicle = payload.getJsonObject(0);
        assertThat(vehicle.getString("brand"), is("Opel 42"));
        assertThat(vehicle.getString("type"), is("Commodore"));
    }

    @Test
    public void Crud(){
        Response response = this.target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
        JsonArray payload = response.readEntity(JsonArray.class);
        System.out.println("payload =  " + payload);

        JsonObject vehicle = payload.getJsonObject(0);
        assertThat(vehicle.getString("brand"), is("Opel 42"));
        assertThat(vehicle.getString("type"), is("Commodore"));

        //Get with id
        JsonObject dedicatedVehicle = this.target
                .path("43")
                .request(MediaType.APPLICATION_JSON)
        assertThat(dedicatedVehicle.getString("brand"), containsString("42"));
        assertThat(dedicatedVehicle.getString("brand"), equalTo("Opel 42"));

        Response deleteResponse = this.target
                .path("42")
                .request(MediaType.APPLICATION_JSON)
                .delete();
        assertThat(deleteResponse.getStatus(), is(Response.Status.OK));
    }
}
