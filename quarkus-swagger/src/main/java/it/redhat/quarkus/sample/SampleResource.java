package it.redhat.quarkus.sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/foo")
public class SampleResource {

    @GET
    @Path("/bar")
    public String hello() {
        return "Hello, World!";
    }
   
}

