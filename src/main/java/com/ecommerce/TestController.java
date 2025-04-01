package com.ecommerce;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@Path("/test-db")
public class TestController {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Conexión OK desde el endpoint REST";
    }

    @POST
    @Consumes("application/json")
    public Response example(String jsonBody) {
        return Response.ok("Received JSON: " + jsonBody).build();
    }
}
