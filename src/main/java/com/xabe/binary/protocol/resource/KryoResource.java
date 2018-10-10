package com.xabe.binary.protocol.resource;

import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.payload.Payload;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.time.LocalDateTime;

@Singleton
@Path("/v1")
@Produces(MediaTypeExt.APPLICATION_KRYO)
@Consumes(MediaTypeExt.APPLICATION_KRYO)
public class KryoResource {

    @Path("/kryo")
    @GET
    public Payload getPayload() {
        return new Payload(LocalDateTime.now(),"kryo",123456,true);
    }

}
