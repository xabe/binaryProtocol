package com.xabe.binary.protocol.it.resource;

import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.config.protobuf.ProtobufProvider;
import com.xabe.binary.protocol.payload.PayloadProtobuf;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ProtobufResourceTest_IT {

    @Test
    public void shouldGetProtofub() throws Exception {
        //Given
        final Client client = ClientBuilder.newClient().register(ProtobufProvider.class);
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/protobuf" );

        //When
        final Response response = client.target(target.getUri())
                .request(MediaTypeExt.APPLICATION_PROTOBUF)
                .header(HttpHeaders.ACCEPT,MediaTypeExt.APPLICATION_PROTOBUF)
                .get();

        //Then
        final PayloadProtobuf.Payload payload = response.readEntity(PayloadProtobuf.Payload.class);
        assertThat(payload, is(notNullValue()));
        assertThat(payload.getName(), is("protobuf"));
        assertThat(payload.getDate(), is(notNullValue()));
        assertThat(payload.getNumber(), is(123123));
        assertThat(payload.getActive(), is(false));
    }

}
