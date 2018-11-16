package com.xabe.binary.protocol.resource;

import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.payload.Payload;

import javax.inject.Singleton;
import javax.ws.rs.*;
import java.time.LocalDateTime;

@Singleton
@Path("/v1")
@Produces(MediaTypeExt.APPLICATION_JSON)
@Consumes(MediaTypeExt.APPLICATION_JSON)
public class JsonResource {

    @GET
    @Path("/json")
    public Payload getPayload() {
        return new Payload(LocalDateTime.now(), "json", 123456, true);
    }

    @DELETE
    @Path("/json")
    public Payload getPayloadDelete() {
        return new Payload(LocalDateTime.now(), "json", 123456, true);
    }
}
