package com.xabe.binary.protocol.resource;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/v1")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class StatusResource {

    public StatusResource() {
    }

    @Path("/status")
    @GET
    public String getStatus() {
        return "OK";
    }
}
