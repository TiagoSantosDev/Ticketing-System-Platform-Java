package com.hascode.jolt;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class SampleRestResource {

    @GET
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData() {
        try {
            String json = new String(
                Files.readAllBytes(
                    Paths.get(getClass().getClassLoader().getResource("input.json").toURI())));
            return Response.ok(json).build();
        } catch (IOException | URISyntaxException e) {
        }
        return Response.serverError().build();
    }
}
