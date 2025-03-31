package com.ecommerce;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test-db")
public class TestController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Conexión OK desde el endpoint REST";
    }
}
