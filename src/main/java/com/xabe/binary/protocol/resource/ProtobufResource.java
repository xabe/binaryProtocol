package com.xabe.binary.protocol.resource;

import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.payload.PayloadProtubuf;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.time.Instant;

@Singleton
@Path("/v1")
@Produces(MediaTypeExt.APPLICATION_PROTOBUF)
@Consumes(MediaTypeExt.APPLICATION_PROTOBUF)
public class ProtobufResource {

    @Path("/protobuf")
    @GET
    public PayloadProtubuf.Payload getPayload() {
        return PayloadProtubuf.Payload.newBuilder().setName("protobuf").setActive(false).setNumber(123123).setDate(PayloadProtubuf.Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond()).build()).build();
    }
}
